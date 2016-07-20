package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 系统设定类型（单位）枚举类
 * @author 
 *
 */
public enum SettingTypeEnum implements CodeListItem{
	/**
	 * 类型--%
	 */
    SETTING_TYPE_SIGN("1", "%"),
    
    /**
     * 类型--天
     */
    SETTING_TYPE_DAY("2", "days"),
    
    /**
     * 类型--dollar
     */
    SETTING_TYPE_YUAN("3", "dollar"),
    
    /**
     * 类型--数值
     */
    SETTING_TYPE_NUMBER("4", "数值"),
    
    /**
     * 类型--文本
     */
    SETTING_TYPE_TEXT("5", "文本"),
    
	/**
	 * 类型--图片
	 */
    SETTING_TYPE_PICTURE("6", "图片"),
    
    /**
     * 类型--hour
     */
    SETTING_TYPE_HOUR("7", "hours");

	private String value = null;
    private String code = null;
    
    private SettingTypeEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static SettingTypeEnum getEnumByKey(String key) {
        for (SettingTypeEnum e : SettingTypeEnum.values()) {
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
