package cn.com.p2p.domain.payment.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * 支付记录表自定义查询参数类
 * 
 * @author 
 *
 */
public class PaymentLogCustomCriteria extends BaseCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 最大交易处理时间 */
	private Date maxDate;
	
	/** 最小交易处理时间 */
	private Date minDate;
	
	/** 用户名 */
    private String userLogin;

	
	/******************************************************* Get Set 方法 ***********************************************************/
	/** 获取最大交易处理时间 */
	public Date getMaxDate() {
		return maxDate;
	}
	/** 设置最大交易处理时间 */
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	/** 获取最小交易处理时间 */
	public Date getMinDate() {
		return minDate;
	}
	/** 设置最小交易处理时间 */
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	/** 获取用户名 */
	public String getUserLogin() {
		return userLogin;
	}
	/** 设置用户名 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
}
