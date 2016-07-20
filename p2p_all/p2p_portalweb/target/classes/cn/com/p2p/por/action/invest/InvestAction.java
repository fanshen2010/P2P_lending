package cn.com.p2p.por.action.invest;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.common.dto.CalculatorResultInstallmentsDto;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.InvestCriteria.OrderField;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.dto.LoaneeLoanRedordDto;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;
import cn.com.p2p.framework.code.EnumCodeList;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.invest.service.InvestService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.usermangent.service.PfmTenantDepartmentManageService;
import cn.com.p2p.utils.Constants;

/**
 * 我要投资
 * 
 * @author 
 *
 */
@Namespace("/invest")
@Results({
        @Result(name = "investList", location = "investList.ftl", type = "freemarker"),
        @Result(name = "investDetail", location = "investDetail.ftl", type = "freemarker"),
        @Result(name = "investVerify", location = "investList.htm", type = "redirect"),
        @Result(name = "guaranteeCompProfile", location = "guaranteeCompProfile.ftl", type = "freemarker") })
public class InvestAction extends BaseAction {

    /** 版本 */
    private static final long serialVersionUID = 1L;

    public static final String ENUM_PACKAGE = "cn.com.p2p.framework.enumpack";
    public static final String NONE = "none";

    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;

    /** 融资共通查询参数 */
    private LoanCommSelCriteria criteria = new LoanCommSelCriteria();

    /** 融资查询接口 */
    @Autowired
    private LoanSearchService loanSearchService;

    /** 投资接口 */
    @Autowired
    private InvestService investService;

    /** 投资支付接口 */
    @Autowired
    private PaymentService paymentService;

    /** 融资信息 */
    private List<Loan> lstLoan;

    /** 融资Dto */
    private LoanDto loanDto;

    /** 融资编号 */
    private String loanCode;

    /** 投资金额 */
    private BigDecimal investAmount;

    /** 投资 */
    private Invest invest;

    /** 投资Id */
    private String investId;

    /**
     * 还款方式列表
     */
    private Map<String, String> rstList = new HashMap<String, String>();

    /**
     * 融资期限列表
     */
    private Map<String, String> loanTimeLimitList = new HashMap<String, String>();

    /**
     * 融资期限
     */
    private String loanTimeLimit;
    
    /**
     * 融资期限
     */
    private String loanInterestManner;
    /**
     * 系统时间
     */
    private Date sysDate;

    /**
     * 当前融资人融资记录
     */
    private LoaneeLoanRedordDto loaneeLoanRedordDto;

    /**
     * 投资记录
     */
    private List<Invest> investRecord = new ArrayList<Invest>();

    /** 投资记录查询 */
    private InvestCriteria investCriteria = new InvestCriteria();

    /** 已完结项目个数 */
    private int endloanNum;

    /** 已完结项目总金额 */
    private double endLoanSumAmount = 0;

    @Override
    public String init() throws Exception {
        return "investList";
    }

    /**
     * 投资列表查询
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "investList")
    public String search() throws Exception {
        sysDate = DateUtils.getCurrentDateTime();
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
        // lstStatus.add(LoanStatusEnum.LOAN_STATUS_13.getCode());
        criteria.setStatus(lstStatus);
        criteria.setLoanPostTime(DateUtils.getCurrentDateTime());

        if (StringUtils.isNotEmpty(loanTimeLimit)
                && !StringUtils.compare(loanTimeLimit, NONE)) {
            String[] timeLimitArr = loanTimeLimit.split("-");
            if (timeLimitArr.length > 0) {
                if (timeLimitArr.length > 1) {
                    criteria.setLoanTimeLimitMax(Integer
                            .parseInt(timeLimitArr[1]));
                }
                criteria.setLoanTimeLimitMin(Integer.parseInt(timeLimitArr[0]));
                criteria.setLoanTimeLimitUnit(LoanTimeLimitUnitEnum.Day
                        .getCode());
            }
        }
        if (StringUtils.isNotEmpty(loanInterestManner)
                && !StringUtils.compare(loanInterestManner, NONE)) {
            criteria.setLoanInterestManner(loanInterestManner);
        }else{
        	criteria.setLoanInterestManner(null);
        }
        lstLoan = loanSearchService.getPageLoanObjectList(criteria);

        // 还款方式列表
        EnumCodeList enumCodeList = new EnumCodeList(
                (Class<? extends Enum<?>>) Class.forName(ENUM_PACKAGE + "."
                        + "LoanInterestMannerEnum"));
        rstList = enumCodeList.toMap();

        // 融资期限列表
        enumCodeList = new EnumCodeList(
                (Class<? extends Enum<?>>) Class.forName(ENUM_PACKAGE + "."
                        + "LoanTimeLimitEnum"));
        loanTimeLimitList = enumCodeList.toMap();
        return "investList";
    }

    /**
     * 投资详细
     * 
     * @return
     * @throws JSONException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Action(value = "investDetail")
    public String detail() throws Exception {
        loanDto = loanSearchService.getLoanInfo(loanCode,
                SearchInfoTypeEnum.LOAN, SearchInfoTypeEnum.LOAN_INFO,
                SearchInfoTypeEnum.INVEST, SearchInfoTypeEnum.LOAN_RECORD,
                SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL);
        if (loanDto != null) {
            loaneeLoanRedordDto = loanSearchService
                    .getLoaneeLoanRedordDtoByCustomCode(loanDto.getCustomCode());
        }
        findPageByInvestCriteria();
        sysDate = new Date();
        return "investDetail";
    }

    /**
     * 投资确认
     * 
     * @return
     * @throws JSONException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Action(value = "investVerify")
    public void investVerify() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        loanCode = map.get("loanCode").toString();
        investAmount = BigDecimal.ZERO;

        if (!StringUtils.objectNull(map.get("investAmount"))) {
            String intMatch = "^[0-9]\\d*$";// 整数
            if (String.valueOf(map.get("investAmount")).matches(intMatch)) {
                investAmount = new BigDecimal(map.get("investAmount").toString());
            } else {
                map.put("result", false);
                map.put("html", "investment money must be integer");
                Struts2Utils.renderJson(map);
                return;
            }
        }
        // 获取融资记录
        loanDto = loanSearchService.getLoanInfo(loanCode,
                SearchInfoTypeEnum.LOAN);

        Integer minAmount = loanDto.getProjectInfoDto().getLoanStartShare();
        Integer maxAmount = loanDto.getProjectInfoDto()
                .getCurrentSurplusShare();
        if (maxAmount < minAmount && maxAmount > 0) {
            if (investAmount.compareTo(new BigDecimal(maxAmount)) != 0) {
                map.put("result", false);
                map.put("html", "investment money must be integer" + maxAmount);
                Struts2Utils.renderJson(map);
                return;
            }
        } else {
            if (maxAmount <= 0) {
                map.put("result", false);
                map.put("html", "this loan has became full scale");
                Struts2Utils.renderJson(map);
                return;
            }
            Integer loanUnitPrice = loanDto.getProjectInfoDto().getLoanUnitPrice();
            if (investAmount.compareTo(new BigDecimal(minAmount)) < 0) {
                map.put("result", false);
                map.put("html", "investment money must be no less than" + minAmount + "dollar");
                Struts2Utils.renderJson(map);
                return;
            }
            if (investAmount.compareTo(new BigDecimal(maxAmount)) > 0) {
                map.put("result", false);
                map.put("html", "investment money must be less than" + maxAmount + "dollar");
                Struts2Utils.renderJson(map);
                return;
            }
            // 递增金额校验
            BigDecimal unitPrice = investAmount.subtract(new BigDecimal(minAmount));

            //
            int unitCopies = unitPrice.intValue() / loanUnitPrice;

            int mold = unitPrice.intValue() % loanUnitPrice;

            if (mold > 0) {
                map.put("result", false);
                map.put("html", "loanUnitPrice" + loanUnitPrice);
                Struts2Utils.renderJson(map);
                return;
            } else {
                if (unitPrice.compareTo(new BigDecimal(unitCopies * loanUnitPrice)) > 0) {
                    map.put("result", false);
                    map.put("html", "loanUnitPrice" + loanUnitPrice);
                    Struts2Utils.renderJson(map);
                    return;
                }

            }
        }
        // 最大投资金额校验
        Integer maxInvestAmount = loanDto.getProjectInfoDto().getLoanMaxInvest();
        if (investAmount.compareTo(new BigDecimal(maxInvestAmount)) > 0) {
            map.put("result", false);
            map.put("html", "maxInvestAmount" + maxInvestAmount + "dollar");
            Struts2Utils.renderJson(map);
            return;
        }

        if (DateUtils.dateCompare(DateUtils.getCurrentDateTime(), loanDto
                .getProjectInfoDto().getLoanEndTime()) > 0) {
            map.put("result", false);
            map.put("html", "this loan has finished investing");
            Struts2Utils.renderJson(map);
            return;
        }

        invest = investService.investVerify(loanCode, investAmount,
                getLoginuser());

        map.put("invest", invest);

        // 获取当前账户的中金账号

        map.put("ciccAccount", null);
        String result = feroFreemarkerProcessor.process(
                Constants.INVEST_VERIFY_TEMPLATE, map, this);
        map.put("html", result);
        map.put("result", true);
        Struts2Utils.renderJson(map);
    }

    /**
     * 投资校验
     * 
     * @throws Exception
     */
    @Action(value = "investCheck")
    public void investCheck() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        loanCode = map.get("loanCode").toString();
        investAmount = BigDecimal.ZERO;

        if (!StringUtils.objectNull(map.get("investAmount"))) {
            String intMatch = "^[0-9]\\d*$";// 整数
            if (String.valueOf(map.get("investAmount")).matches(intMatch)) {
                investAmount = new BigDecimal(map.get("investAmount").toString());
            } else {
                map.put("result", false);
                map.put("html", "investment money must be integer");
                Struts2Utils.renderJson(map);
                return;
            }
        }
        // 获取融资记录
        loanDto = loanSearchService.getLoanInfo(loanCode,
                SearchInfoTypeEnum.LOAN);

        Integer minAmount = loanDto.getProjectInfoDto().getLoanStartShare();
        Integer maxAmount = loanDto.getProjectInfoDto()
                .getCurrentSurplusShare();
        if (maxAmount < minAmount && maxAmount > 0) {
            if (investAmount.compareTo(new BigDecimal(maxAmount)) != 0) {
                map.put("result", false);
                map.put("html", "investment money must be" + maxAmount);
                Struts2Utils.renderJson(map);
                return;
            }
        } else {
            if (maxAmount <= 0) {
                map.put("result", false);
                map.put("html", "this loan has became full scale");
                Struts2Utils.renderJson(map);
                return;
            }
            Integer loanUnitPrice = loanDto.getProjectInfoDto().getLoanUnitPrice();
            if (investAmount.compareTo(new BigDecimal(minAmount)) < 0) {
                map.put("result", false);
                map.put("html", "investment money must be no less than" + minAmount + "dollar");
                Struts2Utils.renderJson(map);
                return;
            }
            if (investAmount.compareTo(new BigDecimal(maxAmount)) > 0) {
                map.put("result", false);
                map.put("html", "investment money must be less than" + maxAmount + "dollar");
                Struts2Utils.renderJson(map);
                return;
            }

            // 递增金额校验
            BigDecimal unitPrice = investAmount.subtract(new BigDecimal(minAmount));

            //
            int unitCopies = unitPrice.intValue() / loanUnitPrice;

            int mold = unitPrice.intValue() % loanUnitPrice;

            if (mold > 0) {
                map.put("result", false);
                map.put("html", "loanUnitPrice" + loanUnitPrice);
                Struts2Utils.renderJson(map);
                return;
            } else {
                if (unitPrice.compareTo(new BigDecimal(unitCopies * loanUnitPrice)) > 0) {
                    map.put("result", false);
                    map.put("html", "loanUnitPrice" + loanUnitPrice);
                    Struts2Utils.renderJson(map);
                    return;
                }
            }
            // 最大投资金额校验
            Integer maxInvestAmount = loanDto.getProjectInfoDto().getLoanMaxInvest();
            if (investAmount.compareTo(new BigDecimal(maxInvestAmount)) > 0) {
                map.put("result", false);
                map.put("html", "maxInvestAmount" + maxInvestAmount + "dollar");
                Struts2Utils.renderJson(map);
                return;
            }
        }
        invest = investService.investVerify(loanCode, investAmount,
                getLoginuser());
        map.put("invest", invest);
        String result = feroFreemarkerProcessor.process(
                Constants.INVEST_CHECK_TEMPLATE, map, this);
        map.put("html", result);
        map.put("result", true);
        Struts2Utils.renderJson(map);
    }

    /**
     * 回款计划
     * 
     * @throws Exception
     */
    @Action(value = "investPlan")
    public void investPlan() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        loanCode = map.get("loanCode").toString();
        investAmount = BigDecimal.ZERO;
        if (!StringUtils.objectNull(map.get("investAmount"))) {
            investAmount = new BigDecimal(map.get("investAmount").toString());
        }

        List<CalculatorResultInstallmentsDto> installments = investService
                .getPlan(loanCode, investAmount);

        map.put("installments", installments);

        String result = feroFreemarkerProcessor.process(
                Constants.INVEST_PLAN_TEMPLATE, map, this);
        map.put("html", result);
        Struts2Utils.renderJson(map);

    }

    /**
     * 支付
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "gopay")
    public String gopay() throws Exception {
        invest = investService.invest(loanCode, investAmount, getLoginuser());
        OperateInfo operateInfo = paymentService.doPay(invest.getId());
        if (operateInfo.getRetCode() == 0) {
            //return operateInfo.getRetAction();
        	return null;
        }

        //return "investVerify";
        return null;
    }

    
    /**
     * 获取投资记录ajax分页数据
     * 
     * @throws Exception
     */
    @Action(value = "investRecordAjax")
    public void getInvestRecordAjaxPage() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        // Integer pageNum =
        // Integer.parseInt(map.get("currentPage").toString());
        // investCriteria.getPage().setCurrentPage(pageNum);
        findPageByInvestCriteria();
        // 获取融资记录
        loanDto = loanSearchService.getLoanInfo(loanCode,
                SearchInfoTypeEnum.LOAN);
        sysDate = DateUtils.getCurrentDateTime();
        map.put("investRecord", investRecord);
        map.put("investCriteria", investCriteria);
        map.put("loanDto", loanDto);

        String result = feroFreemarkerProcessor.process(
                Constants.INVEST_RECORD_TEMPLATE, map, this);
        map.put("html", result);
        Struts2Utils.renderJson(map);
    }

    private void findPageByInvestCriteria() {
        investCriteria.setStatus(InvestStatusEnmu.INVEST_STATUS_02.getCode(),
                Operator.greaterThanAndEqual);
        investCriteria.setLoanCode(loanCode, Operator.equal);
        investCriteria.setSortFields(OrderField.investmentTime, SortType.DESC);
        investRecord = investService
                .findPageByCriteriaByLoanCode(investCriteria);
    }


    /**
     * @return the criteria
     */
    public LoanCommSelCriteria getCriteria() {
        return criteria;
    }

    /**
     * @param criteria
     *            the criteria to set
     */
    public void setCriteria(LoanCommSelCriteria criteria) {
        this.criteria = criteria;
    }

    /**
     * @return the lstLoan
     */
    public List<Loan> getLstLoan() {
        return lstLoan;
    }

    /**
     * @param lstLoan
     *            the lstLoan to set
     */
    public void setLstLoan(List<Loan> lstLoan) {
        this.lstLoan = lstLoan;
    }

    /**
     * @return the loanCode
     */
    public String getLoanCode() {
        return loanCode;
    }

    /**
     * @param loanCode
     *            the loanCode to set
     */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    /**
     * @return the loanDto
     */
    public LoanDto getLoanDto() {
        return loanDto;
    }

    /**
     * @param loanDto
     *            the loanDto to set
     */
    public void setLoanDto(LoanDto loanDto) {
        this.loanDto = loanDto;
    }

    /**
     * @return the investAmount
     */
    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    /**
     * @param investAmount
     *            the investAmount to set
     */
    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    /**
     * @return the invest
     */
    public Invest getInvest() {
        return invest;
    }

    /**
     * @param invest
     *            the invest to set
     */
    public void setInvest(Invest invest) {
        this.invest = invest;
    }

    /**
     * @return the investId
     */
    public String getInvestId() {
        return investId;
    }

    /**
     * @param investId
     *            the investId to set
     */
    public void setInvestId(String investId) {
        this.investId = investId;
    }

    /**
     * @return the sysDate
     */
    public Date getSysDate() {
        return sysDate;
    }

    /**
     * @param sysDate
     *            the sysDate to set
     */
    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * @return the rstList
     */
    public Map<String, String> getRstList() {
        return rstList;
    }

    /**
     * @param rstList
     *            the rstList to set
     */
    public void setRstList(Map<String, String> rstList) {
        this.rstList = rstList;
    }

    /**
     * @return the loanTimeLimitList
     */
    public Map<String, String> getLoanTimeLimitList() {
        return loanTimeLimitList;
    }

    /**
     * @param loanTimeLimitList
     *            the loanTimeLimitList to set
     */
    public void setLoanTimeLimitList(Map<String, String> loanTimeLimitList) {
        this.loanTimeLimitList = loanTimeLimitList;
    }

    /**
     * @return the loanTimeLimit
     */
    public String getLoanTimeLimit() {
        return loanTimeLimit;
    }

    /**
     * @param loanTimeLimit
     *            the loanTimeLimit to set
     */
    public void setLoanTimeLimit(String loanTimeLimit) {
        this.loanTimeLimit = loanTimeLimit;
    }

    /**
     * @return the loaneeLoanRedordDto
     */
    public LoaneeLoanRedordDto getLoaneeLoanRedordDto() {
        return loaneeLoanRedordDto;
    }

    /**
     * @param loaneeLoanRedordDto
     *            the loaneeLoanRedordDto to set
     */
    public void setLoaneeLoanRedordDto(LoaneeLoanRedordDto loaneeLoanRedordDto) {
        this.loaneeLoanRedordDto = loaneeLoanRedordDto;
    }

    /**
     * @return the investRecord
     */
    public List<Invest> getInvestRecord() {
        return investRecord;
    }

    /**
     * @param investRecord
     *            the investRecord to set
     */
    public void setInvestRecord(List<Invest> investRecord) {
        this.investRecord = investRecord;
    }

    /**
     * @return the investCriteria
     */
    public InvestCriteria getInvestCriteria() {
        return investCriteria;
    }

    /**
     * @param investCriteria
     *            the investCriteria to set
     */
    public void setInvestCriteria(InvestCriteria investCriteria) {
        this.investCriteria = investCriteria;
    }


    /** 获取已完结项目个数 */
    public int getEndloanNum() {
        return endloanNum;
    }

    /** 设置已完结项目个数 */
    public void setEndloanNum(int endloanNum) {
        this.endloanNum = endloanNum;
    }

    /** 获取已完结项目总金额 */
    public double getEndLoanSumAmount() {
        return endLoanSumAmount;
    }

    /** 设置已完结项目总金额 */
    public void setEndLoanSumAmount(double endLoanSumAmount) {
        this.endLoanSumAmount = endLoanSumAmount;
    }

	public String getLoanInterestManner() {
		return loanInterestManner;
	}

	public void setLoanInterestManner(String loanInterestManner) {
		this.loanInterestManner = loanInterestManner;
	}

}
