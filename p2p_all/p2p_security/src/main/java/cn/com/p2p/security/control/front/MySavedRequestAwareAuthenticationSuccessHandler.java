package cn.com.p2p.security.control.front;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import cn.com.p2p.security.login.service.FrontLoginService;

public class MySavedRequestAwareAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private List<String> ignoreUrl = null;

	@Autowired
	private Properties settings;

	@Autowired
	private FrontLoginService frontLoginService;

	public void setIgnoreUrl(List<String> ignoreUrl) {
		this.ignoreUrl = ignoreUrl;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException { 

		try {
			frontLoginService.doLoginSuccess(getIpAddress(request));
		} catch (Exception ex) {
			logger.error("userLoginSuccessHandle ", ex);
		}
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();

		if ((isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils
						.hasText(request.getParameter(targetUrlParameter))))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);

			return;
		}

		clearAuthenticationAttributes(request);

		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	public void setRequestCache(RequestCache requestCache) {

		this.requestCache = requestCache;
	}
	
	private String getIpAddress(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		} 
		return ip;
	}
}
