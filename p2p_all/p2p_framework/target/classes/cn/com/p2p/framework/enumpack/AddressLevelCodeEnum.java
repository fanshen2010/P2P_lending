package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum AddressLevelCodeEnum implements CodeListItem {
	/** 省、直辖市、自治区、特别行政区 */
	Province("0", "省、直辖市、自治区、特别行政区"),
	
	/** 市、自治州、盟 */
	City("1", "市、自治州、盟"),
	
	/** 市辖区、县级市、县 */
	County("2", "市辖区、县级市、县");
	
	private String value = null;
	private String code = null;
	
	private AddressLevelCodeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static AddressLevelCodeEnum getEnumByKey(String key) {
        for (AddressLevelCodeEnum e : AddressLevelCodeEnum.values()) {
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
