package cn.com.p2p.customer.dto;

import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.invest.dto.InvestDetailDto;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.invest.service.dto.InvestIncomeDto;


/**
 * 投资客户明细Dto
 * @author 
 *
 */
public class InvestorsDetailDto {

	
	/**
	 * 基本信息
	 */
	private  WebUser basicMsg;
	
	
	
	
	
	/**
	 * 投资情况
	 */
	private InvestIncomeDto investDetailDto;
	
	/**
	 * 还款计划
	 */
	private List<InvestDetailDto> repaymentPlan;


	private LinkedMap  years;
	
	
	/**
	 * @return the basicMsg
	 */
	public WebUser getBasicMsg() {
		return basicMsg;
	}

	/**
	 * @param basicMsg the basicMsg to set
	 */
	public void setBasicMsg(WebUser basicMsg) {
		this.basicMsg = basicMsg;
	}

	/**
	 * @return the investDetailDto
	 */
	public InvestIncomeDto getInvestDetailDto() {
		return investDetailDto;
	}

	/**
	 * @param investDetailDto the investDetailDto to set
	 */
	public void setInvestDetailDto(InvestIncomeDto investDetailDto) {
		this.investDetailDto = investDetailDto;
	}

	/**
	 * @return the repaymentPlan
	 */
	public List<InvestDetailDto> getRepaymentPlan() {
		return repaymentPlan;
	}

	/**
	 * @param repaymentPlan the repaymentPlan to set
	 */
	public void setRepaymentPlan(List<InvestDetailDto> repaymentPlan) {
		this.repaymentPlan = repaymentPlan;
	}

	/**
	 * @return the years
	 */
	public LinkedMap getYears() {
		return years;
	}

	/**
	 * @param years the years to set
	 */
	public void setYears(LinkedMap years) {
		this.years = years;
	}

}
