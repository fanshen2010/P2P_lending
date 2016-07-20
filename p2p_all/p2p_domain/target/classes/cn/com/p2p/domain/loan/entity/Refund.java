/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        Refund.java
 * Description:       实体Refund类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-23             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

public class Refund implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**退款金额*/
    private BigDecimal amount;

    /**交易时间*/
    private Date refundTime;

    /**到账时间*/
    private Date toaccountTime;

    /**交易类型*/
    private String type;

    /**交易状态*/
    private String status;

    /**退款原因*/
    private String reason;

    /**投资编号*/
    private String investCode;

    /**投资客户编号*/
    private String userId;

    /**融资编号*/
    private String loanCode;

    /**交易流水号*/
    private String serialNumber;

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

         
    public Refund() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取退款金额*/
    public BigDecimal getAmount() {
        return amount;
    }

    /**设置退款金额*/
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**获取交易时间*/
    public Date getRefundTime() {
        return refundTime;
    }

    /**设置交易时间*/
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
    /**获取到账时间*/
    public Date getToaccountTime() {
        return toaccountTime;
    }

    /**设置到账时间*/
    public void setToaccountTime(Date toaccountTime) {
        this.toaccountTime = toaccountTime;
    }
    /**获取交易类型*/
    public String getType() {
        return type;
    }

    /**设置交易类型*/
    public void setType(String type) {
        this.type = type;
    }
    /**获取交易状态*/
    public String getStatus() {
        return status;
    }

    /**设置交易状态*/
    public void setStatus(String status) {
        this.status = status;
    }
    /**获取退款原因*/
    public String getReason() {
        return reason;
    }

    /**设置退款原因*/
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**获取投资编号*/
    public String getInvestCode() {
        return investCode;
    }

    /**设置投资编号*/
    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }
    /**获取投资客户编号*/
    public String getUserId() {
        return userId;
    }

    /**设置投资客户编号*/
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**获取交易流水号*/
    public String getSerialNumber() {
        return serialNumber;
    }

    /**设置交易流水号*/
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }

    /**设置商户ID*/
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version) {
        this.version = version;
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
