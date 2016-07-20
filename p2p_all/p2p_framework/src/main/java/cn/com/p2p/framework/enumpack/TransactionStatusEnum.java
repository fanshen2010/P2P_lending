package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 交易状态的枚举
 * 
 * @author 
 * */
public enum TransactionStatusEnum  implements CodeListItem {
    /**
     * 退款中
     */
    LOAN_STATUS_1("0", "refunding"),
    /**
     * 已退款
     */
    LOAN_STATUS_2("1", "refunded");

	private String value = null;
	private String code = null;
	
	private TransactionStatusEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static TransactionStatusEnum getEnumByKey(String key) {
        for (TransactionStatusEnum e : TransactionStatusEnum.values()) {
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
