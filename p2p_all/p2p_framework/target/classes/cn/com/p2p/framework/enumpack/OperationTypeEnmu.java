package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 操作类别枚举
 * @author 
 *
 */
public enum OperationTypeEnmu implements CodeListItem {
    /**
     * 批准
     */
    Approve("1", "批准"),
    /**
     * 拒绝
     */
    Refusal("0", "拒绝");

    private String value = null;
    private String code = null;

    private OperationTypeEnmu(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static OperationTypeEnmu getEnumByKey(String key) {
        for (OperationTypeEnmu e : OperationTypeEnmu.values()) {
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