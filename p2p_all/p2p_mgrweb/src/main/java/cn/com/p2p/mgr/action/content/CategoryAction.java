package cn.com.p2p.mgr.action.content;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.contentmanagent.service.ArticleService;
import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.ArticleCustomCriteria;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.system.service.UploadFileService;
import cn.com.p2p.utils.UploadUtils;

/**
 * 
 * 栏目内容管理Action
 * 
 * @author 
 *
 */
@Namespace("/content/category")
@Results({ @Result(name = "categoryIndex", location = "categoryIndex.ftl", type = "freemarker"),
		   @Result(name = "categoryEdit", location = "categoryEdit.ftl", type = "freemarker"),
		   @Result(name = "articleIndex", location = "articleIndex.ftl", type = "freemarker"),
		   @Result(name = "retArticleIndex", location = "articleIndex.htm", type = "redirect",params={"categoryId","%{article.category}","firstTitle","%{firstTitle}","secondTitle","%{secondTitle}"}),
		   @Result(name = "retCategoryIndex", location = "categoryIndex.htm", type = "redirect"),
		   @Result(name = "articleEdit", location = "articleEdit.ftl", type = "freemarker")})
public class CategoryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 栏目内容管理Service */
	@Autowired
	private CategoryService categoryService;
	
	/** 文章管理Service */
	@Autowired
	private ArticleService articleService;
	
	/** 文件上传操作接口 */
	@Autowired
	private UploadFileService uploadFileService;
	
	/** 栏目内容集合 */
	private List<Category> categoryList;
	
	/** 文章内容集合 */
	private List<Article> articleList;

	/** 栏目内容 */
	private Category category;
	
	/** 文章实体 */
	private Article	article;

	/** 当前栏目Id */
	private String categoryId;
	
	/** 当前文章Id */
	private String articleId;
	
	/** 父级栏目名称 */
	private String parentName;
	
	/** 父级栏目Id */
	private String parentId;
	
	/** 一级栏目DropdownList */
	private Map<String,String> firstCategoryMap;
	
	/** 二级栏目DropdownList */
	private Map<String,String> secondCategoryMap;
	
	/** 文章页面加载类型 */
	private String articleLoadType;
	
	/** 栏目页面加载类型 */
	private String categoryLoadType;
	
	/** 文章标题检索条件 */
	private String articleTitle;
	
	/** 上传文件 */
	private UploadFile defultUf;
	
	/** 文章编辑页标题 */
	private String articleEditMenu;

	/** 文章检索条件类 */
	private ArticleCustomCriteria criteria = new ArticleCustomCriteria();
	
	/** 模板引擎 */
	@Autowired
	protected FeroFreemarkerProcessor feroFreemarkerProcessor;

	/** 页面加载类型-添加文章 */
	private static final String PAGE_LOAD_ADD = "1";
	/** 页面加载类型-更新文章 */
	private static final String PAGE_LOAD_UPDATE = "2";
	
	/** 当前提交方式 */
	private String method = org.apache.struts2.ServletActionContext.getRequest() .getMethod();
	
	/** checkbox列表 */
	private String[] checkboxList;

	/**
	 * 栏目内容管理列表页
	 */
	@Override
	@Action(value="categoryIndex")
	public String init() throws Exception {
		
		//获取栏目列表
		categoryList = categoryService.getCategoryList();
		
		return "categoryIndex";
	}
	
	/**
	 * 栏目管理编辑页面
	 */
	@Action(value="categoryEdit")
	public String categoryEdit() throws Exception{
		
		if("GET".equalsIgnoreCase(method) && StringUtils.isNotEmpty(parentName)){	//提交方式为get则转码,防止标题乱码
			
			parentName = new String(parentName.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		if(StringUtils.isEmpty(parentId)){
			//父级ID为空则说明当前栏目为根目录
			parentName = "root category";
		}
		
		if(PAGE_LOAD_ADD.equals(categoryLoadType)){
			
			category = new Category();
			category.setStatus("1");	//默认为启用状态
			category.setOrderNum(99);	//排序默认为99
			
		} else if(PAGE_LOAD_UPDATE.equals(categoryLoadType)){
			//当前页面加载为更新，获取默认值
			category = categoryService.getCategoryById(categoryId);
			
			if(StringUtils.isNotEmpty(parentId)){	//当前修改为二级栏目则获取一级栏目DropdownList
				firstCategoryMap = categoryService.getFirstCategoryMap();
			}
		}
		
		return "categoryEdit";
	}
	
	/**
	 * 栏目内容管理编辑页面提交方法 
	 */
	@Action(value="okSubmitCategoryEdit")
	public String okSubmitCategoryEdit(){
		
		//当前操作为添加
		if(PAGE_LOAD_ADD.equals(categoryLoadType)){
		
			categoryService.insertCategory(category);
			
		} else if(PAGE_LOAD_UPDATE.equals(categoryLoadType)) {
			//当前操作为修改
			categoryService.dynamicUpdateCategory(category);
		}
		
		return "retCategoryIndex";
	}
	
	/**
	 * 栏目编码重复校验 
	 */
	@Action(value="categoryCodeCheck")
	public void categoryCodeCheck(){
		
		String checkFlag = "true";
		
		//当前栏目编码存在则验证失败
		if(categoryService.categoryCodeCheck(category.getCategoryCode(),category.getId())){
			checkFlag = "false";
		}
		
		Struts2Utils.renderText(checkFlag);
	}
	
	/**
	 * 栏目删除提交方法
	 */
	@Action(value="okSubmitcategoryDelete")
	public void okSubmitcategoryDelete() throws Exception{
		
		categoryService.deleteById(categoryId);
		
		delSuccess();
	}
	
	/**
	 * 栏目保存排序 
	 * @return
	 */
	@Action(value="saveOrderNumCategory")
	public void saveOrderNumCategory() throws Exception{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", categoryService.batchDynamicUpdateCategory(categoryList));
		
		Struts2Utils.renderJson(retMap);
	}
	
	/******************************************* 文章页面方法 *********************************************************/
	/**
	 * 文章管理-列表页
	 * @return
	 */
	@Action(value="articleIndex")
	public String articleIndex() throws Exception{
		
		firstCategoryMap = categoryService.getFirstCategoryMap();
		if(criteria != null && StringUtils.isNotEmpty(criteria.getParentCategory())){
			secondCategoryMap = categoryService.getSecondCategoryMap(criteria.getParentCategory());
		}
		
		articleList = articleService.getPageByCriteria(criteria);
		
		return "articleIndex";
	}
	
	/**
	 * 加载二级栏目DropdownList
	 */
	@Action(value="loadSecondCategoryMap")
	public void loadSecondCategoryMap() throws Exception{
		
		secondCategoryMap = categoryService.getSecondCategoryMap(parentId);
		
		Struts2Utils.renderJson(secondCategoryMap);
	}
	
	/**
	 * 文章管理-编辑页
	 * @return
	 */
	@Action(value="articleEdit")
	public String articleEdit() throws Exception{
	
		//获取一级栏目列表Map
		firstCategoryMap = categoryService.getFirstCategoryMap();
		
		//当前页面为修改，获取默认值
		if(PAGE_LOAD_UPDATE.equals(articleLoadType)){
			articleEditMenu = "Modify Article";
			article = articleService.getArticleById(articleId);
			//根据所属栏目Id获取栏目信息
			Category curCategory = categoryService.getCategoryById(article.getCategory());
			//获取父级Id
			parentId = curCategory.getParentCategory();
			
			//根据父级栏目 获取栏目DropdownList
			secondCategoryMap = categoryService.getSecondCategoryMap(parentId);
			
			defultUf = uploadFileService.findUploadFileById(article.getTitlePic());
		} 
		//当前页面为添加，设置默认值
		else if(PAGE_LOAD_ADD.equals(articleLoadType)){
			articleEditMenu = "Add Article";
			article = new Article();
			article.setOrderNum(99);	//排序默认为99
			article.setStatus("1");		//文章状态默认启用
			article.setRecommendFlag("1");//是否推荐默认为是
			article.setPostAt(new Date());//创建时间默认为当前系统时间
		}
		
		return "articleEdit";
	}
	
	/**
	 * 文章编辑页提交方法
	 */
	@Validators(str="articleCheck",result = "businessStaff")
	@Action(value="okSubmitArticleEdit")
	public String okSubmitArticleEdit() throws Exception{
		
		//获取上传文件
		UploadFile uf = UploadUtils.upload(file, fileFileName, fileContentType);
		
		if(StringUtils.isEmpty(uf.getFileUrlOriginal()) && StringUtils.isEmpty(fileId)){
			//不存在文件则清空图片ID
			article.setTitlePic("");
		}
		
		//插入上传文件
		if(uploadFileService.insertUploadFile(uf) > 0){
			article.setTitlePic(uf.getId());
		}
		
		if(PAGE_LOAD_ADD.equals(articleLoadType)){
			
			//当前为添加操作
			articleService.insertArticle(article);	
			
		} else if(PAGE_LOAD_UPDATE.equals(articleLoadType)){
			
			//当前操作为更新
			articleService.dynamicUpdateCategory(article);	
		}

		return "retArticleIndex";
	}
	
	/**
	 * 文章删除提交方法
	 */
	@Action(value="okSubmitArticleDelete")
	public void okSubmitArticleDelete() throws Exception{
		
		articleService.deleteById(articleId);
		
		delSuccess();
	}
	
	/**
	 * 文章批量删除提交方法
	 */
	@Action(value="okSubmitArticleBatchDel")
	public void okSubmitArticleBatchDel() throws Exception{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", articleService.batchDeleteById(checkboxList));
		
		Struts2Utils.renderJson(retMap);
	}
	
	/**
	 * 文章保存排序 
	 * @return
	 */
	@Action(value="saveOrderNumArticle")
	public void saveOrderNumArticle() throws Exception{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", articleService.batchDynamicUpdateArticle(articleList));
		
		Struts2Utils.renderJson(retMap);
		
	}
	
	/********************************************************* Get Set 方法 ***************************************************************************/
	/** 获取栏目内容 */
	public Category getCategory() {
		return category;
	}

	/** 设置栏目内容 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/** 获取当前栏目Id */
	public String getCategoryId() {
		return categoryId;
	}
	/** 设置当前栏目Id */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/** 获取父级栏目名称 */
	public String getParentName() {
		return parentName;
	}
	/** 设置父级栏目名称 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/** 获取父级栏目Id */
	public String getParentId() {
		return parentId;
	}
	/** 设置父级栏目Id */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 获取文章页面加载类型 */
	public String getArticleLoadType() {
		return articleLoadType;
	}
	
	/** 设置文章页面加载类型 */
	public void setArticleLoadType(String articleLoadType) {
		this.articleLoadType = articleLoadType;
	}
	
	/** 获取文章实体 */
	public Article getArticle() {
		return article;
	}
	
	/** 设置文章实体 */
	public void setArticle(Article article) {
		this.article = article;
	}
	
	/** 获取文章内容集合 */
	public List<Article> getArticleList() {
		return articleList;
	}
	/** 设置文章内容集合 */
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	
	/** 获取文章标题检索条件 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/** 设置文章标题检索条件 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	/** 获取文章检索条件类 */
	public ArticleCustomCriteria getCriteria() {
		return criteria;
	}
	/** 设置文章检索条件类 */
	public void setCriteria(ArticleCustomCriteria criteria) {
		this.criteria = criteria;
	}
	
	/** 获取当前文章Id */
	public String getArticleId() {
		return articleId;
	}
	/** 设置当前文章Id */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	/** 获取checkbox列表 */
	public String[] getCheckboxList() {
		return checkboxList;
	}
	/** 设置checkbox列表 */
	public void setCheckboxList(String[] checkboxList) {
		this.checkboxList = checkboxList;
	}
	/** 获取栏目内容集合 */
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/** 设置栏目内容集合 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	/** 获取上传文件 */
	public UploadFile getDefultUf() {
		return defultUf;
	}

	/** 设置上传文件 */
	public void setDefultUf(UploadFile defultUf) {
		this.defultUf = defultUf;
	}
	/** 获取文章编辑页标题 */
	public String getArticleEditMenu() {
		return articleEditMenu;
	}
	/** 设置文章编辑页标题 */
	public void setArticleEditMenu(String articleEditMenu) {
		this.articleEditMenu = articleEditMenu;
	}
	
	/** 获取栏目页面加载类型 */
	public String getCategoryLoadType() {
		return categoryLoadType;
	}
	/** 设置栏目页面加载类型 */
	public void setCategoryLoadType(String categoryLoadType) {
		this.categoryLoadType = categoryLoadType;
	}
	/** 获取父级栏目DropdownList */
	public Map<String, String> getFirstCategoryMap() {
		return firstCategoryMap;
	}
	/** 设置父级栏目DropdownList */
	public void setFirstCategoryMap(Map<String, String> firstCategoryMap) {
		this.firstCategoryMap = firstCategoryMap;
	}
	
	/** 获取二级栏目DropdownList */
	public Map<String, String> getSecondCategoryMap() {
		return secondCategoryMap;
	}
	/** 设置二级栏目DropdownList */
	public void setSecondCategoryMap(Map<String, String> secondCategoryMap) {
		this.secondCategoryMap = secondCategoryMap;
	}
}
