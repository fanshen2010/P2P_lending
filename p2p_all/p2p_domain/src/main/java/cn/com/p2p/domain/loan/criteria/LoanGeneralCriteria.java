/** -------------------------------------------------------------------------------------------------
 * 
 * 
 * Title:			
 * 					LoanGeneralCriteria.java
 * Description:		
 * 					融资通用查询条件定义类
 * Dependencies:
 * 					cn.com.p2p.framework.dao.BaseCriteria
 * History:
 *     				Date                   Modifier              Log
 *     				2015-09-17                       Created
 * 
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.math.BigDecimal;

import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * <p>
 * 融资通用查询-查询条件类
 * </p>
 * 
 * <pre>
 * 【查询条件】：
 * 1、融资编号
 * 2、项目名称
 * 3、客户名称
 * 4、最小融资金额
 * 5、最大融资金额
 * 6、最小年化收益率
 * 7、最大年化收益率
 * 8、融资状态
 * </pre>
 * 
 * @author 
 *
 */
public class LoanGeneralCriteria extends BaseCriteria {

	/** 融资编号 */
	private String loanCode;

	/** 项目名称 */
	private String loanName;

	/** 客户名称 */
	private String customName;

	/** 最小融资金额 */
	private Integer loanAmountMin;

	/** 最大融资金额 */
	private Integer loanAmountMax;

	/** 最小年化收益率 */
	private BigDecimal loanInterestRatesMin;

	/** 最大年化收益率 */
	private BigDecimal loanInterestRatesMax;

	/** 融资状态 */
	private String loanStatus;

	/*
	 * ==================================================================
	 * ===========================Get/Set方法============================
	 * ==================================================================
	 */

	/** 获取融资编号 */
	public String getLoanCode() {
		return loanCode;
	}

	/** 设置融资编号 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 获取项目名称 */
	public String getLoanName() {
		return loanName;
	}

	/** 设置项目名称 */
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	/** 获取客户名称 */
	public String getCustomName() {
		return customName;
	}

	/** 设置客户名称 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/** 获取最小融资金额 */
	public Integer getLoanAmountMin() {
		return loanAmountMin;
	}

	/** 设置最小融资金额 */
	public void setLoanAmountMin(Integer loanAmountMin) {
		this.loanAmountMin = loanAmountMin;
	}

	/** 获取最大融资金额 */
	public Integer getLoanAmountMax() {
		return loanAmountMax;
	}

	/** 设置最大融资金额 */
	public void setLoanAmountMax(Integer loanAmountMax) {
		this.loanAmountMax = loanAmountMax;
	}

	/** 获取最小年化收益率 */
	public BigDecimal getLoanInterestRatesMin() {
		return loanInterestRatesMin;
	}

	/** 设置最小年化收益率 */
	public void setLoanInterestRatesMin(BigDecimal loanInterestRatesMin) {
		this.loanInterestRatesMin = loanInterestRatesMin;
	}

	/** 获取最大年化收益率 */
	public BigDecimal getLoanInterestRatesMax() {
		return loanInterestRatesMax;
	}

	/** 设置最大年化收益率 */
	public void setLoanInterestRatesMax(BigDecimal loanInterestRatesMax) {
		this.loanInterestRatesMax = loanInterestRatesMax;
	}

	/** 获取融资状态 */
	public String getLoanStatus() {
		return loanStatus;
	}

	/** 设置融资状态 */
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
}
