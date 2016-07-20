package cn.com.p2p.ui.control;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.message.service.MessageSupportService;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class HeaderMessageControl implements TemplateMethodModel{

	@Autowired
	private MessageSupportService messageSupportService;

	@Override
	public Object exec(List argList) throws TemplateModelException {
		String value="";
		if (argList.get(0) != null) {
			String msgReceType = (String) argList.get(0);
			String userId = (String) argList.get(1);
			int count = messageSupportService.getUnreadMessageCount(msgReceType, userId);
			
		    value = String.valueOf(count);	
			
		}

		return value;
	}

}
