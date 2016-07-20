package cn.com.p2p.businessmanagentsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.businessmanagentsys.service.SysRoleMenuManagentService;
import cn.com.p2p.domain.user.criteria.PfmRoleMenuCriteria;
import cn.com.p2p.domain.user.entity.PfmRoleMenu;
import cn.com.p2p.domain.user.repository.PfmRoleMenuRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.security.control.back.MySecurityMetaDataSourceAccessHandler;
/**
 * 系统角色菜单管理接口实现
 * @author wangxiangshun
 *
 */
@Service
public class SysRoleMenuManagentServiceImpl implements SysRoleMenuManagentService{
	/**
	 * 菜单角色关联数据组件
	 */
	@Autowired
	private PfmRoleMenuRepository pfmRoleMenuRepository;
	
	@Autowired
	private MySecurityMetaDataSourceAccessHandler securityMetaDataSource;
	
	/**
	 * 系统角色权限的设置
	 */
	@Override
	public boolean saveRoleMenu(String[] menuIds,String roleId) {
		PfmRoleMenuCriteria criteria = new PfmRoleMenuCriteria();
		criteria.setRoleId(roleId, Operator.equal);
		List<PfmRoleMenu> pfmRoleMenuList = pfmRoleMenuRepository.findByCriteria(criteria);
		if(pfmRoleMenuList!=null){
			for(int j = 0 ; j < pfmRoleMenuList.size() ; j++){
				pfmRoleMenuRepository.delete(pfmRoleMenuList.get(j).getId());
			}
		}
		int num = 0;
		if(menuIds!=null&&menuIds.length!=0){
			for(int i = 0 ; i < menuIds.length ; i++){
				PfmRoleMenu pfmRoleMenu = new PfmRoleMenu();
				pfmRoleMenu.setId(StringUtils.getUUID());
				pfmRoleMenu.setMenuId(menuIds[i]);
				pfmRoleMenu.setRoleId(roleId);
				num = pfmRoleMenuRepository.insert(pfmRoleMenu);
				if(num==0){
					break;
				}
			}
		}else{
			num = 1;
		}
		
		securityMetaDataSource.refresh();
		if(num==1){//插入成功
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 系统角色权限的查询
	 */
	@Override
	public List<PfmRoleMenu> findPfmRoleMenuByConditions(
			PfmRoleMenuCriteria criteria) {
		return pfmRoleMenuRepository.findByCriteria(criteria);
	}

}
