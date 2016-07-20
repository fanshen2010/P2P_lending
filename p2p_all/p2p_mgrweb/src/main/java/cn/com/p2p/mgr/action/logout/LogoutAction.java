package cn.com.p2p.mgr.action.logout;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;

@SuppressWarnings("serial")
@Namespace("/")
@Results({ @Result(name = BaseAction.INIT, location = "login/login.ftl", type = "freemarker")})
public class LogoutAction extends BaseAction {

	@Override
	@Action("/logout")
	public String init() throws Exception {
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-cache");
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-store");
		Struts2Utils.getResponse().setDateHeader("Expires", 0);
		Struts2Utils.getResponse().setHeader("Pragma", "no-cache");

		// 清除错误信息
		ServletActionContext.getRequest().getSession().invalidate();

		return INIT;
	}
	

	
}
