package cn.com.p2p.domain.loan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 还款管理查询列表实体
 * 
 * @author 
 */
public class LoanBuckleRepay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 融资编号 */
	private String loanCode;

	/** 融资项目 */
	private String loanProject;

	/** 客户名称 */
	private String customName;

	/** 还款方式 */
	private String interestManner;

	/** 当前期数 */
	private int currentNum;

	/** 当期还款期数 */
	private int receivableNum;

	/** 融资总额（元） */
	private Integer loanAmount;

	/** 还款类型 */
	private String repayType;

	/** 应还款日期 */
	private Date repayPlanDate;

	/** 还款状态 */
	private String repayStatus;

	/********************************* Get Set 方法 ***********************************************/
	/** 获取融资编号 */
	public String getLoanCode() {
		return loanCode;
	}

	/** 设置融资编号 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 获取融资项目 */
	public String getLoanProject() {
		return loanProject;
	}

	/** 设置融资项目 */
	public void setLoanProject(String loanProject) {
		this.loanProject = loanProject;
	}

	/** 获取客户名称 */
	public String getCustomName() {
		return customName;
	}

	/** 设置客户名称 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/** 获取还款方式 */
	public String getInterestManner() {
		return interestManner;
	}

	/** 设置还款方式 */
	public void setInterestManner(String interestManner) {
		this.interestManner = interestManner;
	}

	/** 获取当前期数 */
	public int getCurrentNum() {
		return currentNum;
	}

	/** 设置当前期数 */
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	/** 获取当期还款期数 */
	public int getReceivableNum() {
		return receivableNum;
	}

	/** 设置当期还款期数 */
	public void setReceivableNum(int receivableNum) {
		this.receivableNum = receivableNum;
	}

	/** 获取融资总额（元） */
	public Integer getLoanAmount() {
		return loanAmount;
	}

	/** 设置融资总额（元） */
	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	/** 获取还款类型 */
	public String getRepayType() {
		return repayType;
	}

	/** 设置还款类型 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	/** 获取应还款日期 */
	public Date getRepayPlanDate() {
		return repayPlanDate;
	}

	/** 设置应还款日期 */
	public void setRepayPlanDate(Date repayPlanDate) {
		this.repayPlanDate = repayPlanDate;
	}

	/** 获取还款状态 */
	public String getRepayStatus() {
		return repayStatus;
	}

	/** 设置还款状态 */
	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}

}
