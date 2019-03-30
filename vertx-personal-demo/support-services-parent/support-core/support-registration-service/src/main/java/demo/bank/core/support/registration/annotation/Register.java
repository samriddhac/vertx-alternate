package demo.bank.core.support.registration.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Register {
	public String id() default "";
	public String name();
	public String propertySource() default "application.properties";
}
