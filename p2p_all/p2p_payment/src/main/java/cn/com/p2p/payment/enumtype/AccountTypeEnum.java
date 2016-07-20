package cn.com.p2p.payment.enumtype;


/**
 * 账户类别枚举
 * 
 * @author 
 *
 */
public enum AccountTypeEnum {

    /**
     * 11=投资人
     */
    Investor("11", "投资人"),
    /**
     * 21=融资人
     */
    Financier("21", "融资人"),
    /**
     * 31=担保人
     */
    Guarantee("31", "担保人"),
    /**
     * 41=平台
     */
    Platform("41", "平台");

   
    private String value = null;
    private String code = null;

    private AccountTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static AccountTypeEnum getEnumByKey(String key) {
        for (AccountTypeEnum e : AccountTypeEnum.values()) {
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