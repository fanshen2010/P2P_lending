package cn.com.p2p.framework.report;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 检验报告数据注解，标识相关的检验报告
 * @author kezhida
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ReportDataAnnotation {
	/**报告ID*/
	String[] reportCode();
}
