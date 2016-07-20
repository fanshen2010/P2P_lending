package cn.com.p2p.security.control.back;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public interface MySecurityMetaDataSourceAccessHandler {

	/**
	 * 提供给应用侧取得权限相关信息
	 * @param url
	 * @return
	 */
	public Collection<ConfigAttribute> getAttributes(String url);
	
	/**
	 * 当用户权限及Menu等变更时，相应等权限控制数据需要刷新
	 */
	public void refresh();
}
