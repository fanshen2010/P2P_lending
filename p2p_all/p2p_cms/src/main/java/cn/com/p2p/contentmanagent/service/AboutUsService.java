package cn.com.p2p.contentmanagent.service;

import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.dto.AboutUsDto;

/**
 * <p>关于我们Service</p>
 * @author 
 */
public interface AboutUsService {

    /**
     * <p>查询所有的栏目和栏目对应的内容</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  栏目ID
     * @param criteria 分页查询所需查询实体
     * @return AboutUsDto 关于我们DTO，相应栏目及栏目对应的文章集合
     * @description 根据当前点击的栏目ID获取栏目对应的文章集合，
     *              初始化界面时，获取默认排序的第一个栏目
     */
    AboutUsDto findCategoryArticle(AboutUsDto aboutUsDto, ArticleCriteria criteria);

    /**
     * <p>查询所有的栏目和栏目对应的内容</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  栏目ID
     * @param criteria 分页查询所需查询实体
     * @return AboutUsDto 关于我们DTO，相应栏目及栏目对应的文章集合
     * @description 根据当前点击的栏目ID获取栏目对应的文章集合，
     *              初始化界面时，获取默认排序的第一个栏目
     */
    AboutUsDto findCategoryArticle(AboutUsDto aboutUsDto, ArticleCriteria criteria,CategoryCriteria categoryCriteria);

    /**
     * <p>根据主键查询文章</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  文章ID
     * @return AboutUsDto 关于我们DTO，相应文章及菜单对应的栏目集合
     * @description 无
     */
    AboutUsDto findArticle(AboutUsDto aboutUsDto);

    /**
     * <p>根据主键查询文章</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  文章ID
     * @return AboutUsDto 关于我们DTO，相应文章及菜单对应的栏目集合
     * @description 无
     */
    AboutUsDto findArticle(AboutUsDto aboutUsDto,CategoryCriteria categoryCriteria);

}
