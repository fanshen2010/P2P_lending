package cn.com.p2p.framework.aop.pagination.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * <p>
 * 分页注解，当需要对实体进行分页处理时，对其进行设置.
 * 只支持在类声明上进行设置
 * </p>
 *
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Paging {
    /**
     * 分页对象的属性名称，
     * 也就是在当参数传递对象时，需要进行设置{@link } 的属性名称
     *
     * @return 分页对象的属性名称，默认page
     */
    String field() default "page";

}
