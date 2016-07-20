package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;
import cn.com.p2p.utils.Constants;

/**
 * <p>栏目内容管理校验</p>
 * @author 
 * @description 验证要求<br>
 *              1.名称：必填、16个字符以内<br>
 *              2.SEO关键字： 32个字符以内<br>
 *              3.SEO描述：64个字符以内<br>
 *              4.栏目排序：必填、小于1000的正整数<br>
 *              5.栏目编码：必填<br>
 *              6.页面类型：必填<br>
 *              7.打开链接方式：必填<br>
 *              8.每页显示条数：必填、小于等于100的正整数
 */
@Service("categoryCheck")
public class CategoryCheck extends BaseCheck  implements DataCheck {

	  public List<String> check(BaseAction action, Map<String, Object> parameters){
	        dataParameters=parameters;
	        errors= new ArrayList<String>();
	        
	    	/* 名称 */
	    	addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.title",getFieldValue("category.title"),null,new String[]{"名称"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"category.title",getFieldValue("category.title"),getParamsValue(new String[]{"max","16"}),new String[]{"名称"}));
	    	
	    	/* SEO关键字 */
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"category.seoKeywords",getFieldValue("category.seoKeywords"),getParamsValue(new String[]{"max","32"}),new String[]{"SEO关键字"}));
	    	
	    	/* SEO描述 */
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"category.seoDescription",getFieldValue("category.seoDescription"),getParamsValue(new String[]{"max","64"}),new String[]{"SEO描述"}));
	    	
	    	/* 栏目排序 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.orderNum",getFieldValue("category.orderNum"),null,new String[]{"栏目排序"}));
	        addError(numberCheck.checkData(this,ValidatorTypeEnum.Length,"category.orderNum",getFieldValue("category.orderNum"),getParamsValue(new String[]{"numberRange","1","999"}),new String[]{"栏目排序"}));
	    	
	    	/* 栏目编码*/
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.categoryCode",getFieldValue("category.categoryCode"),null,new String[]{"栏目编码"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"category.categoryCode",getFieldValue("category.categoryCode"),getParamsValue(new String[]{"max","20"}),new String[]{"栏目编码"}));
	        
	    	/* 页面类型 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.categoryType",getFieldValue("category.categoryType"),null,new String[]{"页面类型"}));
	    	
	    	/* 打开链接方式 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.target",getFieldValue("category.target"),null,new String[]{"打开链接方式"}));
	    	
	    	/* 每页显示条数-当页面类型为列表的时候需要验证 */
	        if(Constants.CATEGORY_PAGETYPE_LIST.equals(getFieldValue("category.categoryType"))){
	        	addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"category.perPage",getFieldValue("category.perPage"),null,new String[]{"每页显示条数"}));
		        addError(numberCheck.checkData(this,ValidatorTypeEnum.Length,"category.perPage",getFieldValue("category.perPage"),getParamsValue(new String[]{"numberRange","1","100"}),new String[]{"每页显示条数"}));
	        }
	   
	        return errors;
	  }
	

}
