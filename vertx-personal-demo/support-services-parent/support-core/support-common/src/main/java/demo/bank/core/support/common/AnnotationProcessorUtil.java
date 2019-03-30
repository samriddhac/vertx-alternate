package demo.bank.core.support.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class AnnotationProcessorUtil {
	private final static Logger LOG = LoggerFactory.getLogger(AnnotationProcessorUtil.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ServiceContractModel processAnnotation(Class source, Class targetAnnotation) {
		ServiceContractModel serviceContractModel = new ServiceContractModel();
		try {
			Reflections reflections = new Reflections("demo.bank.core");
			Set<Class<? extends Object>> subTypes = reflections.getSubTypesOf(source);
			Class<? extends Object> resourceConfigClass = subTypes.iterator().next();
			Annotation registeredAnnotation = resourceConfigClass.getAnnotation(targetAnnotation);
			Arrays.asList(registeredAnnotation.getClass().getMethods())
				.forEach(method -> {
					try {
						IServiceContract.PROPERTY property = IServiceContract.PROPERTY.fromString(method.getName());
						if(!Objects.isNull(property)) {
							switch(property) {
							case ID:
								Object idObject = method.invoke(registeredAnnotation);
								if(isValidObjectReference(idObject))
									serviceContractModel.setId(idObject.toString());
								break;
							case NAME:
								Object nameObject = method.invoke(registeredAnnotation);
								if(isValidObjectReference(nameObject))
									serviceContractModel.setName(nameObject.toString());
								break;
							case PROPERTYSOURCE:
								Object propertyObject = method.invoke(registeredAnnotation);
								if(isValidObjectReference(propertyObject))
									serviceContractModel.setPropertyMap(loadClassPathProperties(propertyObject.toString()));
								break;
							default:
								break;	
							}
						}
					}
					catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new RuntimeException(e);
					}
				});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return serviceContractModel;
	}
	
	private static boolean isValidObjectReference(Object object) {
		boolean value = false;
		if(!Objects.isNull(object) && !Strings.isNullOrEmpty(object.toString())) {
			value = true;
		}
		return value;
	}
	
	private static Map<String, String> loadClassPathProperties(String propertiesFile) {
		Map<String, String> propertyMap = new HashMap<>();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Properties properties = new Properties();
            properties.load(classLoader.getResourceAsStream(propertiesFile));
            propertyMap = properties.entrySet().stream().collect(
        	    Collectors.toMap(
        	         e -> e.getKey().toString(),
        	         e -> e.getValue().toString()
        	    )
        	);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return propertyMap;
	}
}
