/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CiccAccount.java
 * Description:       实体CiccAccount类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-14             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;


public class CiccAccount implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;
    
    /**用户账户号码*/
    private String paymentAccountNumber;

    /**用户姓名*/
    private String userName;

    /**用户类型  */
    private String userType;

    /**账户余额  */
    private BigDecimal balance;
    
    /**融资人客户，投资人客户 custCode*/
    private String custCode;
    
    /**前台用户Id*/
    private String userId;
    
    public CiccAccount() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取用户姓名*/
    public String getUserName() {
        return userName;
    }
    /**设置用户姓名*/
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**获取用户账户号码*/
    public String getPaymentAccountNumber() {
        return paymentAccountNumber;
    }

    /**设置用户账户号码*/
    public void setPaymentAccountNumber(String paymentAccountNumber) {
        this.paymentAccountNumber = paymentAccountNumber;
    }
    /**获取用户类型*/
    public String getUserType() {
        return userType;
    }

    /**设置用户类型*/
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}


}
