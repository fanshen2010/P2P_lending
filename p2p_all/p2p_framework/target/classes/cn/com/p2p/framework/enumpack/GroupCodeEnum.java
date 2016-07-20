package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 组编码枚举类
 * @author 
 *
 */
public enum GroupCodeEnum implements CodeListItem {
	/** 基础费用相关参数 */
    GROUP_CODE_COST_SETTING("cost_setting", "COST SETTING"),
	
    /** 时间相关参数 */
    GROUP_CODE_DATE_SETTING("date_setting", "DATE SETTING"),
    
    /** 合同参数 */
    GROUP_CODE_CONTRACT_SETTING("contract_setting", "CONTRACT SETTING");
    
	
	private String value = null;
	private String code = null;
	
	private GroupCodeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static GroupCodeEnum getEnumByKey(String key) {
        for (GroupCodeEnum e : GroupCodeEnum.values()) {
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
