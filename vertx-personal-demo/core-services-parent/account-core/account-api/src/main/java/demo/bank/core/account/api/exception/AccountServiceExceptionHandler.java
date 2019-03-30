package demo.bank.core.account.api.exception;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Name :- AccountServiceExceptionHandler
 * Purpose :- Handles runtime exceptions and provide exception information to the consumer.
 */
@Provider
public class AccountServiceExceptionHandler implements ExceptionMapper<Exception> {
	private static final Logger LOG = LoggerFactory.getLogger(AccountServiceExceptionHandler.class);
	
	

	@Override
	public Response toResponse(Exception exception) {
		return exceptionHandler(exception);
	}

	private Response exceptionHandler(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat("EEEEE MMMMM yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
		exception.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		exception.setError(Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
		exception.setMessage(ex.getLocalizedMessage());
		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(exception).build();
	}

}
