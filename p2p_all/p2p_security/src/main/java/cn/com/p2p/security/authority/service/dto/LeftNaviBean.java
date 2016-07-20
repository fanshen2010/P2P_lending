package cn.com.p2p.security.authority.service.dto;

import java.util.List;

import cn.com.p2p.domain.user.entity.PfmMenu;

public class LeftNaviBean {
	String menuId;
	String menuName;
	String menuIcon;
	String menuClass;
	String menuUrl;
	String status;
	
	List<PfmMenu> childs = null;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuClass() {
		return menuClass;
	}
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PfmMenu> getChilds() {
		return childs;
	}
	public void setChilds(List<PfmMenu> childs) {
		this.childs = childs;
	}

	
}
