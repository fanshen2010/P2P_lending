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

@Service("menuOrderNumsCheck")
public class MenuOrderNumsCheck extends BaseCheck  implements DataCheck {

	
	@Override
	public List<String>  check(BaseAction action, Map<String, Object> parameters) {
		dataParameters=parameters;
		 errors= new ArrayList<String>();
		//这个地方还需要测试
		Integer orderNums = Integer.valueOf((String) getFieldValue("menuCount"));
		
		for(int i=0;i<orderNums;i++){
			//排序
			addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"orderNums["+i+"].orderNum",getFieldValue("orderNums["+i+"].orderNum"),null,new String[]{"排序"}));
			addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"orderNums["+i+"].orderNum",getFieldValue("orderNums["+i+"].orderNum"),getParamsValue(new String[]{"integer"}),new String[]{"排序"}));
			addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"orderNums["+i+"].orderNum",getFieldValue("orderNums["+i+"].orderNum"),getParamsValue(new String[]{"max","3"}),new String[]{"排序"}));
		}
		return errors;
	}

	
}
