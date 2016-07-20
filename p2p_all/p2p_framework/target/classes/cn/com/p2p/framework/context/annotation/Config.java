package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD, FIELD,TYPE})
@Retention(RUNTIME)
public @interface Config {
	/**验证属性名*/
	String field() default "";
	/**校验类型*/
	Check[] check();
	/**返回错误信息*/
	String msg() default "数据不合法!";
}
