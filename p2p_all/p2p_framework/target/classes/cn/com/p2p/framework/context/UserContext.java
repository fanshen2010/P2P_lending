package cn.com.p2p.framework.context;

import java.util.List;

public interface UserContext {


	public String getMessage();


	public void setMessage(String mesage);



	public String getInformation();


	public void setInformation(String information);


	/**
	 * 用户类型
	 * 
	 * @return
	 */
	public int getUserType();


	/**
	 * 手机号
	 * 
	 * @return
	 */
	public String getPhoneNumber();


	/**
	 * 真实姓名
	 * 
	 * @return
	 */
	public String getRealName();


	/**
	 * 身份证号
	 * 
	 * @return
	 */
	public String getIdentity();


	/**
	 * 取得角色名称
	 * 
	 * @return
	 */
	public List<String> getRoleNameList();


	/**
	 * 登录用户的公司id
	 * 
	 * @return
	 */
	public String getCompanyId();


	/**
	 * 登录用户的部门id
	 * 
	 * @return
	 */
	public String getOrganizationId();



	public String getId();


	public String getPasswordSalt();


	public String getPasswordOperation();


	public String getPassword();


	public void setPassword(String password);


	public String getPasswordOperationSalt();


	public void setPasswordOperationSalt(String passwordOperationSalt);

	public String getPostCd();

	public void setPostCd(String postCd);
	/**
	 * 判断是否是注册经理人
	 * 
	 * @return
	 */
	public boolean getIsManager();


	public void setIsManager(boolean isManager);

	public String getUsername();

	public String getEmail();


	public String getCiccAccountId();


	public String getCiccDebitAccountId();

}
