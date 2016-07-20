package cn.com.p2p.framework.context.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;


@Target({METHOD, FIELD,TYPE})
@Retention(RUNTIME)
public @interface Check {
	String[] errMsgParam() default {};
	/**校验类型*/
	ValidatorTypeEnum type() default ValidatorTypeEnum.TextRequired;
	/**校验参数*/
	String[] params() default {};
}
