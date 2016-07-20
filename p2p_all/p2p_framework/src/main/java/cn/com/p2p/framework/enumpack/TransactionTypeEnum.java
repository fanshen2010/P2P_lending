package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 交易类型的枚举
 * 
 * @author 
 * */
public enum TransactionTypeEnum  implements CodeListItem {
    /**
     * 超募退款
     */
    LOAN_STATUS_1("0", "超募退款"),
    /**
     * 流标退款
     */
    LOAN_STATUS_2("1", "流标退款");

	private String value = null;
	private String code = null;
	
	private TransactionTypeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static TransactionTypeEnum getEnumByKey(String key) {
        for (TransactionTypeEnum e : TransactionTypeEnum.values()) {
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
