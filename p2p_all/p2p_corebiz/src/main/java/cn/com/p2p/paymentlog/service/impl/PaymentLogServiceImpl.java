package cn.com.p2p.paymentlog.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.payment.criteria.PaymentLogCreateCriteria;
import cn.com.p2p.domain.payment.criteria.PaymentLogCriteria;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.domain.payment.query.CiccAccountQuery;
import cn.com.p2p.domain.payment.query.PaymentLogQuery;
import cn.com.p2p.domain.payment.repository.CiccAccountRepository;
import cn.com.p2p.domain.payment.repository.PaymentLogRepository;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.paymentlog.service.PaymentLogService;
import cn.com.p2p.paymentlog.service.dto.PaymentInvestDto;
import cn.com.p2p.paymentlog.service.dto.PaymentLoanDto;
import cn.com.p2p.paymentlog.service.dto.PaymentLogDto;

/**
 * Created by  on 2015/4/22.
 * 交易支付日志查询接口实现类
 */
@Service
public class PaymentLogServiceImpl implements PaymentLogService {

    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @Autowired
    private PaymentLogQuery paymentLogQuery;

    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private InvestQuery investQuery;
    @Autowired
    private CiccAccountRepository ciccAccountRepository;
    @Autowired
    private CiccAccountQuery ciccAccountQuery;

    @Autowired
    private LoanRepository loanRepository;
    private PaymentInvestDto investJson = new PaymentInvestDto();
    private PaymentLoanDto loanJson = new PaymentLoanDto();

    @Override
    public List<PaymentLog> findPageByCriteria(PaymentLogCriteria criteria) {
        return paymentLogRepository.findPageByCriteria(criteria);
    }

    @Override
    public List<PaymentLog> findPageByCriteria(PaymentLogCreateCriteria criteria) {
        return paymentLogQuery.findPageByCreateCriteria(criteria);
    }

    @Override
    public PaymentLogDto getPaymentLog(String id) {
        PaymentLogDto paymentLogDto = new PaymentLogDto();
        PaymentLog paymentLog = paymentLogRepository.findOne(id);
        paymentLogDto.setProjectName(paymentLog.getProjectName());
        paymentLogDto.setProjectNo(paymentLog.getProjectNo());
        // 投资信息
        paymentLogDto.setInvestDto(JsonPluginsUtil.jsonToBean(paymentLog.getInvestJosn(), PaymentInvestDto.class));
        // 融资信息
        paymentLogDto.setLoanDto(JsonPluginsUtil.jsonToBean(paymentLog.getLoanJson(), PaymentLoanDto.class));
        return paymentLogDto;
    }

    @Override
    public PaymentInvestDto getInvestJson(String paymentNo) {
        // 有投资交易流水号查询到对应投资记录
        Invest invest = investQuery.findOneBySerialNumber(paymentNo);
        if (!StringUtils.objectNull(invest)) {
            // 用户名
            investJson.setInvestorLogin(invest.getInvestUserName());
            // 支付投资交易流水号
            investJson.setSerialNumber(paymentNo);
            // 投资金额
            investJson.setInvestAmount(invest.getInvestmentAmount());
            // 支付金额 = 投资金额 - 优惠券金额 暂时没优惠券数据
            investJson.setPayAmount(invest.getInvestmentAmount());
            // 支付状态 须要到service中再设置
            investJson.setPayType(null);
            // 投资类型
            investJson.setInvestType(invest.getInvestType());
            if (StringUtils.isNotEmpty(invest.getReceivedAccount())) {
                // 由投资还款帐户查询到投资者帐户信息
                CiccAccount ciccAccount = ciccAccountQuery.findOneByPaymentAccountNumber(invest.getReceivedAccount());
                // 中金支付账户名称
                investJson.setCiccLogin(ciccAccount.getUserName());
                // 中金支付账户号码
                investJson.setCiccNo(ciccAccount.getPaymentAccountNumber());
            }
        }
        // 投资时间
        investJson.setPayDate(DateUtils.getCurrentDateTime());
        // 支付成功时间
        investJson.setPaySuccessDate(DateUtils.getCurrentDateTime());
        // 优惠券使用状态 : 未使用/已使用 未取得
        investJson.setCouponUseType(null);
        // 优惠券编号 未取得
        investJson.setCouponNo(null);
        // 优惠券金额 未取得
        investJson.setCouponAmount(null);
        return investJson;
    }

    @Override
    public PaymentLoanDto getLoanJson(String loanCode) {

        Loan loan = loanRepository.findLoanByLoanCode(loanCode);
        // 项目名称
        loanJson.setLoanName(loan.getLoanName());
        // 项目编号
        loanJson.setLoanCode(loan.getLoanCode());
        // 项目发行规模
        loanJson.setAmount(new BigDecimal(loan.getLoanAmount()));
        // 预期年化收益率
        loanJson.setLoanInterestRates(loan.getLoanInterestRates());
        // 起息日
        loanJson.setCarryInterestFrom(loan.getCarryInterestFrom());
        // 到息日
        loanJson.setCarryInterestTo(loan.getCarryInterestTo());
        // 项目投资期限
        loanJson.setLoanTimeLimit(loan.getLoanTimeLimit()
                + LoanTimeLimitUnitEnum.getEnumByKey(loan.getLoanTimeLimitUnit()).getValue());

        return loanJson;
    }

    @Override
    public PaymentLog getPaymentLogBySerialNumber(String serialNumber) {
        return paymentLogQuery.findOneBySerialNumber(serialNumber);
    }

}
