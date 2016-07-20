package cn.com.p2p.usermangent.service;

import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;
import cn.com.p2p.usermangent.service.dto.PfmTenantDepartmentDto;

public interface PfmTenantDepartmentManageService {
	
	/**
	 * 
	  * 
	  * <p> 组织机构列表页* </p>.<br> 说明：查询所有的组织机构
	  * 
	  * author：<br> 
	  * ===================================
	  * @param criteria 查询条件类
	  * @return 返回 PfmTenantDepartment 集合
	  *
	 */
	public List<PfmTenantDepartment> findDepartmentPage(PfmTenantDepartmentCriteria criteria);
	/**
	 * 
	 * 
	 * <p> 组织机构列表* </p>.<br> 说明：查询所有的组织机构
	 * 
	 * author：<br> 
	 * ===================================
	 * @param criteria 查询条件类
	 * @return 返回 PfmTenantDepartment 集合
	 *
	 */
	public List<PfmTenantDepartment> findDepartmentAll(PfmTenantDepartmentCriteria criteria);
	
	/**
	 * 根据部门Cd获取部门详情信息
	 * @author 
	 * 
	 * @param departmentCd 部门Cd
	 * @return PfmTenantDepartmentInfo 部门详情信息
	 */
	public PfmTenantDepartmentInfo getDepartmentInfoByCd(String departmentCd);
	
	
	/**
	 * 
	  * 
	  * <p> 添加组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param pfmTenantDepartment 商户部门
	  * @param pfmTenantDepartmentInfo 商户部门信息
	  * @param repayWay 还款方式
	  * @param departType 组织机构类型
	  * @param pic 上传文件ID
	  * 
	  *
	 */
	public int addPfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment,PfmTenantDepartmentInfo pfmTenantDepartmentInfo,String departType,String reapayWay,String pic);
	
	
	/**
	 * 
	  * 
	  * <p> 更新组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param pfmTenantDepartment 商户部门类
	  * @param pfmTenantDepartmentInfo 商户部门信息类
	  *
	 */
	public void updatePfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment,PfmTenantDepartmentInfo pfmTenantDepartmentInfo,String departType);
	
	
	/**
	 * 
	  * 
	  * <p> 删除组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param departmentCd 部门CD
	  * @param type 部门类型
	  *
	 */
	public int deletePfmTenantDepartment(String departmentCd,String type,String tenantCd);
	
	
	/**
	 * 
	  * 
	  * <p> 查询组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param departCd 部门CD
	  * @param departType 部门类型
	  * @return PfmTenantDepartmentDto 
	  *
	 */
	public PfmTenantDepartmentDto findPfmTenantDepartmentBydepartCd(String departCd,String departType);


	/**
     * 获取组织机构DropdownList值
     * @author 
     * 
     * @return Map<String,String> 组织机构DropdownList Map
     */
	public Map<String, String> getDepartmentDDL();
	
	/**
	 * 
	 * 
	 * <p> 组织机构全体查询 </p>
	 * 
	 * @return 返回 PfmTenantDepartment 集合
	 *
	 */
	public List<PfmTenantDepartment> findDepartmentByTenant(String tenantCd);


	public List<PfmTenantDepartment> getDepartmentByName(String name, String tenantCd);
	
	/**
	 * 
	  * 
	  * <p> 校验组织机构名称是否重复* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param departName 组织机构名称
	  * @param department 组织机构实体
	  *
	 */
	public boolean checkDepartmentName(String departName,PfmTenantDepartment department);
	
	/**
	 * 组织机构名称重复校验
	 * @author 
	 * @param String departmentName 组织机构名称
	 * @param departmenCd 当前部门CD
	 * 
	 * @return boolean 验证结果(当前名称存在返回 true)
	 */
	public boolean departmentNameCheck(String departmentName,String departmentCd);

}
