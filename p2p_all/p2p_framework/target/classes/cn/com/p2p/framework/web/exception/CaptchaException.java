package cn.com.p2p.framework.web.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	public CaptchaException(String msg) {
		super(msg);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
