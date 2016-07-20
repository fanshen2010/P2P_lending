package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;



/**
 * 还款方式枚举类
 * @author 
 *
 */
public enum LoanInterestMannerEnum implements CodeListItem {
    /** 一次性还本付息 */
    DISPOSABLE_PRINCIPAL_INTEREST("0", "disposable principal interest"),

    /** 先息后本 */
    MONTHLY_INTEREST("1", "monthly interest"),

    /** 等额本息 */
    AVERAGE_CAPITAL_PLUS_INTEREST("2", "average capital plus interest"),

    /** 等额本金 */
    AVERAGE_CAPITAL("3", "average capital");

    private String value = null;
    private String code = null;

    private LoanInterestMannerEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static LoanInterestMannerEnum getEnumByKey(String key) {
        for (LoanInterestMannerEnum e : LoanInterestMannerEnum.values()) {
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