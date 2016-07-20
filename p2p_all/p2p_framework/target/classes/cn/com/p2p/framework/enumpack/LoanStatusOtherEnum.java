package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 融资其他状态枚举类
 * 
 * @author 
 * */
public enum LoanStatusOtherEnum implements CodeListItem {
    /**
     * 验资审核中
     */
    LOAN_STATUS_1("1", "验资审核中"),
    /**
     * 担保审核中
     */
    LOAN_STATUS_2("2", "担保审核中"),
    /**
     * 风控审核中
     */
    LOAN_STATUS_3("3", "风控审核中"),
    /**
     * 到期未满标
     */
    LOAN_STATUS_4("4", "Under Scale"),
    /**
     * 超募
     */
    LOAN_STATUS_5("5", "超募"),
    /**
     * 已满标
     */
    LOAN_STATUS_6("6", "Full Scale"),
    /**
     * 招标中
     */
    LOAN_STATUS_7("7", "Investing");

    private String value = null;
    private String code = null;

    private LoanStatusOtherEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static LoanStatusOtherEnum getEnumByKey(String key) {
        for (LoanStatusOtherEnum e : LoanStatusOtherEnum.values()) {
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