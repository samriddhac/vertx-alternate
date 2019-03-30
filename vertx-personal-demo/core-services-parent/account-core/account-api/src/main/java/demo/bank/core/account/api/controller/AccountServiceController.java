
package demo.bank.core.account.api.controller;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bank.core.account.api.AccountServiceProxyHelper;
import demo.bank.core.account.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Name :- AccountServiceController
 * Purpose :- expose Rest services for reference POC implementation.
 */
@Path("/accounts")
@Api("account-service")
public class AccountServiceController {

	private final static Logger LOG = LoggerFactory.getLogger(AccountServiceController.class);
	
	/**
	 * Rest service to retrieve list of accounts for a user.
	 * This reference implementation test jdbc integration, which returns list of resources. 
	 * Example service url :- GET /accounts?user=300
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "retrieve a accounts details", tags = "GET /accounts", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved accounts list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public void getAccounts(
			@ApiParam("account holder") @QueryParam("user") @NotNull String user, 
			@Suspended final AsyncResponse response) {
		
		LOG.info("Received Request getAccounts");
		AccountServiceProxyHelper.getInstance().getAccountDatabaseService().getAccounts(user, (results) -> {
			if (results.succeeded()) {
				List<Account> accounts = (List<Account>) results.result();
				if(accounts != null && !accounts.isEmpty()) {
					response.resume(Response.status(HttpURLConnection.HTTP_OK).entity(accounts).build());
				}
				else {
					response.resume(Response.status(HttpURLConnection.HTTP_NOT_FOUND).build());
				}
			}
			else {
				response.resume(Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build());
			}
		});
	}

	/**
	 * Rest service to retrieve an account given an account id.
	 * This reference implementation test jdbc integration, which returns single resource. 
	 * Example service url :- GET /accounts/700
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "retrieve a account details", tags = "GET /accounts/{id}", response = Void.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved account list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public void getAccount(@PathParam("id") @NotNull Long accountId, @Suspended final AsyncResponse response) {
		
		LOG.info("Received Request getAccount");
		AccountServiceProxyHelper.getInstance().getAccountDatabaseService().getAccount(accountId, (results)-> {
			if (results.succeeded()) {
				Account account = (Account) results.result();
				if(!Objects.isNull(account))
					response.resume(Response.status(HttpURLConnection.HTTP_OK).entity(account).build());
				else
					response.resume(Response.status(HttpURLConnection.HTTP_NOT_FOUND).build());
			}
			else {
				response.resume(Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build());
			}
		});
	}

}
