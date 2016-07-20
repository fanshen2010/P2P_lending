package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 版本验证错误注解，回调函数为doOptimisticLockingFailure
 * @author Administrator
 *
 */
@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface OptimisticLockingFailure {

	/**在回调函数中传入的参数*/
	String param() default "";
	/** 错误返回值，用于Action跳转 */
	String result() default "";
	/**版本验证错误消息*/
	String message() default "数据已经被更新，请重新操作。";
}
