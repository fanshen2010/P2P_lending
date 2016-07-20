package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;




/**
 * LoanInfoByCriteriaQuery定义
 * <p>
 * 数据访问层<融资信息>的组件 :供业务逻辑层调用的融资数据查询类
 * 
 * @author 
 */
public interface LoanInfoByCriteriaQuery {

	/**
	 * 通过融资状态、查询条件进行融资分页查询
	 * @author 
	 * 
	 * @param loanCommSelectParameters loanParameter 查询条件
	 * @return Loan
	 */
	public List<Loan> findPageLoanList(@Param("loanParameter")LoanCommSelCriteria loanParameter);
	
	/**
	 * 通过融资状态、查询条件进行融资查询
	 * @author 
	 * 
	 * @param loanCommSelectParameters loanParameter 查询条件
	 * @return Loan
	 */
	public List<Loan> findLoanList(@Param("loanParameter")LoanCommSelCriteria loanParameter);
	
	/**
     * 通过融资状态、查询条件进行融资查询
     * @author 
     * @return Loan
     */
    public List<Loan> findPageFinancialApproval(@Param("loanParameter")LoanCommSelCriteria loanParameter);
	
	
	/**
	 * 通过页面的条件，获取前台项目投资列表(分页)
	 * @param loanParameter 查询条件
	 * @return 项目投资列表
	 */
	public List<Loan> findPageInvestObjectList(@Param("criteria") LoanCommSelCriteria criteria);
	
	/**
	 * 通过页面的条件，获取前台项目投资列表
	 * @param loanParameter 查询条件
	 * @return 项目投资列表
	 */
	public List<Loan> findInvestObjectList(@Param("criteria") LoanCommSelCriteria criteria);
	
	/**
	 * 获取投资预告列表 
	 * @param loanParameter 查询条件
	 * @return 预告列表
	 */
	public List<Loan> findInvestNoticeList(@Param("loanParameter") LoanCommSelCriteria loanParameter); 
	
}
