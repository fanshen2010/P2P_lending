package cn.com.p2p.invest.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资信息（对应前台个人中心收益详情）
 * @author 
 *
 */
public class InvestIncomeDto {
	/**累计投资*/
    private BigDecimal totalInvestmentAmount;
    /**累计收益*/
    private BigDecimal totalInvestEarnings;
    /**待收收益*/
    private BigDecimal collectEarnings;
    /**已收收益*/
    private BigDecimal receivedEarnings;    
    /**下次收款日期*/
    private Date nextPaymentDate;
    /**下次收款金额*/
    private BigDecimal nextPaymentAmount;
    /**待生效的投资总额*/
    private BigDecimal toInvestAmount;
    /**回款中的投资总额*/
    private BigDecimal investingAmount;
    /**已回款的投资总额*/
    private BigDecimal investedAmount;
    /**待生效的投资笔数*/
    private int toInvestCount;
    /**回款中的投资笔数*/
    private int investingCount;
    
    /** 获取累计投资 */
	public BigDecimal getTotalInvestmentAmount() {
		return totalInvestmentAmount;
	}
	
	/** 设置累计投资 */
	public void setTotalInvestmentAmount(BigDecimal totalInvestmentAmount) {
		this.totalInvestmentAmount = totalInvestmentAmount;
	}
	
	/** 获取累计收益 */
	public BigDecimal getTotalInvestEarnings() {
		return totalInvestEarnings;
	}
	
	/** 设置累计收益 */
	public void setTotalInvestEarnings(BigDecimal totalInvestEarnings) {
		this.totalInvestEarnings = totalInvestEarnings;
	}
	
	/** 获取待收收益 */
	public BigDecimal getCollectEarnings() {
		return collectEarnings;
	}
	
	/** 设置待收收益 */
	public void setCollectEarnings(BigDecimal collectEarnings) {
		this.collectEarnings = collectEarnings;
	}
	
	/** 获取下次收款日期 */
	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}
	
	/** 设置下次收款日期 */
	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	
	/** 获取下次收款金额 */
	public BigDecimal getNextPaymentAmount() {
		return nextPaymentAmount;
	}
	
	/** 设置下次收款金额 */
	public void setNextPaymentAmount(BigDecimal nextPaymentAmount) {
		this.nextPaymentAmount = nextPaymentAmount;
	}

	/** 获取已收收益*/
	public BigDecimal getReceivedEarnings() {
		return receivedEarnings;
	}

	/** 设置已收收益*/
	public void setReceivedEarnings(BigDecimal receivedEarnings) {
		this.receivedEarnings = receivedEarnings;
	}

	/** 获取待生效的投资总额*/
	public BigDecimal getToInvestAmount() {
		return toInvestAmount;
	}

	/** 设置待生效的投资总额*/
	public void setToInvestAmount(BigDecimal toInvestAmount) {
		this.toInvestAmount = toInvestAmount;
	}

	/** 获取回款中的投资总额*/
	public BigDecimal getInvestingAmount() {
		return investingAmount;
	}

	/** 设置回款中的投资总额*/
	public void setInvestingAmount(BigDecimal investingAmount) {
		this.investingAmount = investingAmount;
	}

	/** 获取已回款的投资总额*/
	public BigDecimal getInvestedAmount() {
		return investedAmount;
	}

	/** 设置已回款的投资总额*/
	public void setInvestedAmount(BigDecimal investedAmount) {
		this.investedAmount = investedAmount;
	}

	/** 获取待生效的投资笔数*/
	public int getToInvestCount() {
		return toInvestCount;
	}

	/** 设置待生效的投资笔数*/
	public void setToInvestCount(int toInvestCount) {
		this.toInvestCount = toInvestCount;
	}

	/** 获取回款中的投资笔数*/
	public int getInvestingCount() {
		return investingCount;
	}

	/** 回款中的投资笔数*/
	public void setInvestingCount(int investingCount) {
		this.investingCount = investingCount;
	}
	
	
}
