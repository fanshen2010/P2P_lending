package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 融资类别枚举
 * 
 * @author 
 *
 */
public enum LoanTypeEnum implements CodeListItem {

	/**
	 * 企业
	 */
	Enterprise("1","enterprise"),
	/**
	 * 个人
	 */
	Personal("0","person");

	   private String value = null;
	    private String code = null;
	    
	    private LoanTypeEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static LoanTypeEnum getEnumByKey(String key) {
        for (LoanTypeEnum e : LoanTypeEnum.values()) {
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