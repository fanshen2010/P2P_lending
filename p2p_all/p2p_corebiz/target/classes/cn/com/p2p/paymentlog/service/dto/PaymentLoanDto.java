package cn.com.p2p.paymentlog.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 交易支付日志中融资信息数据传输对象
 * </p>
 *
 * @author 
 * @date 2015年4月21日 17:15:07
 */
public class PaymentLoanDto {

	/**
	 * 项目名称
	 */
	private String loanName;
	/**
	 * 项目编号
	 */
	private String loanCode;

	/**
	 * 金额(项目发行规模/结算金额/转账金额/还款金额)
	 */
	private BigDecimal amount;

	/* 投资支付查询 invest */
	/**
	 * 预期年化收益率
	 */
	private BigDecimal loanInterestRates;
	/**
	 * 起息日
	 */
	private Date carryInterestFrom;
	/**
	 * 到息日
	 */
	private Date carryInterestTo;
	/**
	 * 项目投资期限
	 */
	private String loanTimeLimit;
	/**
	 * 结算用途/转账用途
	 */
	private String usage;
	/**
	 * 投资人用户名
	 */
	private String investorLogin;

	/**
	 * 支付交易流水号
	 */
	private String paymentNo;
	/**
	 * 融资人用户名
	 */
	private String financierLogin;
	/**
	 * 项目结算时间
	 */
	private Date settlementDate;
	/**
	 * 结算类型
	 */
	private String settlementType;
	/**
	 * 转账结果
	 */
	private String result;

	/* 项目还款查询 repayment */
	/**
	 * 还款人用户名
	 */
	private String repayLogin;
	/**
	 * 还款类型
	 */
	private String repayType;
	/**
	 * 发起还款时间
	 */
	private Date repayDate;
	/**
	 * 还款处理时间
	 */
	private Date repayDealDate;

	/*
	 * ==================================================================
	 * ===========================Get/Set方法============================
	 * ==================================================================
	 */
	/**
	 * 预期年化收益率
	 * 
	 * @return
	 */
	public BigDecimal getLoanInterestRates() {
		return loanInterestRates;
	}

	/**
	 * 预期年化收益率
	 * 
	 * @param loanInterestRates
	 */
	public void setLoanInterestRates(BigDecimal loanInterestRates) {
		this.loanInterestRates = loanInterestRates;
	}

	/**
	 * 起息日
	 * 
	 * @return
	 */
	public Date getCarryInterestFrom() {
		return carryInterestFrom;
	}

	/**
	 * 起息日
	 * 
	 * @param carryInterestFrom
	 */
	public void setCarryInterestFrom(Date carryInterestFrom) {
		this.carryInterestFrom = carryInterestFrom;
	}

	/**
	 * 到息日
	 * 
	 * @return
	 */
	public Date getCarryInterestTo() {
		return carryInterestTo;
	}

	/**
	 * 到息日
	 * 
	 * @param carryInterestTo
	 */
	public void setCarryInterestTo(Date carryInterestTo) {
		this.carryInterestTo = carryInterestTo;
	}

	/**
	 * 项目投资期限
	 * 
	 * @return
	 */
	public String getLoanTimeLimit() {
		return loanTimeLimit;
	}

	/**
	 * 项目投资期限
	 * 
	 * @param loanTimeLimit
	 */
	public void setLoanTimeLimit(String loanTimeLimit) {
		this.loanTimeLimit = loanTimeLimit;
	}

	/**
	 * 投资人用户名
	 * 
	 * @return
	 */
	public String getInvestorLogin() {
		return investorLogin;
	}

	/**
	 * 投资人用户名
	 * 
	 * @param investorLogin
	 */
	public void setInvestorLogin(String investorLogin) {
		this.investorLogin = investorLogin;
	}


	/**
	 * 融资人用户名
	 * 
	 * @return
	 */
	public String getFinancierLogin() {
		return financierLogin;
	}

	/**
	 * 融资人用户名
	 * 
	 * @param financierLogin
	 */
	public void setFinancierLogin(String financierLogin) {
		this.financierLogin = financierLogin;
	}

	/**
	 * 项目结算时间
	 * 
	 * @return
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}

	/**
	 * 项目结算时间
	 * 
	 * @param settlementDate
	 */
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * 结算类型
	 * 
	 * @return
	 */
	public String getSettlementType() {
		return settlementType;
	}

	/**
	 * 结算类型
	 * 
	 * @param settlementType
	 */
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}


	/**
	 * 转账结果
	 * 
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 转账结果
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 融资人用户名
	 * 
	 * @return
	 */
	public String getRepayLogin() {
		return repayLogin;
	}

	/**
	 * 融资人用户名
	 * 
	 * @param repayLogin
	 */
	public void setRepayLogin(String repayLogin) {
		this.repayLogin = repayLogin;
	}

	/**
	 * 还款类型
	 * 
	 * @return
	 */
	public String getRepayType() {
		return repayType;
	}

	/**
	 * 还款类型
	 * 
	 * @param repayType
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}


	/**
	 * 发起还款时间
	 * 
	 * @return
	 */
	public Date getRepayDate() {
		return repayDate;
	}

	/**
	 * 发起还款时间
	 * 
	 * @param repayDate
	 */
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	/**
	 * 还款处理时间
	 * 
	 * @return
	 */
	public Date getRepayDealDate() {
		return repayDealDate;
	}

	/**
	 * 还款处理时间
	 * 
	 * @param repayDealDate
	 */
	public void setRepayDealDate(Date repayDealDate) {
		this.repayDealDate = repayDealDate;
	}


	/**
	 * 项目名称
	 * 
	 * @return the loanName
	 */
	public String getLoanName() {
		return loanName;
	}

	/**
	 * 项目名称
	 * 
	 * @param loanName
	 *            the loanName to set
	 */
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	/**
	 * 项目编号
	 * 
	 * @return the loanCode
	 */
	public String getLoanCode() {
		return loanCode;
	}

	/**
	 * 项目编号
	 * 
	 * @param loanCode
	 *            the loanCode to set
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/**
	 * 金额(项目发行规模/结算金额/转账金额/还款金额)
	 * 
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 金额(项目发行规模/结算金额/转账金额/还款金额)
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 结算用途/转账用途
	 * 
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * 结算用途/转账用途
	 * 
	 * @param usage
	 *            the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}

	/**
	 * 支付交易流水号
	 * 
	 * @return the paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * 支付交易流水号
	 * 
	 * @param paymentNo
	 *            the paymentNo to set
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
}
