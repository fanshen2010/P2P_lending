package cn.com.p2p.framework.web.validator;

import java.util.ArrayList;
import java.util.List;

import cn.com.p2p.framework.web.interceptor.Constant;
import cn.com.p2p.framework.web.util.Struts2Utils;

public class ActionErrors {

	private List<String> errors = null;
	
	public ActionErrors() {
		this.errors = new ArrayList<String>();
		Struts2Utils.getRequest().setAttribute(Constant.COMM_ERROR_MESSAGE_KEY, errors);
	}
	
	public void addError(String message) {
		if (!"".equals(message)) {
			errors.add(message);
		}
	}
	public List<String> getErrors() {
		return errors;
	}
}
