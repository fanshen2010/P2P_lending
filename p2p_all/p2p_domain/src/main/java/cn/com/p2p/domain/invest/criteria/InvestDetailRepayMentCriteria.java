/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestDetailCriteria.java
 * Description:       查询条件InvestDetailCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-09-16             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.criteria;

import java.util.Date;
import java.math.BigDecimal;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "INVEST_DETAIL")
public class InvestDetailRepayMentCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**投资回款明细编号*/
    @Column(name="INVEST_DETAIL_CODE")
    private String investDetailCode;

    /**还款期号*/
    @Column(name="NUM")
    private String num;

    /**计划还款日*/
    @Column(name="RECIEVE_PLAN_DATE")
    private Date recievePlanDate;

    /**实际还款日*/
    @Column(name="RECIEVE_REAL_DATE")
    private Date recieveRealDate;

    /**已收本金*/
    @Column(name="RECEIVED_PRINCIPAL")
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    @Column(name="RECEIVED_INTEREST")
    private BigDecimal receivedInterest;

    /**已收总金额*/
    @Column(name="RECIEVED_SUM")
    private BigDecimal recievedSum;

    /**应收本金*/
    @Column(name="RECEIVABLE_PRINCIPAL")
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    @Column(name="RECEIVABLE_INTEREST")
    private BigDecimal receivableInterest;

    /**应收总金额*/
    @Column(name="RECEIVABLE_SUM")
    private BigDecimal receivableSum;

    /**逾期天数*/
    @Column(name="PUNITIVE_DAY")
    private BigDecimal punitiveDay;

    /**逾期罚息*/
    @Column(name="PUNITIVE_INTEREST")
    private BigDecimal punitiveInterest;

    /**还款状态*/
    @Column(name="STATUS")
    private String status;

    /**投资编号*/
    @Column(name="INVEST_CODE")
    private String investCode;

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
    @Column(name="CREATE_UAER_ID")
    private String createUaerId;

    /**更新者*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;

    /**版本*/
    @Column(name="VERSION")
    private Integer version;

         
    public InvestDetailRepayMentCriteria() {

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
    /**获取投资回款明细编号*/
    public String getInvestDetailCode() {
        return investDetailCode;
    }

    /**设置投资回款明细编号*/
    public void setInvestDetailCode(String investDetailCode, Operator ... oper) {
        this.investDetailCode = investDetailCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investDetailCode", param);
            }
        }
    }
    /**获取还款期号*/
    public String getNum() {
        return num;
    }

    /**设置还款期号*/
    public void setNum(String num, Operator ... oper) {
        this.num = num;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("num", param);
            }
        }
    }
    /**获取计划还款日*/
    public Date getRecievePlanDate() {
        return recievePlanDate;
    }

    /**设置计划还款日*/
    public void setRecievePlanDate(Date recievePlanDate, Operator ... oper) {
        this.recievePlanDate = recievePlanDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("recievePlanDate", param);
            }
        }
    }
    /**获取实际还款日*/
    public Date getRecieveRealDate() {
        return recieveRealDate;
    }

    /**设置实际还款日*/
    public void setRecieveRealDate(Date recieveRealDate, Operator ... oper) {
        this.recieveRealDate = recieveRealDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("recieveRealDate", param);
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
    /**获取已收总金额*/
    public BigDecimal getRecievedSum() {
        return recievedSum;
    }

    /**设置已收总金额*/
    public void setRecievedSum(BigDecimal recievedSum, Operator ... oper) {
        this.recievedSum = recievedSum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("recievedSum", param);
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
    public BigDecimal getPunitiveDay() {
        return punitiveDay;
    }

    /**设置逾期天数*/
    public void setPunitiveDay(BigDecimal punitiveDay, Operator ... oper) {
        this.punitiveDay = punitiveDay;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("punitiveDay", param);
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
    public String getCreateUaerId() {
        return createUaerId;
    }

    /**设置创建者*/
    public void setCreateUaerId(String createUaerId, Operator ... oper) {
        this.createUaerId = createUaerId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUaerId", param);
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
        investDetailCode("INVEST_DETAIL_CODE"),
        num("NUM"),
        recievePlanDate("RECIEVE_PLAN_DATE"),
        recieveRealDate("RECIEVE_REAL_DATE"),
        receivedPrincipal("RECEIVED_PRINCIPAL"),
        receivedInterest("RECEIVED_INTEREST"),
        recievedSum("RECIEVED_SUM"),
        receivablePrincipal("RECEIVABLE_PRINCIPAL"),
        receivableInterest("RECEIVABLE_INTEREST"),
        receivableSum("RECEIVABLE_SUM"),
        punitiveDay("PUNITIVE_DAY"),
        punitiveInterest("PUNITIVE_INTEREST"),
        status("STATUS"),
        investCode("INVEST_CODE"),
        loanCode("LOAN_CODE"),
        serialNumber("SERIAL_NUMBER"),
        tenantId("TENANT_ID"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME"),
        createUaerId("CREATE_UAER_ID"),
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
        return "InvestDetail ["
        + ", id=" + id
        + ", investDetailCode=" + investDetailCode
        + ", num=" + num
        + ", recievePlanDate=" + recievePlanDate
        + ", recieveRealDate=" + recieveRealDate
        + ", receivedPrincipal=" + receivedPrincipal
        + ", receivedInterest=" + receivedInterest
        + ", recievedSum=" + recievedSum
        + ", receivablePrincipal=" + receivablePrincipal
        + ", receivableInterest=" + receivableInterest
        + ", receivableSum=" + receivableSum
        + ", punitiveDay=" + punitiveDay
        + ", punitiveInterest=" + punitiveInterest
        + ", status=" + status
        + ", investCode=" + investCode
        + ", loanCode=" + loanCode
        + ", serialNumber=" + serialNumber
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUaerId=" + createUaerId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
