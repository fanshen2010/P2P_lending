package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 支付方式枚举
 * @author  
 *
 */
public enum PaymentEnum  implements CodeListItem {
    /**
     * 支付方式--现金
     */
	Payment_01("1", "cash"),
    /**
     * 支付方式--公司卡
     */
	Payment_02("2", "card"),
	/**
	* 支付方式--其他
	*/
	Payment_03("3", "other");

    private String value = null;
    private String code = null;

    private PaymentEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentEnum getEnumByKey(String key) {
        for (PaymentEnum e : PaymentEnum.values()) {
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
