/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RepayDetailCriteria.java
 * Description:       查询条件RepayDetailCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.util.Date;
import java.math.BigDecimal;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "REPAY_DETAIL")
public class RepayDetailCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**融资还款详细编号*/
    @Column(name="REPAY_DETAIL_CODE")
    private String repayDetailCode;

    /**还款期号*/
    @Column(name="NUM")
    private Integer num;

    /**计划收款日*/
    @Column(name="REPAY_PLAN_DATE")
    private Date repayPlanDate;

    /**实际收款日*/
    @Column(name="REPAY_REAL_DATE")
    private Date repayRealDate;

    /**已收利息*/
    @Column(name="RECEIVED_INTEREST")
    private BigDecimal receivedInterest;

    /**已收本金*/
    @Column(name="RECEIVED_PRINCIPAL")
    private BigDecimal receivedPrincipal;

    /**已收总金额*/
    @Column(name="RECEIVED_SUM")
    private BigDecimal receivedSum;

    /**应收利息*/
    @Column(name="RECEIVABLE_INTEREST")
    private BigDecimal receivableInterest;

    /**应收本金*/
    @Column(name="RECEIVABLE_PRINCIPAL")
    private BigDecimal receivablePrincipal;

    /**应收总金额*/
    @Column(name="RECEIVABLE_SUM")
    private BigDecimal receivableSum;

    /**逾期天数*/
    @Column(name="OVERDUE_DAYS")
    private Integer overdueDays;

    /**逾期滞纳金*/
    @Column(name="PUNITIVE_DELAY_PAYMENT")
    private BigDecimal punitiveDelayPayment;

    /**逾期罚息*/
    @Column(name="PUNITIVE_INTEREST")
    private BigDecimal punitiveInterest;

    /**还款状态*/
    @Column(name="STATUS")
    private String status;

    /**还款账号*/
    @Column(name="ACCOUNT_NO")
    private String accountNo;

    /**还款用户类别*/
    @Column(name="ACCOUNT_TYPE")
    private String accountType;

    /**融资编号*/
    @Column(name="LOAN_CODE")
    private String loanCode;

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

         
    public RepayDetailCriteria() {

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
    /**获取融资还款详细编号*/
    public String getRepayDetailCode() {
        return repayDetailCode;
    }

    /**设置融资还款详细编号*/
    public void setRepayDetailCode(String repayDetailCode, Operator ... oper) {
        this.repayDetailCode = repayDetailCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("repayDetailCode", param);
            }
        }
    }
    /**获取还款期号*/
    public Integer getNum() {
        return num;
    }

    /**设置还款期号*/
    public void setNum(Integer num, Operator ... oper) {
        this.num = num;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("num", param);
            }
        }
    }
    /**获取计划收款日*/
    public Date getRepayPlanDate() {
        return repayPlanDate;
    }

    /**设置计划收款日*/
    public void setRepayPlanDate(Date repayPlanDate, Operator ... oper) {
        this.repayPlanDate = repayPlanDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("repayPlanDate", param);
            }
        }
    }
    /**获取实际收款日*/
    public Date getRepayRealDate() {
        return repayRealDate;
    }

    /**设置实际收款日*/
    public void setRepayRealDate(Date repayRealDate, Operator ... oper) {
        this.repayRealDate = repayRealDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("repayRealDate", param);
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
    /**获取已收总金额*/
    public BigDecimal getReceivedSum() {
        return receivedSum;
    }

    /**设置已收总金额*/
    public void setReceivedSum(BigDecimal receivedSum, Operator ... oper) {
        this.receivedSum = receivedSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivedSum", param);
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
    /**获取应收总金额*/
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }

    /**设置应收总金额*/
    public void setReceivableSum(BigDecimal receivableSum, Operator ... oper) {
        this.receivableSum = receivableSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receivableSum", param);
            }
        }
    }
    /**获取逾期天数*/
    public Integer getOverdueDays() {
        return overdueDays;
    }

    /**设置逾期天数*/
    public void setOverdueDays(Integer overdueDays, Operator ... oper) {
        this.overdueDays = overdueDays;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("overdueDays", param);
            }
        }
    }
    /**获取逾期滞纳金*/
    public BigDecimal getPunitiveDelayPayment() {
        return punitiveDelayPayment;
    }

    /**设置逾期滞纳金*/
    public void setPunitiveDelayPayment(BigDecimal punitiveDelayPayment, Operator ... oper) {
        this.punitiveDelayPayment = punitiveDelayPayment;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("punitiveDelayPayment", param);
            }
        }
    }
    /**获取逾期罚息*/
    public BigDecimal getPunitiveInterest() {
        return punitiveInterest;
    }

    /**设置逾期罚息*/
    public void setPunitiveInterest(BigDecimal punitiveInterest, Operator ... oper) {
        this.punitiveInterest = punitiveInterest;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("punitiveInterest", param);
            }
        }
    }
    /**获取还款状态*/
    public String getStatus() {
        return status;
    }

    /**设置还款状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
            }
        }
    }
    /**获取还款账号*/
    public String getAccountNo() {
        return accountNo;
    }

    /**设置还款账号*/
    public void setAccountNo(String accountNo, Operator ... oper) {
        this.accountNo = accountNo;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("accountNo", param);
            }
        }
    }
    /**获取还款用户类别*/
    public String getAccountType() {
        return accountType;
    }

    /**设置还款用户类别*/
    public void setAccountType(String accountType, Operator ... oper) {
        this.accountType = accountType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("accountType", param);
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
        repayDetailCode("REPAY_DETAIL_CODE"),
        num("NUM"),
        repayPlanDate("REPAY_PLAN_DATE"),
        repayRealDate("REPAY_REAL_DATE"),
        receivedInterest("RECEIVED_INTEREST"),
        receivedPrincipal("RECEIVED_PRINCIPAL"),
        receivedSum("RECEIVED_SUM"),
        receivableInterest("RECEIVABLE_INTEREST"),
        receivablePrincipal("RECEIVABLE_PRINCIPAL"),
        receivableSum("RECEIVABLE_SUM"),
        overdueDays("OVERDUE_DAYS"),
        punitiveDelayPayment("PUNITIVE_DELAY_PAYMENT"),
        punitiveInterest("PUNITIVE_INTEREST"),
        status("STATUS"),
        accountNo("ACCOUNT_NO"),
        accountType("ACCOUNT_TYPE"),
        loanCode("LOAN_CODE"),
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
        return "RepayDetail ["
        + ", id=" + id
        + ", repayDetailCode=" + repayDetailCode
        + ", num=" + num
        + ", repayPlanDate=" + repayPlanDate
        + ", repayRealDate=" + repayRealDate
        + ", receivedInterest=" + receivedInterest
        + ", receivedPrincipal=" + receivedPrincipal
        + ", receivedSum=" + receivedSum
        + ", receivableInterest=" + receivableInterest
        + ", receivablePrincipal=" + receivablePrincipal
        + ", receivableSum=" + receivableSum
        + ", overdueDays=" + overdueDays
        + ", punitiveDelayPayment=" + punitiveDelayPayment
        + ", punitiveInterest=" + punitiveInterest
        + ", status=" + status
        + ", accountNo=" + accountNo
        + ", accountType=" + accountType
        + ", loanCode=" + loanCode
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
