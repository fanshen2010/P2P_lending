package cn.com.p2p.framework.web.validator.impl;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 后端数字格式校验
 * 
 * @author Administrator
 *
 */
@Service("numberCheck")
public class NumberCheck implements ICommonDataCheck {

	@Override
	public String checkData(Object dataAction, ValidatorTypeEnum checkType,
			String proName, Object proValue, Object[] params,
			String[] errMsgParam) {
		if (!StringUtils.objectNull(proValue)) {
			String chkType = String.valueOf(params[0]);
			if (StringUtils.compare(chkType, "integer")) {
				String intMatch = "^[0-9]\\d*$";// 整数
				if (!String.valueOf(proValue).matches(intMatch)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "invalid integer";
					return errMsg;
				}
			} else if (StringUtils.compare(chkType, "integer3")) {
				String numberMatch = "^[1-9]\\d{0,2}$";// 3位正数字
				if (!String.valueOf(proValue).matches(numberMatch)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "invalid number";
					return errMsg;
				}
			} else if (StringUtils.compare(chkType, "integer30")) {
				String numberMatch = "^[0-9]{19}$";// 3位正数字
				if (!String.valueOf(proValue).matches(numberMatch)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "invalid number";
					return errMsg;
				}
			} else if (StringUtils.compare(chkType, "number")) {
				String numberMatch = "^[\\-\\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?((\\.\\d{1,2}){0,1})$";// 数字
				if (!String.valueOf(proValue).matches(numberMatch)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "invalid number";
					return errMsg;
				}
			} else if (StringUtils.compare(chkType, "rateFormat")) {
				String rateMatch = "^\\d{1,2}((\\.\\d{1,2}){0,1})$";// 百分比
				if (!String.valueOf(proValue).matches(rateMatch)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "Please fill in 0~100 number";
					return errMsg;
				}
			}else if(StringUtils.compare(chkType,"numberRange")){ 
				String range = "^(0|([1-9]\\d*))$";
				int min = Integer.parseInt(String.valueOf(params[1]));
				int max = Integer.parseInt(String.valueOf(params[2]));
				if (!String.valueOf(proValue).matches(range) || Integer.parseInt(String.valueOf(proValue)) > max || Integer.parseInt(String.valueOf(proValue)) < min) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam) + "Please fill in " + min + "~" + max + " integer";
					return errMsg;
				}
			} 
			return null;
		}
		return null;
	}

}
