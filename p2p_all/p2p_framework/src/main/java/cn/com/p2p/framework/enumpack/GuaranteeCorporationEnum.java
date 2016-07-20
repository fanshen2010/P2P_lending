package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 担保公司枚举类
 * @author wangxiangshun
 *
 */
public enum GuaranteeCorporationEnum implements CodeListItem{
	/**
	 * 担保公司--中金支付
	 */
	GUARANTEE_CORPORATION_01("10", "----"),
	/**
	 * 担保公司--互联网
	 */
	GUARANTEE_CORPORATION_02("12", "---");

	   private String value = null;
	    private String code = null;
	    
	    private GuaranteeCorporationEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static GuaranteeCorporationEnum getEnumByKey(String key) {
	        for (GuaranteeCorporationEnum e : GuaranteeCorporationEnum.values()) {
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