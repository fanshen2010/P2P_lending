package cn.com.p2p.usermangent.service.dto;

import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;

public class PfmTenantDepartmentDto {
	
	private PfmTenantDepartment pfmTenantDepartment;
	
	private PfmTenantDepartmentInfo pfmTenantDepartmentInfo;

	public PfmTenantDepartment getPfmTenantDepartment() {
		return pfmTenantDepartment;
	}

	public void setPfmTenantDepartment(PfmTenantDepartment pfmTenantDepartment) {
		this.pfmTenantDepartment = pfmTenantDepartment;
	}

	public PfmTenantDepartmentInfo getPfmTenantDepartmentInfo() {
		return pfmTenantDepartmentInfo;
	}

	public void setPfmTenantDepartmentInfo(PfmTenantDepartmentInfo pfmTenantDepartmentInfo) {
		this.pfmTenantDepartmentInfo = pfmTenantDepartmentInfo;
	}
}
