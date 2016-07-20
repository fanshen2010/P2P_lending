/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanDraft.java
 * Description:       实体LoanDraft类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-09-12             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.loan.service.dto;


import java.io.Serializable;
import java.util.List;

import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.invest.service.dto.InvestInformationDto;

/**
 * 融资草稿(包含所有个人、企业信息内容) Dto
 * 
 * @author 
 *
 */
public class LoanDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;
	
	/** 融资编号 */
	private String loanCode;

	/** 客户编号 */
	private String customCode;
	/** 客户名称 */
	private String customName;

	/** 融资类型 */
	private String loanType;

	/** 后台用户ID */
	private String staffId;
	
	/** 企业基本信息Dto */
	private LoanEnterpriseInfoDto enterpriseInfoDto;
	
	/** 融资项目基本信息Dto */
	private LoanProjectInfoDto projectInfoDto;
	
	/** 融资项目其它信息Dto */
	private LoanProjectMsgDto loanProjectMsgDto;
	
	/** 个人基本信息Dto */
	private LoanPersonalBasicDto personalBasicDto;
	
	/** 个人工作信息Dto */
	private LoanPersonalJobDto personalJobDto;
	
    /** 投资记录*/
    private List<InvestInformationDto> investInformations;
    
    /** 还款记录*/
    private List<RepayDetail> repayDetailList;
    
    /** 当期还款信息 */
    private RepayDetail presentRepayDetail;
    
    /** 还款总期数 */
    private Integer totalRepayNumber;
    
	public LoanDto() {
	}

	/** 获取ID */
	public String getId() {
		return id;
	}

	/** 设置ID */
	public void setId(String id) {
		this.id = id;
	}
	
    /** 获取融资编号 */
	public String getLoanCode() {
		return loanCode;
	}

	/** 设置融资编号 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 获取客户名称 */
	public String getCustomName() {
		return customName;
	}

	/** 设置客户名称 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/** 获取融资类型 */
	public String getLoanType() {
		return loanType;
	}

	/** 设置融资类型 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	/** 获取后台用户ID */
	public String getStaffId() {
		return staffId;
	}

	/** 设置后台用户ID */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/** 获取企业基本信息Dto */
	public LoanEnterpriseInfoDto getEnterpriseInfoDto() {
		return enterpriseInfoDto;
	}

	/** 设置企业基本信息Dto */
	public void setEnterpriseInfoDto(LoanEnterpriseInfoDto enterpriseInfoDto) {
		this.enterpriseInfoDto = enterpriseInfoDto;
	}

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

	/** 获取个人基本信息Dto */
	public LoanPersonalBasicDto getPersonalBasicDto() {
		return personalBasicDto;
	}

	/** 设置个人基本信息Dto */
	public void setPersonalBasicDto(LoanPersonalBasicDto personalBasicDto) {
		this.personalBasicDto = personalBasicDto;
	}

	/** 获取个人工作信息Dto */
	public LoanPersonalJobDto getPersonalJobDto() {
		return personalJobDto;
	}

	/** 设置个人工作信息Dto */
	public void setPersonalJobDto(LoanPersonalJobDto personalJobDto) {
		this.personalJobDto = personalJobDto;
	}


    /** 获取客户编号 */
    public String getCustomCode() {
        return customCode;
    }

    /** 设置客户编号*/
    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    /** 获取投资记录 */
    public List<InvestInformationDto> getInvestInformations() {
        return investInformations;
    }

    /** 设置投资记录*/
    public void setInvestInformations(List<InvestInformationDto> investInformations) {
        this.investInformations = investInformations;
    }

    /** 获取还款记录 */
    public List<RepayDetail> getRepayDetailList() {
        return repayDetailList;
    }

    /** 设置还款记录*/
    public void setRepayDetailList(List<RepayDetail> repayDetailList) {
        this.repayDetailList = repayDetailList;
    }

    /** 获取当期还款信息 */
    public RepayDetail getPresentRepayDetail() {
        return presentRepayDetail;
    }

    /** 设置当期还款信息*/
    public void setPresentRepayDetail(RepayDetail presentRepayDetail) {
        this.presentRepayDetail = presentRepayDetail;
    }

	/** 获取还款总期数 */
	public Integer getTotalRepayNumber() {
		return totalRepayNumber;
	}

	/** 设置还款总期数 */
	public void setTotalRepayNumber(Integer totalRepayNumber) {
		this.totalRepayNumber = totalRepayNumber;
	}

}
