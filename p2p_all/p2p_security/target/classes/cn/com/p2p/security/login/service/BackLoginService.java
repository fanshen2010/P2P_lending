package cn.com.p2p.security.login.service;


public interface BackLoginService {
	
	/**
	 * 
	 * @param userId
	 */
	public void doCountLogout(String userId);

	/**
	 * 
	 * @param userId
	 */
	public void doCountLogin(String userId);

	/**
	 * 
	 * @param currentIp
	 */
	public void doLoginSuccess(String currentIp);

    /**
     * 
     * @param j_username
     * @param ip
     */
	public int doLoginFailure(String j_username, String ip);	

}
