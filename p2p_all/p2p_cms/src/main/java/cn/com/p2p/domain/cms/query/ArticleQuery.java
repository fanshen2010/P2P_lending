package cn.com.p2p.domain.cms.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCustomCriteria;
import cn.com.p2p.domain.cms.entity.Article;


/**
 * ArticleQuery定义
 * 
 *  数据访问层<文章管理>的自定义查询组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 *
 */
public interface ArticleQuery {

	
	/**
	 * 栏目为单页时，获取文章集合
	 * 
	 * @param ArticleCriteria 条件参数
	 * @author 
	 * 
	 * @return List<Article> 文章集合
	 */
	public List<Article> findSinglePageByCriteria(@Param("criteria")ArticleCriteria criteria);
	
	/**
	 * 根据文章所属栏目父级栏目及其他自定义条件查询
	 * @author 
	 * 
	 * @param ArticleCustomCriteria 自定义条件类
	 * @return List<Article> 文章集合
	 */
	public List<Article> findPageByCustomCriteria(@Param("criteria")ArticleCustomCriteria criteria);
	
	
	/**
	 * 根据所属栏目Id删除文章
	 * @author 
	 * 
	 * @param categoryId 所属栏目Id
	 * @return 成功条数
	 */
	public int deleteByCategory(@Param("categoryId")String categoryId);
}
