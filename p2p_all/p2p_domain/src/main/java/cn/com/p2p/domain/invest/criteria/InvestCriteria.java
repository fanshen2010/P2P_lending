/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestCriteria.java
 * Description:       查询条件InvestCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.criteria;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "INVEST")
public class InvestCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**投资编号*/
    @Column(name="INVEST_CODE")
    private String investCode;

    /**投资项目名*/
    @Column(name="LOAN_NAME")
    private String loanName;

    /**投资金额*/
    @Column(name="INVESTMENT_AMOUNT")
    private BigDecimal investmentAmount;

    /**投资期限*/
    @Column(name="INVEST_LIMIT")
    private Integer investLimit;

    /**投资期限单位*/
    @Column(name="INVEST_TIME_LIMIT_UNIT")
    private String investTimeLimitUnit;

    /**投资日*/
    @Column(name="INVESTMENT_TIME")
    private Date investmentTime;

    /**起息日*/
    @Column(name="INTEREST_DATE")
    private Date interestDate;

    /**结清日*/
    @Column(name="END_DATE")
    private Date endDate;

    /**投资收益率*/
    @Column(name="INVEST_INTERST_RATE")
    private BigDecimal investInterstRate;

    /**预期收益*/
    @Column(name="INVEST_INTERST")
    private BigDecimal investInterst;

    /**已收本金*/
    @Column(name="RECEIVED_PRINCIPAL")
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    @Column(name="RECEIVED_INTEREST")
    private BigDecimal receivedInterest;

    /**应收本金*/
    @Column(name="RECEIVABLE_PRINCIPAL")
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    @Column(name="RECEIVABLE_INTEREST")
    private BigDecimal receivableInterest;

    /**已收罚息*/
    @Column(name="RECEIVED_PUNITIVE")
    private BigDecimal receivedPunitive;

    /**待收罚息*/
    @Column(name="RECEIVABLE_PUNITIVE")
    private BigDecimal receivablePunitive;

    /**还款起息日*/
    @Column(name="PAYMENT_START_DATE")
    private Date paymentStartDate;

    /**下期还款日*/
    @Column(name="NEXT_PAYMENT_DATE")
    private Date nextPaymentDate;

    /**下期还款金额*/
    @Column(name="NEXT_PAYMENT_AMOUNT")
    private BigDecimal nextPaymentAmount;

    /**下期还款期号*/
    @Column(name="NEXT_PAYMENT_NO")
    private String nextPaymentNo;

    /**还款总期数*/
    @Column(name="PAYMENT_TOTAL_NUM")
    private Integer paymentTotalNum;

    /**投资状态*/
    @Column(name="STATUS")
    private String status;

    /**投资类型*/
    @Column(name="INVEST_TYPE")
    private String investType;

    /**结清方式*/
    @Column(name="SETTLEMENT_TYPE")
    private String settlementType;

    /**中金交易记录json*/
    @Column(name="CICC_TRADING_JSON")
    private String ciccTradingJson;

    /**投资客户名称*/
    @Column(name="INVEST_USER_NAME")
    private String investUserName;

    /**投资用户ID*/
    @Column(name="USER_ID")
    private String userId;

    /**融资编号（code）*/
    @Column(name="LOAN_CODE")
    private String loanCode;

    /**债权编号（code）*/
    @Column(name="DEBT_CODE")
    private String debtCode;

    /**总收益*/
    @Column(name="INVEST_EARNINGS")
    private BigDecimal investEarnings;

    /**投资客户回款账户*/
    @Column(name="RECEIVED_ACCOUNT")
    private String receivedAccount;

    /**投资客户回款账户名*/
    @Column(name="RECEIVED_ACCOUNT_NAME")
    private String receivedAccountName;

    /**交易流水号*/
    @Column(name="SERIAL_NUMBER")
    private String serialNumber;

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

         
    public InvestCriteria() {

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
    /**获取投资编号*/
    public String getInvestCode() {
        return investCode;
    }

    /**设置投资编号*/
    public void setInvestCode(String investCode, Operator ... oper) {
        this.investCode = investCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investCode", param);
            }
        }
    }
    /**获取投资项目名*/
    public String getLoanName() {
        return loanName;
    }

    /**设置投资项目名*/
    public void setLoanName(String loanName, Operator ... oper) {
        this.loanName = loanName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanName", param);
            }
        }
    }
    /**获取投资金额*/
    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    /**设置投资金额*/
    public void setInvestmentAmount(BigDecimal investmentAmount, Operator ... oper) {
        this.investmentAmount = investmentAmount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investmentAmount", param);
            }
        }
    }
    /**获取投资期限*/
    public Integer getInvestLimit() {
        return investLimit;
    }

    /**设置投资期限*/
    public void setInvestLimit(Integer investLimit, Operator ... oper) {
        this.investLimit = investLimit;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investLimit", param);
            }
        }
    }
    /**获取投资期限单位*/
    public String getInvestTimeLimitUnit() {
        return investTimeLimitUnit;
    }

    /**设置投资期限单位*/
    public void setInvestTimeLimitUnit(String investTimeLimitUnit, Operator ... oper) {
        this.investTimeLimitUnit = investTimeLimitUnit;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investTimeLimitUnit", param);
            }
        }
    }
    /**获取投资日*/
    public Date getInvestmentTime() {
        return investmentTime;
    }

    /**设置投资日*/
    public void setInvestmentTime(Date investmentTime, Operator ... oper) {
        this.investmentTime = investmentTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investmentTime", param);
            }
        }
    }
    /**获取起息日*/
    public Date getInterestDate() {
        return interestDate;
    }

    /**设置起息日*/
    public void setInterestDate(Date interestDate, Operator ... oper) {
        this.interestDate = interestDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("interestDate", param);
            }
        }
    }
    /**获取结清日*/
    public Date getEndDate() {
        return endDate;
    }

    /**设置结清日*/
    public void setEndDate(Date endDate, Operator ... oper) {
        this.endDate = endDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("endDate", param);
            }
        }
    }
    /**获取投资收益率*/
    public BigDecimal getInvestInterstRate() {
        return investInterstRate;
    }

    /**设置投资收益率*/
    public void setInvestInterstRate(BigDecimal investInterstRate, Operator ... oper) {
        this.investInterstRate = investInterstRate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investInterstRate", param);
            }
        }
    }
    /**获取预期收益*/
    public BigDecimal getInvestInterst() {
        return investInterst;
    }

    /**设置预期收益*/
    public void setInvestInterst(BigDecimal investInterst, Operator ... oper) {
        this.investInterst = investInterst;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investInterst", param);
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
    /**获取已收罚息*/
    public BigDecimal getReceivedPunitive() {
        return receivedPunitive;
    }

    /**设置已收罚息*/
    public void setReceivedPunitive(BigDecimal receivedPunitive, Operator ... oper) {
        this.receivedPunitive = receivedPunitive;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedPunitive", param);
            }
        }
    }
    /**获取待收罚息*/
    public BigDecimal getReceivablePunitive() {
        return receivablePunitive;
    }

    /**设置待收罚息*/
    public void setReceivablePunitive(BigDecimal receivablePunitive, Operator ... oper) {
        this.receivablePunitive = receivablePunitive;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivablePunitive", param);
            }
        }
    }
    /**获取还款起息日*/
    public Date getPaymentStartDate() {
        return paymentStartDate;
    }

    /**设置还款起息日*/
    public void setPaymentStartDate(Date paymentStartDate, Operator ... oper) {
        this.paymentStartDate = paymentStartDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentStartDate", param);
            }
        }
    }
    /**获取下期还款日*/
    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**设置下期还款日*/
    public void setNextPaymentDate(Date nextPaymentDate, Operator ... oper) {
        this.nextPaymentDate = nextPaymentDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("nextPaymentDate", param);
            }
        }
    }
    /**获取下期还款金额*/
    public BigDecimal getNextPaymentAmount() {
        return nextPaymentAmount;
    }

    /**设置下期还款金额*/
    public void setNextPaymentAmount(BigDecimal nextPaymentAmount, Operator ... oper) {
        this.nextPaymentAmount = nextPaymentAmount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("nextPaymentAmount", param);
            }
        }
    }
    /**获取下期还款期号*/
    public String getNextPaymentNo() {
        return nextPaymentNo;
    }

    /**设置下期还款期号*/
    public void setNextPaymentNo(String nextPaymentNo, Operator ... oper) {
        this.nextPaymentNo = nextPaymentNo;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("nextPaymentNo", param);
            }
        }
    }
    /**获取还款总期数*/
    public Integer getPaymentTotalNum() {
        return paymentTotalNum;
    }

    /**设置还款总期数*/
    public void setPaymentTotalNum(Integer paymentTotalNum, Operator ... oper) {
        this.paymentTotalNum = paymentTotalNum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentTotalNum", param);
            }
        }
    }
    /**获取投资状态*/
    public String getStatus() {
        return status;
    }

    /**设置投资状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
            }
        }
    }
    /**获取投资类型*/
    public String getInvestType() {
        return investType;
    }

    /**设置投资类型*/
    public void setInvestType(String investType, Operator ... oper) {
        this.investType = investType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investType", param);
            }
        }
    }
    /**获取结清方式*/
    public String getSettlementType() {
        return settlementType;
    }

    /**设置结清方式*/
    public void setSettlementType(String settlementType, Operator ... oper) {
        this.settlementType = settlementType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("settlementType", param);
            }
        }
    }
    /**获取中金交易记录json*/
    public String getCiccTradingJson() {
        return ciccTradingJson;
    }

    /**设置中金交易记录json*/
    public void setCiccTradingJson(String ciccTradingJson, Operator ... oper) {
        this.ciccTradingJson = ciccTradingJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("ciccTradingJson", param);
            }
        }
    }
    /**获取投资客户名称*/
    public String getInvestUserName() {
        return investUserName;
    }

    /**设置投资客户名称*/
    public void setInvestUserName(String investUserName, Operator ... oper) {
        this.investUserName = investUserName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investUserName", param);
            }
        }
    }
    /**获取投资用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置投资用户ID*/
    public void setUserId(String userId, Operator ... oper) {
        this.userId = userId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userId", param);
            }
        }
    }
    /**获取融资编号（code）*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号（code）*/
    public void setLoanCode(String loanCode, Operator ... oper) {
        this.loanCode = loanCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanCode", param);
            }
        }
    }
    /**获取债权编号（code）*/
    public String getDebtCode() {
        return debtCode;
    }

    /**设置债权编号（code）*/
    public void setDebtCode(String debtCode, Operator ... oper) {
        this.debtCode = debtCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("debtCode", param);
            }
        }
    }
    /**获取总收益*/
    public BigDecimal getInvestEarnings() {
        return investEarnings;
    }

    /**设置总收益*/
    public void setInvestEarnings(BigDecimal investEarnings, Operator ... oper) {
        this.investEarnings = investEarnings;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investEarnings", param);
            }
        }
    }
    /**获取投资客户回款账户*/
    public String getReceivedAccount() {
        return receivedAccount;
    }

    /**设置投资客户回款账户*/
    public void setReceivedAccount(String receivedAccount, Operator ... oper) {
        this.receivedAccount = receivedAccount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedAccount", param);
            }
        }
    }
    /**获取投资客户回款账户名*/
    public String getReceivedAccountName() {
        return receivedAccountName;
    }

    /**设置投资客户回款账户名*/
    public void setReceivedAccountName(String receivedAccountName, Operator ... oper) {
        this.receivedAccountName = receivedAccountName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedAccountName", param);
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
        investCode("INVEST_CODE"),
        loanName("LOAN_NAME"),
        investmentAmount("INVESTMENT_AMOUNT"),
        investLimit("INVEST_LIMIT"),
        investTimeLimitUnit("INVEST_TIME_LIMIT_UNIT"),
        investmentTime("INVESTMENT_TIME"),
        interestDate("INTEREST_DATE"),
        endDate("END_DATE"),
        investInterstRate("INVEST_INTERST_RATE"),
        investInterst("INVEST_INTERST"),
        receivedPrincipal("RECEIVED_PRINCIPAL"),
        receivedInterest("RECEIVED_INTEREST"),
        receivablePrincipal("RECEIVABLE_PRINCIPAL"),
        receivableInterest("RECEIVABLE_INTEREST"),
        receivedPunitive("RECEIVED_PUNITIVE"),
        receivablePunitive("RECEIVABLE_PUNITIVE"),
        paymentStartDate("PAYMENT_START_DATE"),
        nextPaymentDate("NEXT_PAYMENT_DATE"),
        nextPaymentAmount("NEXT_PAYMENT_AMOUNT"),
        nextPaymentNo("NEXT_PAYMENT_NO"),
        paymentTotalNum("PAYMENT_TOTAL_NUM"),
        status("STATUS"),
        investType("INVEST_TYPE"),
        settlementType("SETTLEMENT_TYPE"),
        ciccTradingJson("CICC_TRADING_JSON"),
        investUserName("INVEST_USER_NAME"),
        userId("USER_ID"),
        loanCode("LOAN_CODE"),
        debtCode("DEBT_CODE"),
        investEarnings("INVEST_EARNINGS"),
        receivedAccount("RECEIVED_ACCOUNT"),
        receivedAccountName("RECEIVED_ACCOUNT_NAME"),
        serialNumber("SERIAL_NUMBER"),
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
        return "Invest ["
        + ", id=" + id
        + ", investCode=" + investCode
        + ", loanName=" + loanName
        + ", investmentAmount=" + investmentAmount
        + ", investLimit=" + investLimit
        + ", investTimeLimitUnit=" + investTimeLimitUnit
        + ", investmentTime=" + investmentTime
        + ", interestDate=" + interestDate
        + ", endDate=" + endDate
        + ", investInterstRate=" + investInterstRate
        + ", investInterst=" + investInterst
        + ", receivedPrincipal=" + receivedPrincipal
        + ", receivedInterest=" + receivedInterest
        + ", receivablePrincipal=" + receivablePrincipal
        + ", receivableInterest=" + receivableInterest
        + ", receivedPunitive=" + receivedPunitive
        + ", receivablePunitive=" + receivablePunitive
        + ", paymentStartDate=" + paymentStartDate
        + ", nextPaymentDate=" + nextPaymentDate
        + ", nextPaymentAmount=" + nextPaymentAmount
        + ", nextPaymentNo=" + nextPaymentNo
        + ", paymentTotalNum=" + paymentTotalNum
        + ", status=" + status
        + ", investType=" + investType
        + ", settlementType=" + settlementType
        + ", ciccTradingJson=" + ciccTradingJson
        + ", investUserName=" + investUserName
        + ", userId=" + userId
        + ", loanCode=" + loanCode
        + ", debtCode=" + debtCode
        + ", investEarnings=" + investEarnings
        + ", receivedAccount=" + receivedAccount
        + ", receivedAccountName=" + receivedAccountName
        + ", serialNumber=" + serialNumber
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
