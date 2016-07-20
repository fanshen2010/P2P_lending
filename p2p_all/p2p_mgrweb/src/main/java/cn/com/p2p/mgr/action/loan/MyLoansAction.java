package cn.com.p2p.mgr.action.loan;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanApplyService;
import cn.com.p2p.loan.service.LoanEffectMangementService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.loan.service.dto.MyLoansDto;

/**
 * <p>
 * 我的融资申请
 * </p>
 * @author zhushanyu
 * @date 2015-03-16 15:44
 */
/**
 * @author FERO-015
 *
 */
@Namespace("/loan/myloans")
@Results({
        @Result(name = "myloans", location = "myloans.ftl", type = "freemarker"),
        @Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker")
})
public class MyLoansAction extends BaseAction {

    private static final long serialVersionUID = -1678704893958476797L;

    /* 根据融资状态查询接口 */
    @Autowired
    private LoanSearchService loanSearchService;
    /* 根据融资状态查询接口 */
    @Autowired
    private LoanApplyService loanApplyService;

    /** 当前用户的融资申请结果集 */
    private List<Loan> loans;

    /** 融资 */
    private Loan loan;

    /** 查询条件实体 */
    private LoanCommSelCriteria criteria = new LoanCommSelCriteria();

    /** 我的融资申请页面DTO */
    private MyLoansDto myLoansDto;

    /** 融资草稿DTO，供查看融资详情使用 */
    private LoanDto loanDto;
    /** 融资状态 */
    private String status;
    /** 融资信息 */
    private List<Loan> lstLoan;

    private String loanCode;
    /** 全部件数 */
    private int allCount = 0;
    /** 被驳回件数 */
    private int rejectedCount = 0;
    /** 审核中件数 */
    private int checkingCount = 0;
    /** 待生效件数 */
    private int effectiveCount = 0;
    /** 还款中件数 */
    private int replayingCount = 0;
    /** 已结束件数 */
    private int overCount = 0;

    @Autowired
    private LoanEffectMangementService effectMangementService;

    @Override
    public String init() throws Exception {
        return null;
    }

    /**
     * <p>
     * 我的融资申请主页
     * </p>
     * @author zhushanyu
     * @date 2015-03-16 15:44
     * @return
     */
    @Action(value = "myloans")
    public String myloans() throws Exception {
        List<String> lstStatus = new ArrayList<String>();
        criteria.setLoginUserId(this.getLoginuser().getId());
        if (StringUtils.isNotEmpty(status) && !"00".equals(status)) {
            String[] astrStatus = status.split(",");
            for (String strTemp : astrStatus) {
                lstStatus.add(strTemp);
            }
            criteria.setStatus(lstStatus);
        } else {
            lstStatus = new ArrayList<String>();
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_01.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_03.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_09.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
            lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
            criteria.setStatus(lstStatus);
        }
        lstLoan = loanSearchService.getPageLoanInfoByCriteria(criteria);

        LoanCommSelCriteria countCriteria = new LoanCommSelCriteria();
        countCriteria.setCustomName(criteria.getCustomName());
        countCriteria.setLoanCode(criteria.getLoanCode());
        countCriteria.setLoanName(criteria.getLoanName());
        countCriteria.setLoginUserId(this.getLoginuser().getId());
        // 全部件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_01.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_03.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_09.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
        countCriteria.setStatus(lstStatus);
        allCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        // 审核中件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_01.getCode());
        countCriteria.setStatus(lstStatus);
        checkingCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        // 被驳回件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_03.getCode());
        countCriteria.setStatus(lstStatus);
        rejectedCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        // 待生效件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
        countCriteria.setStatus(lstStatus);
        effectiveCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        // 还款中件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        countCriteria.setStatus(lstStatus);
        replayingCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        // 已结束件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_09.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
        countCriteria.setStatus(lstStatus);
        overCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        return "myloans";
    }

    /**
     * <p>
     * 查看融资
     * </p>
     * @author zhushanyu
     * @date 2015-03-17 14:36
     * @return
     */
    @Action(value = "view")
    public String viewloan() throws Exception {
        loanDto = loanSearchService.getLoanInfo(loan.getLoanCode(), SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL,
                SearchInfoTypeEnum.LOAN, SearchInfoTypeEnum.LOAN_INFO, SearchInfoTypeEnum.LOAN_RECORD);
        return VIEW;
    }

    /**
     * <p>
     * 删除融资
     * </p>
     * @author 
     * @return
     * @throws Exception
     */
    @Action(value = "delete")
    public void delete() throws Exception {
        loanApplyService.doDeletetLoanById(loanCode);
        this.delSuccess();
    }

    /* ********************* getter/setter ************************* */
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public LoanCommSelCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(LoanCommSelCriteria criteria) {
        this.criteria = criteria;
    }

    public MyLoansDto getMyLoansDto() {
        return myLoansDto;
    }

    public void setMyLoansDto(MyLoansDto myLoansDto) {
        this.myLoansDto = myLoansDto;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LoanDto getLoanDto() {
        return loanDto;
    }

    public void setLoanDto(LoanDto loanDto) {
        this.loanDto = loanDto;
    }

    /** 获取status */
    public String getStatus() {
        return status;
    }

    /** 设置status */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 获取lstLoan */
    public List<Loan> getLstLoan() {
        return lstLoan;
    }

    /** 设置lstLoan */
    public void setLstLoan(List<Loan> lstLoan) {
        this.lstLoan = lstLoan;
    }

    /** 获取allCount */
    public int getAllCount() {
        return allCount;
    }

    /** 设置allCount */
    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    /** 获取rejectedCount */
    public int getRejectedCount() {
        return rejectedCount;
    }

    /** 设置rejectedCount */
    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    /** 获取checkingCount */
    public int getCheckingCount() {
        return checkingCount;
    }

    /** 设置checkingCount */
    public void setCheckingCount(int checkingCount) {
        this.checkingCount = checkingCount;
    }

    /** 获取effectiveCount */
    public int getEffectiveCount() {
        return effectiveCount;
    }

    /** 设置effectiveCount */
    public void setEffectiveCount(int effectiveCount) {
        this.effectiveCount = effectiveCount;
    }

    /** 获取replayingCount */
    public int getReplayingCount() {
        return replayingCount;
    }

    /** 设置replayingCount */
    public void setReplayingCount(int replayingCount) {
        this.replayingCount = replayingCount;
    }

    /** 获取overCount */
    public int getOverCount() {
        return overCount;
    }

    /** 设置overCount */
    public void setOverCount(int overCount) {
        this.overCount = overCount;
    }

    /** 获取loanCode */
    public String getLoanCode() {
        return loanCode;
    }

    /** 设置loanCode*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

}
