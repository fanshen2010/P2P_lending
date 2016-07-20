package cn.com.p2p.mgr.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.p2p.framework.util.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.businessmanagentsys.service.SysMenuMangentService;
import cn.com.p2p.businessmanagentsys.service.SysRoleMangentService;
import cn.com.p2p.businessmanagentsys.service.SysRoleMenuManagentService;
import cn.com.p2p.domain.user.criteria.PfmRoleCriteria;
import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.utils.Constants;

@Namespace("/system/role")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = BaseAction.SAVE, location = "index.htm", type = "redirect"),
		@Result(name =  BaseAction.UPDATE, location = "index.htm", type = "redirect"),
		@Result(name = "permissionSet", location = "permissionSet.ftl", type = "freemarker"),
		@Result(name = "dataPermissionSet", location = "index.htm", type = "redirect")})
public class SysRoleAction extends BaseAction{

	/**
	 * @author 
	 */
	private static final long serialVersionUID = 1L;

	/** 系统角色管理接口*/
	@Autowired
	private SysRoleMangentService sysRoleMangentService;
	/** 系统菜单管理接口*/
	@Autowired
	private SysMenuMangentService sysMenuMangentService;
	/** 系统角色菜单接口*/
	@Autowired
	private SysRoleMenuManagentService sysRoleMenuManagentService;
	@Autowired
	private FeroFreemarkerProcessor feroFreemarkerProcessor;

	/** 角色集合*/
	private List<PfmRole> lstRole = new ArrayList<PfmRole>();
	/** 角色对象*/
	private PfmRole role;
	/** 角色名称*/
	private String roleName;
	/** 角色ID*/
	private String roleId;
	/** 角色条件类*/
	private PfmRoleCriteria criteria = new PfmRoleCriteria();

	/** 菜单ID*/
	private String[] menuId;
	/** 返回的权限MAP*/
	private Map<String,Object> menuMap;

	/**
	 * 系统角色——列表
	 */
	@Action(value="index")
	@Override
	public String init() throws Exception {
		criteria.setRoleName(roleName, Operator.like);
		criteria.setOrderBy(cn.com.p2p.domain.user.criteria.PfmRoleCriteria.OrderField.createTime);
		lstRole = sysRoleMangentService.findPageRoleAll(criteria);
		return INIT;
	}

	/**
	 * 角色管理——添加
	 */
	@Action(value="save")
	public String save() throws Exception {
        role.setCreateTime(DateUtils.getCurrentDateTime());
        role.setUpdateTime(DateUtils.getCurrentDateTime());
        int result = sysRoleMangentService.saveRole(role);
        if (result > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("删除成功！");
        }
        return SAVE;
    }

    /**
	 * 角色管理——修改
	 */
	@Action(value="edit")
	public void edit() throws Exception{
		role = sysRoleMangentService.findPfmRoleByRoleId(roleId);
		Map <String,Object> map = new HashMap<String, Object>();
		String result = feroFreemarkerProcessor.process(Constants.SYSTEM_ROLE_EDIT, map, this);
		map.put("html", result);
		Struts2Utils.renderJson(map);
	}

	/**
	 * 角色管理——更新
	 */
	@Action(value="update")
	public String update() throws Exception {
        role.setUpdateTime(DateUtils.getCurrentDateTime());
        int result = sysRoleMangentService.updateRole(role);
        if (result > 0) {
            System.out.println("更新成功！");
        } else {
            System.out.println("更新成功！");
        }
        return UPDATE;
    }

    /**
	 * 角色管理——删除
	 */
	@Action(value="delete")
	public void delete() throws Exception{
		boolean result = sysRoleMangentService.deleteRole(roleId);
		if(result){
			this.delSuccess();
		}else{
			this.delFailure();
		}
	}

	/**
	 *角色管理——权限设置
	 */
	@Action(value = "permissionSet")
	public String permissionSet() throws Exception{
		menuMap = sysMenuMangentService.findPfmMenuByroleId(roleId);
		return "permissionSet";
	}

	/**
	 * 角色管理——权限设置保存
	 */
	@Action(value = "dataPermissionSet")
	public String dataPermissionSet() throws Exception{
		boolean result = sysRoleMenuManagentService.saveRoleMenu(menuId, roleId);
		if(result){
			System.out.println("权限更新成功！");
		}else{
			System.out.println("权限更新成功！");
		}
		return "dataPermissionSet";
	}

	/**
	 * 角色管理——角色名称唯一校验
	 */
	@Action(value="checkRoleName")
	public void checkRoleName() throws Exception{
		String roleName = this.getAjaxMap().get("role.roleName").toString();
		boolean result = sysRoleMangentService.checkRoleName(roleName, role);
		if(result){
			this.ajaxCheckSuccess();
		}else{
			this.ajaxCheckFailure();
		}
	}


	public List<PfmRole> getLstRole() {
		return lstRole;
	}


	public void setLstRole(List<PfmRole> lstRole) {
		this.lstRole = lstRole;
	}


	public PfmRole getRole() {
		return role;
	}


	public void setRole(PfmRole role) {
		this.role = role;
	}


	public PfmRoleCriteria getCriteria() {
		return criteria;
	}


	public void setCriteria(PfmRoleCriteria criteria) {
		this.criteria = criteria;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String[] getMenuId() {
		return menuId;
	}

	public void setMenuId(String[] menuId) {
		this.menuId = menuId;
	}

	public Map<String,Object> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String,Object> menuMap) {
		this.menuMap = menuMap;
	}

}
