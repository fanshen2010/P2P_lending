package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 融资查询区分枚举类
 * 
 * @author 
 * */
public enum SearchInfoTypeEnum implements CodeListItem{
    /**
     * 共通TAB全部查询
     */
    COMMON_TAB_ALL("COMMON_TAB_ALL","共通TAB全部查询"),
    /**
     * 个人企业
     */
    ENTERPRISE_OR_PERSONAL("ENTERPRISE_OR_PERSONAL","个人企业"),
    /** 
     *  产品
     * */
    PRODUCT("PRODUCT","产品"),
    /**
     * 融资
     */
    LOAN("LOAN","融资"),
    /**
     * 融资信息
     */
    LOAN_INFO("LOAN_INFO","融资信息"),
    /**
     * 融资操作记录表
     */
    LOAN_RECORD("LOAN_RECORD","融资操作记录表"),
    /**
     * 还款情况
     */
    REPAYMENT_INFO("REPAYMENT_INFO","还款情况"),
    /**
     * 投资信息
     */
    INVEST("INVEST","投资信息"),
    /**
     * 还款记录
     */
    REPAYMENT_RECORD("REPAYMENT_RECORD","还款记录");
    
	private String value = null;
	private String code = null;
	
	private SearchInfoTypeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static SearchInfoTypeEnum getEnumByKey(String key) {
        for (SearchInfoTypeEnum e : SearchInfoTypeEnum.values()) {
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
