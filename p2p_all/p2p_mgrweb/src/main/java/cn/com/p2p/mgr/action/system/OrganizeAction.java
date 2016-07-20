package cn.com.p2p.mgr.action.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentCriteria;
import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.DepartmentTypeEnum;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.system.service.UploadFileService;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.PfmTenantDepartmentManageService;
import cn.com.p2p.usermangent.service.dto.PfmTenantDepartmentDto;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;
import cn.com.p2p.utils.UploadUtils;


@Namespace("/system/organize")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = BaseAction.NEWS, location = "new_index.ftl", type = "freemarker"),
		@Result(name = BaseAction.DELETE, location = "index.htm", type = "redirect"),
		@Result(name = BaseAction.SAVE, location = "index.htm", type = "redirect"),
		@Result(name = "depart_edit", location = "depart/depart_edit.ftl", type = "freemarker"),
		@Result(name = "branch_edit", location = "branch/branch_edit.ftl", type = "freemarker"),
		@Result(name = "guarantee_edit", location = "guarantee/guarantee_edit.ftl", type = "freemarker"),
		@Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect"),
		@Result(name = "setUser", location = "setUser.ftl", type = "freemarker"),
		@Result(name = "remove", location = "setUser.htm", type = "redirect",params={"departCd","${departCd}"}),
		@Result(name = "set_add", location = "setUser.htm", type = "redirect",params={"departCd","${departCd}"})
		})
public class OrganizeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 部门接口*/
	@Autowired
	private PfmTenantDepartmentManageService departmentManageService;
	/** 上传接口*/
	@Autowired
	private UploadFileService uploadFileService;
	/** 用户接口*/
	@Autowired
	private BusinessStaffService businessStaffService;
	
	/** 部门集合*/
	private List<PfmTenantDepartment> lstResult = new ArrayList<PfmTenantDepartment>();
	/** 部门查询条件*/
	private PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
	/** 部门下面的人员*/
	private List<PfmUserDto> lstPfmUser = new ArrayList<PfmUserDto>();
	/** 没有组织机构的人员*/
	private List<PfmUser> lstName = new ArrayList<PfmUser>();
	/** 查询条件  组织机构名称*/
	private String departmentName;
	/** 查询条件 组织机构类型*/
	private String departmentType;
	/** 部门 */
	private PfmTenantDepartment pfmTenantDepartment;
	/** 部门信息*/
	private PfmTenantDepartmentInfo pfmTenantDepartmentInfo;
	/** 上传实体*/
	private List<UploadFile> uploadFiles;
	/** 部门CD*/
	private String departCd;
	/** 商户CD*/
	private String tenantCd;
	/** 成员姓名*/
	private String realName;
	/** 用户ID*/
	private String userId;
	/** 部门设置成员条件*/
	private String departmentCd;
	/** 成员ID数组*/
	private String ck[];
	
	/** 上传文件 */
	private UploadFile uploadFile;
	

	@Action(value="index")
	@Override
	public String init() throws Exception {
		criteria.setDepartmentName(departmentName, Operator.like);
		criteria.setDepartmentType(departmentType, Operator.equal);
		lstResult = departmentManageService.findDepartmentPage(criteria);
		return INIT;
	}
	
	@Action(value="new_index")
	public String new_index() throws Exception{
		return NEWS;
	}
	
	@Action(value="save")
	public String save() throws Exception{
		int result = 0;
		String departType = pfmTenantDepartment.getDepartmentType();
		if(departType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_2.getCode())||departType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_1.getCode())){
			pfmTenantDepartment.setTenantCd(getLoginuser().getCompanyId());
			result = departmentManageService.addPfmTenantDepartment(pfmTenantDepartment,null,departType,null,null);
		}else{
		    /* author: zhushanyu date: 2015-06-04 17:01 增加电子签章     BEGUN      */
			uploadFile = UploadUtils.upload(file, fileFileName, fileContentType);
			uploadFileService.insertUploadFile(uploadFile);
			/* author: zhushanyu date: 2015-06-04 17:01 增加电子签章     END      */
			uploadFileService.qualificationUploadFile(uploadFiles);
			String pic =JsonPluginsUtil.beanListToJson(uploadFiles);
			
			pfmTenantDepartment.setTenantCd(getLoginuser().getCompanyId());
			pfmTenantDepartmentInfo.setTenantCd(getLoginuser().getCompanyId());
			result = departmentManageService.addPfmTenantDepartment(pfmTenantDepartment,pfmTenantDepartmentInfo,departType,null,pic);
		}
		
		if(result > 0){
			System.out.println("添加成功！");
		}
		return SAVE;
	}
	
	/**
	 *  组织机构名称校验
	 */
	@Action(value="departmentNameCheck")
	public void departmentNameCheck(){
		
		String retCheck = "true";	//默认验证通过
		
		//判断当前用户名是否存在
		if(departmentManageService.departmentNameCheck(pfmTenantDepartment.getDepartmentName(),pfmTenantDepartment.getDepartmentCd())){
			//存在则验证不通过
			retCheck = "false";
		}
		
		Struts2Utils.renderText(retCheck);
	}
	
	@Action(value="delete")
	public void delete() throws Exception{
		int resultCount = 0;
		int result = departmentManageService.deletePfmTenantDepartment(departCd, departmentType, tenantCd);
		if(departmentType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			//resultCount = payAccountService.deletePayAccount(departCd);
		}
		if(departmentType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
			if(result > 0 && resultCount > 0){
				this.delSuccess();
			}else{
				this.delFailure();
			}
		}else{
			if(result > 0){
				this.delSuccess();
			}else{
				this.delFailure();
			}
		}
	}
	
	
	@Action(value="edit")
	public String edit() throws Exception{
		PfmTenantDepartmentDto departmentDto = departmentManageService.findPfmTenantDepartmentBydepartCd(departCd, departmentType);
		pfmTenantDepartment = departmentDto.getPfmTenantDepartment();
		if(departmentType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_1.getCode())){
			//部门
			return "depart_edit";
		}else if(departmentType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_2.getCode())){
			//组织机构
			return "branch_edit";
		}else{
			//部门信息
			pfmTenantDepartmentInfo = departmentDto.getPfmTenantDepartmentInfo();
			//担保公司
			return "guarantee_edit";
		}
	}
	
	
	@Action(value="update")
	public String update() throws Exception{
		
		if(departmentType.equals(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode())){
	        departmentManageService.updatePfmTenantDepartment(pfmTenantDepartment, pfmTenantDepartmentInfo,departmentType);
		}else{
			departmentManageService.updatePfmTenantDepartment(pfmTenantDepartment, null,departmentType);
		}
		return UPDATE;
	}
	
	@Action(value="setUser")
	public String setUser() throws Exception{
		PfmUserCriteria userCriteria = new PfmUserCriteria();
		userCriteria.setDepartCd(departCd,Operator.equal);
		lstPfmUser = businessStaffService.getPfmUserDtoPageByCriteria(userCriteria);

		lstName = businessStaffService.findPfmUserByRealName(realName);
		return "setUser";
	}
	
	@Action(value="remove")
	public String remove() throws Exception{
		PfmUser pfmUser = new PfmUser();
		pfmUser.setDepartCd("");
		pfmUser.setId(userId);
	    int result = businessStaffService.dynamicUpdatePfmUser(pfmUser);
	    departCd=departmentCd;
	    if(result > 0){
	    	System.out.println("aaa");
	    }else{
	    	System.out.println("bbb");
	    }
		return "remove";
	}
	
	@Action(value="set_add")
	public String set_add() throws Exception{
		if(ck!=null&&ck.length>0){
			for(int i=0;i<ck.length;i++){
				PfmUser pfmUser = new PfmUser();
				pfmUser.setDepartCd(departCd);
				pfmUser.setId(ck[i]);
			    businessStaffService.dynamicUpdatePfmUser(pfmUser);
			}
		}
		return "set_add";
	}
	
	@Action(value="checkName")
	public void checkProductName() throws Exception{
		String departmentName = this.getAjaxMap().get("pfmTenantDepartment.departmentName").toString();
		boolean result =departmentManageService.checkDepartmentName(departmentName,pfmTenantDepartment);
		if(result){
			this.ajaxCheckSuccess();
		}else{
			this.ajaxCheckFailure();
		}
	}
	

	public List<PfmTenantDepartment> getLstResult() {
		return lstResult;
	}

	public void setLstResult(List<PfmTenantDepartment> lstResult) {
		this.lstResult = lstResult;
	}

	public PfmTenantDepartmentCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(PfmTenantDepartmentCriteria criteria) {
		this.criteria = criteria;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public PfmTenantDepartment getPfmTenantDepartment() {
		return pfmTenantDepartment;
	}

	public void setPfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment) {
		this.pfmTenantDepartment = pfmTenantDepartment;
	}

	public PfmTenantDepartmentInfo getPfmTenantDepartmentInfo() {
		return pfmTenantDepartmentInfo;
	}

	public void setPfmTenantDepartmentInfo(PfmTenantDepartmentInfo pfmTenantDepartmentInfo) {
		this.pfmTenantDepartmentInfo = pfmTenantDepartmentInfo;
	}

	public String getDepartCd() {
		return departCd;
	}

	public void setDepartCd(String departCd) {
		this.departCd = departCd;
	}

	public String getTenantCd() {
		return tenantCd;
	}

	public void setTenantCd(String tenantCd) {
		this.tenantCd = tenantCd;
	}

	public List<PfmUserDto> getLstPfmUser() {
		return lstPfmUser;
	}

	public void setLstPfmUser(List<PfmUserDto> lstPfmUser) {
		this.lstPfmUser = lstPfmUser;
	}

	public List<PfmUser> getLstName() {
		return lstName;
	}

	public void setLstName(List<PfmUser> lstName) {
		this.lstName = lstName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDepartmentCd() {
		return departmentCd;
	}

	public void setDepartmentCd(String departmentCd) {
		this.departmentCd = departmentCd;
	}

	public String[] getCk() {
		return ck;
	}

	public void setCk(String ck[]) {
		this.ck = ck;
	}

	public List<UploadFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}
