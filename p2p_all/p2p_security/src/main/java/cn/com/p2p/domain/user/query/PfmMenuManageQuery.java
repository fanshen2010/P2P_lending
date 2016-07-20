package cn.com.p2p.domain.user.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.user.entity.PfmMenu;

public interface PfmMenuManageQuery {
	
	/**
	 * 
	  * 
	  * <p> 根据父级菜单ID查找菜单信息* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param menuParentId 父级菜单ID
	  * @return List<PfmMenu>
	  *
	 */
	public List<PfmMenu> findPfmMenuByParentId(@Param("menuParentId") String menuParentId);

}
