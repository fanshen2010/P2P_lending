/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanPersonalInfo.java
 * Description:       实体LoanPersonalInfo类定义
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

public class LoanPersonalInfo implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**融资编号*/
    private String loanCode;

    /**个人基本json*/
    private String basicJson;

    /**家庭信息json*/
    private String familyJson;

    /**工作信息json*/
    private String jobJson;

    /**联系人json*/
    private String contactJson;

    /**房产信息json*/
    private String houseJson;

    /**车产信息json*/
    private String carJson;

    /**信用卡信息json*/
    private String creditcardJson;

    /**个人证照json*/
    private String creditJson;

    /**电子合同json*/
    private String electJson;

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

         
    public LoanPersonalInfo() {

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
    /**获取个人基本json*/
    public String getBasicJson() {
        return basicJson;
    }

    /**设置个人基本json*/
    public void setBasicJson(String basicJson) {
        this.basicJson = basicJson;
    }
    /**获取家庭信息json*/
    public String getFamilyJson() {
        return familyJson;
    }

    /**设置家庭信息json*/
    public void setFamilyJson(String familyJson) {
        this.familyJson = familyJson;
    }
    /**获取工作信息json*/
    public String getJobJson() {
        return jobJson;
    }

    /**设置工作信息json*/
    public void setJobJson(String jobJson) {
        this.jobJson = jobJson;
    }
    /**获取联系人json*/
    public String getContactJson() {
        return contactJson;
    }

    /**设置联系人json*/
    public void setContactJson(String contactJson) {
        this.contactJson = contactJson;
    }
    /**获取房产信息json*/
    public String getHouseJson() {
        return houseJson;
    }

    /**设置房产信息json*/
    public void setHouseJson(String houseJson) {
        this.houseJson = houseJson;
    }
    /**获取车产信息json*/
    public String getCarJson() {
        return carJson;
    }

    /**设置车产信息json*/
    public void setCarJson(String carJson) {
        this.carJson = carJson;
    }
    /**获取信用卡信息json*/
    public String getCreditcardJson() {
        return creditcardJson;
    }

    /**设置信用卡信息json*/
    public void setCreditcardJson(String creditcardJson) {
        this.creditcardJson = creditcardJson;
    }
    /**获取个人证照json*/
    public String getCreditJson() {
        return creditJson;
    }

    /**设置个人证照json*/
    public void setCreditJson(String creditJson) {
        this.creditJson = creditJson;
    }
    /**获取电子合同json*/
    public String getElectJson() {
        return electJson;
    }

    /**设置电子合同json*/
    public void setElectJson(String electJson) {
        this.electJson = electJson;
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
        return "LoanPersonalInfo ["
        + ", id=" + id
        + ", loanCode=" + loanCode
        + ", basicJson=" + basicJson
        + ", familyJson=" + familyJson
        + ", jobJson=" + jobJson
        + ", contactJson=" + contactJson
        + ", houseJson=" + houseJson
        + ", carJson=" + carJson
        + ", creditcardJson=" + creditcardJson
        + ", creditJson=" + creditJson
        + ", electJson=" + electJson
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
