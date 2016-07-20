package cn.com.p2p.mgr.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.customer.service.PersonalService;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.query.LoanInfoByCriteriaQuery;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.RepaymentStatusEnum;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.security.password.service.CheckUserPasswordService;

@Namespace("/")
@Results({ 
    @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
    })
public class IndexAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private  CheckUserPasswordService checkUserPasswordService;
	
	@Autowired
	private PersonalService personalService;

    /* 根据融资状态查询接口 */
    @Autowired
    private LoanSearchService loanSearchService;
    
    /* 根据融资状态查询接口 */
    @Autowired
    private LoanInfoByCriteriaQuery loanInfoByCriteriaQuery;
    
    /** 被驳回件数 */
    private int rejectedCount = 0;
    
    /** 审核中件数 */
    private int checkingCount = 0;
    
    /** 待生效件数 */
    private int effectiveCount = 0;
    
    /** 还款中件数 */
    private int replayingCount = 0;
    
    /** 待审批件数 */
    private int approvalCount = 0;
    
    /** 逾期件数 */
    private int overdueCount = 0;
    
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="index")
	public String init() throws Exception {
        // 被驳回件数
	    List<String> lstStatus = new ArrayList<String>();
	    LoanCommSelCriteria countCriteria = new LoanCommSelCriteria();
        
        // 待生效件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_05.getCode());
        countCriteria.setStatus(lstStatus);
        countCriteria.setLoginUserId(null);
        effectiveCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        
        // 还款中件数
        lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        countCriteria.setStatus(lstStatus);
        countCriteria.setLoginUserId(null);
        replayingCount = loanSearchService.getLoanCountByCriteria(countCriteria);
        
       List<Loan> loanList = loanInfoByCriteriaQuery.findPageFinancialApproval(new LoanCommSelCriteria());
       if(loanList != null){
           approvalCount = loanList.size();
       }
       
       // 还款中件数
       lstStatus = new ArrayList<String>();
       lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
       countCriteria.setStatus(lstStatus);
       countCriteria.setReceivableRepayStatus(RepaymentStatusEnum.REPAYMENT_STATUS_TWO.getCode());
       countCriteria.setLoginUserId(null);
       overdueCount = loanSearchService.getLoanCountByCriteria(countCriteria);
       return INIT;
	}
    
	@Action(value="checkPassword")
	public void checkPassword() throws Exception{
		Boolean result= checkUserPasswordService.checkPassword(passWord, getLoginuser().getId(), true);
		Struts2Utils.renderText(result.toString());
		
	}
	
	/**
     * <p>个人客户管理：身份证号唯一性校验</p>
     * @author 
     * @date 2015年4月30日 17:01:59
     * @description 无
     */
    @Action(value="checkPersonalIdentityCard")
    public String checkPersonalIdentityCard() {
        try {
            String data = this.getAjaxMap().get("custPersonalInfo.identity").toString();
            boolean isExsit = personalService.checkIdentity(data);
            if(isExsit){
                this.ajaxCheckFailure();
            }else{
                this.ajaxCheckSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
/*  ================================================ getter setter ================================================  */
    public int getRejectedCount() {
        return rejectedCount;
    }

    public int getCheckingCount() {
        return checkingCount;
    }

    public int getEffectiveCount() {
        return effectiveCount;
    }

    public int getReplayingCount() {
        return replayingCount;
    }

    public int getApprovalCount() {
        return approvalCount;
    }

    public int getOverdueCount() {
        return overdueCount;
    }

}
