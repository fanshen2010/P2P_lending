/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartmentInfo.java
 * Description:       实体PfmTenantDepartmentInfo类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-13             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;

import java.util.Date;

public class PfmTenantDepartmentInfo implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**商户CD*/
    private String tenantCd;

    /**部门CD*/
    private String departmentCd;

    /**注册资金*/
    private String registeredCapital;

    /**成立日*/
    private Date createdDate;

    /**合作开始日*/
    private Date cooperateDate;

    /**地址*/
    private String address;

    /**简介*/
    private String introduction;

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

         
    public PfmTenantDepartmentInfo() {

    }

    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }
    /**获取部门CD*/
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**设置部门CD*/
    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }
    /**获取注册资金*/
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    /**设置注册资金*/
    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }
    /**获取成立日*/
    public Date getCreatedDate() {
        return createdDate;
    }

    /**设置成立日*/
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**获取合作开始日*/
    public Date getCooperateDate() {
        return cooperateDate;
    }

    /**设置合作开始日*/
    public void setCooperateDate(Date cooperateDate) {
        this.cooperateDate = cooperateDate;
    }

    /**获取地址*/
    public String getAddress() {
        return address;
    }

    /**设置地址*/
    public void setAddress(String address) {
        this.address = address;
    }
    /**获取简介*/
    public String getIntroduction() {
        return introduction;
    }

    /**设置简介*/
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        return "PfmTenantDepartmentInfo ["
        + ", tenantCd=" + tenantCd
        + ", departmentCd=" + departmentCd
        + ", registeredCapital=" + registeredCapital
        + ", createdDate=" + createdDate
        + ", cooperateDate=" + cooperateDate
        + ", address=" + address
        + ", introduction=" + introduction
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
