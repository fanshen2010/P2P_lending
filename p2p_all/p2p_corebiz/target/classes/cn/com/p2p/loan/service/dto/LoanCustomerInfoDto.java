package cn.com.p2p.loan.service.dto;

import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.user.entity.WebUser;

/**
 * 客户信息
 * @author 
 *
 */
public class LoanCustomerInfoDto {
	
    /* 客户信息--企业 */
    private CustEnterpriseInfo custEnterpriseInfo;
    
    /* 客户信息--个人 */
    private CustPersonalInfo custPersonalInfo;
    
    /* 融资次数 */
    private int loanCount;

    /* 融资编号 */
    private String loanCode;
    
    /*平台用户信息*/
    private WebUser userInfoDto;
    /* 客户Code*/
    private String customerCode;

    /** 获取融资次数 */
    public int getLoanCount() {
        return loanCount;
    }

    /** 设置融资次数 */
    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    /** 获取融资编号 */
    public String getLoanCode() {
        return loanCode;
    }

    /** 设置融资编号 */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }


	/**
	 * @return the custEnterpriseInfo
	 */
	public CustEnterpriseInfo getCustEnterpriseInfo() {
		return custEnterpriseInfo;
	}

	/**
	 * @param custEnterpriseInfo the custEnterpriseInfo to set
	 */
	public void setCustEnterpriseInfo(CustEnterpriseInfo custEnterpriseInfo) {
		this.custEnterpriseInfo = custEnterpriseInfo;
	}

	/**
	 * @return the custPersonalInfo
	 */
	public CustPersonalInfo getCustPersonalInfo() {
		return custPersonalInfo;
	}

	/**
	 * @param custPersonalInfo the custPersonalInfo to set
	 */
	public void setCustPersonalInfo(CustPersonalInfo custPersonalInfo) {
		this.custPersonalInfo = custPersonalInfo;
	}

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


    /** 获取平台用户信息 */
    public WebUser getUserInfoDto() {
        return userInfoDto;
    }

    /** 设置平台用户信息 */
    public void setUserInfoDto(WebUser userInfoDto) {
        this.userInfoDto = userInfoDto;
    }
    
    

}
