package cn.com.p2p.por.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

import cn.com.p2p.framework.resource.MessageResourceBundle;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.exception.CaptchaException;
import cn.com.p2p.framework.web.validator.ActionErrors;
import cn.com.p2p.security.login.service.FrontLoginService;

@SuppressWarnings("serial")
@Namespace("/")
@Results({ @Result(name = BaseAction.INIT, location = "login.ftl", type = "freemarker"),
	     @Result(name = com.opensymphony.xwork2.Action.INPUT, location = "login/login.ftl", type = "freemarker")})
public class LoginAction extends BaseAction {

	@Autowired
	private FrontLoginService staffLoginSuccessHandle;

	private String j_username;
	private String errMsg = "";


	@Override
	@Action("/login")
	public String init() throws Exception {
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-cache");
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-store");
		Struts2Utils.getResponse().setDateHeader("Expires", 0);
		Struts2Utils.getResponse().setHeader("Pragma", "no-cache");

		Exception last = (Exception) ServletActionContext.getRequest()
				.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		// 获取登录名
		j_username = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("j_username");

		if (j_username == null) {
			j_username = "";
		}
		
		int loginFail = 0;
		if (last != null) {
			if (last instanceof CaptchaException) {
                //验证码错误
				errMsg = last.getMessage();
			} else if (last instanceof BadCredentialsException) {
				if (j_username.length() > 0) {
					loginFail = staffLoginSuccessHandle.doLoginFailure(j_username, null);
				}
				if (loginFail > 5 && loginFail < 10) {
					errMsg = MessageResourceBundle.getMessage("BLE001", String.valueOf(10 - loginFail));
				} else if (loginFail >= 10) {
					errMsg = MessageResourceBundle.getMessage("BLE006");
				} else {
					errMsg = MessageResourceBundle.getMessage("BLE003");
				}

			} else if (last instanceof DisabledException) {
				errMsg = MessageResourceBundle.getMessage("BLE002");
			} else if (last instanceof AccountExpiredException){
				errMsg = MessageResourceBundle.getMessage("BLE005");
			} else if (last instanceof LockedException) {
				errMsg = MessageResourceBundle.getMessage("BLE004");
			}
		}

		
		//error 信息追加
		if (!"".equals(errMsg)) {
			ActionErrors errors = new ActionErrors();
			errors.addError(errMsg);
		}

		// 清除错误信息
		ServletActionContext.getRequest().getSession()
				.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", null);

		return INIT;
	}
	

	
}
