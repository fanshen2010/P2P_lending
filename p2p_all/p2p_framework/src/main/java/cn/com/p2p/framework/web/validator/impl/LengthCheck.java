package cn.com.p2p.framework.web.validator.impl;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 后端长度校验
 * 
 * @author 
 *
 */
@Service("lengthCheck")
public class LengthCheck implements ICommonDataCheck {

	private static String Str_MAX = "max";

	private static String Str_MIN = "min";

	private static String Str_RANGE = "range";


	@Override
	public String checkData(Object dataAction,ValidatorTypeEnum checkType,String proName,Object proValue,Object[] params,
			String[] errMsgParam)
	{

		if(!StringUtils.objectNull(proValue)){
			try{
				// int len =
				// StringUtils.getWordCount(String.valueOf(proValue));//字节数
				int len = String.valueOf(proValue).length();// 字节数

				String type = String.valueOf(params[0]);
				if(StringUtils.compare(type,Str_MAX)){
					int maxLen = Integer.parseInt(String.valueOf(params[1]));
					if(len > maxLen){
						String errMsg = proName + ":" + String.format(checkType.getErrorMessage(),errMsgParam) + "长度为" + maxLen + "位";
						return errMsg;
					}
				} else if(StringUtils.compare(type,Str_MIN)){
					int minLen = Integer.parseInt(String.valueOf(params[1]));
					if(len < minLen){
						String errMsg = proName + ":" + String.format(checkType.getErrorMessage(),errMsgParam) + "长度为" + minLen + "位";
						return errMsg;
					}
				} else if(StringUtils.compare(type,Str_RANGE)){
					int minLen = Integer.parseInt(String.valueOf(params[1]));
					int maxLen = Integer.parseInt(String.valueOf(params[2]));
					if(len < minLen || len > maxLen){
						String errMsg = proName + ":" + String.format(checkType.getErrorMessage(),errMsgParam) + "最小为" + minLen + "位，最长为"
								+ maxLen + "位";
						return errMsg;
					}
				} else{
					String errMsg = proName + ":非法的校验格式";
					return errMsg;
				}
			} catch(Exception e){

			}
			return null;
		}
		return null;
	}

}
