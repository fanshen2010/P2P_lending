package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 融资项目其它信息Dto
 * 
 * @author 
 *
 */
public class LoanProjectMsgDto {
    
    /** 融资用途 */
    private String loanUse;
    
    /** 成立前提 */
    private String premise;
    
    /** 还款来源 */
    private String repaySource;
    
    /** 风险控制措施 */
    private String riskControl;
    
    /** 平台建议 */
    private String platform;
    

	/** 获取融资用途 */
	public String getLoanUse() {
		return loanUse;
	}

	/** 设置融资用途 */
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	/** 获取成立前提 */
	public String getPremise() {
		return premise;
	}

	/** 设置成立前提 */
	public void setPremise(String premise) {
		this.premise = premise;
	}

	/** 获取还款来源 */
	public String getRepaySource() {
		return repaySource;
	}

	/** 设置还款来源 */
	public void setRepaySource(String repaySource) {
		this.repaySource = repaySource;
	}

	/** 获取风险控制措施 */
	public String getRiskControl() {
		return riskControl;
	}

	/** 设置风险控制措施 */
	public void setRiskControl(String riskControl) {
		this.riskControl = riskControl;
	}

	/** 获取平台建议 */
	public String getPlatform() {
		return platform;
	}

	/** 设置平台建议 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public LoanProjectMsgDto() {
		super();
	}

	/**
	 * @param loanUse
	 * @param premise
	 * @param repaySource
	 * @param riskControl
	 * @param platform
	 * @param projectDescription
	 */
	public LoanProjectMsgDto(String loanUse, String premise,
			String repaySource, String riskControl, String platform,
			String projectDescription) {
		super();
		this.loanUse = loanUse;
		this.premise = premise;
		this.repaySource = repaySource;
		this.riskControl = riskControl;
		this.platform = platform;
	}
}
