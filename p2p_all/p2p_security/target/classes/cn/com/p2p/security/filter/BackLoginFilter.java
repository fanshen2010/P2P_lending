package cn.com.p2p.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.com.p2p.security.control.back.MyVerifyCodeBack;

public class BackLoginFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		// String requestCaptcha =
		// request.getParameter(this.getCaptchaFieldName());

		logger.info("开始");

//		// 保存用户名称
//		request.getSession(false).setAttribute(getUsernameParameter(),
//				obtainUsername(request));

		MyVerifyCodeBack.checkVerifyCode(request);
		return super.attemptAuthentication(request, response);
	}
}
