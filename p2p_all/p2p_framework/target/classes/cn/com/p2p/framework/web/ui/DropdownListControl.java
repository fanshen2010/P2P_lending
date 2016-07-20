package cn.com.p2p.framework.web.ui;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * <#list cart?keys as itemKey>  
 * <#assign item = cart[itemKey]>
 * @author 
 * 
 */
public class DropdownListControl extends UiListControl {
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// DropDownList所需要调用的模板路径
		super.tplPath = "/common/control/select.ftl";
		super.execute(env, params, loopVars, body);
	}
}
