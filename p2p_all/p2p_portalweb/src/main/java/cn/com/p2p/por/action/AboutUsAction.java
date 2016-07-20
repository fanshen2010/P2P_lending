package cn.com.p2p.por.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.contentmanagent.service.AboutUsService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.domain.cms.dto.AboutUsDto;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.utils.Constants;

/**
 * <p>关于我们</p>
 * @author 
 * @date 2015-10-21 17:03
 */
@Namespace("/aboutus")
@Results({ 
          @Result(name = "singlepage", location = "singlepage.ftl", type = "freemarker"),
          @Result(name = "listpage", location = "listpage.ftl", type = "freemarker"),
          @Result(name = BaseAction.VIEW, location = "content.ftl", type = "freemarker"),
        })

public class AboutUsAction extends BaseAction{

    private static final long serialVersionUID = -91279158110519603L;

    /** 关于我们Service */
    @Autowired
    private AboutUsService aboutUsService;

    /** 关于我们DTO */
    private AboutUsDto aboutUsDto;
    
    /** 文章查询条件实体，此时实例化实体，防止舒适化界面时报错  */
    private ArticleCriteria criteria = new ArticleCriteria();
    
    /**
     * <p>关于我们首页</p>
     * @author 
     * @date 2015-10-21 17:36
     * @update 2015-10-25 14:00 已禁用的文章过滤掉
     * @description 根据当前点击的栏目ID获取栏目对应的文章集合，
     *              初始化界面时，获取默认排序的第一个栏目，
     *              根据栏目的Page类型返回不同的界面
     */
    @Action(value="index")
    public String init() {
    	criteria.setSortFields(OrderField.orderNum, SortType.ASC);
    	criteria.setSortFields(OrderField.postAt, SortType.DESC);
    	criteria.setSortFields(OrderField.updateTime, SortType.DESC);
    	criteria.setStatus(Constants.VALID_FLAG_VALID, Operator.equal);
        aboutUsDto = aboutUsService.findCategoryArticle(aboutUsDto, criteria);
        
        String categoryType = Constants.CATEGORY_PAGETYPE_SINGLE;
        if(aboutUsDto.getCategory() != null) {
        	categoryType = aboutUsDto.getCategory().getCategoryType();
        }
        if(Constants.CATEGORY_PAGETYPE_SINGLE.equals(categoryType)){
            return "singlepage";
        }else{
            return "listpage";
        }
    }
    
    
    /**
     * <p>文章内容页</p>
     * @author 
     * @date 2015-10-07 13:16
     * @description 无
     */
    @Action(value="view")
    public String view() {
        aboutUsDto = aboutUsService.findArticle(aboutUsDto);
        return VIEW;
    }
    
    /* ======================================================= getter setter method ============================================================ */
    
    public AboutUsDto getAboutUsDto() {
        return aboutUsDto;
    }
    public void setAboutUsDto(AboutUsDto aboutUsDto) {
        this.aboutUsDto = aboutUsDto;
    }

    public ArticleCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(ArticleCriteria criteria) {
        this.criteria = criteria;
    }

}
