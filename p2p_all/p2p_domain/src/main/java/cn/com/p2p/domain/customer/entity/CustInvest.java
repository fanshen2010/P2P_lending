/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CustInvest.java
 * Description:       实体CustInvest类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-20             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.customer.entity;

import java.io.Serializable;

import java.util.Date;

public class CustInvest implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**客户名称*/
    private String customerName;

    /**身份证号*/
    private String identity;

    /**用户ID*/
    private String userId;

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

         
    public CustInvest() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取客户名称*/
    public String getCustomerName() {
        return customerName;
    }

    /**设置客户名称*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**获取身份证号*/
    public String getIdentity() {
        return identity;
    }

    /**设置身份证号*/
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    /**获取用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置用户ID*/
    public void setUserId(String userId) {
        this.userId = userId;
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
        return "CustInvest ["
        + ", id=" + id
        + ", customerName=" + customerName
        + ", identity=" + identity
        + ", userId=" + userId
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
