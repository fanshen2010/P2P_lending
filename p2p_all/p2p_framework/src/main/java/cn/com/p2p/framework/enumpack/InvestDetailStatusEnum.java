package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum InvestDetailStatusEnum implements CodeListItem {

    /**
     * 待收款
     */
    INVEST_DETAIL_STATUS_00("0", "repaying"),
    /**
     * 已收款
     */
    INVEST_DETAIL_STATUS_01("1", "repaid"),
    /**
     *逾期处理中
     */
    INVEST_DETAIL_STATUS_02("2", "repaying overdue"),
    /**
     *逾期已回款
     */
    INVEST_DETAIL_STATUS_03("3", "repaid overdue");

    private String value = null;
    private String code = null;

    private InvestDetailStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static InvestDetailStatusEnum getEnumByKey(String key) {
        for (InvestDetailStatusEnum e : InvestDetailStatusEnum.values()) {
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