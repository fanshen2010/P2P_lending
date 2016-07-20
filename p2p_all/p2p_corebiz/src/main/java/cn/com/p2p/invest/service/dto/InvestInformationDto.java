package cn.com.p2p.invest.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 融资业务中的投资信息
 * @author 
 *
 */
public class InvestInformationDto {
	
	/**投资客户名称*/
	private String login;
	
	/**投资时间*/
	private Date investmentTime;
	
	/**投资金额(元)*/
	private BigDecimal investmentAmount;
	
	/**应收总额(元)*/
	private BigDecimal receivableAmount;
	
	/**应收本金(元)*/
	private BigDecimal receivablePrincipal;
	
	/**应收利息(元)*/
	private BigDecimal receivableInterest;

	
	/** 获取用户名 */
	public String getLogin() {
		return login;
	}
	
	/** 设置用户名 */
	public void setLogin(String login) {
		this.login = login;
	}

	/** 获取投资时间 */
	public Date getInvestmentTime() {
		return investmentTime;
	}

	/** 设置投资时间 */
	public void setInvestmentTime(Date investmentTime) {
		this.investmentTime = investmentTime;
	}

	/** 获取投资金额 */
	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	/** 设置投资金额 */
	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	/** 获取应收总额 */
	public BigDecimal getReceivableAmount() {
		return receivableAmount;
	}

	/** 设置应收总额 */
	public void setReceivableAmount(BigDecimal receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	/** 获取应收本金 */
	public BigDecimal getReceivablePrincipal() {
		return receivablePrincipal;
	}

	/** 设置应收本金 */
	public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
		this.receivablePrincipal = receivablePrincipal;
	}

	/** 获取应收利息 */
	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}

	/** 设置应收利息 */
	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}
}
