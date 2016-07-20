package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.util.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class GetLoanInterestMannerControl  implements TemplateDirectiveModel{

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String status = "";
		// 获取common路径
		String commonResultPath = env.getVariable("commonResultPath").toString();
		if(params != null && params.size() > 0){
			String loanInterestManner = DirectiveUtils.getString("loanInterestManner", params);
			
			LoanInterestMannerEnum lse = LoanInterestMannerEnum.getEnumByKey(loanInterestManner);
			if(lse != null){
				status = lse.getValue();
			}
		}
		env.setVariable("status", DEFAULT_WRAPPER.wrap(status));
		env.include("/" + commonResultPath + "/common/bizTag/statusTag.ftl", "UTF-8", true);
	}

}
