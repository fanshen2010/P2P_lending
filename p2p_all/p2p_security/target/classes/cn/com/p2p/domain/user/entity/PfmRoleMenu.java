/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRoleMenu.java
 * Description:       实体PfmRoleMenu类定义
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


public class PfmRoleMenu implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**菜单ID*/
    private String menuId;

    /**角色ID*/
    private String roleId;

    /**商户CD*/
    private String tenantCd;

         
    public PfmRoleMenu() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取菜单ID*/
    public String getMenuId() {
        return menuId;
    }

    /**设置菜单ID*/
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        return "PfmRoleMenu ["
        + ", id=" + id
        + ", menuId=" + menuId
        + ", roleId=" + roleId
        + ", tenantCd=" + tenantCd
        + "]";
    }

}
