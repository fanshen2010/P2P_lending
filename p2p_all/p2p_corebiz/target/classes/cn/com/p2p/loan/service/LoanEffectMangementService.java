package cn.com.p2p.loan.service;

/**
 * 融资接口 <p> 融资的主要实现接口
 * 
 * @作者 何生
 * @创建时间 2015-09-13 10:14
 */

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;


public interface LoanEffectMangementService {
	
    /**
     * 融资放款
     */
	public OperateInfo makeLoans(String pstrLoanCode, UserContext loginUser);
	
	/**
	 * 融资流标退款 *
	 */
	public String doloanFlowExamine(String pstrLoanCode, String pstrLoanId, UserContext loginuser);

	/**
	 * 融资人还款
	 * 融资还款可以多期合在一个归返 还款分为三种 融资人还款、平台垫付、担保人代偿 还款金额包括 本金，利息，罚息，滞纳金
	 */
	public OperateInfo doLoanDetailRepayment(
    		String loanCode,
    		String passWord,
            UserContext loginUser,
            String repaymentAccountTypeEnumKey) throws Exception;
    
    /**
	 * 根据ID 动态更新融资表
	 * Loan的属性 ：保存要更新的内容 ID ：去查找具体的融资记录 author：<br>
	 */
	public void updateLoan(Loan pLoan);

}
