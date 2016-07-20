package cn.com.p2p.security.control.back;

import javax.servlet.http.HttpServletRequest;

import cn.com.p2p.framework.resource.MessageResourceBundle;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.VerifyCodeGenerator;
import cn.com.p2p.framework.web.exception.CaptchaException;

/**
 * @author 
 * 后台验证码相关
 */
public class MyVerifyCodeBack extends VerifyCodeGenerator {
	public static void checkVerifyCode(HttpServletRequest request) throws CaptchaException{
		String requestverifyCode = request.getParameter("verifyCode");
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");

		if (StringUtils.isNotEmpty(requestverifyCode)) {

			if (!requestverifyCode.equals(verifyCode)) {
				throw new CaptchaException(MessageResourceBundle.getMessage("BLE008"));
			}

		} else {
			throw new CaptchaException(MessageResourceBundle.getMessage("BLE007"));
		}
	}
}
