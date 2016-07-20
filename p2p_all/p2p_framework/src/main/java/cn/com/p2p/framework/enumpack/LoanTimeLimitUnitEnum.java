package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * <p>
 * 融资期限单位枚举
 * </p>
 * 
 * <pre>
 * Day("0", "天")
 * Month("1", "月")
 * </pre>
 * @author 
 *
 */
public enum LoanTimeLimitUnitEnum implements CodeListItem {
    /** 天 */
    Day("0", "Days"),

    /** 月 */
    Month("1", "Months");

    private String value = null;
    private String code = null;

    private LoanTimeLimitUnitEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static LoanTimeLimitUnitEnum getEnumByKey(String key) {
        for (LoanTimeLimitUnitEnum e : LoanTimeLimitUnitEnum.values()) {
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
