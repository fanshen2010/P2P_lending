package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.criteria.LoanRepaymentCriteria;
import cn.com.p2p.domain.loan.entity.LoanBuckleRepay;


/**
 * LoanBuckleRepayQuery定义
 * <p>
 * 数据访问层<还款管理>的组件 :供业务逻辑层调用的还款管理数据查询类
 * 
 * @author 
 */
public interface LoanBuckleRepayQuery {
	
	
	/**
	 * 通过查询条件查询还款列表
	 * 
	 * @param  LoanRepaymentCriteria 查询条件
	 * 
	 * @return List<LoanBuckleRepay> 还款数据
	 * 
	 * @author 
	 */
	public List<LoanBuckleRepay> findLoanBuckleRepay(@Param("repay")LoanRepaymentCriteria repay); 

}
