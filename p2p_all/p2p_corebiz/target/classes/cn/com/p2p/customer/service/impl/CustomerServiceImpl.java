package cn.com.p2p.customer.service.impl;

import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.customer.dto.InvestorsDetailDto;
import cn.com.p2p.customer.service.CustomerService;
import cn.com.p2p.domain.customer.dto.InvestorsCriteria;
import cn.com.p2p.domain.customer.dto.InvestorsDto;
import cn.com.p2p.domain.customer.query.CustInvestQuery;
import cn.com.p2p.domain.invest.dto.InvestDetailDto;
import cn.com.p2p.domain.invest.query.InvestDetailQuery;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.invest.service.dto.InvestIncomeDto;


@Service
public class CustomerServiceImpl implements CustomerService {

	
	
	@Autowired
	private CustInvestQuery custInvestQuery;
	@Autowired
	private InvestSearchService investSearchService;
	
	@Autowired
	private WebUserRepository webUserRepository;
	
	
	@Autowired
	private InvestDetailQuery investDetailQuery;
	@Override
	public List<InvestorsDto> findInvestorsByCriteria(InvestorsCriteria criteria) {
		return custInvestQuery.findPageInvestorsByCriteria(criteria);
	}

	@Override
	public InvestorsDetailDto getInvestorsDetail(String userId) {
		InvestorsDetailDto detail=new InvestorsDetailDto();
		
		//获取用户信息
		
		WebUser basicMsg=webUserRepository.findOne(userId);
		detail.setBasicMsg(basicMsg);
		
		//投资情况
		InvestIncomeDto investDetailDto=investSearchService.getInvestByUser(userId);
		detail.setInvestDetailDto(investDetailDto);
		
		List<InvestDetailDto> repaymentPlan =investDetailQuery.findInvestByInvestYear(null, userId);
		detail.setRepaymentPlan(repaymentPlan);
		
		//日期
		List<String> years=investDetailQuery.findInvestDetailYearByUserId(userId);
		LinkedMap map=new LinkedMap();
		for(String str:years){
			map.put(str, str);
		}
		detail.setYears(map);
		
		return detail;
	}

	@Override
	public InvestDetailDto getInvestDetailDtoByYearMonth(
			String yearmonth, String userId) {
		String year=yearmonth.substring(0,4);
		String month=yearmonth.substring(4);
		InvestDetailDto investDetailDto=new InvestDetailDto();
		investDetailDto.setInvestDetail(investDetailQuery.findInvestDetailByYearMonth(year, month, userId));
		return investDetailDto;
	}

	@Override
	public InvestorsDetailDto getRepaymentPlan(String year, String userId) {
		
	
	InvestorsDetailDto detail=new InvestorsDetailDto();
	
	
	List<InvestDetailDto> repaymentPlan =investDetailQuery.findInvestByInvestYear(year, userId);
	detail.setRepaymentPlan(repaymentPlan);
	
	
	return detail;}

}
