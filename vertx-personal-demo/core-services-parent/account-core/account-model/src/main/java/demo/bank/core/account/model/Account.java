package demo.bank.core.account.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Account entity
 *
 */
@DataObject(generateConverter = true, inheritConverter=true)
@ApiModel(description = "Represents an account of the system")
@JsonInclude(Include.NON_NULL)
public class Account {

	@ApiModelProperty(value = "The ID of the account", required = true)
    @NotNull(message = "account ID cannot be null")
	private Long id;
	@ApiModelProperty(value = "The account number", required = true)
	@NotNull(message = "account number cannot be null")
	private String number;
	@ApiModelProperty(value = "The account type", required = true)
	private AccountType type;
	@ApiModelProperty(value = "The account balance", required = true)
	private Double balance;
	@ApiModelProperty(value = "The customer holding the account", required = true)
	private Customer customer;
	@ApiModelProperty(value = "The flag representing if scheduled transactions are allowed", required = true)
	@NotNull(message = "allow scheduled transaction for account cannot be null")
	private String allowScheduledTransactions;

	public Account() {
	}
	
	public Account(JsonObject jsonObject) {
		AccountConverter.fromJson(jsonObject, this);
	}

	public Account(Long id, String number, AccountType type, Double balance, Customer customer,
			String allowScheduledTransactions) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
		this.balance = balance;
		this.customer = customer;
		this.allowScheduledTransactions = allowScheduledTransactions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@JsonProperty("allow_scheduled_transactions")
	public String getAllowScheduledTransactions() {
		return allowScheduledTransactions;
	}

	public void setAllowScheduledTransactions(String allowScheduledTransactions) {
		this.allowScheduledTransactions = allowScheduledTransactions;
	}
	
	public JsonObject toJson() {
		JsonObject jsonObject = new JsonObject();
		AccountConverter.toJson(this, jsonObject);
		return jsonObject;
	}

}
