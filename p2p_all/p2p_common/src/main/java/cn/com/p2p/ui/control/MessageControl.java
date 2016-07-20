package cn.com.p2p.ui.control;

import java.util.List;

import cn.com.p2p.framework.resource.MessageResourceBundle;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class MessageControl implements TemplateMethodModel{

	@Override
	public Object exec(List argList) throws TemplateModelException {
		String value="";
		String msgId = (String) argList.get(0);
		String[] msgParams = null;
		if (argList.size() > 1) {
			msgParams = new String[argList.size() - 1];
		}
				
		for (int i = 1; i < argList.size(); i++) {
			msgParams[i-1] = (String) argList.get(i);
		}
		value = MessageResourceBundle.getMessage(msgId, msgParams);

		
		return value;
	}

}
