/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PaymentLog.java
 * Description:       实体PaymentLog类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-22             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentLog implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**交易编码*/
    private String txCode;

    /**用户名*/
    private String userLogin;

    /**项目编号*/
    private String projectNo;

    /**项目名称*/
    private String projectName;

    /**融资项目信息*/
    private String loanJson;

    /**投资信息*/
    private String investJosn;

    /**支付金额*/
    private BigDecimal paymentAmount;

    /**类型
     * 1投资
     * 2结算
     * 3转账
     * 4还款
     * 5充值
     * 6提现
     * 
     * */
    private String type;

    /**用途
     * 投资:正常投资/债权转让
     * 结算:融资人融资款/担保公司担保费/P2P 
     * 平台服务费/投资收益/投资撤回退款（指募集期内投资人的投资反悔的主动退款申请）/投资超募退款
     * 转账:P2P 平台服务费/担保公司担保费/P2P 平台返现
     * 充值：1充值
     * 提现：1提现
     * */
    private String paymentUsage;

    /**交易处理时间*/
    private Date paymentTime;

    /**商户ID*/
    private String tenantId;

    /**本地处理状态*/
    private String status;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

         
    public PaymentLog() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }
    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取交易编码*/
    public String getTxCode() {
        return txCode;
    }
    /**设置交易编码*/
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }
    /**获取用户名*/
    public String getUserLogin() {
        return userLogin;
    }
    /**设置用户名*/
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
    /**获取项目编号*/
    public String getProjectNo() {
        return projectNo;
    }
    /**设置项目编号*/
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }
    /**获取项目名称*/
    public String getProjectName() {
        return projectName;
    }
    /**设置项目名称*/
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    /**获取融资项目信息*/
    public String getLoanJson() {
        return loanJson;
    }
    /**设置融资项目信息*/
    public void setLoanJson(String loanJson) {
        this.loanJson = loanJson;
    }
    /**获取投资信息*/
    public String getInvestJosn() {
        return investJosn;
    }

    /**设置投资信息*/
    public void setInvestJosn(String investJosn) {
        this.investJosn = investJosn;
    }
    /**获取支付金额*/
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**设置支付金额*/
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    /**获取类型*/
    public String getType() {
        return type;
    }

    /**设置类型*/
    public void setType(String type) {
        this.type = type;
    }
    /**获取用途*/
    public String getPaymentUsage() {
        return paymentUsage;
    }

    /**设置用途*/
    public void setPaymentUsage(String paymentUsage) {
        this.paymentUsage = paymentUsage;
    }
    /**获取交易处理时间*/
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**设置交易处理时间*/
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }
    /**设置商户ID*/
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    /**获取本地处理状态*/
    public String getStatus() {
        return status;
    }

    /**设置本地处理状态*/
    public void setStatus(String status) {
        this.status = status;
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

    public String toString() {
        return "PaymentLog ["
        + ", id=" + id
        + ", txCode=" + txCode
        + ", userLogin=" + userLogin
        + ", projectNo=" + projectNo
        + ", projectName=" + projectName
        + ", loanJson=" + loanJson
        + ", investJosn=" + investJosn
        + ", paymentAmount=" + paymentAmount
        + ", type=" + type
        + ", paymentUsage=" + paymentUsage
        + ", paymentTime=" + paymentTime
        + ", tenantId=" + tenantId
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + "]";
    }

}
