package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;

import cn.com.p2p.domain.system.entity.UploadFile;

/**
 * 融资企业基本信息Dto
 * 
 * @author 
 *
 */
public class LoanEnterpriseInfoDto {
    
	/** 企业全称 */
	private String enterpriseName;
	
	/** 营业执照号 */
	private String businessIicense;
	
	/** 所属行业  */
	private String industry;
	
	/** 企业规模 */
	private String enterpriseScale;
	
	/** 注册资金 */
	private BigDecimal registeredCapital;
	
	/** 成立年份 */
	private String createdYear;
	
	/** 法定代表人/企业负责人姓名（联系人） */
	private String legalRepresentative;
	
	/** 办公地址 */
	private String officeAddr;
	
	/** 年收益 */
	private BigDecimal annualEarnings;
	
	/** 企业简介 */
	private String brief;
	
	/** 联系电话 */
	private String contactPhone;
	
	/** 联系邮箱 */
	private String contactMail;
	
	
	/** 获取企业全称 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/** 设置企业全称 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/** 获取营业执照号 */
	public String getBusinessIicense() {
		return businessIicense;
	}

	/** 设置营业执照号 */
	public void setBusinessIicense(String businessIicense) {
		this.businessIicense = businessIicense;
	}

	/** 获取所属行业 */
	public String getIndustry() {
		return industry;
	}

	/** 设置所属行业 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/** 获取企业规模 */
	public String getEnterpriseScale() {
		return enterpriseScale;
	}

	/** 设置企业规模 */
	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	/** 获取注册资金 */
	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	/** 设置注册资金 */
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	/** 获取成立年份 */
	public String getCreatedYear() {
		return createdYear;
	}

	/** 设置成立年份 */
	public void setCreatedYear(String createdYear) {
		this.createdYear = createdYear;
	}

	/** 获取法定代表人/企业负责人姓名（联系人） */
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	/** 设置法定代表人/企业负责人姓名（联系人） */
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	/** 获取办公地址 */
	public String getOfficeAddr() {
		return officeAddr;
	}

	/** 设置办公地址 */
	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}

	/** 获取年收益 */
	public BigDecimal getAnnualEarnings() {
		return annualEarnings;
	}

	/** 设置年收益 */
	public void setAnnualEarnings(BigDecimal annualEarnings) {
		this.annualEarnings = annualEarnings;
	}

	/** 获取企业简介 */
	public String getBrief() {
		return brief;
	}

	/** 设置企业简介 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/** 获取联系电话 */
    public String getContactPhone() {
        return contactPhone;
    }

    /** 设置联系电话*/
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /** 获取联系邮箱 */
    public String getContactMail() {
        return contactMail;
    }

    /** 设置联系邮箱*/
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public LoanEnterpriseInfoDto() {
		super();
	}

}
