/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CiccAccountCriteria.java
 * Description:       查询条件CiccAccountCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-14             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.criteria;


import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "CICC_ACCOUNT")
public class CiccAccountCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**用户账户号码*/
    @Column(name="PAYMENT_ACCOUNT_NUMBER")
    private String paymentAccountNumber;

    /**用户姓名*/
    @Column(name="USER_NAME")
    private String userName;

    /**用户类型*/
    @Column(name="USER_TYPE")
    private String userType;
    
         
    public CiccAccountCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取用户姓名*/
    public String getUserName() {
        return userName;
    }

    /**设置用户姓名*/
    public void setUserName(String userName, Operator ... oper) {
        this.userName = userName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userName", param);
            }
        }
    }
    /**获取用户账户号码*/
    public String getPaymentAccountNumber() {
        return paymentAccountNumber;
    }

    /**设置用户账户号码*/
    public void setPaymentAccountNumber(String paymentAccountNumber, Operator ... oper) {
        this.paymentAccountNumber = paymentAccountNumber;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("paymentAccountNumber", param);
            }
        }
    }
    /**获取用户类型*/
    public String getUserType() {
        return userType;
    }

    /**设置用户类型*/
    public void setUserType(String userType, Operator ... oper) {
        this.userType = userType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userType", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        paymentAccountNumber("PAYMENT_ACCOUNT_NUMBER"),
        userName("USER_NAME"),
        userType("USER_TYPE");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
    }
    public String toString() {
        return "CiccAccount ["
        + ", id=" + id
        + ", userName=" + userName
        + ", paymentAccountNumber=" + paymentAccountNumber
        + ", userType=" + userType
        + "]";
    }

}
