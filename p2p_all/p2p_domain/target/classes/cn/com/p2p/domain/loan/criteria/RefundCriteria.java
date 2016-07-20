/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RefundCriteria.java
 * Description:       查询条件RefundCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-23             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "REFUND")
public class RefundCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**退款金额*/
    @Column(name="AMOUNT")
    private BigDecimal amount;

    /**交易时间*/
    @Column(name="REFUND_TIME")
    private Date refundTime;

    /**到账时间*/
    @Column(name="TOACCOUNT_TIME")
    private Date toaccountTime;

    /**交易类型*/
    @Column(name="TYPE")
    private String type;

    /**交易状态*/
    @Column(name="STATUS")
    private String status;

    /**退款原因*/
    @Column(name="REASON")
    private String reason;

    /**投资编号*/
    @Column(name="INVEST_CODE")
    private String investCode;

    /**投资客户编号*/
    @Column(name="USER_ID")
    private String userId;

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

         
    public RefundCriteria() {

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
    /**获取退款金额*/
    public BigDecimal getAmount() {
        return amount;
    }

    /**设置退款金额*/
    public void setAmount(BigDecimal amount, Operator ... oper) {
        this.amount = amount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("amount", param);
            }
        }
    }
    /**获取交易时间*/
    public Date getRefundTime() {
        return refundTime;
    }

    /**设置交易时间*/
    public void setRefundTime(Date refundTime, Operator ... oper) {
        this.refundTime = refundTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("refundTime", param);
            }
        }
    }
    /**获取到账时间*/
    public Date getToaccountTime() {
        return toaccountTime;
    }

    /**设置到账时间*/
    public void setToaccountTime(Date toaccountTime, Operator ... oper) {
        this.toaccountTime = toaccountTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("toaccountTime", param);
            }
        }
    }
    /**获取交易类型*/
    public String getType() {
        return type;
    }

    /**设置交易类型*/
    public void setType(String type, Operator ... oper) {
        this.type = type;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("type", param);
            }
        }
    }
    /**获取交易状态*/
    public String getStatus() {
        return status;
    }

    /**设置交易状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
            }
        }
    }
    /**获取退款原因*/
    public String getReason() {
        return reason;
    }

    /**设置退款原因*/
    public void setReason(String reason, Operator ... oper) {
        this.reason = reason;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("reason", param);
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
    /**获取投资客户编号*/
    public String getUserId() {
        return userId;
    }

    /**设置投资客户编号*/
    public void setUserId(String userId, Operator ... oper) {
        this.userId = userId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userId", param);
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
        amount("AMOUNT"),
        refundTime("REFUND_TIME"),
        toaccountTime("TOACCOUNT_TIME"),
        type("TYPE"),
        status("STATUS"),
        reason("REASON"),
        investCode("INVEST_CODE"),
        userId("USER_ID"),
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
        return "Refund ["
        + ", id=" + id
        + ", amount=" + amount
        + ", refundTime=" + refundTime
        + ", toaccountTime=" + toaccountTime
        + ", type=" + type
        + ", status=" + status
        + ", reason=" + reason
        + ", investCode=" + investCode
        + ", userId=" + userId
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
