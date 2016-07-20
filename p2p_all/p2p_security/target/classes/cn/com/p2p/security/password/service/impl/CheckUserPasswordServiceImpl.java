package cn.com.p2p.security.password.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.repository.PfmUserRepository;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.security.control.CheckUserPasswordEncoder;
import cn.com.p2p.security.password.service.CheckUserPasswordService;

@Service
public class CheckUserPasswordServiceImpl implements CheckUserPasswordService {
	@Autowired
	PfmUserRepository pfmUserRepo;
	@Autowired
	WebUserRepository WebUserRepository;
	@Autowired
	CheckUserPasswordEncoder passwordEncoderUser;
	@Override
	public boolean checkPassword(String rawPass, String userLogin,
			boolean backFlag) {
		if(backFlag){
			PfmUser pfmuser = pfmUserRepo.findOne(userLogin);
			return passwordEncoderUser.isPasswordValid(pfmuser.getPassword(), rawPass, pfmuser.getPasswordSalt());
		}
		return false;
	}

}
