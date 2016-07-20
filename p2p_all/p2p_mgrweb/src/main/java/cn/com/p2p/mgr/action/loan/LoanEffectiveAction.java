package cn.com.p2p.mgr.action.loan;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.token.Token;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.loan.service.LoanEffectMangementService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;
import freemarker.template.TemplateException;

/**
 * 待生效的融资
 * @author 
 *
 */
@Namespace("/loan/effective")
@Results({ @Result(name = "effective", location = "effective.ftl", type = "freemarker"),
        @Result(name = "dispose", location = "dispose.ftl", type = "freemarker"),
        @Result(name = "index", location = "effective.htm", type = "redirect",params={"updateFlag","${updateFlag}"}) })
public class LoanEffectiveAction extends BaseAction {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /** 模板引擎 */
    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;
    /** 融资查询接口 */
    @Autowired
    private LoanSearchService loanSearchService;
    /** 投资查询接口 */
    @Autowired
    private InvestSearchService investSearchService;
    @Autowired
    private LoanEffectMangementService effectMangementService;
    /** 融资共通查询参数 */
    private LoanCommSelCriteria criteria = new LoanCommSelCriteria();
    /* 融资ID */
    private String loanId;
    /* 融资编号 */
    private String loanCode;
    /* 融资状态 */
    private String loanStatus;
    /* 延时原因 */
    private String delayReason;
    /* 延时日期 */
    private String delayDate;
    /* 放款原因 */
    private String loanReason;
    /* 流标原因 */
    private String flowReason;
    /* 超募原因 */
    private String ultraReason;
    /* 投资表ID */
    private List<String> investId;
    /* 融资信息 */
    private List<Loan> lstLoan;
    /* 融资Dto */
    private LoanDto loanDto;
    /* 超募投资信息 */
    private List<Invest> lstInvest;
    /* 投资截至时间 */
    private Date loanEndTime;
    /* 更新提示信息*/
    private String updateFlag;
    /**
     * 画面表示
     * @return
     */
    @Override
    @Action(value = "effective")
    public String init() throws Exception {
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
        criteria.setStatus(lstStatus);
        List<Loan> lstLoan = loanSearchService.getPageLoanInfoByCriteria(criteria);
        setLstLoan(lstLoan);
        return "effective";
    }

    /**
     * 查询
     * @return
     */
    @Action(value = "search")
    public String search() {
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
        criteria.setStatus(lstStatus);
        List<Loan> lstLoan = loanSearchService.getPageLoanInfoByCriteria(criteria);
        this.lstLoan = lstLoan;
        return "effective";
    }

    /**
     * 处理
     * @return
     */
    @Action(value = "dispose")
    @Token(generator=true)
    public String dispose() throws IllegalAccessException, InvocationTargetException, JSONException {
        loanDto = loanSearchService.getLoanInfo(loanCode, SearchInfoTypeEnum.LOAN, SearchInfoTypeEnum.LOAN_INFO,
                SearchInfoTypeEnum.INVEST, SearchInfoTypeEnum.LOAN_RECORD);
        if (loanDto != null && loanDto.getProjectInfoDto() != null) {
            Date loanEndTime = loanDto.getProjectInfoDto().getLoanEndTime();
            Date nowTime = DateUtils.getCurrentDateTime();
            if (DateUtils.dateCompare(loanEndTime, nowTime) == 1) {
                this.loanEndTime = loanEndTime;
            } else {
                this.loanEndTime = nowTime;
            }
        }
        return "dispose";
    }
    
    /**
     * 启用
     * @throws JSONException
     */
    @Action(value = "start")
    public String start() throws JSONException {
        Loan loan = new Loan();
        loan.setId(loanId);
        // 增加 更新时间，author: zhushanyu date:2015-06-03 16:48
        loan.setUpdateTime(new java.util.Date());
        loan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_04.getCode());
        effectMangementService.updateLoan(loan);
        updateFlag = "start";
        return "index";
    }

    /**
     * 禁用
     * @throws JSONException
     */
    @Action(value = "forbidden")
    public String forbidden() throws JSONException {
        Loan loan = new Loan();
        loan.setId(loanId);
        loan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_05.getCode());
        // 增加 更新时间，author: zhushanyu date:2015-06-03 16:48
        loan.setUpdateTime(new java.util.Date());
        effectMangementService.updateLoan(loan);
        updateFlag = "forbidden";
        return "index";
    }


    /**
     * 流标
     * @throws JSONException
     */
    @Action(value = "flow")
    @Token(check=true,result="index")
    public String flow() throws JSONException {
    	updateFlag= effectMangementService.doloanFlowExamine(loanCode, loanId, this.getLoginuser());
        return "index";
    }

    /**
     * 放款
     * @throws JSONException
     */
    @Action(value = "loan")
    @Token(check=true,result="index")
    public String loan() throws JSONException {
        effectMangementService.makeLoans(loanCode, this.getLoginuser());
        updateFlag = "loan";
        return "index";
    }


    /** 获取融资ID */
    public String getLoanId() {
        return loanId;
    }

    /** 设置融资ID */
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    /** 获取融资编号 */
    public String getLoanCode() {
        return loanCode;
    }

    /** 设置融资编号 */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    /** 获取融资状态 */
    public String getLoanStatus() {
        return loanStatus;
    }

    /** 设置融资状态 */
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    /** 获取延时日期 */
    public String getDelayDate() {
        return delayDate;
    }

    /** 设置延时日期 */
    public void setDelayDate(String delayDate) {
        this.delayDate = delayDate;
    }

    /** 获取融资信息 */
    public List<Loan> getLstLoan() {
        return lstLoan;
    }

    /** 设置融资信息 */
    public void setLstLoan(List<Loan> lstLoan) {
        this.lstLoan = lstLoan;
    }

    /** 获取融资Dto */
    public LoanDto getLoanDto() {
        return loanDto;
    }

    /** 设置融资Dto */
    public void setLoanDto(LoanDto loanDto) {
        this.loanDto = loanDto;
    }

    /** 获取超募投资信息 */
    public List<Invest> getLstInvest() {
        return lstInvest;
    }

    /** 设置超募投资信息 */
    public void setLstInvest(List<Invest> lstInvest) {
        this.lstInvest = lstInvest;
    }

    /** 获取超募原因 */
    public String getUltraReason() {
        return ultraReason;
    }

    /** 设置超募原因 */
    public void setUltraReason(String ultraReason) {
        this.ultraReason = ultraReason;
    }

    /** 获取投资ID */
    public List<String> getInvestId() {
        return investId;
    }

    /** 设置投资ID */
    public void setInvestId(List<String> investId) {
        this.investId = investId;
    }

    /** 获取放款原因 */
    public String getLoanReason() {
        return loanReason;
    }

    /** 设置放款原因 */
    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    /** 获取流标原因 */
    public String getFlowReason() {
        return flowReason;
    }

    /** 设置流标原因 */
    public void setFlowReason(String flowReason) {
        this.flowReason = flowReason;
    }

    /** 获取criteria */
    public LoanCommSelCriteria getCriteria() {
        return criteria;
    }

    /** 设置criteria */
    public void setCriteria(LoanCommSelCriteria criteria) {
        this.criteria = criteria;
    }

    /** 获取投资截至时间 */
    public Date getLoanEndTime() {
        return loanEndTime;
    }

    /** 设置投资截至时间 */
    public void setLoanEndTime(Date loanEndTime) {
        this.loanEndTime = loanEndTime;
    }
   
    /** 获取updateFlag */
    public String getUpdateFlag() {
        return updateFlag;
    }

    /** 设置updateFlag*/
    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    /** 获取delayReason */
    public String getDelayReason() {
        return delayReason;
    }

    /** 设置delayReason*/
    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }
}
