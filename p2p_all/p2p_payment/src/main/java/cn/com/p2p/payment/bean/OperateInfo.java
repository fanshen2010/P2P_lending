
package cn.com.p2p.payment.bean;


import java.math.BigDecimal;
import java.util.List;

import cn.com.p2p.domain.payment.entity.CiccAccount;


public class OperateInfo{

	/**
	 * 返回类型 0：成功，1,系统错误，2其他错误，3已经成功操作,4 项目还款失败(需要更改交易流水号) ,5 交易正在处理，对应中金查询 ,6 不存在的交易
	 */
	private int retCode = 0;

	/**
	 * 收集支付信息
	 */
	private  PaymentInfo paymentInfo ;// 收集支付信息
    
	private String retStatus;
	private BigDecimal balance = BigDecimal.ZERO;

	/**
	 * 账户信息
	 */
	private CiccAccount ciccAccount;

	/**
	 * 余额单位(元)
	 * 
	 * @return
	 */
	public BigDecimal getBalance(){

		return balance;
	}


	/**
	 * 错误消息，只有发生错误的时候才有
	 */
	private String errMessage = "";

	/**
	 * 成功消息
	 */
	private String successMsg="";
	/**
	 * 返回跳转的action
	 */
	private String retAction = "";

	/**
	 * 签约账户名称.
	 */
	private String paymentAccountName;


	/**
	 * 签约账户名称
	 * 
	 * @return
	 */
	public String getPaymentAccountName(){

		return paymentAccountName;
	}


	/**
	 * 签约账户名称
	 * 
	 * @param paymentAccountName
	 */
	public void setPaymentAccountName(String paymentAccountName){

		this.paymentAccountName = paymentAccountName;
	}


	public OperateInfo(){

	}

	
	public OperateInfo(BigDecimal balance){

		this.balance = balance;
	}


	public OperateInfo(int retCode,String errMessage){

		this.retCode = retCode;
		this.errMessage = errMessage;
	}


	public OperateInfo(int retCode,String errMessage,String retAction){

		this.retCode = retCode;
		this.errMessage = errMessage;
		this.retAction = retAction;
	}



	public OperateInfo(int retCode, PaymentInfo paymentInfo) {
	    this.retCode = retCode;
        this.paymentInfo = paymentInfo;
    }
	
	
	public OperateInfo(int retCode, PaymentInfo paymentInfo,String successMsg) {
	    this.retCode = retCode;
	    this.paymentInfo = paymentInfo;
	    this.successMsg = successMsg;
	}


    /**
	 * 返回类型 0：成功，1,系统错误，2其他错误
	 * 
	 * @return
	 */
	public int getRetCode(){

		return retCode;
	}


	/**
	 * 返回类型 0：成功，1,系统错误，2其他错误
	 * 
	 * @param retCode
	 */
	public void setRetCode(int retCode){

		this.retCode = retCode;
	}


	/**
	 * 错误消息，只有发生错误的时候才有
	 * 
	 * @return
	 */
	public String getErrMessage(){

		return errMessage;
	}


	/**
	 * 错误消息，只有发生错误的时候才有
	 * 
	 * @param errMessage
	 */
	public void setErrMessage(String errMessage){

		this.errMessage = errMessage;
	}


	/**
	 * 返回跳转的action
	 * 
	 * @return
	 */
	public String getRetAction(){

		return retAction;
	}


	/**
	 * 返回跳转的action
	 * 
	 * @param retAction
	 */
	public void setRetAction(String retAction){

		this.retAction = retAction;
	}

	/**
	 * @return the retStatus
	 */
	public String getRetStatus() {
		return retStatus;
	}


	/**
	 * @param retStatus the retStatus to set
	 */
	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus;
	}


	/**
	 * @return the ciccAccount
	 */
	public CiccAccount getCiccAccount() {
		return ciccAccount;
	}


	/**
	 * @param ciccAccount the ciccAccount to set
	 */
	public void setCiccAccount(CiccAccount ciccAccount) {
		this.ciccAccount = ciccAccount;
	}


    /**
     * @return the paymentInfo
     */
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }


    /**
     * @param paymentInfo the paymentInfo to set
     */
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }


    /**
     * @return the successMsg
     */
    public String getSuccessMsg() {
        return successMsg;
    }


    /**
     * @param successMsg the successMsg to set
     */
    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

}
