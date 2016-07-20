package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.security.authority.service.MenuAuthManageService;
import cn.com.p2p.security.authority.service.dto.LeftNaviBean;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class LeftMenuControl implements TemplateDirectiveModel {

	@Autowired
	private MenuAuthManageService menuAuthManageService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		try {
			//String userId = DirectiveUtils.getString("userId", params);
			List<Object> roleIdList = DirectiveUtils.getList("roleIdList", params);
			
			//请求URI算出
			String requestURI = DirectiveUtils.getString("requestURI", params);
			String contextPath = DirectiveUtils.getString("contextPath", params);

			if (contextPath != "/" && requestURI.startsWith(contextPath)) {
				requestURI = requestURI.replaceFirst(contextPath, "");
			}
			
			List<LeftNaviBean> fmMenus = null;

			// 调用selectManager接口，查询字典表数据
			fmMenus = menuAuthManageService.getMenuByRoleId(roleIdList, requestURI);


			env.setVariable("fmMenus", DEFAULT_WRAPPER.wrap(fmMenus));

			body.render(env.getOut());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
