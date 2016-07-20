package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 公司类别枚举类
 * 
 * @author wangxiangshun
 *
 */
public enum CompanyTypeEnum implements CodeListItem {

	/**
	 * 公司类别--股份有限公司
	 */

	COMPANY_TYPE_01("1", "Limited Liability"),
	/**
	 * 公司类别--股份制
	 */

	COMPANY_TYPE_02("2", "Stock"),
	/**
	 * 公司类别--股份两合公司
	 */

	COMPANY_TYPE_03("3", "Limited Partnership On shares"),
	/**
	 * 公司类别--两合公司
	 */

	COMPANY_TYPE_04("4", "limited Partnership"),
	/**
	 * 公司类别--无限公司
	 */

	COMPANY_TYPE_05("5", "Unlimited Liability");

	private String value = null;
	private String code = null;

	private CompanyTypeEnum(String _code, String _value) {
		this.value = _value;
		this.code = _code;
	}

	public static CompanyTypeEnum getEnumByKey(String key) {
		for (CompanyTypeEnum e : CompanyTypeEnum.values()) {
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
