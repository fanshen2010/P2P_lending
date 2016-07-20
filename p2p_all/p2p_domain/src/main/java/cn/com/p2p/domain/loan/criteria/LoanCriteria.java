/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanCriteria.java
 * Description:       查询条件LoanCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-05             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "LOAN")
public class LoanCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**融资编号*/
    @Column(name="LOAN_CODE")
    private String loanCode;

    /**项目名称*/
    @Column(name="LOAN_NAME")
    private String loanName;

    /**产品编号*/
    @Column(name="PRODUCT_CODE")
    private String productCode;

    /**客户编号*/
    @Column(name="CUSTOM_CODE")
    private String customCode;

    /**客户名称*/
    @Column(name="CUSTOM_NAME")
    private String customName;

    /**担保公司编号*/
    @Column(name="GUARANTEE_COM_CODE")
    private String guaranteeComCode;

    /**融资状态*/
    @Column(name="LOAN_STATUS")
    private String loanStatus;

    /**融资类型*/
    @Column(name="LOAN_TYPE")
    private String loanType;

    /**融资金额*/
    @Column(name="LOAN_AMOUNT")
    private Integer loanAmount;

    /**计息方式*/
    @Column(name="LOAN_INTEREST_MANNER")
    private String loanInterestManner;

    /**利率*/
    @Column(name="LOAN_INTEREST_RATES")
    private BigDecimal loanInterestRates;

    /**融资期限（天）*/
    @Column(name="LOAN_TIME_LIMIT_DAYS")
    private Integer loanTimeLimitDays;

    /**融资期限*/
    @Column(name="LOAN_TIME_LIMIT")
    private Integer loanTimeLimit;

    /**融资期限单位*/
    @Column(name="LOAN_TIME_LIMIT_UNIT")
    private String loanTimeLimitUnit;

    /**起投份数*/
    @Column(name="LOAN_START_SHARE")
    private Integer loanStartShare;

    /**每份单价*/
    @Column(name="LOAN_UNIT_PRICE")
    private Integer loanUnitPrice;

    /**总份数*/
    @Column(name="LOAN_TOTAL_SHARE")
    private Integer loanTotalShare;

    /**融资发布时间*/
    @Column(name="LOAN_POST_TIME")
    private Date loanPostTime;

    /**投资截止时间*/
    @Column(name="LOAN_END_TIME")
    private Date loanEndTime;

    /**预期利息*/
    @Column(name="LOAN_INTEREST")
    private BigDecimal loanInterest;

    /**单用户最大投资限额*/
    @Column(name="LOAN_MAX_INVEST")
    private Integer loanMaxInvest;

    /**交易流水号*/
    @Column(name="SERIAL_NUMBER")
    private String serialNumber;

    /**平台管理费率*/
    @Column(name="PLATFORM_RATE")
    private BigDecimal platformRate;

    /**平台管理费*/
    @Column(name="PLATFORM_FEE")
    private BigDecimal platformFee;

    /**平台费流水号*/
    @Column(name="PLAT_SERIAL_NUMBER")
    private String platSerialNumber;

    /**担保费率*/
    @Column(name="GUARANTEE_RATE")
    private BigDecimal guaranteeRate;

    /**担保费*/
    @Column(name="GUARANTEE_FEE")
    private BigDecimal guaranteeFee;

    /**担保费流水号*/
    @Column(name="VOUCH_SERIAL_NUMBER")
    private String vouchSerialNumber;

    /**备付金费率*/
    @Column(name="DEPOSIT_RATE")
    private BigDecimal depositRate;

    /**已投份数*/
    @Column(name="CURRENT_INVESTED_SHARE")
    private Integer currentInvestedShare;

    /**剩余份数*/
    @Column(name="CURRENT_SURPLUS_SHARE")
    private Integer currentSurplusShare;

    /**实际融资额*/
    @Column(name="ACTUAL_AMOUNT")
    private Integer actualAmount;

    /**实际放款总额*/
    @Column(name="AMOUNT")
    private BigDecimal amount;

    /**放款时间*/
    @Column(name="LOAN_TIME")
    private Date loanTime;

    /**起息日期*/
    @Column(name="CARRY_INTEREST_FROM")
    private Date carryInterestFrom;

    /**到期日期*/
    @Column(name="CARRY_INTEREST_TO")
    private Date carryInterestTo;

    /**还款类型*/
    @Column(name="REPAY_TYPE")
    private String repayType;

    /**还款总期数*/
    @Column(name="TOTAL_REPAY_NUMBER")
    private Integer totalRepayNumber;

    /**当期还款期数*/
    @Column(name="RECEIVABLE_REPAY_NUMBER")
    private Integer receivableRepayNumber;

    /**当期还款状态*/
    @Column(name="RECEIVABLE_REPAY_STATUS")
    private String receivableRepayStatus;

    /**下次还款日*/
    @Column(name="RECEIVABLE_NEXT_DATE")
    private Date receivableNextDate;

    /**下次还款金额*/
    @Column(name="RECEIVABLE_NEXT_SUM")
    private BigDecimal receivableNextSum;

    /**应收总额*/
    @Column(name="RECEIVABLE_SUM")
    private BigDecimal receivableSum;

    /**应收本金*/
    @Column(name="RECEIVABLE_PRINCIPAL")
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    @Column(name="RECEIVABLE_INTEREST")
    private BigDecimal receivableInterest;

    /**应收滞纳金*/
    @Column(name="RECEIVABLE_DELAYING")
    private BigDecimal receivableDelaying;

    /**应收逾期罚金*/
    @Column(name="RECEIVABLE_OVERDUE")
    private BigDecimal receivableOverdue;

    /**已收总额*/
    @Column(name="RECEIVED_SUM")
    private BigDecimal receivedSum;

    /**已收本金*/
    @Column(name="RECEIVED_PRINCIPAL")
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    @Column(name="RECEIVED_INTEREST")
    private BigDecimal receivedInterest;

    /**已收滞纳金*/
    @Column(name="RECEIVED_DELAYING")
    private BigDecimal receivedDelaying;

    /**已收逾期罚金*/
    @Column(name="RECEIVED_OVERDUE")
    private BigDecimal receivedOverdue;

    /**申请者*/
    @Column(name="APPLY_USER_ID")
    private String applyUserId;

    /**商户ID*/
    @Column(name="TENANT_ID")
    private String tenantId;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    /**创建者*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

    /**更新者*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;

    /**版本*/
    @Column(name="VERSION")
    private Integer version;

         
    public LoanCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode, Operator ... oper) {
        this.loanCode = loanCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanCode", param);
            }
        }
    }
    /**获取项目名称*/
    public String getLoanName() {
        return loanName;
    }

    /**设置项目名称*/
    public void setLoanName(String loanName, Operator ... oper) {
        this.loanName = loanName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanName", param);
            }
        }
    }
    /**获取产品编号*/
    public String getProductCode() {
        return productCode;
    }

    /**设置产品编号*/
    public void setProductCode(String productCode, Operator ... oper) {
        this.productCode = productCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("productCode", param);
            }
        }
    }
    /**获取客户编号*/
    public String getCustomCode() {
        return customCode;
    }

    /**设置客户编号*/
    public void setCustomCode(String customCode, Operator ... oper) {
        this.customCode = customCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("customCode", param);
            }
        }
    }
    /**获取客户名称*/
    public String getCustomName() {
        return customName;
    }

    /**设置客户名称*/
    public void setCustomName(String customName, Operator ... oper) {
        this.customName = customName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("customName", param);
            }
        }
    }
    /**获取担保公司编号*/
    public String getGuaranteeComCode() {
        return guaranteeComCode;
    }

    /**设置担保公司编号*/
    public void setGuaranteeComCode(String guaranteeComCode, Operator ... oper) {
        this.guaranteeComCode = guaranteeComCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("guaranteeComCode", param);
            }
        }
    }
    /**获取融资状态*/
    public String getLoanStatus() {
        return loanStatus;
    }

    /**设置融资状态*/
    public void setLoanStatus(String loanStatus, Operator ... oper) {
        this.loanStatus = loanStatus;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanStatus", param);
            }
        }
    }
    /**获取融资类型*/
    public String getLoanType() {
        return loanType;
    }

    /**设置融资类型*/
    public void setLoanType(String loanType, Operator ... oper) {
        this.loanType = loanType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanType", param);
            }
        }
    }
    /**获取融资金额*/
    public Integer getLoanAmount() {
        return loanAmount;
    }

    /**设置融资金额*/
    public void setLoanAmount(Integer loanAmount, Operator ... oper) {
        this.loanAmount = loanAmount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanAmount", param);
            }
        }
    }
    /**获取计息方式*/
    public String getLoanInterestManner() {
        return loanInterestManner;
    }

    /**设置计息方式*/
    public void setLoanInterestManner(String loanInterestManner, Operator ... oper) {
        this.loanInterestManner = loanInterestManner;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanInterestManner", param);
            }
        }
    }
    /**获取利率*/
    public BigDecimal getLoanInterestRates() {
        return loanInterestRates;
    }

    /**设置利率*/
    public void setLoanInterestRates(BigDecimal loanInterestRates, Operator ... oper) {
        this.loanInterestRates = loanInterestRates;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanInterestRates", param);
            }
        }
    }
    /**获取融资期限（天）*/
    public Integer getLoanTimeLimitDays() {
        return loanTimeLimitDays;
    }

    /**设置融资期限（天）*/
    public void setLoanTimeLimitDays(Integer loanTimeLimitDays, Operator ... oper) {
        this.loanTimeLimitDays = loanTimeLimitDays;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanTimeLimitDays", param);
            }
        }
    }
    /**获取融资期限*/
    public Integer getLoanTimeLimit() {
        return loanTimeLimit;
    }

    /**设置融资期限*/
    public void setLoanTimeLimit(Integer loanTimeLimit, Operator ... oper) {
        this.loanTimeLimit = loanTimeLimit;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanTimeLimit", param);
            }
        }
    }
    /**获取融资期限单位*/
    public String getLoanTimeLimitUnit() {
        return loanTimeLimitUnit;
    }

    /**设置融资期限单位*/
    public void setLoanTimeLimitUnit(String loanTimeLimitUnit, Operator ... oper) {
        this.loanTimeLimitUnit = loanTimeLimitUnit;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanTimeLimitUnit", param);
            }
        }
    }
    /**获取起投份数*/
    public Integer getLoanStartShare() {
        return loanStartShare;
    }

    /**设置起投份数*/
    public void setLoanStartShare(Integer loanStartShare, Operator ... oper) {
        this.loanStartShare = loanStartShare;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanStartShare", param);
            }
        }
    }
    /**获取每份单价*/
    public Integer getLoanUnitPrice() {
        return loanUnitPrice;
    }

    /**设置每份单价*/
    public void setLoanUnitPrice(Integer loanUnitPrice, Operator ... oper) {
        this.loanUnitPrice = loanUnitPrice;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanUnitPrice", param);
            }
        }
    }
    /**获取总份数*/
    public Integer getLoanTotalShare() {
        return loanTotalShare;
    }

    /**设置总份数*/
    public void setLoanTotalShare(Integer loanTotalShare, Operator ... oper) {
        this.loanTotalShare = loanTotalShare;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanTotalShare", param);
            }
        }
    }
    /**获取融资发布时间*/
    public Date getLoanPostTime() {
        return loanPostTime;
    }

    /**设置融资发布时间*/
    public void setLoanPostTime(Date loanPostTime, Operator ... oper) {
        this.loanPostTime = loanPostTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanPostTime", param);
            }
        }
    }
    /**获取投资截止时间*/
    public Date getLoanEndTime() {
        return loanEndTime;
    }

    /**设置投资截止时间*/
    public void setLoanEndTime(Date loanEndTime, Operator ... oper) {
        this.loanEndTime = loanEndTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanEndTime", param);
            }
        }
    }
    /**获取预期利息*/
    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    /**设置预期利息*/
    public void setLoanInterest(BigDecimal loanInterest, Operator ... oper) {
        this.loanInterest = loanInterest;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanInterest", param);
            }
        }
    }
    /**获取单用户最大投资限额*/
    public Integer getLoanMaxInvest() {
        return loanMaxInvest;
    }

    /**设置单用户最大投资限额*/
    public void setLoanMaxInvest(Integer loanMaxInvest, Operator ... oper) {
        this.loanMaxInvest = loanMaxInvest;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanMaxInvest", param);
            }
        }
    }
    /**获取交易流水号*/
    public String getSerialNumber() {
        return serialNumber;
    }

    /**设置交易流水号*/
    public void setSerialNumber(String serialNumber, Operator ... oper) {
        this.serialNumber = serialNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("serialNumber", param);
            }
        }
    }
    /**获取平台管理费率*/
    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    /**设置平台管理费率*/
    public void setPlatformRate(BigDecimal platformRate, Operator ... oper) {
        this.platformRate = platformRate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("platformRate", param);
            }
        }
    }
    /**获取平台管理费*/
    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    /**设置平台管理费*/
    public void setPlatformFee(BigDecimal platformFee, Operator ... oper) {
        this.platformFee = platformFee;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("platformFee", param);
            }
        }
    }
    /**获取平台费流水号*/
    public String getPlatSerialNumber() {
        return platSerialNumber;
    }

    /**设置平台费流水号*/
    public void setPlatSerialNumber(String platSerialNumber, Operator ... oper) {
        this.platSerialNumber = platSerialNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("platSerialNumber", param);
            }
        }
    }
    /**获取担保费率*/
    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    /**设置担保费率*/
    public void setGuaranteeRate(BigDecimal guaranteeRate, Operator ... oper) {
        this.guaranteeRate = guaranteeRate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("guaranteeRate", param);
            }
        }
    }
    /**获取担保费*/
    public BigDecimal getGuaranteeFee() {
        return guaranteeFee;
    }

    /**设置担保费*/
    public void setGuaranteeFee(BigDecimal guaranteeFee, Operator ... oper) {
        this.guaranteeFee = guaranteeFee;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("guaranteeFee", param);
            }
        }
    }
    /**获取担保费流水号*/
    public String getVouchSerialNumber() {
        return vouchSerialNumber;
    }

    /**设置担保费流水号*/
    public void setVouchSerialNumber(String vouchSerialNumber, Operator ... oper) {
        this.vouchSerialNumber = vouchSerialNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("vouchSerialNumber", param);
            }
        }
    }
    /**获取备付金费率*/
    public BigDecimal getDepositRate() {
        return depositRate;
    }

    /**设置备付金费率*/
    public void setDepositRate(BigDecimal depositRate, Operator ... oper) {
        this.depositRate = depositRate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("depositRate", param);
            }
        }
    }
    /**获取已投份数*/
    public Integer getCurrentInvestedShare() {
        return currentInvestedShare;
    }

    /**设置已投份数*/
    public void setCurrentInvestedShare(Integer currentInvestedShare, Operator ... oper) {
        this.currentInvestedShare = currentInvestedShare;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentInvestedShare", param);
            }
        }
    }
    /**获取剩余份数*/
    public Integer getCurrentSurplusShare() {
        return currentSurplusShare;
    }

    /**设置剩余份数*/
    public void setCurrentSurplusShare(Integer currentSurplusShare, Operator ... oper) {
        this.currentSurplusShare = currentSurplusShare;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentSurplusShare", param);
            }
        }
    }
    /**获取实际融资额*/
    public Integer getActualAmount() {
        return actualAmount;
    }

    /**设置实际融资额*/
    public void setActualAmount(Integer actualAmount, Operator ... oper) {
        this.actualAmount = actualAmount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("actualAmount", param);
            }
        }
    }
    /**获取实际放款总额*/
    public BigDecimal getAmount() {
        return amount;
    }

    /**设置实际放款总额*/
    public void setAmount(BigDecimal amount, Operator ... oper) {
        this.amount = amount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("amount", param);
            }
        }
    }
    /**获取放款时间*/
    public Date getLoanTime() {
        return loanTime;
    }

    /**设置放款时间*/
    public void setLoanTime(Date loanTime, Operator ... oper) {
        this.loanTime = loanTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanTime", param);
            }
        }
    }
    /**获取起息日期*/
    public Date getCarryInterestFrom() {
        return carryInterestFrom;
    }

    /**设置起息日期*/
    public void setCarryInterestFrom(Date carryInterestFrom, Operator ... oper) {
        this.carryInterestFrom = carryInterestFrom;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("carryInterestFrom", param);
            }
        }
    }
    /**获取到期日期*/
    public Date getCarryInterestTo() {
        return carryInterestTo;
    }

    /**设置到期日期*/
    public void setCarryInterestTo(Date carryInterestTo, Operator ... oper) {
        this.carryInterestTo = carryInterestTo;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("carryInterestTo", param);
            }
        }
    }
    /**获取还款类型*/
    public String getRepayType() {
        return repayType;
    }

    /**设置还款类型*/
    public void setRepayType(String repayType, Operator ... oper) {
        this.repayType = repayType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("repayType", param);
            }
        }
    }
    /**获取还款总期数*/
    public Integer getTotalRepayNumber() {
        return totalRepayNumber;
    }

    /**设置还款总期数*/
    public void setTotalRepayNumber(Integer totalRepayNumber, Operator ... oper) {
        this.totalRepayNumber = totalRepayNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("totalRepayNumber", param);
            }
        }
    }
    /**获取当期还款期数*/
    public Integer getReceivableRepayNumber() {
        return receivableRepayNumber;
    }

    /**设置当期还款期数*/
    public void setReceivableRepayNumber(Integer receivableRepayNumber, Operator ... oper) {
        this.receivableRepayNumber = receivableRepayNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableRepayNumber", param);
            }
        }
    }
    /**获取当期还款状态*/
    public String getReceivableRepayStatus() {
        return receivableRepayStatus;
    }

    /**设置当期还款状态*/
    public void setReceivableRepayStatus(String receivableRepayStatus, Operator ... oper) {
        this.receivableRepayStatus = receivableRepayStatus;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableRepayStatus", param);
            }
        }
    }
    /**获取下次还款日*/
    public Date getReceivableNextDate() {
        return receivableNextDate;
    }

    /**设置下次还款日*/
    public void setReceivableNextDate(Date receivableNextDate, Operator ... oper) {
        this.receivableNextDate = receivableNextDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableNextDate", param);
            }
        }
    }
    /**获取下次还款金额*/
    public BigDecimal getReceivableNextSum() {
        return receivableNextSum;
    }

    /**设置下次还款金额*/
    public void setReceivableNextSum(BigDecimal receivableNextSum, Operator ... oper) {
        this.receivableNextSum = receivableNextSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableNextSum", param);
            }
        }
    }
    /**获取应收总额*/
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }

    /**设置应收总额*/
    public void setReceivableSum(BigDecimal receivableSum, Operator ... oper) {
        this.receivableSum = receivableSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableSum", param);
            }
        }
    }
    /**获取应收本金*/
    public BigDecimal getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**设置应收本金*/
    public void setReceivablePrincipal(BigDecimal receivablePrincipal, Operator ... oper) {
        this.receivablePrincipal = receivablePrincipal;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivablePrincipal", param);
            }
        }
    }
    /**获取应收利息*/
    public BigDecimal getReceivableInterest() {
        return receivableInterest;
    }

    /**设置应收利息*/
    public void setReceivableInterest(BigDecimal receivableInterest, Operator ... oper) {
        this.receivableInterest = receivableInterest;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableInterest", param);
            }
        }
    }
    /**获取应收滞纳金*/
    public BigDecimal getReceivableDelaying() {
        return receivableDelaying;
    }

    /**设置应收滞纳金*/
    public void setReceivableDelaying(BigDecimal receivableDelaying, Operator ... oper) {
        this.receivableDelaying = receivableDelaying;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableDelaying", param);
            }
        }
    }
    /**获取应收逾期罚金*/
    public BigDecimal getReceivableOverdue() {
        return receivableOverdue;
    }

    /**设置应收逾期罚金*/
    public void setReceivableOverdue(BigDecimal receivableOverdue, Operator ... oper) {
        this.receivableOverdue = receivableOverdue;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableOverdue", param);
            }
        }
    }
    /**获取已收总额*/
    public BigDecimal getReceivedSum() {
        return receivedSum;
    }

    /**设置已收总额*/
    public void setReceivedSum(BigDecimal receivedSum, Operator ... oper) {
        this.receivedSum = receivedSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedSum", param);
            }
        }
    }
    /**获取已收本金*/
    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**设置已收本金*/
    public void setReceivedPrincipal(BigDecimal receivedPrincipal, Operator ... oper) {
        this.receivedPrincipal = receivedPrincipal;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedPrincipal", param);
            }
        }
    }
    /**获取已收利息*/
    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    /**设置已收利息*/
    public void setReceivedInterest(BigDecimal receivedInterest, Operator ... oper) {
        this.receivedInterest = receivedInterest;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedInterest", param);
            }
        }
    }
    /**获取已收滞纳金*/
    public BigDecimal getReceivedDelaying() {
        return receivedDelaying;
    }

    /**设置已收滞纳金*/
    public void setReceivedDelaying(BigDecimal receivedDelaying, Operator ... oper) {
        this.receivedDelaying = receivedDelaying;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedDelaying", param);
            }
        }
    }
    /**获取已收逾期罚金*/
    public BigDecimal getReceivedOverdue() {
        return receivedOverdue;
    }

    /**设置已收逾期罚金*/
    public void setReceivedOverdue(BigDecimal receivedOverdue, Operator ... oper) {
        this.receivedOverdue = receivedOverdue;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedOverdue", param);
            }
        }
    }
    /**获取申请者*/
    public String getApplyUserId() {
        return applyUserId;
    }

    /**设置申请者*/
    public void setApplyUserId(String applyUserId, Operator ... oper) {
        this.applyUserId = applyUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("applyUserId", param);
            }
        }
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }

    /**设置商户ID*/
    public void setTenantId(String tenantId, Operator ... oper) {
        this.tenantId = tenantId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantId", param);
            }
        }
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime, Operator ... oper) {
        this.createTime = createTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createTime", param);
            }
        }
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime, Operator ... oper) {
        this.updateTime = updateTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateTime", param);
            }
        }
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId, Operator ... oper) {
        this.createUserId = createUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUserId", param);
            }
        }
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId, Operator ... oper) {
        this.updateUserId = updateUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateUserId", param);
            }
        }
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version, Operator ... oper) {
        this.version = version;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("version", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        loanCode("LOAN_CODE"),
        loanName("LOAN_NAME"),
        productCode("PRODUCT_CODE"),
        customCode("CUSTOM_CODE"),
        customName("CUSTOM_NAME"),
        guaranteeComCode("GUARANTEE_COM_CODE"),
        loanStatus("LOAN_STATUS"),
        loanType("LOAN_TYPE"),
        loanAmount("LOAN_AMOUNT"),
        loanInterestManner("LOAN_INTEREST_MANNER"),
        loanInterestRates("LOAN_INTEREST_RATES"),
        loanTimeLimitDays("LOAN_TIME_LIMIT_DAYS"),
        loanTimeLimit("LOAN_TIME_LIMIT"),
        loanTimeLimitUnit("LOAN_TIME_LIMIT_UNIT"),
        loanStartShare("LOAN_START_SHARE"),
        loanUnitPrice("LOAN_UNIT_PRICE"),
        loanTotalShare("LOAN_TOTAL_SHARE"),
        loanPostTime("LOAN_POST_TIME"),
        loanEndTime("LOAN_END_TIME"),
        loanInterest("LOAN_INTEREST"),
        loanMaxInvest("LOAN_MAX_INVEST"),
        serialNumber("SERIAL_NUMBER"),
        platformRate("PLATFORM_RATE"),
        platformFee("PLATFORM_FEE"),
        platSerialNumber("PLAT_SERIAL_NUMBER"),
        guaranteeRate("GUARANTEE_RATE"),
        guaranteeFee("GUARANTEE_FEE"),
        vouchSerialNumber("VOUCH_SERIAL_NUMBER"),
        depositRate("DEPOSIT_RATE"),
        currentInvestedShare("CURRENT_INVESTED_SHARE"),
        currentSurplusShare("CURRENT_SURPLUS_SHARE"),
        actualAmount("ACTUAL_AMOUNT"),
        amount("AMOUNT"),
        loanTime("LOAN_TIME"),
        carryInterestFrom("CARRY_INTEREST_FROM"),
        carryInterestTo("CARRY_INTEREST_TO"),
        repayType("REPAY_TYPE"),
        totalRepayNumber("TOTAL_REPAY_NUMBER"),
        receivableRepayNumber("RECEIVABLE_REPAY_NUMBER"),
        receivableRepayStatus("RECEIVABLE_REPAY_STATUS"),
        receivableNextDate("RECEIVABLE_NEXT_DATE"),
        receivableNextSum("RECEIVABLE_NEXT_SUM"),
        receivableSum("RECEIVABLE_SUM"),
        receivablePrincipal("RECEIVABLE_PRINCIPAL"),
        receivableInterest("RECEIVABLE_INTEREST"),
        receivableDelaying("RECEIVABLE_DELAYING"),
        receivableOverdue("RECEIVABLE_OVERDUE"),
        receivedSum("RECEIVED_SUM"),
        receivedPrincipal("RECEIVED_PRINCIPAL"),
        receivedInterest("RECEIVED_INTEREST"),
        receivedDelaying("RECEIVED_DELAYING"),
        receivedOverdue("RECEIVED_OVERDUE"),
        applyUserId("APPLY_USER_ID"),
        tenantId("TENANT_ID"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME"),
        createUserId("CREATE_USER_ID"),
        updateUserId("UPDATE_USER_ID"),
        version("VERSION");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
    }
    public String toString() {
        return "Loan ["
        + ", id=" + id
        + ", loanCode=" + loanCode
        + ", loanName=" + loanName
        + ", productCode=" + productCode
        + ", customCode=" + customCode
        + ", customName=" + customName
        + ", guaranteeComCode=" + guaranteeComCode
        + ", loanStatus=" + loanStatus
        + ", loanType=" + loanType
        + ", loanAmount=" + loanAmount
        + ", loanInterestManner=" + loanInterestManner
        + ", loanInterestRates=" + loanInterestRates
        + ", loanTimeLimitDays=" + loanTimeLimitDays
        + ", loanTimeLimit=" + loanTimeLimit
        + ", loanTimeLimitUnit=" + loanTimeLimitUnit
        + ", loanStartShare=" + loanStartShare
        + ", loanUnitPrice=" + loanUnitPrice
        + ", loanTotalShare=" + loanTotalShare
        + ", loanPostTime=" + loanPostTime
        + ", loanEndTime=" + loanEndTime
        + ", loanInterest=" + loanInterest
        + ", loanMaxInvest=" + loanMaxInvest
        + ", serialNumber=" + serialNumber
        + ", platformRate=" + platformRate
        + ", platformFee=" + platformFee
        + ", platSerialNumber=" + platSerialNumber
        + ", guaranteeRate=" + guaranteeRate
        + ", guaranteeFee=" + guaranteeFee
        + ", vouchSerialNumber=" + vouchSerialNumber
        + ", depositRate=" + depositRate
        + ", currentInvestedShare=" + currentInvestedShare
        + ", currentSurplusShare=" + currentSurplusShare
        + ", actualAmount=" + actualAmount
        + ", amount=" + amount
        + ", loanTime=" + loanTime
        + ", carryInterestFrom=" + carryInterestFrom
        + ", carryInterestTo=" + carryInterestTo
        + ", repayType=" + repayType
        + ", totalRepayNumber=" + totalRepayNumber
        + ", receivableRepayNumber=" + receivableRepayNumber
        + ", receivableRepayStatus=" + receivableRepayStatus
        + ", receivableNextDate=" + receivableNextDate
        + ", receivableNextSum=" + receivableNextSum
        + ", receivableSum=" + receivableSum
        + ", receivablePrincipal=" + receivablePrincipal
        + ", receivableInterest=" + receivableInterest
        + ", receivableDelaying=" + receivableDelaying
        + ", receivableOverdue=" + receivableOverdue
        + ", receivedSum=" + receivedSum
        + ", receivedPrincipal=" + receivedPrincipal
        + ", receivedInterest=" + receivedInterest
        + ", receivedDelaying=" + receivedDelaying
        + ", receivedOverdue=" + receivedOverdue
        + ", applyUserId=" + applyUserId
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
