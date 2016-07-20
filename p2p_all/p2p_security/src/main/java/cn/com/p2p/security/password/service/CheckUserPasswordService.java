package cn.com.p2p.security.password.service;

public interface CheckUserPasswordService {

	
	
	/**
	 * 校验密码check
	 * @param rawPass 校验密码
	 * @param userLogin  登录人ID
	 * @param backFlag 前后台flag 前台 false  后台true 
	 * @return
	 */
	public boolean checkPassword(String rawPass,String userLogin,boolean backFlag);
}
