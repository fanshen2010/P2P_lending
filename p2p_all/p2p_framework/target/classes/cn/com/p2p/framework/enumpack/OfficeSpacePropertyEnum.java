package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;
/**
 * 办公场地的性质
 * @author wangxiangshun
 *
 */
public enum OfficeSpacePropertyEnum implements CodeListItem{
	/**
	 * 办公场地的性质--租用
	 */
	OFFICE_SPACE_PROPERTY_01("1", "rental"),
	/**
	 * 办公场地的性质--按揭
	 */
	OFFICE_SPACE_PROPERTY_02("2", "mortgage"),
	/**
	 * 办公场地的性质--自置
	 */
	OFFICE_SPACE_PROPERTY_03("3", "own");

	private String value = null;
    private String code = null;
    
    private OfficeSpacePropertyEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static OfficeSpacePropertyEnum getEnumByKey(String key) {
        for (OfficeSpacePropertyEnum e : OfficeSpacePropertyEnum.values()) {
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