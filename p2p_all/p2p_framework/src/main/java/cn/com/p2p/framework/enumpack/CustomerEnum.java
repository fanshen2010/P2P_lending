package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;


/**
 * 客户类型枚举
 * @author 
 *
 */
public enum CustomerEnum  implements CodeListItem {

    /**
     * 1=个人
     */
    Personal("1", "person"),
    /**
     * 2=企业
     */
    Enterprise("2", "enterprise");
    
    // 定义私有变量
    private String value = null;
    private String code = null;

    private CustomerEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static CustomerEnum getEnumByKey(String key) {
        for (CustomerEnum e : CustomerEnum.values()) {
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
