package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 还款途径枚举类
 * @author wangxiangshun
 *
 */
public enum PaymentWayEnum implements CodeListItem {
    /**
     * 还款途径--中金支付
     */
    PAYMENT_WAY_01("20", "中金支付"),
    /**
     * 还款途径--银行卡
     */
    PAYMENT_WAY_02("11", "银行卡");

    private String value = null;
    private String code = null;

    private PaymentWayEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentWayEnum getEnumByKey(String key) {
        for (PaymentWayEnum e : PaymentWayEnum.values()) {
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