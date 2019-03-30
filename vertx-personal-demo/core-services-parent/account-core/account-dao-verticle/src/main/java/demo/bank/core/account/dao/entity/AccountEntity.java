package demo.bank.core.account.dao.entity;

public class AccountEntity {


    private Integer id;
 
    private String number;

    private String allowScheduledTransactions;
 
    private AccountTypeEntity type;
    
    private CustomerEntity customer;
    
	public AccountEntity() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAllowScheduledTransactions() {
		return allowScheduledTransactions;
	}

	public void setAllowScheduledTransactions(String allowScheduledTransactions) {
		this.allowScheduledTransactions = allowScheduledTransactions;
	}

	public AccountTypeEntity getType() {
		return type;
	}

	public void setType(AccountTypeEntity type) {
		this.type = type;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	
}
