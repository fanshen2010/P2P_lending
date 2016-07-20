package cn.com.p2p.validator;


import java.util.List;
import java.util.Map;

import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.ActionErrors;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

public class BaseCheck implements DataCheck {
	
	protected  Map<String, Object> dataParameters;
	
	List<String> errors ;
	ICommonDataCheck requiredDataCheck = (ICommonDataCheck) SpringContextUtils.getBean(ValidatorTypeEnum.TextRequired.getValidatorBeanName());
	ICommonDataCheck selectRequiredDataCheck = (ICommonDataCheck) SpringContextUtils.getBean(ValidatorTypeEnum.SelectRequired.getValidatorBeanName());
	ICommonDataCheck numberCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.Number.getValidatorBeanName());
	ICommonDataCheck rangeCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.Range.getValidatorBeanName());
	ICommonDataCheck dateCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.Date.getValidatorBeanName());
	ICommonDataCheck stringCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.String.getValidatorBeanName());
	ICommonDataCheck customCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.Custom.getValidatorBeanName());
	ICommonDataCheck legthCheck = (ICommonDataCheck) SpringContextUtils
			.getBean(ValidatorTypeEnum.Length.getValidatorBeanName());

	//@Override TODOBUG
	public List<String> check(BaseAction action, Map<String, Object> parameters)  {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void addError(String errMsg) {
		if(!StringUtils.objectNull(errMsg)){
			errors.add(errMsg);
		}
	}
	
	protected Object getFieldValue(String fieldName) {
		Object fieldValue = dataParameters.get(fieldName);
		if (fieldValue != null && fieldValue.getClass().isArray()) {
			String[] arr = (String[]) fieldValue;
			if (arr != null && arr.length > 0) {
				fieldValue = arr[0];
			}
		}
		return fieldValue;
	}

	protected  Object[] getParamsValue(String[] params){
		Object[] paramsValue = null;
		if (params != null) {
			paramsValue = new Object[params.length];

			for (int i = 0; i < params.length; i++) {
				String pm = params[i];
				// 参数以"p:"开头
				if (pm != null && pm.matches("^(?i)p:.+$")) {
					String pm_fiedName = pm.replaceAll("(?i)p:", "");
					Object value = getFieldValue(pm_fiedName);// Ognl.getValue(pm_fiedName,
										// action);
					paramsValue[i] = value;
//					rewriteParam(pm_fiedName,value);
				} else {
					paramsValue[i] = pm;
				}
			}
		}
		return paramsValue;

	}


}
