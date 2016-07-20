package cn.com.p2p.framework.web.validator.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/***
 * 数据必须输入项校验
 * @author kezhida
 *
 */
@Service("requiredDataCheck")
public class RequiredDataCheckImpl implements ICommonDataCheck {

	public String checkData(Object data,ValidatorTypeEnum checkType, String proName, Object proValue,Object[] params,String[] errMsgParam) {
		
		if(proValue==null || StringUtils.isBlank(String.valueOf(proValue))){
			String errMsg =  proName+":"+String.format(checkType.getErrorMessage(), errMsgParam);
			return errMsg;
		}
		return null;
	}

}
