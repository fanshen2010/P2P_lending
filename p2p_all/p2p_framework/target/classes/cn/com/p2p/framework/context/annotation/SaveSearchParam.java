package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface SaveSearchParam {
	/**
	 * 拦截保存标识
	 * 
	 * @return
	 */
	String saveFlag() default "commonReturnFlag";

	/**
	 * 需要保存的参数id
	 * 
	 * @return
	 */
	String[] paramIds() default {};

	/**
	 * 重定向的 url对应的result
	 * 
	 * @return
	 */
	String redirectResult();

	/**
	 * 是否有翻页控件
	 * 
	 * @return
	 */
	boolean hasPage() default false;
}
