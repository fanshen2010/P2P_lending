package cn.com.p2p.por.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.cms.ADDto;
import cn.com.p2p.contentmanagent.service.ArticleService;
import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanSearchService;

/**
 * 前台首页
 * 
 * @author 
 */
@Namespace("/")
@Results({ @Result(name = "index", location = "index.ftl", type = "freemarker")})

public class IndexAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 指定融资的所有信息查询 */
	@Autowired
	private LoanSearchService loanSearchService;
	
	/** 文章管理Service */
	@Autowired
	private ArticleService articleService;
	
	/** 栏目Service */
	@Autowired
	private CategoryService categoryService;
	
	
	/** 融资共通查询参数 */
    private LoanCommSelCriteria criteria = new LoanCommSelCriteria();
    
    /** 广告管理数据传输对象 */
    private List<ADDto> lstADDto = new ArrayList<ADDto>();
    
    /** 融资信息列表 */
    private List<Loan> lstLoan = new ArrayList<Loan>();
    
    /** 媒体报道文章内容集合 */
    private List<Article> mediaReports = new ArrayList<Article>();
    
    /** 平台公告文章内容集合 */
    private List<Article> announcements = new ArrayList<Article>();
    
    /** 系统时间*/
    private Date sysDate;
    
    /** 栏目1*/
    private Category categoryF;
    
    /** 栏目2*/
    private Category categoryS;
    
    
	@Action(value="index")
	@Override
	public String init() throws Exception {
		sysDate=DateUtils.getCurrentDateTime();
		return "index";
	}

	
	//----------------------------画面使用数据定义----------------------------------------------
	public List<Loan> getLstLoan() {
		return lstLoan;
	}
	
	public void setLstLoan(List<Loan> lstLoan) {
		this.lstLoan = lstLoan;
	}
	
	public List<Article> getMediaReports() {
		return mediaReports;
	}
	
	public void setMediaReports(List<Article> mediaReports) {
		this.mediaReports = mediaReports;
	}
	
	public List<Article> getAnnouncements() {
		return announcements;
	}
	
	public void setAnnouncements(List<Article> announcements) {
		this.announcements = announcements;
	}

	public List<ADDto> getLstADDto() {
		return lstADDto;
	}

	public void setLstADDto(List<ADDto> lstADDto) {
		this.lstADDto = lstADDto;
	}

	public Date getSysDate() {
		return sysDate;
	}

	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}


	public Category getCategoryF() {
		return categoryF;
	}


	public void setCategoryF(Category categoryF) {
		this.categoryF = categoryF;
	}


	public Category getCategoryS() {
		return categoryS;
	}


	public void setCategoryS(Category categoryS) {
		this.categoryS = categoryS;
	}

}
