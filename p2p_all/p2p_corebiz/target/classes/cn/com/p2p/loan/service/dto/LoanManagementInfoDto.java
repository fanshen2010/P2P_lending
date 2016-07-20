package cn.com.p2p.loan.service.dto;

import java.util.List;

import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.invest.service.dto.InvestInformationDto;


/**
 * 融资管理信息Dto
 * 贷前、后管理中，查看画面中使用的集合
 * @author 
 *
 */
public class LoanManagementInfoDto {
    /** 融资项目基本信息Dto */
    private LoanProjectInfoDto projectInfoDto;
    /** 融资项目其它信息Dto */
    private LoanProjectMsgDto loanProjectMsgDto;
    /** 还款情况*/
    private Loan loan;
    /** 投资记录*/
    private List<InvestInformationDto> investInformations;
    /** 还款记录*/
    private List<RepayDetail> repayDetailList;
    /** 期数*/
    private int MaxNum;
    
    
    
    /** 获取融资项目信息Dto */
    public LoanProjectInfoDto getProjectInfoDto() {
        return projectInfoDto;
    }

    /** 设置融资项目信息Dto */
    public void setProjectInfoDto(LoanProjectInfoDto projectInfoDto) {
        this.projectInfoDto = projectInfoDto;
    }

    /** 获取融资项目其它信息Dto */
    public LoanProjectMsgDto getLoanProjectMsgDto() {
        return loanProjectMsgDto;
    }

    /** 设置融资项目其它信息Dto */
    public void setLoanProjectMsgDto(LoanProjectMsgDto loanProjectMsgDto) {
        this.loanProjectMsgDto = loanProjectMsgDto;
    }

	/**
	 * @return the loan
	 */
	public Loan getLoan() {
		return loan;
	}

	/**
	 * @param loan the loan to set
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	/**
	 * @return the investInformations
	 */
	public List<InvestInformationDto> getInvestInformations() {
		return investInformations;
	}

	/**
	 * @param investInformations the investInformations to set
	 */
	public void setInvestInformations(List<InvestInformationDto> investInformations) {
		this.investInformations = investInformations;
	}

	/**
	 * @return the repayDetailList
	 */
	public List<RepayDetail> getRepayDetailList() {
		return repayDetailList;
	}

	/**
	 * @param repayDetailList the repayDetailList to set
	 */
	public void setRepayDetailList(List<RepayDetail> repayDetailList) {
		this.repayDetailList = repayDetailList;
	}

	/**
	 * @return the maxNum
	 */
	public int getMaxNum() {
		return MaxNum;
	}

	/**
	 * @param maxNum the maxNum to set
	 */
	public void setMaxNum(int maxNum) {
		MaxNum = maxNum;
	}
}
