package cn.com.p2p.loan.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.payment.criteria.PaymentLogCustomCriteria;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.payment.enumtype.SettlementUsageTypeEnum;
import cn.com.p2p.paymentlog.service.dto.PaymentLoanDto;


/**
 * 投资支付接口
 * @author 
 *
 */
public interface PaymentService {

    
    /**
     * 投资支付
     */
    public OperateInfo doPay(String investId);
    
    
    /**
     * P2P项目还款
     */
    public OperateInfo ciccDoRepayment(List<RepayDetail> details,Loan loan, AccountTypeEnum repaymentAccountTypeEnum);
    
    public OperateInfo ciccDoInvestReceive(Loan loan,Invest invest,InvestDetail investDetail);
    /**
     * 根据自定义条件进行分页查询支付记录
     * @author 
     * 
     * @param PaymentLogCustomCriteria criteria 自定义查询条件
     * @return List<PaymentLog> 支付记录列表
     */
    public List<PaymentLog> getPageListByCustomCriteria(PaymentLogCustomCriteria criteria);
    
    
    /**
     * 给投资人退投资费用	项目流标/超募处理 给
     */
    public OperateInfo ciccDoRefund(Loan loan, Invest pInvest);
    
    /**
     * 给融资人支付放款费用
     */
    public OperateInfo ciccDoMakeLoan(Loan loan);
    
    /**
     * 给担保人支付担保费
     */
    public OperateInfo ciccDoGaranteeFee(Loan loan);
    
    /**
     * 给平台支付服务费
     */
    public OperateInfo ciccDoPlatformFee(Loan loan);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
	 * 用户注册绑定账户
	 */
	public OperateInfo ciccDoRegister(String userId,String userName);
	
	/**
	 * 客户户注册绑定账户
	 */
	public OperateInfo ciccDoCustRegister(String custCode,String custName);

	/**
	 * 用户支付账户充值
	 */
	public OperateInfo ciccDoAccountRecharge(
			BigDecimal amount,
			String userId);
	
	/**
	 * 用户支付账户提现
	 */
	public OperateInfo ciccDoAccountCash(
			BigDecimal amount,
			String userId);
	
	public BigDecimal ciccDoSearchBalance(String userId);
	
	public BigDecimal ciccDoSearchBalanceByCustCode(String custCode);
    
}
