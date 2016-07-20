package cn.com.p2p.framework.web.validator.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 字符串的校验 中文汉字：chinese 半角字符：onlybyte 邮编：chinaZip 只接受英文字母大小写：onlyLetterSp
 * 不接受特殊字符：onlyLetterNumber 只能填数字：onlyNumberSp 无效的 IP 地址：ipv4 无效的 URL：url
 * 手机号码：phone 银行卡号：bankCard 军官证号码：militaryId 身份证号码：identityCard
 * 只能输入由数字、26个英文字母或者下划线组成的字符串：allcharacter 国内电话号码：telephone 邮件：email organizationCert:组织机构代码证 businessIicense:营业执照号
 * 
 * @author 
 * 
 */
@Service("stringCheck")
public class StringCheck implements ICommonDataCheck {

	@Override
	public String checkData(Object dataAction, ValidatorTypeEnum checkType,
			String proName, Object proValue, Object[] params,
			String[] errMsgParam) {
		if (!StringUtils.objectNull(proValue)) {
			String errMsg = null;
			String otherMsg = null;
			String chkType = String.valueOf(params[0]);
			String match = "";
			if (StringUtils.compare(chkType, "chinese")) {
				match = "^[\u4E00-\u9FA5]+$";
				otherMsg = "只能为中文汉字";
			} else if (StringUtils.compare(chkType, "onlybyte")) {
				match = "^[\\x00-\\xff]*$";
				otherMsg = "不能输入全角字符";
			} else if (StringUtils.compare(chkType, "chinaZip")) {
				match = "^\\d{6}$";
				otherMsg = "无效的邮编号码";
			} else if (StringUtils.compare(chkType, "onlyLetterSp")) {
				match = "^[a-zA-Z\\ \\']+$";
				otherMsg = "只能为英文字母";
			} else if (StringUtils.compare(chkType, "onlyLetterNumber")) {
				match = "^[0-9a-zA-Z]+$";
				otherMsg = "自能为数字与英文";
			} else if (StringUtils.compare(chkType, "onlyNumberSp")) {
				match = "^\\d+$";
				otherMsg = "只能输入数字";
			} else if (StringUtils.compare(chkType, "ipv4")) {
				match = "^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$";
				otherMsg = "无效的IP地址";
			} else if (StringUtils.compare(chkType, "url")) {
				match = "^(https?|ftp)://(((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+(\\/(([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)|\\/|\\?)*)?$";
				otherMsg = "无效的url";
			} else if (StringUtils.compare(chkType, "phone")) {
				match = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0-9])\\d{8}$";
				otherMsg = "无效的手机号码";
			} else if (StringUtils.compare(chkType, "bankCard")) {
				match = "^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$";
				otherMsg = "无效的银行卡号";
			} else if (StringUtils.compare(chkType, "militaryId")) {
				match = "^(南|北|沈|兰|成|济|广|参|证|后|装|海|空)字第(\\d{6}|\\d{8})号$";
				otherMsg = "无效的军官证号码";
			} else if (StringUtils.compare(chkType, "allcharacter")) {
				match = "^\\w+$";
				otherMsg = "只能输入由数字、26个英文字母或者下划线组成的字符串";
			} else if (StringUtils.compare(chkType, "identityCard")) {
				try {
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
							Locale.CHINA);

					Map<String, Object> aCity = new HashMap<String, Object>();
					aCity.put("11", "北京");
					aCity.put("34", "安徽");
					aCity.put("52", "贵州");
					aCity.put("12", "天津");
					aCity.put("35", "福建");
					aCity.put("53", "云南");
					aCity.put("13", "河北");
					aCity.put("14", "山西");
					aCity.put("15", "内蒙古");
					aCity.put("21", "辽宁");
					aCity.put("22", "吉林");
					aCity.put("23", "黑龙江");
					aCity.put("31", "上海");
					aCity.put("32", "江苏");
					aCity.put("33", "浙江");
					aCity.put("36", "江西");
					aCity.put("37", "山东");
					aCity.put("41", "河南");
					aCity.put("42", "湖北");
					aCity.put("43", "湖南");
					aCity.put("44", "广东");
					aCity.put("45", "广西");
					aCity.put("46", "海南");
					aCity.put("50", "重庆");
					aCity.put("51", "四川");
					aCity.put("54", "西藏");
					aCity.put("61", "陕西");
					aCity.put("62", "甘肃");
					aCity.put("63", "青海");
					aCity.put("64", "宁夏");
					aCity.put("65", "新疆");
					aCity.put("71", "台湾");
					aCity.put("81", "香港");
					aCity.put("82", "澳门");
					aCity.put("91", "国外");
					String strID = String.valueOf(proValue);
					String errmsg = "无效的身份证号码";
					int iSum = 0;
					match = "^\\d{17}(\\d|X|x)$";
					if (!strID.matches(match)) {
						errMsg = proName
								+ ":"
								+ String.format(checkType.getErrorMessage(),
										errMsgParam) + "无效的身份证号码";
					}
					strID = strID.toUpperCase().replace("X", "a");
					if (aCity.get(strID.substring(0, 2)) == null) {
						errMsg = proName
								+ ":"
								+ String.format(checkType.getErrorMessage(),
										errMsgParam) + "无效的身份证号码";
					}
					String sBirthday = strID.substring(6, 10) + "-"
							+ strID.substring(10, 12) + "-"
							+ strID.substring(12, 14);

					Date date = sdf.parse(sBirthday);
					Calendar canlendar = Calendar.getInstance();
					canlendar.setTime(date);
					date = canlendar.getTime();
					String newdate = sdf.format(date);
					if (!StringUtils.compare(sBirthday, newdate)) {
						errMsg = proName
								+ ":"
								+ String.format(checkType.getErrorMessage(),
										errMsgParam) + "无效的身份证号码";
					}
					for (int i = 17; i >= 0; i--) {
						iSum += (Math.pow(2, i) % 11)
								* Integer.parseInt(
										String.valueOf(strID.charAt(17 - i)),
										11);
					}
					if (iSum % 11 != 1) {
						errMsg = proName
								+ ":"
								+ String.format(checkType.getErrorMessage(),
										errMsgParam) + "无效的身份证号码";
					}
					// return
					// aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
				} catch (Exception e) {
					// e.printStackTrace();
					errMsg = "无效的身份证号码";
				}
			} else if (StringUtils.compare(chkType, "telephone")) {
				match = "\\d{3}-\\d{8}|\\d{4}-\\d{7,8}";
				otherMsg = "无效的固定话号码";
			} else if (StringUtils.compare(chkType, "phoneOrTel")) {
				match = "^((13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0-9])\\d{8})|(\\d{3}-\\d{8}|\\d{4}-\\d{7,8})$";
				otherMsg = "无效的手机号码或固定话号码";
			} else if (StringUtils.compare(chkType, "email")) {
				match = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
				otherMsg = "邮件地址无效";
			} else if (StringUtils.compare(chkType, "imagePattern")) {
				match = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
				otherMsg = "图片的格式必须是jpg|png|gif|bmp。";
			}else if (StringUtils.compare(chkType, "organizationCert")){
				match = "^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{1}$";
				otherMsg = "无效的组织机构代码证";
			}else if (StringUtils.compare(chkType, "businessIicense")){
				match = "\\d{15}";
				otherMsg = "无效的营业执照号";
			}
			if (StringUtils.isNotEmpty(errMsg)) {
				return errMsg;
			}
			if (!String.valueOf(proValue).matches(match)) {
				errMsg = proName
						+ ":"
						+ String.format(checkType.getErrorMessage(),
								errMsgParam) + otherMsg;
				return errMsg;
			}
			return null;
		}
		return null;
	}

}
