package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;


/**
 * 前台个人中心交易记录备注
 * @author 
 *
 */
public enum PaymentRemarkEnum implements CodeListItem{


	Invest("31111","投资"),
	
	FinancingRepay("313110","回款"),
	
	GuaranteeRepay("313120","担保代偿"),
	
	PlatformRepay("313130","平台代偿"),
	
	InvestmentIncome("313140","投资收益"),
	
	WithdrawRefund("313150","撤回退款"),
	
	OversubscriptionRefunds("313160","超募退款"),
	
	FailureRefunds("313161","流标退款"),
	
	InvestorRepay("3141","还款");
	
	
	
	private String value = null;
    private String code = null;

    private PaymentRemarkEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentRemarkEnum getEnumByKey(String key) {
        for (PaymentRemarkEnum e : PaymentRemarkEnum.values()) {
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
