/**
 * 
 */
package demo.bank.core.support.registration.listener;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bank.core.support.registration.annotation.Register;

/**
 * @author 773704
 *
 */
@WebListener
public class RegistrationContextListener implements ServletContextListener {
	
	private final static Logger LOG = LoggerFactory.getLogger(RegistrationContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Reflections reflections = new Reflections("demo.bank.core");
			Set<Class<? extends ResourceConfig>> subTypes = reflections.getSubTypesOf(ResourceConfig.class);
			Class<? extends ResourceConfig> resourceConfigClass = subTypes.iterator().next();
			Register registeredAnnotation = resourceConfigClass.getAnnotation(Register.class);
			LOG.info("name ::: "+registeredAnnotation.name());
			LOG.info("id ::: "+registeredAnnotation.id());
			LOG.info("propertySource ::: "+registeredAnnotation.propertySource());
		}
		catch (Exception e) {
			LOG.error("Error ",e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
