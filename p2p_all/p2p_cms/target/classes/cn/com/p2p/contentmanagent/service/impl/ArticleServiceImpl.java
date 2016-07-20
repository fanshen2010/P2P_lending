package cn.com.p2p.contentmanagent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.contentmanagent.service.ArticleService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCustomCriteria;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.query.ArticleQuery;
import cn.com.p2p.domain.cms.repository.ArticleRepository;
import cn.com.p2p.framework.util.StringUtils;

/**
 * 栏目文章管理接口实现类
 * 
 * @author 
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	/** 文章管理数据访问层接口 */
	@Autowired
	private ArticleRepository articleRepository; 
	
	/** 自定义文章管理数据访问层接口 */
	@Autowired
	private ArticleQuery articleQuery;
	
	/**
	 * 插入一条新纪录，正确插入时返回值为 1
	 * @author 
	 * 
	 * @param Article 插入实体
	 * @return 插入成功标志
	 */
	@Override
	public int insertArticle(Article article){
		
		//TODO 没写共通方法 暂时只添加Id，没考虑更新者，更新日期。。。。
		article.setId(StringUtils.getUUID());
		
		return articleRepository.insert(article);
	}
	
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
	 * @author 
	 * 
	 * @param Article 更新实体
	 * @return 更新成功标志
	 */
	@Override
	public int dynamicUpdateCategory(Article article){
		
		return articleRepository.dynamicUpdate(article);
	}
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行批量更新
	 * @author 
	 * 
	 * @param Article 更新实体
	 * @return  0：更新失败、-1：排序字段为空、>0：更新成功
	 */
	@Override
	public int batchDynamicUpdateArticle(List<Article> articleList){
		
		int ret = 0;
		
		for (Article article2 : articleList) {
			if(article2 != null){
				
				if(article2.getOrderNum() == null){
					ret = -1;
					break;
				}
				
				ret = ret + articleRepository.dynamicUpdate(article2);
			}
		}
		
		return ret;
	}
	
	/**
	 * 根据当前Id进行Delete删除
	 * @author 
	 * 
	 * @param String id 当前Id
	 * @return 删除成功条数
	 */
	@Override
	public int deleteById(String id){
		
		return articleRepository.delete(id);
	}
	
	/**
	 * 根据当前Id进行批量Delete删除
	 * @author 
	 * 
	 * @param String[] id 当前删除Id
	 * @return 删除成功条数
	 */
	@Override
	public int batchDeleteById(String[] id) {
		
		int retDelNum = 0;	//删除条数
		if(id != null && id.length > 0){
			
			for (int i = 0; i < id.length; i++) {
				
				if(articleRepository.delete(id[i])>0){
					retDelNum++;
				}
			}
		}
		
		return retDelNum;
	}
	
	/**
	 * 按条件动态翻页检索，返回实体集合
	 * @author 
	 *  
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	@Override
	public List<Article> getPageByCriteria(ArticleCustomCriteria criteria){
		
		return articleQuery.findPageByCustomCriteria(criteria);
	}
	
	/**
	 * 按条件动态检索，返回实体集合
	 * @author 
	 *  
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	@Override
	public List<Article> getArticleByCriteria(ArticleCriteria criteria){
		
		return articleRepository.findByCriteria(criteria);
		
	}
	
	/**
	 * 栏目类型为单页查询文章,按照序号和更新日期排序
	 * @author 
	 * 
	 * @param criteria 条件参数
	 * @return List<Article> 文章集合
	 */
	@Override
	public List<Article> getSinglePageByCriteria(ArticleCriteria criteria){
		
		return articleQuery.findSinglePageByCriteria(criteria);
	}
	
	/**
	 * 根据当前Id获取文章内容
	 * @author 
	 * 
	 * @param 当前 id
	 * @return Article 文章内容实体
	 */
	@Override
	public Article getArticleById(String id){
		
		return articleRepository.findOne(id);
	}


    @Override
    public List<Article> getPageByCriteria(ArticleCriteria criteria) {
        return articleRepository.findPageByCriteria(criteria);
    }
}
