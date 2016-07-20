package cn.com.p2p.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>利息计算器返回结果中分期信息Dto</p>
 * @author 
 * 
 */
public class CalculatorResultInstallmentsDto {
	/** 期数 */
	private Integer num;

	/** 本金 */
	private BigDecimal principal;

	/** 利息 */
	private BigDecimal interest;

	/** 本息和 */
	private BigDecimal total;

	/** 还款日期 */
	private Date repaymentDate;

	/**
	 * <p>无参数构造方法</p>
	 */
	public CalculatorResultInstallmentsDto() {

	}

	/**
	 * <p>有参数构造方法</p>
	 * <pre>使用此方法构造对象，只需传入【本金】和【利息】，本息和字段会自动计算</pre>
	 * @param num 期数
	 * @param principal 本金
	 * @param interest 利息
	 * @param repaymentDate 还款日期
	 */
	public CalculatorResultInstallmentsDto(Integer num, BigDecimal principal, BigDecimal interest, Date repaymentDate) {
		this.num = num;
		this.principal = principal;
		this.interest = interest;
		this.total = this.principal.add(this.interest);
		this.repaymentDate = repaymentDate;
	}

	/*
	 * ==================================================================
	 * ===========================Get/Set方法============================
	 * ==================================================================
	 */

	/** 获取期数 */
	public Integer getNum() {
		return num;
	}

	/** 设置期数 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/** 获取本金 */
	public BigDecimal getPrincipal() {
		return principal;
	}

	/** 设置本金 */
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	/** 获取利息 */
	public BigDecimal getInterest() {
		return interest;
	}

	/** 设置利息 */
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	/** 获取本息和 */
	public BigDecimal getTotal() {
		return total;
	}

	/** 设置本息和 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/** 获取还款日期 */
	public Date getRepaymentDate() {
		return repaymentDate;
	}

	/** 设置还款日期 */
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
}
