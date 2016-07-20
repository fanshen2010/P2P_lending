/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRoleMenuCriteria.java
 * Description:       查询条件PfmRoleMenuCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;


import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_ROLE_MENU")
public class PfmRoleMenuCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**菜单ID*/
    @Column(name="MENU_ID")
    private String menuId;

    /**角色ID*/
    @Column(name="ROLE_ID")
    private String roleId;

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

         
    public PfmRoleMenuCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取菜单ID*/
    public String getMenuId() {
        return menuId;
    }

    /**设置菜单ID*/
    public void setMenuId(String menuId, Operator ... oper) {
        this.menuId = menuId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuId", param);
            }
        }
    }
    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId, Operator ... oper) {
        this.roleId = roleId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleId", param);
            }
        }
    }
    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd, Operator ... oper) {
        this.tenantCd = tenantCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantCd", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        menuId("MENU_ID"),
        roleId("ROLE_ID"),
        tenantCd("TENANT_CD");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
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
