package cn.com.p2p.framework.web.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.com.p2p.framework.exception.FreemarkerTemplateExceptionHandler;

import freemarker.template.TemplateException;

public class MyFreeMarkerConfigurer extends FreeMarkerConfigurer {

	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		if (getConfiguration() == null) {
			setConfiguration(createConfiguration());
		}
		getConfiguration().setTemplateExceptionHandler(
				new FreemarkerTemplateExceptionHandler());
	}
}
