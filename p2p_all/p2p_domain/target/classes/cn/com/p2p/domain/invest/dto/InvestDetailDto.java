package cn.com.p2p.domain.invest.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.p2p.domain.invest.entity.InvestDetail;

public class InvestDetailDto {
	
	/**计划还款日期*/
	private Date recievePlanDate;
	/**已收总金额*/
	private BigDecimal recievedPayment;
	/**逾期罚息*/
    private BigDecimal penalty;
    /**待收款*/
    private BigDecimal collectPayment;
    /**回款列表*/
    private List<InvestDetail> investDetail;
    /**投资的日期*/
    private String investDate;
    /**投资月份*/
    private String investMonth;
    
	

	public List<InvestDetail> getInvestDetail() {
		return investDetail;
	}

	public void setInvestDetail(List<InvestDetail> investDetail) {
		this.investDetail = investDetail;
	}

	public BigDecimal getRecievedPayment() {
		return recievedPayment;
	}

	public void setRecievedPayment(BigDecimal recievedPayment) {
		this.recievedPayment = recievedPayment;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getCollectPayment() {
		return collectPayment;
	}

	public void setCollectPayment(BigDecimal collectPayment) {
		this.collectPayment = collectPayment;
	}

	public Date getRecievePlanDate() {
		return recievePlanDate;
	}

	public void setRecievePlanDate(Date recievePlanDate) {
		this.recievePlanDate = recievePlanDate;
	}

	public String getInvestDate() {
		return investDate;
	}

	public void setInvestDate(String investDate) {
		this.investDate = investDate;
	}

	public String getInvestMonth() {
		return investMonth;
	}

	public void setInvestMonth(String investMonth) {
		this.investMonth = investMonth;
	}
}
