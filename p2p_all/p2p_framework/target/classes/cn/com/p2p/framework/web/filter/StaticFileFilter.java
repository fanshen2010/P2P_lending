package cn.com.p2p.framework.web.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.util.StringUtils;

public class StaticFileFilter implements Filter {

	private Properties settings = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (settings == null) {
			settings = SpringContextUtils.getBean("settings");

		}
		String url = ((HttpServletRequest) request).getServletPath();

		// /style
		String resultPath = (String) ((HttpServletRequest) request)
				.getSession().getAttribute("RESULTPATH");

		if (StringUtils.isEmpty(resultPath)) {
			resultPath = settings.getProperty("commonResultStyle", "/style");
		}

		request.getRequestDispatcher(resultPath + url.substring(6)).forward(
				request, response);
		// chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
