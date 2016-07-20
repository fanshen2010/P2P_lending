package cn.com.p2p.security.authority.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.query.PfmRoleManageQuery;
import cn.com.p2p.domain.user.repository.PfmUserRoleRepository;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.security.authority.service.MenuAuthManageService;
import cn.com.p2p.security.authority.service.dto.LeftNaviBean;

@Service
public class MenuAuthManageServiceImpl implements MenuAuthManageService {

	@Autowired
	PfmRoleManageQuery pfmRoleMenuQuery;

	@Autowired
	PfmUserRoleRepository pfmUserRoleRepository;
	
	@Override
	public List<LeftNaviBean> getMenuByRoleId(List<Object> roleIdList, String requestUri) {
		System.out.println("*****************请求URL："+ requestUri);
		//当前用户没有role信息
		if (roleIdList == null || roleIdList.size() == 0) {
		    return null;
		}

		List<LeftNaviBean> parent = new ArrayList<LeftNaviBean>();
		
		List<PfmMenu> leftMenuList = pfmRoleMenuQuery.findLeftPfmMenu(roleIdList);
		String activeMenuId = "";
		for (PfmMenu pfmMenu : leftMenuList) {
			
			//menu层次整理 <1 Level> <2 Level, 2 Level>
			if (StringUtils.compare(pfmMenu.getMenuLevel(), "1")) {
				LeftNaviBean access = new LeftNaviBean();
				access.setMenuId(pfmMenu.getMenuId());
				access.setMenuName(pfmMenu.getMenuName());
				access.setStatus(pfmMenu.getStatus());
				access.setMenuClass(pfmMenu.getMenuClass());
				List<PfmMenu> childs = new ArrayList<PfmMenu>();
				for (PfmMenu pfmMenuSecond : leftMenuList) {
					if (requestUri.equals(pfmMenuSecond.getMenuUrl()) && StringUtils.compare(pfmMenuSecond.getMenuLevel(), "3")) {
						activeMenuId = pfmMenuSecond.getMenuParentId();
					}
					if (StringUtils.compare(pfmMenuSecond.getMenuLevel(), "2")
							&& StringUtils.compare(pfmMenuSecond.getMenuParentId(), pfmMenu.getMenuId())) {
						
						//被激活Menu的样式设置
						if (requestUri.equals(pfmMenuSecond.getMenuUrl()) || activeMenuId.equals(pfmMenuSecond.getMenuId())) {
							pfmMenuSecond.setMenuClass("selected");
						}
						childs.add(pfmMenuSecond);

					}
				}
				access.setChilds(childs);
				parent.add(access);

			}
		}
	
		

		return parent;
	}

}
