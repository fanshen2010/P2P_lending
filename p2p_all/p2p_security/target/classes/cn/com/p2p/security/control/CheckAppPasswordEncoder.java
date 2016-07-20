package cn.com.p2p.security.control;




import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.DESPlus;



public class CheckAppPasswordEncoder implements PasswordEncoder {


	public String encodePassword(String rawPass, Object salt)
			throws DataAccessException {
		
		String encodePassword="";
		try {
			encodePassword = DESPlus.byteArr2HexStr((rawPass + salt.toString()).getBytes());
		} catch (Exception e) {
			encodePassword="error";
		}
		return encodePassword;
	}


	public boolean isPasswordValid(String encPass,String rawPass,Object salt)
			throws DataAccessException {
		//-------------------------------------------------------对应
//		String encodePassword= encodePassword(rawPass,salt);
//		boolean resultflag = encodePassword.equals(encPass);
		return true;
	}

}
