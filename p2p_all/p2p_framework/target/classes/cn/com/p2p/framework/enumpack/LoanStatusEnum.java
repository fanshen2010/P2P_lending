package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 融资状态枚举类
 * 
 * @author 
 * */
public enum LoanStatusEnum implements CodeListItem {
	// LOAN_STATUS_00("00","撤回(预留)"),
	/**
	 * 待审核
	 */
	LOAN_STATUS_01("01", "待审核"),
	/**
	 * 被驳回
	 */
	LOAN_STATUS_03("03", "被驳回"),
	/**
	 * 上线
	 */
	LOAN_STATUS_04("04", "Investing"),
	/**
	 * 下线
	 */
	LOAN_STATUS_05("05", "Offline"),
//	/**
//	 * 超募退款中
//	 */
//	LOAN_STATUS_06("06", "超募退款中"),
	/**
	 * 待放款
	 */
	LOAN_STATUS_07("07", "Auditing"),
//	/**
//	 * 已流标待退款
//	 */
//	LOAN_STATUS_08("08", "已流标待退款"),
	/**
	 * 流标退款完成
	 */
	LOAN_STATUS_09("09", "Failing"),
	/**
	 * 还款中
	 */
	LOAN_STATUS_10("10", "Repaying"),
	/**
	 * 还款完成
	 */
	LOAN_STATUS_11("11", "Repaid"),
	/**
	 * 逾期还款完成
	 */
	LOAN_STATUS_12("12", "Repaid Overdue"),
	/**
	 * 提前还款审核中
	 */
	LOAN_STATUS_13("13", "提前还款审核中"),
	/**
	 * 提前还款完成
	 */
	LOAN_STATUS_14("13", "提前还款审核中");

	   private String value = null;
	    private String code = null;
	    
	    private LoanStatusEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static LoanStatusEnum getEnumByKey(String key) {
        for (LoanStatusEnum e : LoanStatusEnum.values()) {
	            if (e.getCode().equals(key)) {
	                return e;
	            }
	        }
	        return null;
	    }

	    /** 获取value */
	    public String getValue() {
	        return value;
	    }

	    /** 获取code */
	    public String getCode() {
	        return code;
	    }
	}