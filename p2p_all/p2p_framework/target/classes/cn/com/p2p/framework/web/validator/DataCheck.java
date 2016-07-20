package cn.com.p2p.framework.web.validator;

import java.util.List;
import java.util.Map;

import cn.com.p2p.framework.web.action.BaseAction;

public interface DataCheck {
	public List<String> check(BaseAction action, Map<String, Object> parameters) ;
}
