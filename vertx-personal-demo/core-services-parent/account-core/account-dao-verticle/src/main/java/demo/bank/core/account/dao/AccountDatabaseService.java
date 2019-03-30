package demo.bank.core.account.dao;

import java.util.List;

import demo.bank.core.account.dao.impl.AccountDatabaseServiceImpl;
import demo.bank.core.account.model.Account;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;

@ProxyGen
@VertxGen
public interface AccountDatabaseService {

	@Fluent
	AccountDatabaseService getAccount(Long id, Handler<AsyncResult<Account>> resultHandler);
	
	@Fluent
	AccountDatabaseService getAccounts(String user, Handler<AsyncResult<List<Account>>> resultHandler);
	
	@GenIgnore
	static AccountDatabaseService create(JDBCClient jdbcClient,Handler<AsyncResult<AccountDatabaseService>> readyHandler) {
		return new AccountDatabaseServiceImpl(jdbcClient, readyHandler);
	}
	
	@GenIgnore
	static AccountDatabaseService createProxy(Vertx vertx, String address) {
		return new AccountDatabaseServiceVertxEBProxy(vertx, address);
	}
}
