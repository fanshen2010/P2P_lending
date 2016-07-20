package cn.com.p2p.businessmanagentsys.service.dto;

import java.util.Date;
import java.util.List;

/**
 * 系统菜单分级信息Dto
 * @author 
 *
 */
public class MenuListDto {
	
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
    
    /**下级菜单*/
    private List<MenuListDto> menuList;


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuTarget() {
		return menuTarget;
	}

	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<MenuListDto> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuListDto> menuList) {
		this.menuList = menuList;
	}
}
