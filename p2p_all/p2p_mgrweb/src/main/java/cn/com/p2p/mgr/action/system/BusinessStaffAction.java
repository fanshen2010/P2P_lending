package cn.com.p2p.mgr.action.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.PfmTenantDepartmentManageService;
import cn.com.p2p.usermangent.service.PostService;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;
import cn.com.p2p.utils.Constants;

/**
 * 业务人员管理Action
 * 
 * @author 
 *
 */
@Namespace("/system/businessStaff")
@Results({
		@Result(name = "businessStaff", location = "businessStaff.ftl", type = "freemarker"),
		@Result(name = "personalview", location = "personalview.ftl", type = "freemarker"),})
public class BusinessStaffAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 当前ID */
	private String curId;
	
	/** 用户表实体 */
	private PfmUser pfmUser;
	
	private PfmUserDto pfmUserDto;
	
	/** 业务人员Dto列表 */
	private List<PfmUserDto> pfmUserDtoList;
	
	/** 页面检索用户名 */
	private String userName;
	
	/** 页面检索姓名 */
	private String realName;
	
	/** 页面检索职位 */
	private String postCd;
	/** 页面检索组织机构 */
	private String departCd;
	
	/** 页面检索状态 */
	private String active;
	
	/** 业务人员第一次输入密码 */
	private String pfmUserPassFir;

	/** 业务人员第二次输入密码 */
	private String pfmUserPassSec;
	
	/** 组织机构DropdownList */
	private Map<String,String> departMap;
	
	/** 职位DropdownList */
	private Map<String,String> postMap;
	
	private BigDecimal balance;
	
	/** 页面检索参数类 */
	PfmUserCriteria criteria = new PfmUserCriteria();
	
	/** 业务人员管理接口 */
	@Autowired
	private BusinessStaffService businessStaffService;
	
	/** 组织机构业务Service接口 */
	@Autowired
	private PfmTenantDepartmentManageService pfmTenantDepartmentManageService;
	
	/** 职位业务Service接口 */
	@Autowired
	private PostService postService;
	
	@Autowired
	protected FeroFreemarkerProcessor feroFreemarkerProcessor;

	@Autowired
	private PaymentService paymentService;

	@Override
	@Action(value="businessStaff")
	public String init() throws Exception {
		
		//获取组织机构DropdownList
		departMap = pfmTenantDepartmentManageService.getDepartmentDDL();
		//职位DropdownList
		postMap = postService.getPostDDL();
		
		//添加查询条件
		criteria.setUserName(userName, Operator.equal);
		criteria.setRealName(realName, Operator.like);
		criteria.setPostCd(postCd, Operator.equal);
		criteria.setDepartCd(departCd, Operator.equal);
		criteria.setActive(active, Operator.equal);
		
		pfmUserDtoList = businessStaffService.getPfmUserDtoPageByCriteria(criteria);
		
		return "businessStaff";
	}

	
	/**
	 * 个人中心-页面加载
	 */
	@Action(value="personal")
	public String viewPersonal() throws Exception{
		
		pfmUserDto = businessStaffService.findPfmUserInfo(this.getLoginuser().getId());
		balance = paymentService.ciccDoSearchBalanceByCustCode("001");
		return "personalview";
	}
	
	/**
	 * 个人中心-电话修改提交方法
	 */
	@Action(value="personalPhoneEditSubmit")
	public void personalPhoneEditSubmit() throws JSONException{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", businessStaffService.dynamicUpdatePfmUser(pfmUser));
		
		Struts2Utils.renderJson(retMap);
	
	}
	
	/**
	 * 个人中心-电子邮箱修改提交方法
	 */
	@Action(value="personalEmailEditSubmit")
	public void personalEmailEditSubmit() throws JSONException{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", businessStaffService.dynamicUpdatePfmUser(pfmUser));
		
		Struts2Utils.renderJson(retMap);
	}
	
	/**
	 * 个人中心-登录密码修改提交方法
	 */
	@Action(value="personalPassEditSubmit")
	public void personalPassEditSubmit() throws JSONException{
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		 if(!businessStaffService.pfmUserPassWordCheck(pfmUser.getUserName(), pfmUser.getPassword())){
			//原密码错误则返回错误信息，错误号为2
			retMap.put("retMes", "2");
		} else if(!StringUtils.compare(pfmUserPassFir, pfmUserPassSec)){	
			//两次输入密码不同则返回错误信息，错误号为3
			retMap.put("retMes", "3");
		} else {
			//密码校验通过进行密码更新
			pfmUser.setPassword(pfmUserPassFir);
			retMap.put("retMes", businessStaffService.dynamicUpdatePfmUser(pfmUser));	
		}
		
		Struts2Utils.renderJson(retMap);
	}
	
	/**
	 * 老密码校验
	 */
	@Action(value="passWordCheck")
	public void passWordCheck(){
		
		String retCheck = "false";
		//密码正确返回true
		if(businessStaffService.pfmUserPassWordCheck(pfmUser.getUserName(), pfmUser.getPassword())){
			retCheck = "true";
		}
		
		Struts2Utils.renderText(retCheck);
	}
	
	/**
	 * 添加数据提交方法
	 * @throws JSONException 
	 * 
	 */
	@Validators(str="businessStaffAddCheck",result = "businessStaff", param = "add")
	@Action(value="okSubmitAdd")
	public void okSubmitAdd() throws JSONException {
		
		pfmUser.setId(StringUtils.getUUID());
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", businessStaffService.insertPfmUser(pfmUser));
		
		Struts2Utils.renderJson(retMap);
	}
	
	/**
	 * 修改数据提交方法
	 * 
	 * @throws JSONException
	 */
	@Validators(str="businessStaffUpdateCheck",result = "businessStaff", param = "update")
	@Action(value="okSubmitEdit")
	public void okSubmitEdit() throws JSONException {
		
		Map<String,Object> retMap = new HashMap<String, Object>();
		
		retMap.put("retMes", businessStaffService.dynamicUpdatePfmUser(pfmUser));
		
		Struts2Utils.renderJson(retMap);
	}
	
	/**
	 * 用户名主键check
	 */
	@Action(value="userNameCheck")
	public void userNameCheck() throws JSONException{
		String retCheck = "true";	//默认验证通过
		
		//判断当前用户名是否存在
		if(businessStaffService.pfmUserNameCheck(pfmUser.getUserName())){
			//存在则验证不通过
			retCheck = "false";
		}
		
		Struts2Utils.renderText(retCheck);
	}
	
	/**
	 * 删除数据提交方法
	 * 
	 * @throws JSONException
	 */
	@Action(value="okSubmitDelete")
	public void okSubmitDelete() throws JSONException {
		
		businessStaffService.deletePfmUserById(curId);
		
		delSuccess();
	}
	
	/**
	 * 人员修改
	 */
	@Action(value="staffEdit")
	public void staffEdit() throws Exception{
		
		//获取组织机构DropdownList
		departMap = pfmTenantDepartmentManageService.getDepartmentDDL();
		//职位DropdownList
		postMap = postService.getPostDDL();
		
		pfmUser = businessStaffService.getPfmUserById(curId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 得到渲染好的模板内容
		String result = feroFreemarkerProcessor.process(Constants.SYSTEM_BUSINESSSTAFF_EDIT, map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}	
	
	/**
	 * 人员添加
	 */
	@Action(value="staffAdd")
	public void staffAdd() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
	    //获取组织机构DropdownList
        departMap = pfmTenantDepartmentManageService.getDepartmentDDL();
        //职位DropdownList
        postMap = postService.getPostDDL();
		
		// 得到渲染好的模板内容
		String result = feroFreemarkerProcessor.process(Constants.SYSTEM_BUSINESSSTAFF_ADD, map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	
	/********************************* Get Set 方法 *************************************/
	/** 获取当前ID */
	public String getCurId() {
		return curId;
	}

	/** 设置当前ID */
	public void setCurId(String curId) {
		this.curId = curId;
	}
	
	/** 获取用户表实体 */
	public PfmUser getPfmUser() {
		return pfmUser;
	}

	/** 设置用户表实体 */
	public void setPfmUser(PfmUser pfmUser) {
		this.pfmUser = pfmUser;
	}
	
	/** 获取业务人员Dto列表 */
	public List<PfmUserDto> getPfmUserDtoList() {
		return pfmUserDtoList;
	}

	/** 设置业务人员Dto列表 */
	public void setPfmUserDtoList(List<PfmUserDto> pfmUserDtoList) {
		this.pfmUserDtoList = pfmUserDtoList;
	}
	
	/** 获取页面检索用户名 */
	public String getUserName() {
		return userName;
	}

	/** 设置页面检索用户名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 获取页面检索姓名  */
	public String getRealName() {
		return realName;
	}

	/** 设置页面检索姓名  */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** 获取页面检索职位 */
	public String getPostCd() {
		return postCd;
	}

	/** 设置页面检索职位 */
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	/** 获取页面检索组织机构 */
	public String getDepartCd() {
		return departCd;
	}

	/** 设置页面检索组织机构 */
	public void setDepartCd(String departCd) {
		this.departCd = departCd;
	}

	/** 获取页面检索状态 */
	public String getActive() {
		return active;
	}

	/** 设置页面检索状态 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/** 获取页面检索参数类 */
	public PfmUserCriteria getCriteria() {
		return criteria;
	}

	/** 设置页面检索参数类 */
	public void setCriteria(PfmUserCriteria criteria) {
		this.criteria = criteria;
	}

	/** 获取组织机构DropdownList */
	public Map<String, String> getDepartMap() {
		return departMap;
	}

	/** 设置组织机构DropdownList */
	public void setDepartMap(Map<String, String> departMap) {
		this.departMap = departMap;
	}

	/** 获取职位DropdownList */
	public Map<String, String> getPostMap() {
		return postMap;
	}

	/** 设置职位DropdownList */
	public void setPostMap(Map<String, String> postMap) {
		this.postMap = postMap;
	}
	public PfmUserDto getPfmUserDto() {
		return pfmUserDto;
	}


	public void setPfmUserDto(PfmUserDto pfmUserDto) {
		this.pfmUserDto = pfmUserDto;
	}
	/** 获取业务人员第一次输入密码 */
	public String getPfmUserPassFir() {
		return pfmUserPassFir;
	}

	/** 设置业务人员第一次输入密码 */
	public void setPfmUserPassFir(String pfmUserPassFir) {
		this.pfmUserPassFir = pfmUserPassFir;
	}

	/** 获取业务人员第二次输入密码 */
	public String getPfmUserPassSec() {
		return pfmUserPassSec;
	}

	/** 设置业务人员第二次输入密码 */
	public void setPfmUserPassSec(String pfmUserPassSec) {
		this.pfmUserPassSec = pfmUserPassSec;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
