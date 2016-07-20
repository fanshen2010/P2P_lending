package cn.com.p2p.framework.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.UnknownHandlerManager;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;

/**
 * 扩展struts2核心过滤器
 * @author zhirong，wuyingbo@4001007777.com
 * @version 1.0
 * <p>------------------------------------------------------------</p>
 * <p>·修改历史</p>
 * <p>·序号······修改日期·····修改人·····修改原因·</p>
 * <p>··1·······································</p>
 */
public class Struts2Filter extends StrutsPrepareAndExecuteFilter {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	Dispatcher dispatcher = null;
	
	private String errorPage;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		InitOperations init = new InitOperations();
		try {
			FilterHostConfig config = new FilterHostConfig(filterConfig);
			init.initLogging(config);
			dispatcher = init.initDispatcher(config);
			init.initStaticContentLoader(config, dispatcher);

			prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);
			execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);
			this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);

			postInit(dispatcher, filterConfig);
		} finally {
			init.cleanup();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		if (httpServletRequest.getRequestURI().indexOf("/dwr/") > -1) { // DWR请求情况跳过Struts2处理
			chain.doFilter(request, response);
		}
		
		// 获得action映射对象
		ActionMapping mapping = prepare.findActionMapping(httpServletRequest, httpServletResponse);
		
		if (mapping != null) { // 映射不为空场合，请求时struts2类型请求。
			// 获得配置管理器
			ConfigurationManager configurationManager = dispatcher.getConfigurationManager();
			// 获得配置
			Configuration configuration = configurationManager.getConfiguration();
			// 获得运行时配置
			RuntimeConfiguration runtimeConfiguration = configuration.getRuntimeConfiguration();
			// 获得action配置，通过名称空间和action名称。
			ActionConfig actionConfig = runtimeConfiguration.getActionConfig(mapping.getNamespace(), mapping.getName());
			if (actionConfig != null) { // action配置不为空场合，执行。
				super.doFilter(request, response, chain);
			} else { // action配置为空场合
				// 获得未知处理管理器
				UnknownHandlerManager unknownHandlerManager = configuration.getContainer().getInstance(UnknownHandlerManager.class);
				// 获得action配置
				actionConfig = unknownHandlerManager.handleUnknownAction(mapping.getNamespace(), mapping.getName());
				if (actionConfig != null) { // action配置不为空场合
					super.doFilter(request, response, chain);
				} else { // action配置为空场合，重定向未知action处理页面。
					String contextPath = httpServletRequest.getContextPath();
					httpServletResponse.sendRedirect(contextPath + errorPage);
				}
			}
		} else { // 映射为空场合
			super.doFilter(request, response, chain);
		}
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
