/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartment.java
 * Description:       实体PfmTenantDepartment类定义
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

public class PfmTenantDepartment implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**商户CD*/
    private String tenantCd;

    /**部门CD*/
    private String departmentCd;

    /**部门名称*/
    private String departmentName;

    /**部门类型*/
    private String departmentType;

    /**有效flag*/
    private String vilidFlag;

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

         
    public PfmTenantDepartment() {

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
    /**获取部门名称*/
    public String getDepartmentName() {
        return departmentName;
    }

    /**设置部门名称*/
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    /**获取部门类型*/
    public String getDepartmentType() {
        return departmentType;
    }

    /**设置部门类型*/
    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }
    /**获取有效flag*/
    public String getVilidFlag() {
        return vilidFlag;
    }

    /**设置有效flag*/
    public void setVilidFlag(String vilidFlag) {
        this.vilidFlag = vilidFlag;
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
        return "PfmTenantDepartment ["
        + ", tenantCd=" + tenantCd
        + ", departmentCd=" + departmentCd
        + ", departmentName=" + departmentName
        + ", departmentType=" + departmentType
        + ", vilidFlag=" + vilidFlag
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
