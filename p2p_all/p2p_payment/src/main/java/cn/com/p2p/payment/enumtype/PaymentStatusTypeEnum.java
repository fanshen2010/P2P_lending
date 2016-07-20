
package cn.com.p2p.payment.enumtype;

/**
 * payment--支付状态枚举
 * @author 
 *
 */
public enum PaymentStatusTypeEnum{
	/**
	 * 10=未支付 , 10=未使用, 10=New ,10=有效
	 */
	PS10("10","未支付"),
	/**
	 * 20=已支付,20=使用成功,20=转账处理中, 20=已经受理,20=无效
	 */
	PS20("20","已支付"),
	/**
	 * 30=已关闭,30=使用失败,30=转账成功,30=还款成功
	 */
	PS30("30","已关闭"),

	/**
	 * 40=已退款,40=转账失败,40=还款失败
	 */
	PS40("40","已退款");

    private String value = null;
    private String code = null;

    private PaymentStatusTypeEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static PaymentStatusTypeEnum getEnumByKey(String key) {
        for (PaymentStatusTypeEnum e : PaymentStatusTypeEnum.values()) {
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
