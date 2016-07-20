package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 公司规模枚举类
 * @author wangxiangshun
 *
 */
public enum CompanySizeEnum implements CodeListItem{
	/**
	 * 公司规模--0~50人
	 */
	COMPANY_SIZE_01("1", "0~50 people"),
	/**
	 * 公司规模--51人~100人
	 */
	COMPANY_SIZE_02("2", "51~100 people");

	private String value = null;
    private String code = null;
    
    private CompanySizeEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static CompanySizeEnum getEnumByKey(String key) {
        for (CompanySizeEnum e : CompanySizeEnum.values()) {
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
