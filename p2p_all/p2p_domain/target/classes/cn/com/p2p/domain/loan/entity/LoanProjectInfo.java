package cn.com.p2p.domain.loan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanProjectInfo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**融资编号*/
    private String loanCode;

    /**项目名称*/
    private String loanName;

    /**产品编号*/
    private String productCode;

    /**客户编号*/
    private String customCode;

    /**客户名称*/
    private String customName;

    /**担保公司编号*/
    private String guaranteeComCode;

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

    /**放款时间*/
    private Date loanTime;

    /**起息日期*/
    private Date carryInterestFrom;

    /**到期日期*/
    private Date carryInterestTo;

    /**还款类型*/
    private String repayType;

    /**当期还款期数*/
    private Integer receivableRepayNumber;

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

    /**融资信息Json*/
    private String loanMsg;

    /**项目说明书*/
    private String instructions;
    
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**获取项目名称*/
    public String getLoanName() {
        return loanName;
    }

    /**设置项目名称*/
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }
    /**获取产品编号*/
    public String getProductCode() {
        return productCode;
    }

    /**设置产品编号*/
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**获取客户编号*/
    public String getCustomCode() {
        return customCode;
    }

    /**设置客户编号*/
    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }
    /**获取客户名称*/
    public String getCustomName() {
        return customName;
    }

    /**设置客户名称*/
    public void setCustomName(String customName) {
        this.customName = customName;
    }
    /**获取担保公司编号*/
    public String getGuaranteeComCode() {
        return guaranteeComCode;
    }

    /**设置担保公司编号*/
    public void setGuaranteeComCode(String guaranteeComCode) {
        this.guaranteeComCode = guaranteeComCode;
    }
    /**获取融资状态*/
    public String getLoanStatus() {
        return loanStatus;
    }

    /**设置融资状态*/
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
    /**获取融资类型*/
    public String getLoanType() {
        return loanType;
    }

    /**设置融资类型*/
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    /**获取融资金额*/
    public Integer getLoanAmount() {
        return loanAmount;
    }

    /**设置融资金额*/
    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }
    /**获取计息方式*/
    public String getLoanInterestManner() {
        return loanInterestManner;
    }

    /**设置计息方式*/
    public void setLoanInterestManner(String loanInterestManner) {
        this.loanInterestManner = loanInterestManner;
    }
    /**获取利率*/
    public BigDecimal getLoanInterestRates() {
        return loanInterestRates;
    }

    /**设置利率*/
    public void setLoanInterestRates(BigDecimal loanInterestRates) {
        this.loanInterestRates = loanInterestRates;
    }
    /**获取融资期限（天）*/
    public Integer getLoanTimeLimitDays() {
        return loanTimeLimitDays;
    }

    /**设置融资期限（天）*/
    public void setLoanTimeLimitDays(Integer loanTimeLimitDays) {
        this.loanTimeLimitDays = loanTimeLimitDays;
    }
    /**获取融资期限*/
    public Integer getLoanTimeLimit() {
        return loanTimeLimit;
    }

    /**设置融资期限*/
    public void setLoanTimeLimit(Integer loanTimeLimit) {
        this.loanTimeLimit = loanTimeLimit;
    }
    /**获取融资期限单位*/
    public String getLoanTimeLimitUnit() {
        return loanTimeLimitUnit;
    }

    /**设置融资期限单位*/
    public void setLoanTimeLimitUnit(String loanTimeLimitUnit) {
        this.loanTimeLimitUnit = loanTimeLimitUnit;
    }
    /**获取起投份数*/
    public Integer getLoanStartShare() {
        return loanStartShare;
    }

    /**设置起投份数*/
    public void setLoanStartShare(Integer loanStartShare) {
        this.loanStartShare = loanStartShare;
    }
    /**获取每份单价*/
    public Integer getLoanUnitPrice() {
        return loanUnitPrice;
    }

    /**设置每份单价*/
    public void setLoanUnitPrice(Integer loanUnitPrice) {
        this.loanUnitPrice = loanUnitPrice;
    }
    /**获取总份数*/
    public Integer getLoanTotalShare() {
        return loanTotalShare;
    }

    /**设置总份数*/
    public void setLoanTotalShare(Integer loanTotalShare) {
        this.loanTotalShare = loanTotalShare;
    }
    /**获取融资发布时间*/
    public Date getLoanPostTime() {
        return loanPostTime;
    }

    /**设置融资发布时间*/
    public void setLoanPostTime(Date loanPostTime) {
        this.loanPostTime = loanPostTime;
    }
    /**获取投资截止时间*/
    public Date getLoanEndTime() {
        return loanEndTime;
    }

    /**设置投资截止时间*/
    public void setLoanEndTime(Date loanEndTime) {
        this.loanEndTime = loanEndTime;
    }
    /**获取预期利息*/
    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    /**设置预期利息*/
    public void setLoanInterest(BigDecimal loanInterest) {
        this.loanInterest = loanInterest;
    }
    /**获取单用户最大投资限额*/
    public Integer getLoanMaxInvest() {
        return loanMaxInvest;
    }

    /**设置单用户最大投资限额*/
    public void setLoanMaxInvest(Integer loanMaxInvest) {
        this.loanMaxInvest = loanMaxInvest;
    }
    /**获取交易流水号*/
    public String getSerialNumber() {
        return serialNumber;
    }

    /**设置交易流水号*/
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    /**获取平台管理费率*/
    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    /**设置平台管理费率*/
    public void setPlatformRate(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }
    /**获取平台管理费*/
    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    /**设置平台管理费*/
    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }
    /**获取平台费流水号*/
    public String getPlatSerialNumber() {
        return platSerialNumber;
    }

    /**设置平台费流水号*/
    public void setPlatSerialNumber(String platSerialNumber) {
        this.platSerialNumber = platSerialNumber;
    }
    /**获取担保费率*/
    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    /**设置担保费率*/
    public void setGuaranteeRate(BigDecimal guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }
    /**获取担保费*/
    public BigDecimal getGuaranteeFee() {
        return guaranteeFee;
    }

    /**设置担保费*/
    public void setGuaranteeFee(BigDecimal guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
    }
    /**获取担保费流水号*/
    public String getVouchSerialNumber() {
        return vouchSerialNumber;
    }

    /**设置担保费流水号*/
    public void setVouchSerialNumber(String vouchSerialNumber) {
        this.vouchSerialNumber = vouchSerialNumber;
    }
    /**获取备付金费率*/
    public BigDecimal getDepositRate() {
        return depositRate;
    }

    /**设置备付金费率*/
    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }
    /**获取已投份数*/
    public Integer getCurrentInvestedShare() {
        return currentInvestedShare;
    }

    /**设置已投份数*/
    public void setCurrentInvestedShare(Integer currentInvestedShare) {
        this.currentInvestedShare = currentInvestedShare;
    }
    /**获取剩余份数*/
    public Integer getCurrentSurplusShare() {
        return currentSurplusShare;
    }

    /**设置剩余份数*/
    public void setCurrentSurplusShare(Integer currentSurplusShare) {
        this.currentSurplusShare = currentSurplusShare;
    }
    /**获取实际融资额*/
    public Integer getActualAmount() {
        return actualAmount;
    }

    /**设置实际融资额*/
    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }
    /**获取放款时间*/
    public Date getLoanTime() {
        return loanTime;
    }

    /**设置放款时间*/
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }
    /**获取起息日期*/
    public Date getCarryInterestFrom() {
        return carryInterestFrom;
    }

    /**设置起息日期*/
    public void setCarryInterestFrom(Date carryInterestFrom) {
        this.carryInterestFrom = carryInterestFrom;
    }
    /**获取到期日期*/
    public Date getCarryInterestTo() {
        return carryInterestTo;
    }

    /**设置到期日期*/
    public void setCarryInterestTo(Date carryInterestTo) {
        this.carryInterestTo = carryInterestTo;
    }
    /**获取还款类型*/
    public String getRepayType() {
        return repayType;
    }

    /**设置还款类型*/
    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }
    /**获取当期还款期数*/
    public Integer getReceivableRepayNumber() {
        return receivableRepayNumber;
    }

    /**设置当期还款期数*/
    public void setReceivableRepayNumber(Integer receivableRepayNumber) {
        this.receivableRepayNumber = receivableRepayNumber;
    }
    /**获取下次还款日*/
    public Date getReceivableNextDate() {
        return receivableNextDate;
    }

    /**设置下次还款日*/
    public void setReceivableNextDate(Date receivableNextDate) {
        this.receivableNextDate = receivableNextDate;
    }
    /**获取应收总额*/
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }

    /**设置应收总额*/
    public void setReceivableSum(BigDecimal receivableSum) {
        this.receivableSum = receivableSum;
    }
    /**获取应收本金*/
    public BigDecimal getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**设置应收本金*/
    public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
        this.receivablePrincipal = receivablePrincipal;
    }
    /**获取应收利息*/
    public BigDecimal getReceivableInterest() {
        return receivableInterest;
    }

    /**设置应收利息*/
    public void setReceivableInterest(BigDecimal receivableInterest) {
        this.receivableInterest = receivableInterest;
    }
    /**获取应收滞纳金*/
    public BigDecimal getReceivableDelaying() {
        return receivableDelaying;
    }

    /**设置应收滞纳金*/
    public void setReceivableDelaying(BigDecimal receivableDelaying) {
        this.receivableDelaying = receivableDelaying;
    }
    /**获取应收逾期罚金*/
    public BigDecimal getReceivableOverdue() {
        return receivableOverdue;
    }

    /**设置应收逾期罚金*/
    public void setReceivableOverdue(BigDecimal receivableOverdue) {
        this.receivableOverdue = receivableOverdue;
    }
    /**获取已收总额*/
    public BigDecimal getReceivedSum() {
        return receivedSum;
    }

    /**设置已收总额*/
    public void setReceivedSum(BigDecimal receivedSum) {
        this.receivedSum = receivedSum;
    }
    /**获取已收本金*/
    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**设置已收本金*/
    public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
        this.receivedPrincipal = receivedPrincipal;
    }
    /**获取已收利息*/
    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    /**设置已收利息*/
    public void setReceivedInterest(BigDecimal receivedInterest) {
        this.receivedInterest = receivedInterest;
    }
    /**获取已收滞纳金*/
    public BigDecimal getReceivedDelaying() {
        return receivedDelaying;
    }

    /**设置已收滞纳金*/
    public void setReceivedDelaying(BigDecimal receivedDelaying) {
        this.receivedDelaying = receivedDelaying;
    }
    /**获取已收逾期罚金*/
    public BigDecimal getReceivedOverdue() {
        return receivedOverdue;
    }

    /**设置已收逾期罚金*/
    public void setReceivedOverdue(BigDecimal receivedOverdue) {
        this.receivedOverdue = receivedOverdue;
    }
    
    /**获取融资信息Json*/
    public String getLoanMsg() {
        return loanMsg;
    }

    /**设置融资信息Json*/
    public void setLoanMsg(String loanMsg) {
        this.loanMsg = loanMsg;
    }
    /**获取项目说明书*/
    public String getInstructions() {
        return instructions;
    }

    /**设置项目说明书*/
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
