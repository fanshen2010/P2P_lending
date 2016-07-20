package cn.com.p2p.system.service.dto;

import java.util.List;

import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

public class MessageReceiverDto {
	
	/** 用户表*/
	private List<PfmUser> pfmUser;
	
	/** 已经设定的用户*/
	private List<PfmUserDto> pfmUsers;
	
	/** 已经设定的用户*/
	private List<PfmUserDto> messageUsers;

	public List<PfmUser> getPfmUser() {
		return pfmUser;
	}

	public void setPfmUser(List<PfmUser> pfmUser) {
		this.pfmUser = pfmUser;
	}

	public List<PfmUserDto> getPfmUsers() {
		return pfmUsers;
	}

	public void setPfmUsers(List<PfmUserDto> pfmUsers) {
		this.pfmUsers = pfmUsers;
	}

	public List<PfmUserDto> getMessageUsers() {
		return messageUsers;
	}

	public void setMessageUsers(List<PfmUserDto> messageUsers) {
		this.messageUsers = messageUsers;
	}
}
