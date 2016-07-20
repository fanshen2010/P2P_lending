package cn.com.p2p.loan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment.tools.util.GUID;
import cn.com.p2p.domain.customer.criteria.CustInvestCriteria;
import cn.com.p2p.domain.customer.entity.CustInvest;
import cn.com.p2p.domain.customer.repository.CustInvestRepository;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.loan.query.LoanQuery;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.payment.criteria.PaymentLogCustomCriteria;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.domain.payment.query.CiccAccountQuery;
import cn.com.p2p.domain.payment.query.PaymentLogQuery;
import cn.com.p2p.domain.payment.repository.CiccAccountRepository;
import cn.com.p2p.domain.payment.repository.PaymentLogRepository;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.PaymentRemarkEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.payment.enumtype.PaymentStatusTypeEnum;
import cn.com.p2p.payment.enumtype.PaymentTypeEnum;
import cn.com.p2p.payment.enumtype.SettlementUsageTypeEnum;
import cn.com.p2p.payment.enumtype.UserTypeEnum;
import cn.com.p2p.payment.service.PaymentCoreService;
import cn.com.p2p.payment.service.impl.PaymentBase;
import cn.com.p2p.payment.util.PaymentSystemEnvironment;
import cn.com.p2p.paymentlog.service.PaymentLogService;
import cn.com.p2p.paymentlog.service.dto.PaymentInvestDto;
import cn.com.p2p.paymentlog.service.dto.PaymentLoanDto;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;

@Service
public class PaymentServiceImpl extends PaymentBase implements PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanQuery loanQuery;
    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private CiccAccountRepository ciccAccountRepository;
    @Autowired
    private Properties settings;

    /** 支付记录查询接口 */
    @Autowired
    private PaymentLogQuery paymentLogQuery;

    @Autowired
    private PaymentLogService paymentLogService;

    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @Autowired
    private InvestQuery investQuery;
    
	@Autowired
    private CiccAccountQuery ciccAccountQuery;
	
	@Autowired
    private CustInvestRepository custInvestRepository;
	
	@Autowired
    private SettingService settingService;
	
	@Autowired
    private PaymentCoreService paymentCoreService;
	
    /**
     * 投资支付
     * 
     * @param loanId
     *            融资id
     * @param userId
     *            投资人id
     * @param successUrl
     *            投资成功Url
     */
    @Override
    public OperateInfo doPay(String investId) {
    	
    	// 校验
        Invest invest = investRepository.findOne(investId);
        if (invest == null) {
            return new OperateInfo(2, "Please confirm invest information");
        }
        Loan loan = loanRepository.findLoanByLoanCode(invest.getLoanCode());
        if (loan == null) {
            return new OperateInfo(2, "Loan can not be invested");
        }
        // 当前项目剩余金额
        Integer currentSurplusShare = loan.getCurrentSurplusShare();
        if (currentSurplusShare <= 0) {
            return new OperateInfo(2, loan.getLoanName() + "Loan is full scale now");
        }
        
        // 调用支付接口
    	PaymentInvestDto investJson = new PaymentInvestDto();
        PaymentLoanDto loanJson = new PaymentLoanDto();
        /* 投资信息 investJson */
        // 用户名
        investJson.setInvestorLogin(invest.getInvestUserName());
        // 投资金额
        investJson.setInvestAmount(invest.getInvestmentAmount());
        // 支付金额 = 投资金额 - 优惠券金额 暂时没优惠券数据
        //investJson.setPayAmount(paymentInfo.getAmount());
        // 支付状态
        investJson.setPayType(PaymentStatusTypeEnum.PS20.getValue());
        // 投资类型
        investJson.setInvestType(invest.getInvestType());
        // 投资时间
        investJson.setPayDate(invest.getCreateTime());
        // 支付成功时间
        investJson.setPaySuccessDate(new Date());
        /* 项目信息loanJson */
        loanJson = paymentLogService.getLoanJson(loan.getLoanCode());
        
        OperateInfo operateInfo = paymentCoreService.ciccDoPay(
        		loan.getLoanCode(), 
        		loan.getLoanName(), 
        		invest.getInvestmentAmount(), 
        		invest.getInvestUserName(), 
        		invest.getUserId(),
        		JsonPluginsUtil.beanToJson(loanJson),
        		JsonPluginsUtil.beanToJson(investJson)
        		);
        
        // 操作成功修改loan，invest信息
        if(operateInfo.getRetCode()==0){
        	
            // 已投金额
            Integer currentInvestedShare = loan.getCurrentInvestedShare() == null ? 0
                    : loan.getCurrentInvestedShare();
            currentInvestedShare = currentInvestedShare
                    + invest.getInvestmentAmount().intValue();
            loan.setCurrentInvestedShare(currentInvestedShare);
            loan.setActualAmount(currentInvestedShare);
            // 剩余金额
            currentSurplusShare = loan.getCurrentSurplusShare() == null ? 0
                    : loan.getCurrentSurplusShare();
            currentSurplusShare = currentSurplusShare
                    - invest.getInvestmentAmount().intValue();
            loan.setCurrentSurplusShare(currentSurplusShare);
            loanRepository.dynamicUpdate(loan);
            if (currentSurplusShare < 0) {
                invest.setStatus(InvestStatusEnmu.INVEST_STATUS_14.getCode());
            } else {
                invest.setStatus(InvestStatusEnmu.INVEST_STATUS_02.getCode());
            }
            invest.setInvestmentTime(new Date());
            investRepository.dynamicUpdate(invest);

            
            // 添加投资客户
            CustInvestCriteria criteria = new CustInvestCriteria();
            criteria.setUserId(invest.getUserId(), Operator.equal);
            List<CustInvest> custInvests = custInvestRepository.findByCriteria(criteria);
            if (custInvests != null && custInvests.size() > 0) {

            } else {
                CustInvest custInvest = new CustInvest();
                custInvest.setId(StringUtils.getUUID());
                custInvest.setUserId(invest.getUserId());
                custInvest.setCreateTime(new Date());
                custInvestRepository.insert(custInvest);
            }
            
            // 发短信，站内信
            String userLogin = invest.getInvestUserName();
            WebUser user = webUserQuery.findByLogin(userLogin);
            webUserService.sendInternalFrontMessage(user.getId(), "7", invest.getInvestCode());
        }
        
        return operateInfo;
    }

    @Override
    // 还款融资详细
    public OperateInfo ciccDoRepayment(
    		List<RepayDetail> details,
    		Loan loan,
    		AccountTypeEnum repaymentAccountTypeEnum) {
        // 校验
        if (loan == null) {
            return new OperateInfo(2, "没有对应的投资项目,请确认");
        }
        
        // 整理还款数据
        // 融资还款-总金额
        BigDecimal repaymount = BigDecimal.ZERO;
        Date repayPlanDate = null;
        for (RepayDetail detail : details) {
            repaymount = repaymount.add(detail.getReceivableSum()).add(detail.getPunitiveDelayPayment()).add(
                    detail.getPunitiveInterest());
            repayPlanDate = detail.getRepayPlanDate();
        }
        
        
        String custCode = null;
        String custName = null;
        if(StringUtils.compare(
        		AccountTypeEnum.Financier.getCode(),
                repaymentAccountTypeEnum.getCode())){
        	custCode = loan.getCustomCode();
        	custName = loan.getCustomName();
        }else if(StringUtils.compare(
        		AccountTypeEnum.Guarantee.getCode(),
                repaymentAccountTypeEnum.getCode())){
        	//custCode = guaranteeCode;
        	//custCode = guaranteeCode;
        }else if(StringUtils.compare(
        		AccountTypeEnum.Platform.getCode(),
                repaymentAccountTypeEnum.getCode())){
        	custCode = "001";
        	custName = "platform";
        }
        
        // 为支付记录准备项目还款信息
        PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号
        loanJson.setLoanCode(loan.getLoanCode());
        // 发起还款时间
        loanJson.setRepayDate(DateUtils.getCurrentDateTime());
        // 还款处理时间
        loanJson.setRepayDealDate(new Date());
        // 融资人用户名
        loanJson.setFinancierLogin(loan.getCustomName());
        // 还款金额
        loanJson.setAmount(repaymount);
        // 计划收款日期
        loanJson.setRepayDate(repayPlanDate);

        if (StringUtils.compare(
        		AccountTypeEnum.Financier.getCode(),
                repaymentAccountTypeEnum.getCode())) {// 融资人还款
            loanJson.setRepayLogin(loan.getCustomName());
        } else if (StringUtils.compare(
        		AccountTypeEnum.Platform.getCode(),
                repaymentAccountTypeEnum.getCode())) {// 平台垫付
            loanJson.setRepayLogin(PaymentSystemEnvironment.webPaymentAccountName);
        } else if (StringUtils.compare(
        		AccountTypeEnum.Guarantee.getCode(),
                repaymentAccountTypeEnum.getCode())) {// 担保公司代偿
            loanJson.setRepayLogin(null);
        }
        // 还款类型
        loanJson.setRepayType(repaymentAccountTypeEnum.getValue());
        
        OperateInfo outputInfo =  paymentCoreService.ciccDoRepayment(
        		custName, 
        		loan.getLoanCode(), 
        		loan.getLoanName(),
        		repaymentAccountTypeEnum,
        		repaymount, 
        		custCode, 
        		JsonPluginsUtil.beanToJson(loanJson), 
        		null);
        		
		return outputInfo;
    }

    /**
     * 根据自定义条件进行分页查询支付记录
     * @author 
     * 
     * @param PaymentLogCustomCriteria criteria 自定义查询条件
     * @return List<PaymentLog> 支付记录列表
     */
    @Override
    public List<PaymentLog> getPageListByCustomCriteria(PaymentLogCustomCriteria criteria) {
        List<PaymentLog> paymentLogList = paymentLogQuery.findPageListByCustomCriteria(criteria);
        List<PaymentLog> outputList = new ArrayList<PaymentLog>();
        for (PaymentLog paymentLog : paymentLogList) {
            PaymentLog elem = new PaymentLog();
            elem.setProjectName(paymentLog.getProjectName());
            elem.setPaymentTime(paymentLog.getPaymentTime());
            elem.setCreateTime(paymentLog.getCreateTime());
            elem.setType(paymentLog.getType());
            elem.setId(paymentLog.getId());
            // remark根据txCode和paymentUsage获得
            String txCode = paymentLog.getTxCode();
            String paymentUsage = paymentLog.getPaymentUsage();
            String remarkCode = txCode + paymentUsage;
            String remarkValue = PaymentRemarkEnum.getEnumByKey(remarkCode) != null ? PaymentRemarkEnum.getEnumByKey(
                    remarkCode).getValue() : "";
            // paymentAmount从json里解析
            BigDecimal paymentAmount = null;
            if (PaymentTypeEnum.invest.getCode().equals(paymentLog.getType())) {
                PaymentInvestDto paymentInvestDto = JsonPluginsUtil.jsonToBean(paymentLog.getInvestJosn(),
                        PaymentInvestDto.class);
                if (paymentInvestDto != null) {
                    paymentAmount = paymentInvestDto.getInvestAmount();
                }
            } else {
                PaymentLoanDto paymentLoanDto = JsonPluginsUtil.jsonToBean(paymentLog.getLoanJson(),
                        PaymentLoanDto.class);
                if (paymentLoanDto != null) {
                    paymentAmount = paymentLoanDto.getAmount();
                }
            }
            elem.setPaymentAmount(paymentAmount);
            outputList.add(elem);
        }
        return outputList;
    }


    @Override
    public OperateInfo ciccDoRefund(Loan loan, Invest pInvest) {
        
        // PaymentLog
        PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称：阿斯顿发
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号：456487654231
        loanJson.setLoanCode(loan.getLoanCode());
        // 结算用途：投资收益
        loanJson.setUsage(SettlementUsageTypeEnum.FailureRefund.getValue());
        // 投资人用户名：zsy
        loanJson.setInvestorLogin(pInvest.getInvestUserName());
        // 融资人用户名：个人账户
        loanJson.setFinancierLogin(loan.getCustomName());
        // 项目结算时间
        loanJson.setSettlementDate(new Date());
        // 结算金额：
        loanJson.setAmount(pInvest.getInvestmentAmount());

        OperateInfo outputInfo = paymentCoreService.ciccDoProjectSettlement(
        		pInvest.getInvestUserName(),
        		loan.getLoanCode(),
                loan.getLoanName(), 
                SettlementUsageTypeEnum.FailureRefund, 
                pInvest.getInvestmentAmount(), 
        		pInvest.getUserId(),
        		JsonPluginsUtil.beanToJson(loanJson),
        		null
        		);

        return outputInfo;
    }
    
    
    @Override
    public OperateInfo ciccDoMakeLoan(Loan loan) {
    	
    	// PaymentLog结算信息
        PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称：阿斯顿发
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号：456487654231
        loanJson.setLoanCode(loan.getLoanCode());
        // 结算用途：投资收益
        loanJson.setUsage(SettlementUsageTypeEnum.Financing.getValue());
        // 投资人用户名：zsy
        loanJson.setInvestorLogin(null);
        // 融资人用户名：个人账户
        loanJson.setFinancierLogin(loan.getCustomName());
        // 项目结算时间：2014-11-11 18:16:16
        loanJson.setSettlementDate(new Date());
        // 结算金额
        loanJson.setAmount(loan.getAmount());
        
        OperateInfo operateInfo = paymentCoreService.ciccDoProjectSettlement(
        		null, 
        		loan.getLoanCode(),
                loan.getLoanName(), 
                SettlementUsageTypeEnum.Financing, 
                loan.getAmount(), 
                loan.getCustomCode(), 
                JsonPluginsUtil.beanToJson(loanJson), 
        		null);
        
        return operateInfo;
    }
    
    @Override
    public OperateInfo ciccDoInvestReceive(Loan loan,Invest invest,InvestDetail investDetail) {
    	
    	BigDecimal investRepayAmount = BigDecimal.ZERO.add(investDetail
                .getReceivableSum());
    	
    	PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称：阿斯顿发
        loanJson.setLoanName(investDetail.getLoanName());
        // 项目编号：456487654231
        loanJson.setLoanCode(investDetail.getLoanCode());
        // 结算用途：投资收益
        loanJson.setUsage(SettlementUsageTypeEnum.InvestmentIncome
                .getValue());
        // 投资人用户名：zsy
        loanJson.setInvestorLogin(investDetail.getUserId());
        // 支付交易流水号：123456798
        loanJson.setPaymentNo(invest.getSerialNumber());
        // 融资人用户名：个人账户
        loanJson.setRepayLogin(loan.getCustomName());
        // 项目结算时间：2014-11-11 18:16:16
        loanJson.setSettlementDate(new Date());
        // 结算金额
        loanJson.setAmount(investRepayAmount);
        
        OperateInfo operateInfo = paymentCoreService.ciccDoProjectSettlement(
	       		 invest.getInvestUserName(), 
	       		 loan.getLoanCode(),
	       		 loan.getLoanName(), 
	       		 SettlementUsageTypeEnum.InvestmentIncome, 
	       		 investRepayAmount, 
	       		 invest.getUserId(), 
	       		 JsonPluginsUtil.beanToJson(loanJson), 
	       		 null);
        
        return operateInfo;
    }
    
    @Override
    public OperateInfo ciccDoGaranteeFee(Loan loan) {
    	
    	// PaymentLog结算信息
        PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称：阿斯顿发
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号：456487654231
        loanJson.setLoanCode(loan.getLoanCode());
        // 结算用途：投资收益
        loanJson.setUsage(SettlementUsageTypeEnum.Guarantee.getValue());
        // 融资人用户名：个人账户
        loanJson.setFinancierLogin(loan.getCustomName());
        // 项目结算时间
        loanJson.setSettlementDate(new Date());
        // 结算金额
        loanJson.setAmount(loan.getGuaranteeFee());
        
        OperateInfo operateInfo = paymentCoreService.ciccDoProjectSettlement(
        		"guarantee", 
        		loan.getLoanCode(),
                loan.getLoanName(), 
                SettlementUsageTypeEnum.Guarantee, 
                loan.getGuaranteeFee(), 
                /*guaranteeCode*/null, 
                JsonPluginsUtil.beanToJson(loanJson), 
        		null);
        
        return operateInfo;
    }

    @Override
    public OperateInfo ciccDoPlatformFee(Loan loan) {
    	
    	// PaymentLog
        PaymentLoanDto loanJson = new PaymentLoanDto();
        // 项目名称：阿斯顿发
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号：456487654231
        loanJson.setLoanCode(loan.getLoanCode());
        // 结算用途：投资收益
        loanJson.setUsage(SettlementUsageTypeEnum.PlatformFee.getValue());
        // 融资人用户名：个人账户
        loanJson.setFinancierLogin(loan.getCustomName());
        // 项目结算时间
        loanJson.setSettlementDate(new Date());
        // 结算金额
        loanJson.setAmount(loan.getPlatformFee());
    	
        OperateInfo outputInfo = paymentCoreService.ciccDoProjectSettlement(
        		"platform", 
        		loan.getLoanCode(),
                loan.getLoanName(), 
                SettlementUsageTypeEnum.PlatformFee, 
                loan.getPlatformFee(), 
        		"001", 
        		JsonPluginsUtil.beanToJson(loanJson), 
        		null);
        
        return outputInfo;
    }
    
    /**
	 * 用户注册绑定账户
	 */
	public OperateInfo ciccDoRegister(String userId,String userName){
		OperateInfo operateInfo = paymentCoreService.ciccDoRegister(userId,userName);
		return operateInfo;
	}
	
	public OperateInfo ciccDoCustRegister(String custCode,String custName){
		OperateInfo operateInfo = paymentCoreService.ciccDoCustRegister(custCode,custName);
		return operateInfo;
    }

	/**
	 * 用户支付账户充值
	 */
	public OperateInfo ciccDoAccountRecharge(
			BigDecimal amount,
			String userId){
		WebUser webUser = webUserService.findOne(userId);
		return paymentCoreService.ciccDoAccountRecharge(amount, userId, webUser.getLogin());
	}
	
	/**
	 * 用户支付账户提现
	 */
	public OperateInfo ciccDoAccountCash(
			BigDecimal amount,
			String userId){
		WebUser webUser = webUserService.findOne(userId);
		return paymentCoreService.ciccDoAccountCash(amount, userId, webUser.getLogin());
	}
	
	/**
	 * 用户支付账户余额查询
	 */
	public BigDecimal ciccDoSearchBalance(String userId){
		return paymentCoreService.ciccDoSearchBalance(userId);
	}
    
	/**
	 * 用户支付账户余额查询
	 */
	public BigDecimal ciccDoSearchBalanceByCustCode(String custCode){
		return paymentCoreService.ciccDoSearchBalanceByCustCode(custCode);
	}

}
