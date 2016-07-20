package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 购买方式枚举类
 * @author wangxiangshun
 *
 */
public enum BuyTheWayEnum implements CodeListItem{
	/**
	 * 购买方式--贷款
	 */
	BUY_THE_WAY_01("1", "loan"),
	/**
	 * 购买方式--全款
	 */
	BUY_THE_WAY_02("2", "payment");

	private String value = null;
    private String code = null;
    
    private BuyTheWayEnum(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static BuyTheWayEnum getEnumByKey(String key) {
        for (BuyTheWayEnum e : BuyTheWayEnum.values()) {
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
