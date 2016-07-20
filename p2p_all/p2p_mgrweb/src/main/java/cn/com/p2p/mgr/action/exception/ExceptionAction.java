package cn.com.p2p.mgr.action.exception;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;

/**
 * 全局异常处理表示处理Action
 * @author lulinghua
 *
 */
@SuppressWarnings("serial")
@Results({ @Result(name = BaseAction.INIT, location = "common/exception/exception.ftl", type = "freemarker")})
public class ExceptionAction extends BaseAction {




	@Override
	@Action("/errorprocess")
	public String init() throws Exception {
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-cache");
		Struts2Utils.getResponse().setHeader("Cache-Control", "no-store");
		Struts2Utils.getResponse().setDateHeader("Expires", 0);
		Struts2Utils.getResponse().setHeader("Pragma", "no-cache");

		return INIT;
	}
	

	
}
