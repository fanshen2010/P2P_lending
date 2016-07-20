package cn.com.p2p.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import cn.com.p2p.entity.Product;
//import cn.com.p2p.entity.ProductLabelSetting;
//import net.sf.json.JSONObject;

import org.springframework.web.util.WebUtils;

//import cn.com.p2p.entity.LabelSetting;
//import cn.com.p2p.framework.web.validator.ICommonDataCheck;
//import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

public class CommonServerValidateUtils {
//	private static final String SERVER_VALIDATE_STRING = "String";
//	private static final String SERVER_VALIDATE_NUMBER = "Number";
//	private static final String SERVER_VALIDATE_LENGTH = "Length";
//	private static final String SERVER_VALIDATE_CUSTOM = "Custom";
//	private static final String SERVER_VALIDATE_RANGE = "Range";
//	private static final String SERVER_VALIDATE_DATE = "Date";
//
//	public static List<String> getCondition(List<LabelSetting> labelsettings,
//			Map params, Object act) {
//		ICommonDataCheck requiredDataCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Required.getValidatorBeanName());
//		ICommonDataCheck numberCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Number.getValidatorBeanName());
//		ICommonDataCheck rangeCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Range.getValidatorBeanName());
//		ICommonDataCheck dateCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Date.getValidatorBeanName());
//		ICommonDataCheck stringCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.String.getValidatorBeanName());
//		ICommonDataCheck customCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Custom.getValidatorBeanName());
//		ICommonDataCheck legthCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Length.getValidatorBeanName());
//
//		List<String> l = new ArrayList<String>();
//		List<String> errMsg = new ArrayList<String>();
//		for (LabelSetting setting : labelsettings) {
//			String[] value = (String[]) params.get(setting.getLabelField());
//			if (value != null && value.length > 0) {
//				if (setting.getLabelRequired()) {
//					l.add(requiredDataCheck.checkData(act,
//							ValidatorTypeEnum.Required,
//							setting.getLabelField(), value[0], null,
//							new String[] { setting.getLabelName() }));
//				}
//
//				if (StringUtils.isNotEmpty(setting.getLabelServerValidate())) {
//					JSONObject jsonObject = JSONObject.fromObject(setting
//							.getLabelServerValidate());
//					// 遍历jsonObject
//					for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
//						String key = (String) iter.next();
//						String[] values = jsonObject.getString(key).split(",");
//						for (int i = 0; i < values.length; i++) {
//							String pm = values[i];
//							// 参数以"p:"开头
//							if (pm != null && pm.matches("^(?i)p:.+$")) {
//								String pm_fiedName = pm
//										.replaceAll("(?i)p:", "");
//								Object value1 = getFieldValue(pm_fiedName,
//										params);// Ognl.getValue(pm_fiedName,
//												// action);
//								values[i] = value1 == null ? "" : value1
//										.toString();
//								rewriteParam(pm_fiedName, value);
//							} else {
//								values[i] = pm;
//							}
//						}
//
//						if (key.equals(SERVER_VALIDATE_NUMBER)) {
//							l.add(numberCheck.checkData(act,
//									ValidatorTypeEnum.Number,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_STRING)) {
//							l.add(stringCheck.checkData(act,
//									ValidatorTypeEnum.String,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_DATE)) {
//							l.add(dateCheck.checkData(act,
//									ValidatorTypeEnum.Date,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_RANGE)) {
//							l.add(rangeCheck.checkData(act,
//									ValidatorTypeEnum.Range,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_LENGTH)) {
//
//							l.add(legthCheck.checkData(act,
//									ValidatorTypeEnum.Length,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_CUSTOM)) {
//							l.add(customCheck.checkData(act,
//									ValidatorTypeEnum.Custom,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						}
//
//					}
//
//				}
//			}
//
//		}
//		for (String str : l) {
//			if (!StringUtils.objectNull(str)) {
//				errMsg.add(str);
//			}
//		}
//		return errMsg;
//	}
//
//	public static List<String> getProductCondition(List<ProductLabelSetting> labelsettings,
//											Map params, Object act) {
//		ICommonDataCheck requiredDataCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Required.getValidatorBeanName());
//		ICommonDataCheck numberCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Number.getValidatorBeanName());
//		ICommonDataCheck rangeCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Range.getValidatorBeanName());
//		ICommonDataCheck dateCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Date.getValidatorBeanName());
//		ICommonDataCheck stringCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.String.getValidatorBeanName());
//		ICommonDataCheck customCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Custom.getValidatorBeanName());
//		ICommonDataCheck legthCheck = (ICommonDataCheck) SpringContextUtils
//				.getBean(ValidatorTypeEnum.Length.getValidatorBeanName());
//
//		List<String> l = new ArrayList<String>();
//		List<String> errMsg = new ArrayList<String>();
//		for (ProductLabelSetting setting : labelsettings) {
//			String[] value = (String[]) params.get(setting.getLabelField());
//			if (value != null && value.length > 0) {
//				if (setting.getLabelRequired()) {
//					l.add(requiredDataCheck.checkData(act,
//							ValidatorTypeEnum.Required,
//							setting.getLabelField(), value[0], null,
//							new String[] { setting.getLabelName() }));
//				}
//
//				if (StringUtils.isNotEmpty(setting.getLabelServerValidate())) {
//					JSONObject jsonObject = JSONObject.fromObject(setting
//							.getLabelServerValidate());
//					// 遍历jsonObject
//					for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
//						String key = (String) iter.next();
//						String[] values = jsonObject.getString(key).split(",");
//						for (int i = 0; i < values.length; i++) {
//							String pm = values[i];
//							// 参数以"p:"开头
//							if (pm != null && pm.matches("^(?i)p:.+$")) {
//								String pm_fiedName = pm
//										.replaceAll("(?i)p:", "");
//								Object value1 = getFieldValue(pm_fiedName,
//										params);// Ognl.getValue(pm_fiedName,
//								// action);
//								values[i] = value1 == null ? "" : value1
//										.toString();
//								rewriteParam(pm_fiedName, value);
//							} else {
//								values[i] = pm;
//							}
//						}
//
//						if (key.equals(SERVER_VALIDATE_NUMBER)) {
//							l.add(numberCheck.checkData(act,
//									ValidatorTypeEnum.Number,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_STRING)) {
//							l.add(stringCheck.checkData(act,
//									ValidatorTypeEnum.String,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_DATE)) {
//							l.add(dateCheck.checkData(act,
//									ValidatorTypeEnum.Date,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_RANGE)) {
//							l.add(rangeCheck.checkData(act,
//									ValidatorTypeEnum.Range,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_LENGTH)) {
//
//							l.add(legthCheck.checkData(act,
//									ValidatorTypeEnum.Length,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						} else if (key.equals(SERVER_VALIDATE_CUSTOM)) {
//							l.add(customCheck.checkData(act,
//									ValidatorTypeEnum.Custom,
//									setting.getLabelField(), value[0], values,
//									new String[] { setting.getLabelName() }));
//						}
//
//					}
//
//				}
//			}
//
//		}
//		for (String str : l) {
//			if (!StringUtils.objectNull(str)) {
//				errMsg.add(str);
//			}
//		}
//		return errMsg;
//	}
//
//	private static Object getFieldValue(String fieldName,
//			Map<String, Object> parameters) {
//		Object fieldValue = parameters.get(fieldName);
//		if (fieldValue != null && fieldValue.getClass().isArray()) {
//			String[] arr = (String[]) fieldValue;
//			if (arr != null && arr.length > 0) {
//				fieldValue = arr[0];
//			}
//		}
//		return fieldValue;
//	}
//
//	private static void rewriteParam(String fieldName, Object fieldValue) {
//		if (fieldName.indexOf("#") != -1) {
//			String tempAry[] = fieldName.split("#");
//
//			Map<String, Object> parmMap = (Map<String, Object>) Struts2Utils
//					.getRequest().getAttribute(tempAry[0]);
//
//			if (parmMap != null) {
//				return;
//			}
//			parmMap = new HashMap<String, Object>();
//
//			Struts2Utils.getRequest().setAttribute(tempAry[0], parmMap);
//			// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map
//			Map<String, Object> filterParamMap = WebUtils
//					.getParametersStartingWith(Struts2Utils.getRequest(),
//							tempAry[0] + "#filter_");
//			// 分析参数Map,构造PropertyFilter列表
//			if (!filterParamMap.isEmpty()) {
//				for (Entry<String, Object> entry : filterParamMap.entrySet()) {
//					String filterName = entry.getKey();
//					String value = ((String) entry.getValue()).trim()
//							.replaceAll("\"", "&quot;");
//					value = value.replaceAll("<", "&lt;");
//					value = value.replaceAll(">", "&gt;");
//
//					// 如果value值为空,则忽略此filter.
//					boolean omit = StringUtils.isEmpty(value);
//					if (!omit) {
//						// PropertyFilter filter = new
//						// PropertyFilter(filterName, value);
//						// filterList.add(filter);
//						// request.setAttribute("filter_" + filterName, value);
//						parmMap.put("filter_" + filterName, value);
//
//					}
//				}
//				// request.setAttribute(SEARCH_KEY, parmMap);
//				// request.setAttribute(filterPrefix, parmMap);
//			}
//			// if (parmMap == null) {
//			// parmMap = new HashMap<String, Object>();
//			//
//			// Struts2Utils.getRequest().setAttribute(tempAry[0],
//			// parmMap);
//			// }
//			// parmMap.put(tempAry[1], fieldValue);
//		}
//	}
}
