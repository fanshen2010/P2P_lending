package cn.com.p2p.contentmanagent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.domain.cms.query.ArticleQuery;
import cn.com.p2p.domain.cms.query.CategoryQuery;
import cn.com.p2p.domain.cms.repository.CategoryRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;

/**
 * 栏目内容管理接口实现类
 * 
 * @author 
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** 内容管理自定义查询接口 */
	@Autowired
	private CategoryQuery categoryQuery;
	
	/** 内容管理数据访问层接口 */
	@Autowired
	private CategoryRepository categoryRepository;
	
	/** 自定义文章管理数据访问层接口 */
	@Autowired
	private ArticleQuery articleQuery;
	
	/**
	 * 获取栏目内容管理列表
	 * @author 
	 * 
	 * @return List<Category> 栏目内容列表
	 */
	@Override
	public List<Category> getCategoryList() {
		
		//返回List
		List<Category> retCategoryList = new ArrayList<Category>();
		//一级栏目List
		List<Category> firstList;
		//二级栏目List
		List<Category> secondList;
		//查询条件
		CategoryCriteria criteria = new CategoryCriteria();
		
		//获取一级栏目
		firstList = categoryQuery.findCategoryFirstTitle();
		
		if(firstList != null && !firstList.isEmpty()){
			
			//遍历一级栏目
			for (Category firCategory : firstList) {
				
				//将一级栏目 Add 到 return List中
				retCategoryList.add(firCategory);
			
				criteria = new CategoryCriteria();
				criteria.setParentCategory(firCategory.getId(), Operator.equal);
				criteria.setOrderBy(OrderField.orderNum);
				
				//根据一级栏目获取二级栏目
				secondList = categoryRepository.findByCriteria(criteria);
				
				if(secondList != null && !secondList.isEmpty()){
					for (Category secCategory : secondList) {
						
						//将当前二级栏目 Add 到 return List中
						retCategoryList.add(secCategory);
					}
				}
			}
		}
		return retCategoryList;
	}
	
	/**
	 * 根据多条件查询栏目列表
	 * @author 
	 * 
	 * @param criteria 参数条件
	 * @return List<Category> 栏目集合
	 */
	@Override
	public List<Category> getCategoryByCriteria(CategoryCriteria criteria){
		
		return categoryRepository.findByCriteria(criteria);
	}
	
	/**
	 * 获取一级栏目DropdownList Map集合
	 * @author 
	 * 
	 * @return Map<String,String> 一级栏目DropdownList Map集合
	 */
	@Override
	public Map<String,String> getFirstCategoryMap(){
		
		Map<String,String> retMap = new HashMap<String, String>();
		//获取一级栏目
		List<Category> firstList = categoryQuery.findCategoryFirstTitle();
		
		//遍历List 将栏目内容添加到Map中
		if(firstList != null && !firstList.isEmpty()){
			for (Category category : firstList) {
				if(category != null){
					retMap.put(category.getId(), category.getTitle());
				}
			}
		}
		
		return retMap;
	}
	
	/**
	 * 根据父级Id 获取二级栏目DropdownList Map集合
	 * @author 
	 * 
	 * @param String parentId 父级Id
	 * @return Map<String,String> 二级栏目DropdownList Map集合
	 */
	@Override
	public Map<String,String> getSecondCategoryMap(String parentId){
		
		Map<String,String> retMap = new HashMap<String, String>();
		
		//查询条件
		CategoryCriteria criteria = new CategoryCriteria();
		criteria.setParentCategory(parentId, Operator.equal);
		
		//根据一级栏目获取二级栏目
		List<Category> secondList =  categoryRepository.findByCriteria(criteria);
		
		//遍历List 将栏目内容添加到Map中
		if(secondList != null && !secondList.isEmpty()){
			for (Category category : secondList) {
				if(category != null){
					retMap.put(category.getId(), category.getTitle());
				}
			}
		}
		
		return retMap;
	}
	
	/**
	 * 根据当前Id获取栏目内容
	 * @author 
	 * 
	 * @param 当前 id
	 * @return Category 栏目内容实体
	 */
	@Override
	public Category getCategoryById(String id){
		
		return categoryRepository.findOne(id);
	}
	

	/**
	 * 插入一条新纪录，正确插入时返回值为 1
	 * @author 
	 * 
	 * @param Category 插入实体
	 * @return 插入成功标志(0:失败、-1：栏目编码重复、1：成功)
	 */
	@Override
	public int insertCategory(Category category){
		
		//TODO 添加Id 暂时没有共通方法直接set
		category.setId(StringUtils.getUUID());
		if(categoryCodeCheck(category.getCategoryCode(),category.getId())){
			
			return -1;
		} else {
			
			return categoryRepository.insert(category);	
		}
	}
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
	 * @author 
	 * 
	 * @param Category 更新实体
	 * @return 更新成功标志(0:失败、-1：栏目编码重复、1：成功)
	 */
	@Override
	public int dynamicUpdateCategory(Category category){
		
		if(categoryCodeCheck(category.getCategoryCode(),category.getId())){
			return -1;
		} else {
			
			return categoryRepository.dynamicUpdate(category);
		}
	}
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行批量更新
	 * * @author 
	 * 
	 * @param List<Category> 更新实体集合
	 * @return 更新成功标志 （0：更新失败、-1：排序字段为空、>0：更新成功）
	 */
	@Override
	public int batchDynamicUpdateCategory(List<Category> categoryList){
		
		int ret = 0;
		
		for (Category category : categoryList) {
			if(category != null) {
				
				if(category.getOrderNum() == null){ 
						ret = -1;
						break;
					} 
				
				ret = ret + categoryRepository.dynamicUpdate(category);	
			}
		}
		
		return ret;
	}
	
	/**
	 * 根据当前Id进行Delete删除
	 * @author 
	 * 
	 * @param String id 当前Id
	 * @return 删除成功条数
	 */
	@Override
	public int deleteById(String id){
		
		List<Category> categoryList;
		CategoryCriteria criteria = new CategoryCriteria();
		criteria.setParentCategory(id, Operator.equal);
		
		//查询当前栏目下是否存在子栏目
		categoryList = categoryRepository.findByCriteria(criteria);
		
		//存在子栏目则遍历子栏目，删除所有文章
		if(categoryList != null && !categoryList.isEmpty()){
			String currentId = "";//当前栏目id
			
			for (Category category : categoryList) {
				if(category != null && !currentId.equals(category.getId())){
					
					//改变当前栏目Id
					currentId=category.getId();	
					//当前Id发生改变则删除当前栏目下所有文章
					articleQuery.deleteByCategory(currentId);
					//删除当前子栏目
					categoryRepository.delete(currentId);
				}
			}
			
		} else {//当前栏目没有子栏目 则删除该栏目下所有文章
			
			articleQuery.deleteByCategory(id);
		}
		
		return categoryRepository.delete(id);	//删除当前栏目
	}
	
	/**
	 * 栏目编码重复校验
	 * @author 
	 * 
	 * @param categoryCode 栏目编码
	 * @param categoryId 当前Id
	 * 
	 * @return 校验结果（存在返回 true）
	 */
	@Override
	public boolean categoryCodeCheck(String categoryCode,String categoryId){
		boolean checkFlag = false;
		
		List<Category> categoryList;
		CategoryCriteria criteria = new CategoryCriteria();
		criteria.setCategoryCode(categoryCode, Operator.equal);
		
		//根据当前栏目编码进行查询
		categoryList = categoryRepository.findByCriteria(criteria);
		
		//存在当前栏目编码则返回true
		if(categoryList != null && !categoryList.isEmpty()){

			//排除当前Id这条数据
			for (Category category : categoryList) {
				if(!StringUtils.equals(category.getId(), categoryId)){
					checkFlag = true;
					break;
				}
			}
		}
		
		return checkFlag;
	}
}
