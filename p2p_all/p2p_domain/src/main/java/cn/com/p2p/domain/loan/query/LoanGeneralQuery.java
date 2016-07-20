/** --------------------------------------------------------------------------------------------------
 * 
 *  
 * Title:			
 * 					LoanGeneralQuery.java
 * Description:		
 * 					融资通用查询接口定义
 *        			本接口所提供方法可以根据条件无差别查询所有融资项目信息，即SQL文中没有默认条件
 *        			所查询的结果完全遵照查询条件进行查询 
 * Dependencies: 
 *        			cn.com.p2p.domain.loan.criteria.LoanGeneralCriteria 
 *        			cn.com.p2p.domain.loan.entity.Loan 
 * History:  		
 * 					Date                 Modifier             Log
 *     				2015-09-17                    Created
 * 
 * --------------------------------------------------------------------------------------------------- */

package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.criteria.LoanGeneralCriteria;
import cn.com.p2p.domain.loan.entity.Loan;

/**
 * <p>
 * 融资通用查询-接口
 * </p>
 * 
 * <pre>
 * 本接口所提供方法可以根据条件无差别查询所有融资项目信息，即SQL文中没有默认条件
 * 所查询的结果完全遵照查询条件进行查询
 * </pre>
 * 
 * @author 
 *
 */
public interface LoanGeneralQuery {
	/**
	 * <p>
	 * 融资通用查询
	 * </p>
	 * 
	 * <pre>
	 * 根据查询条件查询融资项目，SQL文中没有默认条件，完全按照查询条件查询返回融资项目List
	 * </pre>
	 * 
	 * @param loanParameter
	 *            查询条件类
	 * @return 融资项目对象Loan的List，即List&lt;Loan&gt;
	 */
	List<Loan> findGeneralLoanList(@Param("loanParameter") LoanGeneralCriteria loanParameter);
}
