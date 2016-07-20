package cn.com.p2p.payment.enumtype;

/**
 * 支付接口 -本地处理状态
 * @author 
 *
 */
public enum PaymentProcessStatusEnum {
	/**
	 * 1:未处理
	 */
	PPS1("1","未处理"),
	/**
	 * 2:处理中
	 */
	PPS2("2","处理中"),
	/**
	 * 3:成功
	 */
	PPS3("3","处理成功"),
	/**
	 * 4:失败
	 */
	PPS4("4","处理失败");

    private String value = null;
    private String code = null;

    private PaymentProcessStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentProcessStatusEnum getEnumByKey(String key) {
        for (PaymentProcessStatusEnum e : PaymentProcessStatusEnum.values()) {
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
