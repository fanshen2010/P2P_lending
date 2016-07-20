package cn.com.p2p.framework.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Spring IoC 启动后自动扫描StaticAutowiredContext指定路径中类的静态变量，并将其注入。
 * @author zhirong
 * @see cn.com.p2p.framework.context.StaticAutowiredContext
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StaticAutowired {

}
