package cn.com.p2p.domain.cms.criteria;
import java.util.Date;
import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * 文章自定义查询参数类
 * @author 
 */
public class ArticleCustomCriteria extends BaseCriteria {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**文章ID*/
    private String id;

    /**所属栏目*/
    private String category;
    
    /**所属栏目父级栏目 */
    private String parentCategory;

    /**标题*/
    private String title;

    /**排序*/
    private Integer orderNum;

    /**发布时间*/
    private Date postAt;

    /**是否推荐*/
    private String recommendFlag;

    /**状态*/
    private String status;

    /**访问量*/
    private Long visits;

    /**文章来源*/
    private String articleSource;
    
    
    

    /** 获取文章ID */
	public String getId() {
		return id;
	}
	/** 设置文章ID */
	public void setId(String id) {
		this.id = id;
	}
	/** 获取所属栏目 */
	public String getCategory() {
		return category;
	}
	/** 设置所属栏目 */
	public void setCategory(String category) {
		this.category = category;
	}
	/** 获取所属栏目父级栏目  */
	public String getParentCategory() {
		return parentCategory;
	}
	/** 设置所属栏目父级栏目  */
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	/** 获取标题 */
	public String getTitle() {
		return title;
	}
	/** 设置标题 */
	public void setTitle(String title) {
		this.title = title;
	}
	/** 获取排序 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/** 设置排序 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/** 获取发布时间 */
	public Date getPostAt() {
		return postAt;
	}
	/** 设置发布时间 */
	public void setPostAt(Date postAt) {
		this.postAt = postAt;
	}
	/** 获取是否推荐 */
	public String getRecommendFlag() {
		return recommendFlag;
	}
	/** 设置是否推荐 */
	public void setRecommendFlag(String recommendFlag) {
		this.recommendFlag = recommendFlag;
	}
	/** 获取状态 */
	public String getStatus() {
		return status;
	}
	/** 设置状态 */
	public void setStatus(String status) {
		this.status = status;
	}
	/** 获取访问量 */
	public Long getVisits() {
		return visits;
	}
	/** 设置访问量 */
	public void setVisits(Long visits) {
		this.visits = visits;
	}
	/** 获取文章来源 */
	public String getArticleSource() {
		return articleSource;
	}
	/** 设置文章来源 */
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
}
