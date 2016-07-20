package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;


/**
 * <p>业务人员管理添加校验</p>
 * @author 
 * @description 验证要求<br>
 *              1.用户名：必填、20个字符以内<br>
 *              2.密码： 必填、6~18个字符以内<br>
 *              3.姓名：必填、20个字符以内<br>
 *              4.联系电话：必填、座机或者手机<br>
 *              5.邮箱：邮箱验证<br>
 *              6.状态：必填<br>
 */
@Service("businessStaffAddCheck")
public class BusinessStaffAddCheck extends BaseCheck  implements DataCheck {
	
	  public List<String> check(BaseAction action, Map<String, Object> parameters){
	        dataParameters=parameters;
	        errors= new ArrayList<String>();
	        
	        /* 用户名 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.userName",getFieldValue("pfmUser.userName"),null,new String[]{"用户名"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmUser.userName",getFieldValue("pfmUser.userName"),getParamsValue(new String[]{"max","20"}),new String[]{"用户名"}));
	        
	        /* 密码 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.password",getFieldValue("pfmUser.password"),null,new String[]{"密码"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmUser.password",getFieldValue("pfmUser.password"),getParamsValue(new String[]{"range","6","18"}),new String[]{"密码"}));
	        
	        /* 姓名 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.realName",getFieldValue("pfmUser.realName"),null,new String[]{"姓名"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmUser.realName",getFieldValue("pfmUser.realName"),getParamsValue(new String[]{"max","10"}),new String[]{"姓名"}));
	        
	        /* 联系电话 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.contactPhone",getFieldValue("pfmUser.contactPhone"),null,new String[]{"联系电话"}));
	        addError(stringCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.contactPhone",getFieldValue("pfmUser.contactPhone"),getParamsValue(new String[]{"phoneOrTel"}),new String[]{"联系电话"}));
	        
	        /* 邮箱 */
	        addError(stringCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.emailAddress",getFieldValue("pfmUser.emailAddress"),getParamsValue(new String[]{"email"}),new String[]{"邮箱"}));
	        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmUser.emailAddress",getFieldValue("pfmUser.emailAddress"),getParamsValue(new String[]{"max","64"}),new String[]{"邮箱"}));
	        
	        /* 状态 */
	        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmUser.active",getFieldValue("pfmUser.active"),null,new String[]{"状态"}));
	        
	        return errors;
	        
	  }

}
