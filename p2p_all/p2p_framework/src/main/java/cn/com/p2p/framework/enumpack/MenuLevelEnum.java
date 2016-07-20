package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 
 * 菜单级别枚举类
 * @author 
 *
 */
public enum MenuLevelEnum implements CodeListItem {
    /**
     * 1=一级菜单
     */
    MENU_LEVEL_1("1","first level Menu"),
    /**
     * 2=二级菜单
     */
    MENU_LEVEL_2("2","second level Menu"),
    /**
     * 3=三级菜单
     */
    MENU_LEVEL_3("3","third level Menu");
    
    private String value = null;
    private String code = null;

    private MenuLevelEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static MenuLevelEnum getEnumByKey(String key) {
        for (MenuLevelEnum e : MenuLevelEnum.values()) {
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
