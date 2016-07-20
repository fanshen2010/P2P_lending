package cn.com.p2p.ui.service;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 页脚，关于我们二级栏目集合标签
 * 
 * @author 
 *
 */
public class AboutCategorys implements TemplateDirectiveModel {
	
    /** 栏目内容管理Service */
	@Autowired
	private CategoryService categoryService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			
		//页脚关于我们二级栏目集合
	    List<Category> aboutCategorys = new ArrayList<Category>();
	    
	    List<Category> curCategorys;
		//页脚关于我们二级栏目
	    CategoryCriteria criteria = new CategoryCriteria();
	    
	    //查询栏目编码为 platform_1st 的栏目
	    criteria.setCategoryCode("platform_1st", Operator.equal);
	    curCategorys = categoryService.getCategoryByCriteria(criteria);
	    
	    //当前栏目存在，则取当前栏目下所有子栏目
	    if(curCategorys != null && !curCategorys.isEmpty()){
	    	criteria = new CategoryCriteria();
	    	//栏目编码不可重复，curCategorys 正常只有一条数据，所以默认取第一条
	    	criteria.setParentCategory(curCategorys.get(0).getId(), Operator.equal);
	    	aboutCategorys = categoryService.getCategoryByCriteria(criteria);
	    }
		
	    env.setVariable("aboutCategorys", DEFAULT_WRAPPER.wrap(aboutCategorys));

		body.render(env.getOut());
	}

}
