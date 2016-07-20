package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum LoanTimeLimitEnum  implements CodeListItem{
	/**
	 * 3个月以下
	 */
	LoanTimeLimit_1("0-90", "less than 3 months"),
	/**
	 *3至6个月
	 */
	LoanTimeLimit_2("91-180", "3-6 months"),
	/**
	 *6至12个月
	 */
	LoanTimeLimit_3("180-360", "6-12 months"),
	/**
	 *12个月以上
	 */
	LoanTimeLimit_4("361", "more than 12 months");

	   private String value = null;
	    private String code = null;
	    
	    private LoanTimeLimitEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static LoanTimeLimitEnum getEnumByKey(String key) {
	        for (LoanTimeLimitEnum e : LoanTimeLimitEnum.values()) {
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
