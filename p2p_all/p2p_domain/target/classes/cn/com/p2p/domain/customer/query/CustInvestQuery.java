package cn.com.p2p.domain.customer.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.customer.dto.InvestorsCriteria;
import cn.com.p2p.domain.customer.dto.InvestorsDto;


/**
 * 投资客户管理自定义查询
 * @author 
 *
 */
public interface CustInvestQuery {


	/**
	 * 根据投资客户条件查询投资客户
	 * @param criteria 条件
	 * @return 投资客户列表
	 */
	List<InvestorsDto> findPageInvestorsByCriteria(@Param("criteria") InvestorsCriteria criteria);

}
