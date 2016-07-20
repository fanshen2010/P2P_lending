package cn.com.p2p.domain.loan.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * 逾期还款查询参数类
 * 
 * @author 
 *
 */
public class LoanRepaymentCriteria extends BaseCriteria {

	/** 融资编号 */
	private String loanCode;
	
	/** 融资项目 */
	private String loanProject;
	
	/** 客户名称 */
	private String customName;
	
	/** 还款状态 */
	private String repayStatus;
	
	/** 最小还款日期 */
	private Date repayDateMin;

	/** 最大还款日期 */
	private Date repayDateMax;
	
	
	/********************************** Get Set 方法 *************************************/
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

	/** 获取还款状态 */
	public String getRepayStatus() {
		return repayStatus;
	}

	/** 设置还款状态 */
	public void setRepayStatus(String RepayStatus) {
		repayStatus = RepayStatus;
	}
	
	/** 获取最小还款日期 */
	public Date getRepayDateMin() {
		return repayDateMin;
	}

	/** 获取最小还款日期 */
	public void setRepayDateMin(Date repayDateMin) {
		this.repayDateMin = repayDateMin;
	}

	/** 获取最大还款日期 */
	public Date getRepayDateMax() {
		return repayDateMax;
	}

	/** 获取最大还款日期 */
	public void setRepayDateMax(Date repayDateMax) {
		this.repayDateMax = repayDateMax;
	}
}
