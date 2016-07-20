package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 支付记录表的交易状态的枚举
 * 
 * @author 
 * */
public enum ResponseStatusEnum  implements CodeListItem {
    /**
     * 未支付
     */
    LOAN_STATUS_10("10", "未支付"),
    /**
     * 已退款
     */
    LOAN_STATUS_20("20", "已支付");

	private String value = null;
	private String code = null;
	
	private ResponseStatusEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static ResponseStatusEnum getEnumByKey(String key) {
        for (ResponseStatusEnum e : ResponseStatusEnum.values()) {
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
