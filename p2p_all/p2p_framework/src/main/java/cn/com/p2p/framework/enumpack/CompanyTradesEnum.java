package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 公司行业枚举类
 * @author wangxiangshun
 *
 */
public enum CompanyTradesEnum implements CodeListItem{
	/**
	 * 公司行业--餐饮业
	 */
	COMPANY_TRADES_01("1", "Catering"),
	/**
	 * 公司行业--互联网
	 */
	COMPANY_TRADES_02("2", "IT");

	private String value = null;
    private String code = null;
    
    private CompanyTradesEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static CompanyTradesEnum getEnumByKey(String key) {
        for (CompanyTradesEnum e : CompanyTradesEnum.values()) {
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
