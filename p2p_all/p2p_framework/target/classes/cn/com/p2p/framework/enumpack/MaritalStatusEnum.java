package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 婚姻状况枚举类
 * @author wangxiangshun
 *
 */
public enum MaritalStatusEnum implements CodeListItem {

    /**
     * 已婚
     */
    MARITAL_STATUS_01("1", "married"),
    /**
     * 未婚
     */
    MARITAL_STATUS_02("2", "single");

    private String value = null;
    private String code = null;

    private MaritalStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static MaritalStatusEnum getEnumByKey(String key) {
        for (MaritalStatusEnum e : MaritalStatusEnum.values()) {
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