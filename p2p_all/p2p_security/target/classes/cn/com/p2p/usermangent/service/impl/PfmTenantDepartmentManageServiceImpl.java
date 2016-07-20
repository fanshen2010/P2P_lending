package cn.com.p2p.usermangent.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentCriteria;
import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.repository.PfmTenantDepartmentInfoRepository;
import cn.com.p2p.domain.user.repository.PfmTenantDepartmentRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.DepartmentTypeEnum;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.PfmTenantDepartmentManageService;
import cn.com.p2p.usermangent.service.dto.PfmTenantDepartmentDto;
@Service
public class PfmTenantDepartmentManageServiceImpl implements PfmTenantDepartmentManageService{

	
	@Autowired
	private PfmTenantDepartmentRepository pfmTenantDepartmentRepository;

	@Autowired
	private PfmTenantDepartmentInfoRepository pfmTenantDepartmentInfoRepository;
	
	@Autowired
	private BusinessStaffService businessStaffService;
	
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
	@Override
	public List<PfmTenantDepartment> findDepartmentPage(
			PfmTenantDepartmentCriteria criteria) {
		//查询部门表所有数据
		return pfmTenantDepartmentRepository.findPageByCriteria(criteria);
	}
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
	@Override
	public List<PfmTenantDepartment> findDepartmentAll(
			PfmTenantDepartmentCriteria criteria) {
		//查询部门表所有数据
		return pfmTenantDepartmentRepository.findByCriteria(criteria);
	}

	
	
	

	/**
	 * 
	  * 
	  * <p> 添加组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param pfmTenantDepartment 商户部门类
	  * @param pfmTenantDepartmentInfo 商户部门信息类
	  * @param repayWay 还款方式
	  *
	 */
	@Override
	public int addPfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment,PfmTenantDepartmentInfo pfmTenantDepartmentInfo,String departType,String reapayWay,String pic) {
		
			//名称check
			if(departmentNameCheck(pfmTenantDepartment.getDepartmentName(),pfmTenantDepartment.getDepartmentCd())){
				return 0;	//当前名称存在则返回插入失败
			}
		
			//设置部门的 部门CD
			pfmTenantDepartment.setDepartmentCd(StringUtils.getUUID());
			//设置部门的 创建时间
			pfmTenantDepartment.setCreateTime(new Date());
			//设置部门的 更新时间
			pfmTenantDepartment.setUpdateTime(new Date());
			//设置部门的 创建者
			pfmTenantDepartment.setCreateUserId("当前登录人ID");
			//设置部门的 更新者
			pfmTenantDepartment.setUpdateUserId("当前登录人ID");
			//设置部门的 版本
			pfmTenantDepartment.setVersion(1);
			//添加数据
			 int result = pfmTenantDepartmentRepository.insert(pfmTenantDepartment);
			
			//当部门类型是 "担保公司"的时候，会添加部门信息表数据
		if(departType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			//设置部门信息的 部门CD
			pfmTenantDepartmentInfo.setDepartmentCd(pfmTenantDepartment.getDepartmentCd());
			//设置部门信息的 创建时间
			pfmTenantDepartmentInfo.setCreateTime(new Date());
			//设置部门信息的 更新时间
			pfmTenantDepartmentInfo.setUpdateTime(new Date());
			//设置部门信息的 创建者
			pfmTenantDepartmentInfo.setCreateUserId("当前登录人ID");
			//设置部门信息的 更新者
			pfmTenantDepartmentInfo.setUpdateUserId("当前登录人ID");
			//设置部门信息的 版本
			pfmTenantDepartmentInfo.setVersion(0);
			//添加数据
	        int resultCount = pfmTenantDepartmentInfoRepository.insert(pfmTenantDepartmentInfo);
	        
		}
		
		return result;
		
	}
	
	/**
	 * 组织机构名称重复校验
	 * @author 
	 * @param String departmentName 组织机构名称
	 * @param departmenCd 当前部门CD
	 * 
	 * @return boolean 验证结果(当前名称存在返回 true)
	 */
	@Override
	public boolean departmentNameCheck(String departmentName,String departmentCd){
		
		boolean checkFlag = false;
		
		PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
		criteria.setDepartmentName(departmentName, Operator.equal);
		List<PfmTenantDepartment> pfmTenantDepartmentList = pfmTenantDepartmentRepository.findByCriteria(criteria);

		//如果为修改，则过滤点当前数据
		if(pfmTenantDepartmentList != null && !pfmTenantDepartmentList.isEmpty()){
			
			for (PfmTenantDepartment pfmTenantDepartment : pfmTenantDepartmentList) {
				//用户名存在则返回true
				if(!StringUtils.equals(pfmTenantDepartment.getDepartmentCd(), departmentCd)){
					checkFlag = true;
					break;
				}
			}
			
		}
		
		return checkFlag;
	}

	
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
	@Override
	public void updatePfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment,PfmTenantDepartmentInfo pfmTenantDepartmentInfo,String departType) {
		//动态更新部门表
	    pfmTenantDepartmentRepository.dynamicUpdate(pfmTenantDepartment);
		//当部门类型是 "担保公司"的 时候 更新部门信息表
		if(departType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			pfmTenantDepartmentInfoRepository.dynamicUpdate(pfmTenantDepartmentInfo);
		}
	}

	
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
	@Override
	public int deletePfmTenantDepartment(String departmentCd, String type,String tenantCd) {
		//返回值
		int result = 0;
		//部门信息操作返回受影响行数
		int resultCount = 0;
		//根据部门CD 删除部门表信息
		int resultNum = pfmTenantDepartmentRepository.delete(tenantCd,departmentCd);
		
		//当部门类型是 "担保公司"的时候，连同部门信息表一起删除
		if(type.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			resultCount = pfmTenantDepartmentInfoRepository.delete(tenantCd,departmentCd);
		}
		
		//把部门下的所有用户删除
		PfmUserCriteria criteria = new PfmUserCriteria();
		criteria.setDepartCd(departmentCd, Operator.equal);
		List<PfmUser> lstPfmUser = businessStaffService.getPfmUserByCriteria(criteria);
		for(int i=0;i<lstPfmUser.size();i++){
			PfmUser pfmUser = new PfmUser();
			pfmUser.setDepartCd("");
			pfmUser.setId(lstPfmUser.get(i).getId());
		    businessStaffService.dynamicUpdatePfmUser(pfmUser);
		}
		
		if(type.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_1.getCode())||type.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_2.getCode())){
			if(resultNum>0){
				result = 1;
			}
		}else{
			if(resultCount>0&&resultNum>0){
				result = 1;
			}
		}
		
		return result;
	}

	
	/**
	 * 
	  * 
	  * <p> 查询组织机构* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param departmentCd 部门CD
	  * @param type 部门类型
	  * @return  PfmTenantDepartmentDto
	  *
	 */
	@Override
	public PfmTenantDepartmentDto findPfmTenantDepartmentBydepartCd(String departCd, String departType) {
		PfmTenantDepartmentDto pfmTenantDepartmentDto = new PfmTenantDepartmentDto();
		//根据部门CD 查询部门表
		PfmTenantDepartment pfmTenantDepartment = pfmTenantDepartmentRepository.findOne(departCd);
		pfmTenantDepartmentDto.setPfmTenantDepartment(pfmTenantDepartment);
		
		//当部门类型是 "担保公司" 的时候，把部门信息表一起查出来
		if(departType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			//根据部门CD 查询部门信息表数据 
			PfmTenantDepartmentInfo pfmTenantDepartmentInfo = pfmTenantDepartmentInfoRepository.findOne(departCd);
			pfmTenantDepartmentDto.setPfmTenantDepartmentInfo(pfmTenantDepartmentInfo);
		}
		
		return pfmTenantDepartmentDto;
	}
	
	
	 /**
     * 获取组织机构DropdownList值
     * @author 
     * 
     * @return Map<String,String> 组织机构DropdownList Map
     */
    @Override
    public Map<String,String> getDepartmentDDL(){
    	
    	Map<String,String> ddlMap = new HashMap<String, String>();
    	
    	PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
    	criteria.setVilidFlag("1", Operator.equal);	//只查询启用状态的组织机构
    	
    	List<PfmTenantDepartment> departmentList = pfmTenantDepartmentRepository.findByCriteria(criteria);
    	
    	if(departmentList != null && !departmentList.isEmpty()){
    		
    		for (PfmTenantDepartment pfmTenantDepartment : departmentList) {
				
    			if(pfmTenantDepartment != null){
    				ddlMap.put(pfmTenantDepartment.getDepartmentCd(), pfmTenantDepartment.getDepartmentName());
    			}
			}
    	}
    	return ddlMap;
    }
	

	@Override
	public List<PfmTenantDepartment> findDepartmentByTenant(String tenantCd) {
		PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
		criteria.setTenantCd(tenantCd, Operator.equal);
		criteria.setVilidFlag("1", Operator.equal);
		return pfmTenantDepartmentRepository.findByCriteria(criteria);
	}
	
	@Override
	public List<PfmTenantDepartment> getDepartmentByName(String name, String tenantCd) {
		PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
		criteria.setDepartmentName(name, Operator.equal);
		criteria.setTenantCd(tenantCd, Operator.equal);
		criteria.setVilidFlag("1", Operator.equal);
		return pfmTenantDepartmentRepository.findByCriteria(criteria);
	}


	@Override
	public boolean checkDepartmentName(String departName,
			PfmTenantDepartment department) {
		 boolean flag = true;
	        
	        List<PfmTenantDepartment> lstDepartment = null;
	        if(StringUtils.isNotEmpty(departName)){    // 此处防止查询全部数据，造成不必要的资源浪费
	        	PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
	            criteria.setDepartmentName(departName,  Operator.equal);
	            // 查询记录
	            lstDepartment = pfmTenantDepartmentRepository.findByCriteria(criteria);
	        }
	        
	        // 查询数据库获取当前修改的职位名称
	        PfmTenantDepartment departments = pfmTenantDepartmentRepository.findOne(department.getDepartmentCd());
	        // 与用户修改后的职位名称进行比较，如果相同则清空查询到的数据，不给予验证
	        if(departments != null && departName.equals(departments.getDepartmentName())){
	        	lstDepartment.clear();
	        }
	        if(lstDepartment != null && !lstDepartment.isEmpty()){  // 如果结果不为空则应被占用
	            flag = false;
	        }
		return flag;
	}
	
	/**
	 * 根据部门Cd获取部门详情信息
	 * @author 
	 * 
	 * @param departmentCd 部门Cd
	 * @return PfmTenantDepartmentInfo 部门详情信息
	 */
	@Override
	public PfmTenantDepartmentInfo getDepartmentInfoByCd(String departmentCd){
		return pfmTenantDepartmentInfoRepository.findOne(departmentCd);
	}
}
