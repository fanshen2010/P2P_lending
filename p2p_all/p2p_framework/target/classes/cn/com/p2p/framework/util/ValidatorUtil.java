package cn.com.p2p.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ognl.OgnlException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import cn.com.p2p.framework.context.annotation.Check;
import cn.com.p2p.framework.context.annotation.Config;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/***
 * 服务器端静态放校验工具类
 * 
 * @author kezhida
 *
 */
public class ValidatorUtil {
	protected static Logger logger = LoggerFactory
			.getLogger(ValidatorUtil.class);

	/**
	 * 根据注解设置，校验Action中的数据是否合法
	 * 
	 * @param action
	 *            Action对象
	 * @param validators
	 *            校验注解
	 * @return 校验的错误信息，null是符合校验规则。
	 * @throws OgnlException
	 */
	public static List<String> checkActionData(BaseAction action,
			Validators validators, Map<String, Object> parameters)
			throws OgnlException {
//		if (validators == null || validators.config() == null
//				|| validators.config().length == 0) {
//			return null;
//		}
		List<String> msgList = new ArrayList<String>();
//		for (Config config : validators.config()) {
//			String fieldName = config.field();
//
//			Object fieldValue = getFieldValue(fieldName, parameters);// Ognl.getValue(fieldName,
//			// action);
//			rewriteParam(fieldName,fieldValue);
//			// 数据校验
//			for (Check check : config.check()) {
//				ValidatorTypeEnum type = check.type();
//				String[] params = check.params();
//				String[] errMsgParmas = check.errMsgParam();
//				String beanName = type.getValidatorBeanName();
//				logger.info("校验对象接口名:" + beanName + ",校验类型:"
//						+ type.getCheckName());
//				if (StringUtils.isBlank(beanName)) {
//					logger.info("校验对象接口名不能为空，ValidatorTypeEnum枚举定义不正确！检验类型："
//							+ type.getCheckName());
//					continue;
//				}
//				// 获取校验对象
//				ICommonDataCheck dataCheck = (ICommonDataCheck) SpringContextUtils
//						.getBean(beanName);
//				if (dataCheck == null) {
//					logger.info("无效的检验接口对象:" + beanName);
//					continue;
//				}
//				Object[] paramsValue = null;
//				if (params != null) {
//					paramsValue = new Object[params.length];
//
//					for (int i = 0; i < params.length; i++) {
//						String pm = params[i];
//						// 参数以"p:"开头
//						if (pm != null && pm.matches("^(?i)p:.+$")) {
//							String pm_fiedName = pm.replaceAll("(?i)p:", "");
//							Object value = getFieldValue(pm_fiedName,
//									parameters);// Ognl.getValue(pm_fiedName,
//												// action);
//							paramsValue[i] = value;
//							rewriteParam(pm_fiedName,value);
//						} else {
//							paramsValue[i] = pm;
//						}
//					}
//				}
//				String msg = dataCheck.checkData(action, type, fieldName,
//						fieldValue, paramsValue, errMsgParmas);
//				if (StringUtils.isNotEmpty(msg)) {
//					
//					msgList.add(msg);
//					return msgList;
//				}
//			}
//		}
		return msgList;
	}

	private static Object getFieldValue(String fieldName,
			Map<String, Object> parameters) {
		Object fieldValue = parameters.get(fieldName);
		if (fieldValue != null && fieldValue.getClass().isArray()) {
			String[] arr = (String[]) fieldValue;
			if (arr != null && arr.length > 0) {
				fieldValue = arr[0];
			}
		}
		return fieldValue;
	}
	private static void rewriteParam(String fieldName,Object fieldValue)
	{
		if (fieldName.indexOf("#") != -1) {
			String tempAry[] = fieldName.split("#");

			Map<String, Object> parmMap = (Map<String, Object>) Struts2Utils
					.getRequest().getAttribute(tempAry[0]);
			
			if(parmMap!=null)
			{
				return;
			}
			parmMap = new HashMap<String, Object>();

			Struts2Utils.getRequest().setAttribute(tempAry[0], parmMap);
			// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map
			Map<String, Object> filterParamMap = WebUtils.getParametersStartingWith(Struts2Utils
					.getRequest(), tempAry[0] + "#filter_");
			// 分析参数Map,构造PropertyFilter列表
			if (!filterParamMap.isEmpty()) {
				for (Entry<String, Object> entry : filterParamMap.entrySet()) {
					String filterName = entry.getKey();
					String value = ((String) entry.getValue()).trim().replaceAll("\"", "&quot;");
					value = value.replaceAll("<", "&lt;");
					value = value.replaceAll(">", "&gt;");
					
					// 如果value值为空,则忽略此filter.
					boolean omit = StringUtils.isBlank(value);
					if (!omit) {
						//PropertyFilter filter = new PropertyFilter(filterName, value);
						//filterList.add(filter);
						//request.setAttribute("filter_" + filterName, value);
						parmMap.put("filter_" + filterName, value);

					}
				}
				//request.setAttribute(SEARCH_KEY, parmMap);
				//request.setAttribute(filterPrefix, parmMap);
			}
//			if (parmMap == null) {
//				parmMap = new HashMap<String, Object>();
//
//				Struts2Utils.getRequest().setAttribute(tempAry[0],
//						parmMap);
//			}
//			parmMap.put(tempAry[1], fieldValue);
		}
	}
}
