package cn.com.p2p.businessmanagentsys.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cn.com.p2p.businessmanagentsys.service.dto.MenuListDto;
import cn.com.p2p.domain.user.criteria.PfmMenuCriteria;
import cn.com.p2p.domain.user.entity.PfmMenu;

/**
 * 系统菜单管理接口
 * @author wangxiangshun
 *
 */
public interface SysMenuMangentService {
	
	/**
	 * 
		 * 根据ROLEID 返回权限MAP---并且验证是否已经选中
		 * <p> * </p>.<br> 说明：
		 * 
		 * author：wangxiangshun<br> 
		 * ===================================
		 * @param criteria
	 */
	public Map<String, Object> findPfmMenuByroleId(String roleId);
	
	/**
     * 系统菜单查询（ 分级查询所有菜单信息）
     * 
     * author：
     * 
     * @return List<menuListDto>菜单Dto
     */
	public List<MenuListDto> getMenuList();
	
	/**
     * 根据ID查询菜单信息
     * 
     * author：
     * 
     * @return PfmMenu
     */
	public PfmMenu findMenuById(String menuId);
	
	/**
     * 系统菜单插入一条新纪录
     * 
     * author：
     * @param pfmMenu
     */
	public boolean insertMenu(PfmMenu pfmMenu);
	
	/**
     * 系统菜单更新一条纪录
     * 
     * author：
     * @param pfmMenu
     */
	public boolean updateMenu(PfmMenu pfmMenu);
	
	/**
     * 系统菜单更新纪录
     * 
     * author：
     * @param pfmMenu
     */
	public boolean updateMenus(List<PfmMenu> orderNums);
	
	/**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param menuId
     * @return 删除成功标志
     */
	public boolean deleteMenu(String menuId);
	
	/**
     * 按条件动态检索，返回实体集合
     * 
     * author：
     * @param PfmMenuCriteria criteria
     * @return List<PfmMenu>
     */
	public List<PfmMenu> findByCriteria(PfmMenuCriteria criteria);

}
