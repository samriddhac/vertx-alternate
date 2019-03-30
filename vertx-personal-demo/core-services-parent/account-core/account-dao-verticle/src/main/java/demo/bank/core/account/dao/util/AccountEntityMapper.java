package demo.bank.core.account.dao.util;

import java.util.List;
import java.util.stream.Collectors;

import demo.bank.core.account.dao.entity.AccountEntity;
import demo.bank.core.account.dao.entity.AccountTypeEntity;
import demo.bank.core.account.dao.entity.CustomerEntity;
import io.vertx.core.json.JsonArray;

public class AccountEntityMapper {

	public AccountEntity mapRow(JsonArray row) {
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(row.getInteger(0));
		accountEntity.setNumber(row.getString(1));
		AccountTypeEntity accountType = new AccountTypeEntity();
		accountType.setId(row.getInteger(3));
		accountType.setDescription(row.getString(4));
		CustomerEntity customer = new CustomerEntity();
		customer.setId(row.getInteger(5));
		customer.setName(row.getString(6));
		accountEntity.setType(accountType);
		accountEntity.setCustomer(customer);
		accountEntity.setAllowScheduledTransactions(row.getString(2));
		return accountEntity;
	}
	
	public List<AccountEntity> mapRows(List<JsonArray> rows) {
		return rows.stream().map(this::mapRow).collect(Collectors.toList());
	}
}
