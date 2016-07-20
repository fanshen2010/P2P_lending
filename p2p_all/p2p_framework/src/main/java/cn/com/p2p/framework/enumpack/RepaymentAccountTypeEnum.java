package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 还款用户类别
 * @author   
 *
 */
public enum RepaymentAccountTypeEnum implements CodeListItem{
	/**
	 * 融资人
	 */
    REPAYMENT_ACCOUNT_TYPE_FINANCING_PERSON("20", "financier"),
	/**
	 * 平台
	 */
    REPAYMENT_ACCOUNT_TYPE_FINANCING_PLATFORM("40", "platform"),
    /**
     * 担保公司
     */
    REPAYMENT_ACCOUNT_TYPE_GUARANTEE_CORPORATION("30", "guarantee");

	   private String value = null;
	    private String code = null;
	    
	    private RepaymentAccountTypeEnum(String _code, String _value){
	        this.value = _value;
	        this.code = _code;
	    }
	    
	    public static RepaymentAccountTypeEnum getEnumByKey(String key) {
	        for (RepaymentAccountTypeEnum e : RepaymentAccountTypeEnum.values()) {
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