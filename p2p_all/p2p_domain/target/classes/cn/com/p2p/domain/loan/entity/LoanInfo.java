/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanInfo.java
 * Description:       实体LoanInfo类定义
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

public class LoanInfo implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**融资编号*/
    private String loanCode;

    /**融资信息Json*/
    private String loanMsg;

    /**项目说明书*/
    private String instructions;

    /**委托合同json*/
    private String entrustJson;

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

         
    public LoanInfo() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**获取融资信息Json*/
    public String getLoanMsg() {
        return loanMsg;
    }

    /**设置融资信息Json*/
    public void setLoanMsg(String loanMsg) {
        this.loanMsg = loanMsg;
    }
    /**获取项目说明书*/
    public String getInstructions() {
        return instructions;
    }

    /**设置项目说明书*/
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    /**获取委托合同json*/
    public String getEntrustJson() {
        return entrustJson;
    }

    /**设置委托合同json*/
    public void setEntrustJson(String entrustJson) {
        this.entrustJson = entrustJson;
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
        return "LoanInfo ["
        + ", id=" + id
        + ", loanCode=" + loanCode
        + ", loanMsg=" + loanMsg
        + ", instructions=" + instructions
        + ", entrustJson=" + entrustJson
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
