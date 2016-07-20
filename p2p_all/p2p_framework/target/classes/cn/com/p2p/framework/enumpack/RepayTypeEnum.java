package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 还款类型枚举类
 * @author 
 *
 */
public enum RepayTypeEnum implements CodeListItem{
    /**
     * 正常还款
     */
    REPAY_TYPE_1("1", "正常还款"),
    /**
     * 提前还款
     */
    REPAY_TYPE_2("2", "提前还款");
	private String value = null;
	private String code = null;
	
	private RepayTypeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static RepayTypeEnum getEnumByKey(String key) {
        for (RepayTypeEnum e : RepayTypeEnum.values()) {
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
