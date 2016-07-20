package cn.com.p2p.por.action.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.invest.service.dto.InvestIncomeDto;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.usermangent.service.WebUserService;
import cn.com.p2p.utils.Constants;

/**
 * 个人中心首页
 * 
 * @author 
 */
@Namespace("/account")
@Results({ @Result(name = "index", location = "index.ftl", type = "freemarker")})
public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	/** 用户投资信息查询Service */
	@Autowired
	private InvestSearchService investSearchService;
	@Autowired
	private WebUserService webUserService;
	
	/** 中金接口Service*/
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private FeroFreemarkerProcessor feroFreemarkerProcessor;
	
    private InvestIncomeDto investIncomeDto;
    private boolean ciccAccountFlag;
    private String months;
    private String investPlan;
    private String investActual;
    private String lineState;
    private WebUser webUser;
    private BigDecimal balance;
    
	/**
	 * <p>个人中心首页初始化</p>.<br>
	 * author：<br>
	 *===================================
	 * @return
	 * @throws Exception
	 */
	@Action(value="index")
	@Override
	public String init() throws Exception {
		String userId = getLoginuser().getId();
		//用户基本信息
		webUser = webUserService.findOne(userId);		
		balance = paymentService.ciccDoSearchBalance(userId);
		//用户投资统计图表数据
		Map<String,String> dateOfLine = investSearchService.getInvestStatisticByUser(userId);
		months = dateOfLine.get("months");
		investPlan = dateOfLine.get("investPlan");
		investActual = dateOfLine.get("investActual");
		lineState = dateOfLine.get("lineState");
		//用户投资累计数据
		investIncomeDto = investSearchService.getInvestByUser(userId);
		return "index";
	}
	
	
	@Action(value = "addBalanceDialog")
	public void addBalanceDialog() throws Exception {
		Map<String, Object> map = getAjaxMap();
		// 得到渲染好的模板内容
		String result = "";
		result = feroFreemarkerProcessor.process("/account/balance/addBalance.ftl", map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	
	@Action(value = "withdrawBalanceDialog")
	public void withdrawBalanceDialog() throws Exception {
		Map<String, Object> map = getAjaxMap();
		// 得到渲染好的模板内容
		String result = "";
		result = feroFreemarkerProcessor.process("/account/balance/withdrawBalance.ftl", map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	
	@Action(value = "addBalance")
	public void updateAddBalance() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String errMsg = "error";
		String oldpassword = map.get("password").toString();
		BigDecimal balance=new BigDecimal(map.get("balance").toString());
		String userId = getLoginuser().getId();
		boolean equalToOldPassword = webUserService.checkOldPassword(oldpassword, userId);
		
		//原密码
		if(oldpassword==null || StringUtils.isBlank(oldpassword)){
			errMsg = "Password：should not be null";
		}else if(oldpassword.length() < 6 || oldpassword.length() > 18){
			errMsg = "Password：length should be 6~18 character";
		}else if(!equalToOldPassword){
			errMsg = "Password Error：fill in again！";
		}
		//blance
		else if(balance==null || StringUtils.isBlank(balance)){
			errMsg = "balance：should not be null";
		}else{
			//更新数据
			paymentService.ciccDoAccountRecharge(balance, userId);
			errMsg = "success";
		}
		map.put("msg", errMsg);
		Struts2Utils.renderJson(map);
	}
	
	@Action(value = "withdrawBalance")
	public void updateWithdrawBalance() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String errMsg = "error";
		String oldpassword = map.get("password").toString();
		BigDecimal balance=new BigDecimal(map.get("balance").toString());
		String userId = getLoginuser().getId();
		boolean equalToOldPassword = webUserService.checkOldPassword(oldpassword, userId);
		
		//原密码
		if(oldpassword==null || StringUtils.isBlank(oldpassword)){
			errMsg = "Password：should not be null";
		}else if(oldpassword.length() < 6 || oldpassword.length() > 18){
			errMsg = "Password：length should be 6~18 character";
		}else if(!equalToOldPassword){
			errMsg = "Password Error：fill in again！";
		}
		//blance
		else if(balance==null || StringUtils.isBlank(balance)){
			errMsg = "balance：should not be null";
		}else{
			//更新数据
			paymentService.ciccDoAccountCash(balance, userId);
			errMsg = "success";
		}
		map.put("msg", errMsg);
		Struts2Utils.renderJson(map);
	}
	
	
	public InvestIncomeDto getInvestIncomeDto() {
		return investIncomeDto;
	}

	public void setInvestIncomeDto(InvestIncomeDto investIncomeDto) {
		this.investIncomeDto = investIncomeDto;
	}

	public boolean isCiccAccountFlag() {
		return ciccAccountFlag;
	}

	public void setCiccAccountFlag(boolean ciccAccountFlag) {
		this.ciccAccountFlag = ciccAccountFlag;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getInvestPlan() {
		return investPlan;
	}

	public void setInvestPlan(String investPlan) {
		this.investPlan = investPlan;
	}

	public String getInvestActual() {
		return investActual;
	}

	public void setInvestActual(String investActual) {
		this.investActual = investActual;
	}

	public String getLineState() {
		return lineState;
	}

	public void setLineState(String lineState) {
		this.lineState = lineState;
	}

	public WebUser getWebUser() {
		return webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
