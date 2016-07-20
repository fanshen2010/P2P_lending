package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * 格据身份证号码计算年龄
 * @author 
 *
 */
public class CountAgeByIdentity implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取common路径
		String commonResultPath = env.getVariable("commonResultPath").toString();
		if(params != null && params.size() > 0){
			String identity = DirectiveUtils.getString("identity", params);
			
			// 出生年月日
			if (identity != null && identity.length() > 0) {
				String birth = identity.substring(6, 10);
				// 当前日期
				String sys = DateUtils.shortDate(DateUtils.getCurrentDateTime())
						.substring(0, 4);

				env.setVariable("status", DEFAULT_WRAPPER.wrap(Long.parseLong(sys) - Long.parseLong(birth)));
				env.include("/" + commonResultPath + "/common/bizTag/statusTag.ftl", "UTF-8", true);
			}
		}
	}

}
