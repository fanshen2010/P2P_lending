package cn.com.p2p.domain.cms.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.cms.entity.Category;

/**
 * CategoryQuery定义
 * 
 *  数据访问层<栏目内容管理>的自定义查询组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 *
 */
public interface CategoryQuery {
	
	/**
	 * 栏目内容管理获取一级标题集合
	 * 
	 * @return List<CategoryDto> 一级标题集合
	 */
	public List<Category> findCategoryFirstTitle();
	
	
	/**
	 * 根据父级栏目Id删除
	 * 
	 * @param parentId 父级栏目Id
	 * @return int 成功条数
	 */
	public int deleteByParentCategory(@Param("parentId")String parentId);
	
}
