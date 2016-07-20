package cn.com.p2p.mgr.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.businessmanagentsys.service.SysMenuMangentService;
import cn.com.p2p.businessmanagentsys.service.dto.MenuListDto;
import cn.com.p2p.domain.user.criteria.PfmMenuCriteria;
import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.framework.web.validator.ValidatorErrorParam;

@Namespace("/system/menu")
@Results({
		@Result(name = "menuList", location = "menuList.ftl", type = "freemarker"),
		@Result(name = "menuEdit", location = "menuEdit.ftl", type = "freemarker"),
		@Result(name = "menuAdd", location = "menuAdd.ftl", type = "freemarker"),
		@Result(name = BaseAction.SAVE, location = "menuList.htm", type = "redirect"),
		@Result(name = BaseAction.UPDATE, location = "menuList.htm", type = "redirect"),
		@Result(name = BaseAction.DELETE, location = "menuList.htm", type = "redirect"),})

public class MenuManagementAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 系统菜单管理接口*/
	@Autowired
	private SysMenuMangentService sysMenuMangentService;
	/** 模板引擎*/
	@Autowired
	protected FeroFreemarkerProcessor feroFreemarkerProcessor;
	/** 融资信息列表*/
	private List<MenuListDto> menuLists= new ArrayList<MenuListDto>();
	/**系统菜单实体*/
	private PfmMenu pfmMenu;
    /**菜单ID*/
	private String menuId;
	/** 菜单排序 */
    private List<PfmMenu> orderNums = new ArrayList<PfmMenu>();
	
	@Override
	@Action(value = "menuList")
	public String init() throws Exception {
		//查询菜单信息
    	menuLists = sysMenuMangentService.getMenuList();
        return "menuList";
	}
    
	/**
	 * 菜单添加页面
	 * @return menuList
	 */
    @Action(value = "menuAdd")
    public void menuAdd() throws Exception{
    	Map<String, Object> map = getAjaxMap();
    	//菜单级别不同，赋不同的初始值
    	if(map.get("menuId")!=null && map.get("menuId")!=""){
			String menuParentId = map.get("menuId").toString();
			pfmMenu = sysMenuMangentService.findMenuById(menuParentId);
			
			map.clear();
			map.put("menuParentId", menuParentId);
			map.put("menuParentName", pfmMenu.getMenuName());
			if(pfmMenu.getMenuLevel().equals("1")){
				map.put("menuLevel", "2");
			}else{
				map.put("menuLevel", "3");
			}
		}else{
			map.clear();
			map.put("menuLevel", "1");	
		}
		
		// 得到渲染好的模板内容
		String result = feroFreemarkerProcessor.process(
				"/system/menu/menuAdd.ftl", map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
    }
	
	/**
	 * 系统菜单插入一条新纪录
	 * @author 
	 */
    @Validators(str="menuCheck",result = "menuAdd", param = "save")
	@Action(value="save")
	public String save() throws Exception{
		if(pfmMenu.getMenuParentId().equals("")){
			pfmMenu.setMenuParentId(null);
		}
		//ID赋值
		pfmMenu.setMenuId(StringUtils.getUUID());
		//保存菜单信息
		sysMenuMangentService.insertMenu(pfmMenu);
		return SAVE;
	}
	
	/**
	 * 菜单修改页面
	 * @author 
	 */
	@Action(value = "menuEdit")
	public void menuEdit() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String menuId = map.get("menuId").toString();
		//根据ID获取菜单信息
		pfmMenu = sysMenuMangentService.findMenuById(menuId);
		PfmMenu parentPfmMenu = sysMenuMangentService.findMenuById(pfmMenu.getMenuParentId());
		map.clear();
		map.put("pfmMenu", pfmMenu);
		map.put("parentPfmMenu", parentPfmMenu);
		// 得到渲染好的模板内容
		String result = feroFreemarkerProcessor.process(
				"/system/menu/menuEdit.ftl", map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	
	/**
	 * 系统菜单更新
	 * @author 
	 */
	@Validators(str="menuCheck",result = "menuEdit", param = "update")
	@Action(value="update")
	public String update() throws Exception{
		//更新菜单信息
		sysMenuMangentService.updateMenu(pfmMenu);
		return UPDATE;
	}
	
	/**
	 * 系统菜单删除纪录
	 * @author 
	 */
	@Action(value="delete")
	public String delete() throws Exception{
		//删除单条数据
		sysMenuMangentService.deleteMenu(menuId);
		return DELETE;
	}
	
	/**
	 * 系统菜单删除页面
	 * @author 
	 */
	@Action(value="menuDeleteCheck")
	public void menuDeleteCheck() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String menuId = map.get("menuId").toString();
		//根据ID获取菜单信息
		PfmMenuCriteria criteria = new PfmMenuCriteria();
		criteria.setMenuParentId(menuId,Operator.equal);
		List<PfmMenu> pfmMenus = sysMenuMangentService.findByCriteria(criteria);
		map.clear();
		map.put("menuId", menuId);
		map.put("size", pfmMenus.size());
		// ajax返回 
		Struts2Utils.renderJson(map);
	}
	
	/**
	 * 系统菜单排序更新
	 * @author 
	 */
	@Validators(str="menuOrderNumsCheck",result = "menuList", param = "updateOrder")
	@Action(value="updateOrder")
    public String saveorder() throws Exception {
		//更新菜单排序
		sysMenuMangentService.updateMenus(orderNums);
        return UPDATE;
    }
	
	/**
	 * 数据验证出错后回调处理，
	 * 
	 * @param param 注解中的参数
	 * @param requestMap
	 */

	public void doDataValidatorFailure(String param,ValidatorErrorParam requestMap){
		// 菜单列表
		menuLists = sysMenuMangentService.getMenuList();
	}


	public List<MenuListDto> getMenuLists() {
		return menuLists;
	}

	public void setMenuLists(List<MenuListDto> menuLists) {
		this.menuLists = menuLists;
	}

	public PfmMenu getPfmMenu() {
		return pfmMenu;
	}

	public void setPfmMenu(PfmMenu pfmMenu) {
		this.pfmMenu = pfmMenu;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public List<PfmMenu> getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(List<PfmMenu> orderNums) {
		this.orderNums = orderNums;
	}
	

}
