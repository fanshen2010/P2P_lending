package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 学历枚举类
 * @author wangxiangshun
 *
 */
public enum EducationalBackgroundEnum implements CodeListItem{
	/**
	 * 专科
	 */
	EDUCATIONAL_BACKGROUND_01("1", "college"),
	/**
	 * 本科
	 */
	EDUCATIONAL_BACKGROUND_02("2", "bachelor");

	   private String value = null;
	    private String code = null;
	    
	    private EducationalBackgroundEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static EducationalBackgroundEnum getEnumByKey(String key) {
	        for (EducationalBackgroundEnum e : EducationalBackgroundEnum.values()) {
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