package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/***
 * 功能权限控制注解
 * @author kezhida
 *
 */
@Target({METHOD, FIELD,TYPE})
@Retention(RUNTIME)
public @interface Permission {

	/**功能ID数组*/
	String[] fnids();
}
