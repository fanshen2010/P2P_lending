package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 性别枚举类
 * @author wangxiangshun
 *
 */
public enum SexEnum implements CodeListItem{

	
	/**
	 * 性别--男
	 */
	SEX_01("1", "male"),

	/**
	 * 性别--女
	 */
	SEX_02("0", "female");

	private String value = null;
	private String code = null;
	
	private SexEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static SexEnum getEnumByKey(String key) {
        for (SexEnum e : SexEnum.values()) {
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
