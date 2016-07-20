package cn.com.p2p.contentmanagent.service;

import java.util.List;

import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCustomCriteria;
import cn.com.p2p.domain.cms.entity.Article;

/**
* 栏目文章管理接口类
 * 
 * @author 
 *
 */
public interface ArticleService {
	
	
	/**
	 * 插入一条新纪录，正确插入时返回值为 1
	 * @author 
	 * 
	 * @param Article 插入实体
	 * @return 插入成功标志
	 */
	public int insertArticle(Article article);
	
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
	 * @author 
	 * 
	 * @param Article 更新实体
	 * @return 更新成功标志
	 */
	public int dynamicUpdateCategory(Article article);
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行批量更新
	 * @author 
	 * 
	 * @param Article 更新实体
	 * @return  0：更新失败、-1：排序字段为空、>0：更新成功
	 */
	public int batchDynamicUpdateArticle(List<Article> articleList);
	
	/**
	 * 根据当前Id进行Delete删除
	 * @author 
	 * 
	 * @param String id 当前Id
	 * @return 删除成功条数
	 */
	public int deleteById(String id);
	
	/**
	 * 根据当前Id进行批量Delete删除
	 * @author 
	 * 
	 * @param String[] id 当前删除Id
	 * @return 删除成功条数
	 */
	public int batchDeleteById(String[] id);
	
	
	/**
	 * 按条件动态翻页检索，返回实体集合
	 * @author 
	 *  
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	public List<Article> getPageByCriteria(ArticleCustomCriteria criteria);
	/**
	 * 按条件动态翻页检索，返回实体集合
	 * @author 
	 *  
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	public List<Article> getPageByCriteria(ArticleCriteria criteria);
	
	/**
	 * 按条件动态检索，返回实体集合
	 * @author 
	 *  
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	public List<Article> getArticleByCriteria(ArticleCriteria criteria);
	
	
	/**
	 * 栏目类型为单页查询文章,按照序号和更新日期排序
	 * @author 
	 * 
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	public List<Article> getSinglePageByCriteria(ArticleCriteria criteria);
	
	/**
	 * 根据当前Id获取文章内容
	 * @author 
	 * 
	 * @param 当前 id
	 * @return Article 文章内容实体
	 */
	public Article getArticleById(String id);

}
