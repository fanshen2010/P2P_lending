package cn.com.p2p.contentmanagent.service;

import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.entity.Category;

/**
 * 栏目内容管理接口类
 * 
 * @author 
 *
 */
public interface CategoryService {
	
	/**
	 * 获取栏目内容管理列表
	 * @author 
	 * 
	 * @return List<Category> 栏目内容列表
	 */
	public List<Category> getCategoryList();
	
	
	/**
	 * 根据多条件查询栏目列表
	 * @author 
	 * 
	 * @param criteria 参数条件
	 * @return List<Category> 栏目集合
	 */
	public List<Category> getCategoryByCriteria(CategoryCriteria criteria);
	
	/**
	 * 获取一级栏目DropdownList Map集合
	 * @author 
	 * 
	 * @return Map<String,String> 一级栏目DropdownList Map集合
	 */
	public Map<String,String> getFirstCategoryMap();
	
	/**
	 * 根据父级Id 获取二级栏目DropdownList Map集合
	 * @author 
	 * 
	 * @param String parentId 父级Id
	 * @return Map<String,String> 二级栏目DropdownList Map集合
	 */
	public Map<String,String> getSecondCategoryMap(String parentId);
	 
	/**
	 * 根据当前Id获取栏目内容
	 * @author 
	 * 
	 * @param 当前 id
	 * @return Category 栏目内容实体
	 */
	public Category getCategoryById(String id);
	
	/**
	 * 插入一条新纪录，正确插入时返回值为 1
	 * @author 
	 * 
	 * @param Category 插入实体
	 * @return 插入成功标志(0:失败、-1：栏目编码重复、1：成功)
	 */
	public int insertCategory(Category category);
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
	 * @author 
	 * 
	 * @param Category 更新实体
	 * @return 更新成功标志(0:失败、-1：栏目编码重复、1：成功)
	 */
	public int dynamicUpdateCategory(Category category);
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行批量更新
	 * * @author 
	 * 
	 * @param List<Category> 更新实体集合
	 * @return 更新成功标志（ 0：更新失败、-1：排序字段为空、>0：更新成功）
	 */
	public int batchDynamicUpdateCategory(List<Category> categoryList);
	
	/**
	 * 根据当前Id进行Delete删除
	 * @author 
	 * 
	 * @param String id 当前Id
	 * @return 删除成功条数
	 */
	public int deleteById(String id);
	
	/**
	 * 栏目编码重复校验
	 * @author 
	 * 
	 * @param categoryCode 栏目编码
	 * @param categoryId 当前Id
	 * 
	 * @return 校验结果（存在返回 true）
	 */
	public boolean categoryCodeCheck(String categoryCode,String categoryId);

}
