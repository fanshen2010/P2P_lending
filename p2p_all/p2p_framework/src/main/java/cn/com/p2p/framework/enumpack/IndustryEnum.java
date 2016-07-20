package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 所属行业枚举
 * @author 
 *
 */
public enum IndustryEnum implements CodeListItem {
	
	
	/**
	 * 互联网
	 */
    Industry_01("1", "IT"),
    /**
	 * 餐饮业
	 */
    Industry_02("2", "Catering");
	

	private String value = null;
	private String code = null;

	private IndustryEnum(String _code, String _value) {
		this.value = _value;
		this.code = _code;
	}

	public static IndustryEnum getEnumByKey(String key) {
		for (IndustryEnum e : IndustryEnum.values()) {
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
