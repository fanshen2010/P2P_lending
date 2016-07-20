package cn.com.p2p.payment.service;

import java.math.BigDecimal;

import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.payment.enumtype.SettlementUsageTypeEnum;
import cn.com.p2p.payment.enumtype.UserTypeEnum;


public interface PaymentCoreService {
	
	
	/**
	 * 用户注册绑定账户
	 */
	public OperateInfo ciccDoRegister(String userId,String userName);
	
	/**
	 * 有效融资客户绑定账户
	 */
    public OperateInfo ciccDoCustRegister(String custCode,String custName);
    
    /**
	 * 有效投资客户绑定账户
	 */
    public OperateInfo ciccDoInvestCustRegister(String custCode, String userId);

	
	/**
	 * 用户支付账户充值
	 */
	public OperateInfo ciccDoAccountRecharge(
			BigDecimal amount,
			String userId,
			String userLogin);
	
	/**
	 * 用户支付账户提现
	 */
	public OperateInfo ciccDoAccountCash(
			BigDecimal amount,
			String userId,
			String userLogin);
	
	
	/**
	 * 3111P2P 项目支付
	 */
	public OperateInfo ciccDoPay(
			String projectNo,
			String projectName,
			BigDecimal investAmount,
            String userLogin,
            String userId,
            String loanJson,
            String investJson);
	
	/**
	 * 3241-P2P项目还款
	 */
	public OperateInfo ciccDoRepayment(
    		String custName, 
    		String projectNo,
            String projectName,
            AccountTypeEnum accountTypeEnum,
            BigDecimal repaymentAmount,
            String custCode,
            String loanJson,
            String investJson);
	
	/**
     * 3231-P2P项目结算
     */
	public OperateInfo ciccDoProjectSettlement(
    		String custName,
            String projectNo, 
            String projectName, 
            SettlementUsageTypeEnum settlementUsage,
            BigDecimal amountBigDeciaml, 
            String userId_custCode,
            String loanJson,
            String investJson);
	
	public BigDecimal ciccDoSearchBalance(String userId);
	
	public BigDecimal ciccDoSearchBalanceByCustCode(String custCode);
}
