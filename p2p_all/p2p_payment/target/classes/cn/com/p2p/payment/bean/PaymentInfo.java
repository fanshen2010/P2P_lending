package cn.com.p2p.payment.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付方回调实体
 * 
 * @author 
 *
 */
public class PaymentInfo {

	/**
	 * 机构编号
	 */
	private String institutionID;
	/**
	 * 支付流水号
	 */
	private String paymentNo;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 支付金额
	 */
	private BigDecimal amount;
	/**
	 * 投资金额
	 */
	private BigDecimal investAmount;
	
	/**
	 * 支付账号名称
	 */
	private String paymentAccountName;
	/**
	 * 支付账号号码
	 */
	private String paymentAccountNumber;
	/**
	 * 支付账号身份证号码
	 */
	private String identificationNumber;
	/**
	 * 支付账号手机号码
	 */
	private String phoneNumber;

	/**
	 * 优惠券使用状态
	 */
	private String couponUseType;
	/**
	 * 支付方式
	 */
	private String paymentWay;

	/**
	 * 优惠券编号
	 */
	private String couponNo;
	/**
	 * 优惠券金额
	 */
	private BigDecimal couponAmount;

	/**
	 * 支付状态
	 */
	private String status;
	/**
	 * 机构编号
	 * 
	 * @return the institutionID
	 */
	public String getInstitutionID() {
		return institutionID;
	}

	/**
	 * 机构编号
	 * 
	 * @param institutionID
	 *            the institutionID to set
	 */
	public void setInstitutionID(String institutionID) {
		this.institutionID = institutionID;
	}

	/**
	 * 支付流水号
	 * 
	 * @return the paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * 支付流水号
	 * 
	 * @param paymentNo
	 *            the paymentNo to set
	 */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * 支付时间
	 * 
	 * @return the paymentTime
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}

	/**
	 * 支付时间
	 * 
	 * @param paymentTime
	 *            the paymentTime to set
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	/**
	 * 支付金额
	 * 
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 支付金额
	 * 
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 支付账号名称
	 * 
	 * @return the paymentAccountName
	 */
	public String getPaymentAccountName() {
		return paymentAccountName;
	}

	/**
	 * 支付账号名称
	 * 
	 * @param paymentAccountName
	 *            the paymentAccountName to set
	 */
	public void setPaymentAccountName(String paymentAccountName) {
		this.paymentAccountName = paymentAccountName;
	}

	/**
	 * 支付账号号码
	 * 
	 * @return the paymentAccountNumber
	 */
	public String getPaymentAccountNumber() {
		return paymentAccountNumber;
	}

	/**
	 * 支付账号号码
	 * 
	 * @param paymentAccountNumber
	 *            the paymentAccountNumber to set
	 */
	public void setPaymentAccountNumber(String paymentAccountNumber) {
		this.paymentAccountNumber = paymentAccountNumber;
	}

	/**
	 * 支付账号身份证号码
	 * 
	 * @return the identificationNumber
	 */
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	/**
	 * 支付账号身份证号码
	 * 
	 * @param identificationNumber
	 *            the identificationNumber to set
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	/**
	 * 支付方式
	 * 
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 支付方式
	 * 
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 支付方式
	 * 
	 * @return the paymentWay
	 */
	public String getPaymentWay() {
		return paymentWay;
	}

	/**
	 * 支付方式
	 * 
	 * @param paymentWay
	 *            the paymentWay to set
	 */
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	/**
	 * 优惠券编号
	 * 
	 * @return the couponNo
	 */
	public String getCouponNo() {
		return couponNo;
	}

	/**
	 * 优惠券编号
	 * 
	 * @param couponNo
	 *            the couponNo to set
	 */
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	/**
	 * 优惠券金额
	 * 
	 * @return the couponAmount
	 */
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	/**
	 * 优惠券金额
	 * 
	 * @param couponAmount
	 *            the couponAmount to set
	 */
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	/**
	 * 优惠券使用状态
	 * @return the couponUseType
	 */
	public String getCouponUseType() {
		return couponUseType;
	}

	/**
	 * 优惠券使用状态
	 * @param couponUseType the couponUseType to set
	 */
	public void setCouponUseType(String couponUseType) {
		this.couponUseType = couponUseType;
	}

    /**
     * @return the investAmount
     */
    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    /**
     * @param investAmount the investAmount to set
     */
    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
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

}
