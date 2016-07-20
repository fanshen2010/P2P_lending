package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;
import cn.com.p2p.utils.Constants;

/**
 * <p>栏目内容管理校验</p>
 * @author 
 * @description 验证要求<br>
 *              1.标题：必填、32个字符以内<br>
 *              2.SEO关键字： 32个字符以内<br>
 *              3.SEO描述：64个字符以内<br>
 *              4.发布时间：时间<br>
 *              5.排序：小于1000正整数<br>
 */
@Service("articleCheck")
public class ArticleCheck extends BaseCheck  implements DataCheck {

	  public List<String> check(BaseAction action, Map<String, Object> parameters){
	        dataParameters=parameters;
	        errors= new ArrayList<String>();
	        
	    	/* 标题 */
	    	addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"article.title",getFieldValue("article.title"),null,new String[]{"标题"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"article.title",getFieldValue("article.title"),getParamsValue(new String[]{"max","32"}),new String[]{"标题"}));
	    	
	    	/* SEO关键字 */
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"article.seoKeywords",getFieldValue("article.seoKeywords"),getParamsValue(new String[]{"max","32"}),new String[]{"SEO关键字"}));
	    	
	    	/* SEO描述 */
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"article.seoDescription",getFieldValue("article.seoDescription"),getParamsValue(new String[]{"max","64"}),new String[]{"SEO描述"}));
	    	
	    	/* 发布时间 */
	        addError(dateCheck.checkData(this,ValidatorTypeEnum.Length,"article.postAt",getFieldValue("article.postAt"),getParamsValue(new String[]{"dateTimeFormat24"}),new String[]{"发布时间"}));
	        
	    	/* 排序-当排序不为空的时候判断（当栏目类型为单页时，排序可以为空，不需要校验） */
	        if(!StringUtils.objectNull(getFieldValue("article.orderNum"))){
		        addError(numberCheck.checkData(this,ValidatorTypeEnum.Length,"article.orderNum",getFieldValue("article.orderNum"),getParamsValue(new String[]{"numberRange","1","999"}),new String[]{"每页显示条数"}));
	        }
	   
	        return errors;
	  }
	

}
