package cn.com.p2p.domain.customer.dto;

import cn.com.p2p.framework.dao.BaseCriteria;


/**
 * 投资客户查询条件
 * @author 
 *
 */
public class InvestorsCriteria  extends BaseCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/** 用户名 */
	private String userName;
	/** 联系电话 */
	private String celphone;
	/** 状态 */
	private Integer status;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the celphone
	 */
	public String getCelphone() {
		return celphone;
	}

	/**
	 * @param celphone the celphone to set
	 */
	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
