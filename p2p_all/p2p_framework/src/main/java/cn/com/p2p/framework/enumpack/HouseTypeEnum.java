package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 住宅类型枚举类
 * @author wangxiangshun
 *
 */
public enum HouseTypeEnum implements CodeListItem {
    /**
     * 住宅类型
     */
    HOUSE_TYPE_01("1", "own"),
    /**
     * 住宅类型
     */
    HOUSE_TYPE_02("2", "mortgage"),
    /**
     * 住宅类型
     */
    HOUSE_TYPE_03("3", "rental"),
    /**
     * 住宅类型
     */
    HOUSE_TYPE_04("4", "other");

    private String value = null;
    private String code = null;

    private HouseTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static HouseTypeEnum getEnumByKey(String key) {
        for (HouseTypeEnum e : HouseTypeEnum.values()) {
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