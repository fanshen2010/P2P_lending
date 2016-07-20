package cn.com.p2p.security.control.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import cn.com.p2p.security.login.service.BackLoginService;

public class MyLoginAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private BackLoginService staffLoginSuccessHandle;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {

		try {
			staffLoginSuccessHandle.doLoginSuccess(getIpAddress(request));
		} catch (Exception ex) {
			logger.error("userLoginSuccessHandle ", ex);
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
