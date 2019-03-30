package demo.bank.core.account.dao;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ServiceBinder;

public class AccountDataBaseVerticle extends AbstractVerticle {
	
	private JDBCClient dbClient;
	
	public static final String CONFIG_WIKIDB_QUEUE = "accountdb.queue";

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		ConfigStoreOptions configOptions = new ConfigStoreOptions()
				  .setType("file")
				  .setFormat("properties")
				  .setConfig(new JsonObject().put("path", "account-service-datasource.properties"));
		
		ConfigRetriever retriever = ConfigRetriever.create(vertx,new ConfigRetrieverOptions().addStore(configOptions));
		retriever.getConfig((json) -> {
			JsonObject config = json.result();
			dbClient = JDBCClient.createShared(vertx, new JsonObject()
					.put("url", config.getString("datasource.url"))
					.put("driver_class", config.getString("datasource.driverClassName"))
					.put("user", config.getString("datasource.username"))
					.put("password", config.getString("datasource.password"))
					.put("max_pool_size", config.getInteger("datasource.maximumPoolSize")));
			
			AccountDatabaseService.create(dbClient, (ready) -> {
				if(ready.succeeded()) {
					ServiceBinder binder = new ServiceBinder(vertx);
					binder.setAddress(CONFIG_WIKIDB_QUEUE)
					.register(AccountDatabaseService.class, ready.result());
					startFuture.complete();
				}
				else {
					startFuture.fail(ready.cause());
				}
			});
		});
		
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		super.stop(stopFuture);
	}
	
}
