/**
 * 
 */
package cn.com.p2p.mgr.action.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;

/**
 * @author  投资查询
 */
@Namespace("/business/investment")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker") })
public class InvestmentAction extends BaseAction {

	@Autowired
	private InvestSearchService investSearchService;
	@Autowired
	private LoanSearchService loanSearchService;

	private List<Invest> lstInvest = new ArrayList<Invest>();
	
	private Invest invest;

	private LoanDto loanDto;
	
	private String loanCode;
	
	private String investId;

	 /** 投资编号 */
	 private String investCode;
	 /** 用户名 */
	 private String loginName;
	 /** 融资项目名 */
	 private String loanName;
	 /** 投资类型 */
	 private String investType;
	 /** 状态 */
	 private String investStatus;
	 /** 投资时间（大） */
	 private Date investMaxTime;
	 /** 投资时间（小） */
	 private Date investMinTime;
	 
	 private InvestCriteria criteria = new InvestCriteria();

	/**
	 * @author  业务查询——投资查询列表页
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投资查询——列表
	 */
	@Override
	@Action(value = "index")
	public String init() throws Exception {
		criteria.setInvestCode(investCode, Operator.like);
		criteria.setStatus(investStatus, Operator.equal);
		criteria.setInvestType(investType,Operator.equal);
		criteria.setLoanName(loanName, Operator.like);
		criteria.setInvestUserName(loginName, Operator.like);
		criteria.setInvestmentTime(investMaxTime, Operator.lessThanAndEqual);
		criteria.setInvestmentTime(investMinTime, Operator.greaterThanAndEqual);
		criteria.setSortFields(OrderField.createTime, SortType.DESC);
		lstInvest = investSearchService.getInvestAll(criteria);
		return INIT;
	}

	/**
	 * 投资查询——查看
	 */
	@Action(value = "view")
	public String view() throws Exception {
		invest = investSearchService.getInvestById(investId);
		loanDto = loanSearchService.getLoanInfo(loanCode,
				SearchInfoTypeEnum.COMMON_TAB_ALL);
		return VIEW;
	}

	/**
	 * @return the lstInvest
	 */
	public List<Invest> getLstInvest() {
		return lstInvest;
	}

	/**
	 * @param lstInvest
	 *            the lstInvest to set
	 */
	public void setLstInvest(List<Invest> lstInvest) {
		this.lstInvest = lstInvest;
	}

	/**
	 * @return the invest
	 */
	public Invest getInvest() {
		return invest;
	}

	/**
	 * @param invest
	 *            the invest to set
	 */
	public void setInvest(Invest invest) {
		this.invest = invest;
	}

	public LoanDto getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(LoanDto loanDto) {
		this.loanDto = loanDto;
	}

	public String getInvestCode() {
		return investCode;
	}

	public void setInvestCode(String investCode) {
		this.investCode = investCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public String getInvestStatus() {
		return investStatus;
	}

	public void setInvestStatus(String investStatus) {
		this.investStatus = investStatus;
	}

	public Date getInvestMaxTime() {
		return investMaxTime;
	}

	public void setInvestMaxTime(Date investMaxTime) {
		this.investMaxTime = investMaxTime;
	}

	public Date getInvestMinTime() {
		return investMinTime;
	}

	public void setInvestMinTime(Date investMinTime) {
		this.investMinTime = investMinTime;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public InvestCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(InvestCriteria criteria) {
		this.criteria = criteria;
	}


}
