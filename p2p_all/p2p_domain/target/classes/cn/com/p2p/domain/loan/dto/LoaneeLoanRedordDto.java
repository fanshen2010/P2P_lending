package cn.com.p2p.domain.loan.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.com.p2p.domain.loan.entity.Loan;

/**
 * 融资人融资记录自定义实体
 * @author 
 *
 */
public class LoaneeLoanRedordDto {
	/**
	 * 正常还清次数
	 */
	Integer normalPay;
	/**
	 * 逾期还清
	 */
	Integer overduePay;
	/**
	 * 总共借入
	 */
	BigDecimal totalBorrow;
	/**
	 * 待还金额
	 */
	BigDecimal stillAmount;
	
	/**
	 * 融资条目
	 */
	List<Loan> loans=new ArrayList<Loan>();

	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	/**
	 * @return the normalPay
	 */
	public Integer getNormalPay() {
		return normalPay;
	}

	/**
	 * @param normalPay the normalPay to set
	 */
	public void setNormalPay(Integer normalPay) {
		this.normalPay = normalPay;
	}

	/**
	 * @return the overduePay
	 */
	public Integer getOverduePay() {
		return overduePay;
	}

	/**
	 * @param overduePay the overduePay to set
	 */
	public void setOverduePay(Integer overduePay) {
		this.overduePay = overduePay;
	}

	/**
	 * @return the totalBorrow
	 */
	public BigDecimal getTotalBorrow() {
		return totalBorrow;
	}

	/**
	 * @param totalBorrow the totalBorrow to set
	 */
	public void setTotalBorrow(BigDecimal totalBorrow) {
		this.totalBorrow = totalBorrow;
	}

	/**
	 * @return the stillAmount
	 */
	public BigDecimal getStillAmount() {
		return stillAmount;
	}

	/**
	 * @param stillAmount the stillAmount to set
	 */
	public void setStillAmount(BigDecimal stillAmount) {
		this.stillAmount = stillAmount;
	}
}
