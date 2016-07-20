package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.ActionErrors;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

@Service("menuCheck")
public class MenuCheck extends BaseCheck  implements DataCheck {

	
	@Override
	public List<String> check(BaseAction action, Map<String, Object> parameters) {
		dataParameters=parameters;
		 errors= new ArrayList<String>();
		
		//菜单名称
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.menuName",getFieldValue("pfmMenu.menuName"),null,new String[]{"菜单名称"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmMenu.menuName",getFieldValue("pfmMenu.menuName"),getParamsValue(new String[]{"max","20"}),new String[]{"菜单名称"}));
		//排序
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.orderNum",getFieldValue("pfmMenu.orderNum"),null,new String[]{"排序"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"pfmMenu.orderNum",getFieldValue("pfmMenu.orderNum"),getParamsValue(new String[]{"integer"}),new String[]{"排序"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmMenu.orderNum",getFieldValue("pfmMenu.orderNum"),getParamsValue(new String[]{"max","3"}),new String[]{"排序"}));
		//菜单路径
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.menuUrl",getFieldValue("pfmMenu.menuUrl"),null,new String[]{"菜单路径"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmMenu.orderNum",getFieldValue("pfmMenu.orderNum"),getParamsValue(new String[]{"max","40"}),new String[]{"菜单路径"}));
		//菜单图标
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.menuIcon",getFieldValue("pfmMenu.menuIcon"),null,new String[]{"菜单图标"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"pfmMenu.menuIcon",getFieldValue("pfmMenu.menuIcon"),getParamsValue(new String[]{"max","20"}),new String[]{"菜单图标"}));
		//菜单状态
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.status",getFieldValue("pfmMenu.status"),null,new String[]{"菜单状态"}));
		//菜单打开方式
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"pfmMenu.menuTarget",getFieldValue("pfmMenu.menuTarget"),null,new String[]{"菜单打开方式"}));
		return errors;
	}
	
	
}
