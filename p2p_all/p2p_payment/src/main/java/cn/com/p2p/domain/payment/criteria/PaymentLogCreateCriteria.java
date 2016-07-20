/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PaymentLogCriteria.java
 * Description:       查询条件PaymentLogCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-22             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * @author FERO-015
 *
 */
public class PaymentLogCreateCriteria extends BaseCriteria {

    /**  */
	private static final long serialVersionUID = 1L;

	/**ID*/
    private String id;
    
    /**用户名*/
    private String userLogin;
    
    /**项目名称*/
    private String projectName;
    
    /**交易状态*/
    private String responseStatus;
    
    /**投资类型*/
    private String paymentUsage;
    
    /**响应码*/
    private String responseCode;
    
    /**类型*/
    private String type;

    /**本地处理状态*/
    private String status;

    /**最小结算时间*/
    private Date paymentMinTime;

    /**最大结算时间*/
    private Date paymentMaxTime;

    /**最小创建时间*/
    private Date createMinTime;

    /**最大创建时间*/
    private Date createMaxTime;
    
    /** 交易处理时间_年月 */
    private String paymentYearMonth;
    


	/** 获取id */
	public String getId() {
		return id;
	}

	/** 设置id */
	public void setId(String id) {
		this.id = id;
	}

	/** 获取userLogin */
	public String getUserLogin() {
		return userLogin;
	}

	/** 设置userLogin */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/** 获取projectName */
	public String getProjectName() {
		return projectName;
	}

	/** 设置projectName */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/** 获取responseStatus */
	public String getResponseStatus() {
		return responseStatus;
	}

	/** 设置responseStatus */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	/** 获取paymentUsage */
	public String getPaymentUsage() {
		return paymentUsage;
	}

	/** 设置paymentUsage */
	public void setPaymentUsage(String paymentUsage) {
		this.paymentUsage = paymentUsage;
	}

	/** 获取responseCode */
	public String getResponseCode() {
		return responseCode;
	}

	/** 设置responseCode */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/** 获取type */
	public String getType() {
		return type;
	}

	/** 设置type */
	public void setType(String type) {
		this.type = type;
	}

    /**获取本地处理状态*/
    public String getStatus() {
        return status;
    }

    /**设置本地处理状态*/
    public void setStatus(String status) {
        this.status = status;
    }

    /**设置最小结算时间*/
    public Date getPaymentMinTime() {
        return paymentMinTime;
    }

    /**获取结算时间*/
    public void setPaymentMinTime(Date paymentMinTime) {
        this.paymentMinTime = paymentMinTime;
    }

    /**设置最大结算时间*/
    public Date getPaymentMaxTime() {
        return paymentMaxTime;
    }

    /**获取最大结算时间*/
    public void setPaymentMaxTime(Date paymentMaxTime) {
        this.paymentMaxTime = paymentMaxTime;
    }

    /**设置最小创建时间*/
    public Date getCreateMinTime() {
        return createMinTime;
    }

    /**获取最小创建时间*/
    public void setCreateMinTime(Date createMinTime) {
        this.createMinTime = createMinTime;
    }

    /**设置最大结算时间*/
    public Date getCreateMaxTime() {
        return createMaxTime;
    }

    /**获取最大结算时间*/
    public void setCreateMaxTime(Date createMaxTime) {
        this.createMaxTime = createMaxTime;
    }

    /** 获取交易处理时间_年月 */
    public String getPaymentYearMonth() {
        return paymentYearMonth;
    }

    /** 设置交易处理时间_年月*/
    public void setPaymentYearMonth(String paymentYearMonth) {
        this.paymentYearMonth = paymentYearMonth;
    }
    
    
}
