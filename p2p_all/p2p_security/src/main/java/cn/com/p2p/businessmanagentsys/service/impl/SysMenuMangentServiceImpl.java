package cn.com.p2p.businessmanagentsys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.businessmanagentsys.service.SysMenuMangentService;
import cn.com.p2p.businessmanagentsys.service.dto.MenuListDto;
import cn.com.p2p.domain.user.criteria.PfmMenuCriteria;
import cn.com.p2p.domain.user.criteria.PfmRoleMenuCriteria;
import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.entity.PfmRoleMenu;
import cn.com.p2p.domain.user.query.PfmMenuManageQuery;
import cn.com.p2p.domain.user.repository.PfmMenuRepository;
import cn.com.p2p.domain.user.repository.PfmRoleMenuRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;

/**
 * 系统菜单管理接口的实现
 * @author wangxiangshun
 *
 */
@Service
public class SysMenuMangentServiceImpl implements SysMenuMangentService{
	
	/**
	 * 菜单查询
	 */
	@Autowired
	private PfmMenuRepository pfmMenuRepository;
	/**
	 * 菜单角色关联数据组件
	 */
	@Autowired
	private PfmRoleMenuRepository pfmRoleMenuRepository;
	
	/**
	 * 菜单查询
	 */
	@Autowired
	private PfmMenuManageQuery pfmMenuManageQuery;
	
	/**
	 * 根据ROLEID 返回权限MAP
	 */
	@Override
	public Map<String, Object> findPfmMenuByroleId(String roleId) {
		List<String> menuIdList = new ArrayList<String>();
		PfmRoleMenuCriteria criteria = new PfmRoleMenuCriteria();
		criteria.setRoleId(roleId, Operator.equal);
		//角色所具有的权限
		List<PfmRoleMenu> pfmRoleMenuList = pfmRoleMenuRepository.findByCriteria(criteria);
		//获得角色所具有的权限ID List
		for(int j = 0 ; j < pfmRoleMenuList.size() ; j++){
			menuIdList.add(pfmRoleMenuList.get(j).getMenuId());
		}
		PfmMenuCriteria  pfmCriteria = new PfmMenuCriteria();
		//第一级菜单
		pfmCriteria.setMenuLevel("1",Operator.equal);
		List<PfmMenu> firstList = pfmMenuRepository.findByCriteria(pfmCriteria);
		//第二级菜单
		pfmCriteria.setMenuLevel("2",Operator.equal);
		List<PfmMenu> secondList = pfmMenuRepository.findByCriteria(pfmCriteria);
		//第三级菜单
		pfmCriteria.setMenuLevel("3",Operator.equal);
		List<PfmMenu> thirdList = pfmMenuRepository.findByCriteria(pfmCriteria);
		//返回MAP
		Map<String,Object> checkBoxListData = new HashMap<String, Object>();
		if(firstList!=null&&firstList.size()!=0){
			for(Integer i = 0 ; i<firstList.size() ; i++){//第一级菜单
				Map<String, Object> level1 = new HashMap<String, Object>();
				Map<String, Map<String, Object>> children1 = new HashMap<String, Map<String, Object>>();
				level1.put("name", firstList.get(i).getMenuName());//第一级菜单的名字
				//判断功能菜单在是不是在所具有的限权中存在
				if(menuIdList.contains(firstList.get(i).getMenuId())){
					level1.put("checked",true);//是否选中
				}else{
					level1.put("checked",false);//是否选中
				}
				if(secondList!=null&&secondList.size()!=0){
					for(int j = 0 ; j < secondList.size() ; j++){//第二级菜单
						if(firstList.get(i).getMenuId().equals(secondList.get(j).getMenuParentId())){//如果二级节点的父亲为一级节点的ID
							Map<String, Object> level2 = new HashMap<String, Object>();
							Map<String, Map<String, Object>> children2 = new HashMap<String, Map<String, Object>>();
							level2.put("name", secondList.get(j).getMenuName());
							//判断功能菜单在是不是在所具有的限权中存在
							if(menuIdList.contains(secondList.get(j).getMenuId())){
								level2.put("checked",true);//是否选中
							}else{
								level2.put("checked",false);//是否选中
							}
							if(thirdList!=null&&thirdList.size()!=0){
								for(int n = 0 ; n < thirdList.size() ; n++){//第三级菜单--功能项
									if(secondList.get(j).getMenuId().equals(thirdList.get(n).getMenuParentId())){//如果三级节点的父亲为二级节点的ID
										Map<String, Object> level3 = new HashMap<String, Object>();
										level3.put("name",thirdList.get(n).getMenuName());
										//判断功能菜单在是不是在所具有的限权中存在
										if(menuIdList.contains(thirdList.get(n).getMenuId())){
											level3.put("checked",true);//是否选中
										}else{
											level3.put("checked",false);//是否选中
										}
										level3.put("children", null);
										//第三级功能菜单的ID
										children2.put(thirdList.get(n).getMenuId(), level3);
									}
								}
							}
							level2.put("children", children2);
							//第二级菜单的ID
							children1.put(secondList.get(j).getMenuId(), level2);
						}
					}
				}
				level1.put("children", children1);
				//第一级菜单的ID
				checkBoxListData.put(firstList.get(i).getMenuId(), level1);
			}
		}
		return checkBoxListData;
	}
	
	/**
     * 系统菜单查询（ 分级查询所有菜单信息）
     * 
     * author：
     * 
     * @return List<menuListDto>菜单Dto
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
     */
	@Override
	public List<MenuListDto> getMenuList() {
		List<MenuListDto> menuLists1= new ArrayList<MenuListDto>();
		
		//查询第一级菜单
		List<PfmMenu> pfmRoleMenu1 = pfmMenuManageQuery.findPfmMenuByParentId(null);
		
		for(int a = 0;a < pfmRoleMenu1.size(); a ++){
			//系统菜单一级信息Dto
			MenuListDto menuListDto1 = new MenuListDto();
			try {
				BeanUtils.copyProperties(menuListDto1,pfmRoleMenu1.get(a));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//查询该栏目下的二级菜单
			List<PfmMenu> pfmRoleMenu2 = pfmMenuManageQuery.findPfmMenuByParentId(pfmRoleMenu1.get(a).getMenuId());
			List<MenuListDto> menuLists2= new ArrayList<MenuListDto>();
				for(int b = 0;b < pfmRoleMenu2.size(); b++){
					//系统菜单二级信息Dto
					MenuListDto menuListDto2 = new MenuListDto();
					try {
						BeanUtils.copyProperties(menuListDto2,pfmRoleMenu2.get(b));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					//查询该栏目下的三级菜单
					List<PfmMenu> pfmRoleMenu3 = pfmMenuManageQuery.findPfmMenuByParentId(pfmRoleMenu2.get(b).getMenuId());
					List<MenuListDto> menuLists3= new ArrayList<MenuListDto>();
					for(int c = 0;c < pfmRoleMenu3.size(); c++){
						//系统菜单三级信息Dto
						MenuListDto menuListDto3 = new MenuListDto();
						try {
							BeanUtils.copyProperties(menuListDto3,pfmRoleMenu3.get(c));
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						menuListDto3.setMenuList(null);
						menuLists3.add(menuListDto3);
					}
					menuListDto2.setMenuList(menuLists3);
					menuLists2.add(menuListDto2);
				}
			menuListDto1.setMenuList(menuLists2);
			menuLists1.add(menuListDto1);
		}
		return menuLists1;
	}
	
	/**
     * 根据ID查询菜单信息
     * 
     * author：
     * 
     * @return PfmMenu
     */
	public PfmMenu findMenuById(String menuId){
		PfmMenu pfmMenu = pfmMenuRepository.findOne(menuId);
		return pfmMenu;
	}
	
	/**
     * 系统菜单插入一条新纪录
     * 
     * author：
     * 
     * @return int 正确插入时返回值为 1
     */
	public boolean insertMenu(PfmMenu pfmMenu){
		pfmMenuRepository.insert(pfmMenu);
		return true;
	}
	
	/**
     * 系统菜单更新一条纪录
     * 
     * author：
     * 
     * @return int 正确插入时返回值为 1
     */
	public boolean updateMenu(PfmMenu pfmMenu){
		pfmMenuRepository.dynamicUpdate(pfmMenu);
		return true;
	}
	
	/**
     * 系统菜单更新多条纪录
     * 
     * author：
     * 
     * @return int 正确插入时返回值为 1
     */
	public boolean updateMenus(List<PfmMenu> orderNums){
		for(int a = 0;a < orderNums.size(); a ++){
			pfmMenuRepository.dynamicUpdate(orderNums.get(a));
			System.out.println(orderNums.get(a).getMenuId());
			System.out.println(orderNums.get(a).getOrderNum());
		}
		return true;
	}
	
	/**
     * 系统菜单删除纪录
     * 
     * author：
     * 
     * @return int 正确删除时返回值为 1
     */
	public boolean deleteMenu(String menuId){
		//定义动态查询条件
		PfmMenuCriteria criteria = new PfmMenuCriteria();
		//设定条件
		criteria.setMenuParentId(menuId,Operator.equal);
		//根据条件查询
		List<PfmMenu> PfmMenus2 = pfmMenuRepository.findByCriteria(criteria);
		for(int a = 0;a < PfmMenus2.size(); a ++){
			List<PfmMenu> PfmMenus3 = pfmMenuRepository.findByCriteria(criteria);
			for(int b = 0;b < PfmMenus3.size(); b ++){
				pfmMenuRepository.delete(PfmMenus3.get(b).getMenuId());
			}
			pfmMenuRepository.delete(PfmMenus2.get(a).getMenuId());
		}
		
		//删除菜单
		pfmMenuRepository.delete(menuId);
		
		return true;
	}
	
	/**
     * 按条件动态检索，返回实体集合
     * 
     * author：
     * 
     * @return PfmMenus
     */
	public List<PfmMenu> findByCriteria(PfmMenuCriteria criteria){
		List<PfmMenu> PfmMenus = pfmMenuRepository.findByCriteria(criteria);
		
		return PfmMenus;
	}
}
