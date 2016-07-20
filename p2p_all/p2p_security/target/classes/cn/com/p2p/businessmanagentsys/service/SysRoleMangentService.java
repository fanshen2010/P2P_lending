package cn.com.p2p.businessmanagentsys.service;

import java.util.List;

import cn.com.p2p.domain.user.criteria.PfmRoleCriteria;
import cn.com.p2p.domain.user.entity.PfmRole;

/**
 * 系统角色管理接口
 * @author wangxiangshun
 *
 */

public interface SysRoleMangentService {
	
	/**
	 * 
		 * 系统角色管理查询Page
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param criteria
	 */
	public List<PfmRole> findPageRoleAll(PfmRoleCriteria criteria);
	
	/**
	 * 
		 * 系统角色保存
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param pfmRole
	 */
	public int saveRole(PfmRole pfmRole);
	
	/**
	 * 
		 * 系统角色删除
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param roleId
	 */
	public boolean deleteRole(String roleId);
	
	/**
	 * 
		 * 根据角色ID得到角色信息
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param roleId
	 */
	public PfmRole findPfmRoleByRoleId(String roleId);
	
	/**
	 * 
		 * 系统角色信息的修改
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param roleId
	 */
	public int updateRole(PfmRole pfmRole);
	/**
	 * 
		 * 系统角色的禁用和启用
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param roleId
	 */
	public boolean stateRole(PfmRole pfmRole);
	
	public List<PfmRole> findAllRole();

	public List<PfmRole> getRoleByName(String name);
	
	/**
	 * 
	  * 
	  * <p> 角色名称唯一校验* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param 
	  * @param 
	  * @param 
	  *
	 */
	public boolean checkRoleName(String roleName,PfmRole role);

}
