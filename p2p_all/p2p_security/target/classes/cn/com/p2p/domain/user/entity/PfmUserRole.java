/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmUserRole.java
 * Description:       实体PfmUserRole类定义
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


public class PfmUserRole implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**角色ID*/
    private String roleId;

    /**用户ID*/
    private String userId;

    /**商户CD*/
    private String tenantCd;

         
    public PfmUserRole() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**获取用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置用户ID*/
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }

    public String toString() {
        return "PfmUserRole ["
        + ", id=" + id
        + ", roleId=" + roleId
        + ", userId=" + userId
        + ", tenantCd=" + tenantCd
        + "]";
    }

}
