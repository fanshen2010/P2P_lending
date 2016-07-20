package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum PayTlementType implements CodeListItem {

    /**
     * 10=投资人
     */
    Investors("10", "investor"),
    /**
     * 20=融资人
     */
    Financiers("20", "financier"),
    /**
     * 30=担保人
     */
    Guarantor("30", "guarantee"),
    /**
     * P2P平台方
     */
    P2PPlatformparty("40", "platform");
    // 定义私有变量

    private String value = null;
    private String code = null;

    private PayTlementType(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PayTlementType getEnumByKey(String key) {
        for (PayTlementType e : PayTlementType.values()) {
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
