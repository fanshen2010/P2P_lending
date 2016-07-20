/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmMenuCriteria.java
 * Description:       查询条件PfmMenuCriteria类定义
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

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_MENU")
public class PfmMenuCriteria extends BaseCriteria {

    /**菜单_ID*/
    @Column(name="MENU_ID")
    private String menuId;

    /**菜单级别*/
    @Column(name="MENU_LEVEL")
    private String menuLevel;

    /**父级菜单ID*/
    @Column(name="MENU_PARENT_ID")
    private String menuParentId;

    /**菜单名*/
    @Column(name="MENU_NAME")
    private String menuName;

    /**菜单排序*/
    @Column(name="ORDER_NUM")
    private Integer orderNum;

    /**菜单图标*/
    @Column(name="MENU_ICON")
    private String menuIcon;

    /**菜单URL*/
    @Column(name="MENU_URL")
    private String menuUrl;

    /**菜单打开方式*/
    @Column(name="MENU_TARGET")
    private String menuTarget;

    /**菜单样式*/
    @Column(name="MENU_CLASS")
    private String menuClass;

    /**菜单状态*/
    @Column(name="STATUS")
    private String status;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    /**创建者*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

    /**更新者*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;

    /**版本*/
    @Column(name="VERSION")
    private Integer version;

         
    public PfmMenuCriteria() {

    }

    /**获取菜单_ID*/
    public String getMenuId() {
        return menuId;
    }

    /**设置菜单_ID*/
    public void setMenuId(String menuId, Operator ... oper) {
        this.menuId = menuId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuId", param);
            }
        }
    }
    /**获取菜单级别*/
    public String getMenuLevel() {
        return menuLevel;
    }

    /**设置菜单级别*/
    public void setMenuLevel(String menuLevel, Operator ... oper) {
        this.menuLevel = menuLevel;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuLevel", param);
            }
        }
    }
    /**获取父级菜单ID*/
    public String getMenuParentId() {
        return menuParentId;
    }

    /**设置父级菜单ID*/
    public void setMenuParentId(String menuParentId, Operator ... oper) {
        this.menuParentId = menuParentId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuParentId", param);
            }
        }
    }
    /**获取菜单名*/
    public String getMenuName() {
        return menuName;
    }

    /**设置菜单名*/
    public void setMenuName(String menuName, Operator ... oper) {
        this.menuName = menuName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuName", param);
            }
        }
    }
    /**获取菜单排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置菜单排序*/
    public void setOrderNum(Integer orderNum, Operator ... oper) {
        this.orderNum = orderNum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("orderNum", param);
            }
        }
    }
    /**获取菜单图标*/
    public String getMenuIcon() {
        return menuIcon;
    }

    /**设置菜单图标*/
    public void setMenuIcon(String menuIcon, Operator ... oper) {
        this.menuIcon = menuIcon;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuIcon", param);
            }
        }
    }
    /**获取菜单URL*/
    public String getMenuUrl() {
        return menuUrl;
    }

    /**设置菜单URL*/
    public void setMenuUrl(String menuUrl, Operator ... oper) {
        this.menuUrl = menuUrl;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuUrl", param);
            }
        }
    }
    /**获取菜单打开方式*/
    public String getMenuTarget() {
        return menuTarget;
    }

    /**设置菜单打开方式*/
    public void setMenuTarget(String menuTarget, Operator ... oper) {
        this.menuTarget = menuTarget;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuTarget", param);
            }
        }
    }
    /**获取菜单样式*/
    public String getMenuClass() {
        return menuClass;
    }

    /**设置菜单样式*/
    public void setMenuClass(String menuClass, Operator ... oper) {
        this.menuClass = menuClass;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("menuClass", param);
            }
        }
    }
    /**获取菜单状态*/
    public String getStatus() {
        return status;
    }

    /**设置菜单状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
            }
        }
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime, Operator ... oper) {
        this.createTime = createTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createTime", param);
            }
        }
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime, Operator ... oper) {
        this.updateTime = updateTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateTime", param);
            }
        }
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId, Operator ... oper) {
        this.createUserId = createUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUserId", param);
            }
        }
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId, Operator ... oper) {
        this.updateUserId = updateUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateUserId", param);
            }
        }
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version, Operator ... oper) {
        this.version = version;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("version", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        menuId("MENU_ID"),
        menuLevel("MENU_LEVEL"),
        menuParentId("MENU_PARENT_ID"),
        menuName("MENU_NAME"),
        orderNum("ORDER_NUM"),
        menuIcon("MENU_ICON"),
        menuUrl("MENU_URL"),
        menuTarget("MENU_TARGET"),
        menuClass("MENU_CLASS"),
        status("STATUS"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME"),
        createUserId("CREATE_USER_ID"),
        updateUserId("UPDATE_USER_ID"),
        version("VERSION");

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
        return "PfmMenu ["
        + ", menuId=" + menuId
        + ", menuLevel=" + menuLevel
        + ", menuParentId=" + menuParentId
        + ", menuName=" + menuName
        + ", orderNum=" + orderNum
        + ", menuIcon=" + menuIcon
        + ", menuUrl=" + menuUrl
        + ", menuTarget=" + menuTarget
        + ", menuClass=" + menuClass
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
