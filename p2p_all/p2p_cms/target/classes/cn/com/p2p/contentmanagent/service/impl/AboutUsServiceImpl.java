package cn.com.p2p.contentmanagent.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.contentmanagent.service.AboutUsService;
import cn.com.p2p.contentmanagent.service.CategoryService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.CategoryCriteria;
import cn.com.p2p.domain.cms.dto.AboutUsDto;
import cn.com.p2p.domain.cms.entity.Category;
import cn.com.p2p.domain.cms.repository.ArticleRepository;
import cn.com.p2p.domain.cms.repository.CategoryRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;

/**
 * <p>关于我们ServiceImpl</p>
 * @author 
 */
@Service
public class AboutUsServiceImpl implements AboutUsService {

    /** 栏目管理数据组件 */
    @Autowired
    private CategoryRepository categoryRepository;
    
    /** 文章管理数据组件 */
    @Autowired
    private ArticleRepository articleRepository;
    
    /** 栏目内容管理Service */
   	@Autowired
   	private CategoryService categoryService;
    
    /**
     * <p>查询所有的栏目和栏目对应的内容</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  栏目ID
     * @param criteria 分页查询所需查询实体
     * @return AboutUsDto 关于我们DTO，相应栏目及栏目对应的文章集合
     * @description 根据当前点击的栏目ID获取栏目对应的文章集合，
     *              初始化界面时，获取默认排序的第一个栏目
     */
    @Override
    public AboutUsDto findCategoryArticle(AboutUsDto aboutUsDto, ArticleCriteria criteria) {

        // 初始画界面时，aboutUsDto为空.用户选择某一栏目后，此DTO将会包含栏目ID
        if(aboutUsDto == null){
            aboutUsDto = new AboutUsDto();
        }
        
       //查询栏目编码为 platform_1st 的栏目
        List<Category> categories = new ArrayList<Category>();
	    CategoryCriteria categoryCriteria = new CategoryCriteria();
	    categoryCriteria.setCategoryCode("platform_1st", Operator.equal);
	    List<Category> curCategorys = categoryService.getCategoryByCriteria(categoryCriteria);
	    
	  //当前栏目存在，则取当前栏目下所有子栏目,以供左侧显示
	    if(curCategorys != null && !curCategorys.isEmpty()){
	    	categoryCriteria = new CategoryCriteria();
	    	//栏目编码不可重复，curCategorys 正常只有一条数据，所以默认取第一条
	    	categoryCriteria.setParentCategory(curCategorys.get(0).getId(), Operator.equal);
	    	categories = categoryService.getCategoryByCriteria(categoryCriteria);
	    }
	    
        aboutUsDto.setCategorys(categories);
        
        // 用户选择某一栏目后，查询侧栏目的信息，否则获取所有栏目的自然排序中的第一个栏目
        if(aboutUsDto.getCategory() != null){
            aboutUsDto.setCategory(categoryRepository.findOne(aboutUsDto.getCategory().getId()));
        }else if(categories.size() > 0){
            aboutUsDto.setCategory(categories.get(0));
        }
        // 将含有分页参数的查询条件实体赋值给DTO
        aboutUsDto.setCriteria(criteria);
        // 设置查询条件 为 用户选择的栏目ID
        if (aboutUsDto.getCategory() != null) {
        	aboutUsDto.getCriteria().setCategory(aboutUsDto.getCategory().getId(), Operator.equal);
        }
        // 分页查询所有文章实体
        aboutUsDto.setArticles(articleRepository.findPageByCriteria(aboutUsDto.getCriteria()));
        
        return aboutUsDto;
    }

    /**
     * <p>根据主键查询文章</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  文章ID
     * @return AboutUsDto 关于我们DTO，相应文章及菜单对应的栏目集合
     * @description 无
     */
    @Override
    public AboutUsDto findArticle(AboutUsDto aboutUsDto) {
        
    	//查询栏目编码为 platform_1st 的栏目
        List<Category> categories = new ArrayList<Category>();
	    CategoryCriteria categoryCriteria = new CategoryCriteria();
	    categoryCriteria.setCategoryCode("platform_1st", Operator.equal);
	    List<Category> curCategorys = categoryService.getCategoryByCriteria(categoryCriteria);
	    
	  //当前栏目存在，则取当前栏目下所有子栏目,以供左侧显示
	    if(curCategorys != null && !curCategorys.isEmpty()){
	    	categoryCriteria = new CategoryCriteria();
	    	//栏目编码不可重复，curCategorys 正常只有一条数据，所以默认取第一条
	    	categoryCriteria.setParentCategory(curCategorys.get(0).getId(), Operator.equal);
	    	categories = categoryService.getCategoryByCriteria(categoryCriteria);
	    }
    	
        aboutUsDto.setCategorys(categories);
        // 获取文章具体信息
        aboutUsDto.setArticle(articleRepository.findOne(aboutUsDto.getArticle().getId()));
        return aboutUsDto;
    }
    
    /**
     * <p>查询所有的栏目和栏目对应的内容</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  栏目ID
     * @param criteria 分页查询所需查询实体
     * @return AboutUsDto 关于我们DTO，相应栏目及栏目对应的文章集合
     * @description 根据当前点击的栏目ID获取栏目对应的文章集合，
     *              初始化界面时，获取默认排序的第一个栏目
     */
    @Override
    public AboutUsDto findCategoryArticle(AboutUsDto aboutUsDto, ArticleCriteria criteria,CategoryCriteria categoryCriteria) {

        // 初始画界面时，aboutUsDto为空.用户选择某一栏目后，此DTO将会包含栏目ID
        if(aboutUsDto == null){
            aboutUsDto = new AboutUsDto();
        }
        
       //查询栏目编码为 platform_1st 的栏目
        List<Category> categories = new ArrayList<Category>();
	    List<Category> curCategorys = categoryService.getCategoryByCriteria(categoryCriteria);
	    
	  //当前栏目存在，则取当前栏目下所有子栏目,以供左侧显示
	    if(curCategorys != null && !curCategorys.isEmpty()){
	    	categoryCriteria = new CategoryCriteria();
	    	//栏目编码不可重复，curCategorys 正常只有一条数据，所以默认取第一条
	    	categoryCriteria.setParentCategory(curCategorys.get(0).getId(), Operator.equal);
	    	categories = categoryService.getCategoryByCriteria(categoryCriteria);
	    }
	    
        aboutUsDto.setCategorys(categories);
        
        // 用户选择某一栏目后，查询侧栏目的信息，否则获取所有栏目的自然排序中的第一个栏目
        if(aboutUsDto.getCategory() != null){
            aboutUsDto.setCategory(categoryRepository.findOne(aboutUsDto.getCategory().getId()));
        }else if(categories.size() > 0){
            aboutUsDto.setCategory(categories.get(0));
        }
        // 将含有分页参数的查询条件实体赋值给DTO
        aboutUsDto.setCriteria(criteria);
        // 设置查询条件 为 用户选择的栏目ID
        if (aboutUsDto.getCategory() != null) {
        	aboutUsDto.getCriteria().setCategory(aboutUsDto.getCategory().getId(), Operator.equal);
        }
        // 分页查询所有文章实体
        aboutUsDto.setArticles(articleRepository.findPageByCriteria(aboutUsDto.getCriteria()));
        
        return aboutUsDto;
    }
    
    /**
     * <p>根据主键查询文章</p>
     * @author 
     * @param aboutUsDto 关于我们DTO，使用到的参数为  文章ID
     * @return AboutUsDto 关于我们DTO，相应文章及菜单对应的栏目集合
     * @description 无
     */
    @Override
    public AboutUsDto findArticle(AboutUsDto aboutUsDto,CategoryCriteria categoryCriteria) {
        
    	//查询栏目编码为 platform_1st 的栏目
        List<Category> categories = new ArrayList<Category>();
	    List<Category> curCategorys = categoryService.getCategoryByCriteria(categoryCriteria);
	    
	  //当前栏目存在，则取当前栏目下所有子栏目,以供左侧显示
	    if(curCategorys != null && !curCategorys.isEmpty()){
	    	categoryCriteria = new CategoryCriteria();
	    	//栏目编码不可重复，curCategorys 正常只有一条数据，所以默认取第一条
	    	categoryCriteria.setParentCategory(curCategorys.get(0).getId(), Operator.equal);
	    	categories = categoryService.getCategoryByCriteria(categoryCriteria);
	    }
    	
        aboutUsDto.setCategorys(categories);
        // 获取文章具体信息
        aboutUsDto.setArticle(articleRepository.findOne(aboutUsDto.getArticle().getId()));
        return aboutUsDto;
    }
    

}
