package cn.com.p2p.security.authority.service;

import java.util.List;

import cn.com.p2p.security.authority.service.dto.LeftNaviBean;

public interface MenuAuthManageService {

	/**
	 * 通过用户的userId获取当前用户可操作的menu。
	 * @param roleIdList
	 * @return
	 */
	List<LeftNaviBean> getMenuByRoleId(List<Object> roleIdList, String requestUri);
}
