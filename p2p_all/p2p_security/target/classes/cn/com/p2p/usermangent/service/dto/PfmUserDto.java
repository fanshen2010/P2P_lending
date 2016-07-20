package cn.com.p2p.usermangent.service.dto;

import java.io.Serializable;

public class PfmUserDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**ID*/
    private String id;

    /**用户名*/
    private String userName;

    /**姓名*/
    private String realName;

    /**手机号*/
    private String contactPhone;

    /**邮箱*/
    private String emailAddress;
    
    /**部门名*/
    private String departName;
    
    /**职位名*/
    private String postName;
    
    /** 业务人员状态 */
    private String active;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
