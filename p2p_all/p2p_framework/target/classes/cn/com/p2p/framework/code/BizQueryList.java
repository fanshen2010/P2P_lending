package cn.com.p2p.framework.code;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

public class BizQueryList implements CodeList {
	private final Map<String, String> codeListMap;
	
	public BizQueryList(Object queryClass) {
        Map<String, String> codeList = new LinkedHashMap<String, String>();
        Method method = ReflectionUtils.findMethod(queryClass.getClass(), "dataQuery");
        Map<String, String> result;
		result = (Map<String, String>) ReflectionUtils.invokeMethod(method, queryClass);
		this.codeListMap = Collections.unmodifiableMap(result);
    }

	@Override
	public Map<String, String> getMap(String bizType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> toMap() {
		return this.codeListMap;
	}

}
