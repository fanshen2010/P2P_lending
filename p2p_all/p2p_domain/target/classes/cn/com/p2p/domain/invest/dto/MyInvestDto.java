package cn.com.p2p.domain.invest.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资信息（对应前台个人中心我的投资）
 * @author 
 *
 */
public class MyInvestDto {
    /**投资ID*/
	private String id;
    
	/**融资编号*/
	private String loanCode;
	
	/**投资编号*/
    private String investCode;
	
	/**投资项目名*/
    private String loanName;
    
    /**招标进度*/
    private Integer investPercentage;
    
    /**总份数*/
    private Integer loanTotalShare;
    
    /**已投份数*/
    private Integer currentInvestedShare;
    
    /**投资截止时间*/
    private Date loanEndTime;
    
    /**投资金额*/
    private BigDecimal investmentAmount;
    
    /**预期收益*/
    private BigDecimal investInterst;
    
    /**投资时间*/
    private Date investmentTime;
    
    /**投资状态*/
    private String status;
    
    /**已收本金*/
    private BigDecimal receivedPrincipal;
    
    /**已收利息*/
    private BigDecimal receivedInterest;
    
    /**到期日期*/
    private Date carryInterestTo;
    
    /**结算方式*/
    private String settlementType;
    
    /**下期还款日*/
    private Date nextPaymentDate;
    
    /**起息日*/
    private Date carryInterestFrom;
    
    /**融资金额*/
    private Integer loanAmount;
    
    /**
     * 项目当期还款状态
     */
    private String receivableRepayStatus;
    
    /**
     * 项目状态
     */
    private String loanStatus;
	/** 获取id */
    public String getId() {
        return id;
    }

    /** 设置id*/
    public void setId(String id) {
        this.id = id;
    }

    /** 获取loanName */
	public String getLoanName() {
		return loanName;
	}

	/** 设置loanName */
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	/** 获取investPercentage */
	public Integer getInvestPercentage() {
		return investPercentage;
	}

	/** 设置investPercentage */
	public void setInvestPercentage(Integer investPercentage) {
		this.investPercentage = investPercentage;
	}

	/** 获取loanEndTime */
	public Date getLoanEndTime() {
		return loanEndTime;
	}

	/** 设置loanEndTime */
	public void setLoanEndTime(Date loanEndTime) {
		this.loanEndTime = loanEndTime;
	}

	/** 获取investmentAmount */
	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	/** 设置investmentAmount */
	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	/** 获取investInterst */
	public BigDecimal getInvestInterst() {
		return investInterst;
	}

	/** 设置investInterst */
	public void setInvestInterst(BigDecimal investInterst) {
		this.investInterst = investInterst;
	}

	/** 获取investmentTime */
	public Date getInvestmentTime() {
		return investmentTime;
	}

	/** 设置investmentTime */
	public void setInvestmentTime(Date investmentTime) {
		this.investmentTime = investmentTime;
	}

	/** 获取status */
	public String getStatus() {
		return status;
	}

	/** 设置status */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 获取receivedPrincipal */
	public BigDecimal getReceivedPrincipal() {
		return receivedPrincipal;
	}

	/** 设置receivedPrincipal */
	public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
		this.receivedPrincipal = receivedPrincipal;
	}

	/** 获取receivedInterest */
	public BigDecimal getReceivedInterest() {
		return receivedInterest;
	}

	/** 设置receivedInterest */
	public void setReceivedInterest(BigDecimal receivedInterest) {
		this.receivedInterest = receivedInterest;
	}

	/** 获取carryInterestTo */
	public Date getCarryInterestTo() {
		return carryInterestTo;
	}

	/** 设置carryInterestTo */
	public void setCarryInterestTo(Date carryInterestTo) {
		this.carryInterestTo = carryInterestTo;
	}

	/** 获取loanTotalShare */
	public Integer getLoanTotalShare() {
		return loanTotalShare;
	}

	/** 设置loanTotalShare */
	public void setLoanTotalShare(Integer loanTotalShare) {
		this.loanTotalShare = loanTotalShare;
	}

	/** 获取currentInvestedShare */
	public Integer getCurrentInvestedShare() {
		return currentInvestedShare;
	}

	/** 设置currentInvestedShare */
	public void setCurrentInvestedShare(Integer currentInvestedShare) {
		this.currentInvestedShare = currentInvestedShare;
	}

	/** 获取loanCode */
	public String getLoanCode() {
		return loanCode;
	}

	/** 设置loanCode */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 获取settlementType */
	public String getSettlementType() {
		return settlementType;
	}

	/** 设置settlementType */
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	/** 获取investCode */
	public String getInvestCode() {
		return investCode;
	}

	/** 设置investCode */
	public void setInvestCode(String investCode) {
		this.investCode = investCode;
	}

    /**
     * @return the loanAmount
     */
    public Integer getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the receivableRepayStatus
     */
    public String getReceivableRepayStatus() {
        return receivableRepayStatus;
    }

    /**
     * @param receivableRepayStatus the receivableRepayStatus to set
     */
    public void setReceivableRepayStatus(String receivableRepayStatus) {
        this.receivableRepayStatus = receivableRepayStatus;
    }

    /**
     * @return the loanStatus
     */
    public String getLoanStatus() {
        return loanStatus;
    }

    /**
     * @param loanStatus the loanStatus to set
     */
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    /**
     * @return the nextPaymentDate
     */
    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**
     * @param nextPaymentDate the nextPaymentDate to set
     */
    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }


    /**
     * @return the carryInterestFrom
     */
    public Date getCarryInterestFrom() {
        return carryInterestFrom;
    }

    /**
     * @param carryInterestFrom the carryInterestFrom to set
     */
    public void setCarryInterestFrom(Date carryInterestFrom) {
        this.carryInterestFrom = carryInterestFrom;
    }

}
