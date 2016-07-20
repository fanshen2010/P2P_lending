/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmMenu.java
 * Description:       实体PfmMenu类定义
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

public class PfmMenu implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**菜单_ID*/
    private String menuId;

    /**菜单级别*/
    private String menuLevel;

    /**父级菜单ID*/
    private String menuParentId;

    /**菜单名*/
    private String menuName;

    /**菜单排序*/
    private Integer orderNum;

    /**菜单图标*/
    private String menuIcon;

    /**菜单URL*/
    private String menuUrl;

    /**菜单打开方式*/
    private String menuTarget;

    /**菜单样式*/
    private String menuClass;

    /**菜单状态*/
    private String status;

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

         
    public PfmMenu() {

    }

    /**获取菜单_ID*/
    public String getMenuId() {
        return menuId;
    }

    /**设置菜单_ID*/
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**获取菜单级别*/
    public String getMenuLevel() {
        return menuLevel;
    }

    /**设置菜单级别*/
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }
    /**获取父级菜单ID*/
    public String getMenuParentId() {
        return menuParentId;
    }

    /**设置父级菜单ID*/
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }
    /**获取菜单名*/
    public String getMenuName() {
        return menuName;
    }

    /**设置菜单名*/
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**获取菜单排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置菜单排序*/
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**获取菜单图标*/
    public String getMenuIcon() {
        return menuIcon;
    }

    /**设置菜单图标*/
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    /**获取菜单URL*/
    public String getMenuUrl() {
        return menuUrl;
    }

    /**设置菜单URL*/
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    /**获取菜单打开方式*/
    public String getMenuTarget() {
        return menuTarget;
    }

    /**设置菜单打开方式*/
    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }
    /**获取菜单样式*/
    public String getMenuClass() {
        return menuClass;
    }

    /**设置菜单样式*/
    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }
    /**获取菜单状态*/
    public String getStatus() {
        return status;
    }

    /**设置菜单状态*/
    public void setStatus(String status) {
        this.status = status;
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
