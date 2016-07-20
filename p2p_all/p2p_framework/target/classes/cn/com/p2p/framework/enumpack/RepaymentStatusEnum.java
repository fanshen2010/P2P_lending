package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 
 * 还款状态枚举类
 * 
 * @author 
 * */
public enum RepaymentStatusEnum implements CodeListItem{

	//TODO 未完成 业务未明确
	/** 未还款 */
	REPAYMENT_STATUS_ZERO("0","repaying"),

	/** 已还款 */
	REPAYMENT_STATUS_ONE("1","repaid"),
	
	/** 逾期未还 */
	REPAYMENT_STATUS_TWO("2","repaying overdue"),
	
	/** 逾期还款 */
	REPAYMENT_STATUS_THREE("3","repaid overdue"),
	
	/** 逾期代偿 */
	REPAYMENT_STATUS_FOUR("4","repaid by other"),
	/**
	 * 财务审批中
	 */
	REPAYMENT_STATUS_FIVE("5","财务审批中"),
	/**
	 * 扣款中
	 */
	REPAYMENT_STATUS_SIX("6","扣款中"),
	/**
	 * 扣款失败
	 */
	REPAYMENT_STATUS_SEVEN("7","扣款失败"),
	/**
	 * 代偿
	 */
	REPAYMENT_STATUS_EIGHT("8","代偿"),
	/**
	 * 等待放款
	 */
	REPAYMENT_STATUS_NINE("9","等待放款"),
	/**
	 * 扣款成功
	 */
	REPAYMENT_STATUS_TEN("10","扣款成功");


	private String value = null;
	private String code = null;
	
	private RepaymentStatusEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static RepaymentStatusEnum getEnumByKey(String key) {
        for (RepaymentStatusEnum e : RepaymentStatusEnum.values()) {
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
