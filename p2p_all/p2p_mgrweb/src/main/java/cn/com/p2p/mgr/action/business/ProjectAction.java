 package cn.com.p2p.mgr.action.business;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;

/**
 * @author 
 *
 */
@Namespace("/business/project")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker")})
public class ProjectAction extends BaseAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	LoanSearchService loanSearchService;
	
	private List<Loan> lstloan = new ArrayList<Loan>();
	
	private LoanDto loanDto;
	
	private String loanCode;
	
	/** 融资编号*/
	private String loanNo;
	/** 融资项目名*/
	private String loanName;
	/** 客户名称*/
	private String customerName;
	/** 融资顾问*/
	private String loanConsultant;
	/** 状态*/
	private String loanStatus;
	/** 融资金额（大）*/
	private Integer loanMaxAmount;
	/** 融资金额（小）*/
	private Integer loanMinAmount;
	/** 年化收益率（大）*/
	private BigDecimal loanMaxRate;
	/** 年华收益率（小）*/
	private BigDecimal loanMinRate;
	
	private LoanCommSelCriteria criteria = new LoanCommSelCriteria();
	
	/** 当前请求方式 */
	private String method = org.apache.struts2.ServletActionContext.getRequest() .getMethod();
	
	
	/**
	 * 业务管理-项目查询
	 * 列表页
	 * @author 
	 */
	@Action(value = "index")
	public String init() throws Exception {
		
		//当前请求为get则转换编码 防止乱码
		if("GET".equalsIgnoreCase(method)){	

			//不为空则进行转码
			if(StringUtils.isNotEmpty(customerName)){
				customerName = new String(customerName.getBytes("ISO-8859-1"), "UTF-8");	
			}
		}
		
		criteria.setLoanCode(loanNo);
		criteria.setLoanName(loanName);
		criteria.setCustomName(customerName);
		List<String> status = new ArrayList<String>();
		if(loanStatus!=null&&StringUtils.isNotEmpty(loanStatus.toString())){
		status.add(loanStatus);
		criteria.setStatus(status);
		}
		criteria.setLoanAmountMax(loanMaxAmount);
		criteria.setLoanAmountMin(loanMinAmount);
		criteria.setLoanConsultant(loanConsultant);
		criteria.setLoanInterestRatesMax(loanMaxRate);
		criteria.setLoanInterestRatesMin(loanMinRate);
		lstloan = loanSearchService.getPageLoanInfoByCriteria(criteria);
		return INIT;
	}
	
	@Action(value = "view")
	public String view() throws Exception{
		loanDto = loanSearchService.getLoanInfo(loanCode,
				SearchInfoTypeEnum.COMMON_TAB_ALL);
		return VIEW;
	}


	/**
	 * @return the lstloan
	 */
	public List<Loan> getLstloan() {
		return lstloan;
	}


	/**
	 * @param lstloan the lstloan to set
	 */
	public void setLstloan(List<Loan> lstloan) {
		this.lstloan = lstloan;
	}

	public LoanDto getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(LoanDto loanDto) {
		this.loanDto = loanDto;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLoanConsultant() {
		return loanConsultant;
	}

	public void setLoanConsultant(String loanConsultant) {
		this.loanConsultant = loanConsultant;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Integer getLoanMaxAmount() {
		return loanMaxAmount;
	}

	public void setLoanMaxAmount(Integer loanMaxAmount) {
		this.loanMaxAmount = loanMaxAmount;
	}

	public Integer getLoanMinAmount() {
		return loanMinAmount;
	}

	public void setLoanMinAmount(Integer loanMinAmount) {
		this.loanMinAmount = loanMinAmount;
	}

	public BigDecimal getLoanMaxRate() {
		return loanMaxRate;
	}

	public void setLoanMaxRate(BigDecimal loanMaxRate) {
		this.loanMaxRate = loanMaxRate;
	}

	public BigDecimal getLoanMinRate() {
		return loanMinRate;
	}

	public void setLoanMinRate(BigDecimal loanMinRate) {
		this.loanMinRate = loanMinRate;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public LoanCommSelCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(LoanCommSelCriteria criteria) {
		this.criteria = criteria;
	}

}
