package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 部门类型枚举类
 * @author 
 *
 */
public enum DepartmentTypeEnum implements CodeListItem {

    /**
     * 1=部门
     */
    DEPARTMENT_TYPE_1("1", "Department"),
    /**
     * 2=分公司
     */
    DEPARTMENT_TYPE_2("2", "Branch"),
    /**
     * 3=担保公司
     */
    DEPARTMENT_TYPE_3("3", "Guarantee");
    
    // 定义私有变量
    private String value = null;
    private String code = null;

    private DepartmentTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static DepartmentTypeEnum getEnumByKey(String key) {
        for (DepartmentTypeEnum e : DepartmentTypeEnum.values()) {
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
