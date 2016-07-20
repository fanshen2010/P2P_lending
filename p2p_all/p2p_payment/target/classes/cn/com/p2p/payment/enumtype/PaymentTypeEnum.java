package cn.com.p2p.payment.enumtype;


/**
 * 支付类型枚举<br>
 * 为payment_log的type字段准备
 * @author 
 *
 */
public enum PaymentTypeEnum {

	/**
	 * 投资
	 */
	invest("1","投资"),
	/**
	 * 结算
	 */
	settlement("2","结算"),
	/**
	 *转账 
	 */
	transfer("3","转账"),
	/**
	 *还款
	 */
	repayment("4","还款"),
	/**
	 * 充值
	 */
	recharge("5","充值"),
	/**
	 * 提现
	 */
	cash("6","提现");
    private String value = null;
    private String code = null;

    private PaymentTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentTypeEnum getEnumByKey(String key) {
        for (PaymentTypeEnum e : PaymentTypeEnum.values()) {
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
