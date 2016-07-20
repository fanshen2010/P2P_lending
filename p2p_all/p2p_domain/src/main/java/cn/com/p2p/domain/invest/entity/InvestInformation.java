package cn.com.p2p.domain.invest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvestInformation implements Serializable{
	
	/**
     * SVU
     */
    private static final long serialVersionUID = 1L;
    
    
    /**投资客户名称*/
	private String investUserName;
	
	/**投资时间*/
	private Date investmentTime;
	
	/**投资金额(元)*/
	private BigDecimal investmentAmount;
	
	/**应收本金(元)*/
	private BigDecimal receivablePrincipal;
	
	/**应收利息(元)*/
	private BigDecimal receivableInterest;
	
	/**用户ID*/
    private String userId;
	
	//=======================前台个人中心累计数据===============================
	
	/**下次收款日期*/
    private Date nextPaymentDate;
    
    /**下次收款金额*/
    private BigDecimal nextPaymentAmount;	
	
	/**累计总收益(元)*/
	private BigDecimal totalInvestEarnings;
	
	/**累计应收利息(元)*/
	private BigDecimal totalReceivableInterest;
	
	/**累计已收收益(元)*/
    private BigDecimal totalReceivedInterest;
    
    /**累计待收罚息(元)*/
	private BigDecimal totalReceivablePunitive;
	
	/**累计已收罚息(元)*/
    private BigDecimal totalReceivedPunitive;    

    /**累计投资金额(元)*/
	private BigDecimal totalInvestmentAmount;
    
    /**累计待生效的投资总额*/
    private BigDecimal totalToInvestAmount;
    
    /**累计回款中的投资总额*/
    private BigDecimal totalInvestingAmount;
    
    /**累计已回款的投资总额*/
    private BigDecimal totalInvestedAmount;
    
    /**累计待生效的投资笔数*/
    private int totalToInvestCount;
    
    /**累计回款中的投资笔数*/
    private int totalInvestingCount;
    
    //=======================前台个人中心图表数据===============================
    
    /**每月计划收益金额*/
    private BigDecimal planEarnings;
    
    /**每月实际收益金额*/
    private BigDecimal actualEarnings;
    
    /**统计月份*/
    private String statisticMonth;
    
    
    

    
    /** 获取投资客户名称 */
    public String getInvestUserName() {
        return investUserName;
    }
    
    /** 设置投资客户名称*/
    public void setInvestUserName(String investUserName) {
        this.investUserName = investUserName;
    }
    
    /**获取投资时间*/
	public Date getInvestmentTime() {
		return investmentTime;
	}
	
	/**设置投资时间*/
	public void setInvestmentTime(Date investmentTime) {
		this.investmentTime = investmentTime;
	}
	
	/**获取投资金额(元)*/
	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}
	
	/**设置投资金额(元)*/
	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	
	/**获取应收本金(元)*/
	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}
	
	/**设置应收本金(元)*/
	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}
	
	/**获取应收利息(元)*/
	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}
	
	/**设置应收利息(元)*/
	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}

	/**下次收款日期*/
	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	/**下次收款日期*/
	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	/**下次收款金额*/
	public BigDecimal getNextPaymentAmount() {
		return nextPaymentAmount;
	}

	/**下次收款金额*/
	public void setNextPaymentAmount(BigDecimal nextPaymentAmount) {
		this.nextPaymentAmount = nextPaymentAmount;
	}

	/**累计总收益(元)*/
	public BigDecimal getTotalInvestEarnings() {
		return totalInvestEarnings;
	}

	/**累计总收益(元)*/
	public void setTotalInvestEarnings(BigDecimal totalInvestEarnings) {
		this.totalInvestEarnings = totalInvestEarnings;
	}
	
	/**累计应收利息(元)*/
	public BigDecimal getTotalReceivableInterest() {
		return totalReceivableInterest;
	}

	/**累计应收利息(元)*/
	public void setTotalReceivableInterest(BigDecimal totalReceivableInterest) {
		this.totalReceivableInterest = totalReceivableInterest;
	}
	
	/**累计已收利息(元)*/
	public BigDecimal getTotalReceivedInterest() {
		return totalReceivedInterest;
	}

	/**累计已收利息(元)*/
	public void setTotalReceivedInterest(BigDecimal totalReceivedInterest) {
		this.totalReceivedInterest = totalReceivedInterest;
	}

	/**累计待收罚息(元)*/
	public BigDecimal getTotalReceivablePunitive() {
		return totalReceivablePunitive;
	}

	/**累计待收罚息(元)*/
	public void setTotalReceivablePunitive(BigDecimal totalReceivablePunitive) {
		this.totalReceivablePunitive = totalReceivablePunitive;
	}

	/**累计已收罚息(元)*/
	public BigDecimal getTotalReceivedPunitive() {
		return totalReceivedPunitive;
	}

	/**累计已收罚息(元)*/
	public void setTotalReceivedPunitive(BigDecimal totalReceivedPunitive) {
		this.totalReceivedPunitive = totalReceivedPunitive;
	}

	/**累计投资金额(元)*/
	public BigDecimal getTotalInvestmentAmount() {
		return totalInvestmentAmount;
	}

	/**累计投资金额(元)*/
	public void setTotalInvestmentAmount(BigDecimal totalInvestmentAmount) {
		this.totalInvestmentAmount = totalInvestmentAmount;
	}

	/**累计待生效的投资金额(元)*/
	public BigDecimal getTotalToInvestAmount() {
		return totalToInvestAmount;
	}

	/**累计待生效的投资金额(元)*/
	public void setTotalToInvestAmount(BigDecimal totalToInvestAmount) {
		this.totalToInvestAmount = totalToInvestAmount;
	}

	/**累计回款中的投资金额(元)*/
	public BigDecimal getTotalInvestingAmount() {
		return totalInvestingAmount;
	}

	/**累计回款中的投资金额(元)*/
	public void setTotalInvestingAmount(BigDecimal totalInvestingAmount) {
		this.totalInvestingAmount = totalInvestingAmount;
	}

	/**累计已回款投资金额(元)*/
	public BigDecimal getTotalInvestedAmount() {
		return totalInvestedAmount;
	}

	/**累计已回款投资金额(元)*/
	public void setTotalInvestedAmount(BigDecimal totalInvestedAmount) {
		this.totalInvestedAmount = totalInvestedAmount;
	}

	/**累计待生效的投资笔数*/
	public int getTotalToInvestCount() {
		return totalToInvestCount;
	}

	/**累计待生效的投资笔数*/
	public void setTotalToInvestCount(int totalToInvestCount) {
		this.totalToInvestCount = totalToInvestCount;
	}

	/**累计回款中的投资笔数*/
	public int getTotalInvestingCount() {
		return totalInvestingCount;
	}

	/**累计回款中的投资笔数*/
	public void setTotalInvestingCount(int totalInvestingCount) {
		this.totalInvestingCount = totalInvestingCount;
	}

	/**每月计划收益金额*/
	public BigDecimal getPlanEarnings() {
		return planEarnings;
	}

	/**每月计划收益金额*/
	public void setPlanEarnings(BigDecimal planEarnings) {
		this.planEarnings = planEarnings;
	}

	/**每月实际收益金额*/
	public BigDecimal getActualEarnings() {
		return actualEarnings;
	}

	/**每月实际收益金额*/
	public void setActualEarnings(BigDecimal actualEarnings) {
		this.actualEarnings = actualEarnings;
	}

	/**统计月份*/
	public String getStatisticMonth() {
		return statisticMonth;
	}

	/**统计月份*/
	public void setStatisticMonth(String statisticMonth) {
		this.statisticMonth = statisticMonth;
	}

    /** 获取用户ID */
    public String getUserId() {
        return userId;
    }

    /** 设置用户ID*/
    public void setUserId(String userId) {
        this.userId = userId;
    }
	
	
	
}
