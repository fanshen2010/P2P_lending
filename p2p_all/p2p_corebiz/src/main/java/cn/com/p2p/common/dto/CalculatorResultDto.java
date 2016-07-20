package cn.com.p2p.common.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>利息计算器返回结果Dto</p>
 * @author 
 *
 */
public class CalculatorResultDto {
	/** 总利息 */
	private BigDecimal totalInterest;
	
	/** 分期信息 */
	private List<CalculatorResultInstallmentsDto> installments;
	
	/**
	 * <p>无参构造方法</p>
	 */
	public CalculatorResultDto(){
		
	}
	
	/**
	 * <p>有参数的构造方法</p>
	 * @param totalInterest 总利息
	 * @param installments 分期列表
	 */
	public CalculatorResultDto(BigDecimal totalInterest, List<CalculatorResultInstallmentsDto> installments){
		this.totalInterest = totalInterest;
		this.installments = installments;
	}
	
	/*
	 * ==================================================================
	 * ===========================Get/Set方法============================
	 * ==================================================================
	 */

	/** 获取总利息 */
	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	/** 设置总利息 */
	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}

	/** 获取分期信息 */
	public List<CalculatorResultInstallmentsDto> getInstallments() {
		return installments;
	}

	/** 设置分期信息 */
	public void setInstallments(List<CalculatorResultInstallmentsDto> installments) {
		this.installments = installments;
	}
}
