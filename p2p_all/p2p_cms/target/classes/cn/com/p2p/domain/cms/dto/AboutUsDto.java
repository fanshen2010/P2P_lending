package cn.com.p2p.domain.cms.dto;

import java.io.Serializable;
import java.util.List;

import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.entity.Category;

/**
 * <p>关于我们DTO</p>
 * @author 
 * @date 2015-10-21 16:11
 */
public class AboutUsDto implements Serializable{

    private static final long serialVersionUID = -8671284856652253340L;

    /** 栏目实体 */
    private Category category;
    
    /** 文章实体 */
    private Article article;
    
    /** 栏目实体集合 */
    private List<Category> categorys;
    
    /** 栏目对应的栏目集合的列表，外层List仅仅用于循环 */
    private List<Article> articles;

    /** 文章查询条件实体 */
    private ArticleCriteria criteria;
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(ArticleCriteria criteria) {
        this.criteria = criteria;
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
