package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.contentmanagent.service.ArticleService;
import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.RoleStateEnum;
import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.framework.util.StringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 根据栏目编号获取文章列表UI组件
 * 
 * @author 
 *
 */
public class CategoryListControl implements TemplateDirectiveModel {
    /** 文章管理Service */
    @Autowired
    private ArticleService articleService;

    /** 栏目Service */
    @Autowired
    private CategoryService categoryService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {

        String categoryCode = DirectiveUtils.getString("categoryCode", params);
        if (StringUtils.isNotEmpty(categoryCode)) {
            Integer pageSize = 4;
            try {
                pageSize =DirectiveUtils.getInt("pageSize", params);
                if(pageSize==null){
                    pageSize=4;
                }
            } catch (Exception e) {
                pageSize = 4;
            }
            String updateTimeOrder = DirectiveUtils.getString("updateTimeOrder", params);
            // 媒体报道
            CategoryCriteria categoryCriteria = new CategoryCriteria();
            categoryCriteria.setCategoryCode(categoryCode, Operator.equal);
            // 根据栏目编码获取当前栏目
            List<Category> categoryList = categoryService.getCategoryByCriteria(categoryCriteria);
            Category category = null;
            if (categoryList != null && !categoryList.isEmpty()) {
                // 当前栏目存在则根据栏目ID查询文章列表
                ArticleCriteria criteria = new ArticleCriteria();
                category = categoryList.get(0);
                // 由于栏目编码不可重复，所以默认取第一条数据
                criteria.setCategory(category.getId(), Operator.equal);
                criteria.setStatus(RoleStateEnum.ROLE_STATE_1.getCode(), Operator.equal);
                criteria.setSortFields(OrderField.orderNum, SortType.ASC);
                criteria.setSortFields(OrderField.postAt, SortType.DESC);
                if ("1".equals(updateTimeOrder)) {
                    criteria.setSortFields(OrderField.updateTime, SortType.DESC);
                }
                criteria.getPage().setDefalutPageRows(pageSize);
                List<Article> articles = articleService.getPageByCriteria(criteria);
                env.setVariable("category", DEFAULT_WRAPPER.wrap(category));
                env.setVariable("articles", DEFAULT_WRAPPER.wrap(articles));
                body.render(env.getOut());
            }
        }
    }

}
