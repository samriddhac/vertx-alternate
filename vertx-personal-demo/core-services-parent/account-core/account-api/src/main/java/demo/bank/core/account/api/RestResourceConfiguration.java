package demo.bank.core.account.api;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;

import demo.bank.core.account.dao.AccountDataBaseVerticle;
import demo.bank.core.support.registration.annotation.Register;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * Name :- RestResourceConfiguration
 * Purpose :- To configure the jax-rs/jersey 2 application , and providing the 
 * packages to be scanned for rest resources.
 * Also build a bridge between jax-rs and vert.x verticles.
 */
@Register(name="account-service")
@ApplicationPath("/1.0")
public class RestResourceConfiguration extends ResourceConfig {

	@Inject
	public RestResourceConfiguration(ServiceLocator serviceLocator) {
		packages("demo.bank.core.account.api");
		deployVerticles();
	}
	
	private void deployVerticles() {
		Vertx vertx = AccountServiceProxyHelper.getInstance().getVertx();
		deployDataBaseVerticles(vertx);
	}
	
	private void deployDataBaseVerticles(Vertx vertx) {
		Future<String> dbVerticleDeployment = Future.future();
		DeploymentOptions options =  new DeploymentOptions();
		options.setInstances(1);
		vertx.deployVerticle(new AccountDataBaseVerticle(), options, dbVerticleDeployment.completer());
	}
}
