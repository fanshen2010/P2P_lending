package cn.com.p2p.security.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.com.p2p.domain.user.entity.PfmPostRole;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.query.PfmRoleManageQuery;
import cn.com.p2p.domain.user.repository.PfmUserRepository;
import cn.com.p2p.domain.user.repository.PfmUserRoleRepository;
import cn.com.p2p.security.login.service.BackLoginService;
import cn.com.p2p.security.login.service.dto.LoginUserDetail;


public class BackLoginServiceImpl implements UserDetailsService,BackLoginService {

    @Autowired
    public Properties settings;
	
	@Autowired
	PfmUserRepository pfmUserRepo;
	
	@Autowired
	PfmUserRoleRepository pfmUserRoleRepo;
	
	@Autowired
	PfmRoleManageQuery pfmRoleMangeQuery;
	

	/**
	 * 用户登录 spring security借口实现
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		LoginUserDetail lud = null;
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		List<String> roleIdList = new ArrayList<String>();

		String phoneNumber = "";
		String realName = "";
		String identity = "";

		// 后台登录用户取得
		PfmUser pfmuser = pfmUserRepo.findPfmUserByUserName(username);
        
		//用户不存在
		if(pfmuser == null){
			lud = new LoginUserDetail();
			lud.setId("");
			lud.setUsername("");
			lud.setPassword("");
			lud.setSalt("");
			lud.setRealName(realName);
			lud.setAuthorities(list);
		} else{
			String postCd = pfmuser.getPostCd();
			
			//用户与角色关联 暂时替换为职位与角色关联
			//List<PfmUserRole> userRoleList = pfmUserRoleRepo.findPfmUserRoleByUserId(pfmuser.getId());
			List<PfmPostRole> postRoleList = pfmRoleMangeQuery.findPfmUserRoleByPost(postCd);
			for(PfmPostRole fms : postRoleList){
				list.add(new SimpleGrantedAuthority(fms.getRoleId()));
				roleIdList.add(fms.getRoleId());
			}
			
			//默认是没有激活状态
			boolean activeflag = false;
			//active 1 是被激活状态 0是没被激活
			if ("1".equals(pfmuser.getActive())) {
				activeflag = true;
			}
			
			boolean isNotExpired = false;
			//Valid 1 是有效状态 0 是已经过期
			if ("1".equals(pfmuser.getValidFlag())) {
				isNotExpired = true;
			}
			
			//lock 1 是用户锁定 0 是用户没有锁
			boolean isNotLocked = false;
			if ("0".equals(pfmuser.getLockFlag())) {
				isNotLocked = true;
			}
			
			lud = new LoginUserDetail(
					pfmuser.getId(),
					pfmuser.getUserName(),
					pfmuser.getPassword(),
					pfmuser.getPasswordSalt(),
					pfmuser.getPasswordOperation(),
					pfmuser.getPasswordOperationSalt(),
					list,
					pfmuser.getTenantCd(),
					pfmuser.getDepartCd(),
					roleIdList,
					pfmuser.getContactPhone(),
					pfmuser.getRealName(),
					identity,
					activeflag,
					isNotExpired,
					isNotLocked);
			lud.setPostCd(postCd);
		}


		return lud;
	}


	@Override
	public void doCountLogout(String userId){

		//loginCountDao.doCountLogout(userId);
	}


	@Override
	public void doCountLogin(String userId){

		//loginCountDao.doCountLogin(userId);
	}


	@Override
	public void doLoginSuccess(String currentIp){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			if(authentication.getPrincipal() != null && authentication.getPrincipal() instanceof LoginUserDetail){
				LoginUserDetail userDetails = (LoginUserDetail) authentication.getPrincipal();
				if(userDetails != null){
					PfmUser pfmuser = pfmUserRepo.findPfmUserByUserName(userDetails.getUsername());
					pfmuser.setLastLoginAt(pfmuser.getCurrentLoginAt());// 上次登录时间
					pfmuser.setLastLoginIp(pfmuser.getCurrentLoginIp());// 上次登录ip
					pfmuser.setCurrentLoginIp(currentIp);// 本次登录ip
					pfmuser.setCurrentLoginAt(new Date());// 最后登录时间
					pfmuser.setFailureLoginAccount(0);
					pfmUserRepo.dynamicUpdate(pfmuser);
				}
			}
		}

	}


	@Override
	public int doLoginFailure(String j_username,String ip){
		int failCnt = 0;
		String userName = j_username;
		if(userName != null && userName.length() > 0){
			PfmUser pfmuser = pfmUserRepo.findPfmUserByUserName(j_username);
			if(pfmuser != null){
				pfmuser.setLastLoginAt(pfmuser.getCurrentLoginAt());// 上次登录时间
				pfmuser.setLastLoginIp(pfmuser.getCurrentLoginIp());// 上次登录ip
				pfmuser.setCurrentLoginIp(ip);// 本次登录ip
				pfmuser.setCurrentLoginAt(new Date());// 最后登录时间
				pfmuser.setUpdateTime(new Date());
				if (pfmuser.getFailureLoginAccount() != null) {
					failCnt = pfmuser.getFailureLoginAccount() + 1;
				} else {
					failCnt = 1;
				}
				
				pfmuser.setFailureLoginAccount(failCnt);
				int lockcnt = 6;
				String lockcntStr = settings.getProperty("backuser_lockcount");
				if (lockcntStr != null) {
					lockcnt = Integer.parseInt(lockcntStr);
				}
				if(failCnt >= lockcnt){
					// 当天超过10密码输错账户锁定，并且发送短信
					//lockFlag 0 没有lock 1 lock
					pfmuser.setLockFlag("1");
					// 您正在登录[瑞赢在线]管理平台，并且已经超出允许最大尝试登录次数，为了您的帐户安全，系统已锁定您的帐户，请联系系统管理员
					// 平台名
					String sysName = "多账户P2P平台";
					//sendMailMessage.send(pfmuser.getTenantCd(), pfmuser.getEmailAddress(), "3", true, sysName, pfmuser.getRealName());
				}
				pfmUserRepo.dynamicUpdate(pfmuser);
			}
		}
		return failCnt;
	}
}
