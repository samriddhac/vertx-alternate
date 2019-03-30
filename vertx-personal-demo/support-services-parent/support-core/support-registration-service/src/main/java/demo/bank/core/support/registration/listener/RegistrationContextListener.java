/**
 * 
 */
package demo.bank.core.support.registration.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.bank.core.support.common.AnnotationProcessorUtil;
import demo.bank.core.support.common.ServiceContractModel;
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
			ServiceContractModel serviceContractModel = AnnotationProcessorUtil.processAnnotation(ResourceConfig.class, Register.class);
			LOG.info("serviceContractModel "+serviceContractModel);
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
