package cn.com.p2p.payment.enumtype;

/**
 * 用户类别枚举
 * @author 
 *
 */
public enum UserTypeEnum {

    /**
     * 个人
     */
    personal("11", "个人"),
    /**
     * 企业
     */
    enterprise("12", "企业");

    private String value = null;
    private String code = null;

    private UserTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static UserTypeEnum getEnumByKey(String key) {
        for (UserTypeEnum e : UserTypeEnum.values()) {
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
