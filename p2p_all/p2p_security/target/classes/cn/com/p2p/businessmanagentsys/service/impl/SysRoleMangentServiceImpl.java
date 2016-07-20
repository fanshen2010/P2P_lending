package cn.com.p2p.businessmanagentsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.businessmanagentsys.service.SysRoleMangentService;
import cn.com.p2p.domain.user.criteria.PfmRoleCriteria;
import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.repository.PfmPostRoleRepository;
import cn.com.p2p.domain.user.repository.PfmRoleRepository;
import cn.com.p2p.framework.constant.Constants;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;

/**
 * 系统角色管理的接口实现
 * @author wangxiangshun
 *
 */
@Service
public class SysRoleMangentServiceImpl implements SysRoleMangentService{
	
	@Autowired
	private PfmRoleRepository pfmRoleRepository;
	
	@Autowired
	private PfmPostRoleRepository pfmPostRoleRepository;

	/**
	 *系统角色管理查询
	 *@author wangxiangshun
	 */
	@Override
	public List<PfmRole> findPageRoleAll(PfmRoleCriteria criteria) {
		return pfmRoleRepository.findPageByCriteria(criteria);
	}
	/**
	 *系统角色的保存
	 *@author wangxiangshun
	 */
	@Override
	public int saveRole(PfmRole pfmRole) {
		pfmRole.setRoleId(StringUtils.getUUID());
		pfmRole.setTenantCd(Constants.PLAT_TENANT_CD);
		return pfmRoleRepository.insert(pfmRole);
	}
	/**
	 * 系统角色的删除
	 * @author wangxiangshun
	 */
	@Override
	public boolean deleteRole(String roleId) {
		int num = pfmRoleRepository.delete(roleId);
		pfmPostRoleRepository.deleteByRoleId(roleId);
		if(num > 0){//返回1，删除成功
			return true;
		}
		return false;
	}
	/**
	 * 根据角色ID得到角色信息
	 * @author wangxiangshun
	 */
	@Override
	public PfmRole findPfmRoleByRoleId(String roleId) {
		PfmRole pfmRole = pfmRoleRepository.findOne(roleId);
		return pfmRole;
	}
	/**
	 * 系统角色信息的修改
	 */
	@Override
	public int updateRole(PfmRole pfmRole) {
		
		return pfmRoleRepository.dynamicUpdate(pfmRole);
	}

	/**
	 * 系统角色的禁用和启用
	 */
	@Override
	public boolean stateRole(PfmRole pfmRole) {
		int num = pfmRoleRepository.dynamicUpdate(pfmRole);
		if(num == 1){//返回1，修改成功
			return true;
		}
		return false;
	}

	@Override
	public List<PfmRole> findAllRole() {
		PfmRoleCriteria roleCrit = new PfmRoleCriteria();
		roleCrit.setRoleState(1, Operator.equal);
		return pfmRoleRepository.findByCriteria(roleCrit);
	}
	
	@Override
	public List<PfmRole> getRoleByName(String name) {
		PfmRoleCriteria roleCrit = new PfmRoleCriteria();
		roleCrit.setRoleState(1, Operator.equal);
		roleCrit.setRoleName(name, Operator.equal);
		return pfmRoleRepository.findByCriteria(roleCrit);
	}
	@Override
	public boolean checkRoleName(String roleName, PfmRole role) {
		boolean result = true;
		List<PfmRole> lstRole = null;
		  if(StringUtils.isNotEmpty(roleName)){
			  PfmRoleCriteria criteria = new PfmRoleCriteria();
			  criteria.setRoleName(roleName, Operator.equal);
			  lstRole = pfmRoleRepository.findByCriteria(criteria);
		  }
		  
		  PfmRole roles = pfmRoleRepository.findOne(role.getRoleId());
		  if(roles != null && roleName.equals(roles.getRoleName())){
			  lstRole.clear();
	        }
	        if(lstRole != null && !lstRole.isEmpty()){  // 如果结果不为空则应被占用
	        	result = false;
	        }
		return result;
	}	

}
