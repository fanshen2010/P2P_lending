package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 审批类型枚举类
 * @author 
 *
 */
public enum ApproveTypeEnum implements CodeListItem{
    
    /**
     * 放款
     */
    APPROVE_TYPE_1("10", "repaying"),
    
    /**
     * 正常还款
     */
    APPROVE_TYPE_2("20", "self repaid"),
    /**
     * 正常还款
     */
    APPROVE_TYPE_3("30", "guarentee repaid"),
    /**
     * 提前还款
     */
    APPROVE_TYPE_4("40", "platform repaid");
    private String value = null;
    private String code = null;
    
    private ApproveTypeEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static ApproveTypeEnum getEnumByKey(String key) {
        for (ApproveTypeEnum e : ApproveTypeEnum.values()) {
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
