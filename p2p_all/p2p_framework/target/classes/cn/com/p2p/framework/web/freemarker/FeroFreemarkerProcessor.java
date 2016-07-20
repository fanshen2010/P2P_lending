package cn.com.p2p.framework.web.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.freemarker.ScopesHashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.com.p2p.framework.web.action.BaseAction;

import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

@Service
public class FeroFreemarkerProcessor {

	@Autowired
	protected FreemarkerManager freemarkerManager;

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
	public String process(String ftl, Map<String, Object> map,
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

		TemplateModel model = createModel(baseAction,
				freemarkerCfg.getObjectWrapper(), map);

		((ScopesHashModel) model).putAll(map);

		template.process(model, result);
		return result.toString();
	}

	protected TemplateModel createModel(Object action, ObjectWrapper wrapper,
			Map<String, Object> map) throws TemplateModelException {
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ValueStack stack = ServletActionContext.getContext().getValueStack();

		for (String key : map.keySet()) {
			stack.set(key, map.get(key));
		}

		return freemarkerManager.buildTemplateModel(stack, action,
				servletContext, request, response, wrapper);
	}
}
