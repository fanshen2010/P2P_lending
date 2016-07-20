package cn.com.p2p.framework.web.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.com.p2p.framework.web.action.BaseAction;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerProcessor {
//	/**
//	 * 初始化模板引擎
//	 * 
//	 * @param ftl
//	 *            模板名称
//	 * @param map
//	 *            模板中需要的参数集合
//	 * @param relativePath
//	 *            模板相对于根路径的相对路径
//	 * @throws IOException
//	 * @throws TemplateException
//	 */
//	public String init(String ftl, Map<String, Object> map, String relativePath)
//			throws IOException, TemplateException {
//		ApplicationContext context = WebApplicationContextUtils
//				.getWebApplicationContext(ServletActionContext
//						.getServletContext());
//		FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) context
//				.getBean("freemarkerConfigurer");
//		Configuration freemarkerCfg = configurer.getConfiguration();
//		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
//		Template template = freemarkerCfg.getTemplate(relativePath + "/" + ftl);
//		template.setEncoding("UTF-8");
//
//		StringWriter result = new StringWriter();
//		template.process(map, result);
//		return result.toString();
//	}

	/**
	 * 初始化模板引擎
	 * 
	 * @param ftl
	 *            模板名称
	 * @param map
	 *            模板中需要的参数集合
	 * @param baseAction
	 *            action的基类
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String init(String ftl, Map<String, Object> map,
			BaseAction baseAction) throws IOException, TemplateException {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());
		FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) context
				.getBean("freemarkerConfigurer");
		Configuration freemarkerCfg = configurer.getConfiguration();
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		String commonResultPath = baseAction.getCommonResultPath();

		String ftlPath = "/" + commonResultPath;
		if (ftl.startsWith("/")) {
			ftlPath = ftlPath + ftl;
		} else {
			ftlPath = ftlPath + "/" + ftl;
		}
		Template template = freemarkerCfg.getTemplate(ftlPath);
		template.setEncoding("UTF-8");

		StringWriter result = new StringWriter();
		map.put("commonResultPath", commonResultPath);

		template.process(map, result);
		return result.toString();
	}
}
