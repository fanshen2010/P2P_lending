package cn.com.p2p.mgr.action.verify;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.security.control.back.MyVerifyCodeBack;

@Namespace("/")
public class VerifyCodeAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value = "verifyCode")
	public void verifyCode(){
		String verifyCode = MyVerifyCodeBack.getVerifyCode(4);
		Struts2Utils.getSession().setAttribute("verifyCode", verifyCode);
		MyVerifyCodeBack.flushVerifyCode(Struts2Utils.getResponse(), verifyCode);
	}
}
