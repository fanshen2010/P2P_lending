package cn.com.p2p.domain.cms.dto;

/**
 * 栏目Dto类
 * 
 * @author 
 *
 */
public class CategoryDto {

	/**ID*/
    private String id;
    
	/**栏目编码*/
    private String categoryCode;

    /**上级栏目*/
    private String parentCategory;
    
    /** 上级栏目名称 */
    private String parentCategoryName;

    /**标题*/
    private String title;

    /**内容*/
    private String content;

    /**seo描述*/
    private String seoDescription;

    /**seo关键字*/
    private String seoKeywords;

    /**栏目排序*/
    private Integer orderNum;

    /**页面类别*/
    private String categoryType;

    /**打开链接方式*/
    private String target;

    /**每页显示条数*/
    private Integer perPage;

    /**状态*/
    private String status;

    /** 获取ID */
    public String getId() {
		return id;
	}
    /** 设置ID */
	public void setId(String id) {
		this.id = id;
	}
	/** 获取栏目编码 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/** 设置栏目编码 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/** 获取上级栏目 */
	public String getParentCategory() {
		return parentCategory;
	}

	/** 设置上级栏目 */
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	/** 获取上级栏目名称 */
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	/** 设置上级栏目名称 */
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	/** 获取标题 */
	public String getTitle() {
		return title;
	}

	/** 设置标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 获取内容 */
	public String getContent() {
		return content;
	}

	/** 设置内容 */
	public void setContent(String content) {
		this.content = content;
	}

	/** 获取seo描述 */
	public String getSeoDescription() {
		return seoDescription;
	}

	/** 设置seo描述 */
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	/** 获取seo关键字 */
	public String getSeoKeywords() {
		return seoKeywords;
	}

	/** 设置seo关键字 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	/** 获取栏目排序 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/** 设置栏目排序 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/** 获取页面类别 */
	public String getCategoryType() {
		return categoryType;
	}

	/** 设置页面类别 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	/** 获取打开链接方式 */
	public String getTarget() {
		return target;
	}

	/** 设置打开链接方式 */
	public void setTarget(String target) {
		this.target = target;
	}

	/** 获取每页显示条数 */
	public Integer getPerPage() {
		return perPage;
	}

	/** 设置每页显示条数 */
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	/** 获取状态 */
	public String getStatus() {
		return status;
	}

	/** 设置状态 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
