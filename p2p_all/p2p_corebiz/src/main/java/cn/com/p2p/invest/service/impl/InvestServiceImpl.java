package cn.com.p2p.invest.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment.tools.util.GUID;
import cn.com.p2p.common.dto.CalculatorResultDto;
import cn.com.p2p.common.dto.CalculatorResultInstallmentsDto;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.MyInvestDtoCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestCriteria;
import cn.com.p2p.domain.invest.dto.MyInvestDto;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.query.InvestDetailQuery;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.entity.LoanProjectInfo;
import cn.com.p2p.domain.loan.query.LoanQuery;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.payment.repository.CiccAccountRepository;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.invest.service.InvestService;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.utils.CalculatorUtils;

@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private LoanQuery loanQuery;
    @Autowired
    private InvestQuery investQuery;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private CiccAccountRepository ciccAccountRepository;
    @Autowired
    private InvestDetailQuery investDetailQuery;

    @Override
    public Invest invest(String loanCode, BigDecimal investAmount, UserContext loginUser) throws Exception {
        Invest invest = new Invest();
        // 融资信息
        LoanProjectInfo loan = loanQuery.findLoanProjectInfo(loanCode);

        if (loan.getCurrentSurplusShare() < investAmount.intValue()) {
            throw new Exception();
        }
        invest.setId(StringUtils.getUUID());
        invest.setInvestCode(getInvestCode(loanCode));

        invest.setLoanName(loan.getLoanName());

        invest.setInvestmentAmount(investAmount);

        invest.setInvestLimit(loan.getLoanTimeLimit());

        invest.setInvestTimeLimitUnit(loan.getLoanTimeLimitUnit());

        invest.setInvestmentTime(new Date());

        invest.setInvestInterstRate(loan.getLoanInterestRates());

        invest.setLoanCode(loanCode);
        invest.setStatus(InvestStatusEnmu.INVEST_STATUS_01.getCode());

        invest.setSerialNumber(GUID.getTxNo());

        CalculatorResultDto resulrDto = CalculatorUtils.interestCalculator(LoanInterestMannerEnum.getEnumByKey(loan
                .getLoanInterestManner()), true, investAmount,
                loan.getLoanInterestRates().divide(new BigDecimal(100)), loan.getLoanTimeLimit(), LoanTimeLimitUnitEnum
                        .getEnumByKey(loan
                                .getLoanTimeLimitUnit()), null);
        //
        invest.setReceivablePrincipal(investAmount);
        invest.setReceivedPrincipal(BigDecimal.ZERO);

        invest.setReceivableInterest(resulrDto.getTotalInterest());
        invest.setReceivedInterest(BigDecimal.ZERO);

        invest.setReceivablePunitive(BigDecimal.ZERO);
        invest.setReceivedPunitive(BigDecimal.ZERO);

        invest.setInvestInterst(resulrDto.getTotalInterest());

        invest.setUserId(loginUser.getId());
        invest.setInvestUserName(loginUser.getUsername());
        investRepository.insert(invest);
        return invest;
    }


    @Override
    public List<Invest> findPageByCriteriaByLoanCode(InvestCriteria criteria) {

        return investRepository.findPageByCriteria(criteria);
    }

    @Override
    public Invest investVerify(String loanCode, BigDecimal investAmount, UserContext loginUser) throws Exception {
        Invest invest = new Invest();
        // 融资信息
        LoanProjectInfo loan = loanQuery.findLoanProjectInfo(loanCode);

        if (loan.getCurrentSurplusShare() < investAmount.intValue()) {
            throw new Exception();
        }
        invest.setLoanName(loan.getLoanName());

        invest.setInvestmentAmount(investAmount);

        invest.setLoanCode(loanCode);

        CalculatorResultDto resulrDto = CalculatorUtils.interestCalculator(LoanInterestMannerEnum.getEnumByKey(loan
                .getLoanInterestManner()), true, investAmount,
                loan.getLoanInterestRates().divide(new BigDecimal(100)), loan.getLoanTimeLimit(), LoanTimeLimitUnitEnum
                        .getEnumByKey(loan.getLoanTimeLimitUnit()), null);

        invest.setReceivableInterest(resulrDto.getTotalInterest());
        if (loginUser != null) {
            invest.setUserId(loginUser.getId());
            invest.setInvestUserName(loginUser.getUsername());
        }
        return invest;
    }

    /**
     * 根据融资编号获取投资编号
     * @param loanCode
     * @return
     */
    private String getInvestCode(String loanCode) {
        String investCode = investQuery.findMaxInvestCodeByLoanCode(loanCode);
        if (StringUtils.isEmpty(investCode)) {
            investCode = loanCode + "00001";
        } else {
            int num = Integer.parseInt(investCode.substring(loanCode.length()));
            if (num >= 99999) {
                investCode = loanCode + (num + 1);
            } else {
                String newNum = "0000" + (num + 1);
                investCode = loanCode + newNum.substring(newNum.length() - 5);
            }
        }
        return investCode;
    }

    
    @Override
    public List<CalculatorResultInstallmentsDto> getPlan(String loanCode,
            BigDecimal investAmount) {
        // 融资信息
        LoanProjectInfo loan = loanQuery.findLoanProjectInfo(loanCode);

        CalculatorResultDto resulrDto = CalculatorUtils.interestCalculator(LoanInterestMannerEnum.getEnumByKey(loan
                .getLoanInterestManner()), true, investAmount,
                loan.getLoanInterestRates().divide(new BigDecimal(100)), loan.getLoanTimeLimit(), LoanTimeLimitUnitEnum
                        .getEnumByKey(loan.getLoanTimeLimitUnit()), null);

        return resulrDto.getInstallments();
    }

    @Override
    public List<MyInvestDto> findPageMyInvestByStatus(MyInvestDtoCriteria criteria) {
        // 根据筛选条件查询当前投资显示列表
        List<MyInvestDto> invests = investQuery.findPageMyInvestDtoList(criteria);
        // 当我的投资为待生效时，计算出页面显示的招标进度
        if (InvestStatusEnmu.INVEST_STATUS_02.getCode().equals(criteria.getStatus())) {
            for (MyInvestDto i : invests) {
                Integer InvestPercentage = 0;
                double result = (i.getCurrentInvestedShare() == null ? 0 : i.getCurrentInvestedShare().doubleValue())
                        / i.getLoanAmount().doubleValue() * 100;
                if (result > 100) {
                    result = 100;
                }
                InvestPercentage = new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                i.setInvestPercentage(InvestPercentage);
            }
        }
        // 返回查询结果
        return invests;
    }
    
    public List<Invest> getMyInvest(CustomInvestCriteria criteria){
        return investQuery.findEffectInvestListByCriteria(criteria);
    }

    @Override
    public Map<String, Integer> findInvestCount(String pstrUserId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        CustomInvestCriteria criteria = new CustomInvestCriteria();
        criteria.setUserId(pstrUserId);
        // 获取待生效的投资列表条数

        List<String> status = new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_02.getCode());
        status.add(InvestStatusEnmu.INVEST_STATUS_14.getCode());
        criteria.setStatus(status);
        Integer pendingCount = investQuery.findInvestCountByStatus(criteria);
        map.put("pendingCount", pendingCount);
        // 获回款中的投资列表条数
        status = new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_07.getCode());
        criteria.setStatus(status);
        Integer receivablesCount = investQuery.findInvestCountByStatus(criteria);
        map.put("receivablesCount", receivablesCount);
        // 获完成的投资列表条数
        status = new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_09.getCode());
        criteria.setStatus(status);
        Integer completeCount = investQuery.findInvestCountByStatus(criteria);
        map.put("completeCount", completeCount);
        return map;
    }

    @Override
    public Invest findInvestByInvestCode(String investCode) {
        // 声明返回的投资实体
        Invest invest = new Invest();
        // 声明查询条件
        InvestCriteria criteria = new InvestCriteria();
        // 设置查询条件为投资code为传入的code
        criteria.setInvestCode(investCode, Operator.equal);
        // 执行根据投资code查询出投资对象
        List<Invest> list = investRepository.findByCriteria(criteria);
        // 如果有查询结果，将查询结果赋值到返回的实体
        if (list != null && list.size() > 0) {
            invest = list.get(0);
        }
        // 返回查询结果
        return invest;
    }

    public String getSelectYear(List<String> lstYear) {
        String selectYear = "";
        // 获取当前年份
        String currentYear = DateUtils.formatCurrentDateTime("yyyy");
        if (lstYear != null && lstYear.size() > 0) {
            if (lstYear.contains(currentYear)) {
                // 判断当前年份是否是有回款计划，如果有回款计划，默认查询年份设置为当年
                selectYear = currentYear;
            } else {
                // 如果当年没有回款计划，循环回款计划的年份
                for (String year : lstYear) {
                    Integer iYear = new Integer(year);
                    Integer iCurrentYear = new Integer(currentYear);
                    // 判断当前循环的年份与当前年份的大小关系
                    if (iYear > iCurrentYear) {
                        // 如果当前循环的年份比当前年份大，将查询年份设置为当前循环年份
                        selectYear = iYear.toString();
                    } else {
                        // 如果当前循环年份比当前年份小，判断是否已经设置查询年份
                        // 这里不会出现相等的情况，相等的情况会在最外面的if中
                        if (StringUtils.isNotEmpty(selectYear)) {
                            // 如果已经设置查询年份，则可以跳出循环
                            break;
                        } else {
                            // 如果没有设置查询年份，设置查询年份为当前循环年份，并跳出循环
                            selectYear = iYear.toString();
                            break;
                        }
                    }
                }
            }
        }
        return selectYear;
    }


    @Override
    public List<MyInvestDto> findPageMyInvest(CustomInvestCriteria criteria) {
        // 根据筛选条件查询当前投资显示列表
        List<MyInvestDto> invests = investQuery.findPageInvestListByCriteria(criteria);
        // 当我的投资为待生效时，计算出页面显示的招标进度
        for (MyInvestDto i : invests) {
            Integer InvestPercentage = 0;
            double result = (i.getCurrentInvestedShare() == null ? 0 : i.getCurrentInvestedShare().doubleValue())
                    / i.getLoanAmount().doubleValue() * 100;
            if (result > 100) {
                result = 100;
            }
            InvestPercentage = new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
            i.setInvestPercentage(InvestPercentage);
        }
        // 返回查询结果
        return invests;
    }
    
}
