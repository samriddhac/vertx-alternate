package demo.bank.core.account.api;

import demo.bank.core.account.dao.AccountDatabaseService;
import io.vertx.core.Vertx;

public class AccountServiceProxyHelper {

	public static final String CONFIG_ACCOUNTDB_QUEUE = "accountdb.queue";
	
	private static AccountServiceProxyHelper accountServiceProxyHelper;
	private Vertx vertx;
	private AccountDatabaseService accountDatabaseService;
	
	private AccountServiceProxyHelper( ) {
		
	}
	
	public static AccountServiceProxyHelper getInstance() {
		if(accountServiceProxyHelper == null) {
			accountServiceProxyHelper = new AccountServiceProxyHelper();
		}
		return accountServiceProxyHelper;
	}
	
	public Vertx getVertx() {
		if(vertx == null) {
			vertx = Vertx.vertx();
		}
		return vertx;
	}
	
	public AccountDatabaseService getAccountDatabaseService() {
		if(accountDatabaseService == null) {
			accountDatabaseService = AccountDatabaseService.createProxy(getVertx(), CONFIG_ACCOUNTDB_QUEUE);
		}
		return accountDatabaseService;
	}
}
