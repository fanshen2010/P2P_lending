package cn.com.p2p.por.action.agreement;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.DocumentException;

import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;
import freemarker.template.TemplateException;

@Namespace("/agreement")
@Results({@Result(name = BaseAction.INIT,location = "agreement.ftl",type = "freemarker"),
	@Result(name = BaseAction.LOGIN,location = "../login.htm",type = "freemarker")})
public class AgreementAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	/** 栏目Service */
	@Autowired
	private CategoryService categoryService;
	
	/** 栏目Service */
	@Autowired
	private LoanSearchService loanSearchService;
	
	private String agreeType;
	
	// 合同相关参数
	private String loanCode;

	private String investCode;

	private String contractContent;
	
	private boolean condition;
	
	// 协议相关参数
	private String catalog;

	private Category category;

	@Override
	@Action(value = "index")
	public String init() throws Exception{
		//栏目列表
		List<Category> categoryList;
		//栏目查询条件类
		CategoryCriteria categoryCriteria;
		
		agreeType = "agreement";

	    categoryCriteria = new CategoryCriteria();
	    categoryCriteria.setCategoryCode(catalog, Operator.equal);
	    //根据栏目编码获取当前栏目
	    categoryList = categoryService.getCategoryByCriteria(categoryCriteria);
	    if(categoryList!=null && !categoryList.isEmpty()){
	    	category = categoryList.get(0);
	    }
		return INIT;
	}
	
	public String getCatalog(){

		return catalog;
	}


	public void setCatalog(String catalog){

		this.catalog = catalog;
	}


	public Category getCategory(){

		return category;
	}


	public void setCategory(Category category){

		this.category = category;
	}

	public String getAgreeType(){

		return agreeType;
	}


	public void setAgreeType(String agreeType){

		this.agreeType = agreeType;
	}


	public String getLoanCode(){

		return loanCode;
	}


	public void setLoanCode(String loanCode){

		this.loanCode = loanCode;
	}


	public String getContractContent(){

		return contractContent;
	}


	public void setContractContent(String contractContent){

		this.contractContent = contractContent;
	}


	public String getInvestCode(){

		return investCode;
	}


	public void setInvestCode(String investCode){

		this.investCode = investCode;
	}

	public boolean isCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

}
