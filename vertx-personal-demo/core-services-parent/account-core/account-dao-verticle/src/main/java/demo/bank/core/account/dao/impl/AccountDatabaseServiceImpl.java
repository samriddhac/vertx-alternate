package demo.bank.core.account.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bank.core.account.dao.AccountDatabaseService;
import demo.bank.core.account.dao.util.AccountBalanceEntityMapper;
import demo.bank.core.account.dao.util.AccountEntityMapper;
import demo.bank.core.account.dao.util.AccountEntityToAccountMapper;
import demo.bank.core.account.dao.util.IConstants;
import demo.bank.core.account.dao.util.IQuery;
import demo.bank.core.account.model.Account;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;

public class AccountDatabaseServiceImpl implements AccountDatabaseService {
	
	private final static Logger LOG = LoggerFactory.getLogger(AccountDatabaseServiceImpl.class);

	JDBCClient jdbcClient;
	AccountEntityMapper accountEntityMapper;
	AccountBalanceEntityMapper accountBalanceEntityMapper;

	public AccountDatabaseServiceImpl(JDBCClient jdbcClient, Handler<AsyncResult<AccountDatabaseService>> readyHandler) {
		this.jdbcClient = jdbcClient;
		this.accountEntityMapper = new AccountEntityMapper();
		this.accountBalanceEntityMapper = new AccountBalanceEntityMapper();
		readyHandler.handle(Future.succeededFuture(this));
	}

	@Override
	public AccountDatabaseService getAccount(Long id, Handler<AsyncResult<Account>> resultHandler) {
		jdbcClient.queryWithParams(IQuery.ACCOUNT.FIND_BY_ACCID, new JsonArray().add(id), response -> {
			Account account = null;
			if (response.succeeded()) {
				Optional<JsonArray> row = response.result().getResults()
						.stream()
						.findFirst();
				if(row.isPresent()) {
					JsonArray rowItem = (JsonArray) row.get();
					account = AccountEntityToAccountMapper
							.getInstance()
							.convertAccountEntitytoAccount(accountEntityMapper.mapRow(rowItem));
				}
				resultHandler.handle(Future.succeededFuture(account));
			}
			else {
				resultHandler.handle(Future.failedFuture(response.cause()));
			}
		});
		return this;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public AccountDatabaseService getAccounts(String user, Handler<AsyncResult<List<Account>>> resultHandler) {
		jdbcClient.queryWithParams(IQuery.ACCOUNT.FIND_BY_CUSTID, new JsonArray().add(user), response -> {
			List<Account> accounts = new ArrayList<Account>();
			if (response.succeeded()) {
				List<JsonArray> rows = response.result().getResults();
				if(rows!=null && !rows.isEmpty()) {
					accounts = AccountEntityToAccountMapper
					.getInstance()
					.convertAccountEntitiestoAccounts(accountEntityMapper.mapRows(rows));
					List<Future> balanceFutures = new ArrayList<Future>();
					accounts.forEach(account -> {
						Future<Void> balanceFuture = getBalance(account);
						balanceFutures.add(balanceFuture);
					});
					final List<Account> accountList = accounts;
					CompositeFuture.all(balanceFutures).setHandler(ar -> {
						resultHandler.handle(Future.succeededFuture(accountList));
					});
				}
			}
			else {
				resultHandler.handle(Future.failedFuture(response.cause()));
			}
		});
		return this;
	}

	public Future<Void> getBalance(Account account) {
		Future<Void> future = Future.future();
		jdbcClient.queryWithParams(IQuery.ACCOUNT.FIND_BALANCE, new JsonArray().add(account.getId()), response -> {
			if(response.succeeded()) {
				Double balance = response.result().getResults()
						.stream()
						.map(row -> accountBalanceEntityMapper.mapRow(row))
						.map((Map<String, Double> result) ->{
							double accountBalance = 0.0;
							String key = result.keySet().iterator().next();
							if (key.equalsIgnoreCase(IConstants.TransactionType.CREDIT.toString())) {
								accountBalance = result.get(key);
							} else if (key.equalsIgnoreCase(IConstants.TransactionType.DEBIT.toString())) {
								accountBalance = - result.get(key);
							}
							return accountBalance;
						})
						.mapToDouble(Double::doubleValue)
						.sum();
				account.setBalance(balance);
				future.complete();
			}
			else {
				future.fail(response.cause());
			}
		});
		return future;
	}

}
