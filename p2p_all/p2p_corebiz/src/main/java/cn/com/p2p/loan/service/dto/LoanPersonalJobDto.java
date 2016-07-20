package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人工作信息Dto
 * @author 
 *
 */
public class LoanPersonalJobDto {
	
	
	/** 单位名称 */
	private String companyName;
	
	/** 部门	 */
	private String department;

	/** 职位 */
	private String workPosition;

	/** 月收入 */
	private Integer monthlyIncome;

	/** 公司类型 */
	private String companyCategory;//TODO

	/** 公司行业 */
	private String companyIndustry;//TODO
	
	/** 公司规模 */
	private String companySize;//TODO
	
	/** 公司联系号码 */
	private String companyTel;
	
	/** 公司地址*/
	private String address;
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public Integer getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Integer monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	
	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
