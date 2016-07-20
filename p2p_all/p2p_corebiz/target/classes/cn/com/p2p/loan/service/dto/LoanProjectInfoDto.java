package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 融资项目基本Dto
 * 
 * @author 
 *
 */
public class LoanProjectInfoDto {
    
	
	   /**ID*/
    private String id;

    /**融资编号*/
    private String loanCode;

    /**项目名称*/
    private String loanName;

    /**客户编号*/
    private String customCode;

    /**客户名称*/
    private String customName;

    /**融资状态*/
    private String loanStatus;

    /**融资类型*/
    private String loanType;

    /**融资金额*/
    private Integer loanAmount;

    /**计息方式*/
    private String loanInterestManner;

    /**利率*/
    private BigDecimal loanInterestRates;

    /**融资期限（天）*/
    private Integer loanTimeLimitDays;

    /**融资期限*/
    private Integer loanTimeLimit;

    /**融资期限单位*/
    private String loanTimeLimitUnit;

    /**起投份数*/
    private Integer loanStartShare;

    /**每份单价*/
    private Integer loanUnitPrice;

    /**总份数*/
    private Integer loanTotalShare;
    
    /**融资发布时间*/
    private Date loanPostTime;

    /**投资截止时间*/
    private Date loanEndTime;

    /**预期利息*/
    private BigDecimal loanInterest;

    /**单用户最大投资限额*/
    private Integer loanMaxInvest;

    /**交易流水号*/
    private String serialNumber;

    /**平台管理费率*/
    private BigDecimal platformRate;

    /**平台管理费*/
    private BigDecimal platformFee;

    /**平台费流水号*/
    private String platSerialNumber;

    /**担保费率*/
    private BigDecimal guaranteeRate;

    /**担保费*/
    private BigDecimal guaranteeFee;

    /**担保费流水号*/
    private String vouchSerialNumber;

    /**备付金费率*/
    private BigDecimal depositRate;

    /**已投份数*/
    private Integer currentInvestedShare;

    /**剩余份数*/
    private Integer currentSurplusShare;

    /**实际融资额*/
    private Integer actualAmount;

    /**实际放款总额*/
    private BigDecimal amount;

    /**放款时间*/
    private Date loanTime;

    /**起息日期*/
    private Date carryInterestFrom;

    /**到期日期*/
    private Date carryInterestTo;

    /**还款类型*/
    private String repayType;

    /**还款总期数*/
    private Integer totalRepayNumber;

    /**当期还款期数*/
    private Integer receivableRepayNumber;

    /**当期还款状态*/
    private String receivableRepayStatus;

    /**下次还款日*/
    private Date receivableNextDate;

    /**应收总额*/
    private BigDecimal receivableSum;

    /**应收本金*/
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    private BigDecimal receivableInterest;

    /**应收滞纳金*/
    private BigDecimal receivableDelaying;

    /**应收逾期罚金*/
    private BigDecimal receivableOverdue;

    /**已收总额*/
    private BigDecimal receivedSum;

    /**已收本金*/
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    private BigDecimal receivedInterest;

    /**已收滞纳金*/
    private BigDecimal receivedDelaying;

    /**已收逾期罚金*/
    private BigDecimal receivedOverdue;

    /**申请者*/
    private String applyUserId;

    /**商户ID*/
    private String tenantId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者*/
    private String createUserId;

    /**更新者*/
    private String updateUserId;

    /**版本*/
    private Integer version;
    
    
    /** 获取融资编号 */
    public String getLoanCode() {
        return loanCode;
    }

    /** 设置融资编号 */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    


	/** 获取融资项目 */
	public String getLoanName() {
		return loanName;
	}

	/** 设置融资项目 */
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	/** 获取融资总额 */
	public Integer getLoanAmount() {
		return loanAmount;
	}

	/** 设置融资总额 */
	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	/** 获取融资期限（天） */
	public Integer getLoanTimeLimitDays() {
		return loanTimeLimitDays;
	}

	/** 设置融资期限（天） */
	public void setLoanTimeLimitDays(Integer loanTimeLimitDays) {
		this.loanTimeLimitDays = loanTimeLimitDays;
	}

	/** 获取融资期限 */
	public Integer getLoanTimeLimit() {
		return loanTimeLimit;
	}

	/** 设置融资期限 */
	public void setLoanTimeLimit(Integer loanTimeLimit) {
		this.loanTimeLimit = loanTimeLimit;
	}

	/** 获取融资期限单位 */
	public String getLoanTimeLimitUnit() {
		return loanTimeLimitUnit;
	}

	/** 设置融资期限单位 */
	public void setLoanTimeLimitUnit(String loanTimeLimitUnit) {
		this.loanTimeLimitUnit = loanTimeLimitUnit;
	}

	/** 获取每份单价 */
	public Integer getLoanUnitPrice() {
		return loanUnitPrice;
	}

	/** 设置每份单价 */
	public void setLoanUnitPrice(Integer loanUnitPrice) {
		this.loanUnitPrice = loanUnitPrice;
	}

	/** 获取起投份额 */
	public Integer getLoanStartShare() {
		return loanStartShare;
	}

	/** 设置起投份额 */
	public void setLoanStartShare(Integer loanStartShare) {
		this.loanStartShare = loanStartShare;
	}

	/** 获取还款方式 */
	public String getLoanInterestManner() {
		return loanInterestManner;
	}

	/** 设置还款方式 */
	public void setLoanInterestManner(String loanInterestManner) {
		this.loanInterestManner = loanInterestManner;
	}

	/** 获取还款途径 */
	public String getRepayType() {
		return repayType;
	}

	/** 设置还款途径 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	/** 获取最大投资限额 */
	public Integer getLoanMaxInvest() {
		return loanMaxInvest;
	}

	/** 设置最大投资限额 */
	public void setLoanMaxInvest(Integer loanMaxInvest) {
		this.loanMaxInvest = loanMaxInvest;
	}

	/** 获取年化收益率 */
	public BigDecimal getLoanInterestRates() {
		return loanInterestRates;
	}

	/** 设置年化收益率 */
	public void setLoanInterestRates(BigDecimal loanInterestRates) {
		this.loanInterestRates = loanInterestRates;
	}

	/** 获取投资截止时间 */
	public Date getLoanEndTime() {
		return loanEndTime;
	}

	/** 设置投资截止时间 */
	public void setLoanEndTime(Date loanEndTime) {
		this.loanEndTime = loanEndTime;
	}

	/** 获取平台管理费率 */
	public BigDecimal getPlatformRate() {
		return platformRate;
	}

	/** 设置平台管理费率 */
	public void setPlatformRate(BigDecimal platformRate) {
		this.platformRate = platformRate;
	}

	/** 获取担保费率 */
	public BigDecimal getGuaranteeRate() {
		return guaranteeRate;
	}

	/** 设置担保费率 */
	public void setGuaranteeRate(BigDecimal guaranteeRate) {
		this.guaranteeRate = guaranteeRate;
	}
	
	public LoanProjectInfoDto() {
		super();
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
	 * @return the customName
	 */
	public String getCustomName() {
		return customName;
	}

	/**
	 * @param customName the customName to set
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	

    /** 获取已投份数 */
    public Integer getCurrentInvestedShare() {
        return currentInvestedShare;
    }

    /** 设置已投份数*/
    public void setCurrentInvestedShare(Integer currentInvestedShare) {
        this.currentInvestedShare = currentInvestedShare;
    }

    /** 获取剩余份数 */
    public Integer getCurrentSurplusShare() {
        return currentSurplusShare;
    }

    /** 设置剩余份数*/
    public void setCurrentSurplusShare(Integer currentSurplusShare) {
        this.currentSurplusShare = currentSurplusShare;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

    /** 获取申请者 */
    public String getApplyUserId() {
        return applyUserId;
    }

    /** 设置申请者*/
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

	/**
	 * @return the customCode
	 */
	public String getCustomCode() {
		return customCode;
	}

	/**
	 * @param customCode the customCode to set
	 */
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	/**
	 * @return the loanTotalShare
	 */
	public Integer getLoanTotalShare() {
		return loanTotalShare;
	}

	/**
	 * @param loanTotalShare the loanTotalShare to set
	 */
	public void setLoanTotalShare(Integer loanTotalShare) {
		this.loanTotalShare = loanTotalShare;
	}

	/**
	 * @return the loanInterest
	 */
	public BigDecimal getLoanInterest() {
		return loanInterest;
	}

	/**
	 * @param loanInterest the loanInterest to set
	 */
	public void setLoanInterest(BigDecimal loanInterest) {
		this.loanInterest = loanInterest;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the platformFee
	 */
	public BigDecimal getPlatformFee() {
		return platformFee;
	}

	/**
	 * @param platformFee the platformFee to set
	 */
	public void setPlatformFee(BigDecimal platformFee) {
		this.platformFee = platformFee;
	}

	/**
	 * @return the platSerialNumber
	 */
	public String getPlatSerialNumber() {
		return platSerialNumber;
	}

	/**
	 * @param platSerialNumber the platSerialNumber to set
	 */
	public void setPlatSerialNumber(String platSerialNumber) {
		this.platSerialNumber = platSerialNumber;
	}

	/**
	 * @return the guaranteeFee
	 */
	public BigDecimal getGuaranteeFee() {
		return guaranteeFee;
	}

	/**
	 * @param guaranteeFee the guaranteeFee to set
	 */
	public void setGuaranteeFee(BigDecimal guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}

	/**
	 * @return the vouchSerialNumber
	 */
	public String getVouchSerialNumber() {
		return vouchSerialNumber;
	}

	/**
	 * @param vouchSerialNumber the vouchSerialNumber to set
	 */
	public void setVouchSerialNumber(String vouchSerialNumber) {
		this.vouchSerialNumber = vouchSerialNumber;
	}

	/**
	 * @return the depositRate
	 */
	public BigDecimal getDepositRate() {
		return depositRate;
	}

	/**
	 * @param depositRate the depositRate to set
	 */
	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}

	/**
	 * @return the actualAmount
	 */
	public Integer getActualAmount() {
		return actualAmount;
	}

	/**
	 * @param actualAmount the actualAmount to set
	 */
	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the loanTime
	 */
	public Date getLoanTime() {
		return loanTime;
	}

	/**
	 * @param loanTime the loanTime to set
	 */
	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
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

	/**
	 * @return the carryInterestTo
	 */
	public Date getCarryInterestTo() {
		return carryInterestTo;
	}

	/**
	 * @param carryInterestTo the carryInterestTo to set
	 */
	public void setCarryInterestTo(Date carryInterestTo) {
		this.carryInterestTo = carryInterestTo;
	}

	/**
	 * @return the totalRepayNumber
	 */
	public Integer getTotalRepayNumber() {
		return totalRepayNumber;
	}

	/**
	 * @param totalRepayNumber the totalRepayNumber to set
	 */
	public void setTotalRepayNumber(Integer totalRepayNumber) {
		this.totalRepayNumber = totalRepayNumber;
	}

	/**
	 * @return the receivableRepayNumber
	 */
	public Integer getReceivableRepayNumber() {
		return receivableRepayNumber;
	}

	/**
	 * @param receivableRepayNumber the receivableRepayNumber to set
	 */
	public void setReceivableRepayNumber(Integer receivableRepayNumber) {
		this.receivableRepayNumber = receivableRepayNumber;
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
	 * @return the receivableNextDate
	 */
	public Date getReceivableNextDate() {
		return receivableNextDate;
	}

	/**
	 * @param receivableNextDate the receivableNextDate to set
	 */
	public void setReceivableNextDate(Date receivableNextDate) {
		this.receivableNextDate = receivableNextDate;
	}

	/**
	 * @return the receivableSum
	 */
	public BigDecimal getReceivableSum() {
		return receivableSum;
	}

	/**
	 * @param receivableSum the receivableSum to set
	 */
	public void setReceivableSum(BigDecimal receivableSum) {
		this.receivableSum = receivableSum;
	}

	/**
	 * @return the receivablePrincipal
	 */
	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}

	/**
	 * @param receivablePrincipal the receivablePrincipal to set
	 */
	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}

	/**
	 * @return the receivableInterest
	 */
	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}

	/**
	 * @param receivableInterest the receivableInterest to set
	 */
	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}

	/**
	 * @return the receivableDelaying
	 */
	public BigDecimal getReceivableDelaying() {
		return receivableDelaying;
	}

	/**
	 * @param receivableDelaying the receivableDelaying to set
	 */
	public void setReceivableDelaying(BigDecimal receivableDelaying) {
		this.receivableDelaying = receivableDelaying;
	}

	/**
	 * @return the receivableOverdue
	 */
	public BigDecimal getReceivableOverdue() {
		return receivableOverdue;
	}

	/**
	 * @param receivableOverdue the receivableOverdue to set
	 */
	public void setReceivableOverdue(BigDecimal receivableOverdue) {
		this.receivableOverdue = receivableOverdue;
	}

	/**
	 * @return the receivedSum
	 */
	public BigDecimal getReceivedSum() {
		return receivedSum;
	}

	/**
	 * @param receivedSum the receivedSum to set
	 */
	public void setReceivedSum(BigDecimal receivedSum) {
		this.receivedSum = receivedSum;
	}

	/**
	 * @return the receivedPrincipal
	 */
	public BigDecimal getReceivedPrincipal() {
		return receivedPrincipal;
	}

	/**
	 * @param receivedPrincipal the receivedPrincipal to set
	 */
	public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
		this.receivedPrincipal = receivedPrincipal;
	}

	/**
	 * @return the receivedInterest
	 */
	public BigDecimal getReceivedInterest() {
		return receivedInterest;
	}

	/**
	 * @param receivedInterest the receivedInterest to set
	 */
	public void setReceivedInterest(BigDecimal receivedInterest) {
		this.receivedInterest = receivedInterest;
	}

	/**
	 * @return the receivedDelaying
	 */
	public BigDecimal getReceivedDelaying() {
		return receivedDelaying;
	}

	/**
	 * @param receivedDelaying the receivedDelaying to set
	 */
	public void setReceivedDelaying(BigDecimal receivedDelaying) {
		this.receivedDelaying = receivedDelaying;
	}

	/**
	 * @return the receivedOverdue
	 */
	public BigDecimal getReceivedOverdue() {
		return receivedOverdue;
	}

	/**
	 * @param receivedOverdue the receivedOverdue to set
	 */
	public void setReceivedOverdue(BigDecimal receivedOverdue) {
		this.receivedOverdue = receivedOverdue;
	}

	/**
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId the updateUserId to set
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getLoanPostTime() {
		return loanPostTime;
	}

	public void setLoanPostTime(Date loanPostTime) {
		this.loanPostTime = loanPostTime;
	}
	
	
}
