/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenant.java
 * Description:       实体PfmTenant类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;

import java.util.Date;

public class PfmTenant implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**商户CD*/
    private String tenantCd;

    /**商户名称*/
    private String tenantName;

    /**商户平台有效期*/
    private String validFlag;

    /**开始日*/
    private Date startDate;

    /**结束日*/
    private Date endDate;

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

         
    public PfmTenant() {

    }

    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }
    /**获取商户名称*/
    public String getTenantName() {
        return tenantName;
    }

    /**设置商户名称*/
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    /**获取商户平台有效期*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置商户平台有效期*/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }
    /**获取开始日*/
    public Date getStartDate() {
        return startDate;
    }

    /**设置开始日*/
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**获取结束日*/
    public Date getEndDate() {
        return endDate;
    }

    /**设置结束日*/
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        return "PfmTenant ["
        + ", tenantCd=" + tenantCd
        + ", tenantName=" + tenantName
        + ", validFlag=" + validFlag
        + ", startDate=" + startDate
        + ", endDate=" + endDate
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
