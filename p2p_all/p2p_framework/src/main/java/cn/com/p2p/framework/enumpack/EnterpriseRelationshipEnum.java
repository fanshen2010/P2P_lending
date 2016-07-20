package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;
/**
 * 与企业关系
 * @author wangxiangshun
 *
 */
public enum EnterpriseRelationshipEnum implements CodeListItem{
	/**
	 * 与企业关系--法人
	 */
	ENTERPRISE_RELATIONSHIP_01("1", "legal person"),
	/**
	 * 与企业关系--董事长秘书
	 */
	ENTERPRISE_RELATIONSHIP_02("2", "secretary"),
	/**
	 * 与企业关系--财务总监
	 */
	ENTERPRISE_RELATIONSHIP_03("3", "financial manager"),
	/**
	 * 与企业关系--总经理
	 */
	ENTERPRISE_RELATIONSHIP_04("4", "general manager"),
	/**
	 * 与企业关系--其他
	 */
	ENTERPRISE_RELATIONSHIP_05("5", "other");

	   private String value = null;
	    private String code = null;
	    
	    private EnterpriseRelationshipEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static EnterpriseRelationshipEnum getEnumByKey(String key) {
	        for (EnterpriseRelationshipEnum e : EnterpriseRelationshipEnum.values()) {
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