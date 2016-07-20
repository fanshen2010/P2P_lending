/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRole.java
 * Description:       实体PfmRole类定义
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

public class PfmRole implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**角色ID*/
    private String roleId;

    /**角色说明*/
    private String roleMem;

    /**角色名称*/
    private String roleName;

    /**角色状态*/
    private Integer roleState;

    /**商户CD*/
    private String tenantCd;

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

         
    public PfmRole() {

    }

    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**获取角色说明*/
    public String getRoleMem() {
        return roleMem;
    }

    /**设置角色说明*/
    public void setRoleMem(String roleMem) {
        this.roleMem = roleMem;
    }
    /**获取角色名称*/
    public String getRoleName() {
        return roleName;
    }

    /**设置角色名称*/
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**获取角色状态*/
    public Integer getRoleState() {
        return roleState;
    }

    /**设置角色状态*/
    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }
    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
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
        return "PfmRole ["
        + ", roleId=" + roleId
        + ", roleMem=" + roleMem
        + ", roleName=" + roleName
        + ", roleState=" + roleState
        + ", tenantCd=" + tenantCd
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
