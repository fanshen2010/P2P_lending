/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RepayDetail.java
 * Description:       实体RepayDetail类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.entity;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;

public class RepayDetail implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**融资还款详细编号*/
    private String repayDetailCode;

    /**还款期号*/
    private Integer num;

    /**计划收款日*/
    private Date repayPlanDate;

    /**实际收款日*/
    private Date repayRealDate;

    /**已收利息*/
    private BigDecimal receivedInterest;

    /**已收本金*/
    private BigDecimal receivedPrincipal;

    /**已收总金额*/
    private BigDecimal receivedSum;

    /**应收利息*/
    private BigDecimal receivableInterest;

    /**应收本金*/
    private BigDecimal receivablePrincipal;

    /**应收总金额*/
    private BigDecimal receivableSum;

    /**逾期天数*/
    private Integer overdueDays;

    /**逾期滞纳金*/
    private BigDecimal punitiveDelayPayment;

    /**逾期罚息*/
    private BigDecimal punitiveInterest;

    /**还款状态*/
    private String status;

    /**还款账号*/
    private String accountNo;

    /**还款用户类别*/
    private String accountType;

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

         
    public RepayDetail() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取融资还款详细编号*/
    public String getRepayDetailCode() {
        return repayDetailCode;
    }

    /**设置融资还款详细编号*/
    public void setRepayDetailCode(String repayDetailCode) {
        this.repayDetailCode = repayDetailCode;
    }
    /**获取还款期号*/
    public Integer getNum() {
        return num;
    }

    /**设置还款期号*/
    public void setNum(Integer num) {
        this.num = num;
    }
    /**获取计划收款日*/
    public Date getRepayPlanDate() {
        return repayPlanDate;
    }

    /**设置计划收款日*/
    public void setRepayPlanDate(Date repayPlanDate) {
        this.repayPlanDate = repayPlanDate;
    }
    /**获取实际收款日*/
    public Date getRepayRealDate() {
        return repayRealDate;
    }

    /**设置实际收款日*/
    public void setRepayRealDate(Date repayRealDate) {
        this.repayRealDate = repayRealDate;
    }
    /**获取已收利息*/
    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    /**设置已收利息*/
    public void setReceivedInterest(BigDecimal receivedInterest) {
        this.receivedInterest = receivedInterest;
    }
    /**获取已收本金*/
    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**设置已收本金*/
    public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
        this.receivedPrincipal = receivedPrincipal;
    }
    /**获取已收总金额*/
    public BigDecimal getReceivedSum() {
        return receivedSum;
    }

    /**设置已收总金额*/
    public void setReceivedSum(BigDecimal receivedSum) {
        this.receivedSum = receivedSum;
    }
    /**获取应收利息*/
    public BigDecimal getReceivableInterest() {
        return receivableInterest;
    }

    /**设置应收利息*/
    public void setReceivableInterest(BigDecimal receivableInterest) {
        this.receivableInterest = receivableInterest;
    }
    /**获取应收本金*/
    public BigDecimal getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**设置应收本金*/
    public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
        this.receivablePrincipal = receivablePrincipal;
    }
    /**获取应收总金额*/
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }

    /**设置应收总金额*/
    public void setReceivableSum(BigDecimal receivableSum) {
        this.receivableSum = receivableSum;
    }
    /**获取逾期天数*/
    public Integer getOverdueDays() {
        return overdueDays;
    }

    /**设置逾期天数*/
    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }
    /**获取逾期滞纳金*/
    public BigDecimal getPunitiveDelayPayment() {
        return punitiveDelayPayment;
    }

    /**设置逾期滞纳金*/
    public void setPunitiveDelayPayment(BigDecimal punitiveDelayPayment) {
        this.punitiveDelayPayment = punitiveDelayPayment;
    }
    /**获取逾期罚息*/
    public BigDecimal getPunitiveInterest() {
        return punitiveInterest;
    }

    /**设置逾期罚息*/
    public void setPunitiveInterest(BigDecimal punitiveInterest) {
        this.punitiveInterest = punitiveInterest;
    }
    /**获取还款状态*/
    public String getStatus() {
        return status;
    }

    /**设置还款状态*/
    public void setStatus(String status) {
        this.status = status;
    }
    /**获取还款账号*/
    public String getAccountNo() {
        return accountNo;
    }

    /**设置还款账号*/
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    /**获取还款用户类别*/
    public String getAccountType() {
        return accountType;
    }

    /**设置还款用户类别*/
    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
