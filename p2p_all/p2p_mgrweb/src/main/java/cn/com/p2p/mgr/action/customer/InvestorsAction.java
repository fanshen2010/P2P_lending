package cn.com.p2p.mgr.action.customer;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.customer.dto.InvestorsDetailDto;
import cn.com.p2p.customer.service.CustomerService;
import cn.com.p2p.domain.customer.dto.InvestorsCriteria;
import cn.com.p2p.domain.customer.dto.InvestorsDto;
import cn.com.p2p.domain.invest.dto.InvestDetailDto;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.utils.Constants;


/**
 * 投资客户管理
 * @author 
 *
 */


@Namespace("/customer/investors")
@Results({
	@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
	@Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker"),
	
	})
public class InvestorsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投资客户查询条件
	 */
	private InvestorsCriteria criteria=new InvestorsCriteria();
	
	private List<InvestorsDto> investorsList=new ArrayList<InvestorsDto>();
	
	/**
	 * 用户ID
	 */
	private String id;
	
	/**
	 * 投资人明细
	 */
	private InvestorsDetailDto detail;
	
	/**
	 * 年份
	 */
	private String year;
	
	/**
	 * 年月
	 */
	private String yearMonth;
	
	private BigDecimal balance;
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FeroFreemarkerProcessor feroFreemarkerProcessor;
	
	@Autowired
	private PaymentService paymentService; 
	/**
	 * 客户列表
	 */
	@Action(value="index")
	@Override
	public String init() throws Exception {
		investorsList=customerService.findInvestorsByCriteria(criteria);
		return INIT;
	}

	/**
	 * 客户详细
	 * @return
	 * @throws Exception
	 */
	@Action(value="view")
	public String view() throws Exception{
		detail=customerService.getInvestorsDetail(id);
		balance = paymentService.ciccDoSearchBalance(id);
		return VIEW;
	}

	
	
	/**
	 * 每一月明细数据
	 * @throws Exception
	 */
	@Action(value="detail")
	public void detail() throws Exception{
		InvestDetailDto investDetail=customerService.getInvestDetailDtoByYearMonth(yearMonth, id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("investDetails", investDetail.getInvestDetail());
		String	result = feroFreemarkerProcessor.process(
				Constants.INVESTOR_INVESTDETAIL_TEMPLATE, map, this);
		map.put("html", result);
		Struts2Utils.renderJson(map);
	}
	
	/**
	 * 获取具体年份的数据
	 * @throws Exception
	 */
	@Action(value="repaymentPlan")
	public void repaymentPlan() throws Exception {
		InvestorsDetailDto investorsDetailDto=customerService.getRepaymentPlan(year, id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("detail"
				+ "", investorsDetailDto);
		String	result = feroFreemarkerProcessor.process(
				Constants.INVESTOR_REPAYMENTPLAN_TEMPLATE, map, this);
		map.put("html", result);
		Struts2Utils.renderJson(map);
	}
	
	
	/**
	 * @return the criteria
	 */
	public InvestorsCriteria getCriteria() {
		return criteria;
	}


	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(InvestorsCriteria criteria) {
		this.criteria = criteria;
	}




	/**
	 * @return the investorsList
	 */
	public List<InvestorsDto> getInvestorsList() {
		return investorsList;
	}


	/**
	 * @param investorsList the investorsList to set
	 */
	public void setInvestorsList(List<InvestorsDto> investorsList) {
		this.investorsList = investorsList;
	}




	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}




	/**
	 * @return the detail
	 */
	public InvestorsDetailDto getDetail() {
		return detail;
	}




	/**
	 * @param detail the detail to set
	 */
	public void setDetail(InvestorsDetailDto detail) {
		this.detail = detail;
	}




	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}




	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}







	/**
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}







	/**
	 * @param yearMonth the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}







	public BigDecimal getBalance() {
		return balance;
	}







	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
