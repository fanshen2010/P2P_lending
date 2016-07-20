/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        ArticleRepository.java
 * Description:       ArticleRepository类定义
 *        实体 Article的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.cms.entity.Article
 *        cn.com.p2p.domain.cms.Criteria.ArticleCriteria
 *        cn.com.p2p.domain.cms.repository.ArticleRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-01             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * ArticleRepository定义.
 * <p>
 * 数据访问层<文章>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface ArticleRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return Article
     */
	public Article findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<Article>
     */
	public List<Article> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  ArticleSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<Article> findPageAll(ArticleCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  ArticleSearchCriteria 检索条件
     * @return List<Article>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Article> findByCriteria(ArticleCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  ArticleSearchCriteria 检索条件
     * @return List<Article>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Article> findPageByCriteria(ArticleCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  Article article
     * @return 插入成功标志
     */
	public int insert(Article article);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  Article article
     * @return 更新成功标志
     */
	public int dynamicUpdate(Article article);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  Article article
     * @return 更新成功标志
     */
	public int update(Article article);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param id
     * @return 假删除成功标志
     */
	public int suspend(@Param("id") String id);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param id
     * @return 删除成功标志
     */
	public int delete(@Param("id") String id);

}

