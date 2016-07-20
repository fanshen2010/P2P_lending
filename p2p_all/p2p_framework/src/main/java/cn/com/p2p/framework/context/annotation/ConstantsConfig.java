package cn.com.p2p.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 常理注入，在Spring IoC容器中的bean，
 * 均可以用@ConstantsConfig的方式，将常量资源文件中的值注入到bean的成员变量中去。
 * @author zhirong
 * @see cn.com.p2p.framework.context.config.ConstantsConfigBeanPostProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConstantsConfig {

	/**
	 * 设置常量名称前缀，默认为空串
	 * @return 常量资源文件中前缀，例：abc.ddd.userName=admin中abc.ddd为前缀；userName为变量名称。
	 */
	String namePrefix() default "";
	
	/**
	 * 设置默认值
	 * @return 默认值
	 */
	String value() default "";
	
	boolean inherited() default false;

}
