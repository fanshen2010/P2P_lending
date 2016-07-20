package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 角色状态枚举类
 * @author wangxiangshun
 *
 */
public enum RoleStateEnum implements CodeListItem{
	/**
     * 角色状态--
     */
    ROLE_STATE_1("1", "enabled"),
    /**
     * 角色状态--
     */
    ROLE_STATE_2("0", "disabled");
    
    // 定义私有变量
    private String value = null;
    private String code = null;

    private RoleStateEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static RoleStateEnum getEnumByKey(String key) {
        for (RoleStateEnum e : RoleStateEnum.values()) {
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
