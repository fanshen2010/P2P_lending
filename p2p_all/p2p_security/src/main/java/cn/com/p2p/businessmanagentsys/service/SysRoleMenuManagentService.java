package cn.com.p2p.businessmanagentsys.service;

import java.util.List;

import cn.com.p2p.domain.user.criteria.PfmRoleMenuCriteria;
import cn.com.p2p.domain.user.entity.PfmRoleMenu;

/**
 * 系统角色和菜单关联的管理接口
 * @author wangxiangshun
 *
 */
public interface SysRoleMenuManagentService {
	
	/**
	 * 
		 * 保存角色菜单信息
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param criteria
	 */
	public boolean saveRoleMenu(String[] menuIds,String roleId);
	
	/**
	 * 
		 * 根据条件查询出角色菜单信息
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param criteria
	 */
	public List<PfmRoleMenu> findPfmRoleMenuByConditions(PfmRoleMenuCriteria criteria);
	
	
	
	
	
}
