package cn.com.p2p.customer.service;

import java.util.List;

import cn.com.p2p.customer.dto.InvestorsDetailDto;
import cn.com.p2p.domain.customer.dto.InvestorsCriteria;
import cn.com.p2p.domain.customer.dto.InvestorsDto;
import cn.com.p2p.domain.invest.dto.InvestDetailDto;


/**
 * 客户管理接口
 * @author  
 *
 */
public interface CustomerService {

	
	/**
	 * 查找投资客户列表
	 * @param criteria
	 * @return
	 */
	public List<InvestorsDto> findInvestorsByCriteria(InvestorsCriteria criteria); 
	
	/**
	 * 投资客户明细
	 * @param userId 用户ID
	 * @return
	 */
	public InvestorsDetailDto getInvestorsDetail(String userId);
	
	
	/**
	 * 根据年月获取当前用户的在当月的还款计划
	 * @param yearmonth
	 * @param userId
	 * @return
	 */
	public  InvestDetailDto getInvestDetailDtoByYearMonth(String yearmonth,String userId);

	
	/**
	 * 根据年份获取用户的还款明细数据
	 * @param year
	 * @param id
	 * @return
	 */
	public InvestorsDetailDto getRepaymentPlan(String year, String userId);
}
