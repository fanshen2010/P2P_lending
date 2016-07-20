package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface UniqueError {
	/** 在回调函数中传入的参数 */
	String param() default "";

	/** 错误返回值，用于Action跳转 */
	String result() default "";

	/** 版本验证错误消息 */
	String message() default "数据不唯一";
}
