package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum EnterpriseScaleEnum implements CodeListItem {

	/**
	 * 10人以下
	 */
	EnterpriseScale_01("1", "0-10 people"),
	/**
	 * 10-20人
	 */
	EnterpriseScale_02("2", "10-20 people"),
	/**
	 * 20-50人
	 */
	EnterpriseScale_03("3", "20-50 people"),
	/**
	 * 50-100人
	 */
	EnterpriseScale_04("4", "50-100 people"),
	/**
	 * 100人以上
	 */
	EnterpriseScale_05("5", "more people");

	private String value = null;
	private String code = null;

	private EnterpriseScaleEnum(String _code, String _value) {
		this.value = _value;
		this.code = _code;
	}

	public static EnterpriseScaleEnum getEnumByKey(String key) {
		for (EnterpriseScaleEnum e : EnterpriseScaleEnum.values()) {
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
