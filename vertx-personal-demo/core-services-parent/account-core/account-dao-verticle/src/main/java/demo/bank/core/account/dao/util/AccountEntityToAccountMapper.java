package demo.bank.core.account.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import demo.bank.core.account.dao.entity.AccountEntity;
import demo.bank.core.account.model.Account;

public class AccountEntityToAccountMapper {

	private static AccountEntityToAccountMapper accountEntityToAccountMapper;
	private ModelMapper modelMapper;
	
	private AccountEntityToAccountMapper( ) {
		
	}
	
	public static AccountEntityToAccountMapper getInstance() {
		if(accountEntityToAccountMapper == null) {
			accountEntityToAccountMapper = new AccountEntityToAccountMapper();
		}
		return accountEntityToAccountMapper;
	}

	public ModelMapper getModelMapper() {
		if(modelMapper == null) {
			modelMapper = new ModelMapper();
		}
		return modelMapper;
	}
	
	public Account convertAccountEntitytoAccount(AccountEntity accountEntity) {
		return getModelMapper().map(accountEntity, Account.class);
	}
	
	public List<Account> convertAccountEntitiestoAccounts(List<AccountEntity> accountEntities) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountEntities.stream()
				.map((entity)->{
					return convertAccountEntitytoAccount(entity);
				})
				.collect(Collectors.toList());
		return accounts;
	}
}
