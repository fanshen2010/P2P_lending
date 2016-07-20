package cn.com.p2p.payment.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2p.Tx3111Request;
import payment.api.tx.p2p.Tx3131Request;
import payment.api.tx.p2p.Tx3131Response;
import payment.api.tx.p2p.Tx3141Request;
import payment.api.tx.p2p.Tx3141Response;
import payment.api.tx.p2p.Tx3211Request;
import payment.api.tx.p2p.Tx3231Request;
import payment.api.tx.p2p.Tx3231Response;
import payment.api.tx.p2p.Tx3241Request;
import payment.api.tx.p2p.Tx3241Response;
import payment.tools.util.GUID;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.domain.payment.query.CiccAccountQuery;
import cn.com.p2p.domain.payment.query.PaymentLogQuery;
import cn.com.p2p.domain.payment.repository.CiccAccountRepository;
import cn.com.p2p.domain.payment.repository.PaymentLogRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.payment.enumtype.PaymentProcessStatusEnum;
import cn.com.p2p.payment.enumtype.PaymentStatusTypeEnum;
import cn.com.p2p.payment.enumtype.PaymentTypeEnum;
import cn.com.p2p.payment.enumtype.SettlementUsageTypeEnum;
import cn.com.p2p.payment.service.PaymentCoreService;
import cn.com.p2p.payment.util.PaymentSystemEnvironment;
import cn.com.p2p.payment.util.PaymentToolUtils;

@Service
public class PaymentCoreServiceImpl extends PaymentBase implements
        PaymentCoreService {
    private static final Logger logger = LoggerFactory
            .getLogger(PaymentCoreServiceImpl.class);

    @Autowired
    private CiccAccountRepository ciccAccountRepository;
    @Autowired
    private CiccAccountQuery ciccAccountQuery;
    @Autowired
    private PaymentLogRepository paymentLogRepository;
    @Autowired
    private PaymentLogQuery paymentLogQuery;

    /**
	 * 用户注册绑定账户
	 */
    @Override
    public OperateInfo ciccDoRegister(String userId,String userName){
    	CiccAccount ciccAccount = new CiccAccount();
    	ciccAccount.setId(StringUtils.getUUID());
		ciccAccount.setBalance(new BigDecimal(10000));
		ciccAccount.setUserId(userId);
		ciccAccount.setUserName(userName);
		ciccAccountRepository.insert(ciccAccount);
		return new OperateInfo(0,"","paymentRequest");
    }
    
    /**
	 * 有效融资客户绑定账户
	 */
    @Override
    public OperateInfo ciccDoCustRegister(String custCode,String custName){
    	CiccAccount ciccAccount = new CiccAccount();
    	ciccAccount.setId(StringUtils.getUUID());
		ciccAccount.setBalance(new BigDecimal(500000));
		ciccAccount.setCustCode(custCode);
		ciccAccount.setUserName(custName);
		ciccAccountRepository.insert(ciccAccount);
		return new OperateInfo(0,"","paymentRequest");
    }
    
    /**
	 * 有效投资客户绑定账户
	 */
    @Override
    public OperateInfo ciccDoInvestCustRegister(String custCode, String userId){
    	CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
    	if(ciccAccount==null){
    		return new OperateInfo(1,"获取ciccAccount失败");
    	}else{
    		ciccAccount.setCustCode(custCode);
    		ciccAccountRepository.dynamicUpdate(ciccAccount);
    		return new OperateInfo(0,"","paymentRequest");
    	}
    }
    
    
    /**
	 * 用户支付账户充值
	 */
	@Override
	public OperateInfo ciccDoAccountRecharge(
			BigDecimal amount,
			String userId,
			String userLogin){

		try{
			logger.debug("[4251-用户支付账户充值] [ciccDoAccountRecharge][开始] ");
			CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
			// 判断是否已经注册
			if(ciccAccount == null){
				logger.debug("[4251-用户支付账户充值] [ciccDoAccountRecharge][错误][支付账户不存在,请确认账户信息] ");
				return new OperateInfo(2,"支付账户不存在,请确认账户信息");
			}
			
			// ciccAccount
			this.BalanceAdd(userId, amount);
			
			// PaymentLog
			this.insertPaymentLog("4251", amount, userLogin, 
					PaymentProcessStatusEnum.PPS3.getCode(), 
					PaymentTypeEnum.recharge.getCode(),
					null,
					null,null, null, null);
			
			logger.debug("[4251-用户支付账户充值] [ciccDoAccountRecharge][end]");
			return new OperateInfo(0,"","paymentRequest");

		} catch(Exception e){
			logger.error("[4251-用户支付账户充值] [ciccDoAccountRecharge]",e);
			return new OperateInfo(1,e.getMessage());
		}
	}
	
	
	/**
	 * 用户支付账户提现
	 */
	@Override
	public OperateInfo ciccDoAccountCash(
			BigDecimal amount,
			String userId,
			String userLogin){

		try{
			logger.debug("[4255-用户支付账户提现] [ciccDoAccountRecharge][开始]");
			CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
			// 判断是否已经注册
			if(ciccAccount == null){
				logger.debug("[4255-用户支付账户提现] [ciccDoAccountCash][错误][支付账户不存在,请确认账户信息] ");
				return new OperateInfo(2,"支付账户不存在,请确认账户信息");
			}
			
			// ciccAccount
			this.BalanceMinus(userId, amount);
			
			// PaymentLog
			this.insertPaymentLog("4255", amount, userLogin, 
					PaymentProcessStatusEnum.PPS3.getCode(), 
					PaymentTypeEnum.cash.getCode(),
					null,
					null,null, null, null);
			
			logger.debug("[4255-用户支付账户提现] [ciccDoAccountCash][end]");
			return new OperateInfo(0,"","paymentRequest");
		} catch(Exception e){
			logger.error("[4255-用户支付账户提现] [ciccDoAccountCash]",e);
			return new OperateInfo(1,e.getMessage());
		}
	}
	
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
            String investJson) {
        logger.debug("[P2P项目支付] [ciccDoPay] [begin]");
        try {
        	
        	// ciccAccount
			this.BalanceMinus(userId, investAmount);
			
			// paymentLog
        	this.insertPaymentLog("3111", investAmount, userLogin, 
        			PaymentProcessStatusEnum.PPS3.getCode(), 
        			PaymentTypeEnum.invest.getCode(), 
        			null, 
        			null, null, loanJson, investJson);
        	
            return new OperateInfo(0, "", "paymentRequest");

        } catch (Exception e) {
            logger.error("[P2P项目支付] [ciccDoPay]", e.getMessage());
            return new OperateInfo(1, e.getMessage());
        }
    }
	
	
	/**
     * 3241-P2P项目还款
     */
    @Override
    public OperateInfo ciccDoRepayment(
    		String custName, 
    		String projectNo,
            String projectName,
            AccountTypeEnum accountTypeEnum,
            BigDecimal repaymentAmount,
            String custCode,
            String loanJson,
            String investJson) {
        try {
            logger.debug("[P2P项目还款] [ciccDoRepayment] [begin]");
            
            // ciccAccount loan账户转账到平台账户
            if(StringUtils.compare(
            		AccountTypeEnum.Financier.getCode(),
                    accountTypeEnum.getCode())){
            	this.BalanceMinusByCustCode(custCode, repaymentAmount);
            }
            else if(StringUtils.compare(
            		AccountTypeEnum.Guarantee.getCode(),
                    accountTypeEnum.getCode())){
            	this.BalanceMinusByCustCode(custCode, repaymentAmount);
            }
            else if(StringUtils.compare(
            		AccountTypeEnum.Platform.getCode(),
                    accountTypeEnum.getCode())){
            	//001 即为平台账户
            	this.BalanceMinusByCustCode("001", repaymentAmount);
            }
         	
 			//paymentLog
            this.insertPaymentLog("3241", repaymentAmount, custName, 
        			PaymentProcessStatusEnum.PPS3.getCode(), 
        			PaymentTypeEnum.repayment.getCode(), 
        			null, 
        			projectNo,projectName, null, null);
            return new OperateInfo(0, "", "paymentRequest");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[P2P项目还款] [ciccDoRepayment]", e.getMessage());
            return new OperateInfo(1, e.getMessage());
        }
    }
    
    /**
     * 3231-P2P项目结算
     */
    @Override
    public OperateInfo ciccDoProjectSettlement(
    		String custName,
            String projectNo, 
            String projectName, 
            SettlementUsageTypeEnum settlementUsage,
            BigDecimal amountBigDeciaml, 
            String userId_custCode,
            String loanJson,
            String investJson) {
        try {

            logger.debug("[P2P项目结算] [ciccDoProjectSettlement][begin]");
            
            // ciccAccount
            if(StringUtils.compare(
            		SettlementUsageTypeEnum.Financing.getCode(),
            		settlementUsage.getCode())){
            	//10=融资人融资款
                this.BalanceAddByCustCode(userId_custCode, amountBigDeciaml);
            }
            else if(StringUtils.compare(
            		SettlementUsageTypeEnum.Guarantee.getCode(),
            		settlementUsage.getCode())){
            	//20=担保公司担保费
                this.BalanceAddByCustCode(userId_custCode, amountBigDeciaml);
            }
            else if(StringUtils.compare(
            		SettlementUsageTypeEnum.PlatformFee.getCode(),
            		settlementUsage.getCode())){
            	//30=P2P平台服务费
            	this.BalanceAddByCustCode("001", amountBigDeciaml);
            }
            else if(StringUtils.compare(
            		SettlementUsageTypeEnum.InvestmentIncome.getCode(),
            		settlementUsage.getCode())){
            	//40=投资收益
            	this.BalanceAdd(userId_custCode, amountBigDeciaml);
            }
            else if(StringUtils.compare(
            		SettlementUsageTypeEnum.FailureRefund.getCode(),
            		settlementUsage.getCode())){
            	//60=投资流标退款
            	this.BalanceAdd(userId_custCode, amountBigDeciaml);
            }
            
            //paymentLog
            this.insertPaymentLog("3241", amountBigDeciaml, custName, 
        			PaymentProcessStatusEnum.PPS3.getCode(), 
        			PaymentTypeEnum.settlement.getCode(), 
        			settlementUsage.getCode(), 
        			projectNo,projectName, loanJson, investJson);
            return new OperateInfo(0, "", "paymentRequest");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[P2P-项目结算] [ciccDoProjectSettlement] [错误] ["
                    + e.getMessage() + "] [end]");
            return new OperateInfo(1, e.getMessage());

        }
    }
    
    public void insertPaymentLog(
    		String TxCode,
    		BigDecimal amount,
    		String userLogin,
    		String paymentProcessEnum,
    		String paymentTypeEnum,
    		String paymentUsageEnum,
    		String ProjectNo,
    		String ProjectName,
    		String loanJson,
    		String investJson){
		PaymentLog pl = new PaymentLog();
		pl.setId(PaymentToolUtils.getUUID());
		pl.setTxCode(TxCode);
		// 操作金额
		pl.setPaymentAmount(amount);
		// 前台用户ID
		pl.setUserLogin(userLogin);
		// 1:未处理/2:处理中/3:成功/4:失败
		pl.setStatus(paymentProcessEnum);
		// 5:充值/提现
		pl.setType(paymentTypeEnum);
		//
		pl.setPaymentUsage(paymentUsageEnum);
        // 创建时间
        pl.setCreateTime(new Date());
        // 支付时间
        pl.setPaymentTime(new Date());
        // 项目信息
    	pl.setProjectName(ProjectName);
    	pl.setProjectNo(ProjectNo);
        pl.setInvestJosn(investJson);
		pl.setLoanJson(loanJson);
        paymentLogRepository.insert(pl);
    }
    
    @Override
    public BigDecimal ciccDoSearchBalance(String userId){
    	CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
    	return ciccAccount.getBalance();
    }
    
    @Override
    public BigDecimal ciccDoSearchBalanceByCustCode(String custCode){
    	CiccAccount ciccAccount = ciccAccountQuery.findOneByCustCode(custCode);
    	return ciccAccount.getBalance();
    }
	
	public OperateInfo BalanceAdd(String userId,BigDecimal addAmount){
		CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
		BigDecimal balance = ciccAccount.getBalance().add(addAmount);
		ciccAccount.setBalance(balance);
		ciccAccountRepository.dynamicUpdate(ciccAccount);
		return new OperateInfo(0, "扣款成功");
	}
	
	public OperateInfo BalanceMinus(String userId,BigDecimal minusAmount){
		CiccAccount ciccAccount = ciccAccountQuery.findOneByUserId(userId);
		if(ciccAccount.getBalance().compareTo(minusAmount)==-1){
			return new OperateInfo(1, "金额不足");
		}else{
			BigDecimal balance = ciccAccount.getBalance().subtract(minusAmount);
			ciccAccount.setBalance(balance);
			ciccAccountRepository.dynamicUpdate(ciccAccount);
			return new OperateInfo(0, "扣款成功");
		}
	}
	
	public OperateInfo BalanceAddByCustCode(String custCode,BigDecimal addAmount){
		CiccAccount ciccAccount = ciccAccountQuery.findOneByCustCode(custCode);
		BigDecimal balance = ciccAccount.getBalance().add(addAmount);
		ciccAccount.setBalance(balance);
		ciccAccountRepository.dynamicUpdate(ciccAccount);
		return new OperateInfo(0, "扣款成功");
	}
	
	public OperateInfo BalanceMinusByCustCode(String custCode,BigDecimal minusAmount){
		CiccAccount ciccAccount = ciccAccountQuery.findOneByCustCode(custCode);
		if(ciccAccount.getBalance().compareTo(minusAmount)==-1){
			return new OperateInfo(1, "金额不足");
		}else{
			BigDecimal balance = ciccAccount.getBalance().subtract(minusAmount);
			ciccAccount.setBalance(balance);
			ciccAccountRepository.dynamicUpdate(ciccAccount);
			return new OperateInfo(0, "扣款成功");
		}
	}



}
