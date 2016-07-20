package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 证件类型枚举类
 * @author wangxiangshun
 *
 */
public enum DocumentTypeEnum implements CodeListItem{
	/**
	 * 证件类型--身份证
	 */
	DOCUMENT_TYPE_01("1", "ID Card"),
	/**
	 * 证件类型--军官证
	 */
	DOCUMENT_TYPE_02("2", "Office Card");

	   private String value = null;
	    private String code = null;
	    
	    private DocumentTypeEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static DocumentTypeEnum getEnumByKey(String key) {
	        for (DocumentTypeEnum e : DocumentTypeEnum.values()) {
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
