package cn.com.p2p.loan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment.tools.util.GUID;
import cn.com.p2p.common.dto.CalculatorResultDto;
import cn.com.p2p.common.dto.CalculatorResultInstallmentsDto;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestCriteria;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.invest.repository.InvestDetailRepository;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.criteria.RepayDetailCriteria;
import cn.com.p2p.domain.loan.criteria.RepayDetailRepaymentCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.Refund;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.loan.query.LoanQuery;
import cn.com.p2p.domain.loan.query.RepayDetailQuery;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.loan.repository.RefundRepository;
import cn.com.p2p.domain.loan.repository.RepayDetailRepository;
import cn.com.p2p.domain.payment.query.PaymentLogQuery;
import cn.com.p2p.domain.payment.repository.CiccAccountRepository;
import cn.com.p2p.domain.payment.repository.PaymentLogRepository;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.InvestDetailStatusEnum;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.enumpack.RepaymentStatusEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.enumpack.TransactionStatusEnum;
import cn.com.p2p.framework.enumpack.TransactionTypeEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.loan.service.LoanEffectMangementService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.loan.service.dto.UpdateInvestDto;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.payment.service.PaymentCoreService;
import cn.com.p2p.security.password.service.CheckUserPasswordService;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;
import cn.com.p2p.utils.CalculatorUtils;

@Service
public class LoanEffectManagementServiceImpl implements
        LoanEffectMangementService {

    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private InvestDetailRepository investDetailRepository;

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private LoanRepository loanRepository;
    /* 融资信息 */
    @Autowired
    private RepayDetailRepository repayDetailRepository;
    @Autowired
    private RepayDetailQuery repayDetailQuery;
    /* 融资信息 */
    @Autowired
    private LoanQuery loanQuery;
    /* 投资 */
    @Autowired
    private InvestQuery investQuery;

    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private CiccAccountRepository ciccAccountRepository;
    @Autowired
    private PaymentLogRepository paymentLogRepository;
    @Autowired
    private PaymentLogQuery paymentLogQuery;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CheckUserPasswordService checkUserPasswordService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private LoanSearchService loanSearchService;
    @Autowired
    private PaymentCoreService paymentCoreService;
    
    /**
     * <p>
     * 根据ID 动态更新融资表
     * Loan的属性 ：保存要更新的内容 ID ：去查找具体的融资记录
     * @param pLoan
     *            更新内容及融资ID
     */
    public void updateLoan(Loan pLoan) {
        loanRepository.dynamicUpdate(pLoan);
    }


    /**
     * 融资放款
     */
    @Override
    public OperateInfo makeLoans(String pstrLoanCode, UserContext loginUser) {
    	
    	// -----------------点放款到财务审核审核  loanstatus：待放款
    	
        Loan loan = loanRepository.findLoanByLoanCode(pstrLoanCode);
        // 平台管理费=实际融资额×平台管理费率
        BigDecimal bdPlatformFee = CalculatorUtils.feeCalculator(
                loan.getActualAmount(),
                loan.getPlatformRate().divide(new BigDecimal(100)));
        loan.setPlatformFee(bdPlatformFee);
        // 担保费=实际融资额×担保费率
        if (StringUtils.isNotEmpty(loan.getGuaranteeComCode())) {
            loan.setGuaranteeFee(CalculatorUtils.feeCalculator(
                    loan.getActualAmount(),
                    loan.getGuaranteeRate().divide(new BigDecimal(100))));
        } else {
            loan.setGuaranteeFee(BigDecimal.ZERO);
        }
        // 实际放款总额=实际融资额-平台管理费-担保费
        loan.setAmount(new BigDecimal(loan.getActualAmount()).subtract(
                loan.getPlatformFee()).subtract(loan.getGuaranteeFee()));
        // 起息日期
        loan.setCarryInterestFrom(DateUtils.dateAdd(
                DateUtils.parseCurrentDateTime("yyyy-MM-dd"),
                Calendar.DAY_OF_MONTH, 1));// 获取融资的起息日期
        Date carryInterestTo = DateUtils
                .dateAdd(
                        loan.getCarryInterestFrom(),
                        (StringUtils.compare(loan.getLoanTimeLimitUnit(),
                                LoanTimeLimitUnitEnum.Day.getCode()) ? Calendar.DAY_OF_MONTH
                                : Calendar.MONTH), loan.getLoanTimeLimit()
                                .intValue());
        // 到期日期=起息日期+融资期限-1天
        loan.setCarryInterestTo(carryInterestTo);// 获取融资的到期日期

        
        OperateInfo operateInfo = null;
        try {
        	// ----------调用payment----------------//
        	
            if (loan.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            	// 融资放款
            	operateInfo = paymentService.ciccDoMakeLoan(loan);
                if (operateInfo.getRetCode() == 0) {
                	// 融资放款成功 
                    if (!StringUtils.objectNull(loan.getGuaranteeComCode())
                            && loan.getGuaranteeFee()
                                    .compareTo(BigDecimal.ZERO) > 0) {
                    	// 进行担保费处理
                    	operateInfo = paymentService.ciccDoGaranteeFee(loan);
                    }
                }
                
                if (operateInfo.getRetCode() == 0) {
                	// 融资放款和担保费处理成功
                    if (loan.getPlatformFee().compareTo(BigDecimal.ZERO) > 0) {
                    	// 进行平台费处理
                    	operateInfo = paymentService.ciccDoPlatformFee(loan);
                    }
                } else {
                    return operateInfo;
                }
            } else {
                return new OperateInfo(2, "放款金额不能为0");
            }
            
            
            // ----------更新融资数据----------------//
            
            if (operateInfo.getRetCode() == 0) {
                loan.setLoanTime(DateUtils.getCurrentDateTime());
                // 融资明细填充
                CalculatorResultDto calculatorResultDto = CalculatorUtils
                        .interestCalculator(
                                LoanInterestMannerEnum.getEnumByKey(loan
                                        .getLoanInterestManner()),
                                false,
                                new BigDecimal(loan.getActualAmount()),
                                loan.getLoanInterestRates().divide(
                                        new BigDecimal(100)), loan
                                        .getLoanTimeLimit(),
                                LoanTimeLimitUnitEnum.getEnumByKey(loan
                                        .getLoanTimeLimitUnit()), loan
                                        .getCarryInterestFrom());
                BigDecimal totalInterest = calculatorResultDto
                        .getTotalInterest();
                if (totalInterest != null) {
                    // 预期利息
                    loan.setLoanInterest(totalInterest);
                    // 应收本金
                    loan.setReceivablePrincipal(new BigDecimal(loan
                            .getActualAmount()));
                    // 应收利息
                    loan.setReceivableInterest(totalInterest);
                    // 应收滞纳金
                    loan.setReceivableDelaying(BigDecimal.ZERO);
                    // 应收逾期罚金
                    loan.setReceivableOverdue(BigDecimal.ZERO);
                    // 应收总额=应收本金+应收利息+应收滞纳金+应收逾期罚金
                    loan.setReceivableSum(loan.getReceivablePrincipal()
                            .add(loan.getReceivableInterest())
                            .add(loan.getReceivableDelaying())
                            .add(loan.getReceivableOverdue()));
                    // 已收总额
                    loan.setReceivedSum(BigDecimal.ZERO);
                    // 已收本金
                    loan.setReceivedPrincipal(BigDecimal.ZERO);
                    // 已收利息
                    loan.setReceivedInterest(BigDecimal.ZERO);
                    // 已收滞纳金
                    loan.setReceivedDelaying(BigDecimal.ZERO);
                    // 已收逾期罚金
                    loan.setReceivedOverdue(BigDecimal.ZERO);

                    List<CalculatorResultInstallmentsDto> installments = calculatorResultDto
                            .getInstallments();
                    // 当期还款期数
                    loan.setReceivableRepayNumber(1);
                    loan.setTotalRepayNumber(installments.size());
                    for (CalculatorResultInstallmentsDto dto : installments) {
                        if (dto.getNum() == 1) {
                            loan.setReceivableNextDate(dto.getRepaymentDate());
                            loan.setReceivableNextSum(dto.getTotal());
                            loan.setReceivableRepayStatus(RepaymentStatusEnum.REPAYMENT_STATUS_ZERO
                                    .getCode());
                        }
                        // TODO 还款详细编号生成规则未定
                        String repayDetailCode = loan.getLoanCode()
                                + String.valueOf(dto.getNum());

                        RepayDetail detail = new RepayDetail();
                        detail.setId(StringUtils.getUUID());
                        detail.setLoanCode(loan.getLoanCode());
                        detail.setRepayDetailCode(repayDetailCode);
                        detail.setNum(dto.getNum());
                        detail.setRepayPlanDate(dto.getRepaymentDate());
                        detail.setReceivableInterest(dto.getInterest());
                        detail.setReceivablePrincipal(dto.getPrincipal());
                        detail.setReceivableSum(dto.getTotal());
                        detail.setTenantId(loginUser.getId());
                        detail.setStatus(RepaymentStatusEnum.REPAYMENT_STATUS_ZERO
                                .getCode());
                        detail.setSerialNumber(GUID.getTxNo());
                        detail.setReceivedInterest(BigDecimal.ZERO);
                        detail.setReceivedPrincipal(BigDecimal.ZERO);
                        detail.setReceivedSum(BigDecimal.ZERO);
                        detail.setOverdueDays(0);
                        detail.setPunitiveDelayPayment(BigDecimal.ZERO);
                        detail.setPunitiveInterest(BigDecimal.ZERO);
                        // 插入融资明细数据
                        repayDetailRepository.insert(detail);
                    }

                } else {
                    throw new Exception();
                }

                // 投资详细填充
                CustomInvestCriteria criteria = new CustomInvestCriteria();
                criteria.setLoanCode(loan.getLoanCode());
                List<String> investStatus = new ArrayList<String>();
                investStatus.add(InvestStatusEnmu.INVEST_STATUS_02.getCode());
                investStatus.add(InvestStatusEnmu.INVEST_STATUS_14.getCode());
                criteria.setStatus(investStatus);

                List<Invest> effectiveInvestes = investQuery
                        .findEffectInvestListByCriteria(criteria);
                if (effectiveInvestes != null && effectiveInvestes.size() > 0) {
                    for (Invest invest : effectiveInvestes) {
                        invest.setStatus(InvestStatusEnmu.INVEST_STATUS_07
                                .getCode());
                        invest.setInterestDate(loan.getCarryInterestFrom());
                        invest.setPaymentStartDate(loan.getCarryInterestFrom());
                        invest.setEndDate(loan.getCarryInterestTo());

                        calculatorResultDto = CalculatorUtils
                                .interestCalculator(
                                        LoanInterestMannerEnum.getEnumByKey(loan
                                                .getLoanInterestManner()),
                                        true,
                                        invest.getInvestmentAmount(),
                                        invest.getInvestInterstRate().divide(
                                                new BigDecimal(100)),
                                        invest.getInvestLimit(),
                                        LoanTimeLimitUnitEnum.getEnumByKey(invest
                                                .getInvestTimeLimitUnit()),
                                        invest.getInterestDate());
                        List<CalculatorResultInstallmentsDto> installments = calculatorResultDto
                                .getInstallments();
                        // 已收本金
                        invest.setReceivedPrincipal(BigDecimal.ZERO);
                        // 已收利息
                        invest.setReceivedInterest(BigDecimal.ZERO);
                        // 应收本金
                        invest.setReceivablePrincipal(invest
                                .getInvestmentAmount());
                        // 应收利息
                        invest.setReceivableInterest(calculatorResultDto
                                .getTotalInterest());
                        // 已收罚息
                        invest.setReceivedPunitive(BigDecimal.ZERO);
                        // 待收罚息
                        invest.setReceivablePunitive(BigDecimal.ZERO);
                        // 发短信，站内信(投资人)
                        WebUser webUser = webUserRepository.findOne(invest.getUserId());
                        webUserService.sendInternalFrontMessage(webUser.getId(), "11", invest.getInvestCode());

                        for (CalculatorResultInstallmentsDto dto : installments) {
                            // 投资明细编号负责未定 TODO
                            String investDetailCode = invest.getInvestCode()
                                    + dto.getNum();

                            InvestDetail detail = new InvestDetail();
                            detail.setId(StringUtils.getUUID());
                            detail.setInvestDetailCode(investDetailCode);
                            detail.setNum(dto.getNum());
                            detail.setRecievePlanDate(dto.getRepaymentDate());
                            detail.setReceivablePrincipal(dto.getPrincipal());
                            detail.setReceivableInterest(dto.getInterest());
                            detail.setReceivableSum(dto.getTotal());
                            detail.setStatus(InvestDetailStatusEnum.INVEST_DETAIL_STATUS_00
                                    .getCode());
                            detail.setInvestCode(invest.getInvestCode());
                            detail.setLoanCode(loan.getLoanCode());
                            detail.setLoanName(loan.getLoanName());
                            detail.setTenantId(loginUser.getId());

                            detail.setSerialNumber(GUID.getTxNo());
                            // 已收本金
                            detail.setReceivedPrincipal(BigDecimal.ZERO);
                            // 已收利息
                            detail.setReceivedInterest(BigDecimal.ZERO);
                            // 已收总金额
                            detail.setRecievedSum(BigDecimal.ZERO);
                            // 逾期天数
                            detail.setPunitiveDay(BigDecimal.ZERO);
                            // 逾期罚息
                            detail.setPunitiveInterest(BigDecimal.ZERO);
                            // 投资用户
                            detail.setUserId(invest.getUserId());
                            if (dto.getNum() == 1) {
                                // 下期还款日
                                invest.setNextPaymentDate(dto
                                        .getRepaymentDate());
                                // 下期还款金额
                                invest.setNextPaymentAmount(dto.getTotal());
                                // 下期还款期号
                                invest.setNextPaymentNo(dto.getNum() + "");
                                // 还款总期数
                                invest.setPaymentTotalNum(installments.size());
                            }
                            // 插入投资明细数据
                            investDetailRepository.insert(detail);
                        }
                        // 更新投资数据
                        investRepository.dynamicUpdate(invest);
                    }
                }
                // 融资状态改为还款中
                loan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_10.getCode());
                loanRepository.dynamicUpdate(loan);

                // 发短信，站内信(后台人员)
                webUserService.sendInternalBackMessage("11", loan.getLoanName(), loan.getLoanCode());

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateInfo(1, "系统异常请稍后再试!");
        }

        return operateInfo;

    }

    

    /**
     * 融资流标退款
     */
    @Override
    public String doloanFlowExamine(String pstrLoanCode, String pstrLoanId, UserContext loginuser) {
        Loan loan = loanRepository.findOne(pstrLoanId);
        CustomInvestCriteria criteria = new CustomInvestCriteria();
        criteria.setLoanCode(pstrLoanCode);
        List<String> status = new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_02.getCode());
        status.add(InvestStatusEnmu.INVEST_STATUS_14.getCode());
        criteria.setStatus(status);
        // 取出投资信息
        List<Invest> lstInvest = investQuery.findEffectInvestListByCriteria(criteria);
        if (lstInvest != null) {
            for (Invest invest : lstInvest) {

                OperateInfo operateInfo = paymentService.ciccDoRefund(loan, invest);
                if (operateInfo.getRetCode() == 0) {
                    // 融资退款信息设置
                    Refund refund = new Refund();
                    /** 退款ID */
                    refund.setId(StringUtils.getUUID());
                    /** 退款编号 */
                    // refund.setRefundCode("");
                    /** 退款金额 */
                    refund.setAmount(invest.getInvestmentAmount());
                    /** 交易时间 */
                    refund.setRefundTime(new Date());
                    /** 到账时间 */
                    refund.setToaccountTime(new Date());
                    /** 交易类型 */
                    refund.setType(TransactionTypeEnum.LOAN_STATUS_2.getCode());
                    /** 交易状态 */
                    refund.setStatus(TransactionStatusEnum.LOAN_STATUS_2
                            .getCode());
                    /** 投资编号 */
                    refund.setInvestCode(invest.getInvestCode());
                    /** 投资客户编号 */
                    refund.setUserId(invest.getUserId());
                    /** 融资编号 */
                    refund.setLoanCode(invest.getLoanCode());
                    /** 交易流水号 */
                    refund.setSerialNumber(invest.getSerialNumber());
                    /** 商户ID */
                    refund.setTenantId(invest.getTenantId());
                    /** 创建时间 */
                    refund.setCreateTime(new Date());
                    /** 更新时间 */
                    refund.setUpdateTime(new Date());
                    /** 创建者 */
                    refund.setCreateUserId(loginuser.getId());
                    /** 更新者 */
                    refund.setUpdateUserId(loginuser.getId());
                    /** 版本 */
                    refund.setVersion(0);
                    // 插入退款数据
                    refundRepository.insert(refund);
                    // 更新投资状态：04 "流标退款中"
                    invest.setStatus(InvestStatusEnmu.INVEST_STATUS_06
                            .getCode());
                    // 更改投资状态
                    investRepository.dynamicUpdate(invest);

                    // 发短信，站内信
                    WebUser webUser = webUserRepository.findOne(invest.getUserId());
                    webUserService.sendInternalFrontMessage(webUser.getId(), "9", invest.getInvestCode());

                }
            }
        }
        // 条件设置
        Loan updateLoan = new Loan();
        // 条件：融资ID
        updateLoan.setId(pstrLoanId);
        // 更新融资状态：流标退款完成
        updateLoan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_09.getCode());
        // 增加 更新时间，author:  date:2015-10-03 16:48
        updateLoan.setUpdateTime(new java.util.Date());
        // 更改融资信息
        loanRepository.dynamicUpdate(updateLoan);
        // 发短信，站内信
        webUserService.sendInternalBackMessage("12", loan.getLoanName(),loan.getLoanCode());
        return "flow";
    }



    /**
     * 融资人还款
     * 融资还款可以多期合在一个归返 还款分为三种 融资人还款、平台垫付、担保人代偿 还款金额包括 本金，利息，罚息，滞纳金
     */
    public OperateInfo doLoanDetailRepayment(
    		String loanCode,
    		String passWord,
            UserContext loginUser,
            String repaymentAccountTypeEnumKey) throws Exception {
    	
    	OperateInfo operateInfo = null;
    	boolean result = checkUserPasswordService.checkPassword(passWord,
                loginUser.getId(), true);
    	
    	// 操作密码正确
    	if (result!=true) {
    		operateInfo = new OperateInfo(2, "操作密码错误!");
    	}
    	
        // 获取对应融资信息
        Loan loan = loanRepository.findLoanByLoanCode(loanCode);
        RepayDetailRepaymentCriteria criteria = new RepayDetailRepaymentCriteria();
        criteria.setLoanCode(loanCode);
        AccountTypeEnum repaymentAccountTypeEnum = AccountTypeEnum.getEnumByKey(repaymentAccountTypeEnumKey);

        // 如果不是第一期还款，需要判断上一期是否已经还款
        // 当期还款期次
        Integer receivableRepayNumber = loan.getReceivableRepayNumber();
        if (receivableRepayNumber > 1) {
            String preNum = String.valueOf(receivableRepayNumber - 1);
            List<String> list = new ArrayList<String>();
            list.add(preNum);
            criteria.setNums(list);
            List<RepayDetail> preDetails = repayDetailQuery
                    .findByRepayDetailRepaymentCriteria(criteria);
            RepayDetail detail = preDetails.get(0);
            if (StringUtils.compare(
                    RepaymentStatusEnum.REPAYMENT_STATUS_ZERO.getCode(),
                    detail.getStatus())
                    || StringUtils.compare(
                            RepaymentStatusEnum.REPAYMENT_STATUS_TWO.getCode(),
                            detail.getStatus())) {
                return new OperateInfo(2, "上期还没有还清");
            }
        }
        
        // 获取还款明细
        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(receivableRepayNumber));
        criteria.setNums(list);
        List<RepayDetail> details = repayDetailQuery
                .findByRepayDetailRepaymentCriteria(criteria);

        if (details != null && details.size() > 0) {
            // 判读是否有重复还款
            boolean canPay = true;
            int repayNum = 0;
            for (int i = 0; i < details.size(); i++) {
                if (StringUtils.compare(
                        RepaymentStatusEnum.REPAYMENT_STATUS_ONE.getCode(),
                        details.get(i).getStatus())
                        || StringUtils.compare(
                                RepaymentStatusEnum.REPAYMENT_STATUS_THREE
                                        .getCode(), details.get(i).getStatus())
                        || StringUtils.compare(
                                RepaymentStatusEnum.REPAYMENT_STATUS_FOUR
                                        .getCode(), details.get(i).getStatus())
                        || StringUtils.compare(
                                RepaymentStatusEnum.REPAYMENT_STATUS_SIX
                                        .getCode(), details.get(i).getStatus())

                        || StringUtils.compare(
                                RepaymentStatusEnum.REPAYMENT_STATUS_EIGHT
                                        .getCode(), details.get(i).getStatus())
                        || StringUtils.compare(
                                RepaymentStatusEnum.REPAYMENT_STATUS_TEN
                                        .getCode(), details.get(i).getStatus())

                ) {
                    canPay = false;
                    repayNum = details.get(i).getNum();
                    break;
                }
            }
            if (!canPay) {
                return new OperateInfo(1, "period:" + repayNum + " has repaying operation.");
            }
        }

            
        RepayDetail repayDetail = details.get(0);
        BigDecimal repaymount = repayDetail.getReceivableSum().add(repayDetail.getPunitiveDelayPayment()).add(
        		repayDetail.getPunitiveInterest());

        if (repaymount.compareTo(BigDecimal.ZERO) <= 0) {
        	operateInfo = new OperateInfo(2, "还款金额必须大于0!");
        }
        	
    	//调用融资人还款接口
        operateInfo = paymentService.ciccDoRepayment(details, loan, repaymentAccountTypeEnum);
        if (operateInfo.getRetCode() == 0) {
            // 更新明细
            for (RepayDetail detail : details) {
                // 还款用户类别
                detail.setAccountType(repaymentAccountTypeEnum
                        .getCode());
                detail.setStatus(RepaymentStatusEnum.REPAYMENT_STATUS_SIX
                        .getCode());// 财务审批中
                loan.setReceivableRepayStatus(RepaymentStatusEnum.REPAYMENT_STATUS_SIX
                        .getCode());
                // 还款账号
                if (AccountTypeEnum.Financier == repaymentAccountTypeEnum) {// 融资人正常还款
                    detail.setAccountNo(loan.getLoanCode());
                } else if (AccountTypeEnum.Platform == repaymentAccountTypeEnum) {// 平台垫付
                	detail.setAccountNo("001");
                } else if (AccountTypeEnum.Guarantee == repaymentAccountTypeEnum) {// 担保公司代偿
                    detail.setAccountNo(loan.getGuaranteeComCode());
                }
                repayDetailRepository.dynamicUpdate(detail);
            }

            loanRepository.dynamicUpdate(loan);

        } else if (operateInfo.getRetCode() == 1) {
            operateInfo = new OperateInfo(1, "系统错误请联系管理员");
        } else if (operateInfo.getRetCode() == 2) {
            operateInfo = new OperateInfo(2,
                    operateInfo.getErrMessage());
        } else if (operateInfo.getRetCode() == 4) {
            operateInfo = new OperateInfo(2,
                    operateInfo.getErrMessage());
        }
        
        //融资对应还款信息更新  and 投资对应还款信息更新
        this.updateLoanInfo(loan, repayDetail);    

        return new OperateInfo(0, "repay success!");
    }
    
    
	/**
	 * 融资对应还款信息更新
	 */
    private void updateLoanInfo(Loan pLoan, RepayDetail pRepayDetail) throws Exception {
    	
        BigDecimal bdInterest = BigDecimal.ZERO;
        BigDecimal bdPrincipal = BigDecimal.ZERO;
        BigDecimal bdTotalAmount = BigDecimal.ZERO;
        // 更新投资明细表
        UpdateInvestDto updateInvestDto = this.updateInvestInfo(pLoan);
       
        // 已收利息
        bdInterest = updateInvestDto.getInterest();
        // 已收本金
        bdPrincipal = updateInvestDto.getPrincipal();
        // 已收总金额
        bdTotalAmount = updateInvestDto.getTotalAmount();
        RepayDetail repayDetail = null;
        if (pRepayDetail != null) {
            repayDetail = pRepayDetail;
        } else {
            // 融资还款明细查询
            RepayDetailCriteria repayDetailCriteria = new RepayDetailCriteria();
            repayDetailCriteria.setLoanCode(pLoan.getLoanCode(), Operator.equal);
            repayDetailCriteria.setNum(pLoan.getReceivableRepayNumber(), Operator.equal);
            List<RepayDetail> lstRepayDetail = repayDetailRepository.findByCriteria(repayDetailCriteria);
            repayDetail = lstRepayDetail.get(0);
        }
        
        // 投资明细表更新失败
        if (!updateInvestDto.isPayStatus()) {
            // 下一条融资项目还款
            // 还款失败的短信
            String repayPlanDateStr = DateUtils.fullTime(repayDetail.getRepayPlanDate());
            BigDecimal bdSum = repayDetail.getReceivableSum().add(repayDetail.getPunitiveDelayPayment()).add(repayDetail.getPunitiveInterest());
            webUserService.sendInternalBackMessage("20", pLoan.getLoanName(), pLoan.getLoanCode(), repayPlanDateStr,
                    bdSum.toString());
            return;
        }
        
        // 实际收款日
        repayDetail.setRepayRealDate(new Date());
        // 已收利息
        repayDetail.setReceivedInterest(repayDetail.getReceivableInterest());
        // 已收本金
        repayDetail.setReceivedPrincipal(repayDetail.getReceivablePrincipal());
        BigDecimal bdReceivableSum = repayDetail.getReceivableSum();
        // 已收总金额
        repayDetail.setReceivedSum(bdReceivableSum.add(repayDetail.getPunitiveDelayPayment()).add(
                repayDetail.getPunitiveInterest()));
        // 有滞纳金
        if (BigDecimal.ZERO.compareTo(repayDetail.getPunitiveDelayPayment()) != 0) {
            OperateInfo operateInfo = paymentService.ciccDoPlatformFee(pLoan);
        }
        // 还款状态
        repayDetail.setStatus(RepaymentStatusEnum.REPAYMENT_STATUS_ONE.getCode());
        // 融资表还款信息更新
        // 已收总额
        pLoan.setReceivedSum(bdReceivableSum.add(pLoan.getReceivedSum()));
        // 已收本金
        pLoan.setReceivedPrincipal(bdPrincipal.add(pLoan.getReceivedPrincipal()));
        // 已收滞纳金
        pLoan.setReceivedDelaying(this.getBigDecimalData(pLoan.getReceivedDelaying()).add(
                this.getBigDecimalData(repayDetail.getPunitiveDelayPayment())));
        // 已收逾期罚金
        pLoan.setReceivedOverdue(this.getBigDecimalData(pLoan.getReceivedOverdue()).add(
                this.getBigDecimalData(repayDetail.getPunitiveInterest())));
        Date receivableNextDate = updateInvestDto.getNextPaymentDate();
        if (receivableNextDate != null) {
            // 已收利息
            pLoan.setReceivedInterest(bdInterest.add(pLoan.getReceivedInterest()));
            // 下次还款日
            pLoan.setReceivableNextDate(receivableNextDate);
            // 下期期号
            pLoan.setReceivableRepayNumber(updateInvestDto.getNum());
            // 当期还款状态
            pLoan.setReceivableRepayStatus(updateInvestDto.getNextStatus());
            
            // 融资还款明细查询
            RepayDetailCriteria repayDetailCriteria = new RepayDetailCriteria();
            repayDetailCriteria.setLoanCode(pLoan.getLoanCode(), Operator.equal);
            repayDetailCriteria.setNum(updateInvestDto.getNum(), Operator.equal);
            List<RepayDetail> lstRepayDetail = repayDetailRepository.findByCriteria(repayDetailCriteria);
            RepayDetail nextRepayDetail = lstRepayDetail.get(0);
            pLoan.setReceivableNextSum(nextRepayDetail.getReceivableSum().add(
                    this.getBigDecimalData(nextRepayDetail.getPunitiveDelayPayment())).add(
                    this.getBigDecimalData(nextRepayDetail.getPunitiveInterest())));
        } else {
            // 已收利息
            pLoan.setReceivedInterest(pLoan.getReceivableInterest());
            // 当期还款状态
            pLoan.setReceivableRepayStatus(RepaymentStatusEnum.REPAYMENT_STATUS_ONE.getCode());
            // 融资状态
            pLoan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_11.getCode());
            // 下次还款金额
            pLoan.setReceivableNextSum(BigDecimal.ZERO);
        }
        repayDetailRepository.dynamicUpdate(repayDetail);
        loanRepository.dynamicUpdate(pLoan);
        
    }
	
    
    /**
     * 投资对应还款信息更新
     */
	private UpdateInvestDto updateInvestInfo(Loan ploan) {
		 
        UpdateInvestDto ouputDto = new UpdateInvestDto();
        boolean blFlag = false;
        InvestDetailCriteria criteria = new InvestDetailCriteria();
        // 融资编号
        criteria.setLoanCode(ploan.getLoanCode(), Operator.equal);
        // 还款期号
        criteria.setNum(ploan.getReceivableRepayNumber(), Operator.equal);
        // 还款信息查询
        List<InvestDetail> lstInvestDetail = investDetailRepository.findByCriteria(criteria);
        // 累计总金额
        BigDecimal bdCountTotalAmount = BigDecimal.ZERO;
        // 累计本金
        BigDecimal bdCountPrincipal = BigDecimal.ZERO;
        // 累计利息
        BigDecimal bdCountInterest = BigDecimal.ZERO;
        // 累计罚息
        BigDecimal bdCountPunitiveInterest = BigDecimal.ZERO;
        // 总金额
        BigDecimal bdReceivedSum = BigDecimal.ZERO;
        // 利息
        BigDecimal bdInterest = BigDecimal.ZERO;
        if (lstInvestDetail != null && !lstInvestDetail.isEmpty()) {
            InvestCriteria investCriteria = null;
            for (InvestDetail investDetail : lstInvestDetail) {
                investCriteria = new InvestCriteria();
                // 投资编号
                investCriteria.setInvestCode(investDetail.getInvestCode(), Operator.equal);
                // 获取投资客户回款账户
                List<Invest> lstInvest = investRepository.findByCriteria(investCriteria);
                Invest invest = lstInvest.get(0);
                // 实际还款金额
                bdReceivedSum = this.getBigDecimalData(investDetail.getReceivableSum()).add(
                        this.getBigDecimalData(investDetail.getPunitiveInterest()));
                // 总金额累加
                bdCountTotalAmount = bdCountTotalAmount.add(bdReceivedSum);
                // 本金累加
                bdCountPrincipal = bdCountPrincipal.add(investDetail.getReceivablePrincipal());
                // 实际利息
                bdInterest = this.getBigDecimalData(investDetail.getReceivableInterest()).add(
                        this.getBigDecimalData(investDetail.getPunitiveInterest()));
                // 利息累加 
                bdCountInterest = bdCountInterest.add(this.getBigDecimalData(investDetail.getReceivableInterest()));
                // 逾期罚息累加
                bdCountPunitiveInterest = bdCountPunitiveInterest.add(investDetail.getPunitiveInterest());
                
                // 调用投资还款接口
                OperateInfo operateInfo = paymentService.ciccDoInvestReceive(ploan, invest, investDetail);

                if (operateInfo.getRetCode() == 0) {
                    // 实际还款日
                    investDetail.setRecieveRealDate(new Date());
                    // 已收本金
                    investDetail.setReceivedPrincipal(investDetail.getReceivablePrincipal());
                    // 已收利息
                    investDetail.setReceivedInterest(bdInterest);
                    // 已收总金额
                    investDetail.setRecievedSum(bdReceivedSum);
                    // 还款状态
                    investDetail.setStatus(InvestDetailStatusEnum.INVEST_DETAIL_STATUS_01.getCode());
                    investDetailRepository.dynamicUpdate(investDetail);
                    // 投资主表数据
                    // 已收本金
                    invest.setReceivedPrincipal(investDetail.getReceivablePrincipal().add(this.getBigDecimalData(invest
                            .getReceivedPrincipal())));
                    // 已收利息
                    invest.setReceivedInterest(this.getBigDecimalData(investDetail.getReceivedInterest()).add(this.getBigDecimalData(invest
                            .getReceivedInterest())));
                    // 罚息
                    invest.setReceivedPunitive(this.getBigDecimalData(invest.getReceivedPunitive()).add(
                            investDetail.getPunitiveInterest()));
                    // 下期还款状态
                    String strNextStatus = RepaymentStatusEnum.REPAYMENT_STATUS_ZERO.getCode();
                    // 下期还款期号处理
                    // 如果还款期数有最后一期就表示已经还完了,投资就结束了
                    if (investDetail.getNum().equals(invest.getPaymentTotalNum())) {// 暂时还没有提前还款处理，即使是提前还款了，也便是为正常还款
                        invest.setStatus(InvestStatusEnmu.INVEST_STATUS_09
                                .getCode());
                    } else {
                        // 不是最后一期还款的处理
                        // 获取下一期数据
                        InvestDetailCriteria detailCriteria = new InvestDetailCriteria();
                        detailCriteria.setInvestCode(invest.getInvestCode(),
                                Operator.equal);
                        detailCriteria.setLoanCode(ploan.getLoanCode(),
                                Operator.equal);
                        detailCriteria.setNum(investDetail.getNum() + 1,
                                Operator.equal);
                        List<InvestDetail> nextInvestDetails = investDetailRepository
                                .findByCriteria(detailCriteria);
                       
                        if (nextInvestDetails != null && !nextInvestDetails.isEmpty()) {
                            InvestDetail d = nextInvestDetails.get(0);
                            // 下期还款日
                            invest.setNextPaymentDate(d.getRecievePlanDate());
                            ouputDto.setNextPaymentDate(d.getRecievePlanDate());
                            // 下期还款金额
                            invest.setNextPaymentAmount(d.getReceivableSum());
                            // 下期还款期号
                            invest.setNextPaymentNo(d.getNum().toString());
                            ouputDto.setNum(d.getNum());
                            if (BigDecimal.ZERO.compareTo(this.getBigDecimalData(d.getPunitiveDay())) != 0 ) {
                                strNextStatus = RepaymentStatusEnum.REPAYMENT_STATUS_TWO.getCode();
                            }
                        }
                    }
                    ouputDto.setNextStatus(strNextStatus);
                    investRepository.dynamicUpdate(invest);

                    // 发短信，站内信(投资人)
                    WebUser webUser = webUserService.findOne(invest.getUserId());
	                webUserService.sendInternalFrontMessage(webUser.getId(), "12", investDetail.getReceivableSum()
	                         .toString());
                    blFlag = true;
                }
            }
        }
        // 支付状态
        ouputDto.setPayStatus(blFlag);
        ouputDto.setTotalAmount(bdCountTotalAmount);
        ouputDto.setInterest(bdCountInterest);
        ouputDto.setPrincipal(bdCountPrincipal);
        ouputDto.setPunitiveInterest(bdCountPunitiveInterest);
        return ouputDto;
    }
	 
	 
	 
 
	 private BigDecimal getBigDecimalData(BigDecimal pbdData) {
	     BigDecimal bdOutputData = BigDecimal.ZERO;
	     if (pbdData != null) {
	         bdOutputData = pbdData;
	     }
	     return bdOutputData;
	 }


}
