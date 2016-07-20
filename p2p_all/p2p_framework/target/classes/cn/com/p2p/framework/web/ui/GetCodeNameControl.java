package cn.com.p2p.framework.web.ui;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.framework.code.CodeList;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class GetCodeNameControl implements TemplateMethodModel{
	@Autowired
	private CodeList bizCodeList;

	@Override
	public Object exec(List argList) throws TemplateModelException {
		String value="";
		String bizType = argList.get(0).toString();
		String codeKey = argList.get(1).toString();
		Map<String, String> codeMap = bizCodeList.getMap(bizType);
		if (codeMap != null) {
			value = codeMap.get(codeKey);
		}
		return value;
	}

}
