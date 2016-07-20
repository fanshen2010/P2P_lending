package cn.com.p2p.paymentlog.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>交易支付日志中投资信息数据传输对象</p>
 *
 * @author 
 * @date 2015年4月21日 17:15:07
 */
public class PaymentInvestDto {
    //用户名
    private String investorLogin;
    //支付投资交易流水号
    private String serialNumber;
    //中金支付账户名称
    private String ciccLogin;
    //中金支付账户号码
    private String ciccNo;
    //身份证号码
    private String idCard;
    //手机号
    private String phone;
    //投资金额
    private BigDecimal investAmount;
    //支付金额
    private BigDecimal payAmount;
    //支付状态
    private String payType;
    //投资类型
    private String investType;
    //原投资人用户名
    private String originInvestorLogin;
    //原投资交易流水号
    private String originSerialNumber;
    //投资时间
    private Date payDate;
    //支付成功时间
    private Date paySuccessDate;
    //优惠券使用状态 : 未使用/已使用
    private String couponUseType;
    //优惠券编号
    private String couponNo;
    //优惠券金额
    private BigDecimal couponAmount;

        /*
     * ==================================================================
     * ===========================Get/Set方法============================
     * ==================================================================
     */

    public String getInvestorLogin() {
        return investorLogin;
    }

    public void setInvestorLogin(String investorLogin) {
        this.investorLogin = investorLogin;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCiccLogin() {
        return ciccLogin;
    }

    public void setCiccLogin(String ciccLogin) {
        this.ciccLogin = ciccLogin;
    }

    public String getCiccNo() {
        return ciccNo;
    }

    public void setCiccNo(String ciccNo) {
        this.ciccNo = ciccNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getOriginInvestorLogin() {
        return originInvestorLogin;
    }

    public void setOriginInvestorLogin(String originInvestorLogin) {
        this.originInvestorLogin = originInvestorLogin;
    }

    public String getOriginSerialNumber() {
        return originSerialNumber;
    }

    public void setOriginSerialNumber(String originSerialNumber) {
        this.originSerialNumber = originSerialNumber;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(Date paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    public String getCouponUseType() {
        return couponUseType;
    }

    public void setCouponUseType(String couponUseType) {
        this.couponUseType = couponUseType;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }
}
