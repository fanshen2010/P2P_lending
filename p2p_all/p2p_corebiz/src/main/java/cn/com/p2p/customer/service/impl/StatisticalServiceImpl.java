package cn.com.p2p.customer.service.impl;

import cn.com.p2p.customer.service.PersonalService;
import cn.com.p2p.customer.service.StatisticalService;
import cn.com.p2p.customer.service.dto.StatisticalDto;
import cn.com.p2p.domain.loan.criteria.LoanCriteria;
import cn.com.p2p.domain.loan.criteria.RepayDetailCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.loan.repository.RepayDetailRepository;
import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.RepaymentStatusEnum;
import cn.com.p2p.framework.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 个人/企业融资客户信息 接口实现
 * @author 
 *
 */
@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private RepayDetailRepository repayDetailRepository;

    /** 融资总金额（个人） 借款总金额（企业） */
    private BigDecimal loanAmount = BigDecimal.ZERO;
    /** 逾期总金额（个人） 逾期金额（企业） */
    private BigDecimal outTimeAmount = BigDecimal.ZERO;
    /** 待还总金额（个人） 待还金额（企业） */
    private BigDecimal waitPayAmount = BigDecimal.ZERO;
    /** 应还总金额 */
    private BigDecimal payAmount = BigDecimal.ZERO;
    /** 已还金额 */
    private BigDecimal hasPayAmount = BigDecimal.ZERO;
    /** 逾期待还 */
    private BigDecimal outTimeWaitPayAmount = BigDecimal.ZERO;
    /** 融资记录 */
    private Integer loanCount = 0;
    /** 逾期记录 */
    private Integer timeOutCount = 0;

    /**
     * 通过客户编号获取该客户融资i统计信息
     * @param code 个人/企业融资客户id
     * @return
     */
    @Override
    public StatisticalDto getLoanCount(String code) {
        // 初始化变量值
        loanAmount = BigDecimal.ZERO;		  // 融资总金额（个人） 借款总金额（企业）
        outTimeAmount = BigDecimal.ZERO;	  // 逾期总金额（个人） 逾期金额（企业）
        waitPayAmount = BigDecimal.ZERO;		  // 待还总金额（个人） 待还金额（企业）
        payAmount = BigDecimal.ZERO;			  // 应还总金额
        hasPayAmount = BigDecimal.ZERO;		  // 已还金额
        outTimeWaitPayAmount = BigDecimal.ZERO;// 逾期待还
        loanCount = 0;						  // 融资记录
        timeOutCount = 0;					  // 逾期记录

        // 查询该客户下所有融资记录
        LoanCriteria loanCriteria = new LoanCriteria();
        RepayDetailCriteria repayDetailCriteria = new RepayDetailCriteria();
        loanCriteria.setCustomCode(code, BaseCriteria.Operator.equal);
        List<Loan> loans = loanRepository.findByCriteria(loanCriteria);
        // 开始获取统计信息
        for (Loan loan : loans) {
            if (LoanStatusEnum.LOAN_STATUS_04.getCode().equals(loan.getLoanStatus())
                    || LoanStatusEnum.LOAN_STATUS_07.getCode().equals(loan.getLoanStatus())
                    || LoanStatusEnum.LOAN_STATUS_10.getCode().equals(loan.getLoanStatus())
                    || LoanStatusEnum.LOAN_STATUS_11.getCode().equals(loan.getLoanStatus())
                    || LoanStatusEnum.LOAN_STATUS_12.getCode().equals(loan.getLoanStatus())) {
                /** 融资总金额（个人） 借款总金额（企业） LOAN.LOAN_AMOUNT */
                if (loan.getLoanAmount() != null) {
                    loanAmount = loanAmount.add(BigDecimal.valueOf(loan.getLoanAmount()));
                }
                /** 逾期总金额（个人） 逾期金额（企业） LOAN.RECEIVABLE_OVERDUE */
                // if (loan.getReceivableOverdue() != null) {
                // outTimeAmount =
                // outTimeAmount.add(loan.getReceivableOverdue()).add(loan.getReceivableDelaying()).subtract(loan.getReceivedDelaying()).subtract(loan.getReceivedOverdue());
                // }
                /** 待还总金额（个人） 待还金额（企业） LOAN.RECEIVABLE_SUM-LOAN.RECEIVED_SUM */
                if (loan.getReceivableSum() != null && loan.getReceivedSum() != null) {
                    if (loan.getReceivableSum() != null && loan.getReceivedSum() != null) {
                        waitPayAmount = waitPayAmount.add(loan.getReceivableSum().subtract(loan.getReceivedSum()));
                    }
                }
                /** 应还总金额 LOAN.RECEIVABLE_SUM */
                if (loan.getReceivableSum() != null) {
                    payAmount = payAmount.add(loan.getReceivableSum());
                }
                /** 已还金额 LOAN.RECEIVED_SUM */
                if (loan.getReceivedSum() != null) {
                    hasPayAmount = hasPayAmount.add(loan.getReceivedSum());
                }
                // 查询该融资对应融资还款明细列表
                repayDetailCriteria.setLoanCode(loan.getLoanCode(), BaseCriteria.Operator.equal);
                List<RepayDetail> repayDetails = repayDetailRepository.findByCriteria(repayDetailCriteria);
                for (RepayDetail repayDetail : repayDetails) {
                    if (Arrays.asList(RepaymentStatusEnum.REPAYMENT_STATUS_TWO.getCode(),
                            RepaymentStatusEnum.REPAYMENT_STATUS_FIVE.getCode(),
                            RepaymentStatusEnum.REPAYMENT_STATUS_SIX.getCode(),
                            RepaymentStatusEnum.REPAYMENT_STATUS_SEVEN.getCode(),
                            RepaymentStatusEnum.REPAYMENT_STATUS_TEN.getCode()).contains(repayDetail.getStatus())
                            && repayDetail.getOverdueDays() > 0) {
                        /**
                         * 逾期待还 REPAY_DETAIL.STATUS=2
                         * LOAN.RECEIVABLE_SUM-LOAN.RECEIVED_SUM 本金+利息
                         */
                        if (repayDetail.getReceivableSum() != null && repayDetail.getReceivedSum() != null) {
                            outTimeWaitPayAmount = outTimeWaitPayAmount.add(repayDetail.getReceivableSum()).subtract(
                                    repayDetail.getReceivedSum());
                        }
                    }
                    if (repayDetail.getOverdueDays() > 0) {
                        /**
                         * 逾期待还 REPAY_DETAIL.STATUS=2
                         * LOAN.RECEIVABLE_SUM-LOAN.RECEIVED_SUM 本金+利息
                         */
                        /** 逾期记录 LOAN.RECEIVABLE_REPAY_STATUS in (2,3,4) */
                        timeOutCount++;
                    }
                }
                if (Integer.parseInt(loan.getLoanStatus()) > Integer.parseInt(LoanStatusEnum.LOAN_STATUS_03.getCode())) {
                    /** 融资记录 LOAN.LOAN_STATUS > 03 */
                    loanCount++;
                }
//                if (Arrays.asList(RepaymentStatusEnum.REPAYMENT_STATUS_TWO.getCode(),
//                        RepaymentStatusEnum.REPAYMENT_STATUS_THREE.getCode(),
//                        RepaymentStatusEnum.REPAYMENT_STATUS_FOUR.getCode()).contains(loan.getReceivableRepayStatus())) {
//                    /** 逾期记录 LOAN.RECEIVABLE_REPAY_STATUS in (2,3,4) */
//                    timeOutCount++;
//                }
            }
        }
        StatisticalDto statisticalDto = new StatisticalDto();
        /** 融资总金额（个人） 借款总金额（企业） LOAN.LOAN_AMOUNT */
        statisticalDto.setLoanAmount(loanAmount);
        /** 逾期总金额（个人） 逾期金额（企业） LOAN.RECEIVABLE_OVERDUE */
        statisticalDto.setOutTimeAmount(outTimeAmount.add(outTimeWaitPayAmount));
        /** 待还总金额（个人） 待还金额（企业） LOAN.RECEIVABLE_SUM-LOAN.RECEIVED_SUM */
        statisticalDto.setWaitPayAmount(waitPayAmount);
        /** 应还总金额 LOAN.RECEIVABLE_SUM */
        statisticalDto.setPayAmount(payAmount);
        /** 已还金额 LOAN.RECEIVED_SUM */
        statisticalDto.setHasPayAmount(hasPayAmount);
        /** 逾期待还 REPAY_DETAIL.STATUS=2 LOAN.RECEIVABLE_SUM-LOAN.RECEIVED_SUM */
        statisticalDto.setOutTimeWaitPayAmount(outTimeAmount.add(outTimeWaitPayAmount));
        /** 融资记录 LOAN.LOAN_STATUS > 03 */
        statisticalDto.setLoanCount(loanCount);
        /** 逾期记录 LOAN.RECEIVABLE_REPAY_STATUS in (2,3,4) */
        statisticalDto.setTimeOutCount(timeOutCount);

        return statisticalDto;
    }
}
