package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 操作状态枚举类
 * @author 
 *
 */
public enum OperateStatusEnum implements CodeListItem {
	/** 驳回 */
    OPERATE_STATUS_REJECT("0", "驳回"),
	
	/** 通过 */
    OPERATE_STATUS_PUSS("1", "通过"),
	
	/** 已提交 */
    OPERATE_STATUS_SUBMIT("2", "已提交"),
    
    /** 成功 */
    OPERATE_STATUS_SUCCESS("3", "成功"),
    
    /** 处理中 */
    OPERATE_STATUS_PROCESSING("4", "处理中"),
    
    /** 还款失败*/
    OPERATE_STATUS_REPAYMENT_FAILURE("5", "还款失败"),
    
    /** 还款成功*/
    OPERATE_STATUS_REPAYMENT_SUCCESS("6", "还款成功"),
    
    /** 扣款失败*/
    OPERATE_STATUS_DEDUCT_MONEY_FAILURE("7", "扣款失败"),
    
    /** 扣款成功*/
    OPERATE_STATUS_DEDUCT_MONEY_SUCCESS("8", "扣款成功");
    
	
	private String value = null;
	private String code = null;
	
	private OperateStatusEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static OperateStatusEnum getEnumByKey(String key) {
        for (OperateStatusEnum e : OperateStatusEnum.values()) {
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
