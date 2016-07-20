/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestDetail.java
 * Description:       实体InvestDetail类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-24             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.entity;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;

public class InvestDetail implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**投资回款明细编号*/
    private String investDetailCode;

    /**还款期号*/
    private Integer num;

    /**计划还款日*/
    private Date recievePlanDate;

    /**实际还款日*/
    private Date recieveRealDate;

    /**已收本金*/
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    private BigDecimal receivedInterest;

    /**已收总金额*/
    private BigDecimal recievedSum;

    /**应收本金*/
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    private BigDecimal receivableInterest;

    /**应收总金额*/
    private BigDecimal receivableSum;

    /**逾期天数*/
    private BigDecimal punitiveDay;

    /**逾期罚息*/
    private BigDecimal punitiveInterest;

    /**还款状态*/
    private String status;

    /**投资编号*/
    private String investCode;

    /**融资编号*/
    private String loanCode;

    /**投资项目名*/
    private String loanName;

    /**交易流水号*/
    private String serialNumber;

    /**商户ID*/
    private String tenantId;

    /**投资人用户ID*/
    private String userId;

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

         
    public InvestDetail() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取投资回款明细编号*/
    public String getInvestDetailCode() {
        return investDetailCode;
    }

    /**设置投资回款明细编号*/
    public void setInvestDetailCode(String investDetailCode) {
        this.investDetailCode = investDetailCode;
    }
    /**获取还款期号*/
    public Integer getNum() {
        return num;
    }

    /**设置还款期号*/
    public void setNum(Integer num) {
        this.num = num;
    }
    /**获取计划还款日*/
    public Date getRecievePlanDate() {
        return recievePlanDate;
    }

    /**设置计划还款日*/
    public void setRecievePlanDate(Date recievePlanDate) {
        this.recievePlanDate = recievePlanDate;
    }
    /**获取实际还款日*/
    public Date getRecieveRealDate() {
        return recieveRealDate;
    }

    /**设置实际还款日*/
    public void setRecieveRealDate(Date recieveRealDate) {
        this.recieveRealDate = recieveRealDate;
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
    /**获取已收总金额*/
    public BigDecimal getRecievedSum() {
        return recievedSum;
    }

    /**设置已收总金额*/
    public void setRecievedSum(BigDecimal recievedSum) {
        this.recievedSum = recievedSum;
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
    /**获取应收总金额*/
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }

    /**设置应收总金额*/
    public void setReceivableSum(BigDecimal receivableSum) {
        this.receivableSum = receivableSum;
    }
    /**获取逾期天数*/
    public BigDecimal getPunitiveDay() {
        return punitiveDay;
    }

    /**设置逾期天数*/
    public void setPunitiveDay(BigDecimal punitiveDay) {
        this.punitiveDay = punitiveDay;
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
    /**获取投资编号*/
    public String getInvestCode() {
        return investCode;
    }

    /**设置投资编号*/
    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**获取投资项目名*/
    public String getLoanName() {
        return loanName;
    }

    /**设置投资项目名*/
    public void setLoanName(String loanName) {
        this.loanName = loanName;
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
    /**获取投资人用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置投资人用户ID*/
    public void setUserId(String userId) {
        this.userId = userId;
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
        + ", loanName=" + loanName
        + ", serialNumber=" + serialNumber
        + ", tenantId=" + tenantId
        + ", userId=" + userId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
