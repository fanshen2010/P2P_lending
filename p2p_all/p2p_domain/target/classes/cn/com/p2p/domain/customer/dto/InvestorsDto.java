package cn.com.p2p.domain.customer.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 投资客户管理DTO
 * @author Administrator
 *
 */
public class InvestorsDto implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名
	 */
	private String 	userName;
	/**
	 * 联系电话
	 */
	private String  celphone;
	/**
	 * 账户Id
	 */
	private String   userId;
	/**
	 * 总投资金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 还款中投资
	 */
	private BigDecimal  payingAmount;
	/**
	 * 待生效投资
	 */
	private BigDecimal effectiveAmount;
	/**
	 * 待收收益
	 */
	private BigDecimal interest;
	/**
	 * 帐户状态
	 */
	private String status;
	
	
	
	
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the payingAmount
	 */
	public BigDecimal getPayingAmount() {
		return payingAmount;
	}
	/**
	 * @param payingAmount the payingAmount to set
	 */
	public void setPayingAmount(BigDecimal payingAmount) {
		this.payingAmount = payingAmount;
	}
	/**
	 * @return the effectiveAmount
	 */
	public BigDecimal getEffectiveAmount() {
		return effectiveAmount;
	}
	/**
	 * @param effectiveAmount the effectiveAmount to set
	 */
	public void setEffectiveAmount(BigDecimal effectiveAmount) {
		this.effectiveAmount = effectiveAmount;
	}
	/**
	 * @return the interest
	 */
	public BigDecimal getInterest() {
		return interest;
	}
	/**
	 * @param interest the interest to set
	 */
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
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
}
