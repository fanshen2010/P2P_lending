package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD,TYPE})
@Retention(RUNTIME)
public @interface Validators {
	
	String str() default "";
	String result() default"";
	String param() default"";
}
