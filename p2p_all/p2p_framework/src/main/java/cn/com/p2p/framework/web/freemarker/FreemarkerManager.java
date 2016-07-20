package cn.com.p2p.framework.web.freemarker;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

@Component("springConfigToFreemarkerManager")
@Scope("prototype")
public class FreemarkerManager extends
		org.apache.struts2.views.freemarker.FreemarkerManager {

	protected Configuration createConfiguration(ServletContext servletContext)
			throws TemplateModelException {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) context
				.getBean("freemarkerConfigurer");
		return configurer.getConfiguration();
	}
}