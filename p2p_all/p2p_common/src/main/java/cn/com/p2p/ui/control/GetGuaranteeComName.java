package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.repository.PfmTenantDepartmentRepository;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * 根据担保公司获取担保公司名称
 * @author 
 *
 */
public class GetGuaranteeComName implements TemplateDirectiveModel{

	@Autowired
	private PfmTenantDepartmentRepository pfmTenantDepartmentRepository;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取common路径
		String commonResultPath = env.getVariable("commonResultPath").toString();
		if(params != null && params.size() > 0){
			String guaranteeComCode = DirectiveUtils.getString("guaranteeComCode", params);
			PfmTenantDepartment PfmTenantDepartment=pfmTenantDepartmentRepository.findOne(guaranteeComCode);
				env.setVariable("status", DEFAULT_WRAPPER.wrap(PfmTenantDepartment.getDepartmentName()));
				env.include("/" + commonResultPath + "/common/bizTag/statusTag.ftl", "UTF-8", true);
		}
	}

}
