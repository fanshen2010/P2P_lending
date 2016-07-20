/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PaymentLogCriteria.java
 * Description:       查询条件PaymentLogCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-22             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.criteria;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PAYMENT_LOG")
public class PaymentLogCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**交易编码*/
    @Column(name="TX_CODE")
    private String txCode;

    /**机构编号*/
    @Column(name="INSTITUTION_ID")
    private String institutionId;

    /**用户名*/
    @Column(name="USER_LOGIN")
    private String userLogin;

    /**项目编号*/
    @Column(name="PROJECT_NO")
    private String projectNo;

    /**项目名称*/
    @Column(name="PROJECT_NAME")
    private String projectName;

    /**融资项目信息*/
    @Column(name="LOAN_JSON")
    private String loanJson;

    /**投资信息*/
    @Column(name="INVEST_JOSN")
    private String investJosn;

    /**支付金额*/
    @Column(name="PAYMENT_AMOUNT")
    private BigDecimal paymentAmount;

    /**类型*/
    @Column(name="TYPE")
    private String type;

    /**用途*/
    @Column(name="PAYMENT_USAGE")
    private String paymentUsage;

    /**交易流水号*/
    @Column(name="SERIAL_NUMBER")
    private String serialNumber;

    /**备注*/
    @Column(name="REMARK")
    private String remark;

    /**请求报文*/
    @Column(name="REQUEST_PLAIN_TEXT")
    private String requestPlainText;

    /**响应码*/
    @Column(name="RESPONSE_CODE")
    private String responseCode;

    /**响应消息*/
    @Column(name="RESPONSE_MESSAGE")
    private String responseMessage;

    /**响应报文*/
    @Column(name="RESPONSE_PLAIN_TEXT")
    private String responsePlainText;

    /**交易状态*/
    @Column(name="RESPONSE_STATUS")
    private String responseStatus;

    /**交易处理时间*/
    @Column(name="PAYMENT_TIME")
    private Date paymentTime;

    /**交易失败原因*/
    @Column(name="ERROR_MESSAGE")
    private String errorMessage;

    /**商户ID*/
    @Column(name="TENANT_ID")
    private String tenantId;

    /**本地处理状态*/
    @Column(name="STATUS")
    private String status;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

         
    public PaymentLogCriteria() {

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
    /**获取交易编码*/
    public String getTxCode() {
        return txCode;
    }

    /**设置交易编码*/
    public void setTxCode(String txCode, Operator ... oper) {
        this.txCode = txCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("txCode", param);
            }
        }
    }
    /**获取机构编号*/
    public String getInstitutionId() {
        return institutionId;
    }

    /**设置机构编号*/
    public void setInstitutionId(String institutionId, Operator ... oper) {
        this.institutionId = institutionId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("institutionId", param);
            }
        }
    }
    /**获取用户名*/
    public String getUserLogin() {
        return userLogin;
    }

    /**设置用户名*/
    public void setUserLogin(String userLogin, Operator ... oper) {
        this.userLogin = userLogin;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userLogin", param);
            }
        }
    }
    /**获取项目编号*/
    public String getProjectNo() {
        return projectNo;
    }

    /**设置项目编号*/
    public void setProjectNo(String projectNo, Operator ... oper) {
        this.projectNo = projectNo;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("projectNo", param);
            }
        }
    }
    /**获取项目名称*/
    public String getProjectName() {
        return projectName;
    }

    /**设置项目名称*/
    public void setProjectName(String projectName, Operator ... oper) {
        this.projectName = projectName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("projectName", param);
            }
        }
    }
    /**获取融资项目信息*/
    public String getLoanJson() {
        return loanJson;
    }

    /**设置融资项目信息*/
    public void setLoanJson(String loanJson, Operator ... oper) {
        this.loanJson = loanJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanJson", param);
            }
        }
    }
    /**获取投资信息*/
    public String getInvestJosn() {
        return investJosn;
    }

    /**设置投资信息*/
    public void setInvestJosn(String investJosn, Operator ... oper) {
        this.investJosn = investJosn;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("investJosn", param);
            }
        }
    }
    /**获取支付金额*/
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**设置支付金额*/
    public void setPaymentAmount(BigDecimal paymentAmount, Operator ... oper) {
        this.paymentAmount = paymentAmount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentAmount", param);
            }
        }
    }
    /**获取类型*/
    public String getType() {
        return type;
    }

    /**设置类型*/
    public void setType(String type, Operator ... oper) {
        this.type = type;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("type", param);
            }
        }
    }
    /**获取用途*/
    public String getPaymentUsage() {
        return paymentUsage;
    }

    /**设置用途*/
    public void setPaymentUsage(String paymentUsage, Operator ... oper) {
        this.paymentUsage = paymentUsage;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentUsage", param);
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
    /**获取备注*/
    public String getRemark() {
        return remark;
    }

    /**设置备注*/
    public void setRemark(String remark, Operator ... oper) {
        this.remark = remark;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("remark", param);
            }
        }
    }
    /**获取请求报文*/
    public String getRequestPlainText() {
        return requestPlainText;
    }

    /**设置请求报文*/
    public void setRequestPlainText(String requestPlainText, Operator ... oper) {
        this.requestPlainText = requestPlainText;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("requestPlainText", param);
            }
        }
    }
    /**获取响应码*/
    public String getResponseCode() {
        return responseCode;
    }

    /**设置响应码*/
    public void setResponseCode(String responseCode, Operator ... oper) {
        this.responseCode = responseCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("responseCode", param);
            }
        }
    }
    /**获取响应消息*/
    public String getResponseMessage() {
        return responseMessage;
    }

    /**设置响应消息*/
    public void setResponseMessage(String responseMessage, Operator ... oper) {
        this.responseMessage = responseMessage;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("responseMessage", param);
            }
        }
    }
    /**获取响应报文*/
    public String getResponsePlainText() {
        return responsePlainText;
    }

    /**设置响应报文*/
    public void setResponsePlainText(String responsePlainText, Operator ... oper) {
        this.responsePlainText = responsePlainText;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("responsePlainText", param);
            }
        }
    }
    /**获取交易状态*/
    public String getResponseStatus() {
        return responseStatus;
    }

    /**设置交易状态*/
    public void setResponseStatus(String responseStatus, Operator ... oper) {
        this.responseStatus = responseStatus;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("responseStatus", param);
            }
        }
    }
    /**获取交易处理时间*/
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**设置交易处理时间*/
    public void setPaymentTime(Date paymentTime, Operator ... oper) {
        this.paymentTime = paymentTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentTime", param);
            }
        }
    }
    /**获取交易失败原因*/
    public String getErrorMessage() {
        return errorMessage;
    }

    /**设置交易失败原因*/
    public void setErrorMessage(String errorMessage, Operator ... oper) {
        this.errorMessage = errorMessage;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("errorMessage", param);
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
    /**获取本地处理状态*/
    public String getStatus() {
        return status;
    }

    /**设置本地处理状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
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

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        txCode("TX_CODE"),
        institutionId("INSTITUTION_ID"),
        userLogin("USER_LOGIN"),
        projectNo("PROJECT_NO"),
        projectName("PROJECT_NAME"),
        loanJson("LOAN_JSON"),
        investJosn("INVEST_JOSN"),
        paymentAmount("PAYMENT_AMOUNT"),
        type("TYPE"),
        paymentUsage("PAYMENT_USAGE"),
        serialNumber("SERIAL_NUMBER"),
        remark("REMARK"),
        requestPlainText("REQUEST_PLAIN_TEXT"),
        responseCode("RESPONSE_CODE"),
        responseMessage("RESPONSE_MESSAGE"),
        responsePlainText("RESPONSE_PLAIN_TEXT"),
        responseStatus("RESPONSE_STATUS"),
        paymentTime("PAYMENT_TIME"),
        errorMessage("ERROR_MESSAGE"),
        tenantId("TENANT_ID"),
        status("STATUS"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME");

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
        return "PaymentLog ["
        + ", id=" + id
        + ", txCode=" + txCode
        + ", institutionId=" + institutionId
        + ", userLogin=" + userLogin
        + ", projectNo=" + projectNo
        + ", projectName=" + projectName
        + ", loanJson=" + loanJson
        + ", investJosn=" + investJosn
        + ", paymentAmount=" + paymentAmount
        + ", type=" + type
        + ", paymentUsage=" + paymentUsage
        + ", serialNumber=" + serialNumber
        + ", remark=" + remark
        + ", requestPlainText=" + requestPlainText
        + ", responseCode=" + responseCode
        + ", responseMessage=" + responseMessage
        + ", responsePlainText=" + responsePlainText
        + ", responseStatus=" + responseStatus
        + ", paymentTime=" + paymentTime
        + ", errorMessage=" + errorMessage
        + ", tenantId=" + tenantId
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + "]";
    }

}
