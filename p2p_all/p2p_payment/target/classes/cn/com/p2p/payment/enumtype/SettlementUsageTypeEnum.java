
package cn.com.p2p.payment.enumtype;




/**
 * 中金结算用途枚举
 * @author 
 *
 */
public enum SettlementUsageTypeEnum {

	/**
	 * 10=融资人融资款
	 */
	Financing("10","融资人融资款"),
	/**
	 * 20=担保公司担保费
	 */
	Guarantee("20","担保公司担保费"),
	/**
	 * 30=P2P平台服务费
	 */
	PlatformFee("30","P2P平台服务费"),
	/**
	 * 投资收益
	 */
	InvestmentIncome("40","投资收益"),
	/**
	 * 50=投资撤回退款（指募集期内投资人的投资反悔的主动退款申请）
	 */
	WithdrawRefund("50","投资撤回退款（指募集期内投资人的投资反悔的主动退款申请）"),
	/**
	 * 60=投资流标退款
	 */
	FailureRefund("60","投资流标退款");
	
	private String value = null;
	private String code = null;
	
	private SettlementUsageTypeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static SettlementUsageTypeEnum getEnumByKey(String key) {
        for (SettlementUsageTypeEnum e : SettlementUsageTypeEnum.values()) {
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
