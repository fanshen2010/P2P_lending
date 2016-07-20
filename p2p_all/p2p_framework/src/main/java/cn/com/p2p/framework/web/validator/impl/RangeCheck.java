package cn.com.p2p.framework.web.validator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 范围校验 日期范围：dateRange 时间日期范围：dateTimeRange 最小：min 最大：max 早于：past 晚于：future 相等
 * ：equals
 * 
 * @author 
 * 
 */

@Service("rangeCheck")
public class RangeCheck implements ICommonDataCheck {

	private static final String STR_DATERANGE = "dateRange";
	private static final String STR_DATETIMERANGE = "dateTimeRange";
	private static final String STR_MIN = "min";
	private static final String STR_MAX = "max";
	private static final String STR_RANGE = "range";
	private static final String STR_PAST = "past";
	private static final String STR_FUTURE = "future";
	private static final String STR_EQUALS = "equals";

	@Override
	public String checkData(Object dataAction, ValidatorTypeEnum checkType,
			String proName, Object proValue, Object[] params,
			String[] errMsgParam) {
		String errMsg = null;
		if (!StringUtils.objectNull(proValue)) {
			String dateMatch = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\\/|-)(?:29|30)))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\\/|-)(?:0?[1-9]|1\\d|2[0-8]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(0?2(\\/|-)29)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
			String timeMatch = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])\\s+(0[0-9]|1[0-9]|2[0-3]){1}:(0?[1-5]|[0-5][0-9]){1}:(0?[0-6]|[0-5][0-9]){1}$|^(?:(?:(?:0?[13578]|1[02])(\\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\\/|-)(?:29|30)))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^((1[012]|0?[1-9]){1}\\/(0?[1-9]|[12][0-9]|3[01]){1}\\/\\d{2,4}\\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1})$";
			String intMatch = "^(-?\\d+)(\\.\\d+)?$";// 整数

			// String intMatch =
			// "^(?!0+$)(?!0*\\.0*$)\\d{1,8}(\\.\\d{1,2})?+$";// 整数
			try {
				String checkFlag = String.valueOf(params[0]);
				if (StringUtils.compare(checkFlag, STR_DATERANGE)) {
					if (String.valueOf(params[1]).matches(dateMatch)
							&& String.valueOf(params[2]).matches(dateMatch)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd", Locale.CHINA);
						if (!StringUtils.objectNull(String.valueOf(params[1]))
								&& !StringUtils.objectNull(String
										.valueOf(params[2]))) {
							Date date1 = sdf.parse(String.valueOf(params[1]));
							Date date2 = sdf.parse(String.valueOf(params[2]));
							if (date1.after(date2)) {
								errMsg = proName
										+ ":"
										+ String.format(
												checkType.getErrorMessage(),
												errMsgParam);
							}
						}
					}
				} else if (StringUtils.compare(checkFlag, STR_DATETIMERANGE)) {
					if (String.valueOf(params[1]).matches(timeMatch)
							&& String.valueOf(params[2]).matches(timeMatch)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
						Date date1 = sdf.parse(String.valueOf(params[1]));
						Date date2 = sdf.parse(String.valueOf(params[2]));
						if (date1.after(date2)) {
							errMsg = proName
									+ ":"
									+ String.format(
											checkType.getErrorMessage(),
											errMsgParam);
						}
					}
				} else if (StringUtils.compare(checkFlag, STR_MIN)) {
					if (String.valueOf(proValue).matches(intMatch)
							&& String.valueOf(params[1]).matches(intMatch)) {
						double min = Double.valueOf(String.valueOf(params[1]));
						if (Double.valueOf(String.valueOf(proValue)) <= min) {
							errMsg = proName + ":" + errMsgParam[0] + ":必须大于"
									+ params[1];
						}
					}
				} else if (StringUtils.compare(checkFlag, STR_MAX)) {

					if (String.valueOf(proValue).matches(intMatch)
							&& String.valueOf(params[1]).matches(intMatch)) {
						double max = Double.valueOf(String.valueOf(params[1]));
						if (Double.valueOf(String.valueOf(proValue)) >= max) {
							errMsg = proName + ":" + errMsgParam[0] + ":必须小于"
									+ params[1];
						}
					}
				} else if (StringUtils.compare(checkFlag, STR_RANGE)) {
					
					if (String.valueOf(proValue).matches(intMatch)
							&& String.valueOf(params[1]).matches(intMatch)&& String.valueOf(params[2]).matches(intMatch)) {
						double min = Double.valueOf(String.valueOf(params[1]));
						double max = Double.valueOf(String.valueOf(params[2]));
						if (Double.valueOf(String.valueOf(proValue)) > max || Double.valueOf(String.valueOf(proValue)) < min) {
							errMsg = proName + ":" + errMsgParam[0] + ":必须在"
									+ params[1] +"与"+ params[2]+"之间";
						}
					}
				} else if (StringUtils.compare(checkFlag, STR_PAST)) {
					String str = "";
					if( params[1] instanceof Date ){
						str = String.valueOf(DateUtils.formatDate(
								 (Date) params[1], "yyyy-MM-dd HH:mm:ss"));
					}
					if(proValue instanceof Date){
						proValue = String.valueOf(DateUtils.formatDate(
								 (Date) proValue, "yyyy-MM-dd HH:mm:ss"));
					}
					
					if ((str.matches(dateMatch)||String.valueOf(str).matches(timeMatch))
							&& (String.valueOf(proValue).matches(dateMatch)||String.valueOf(proValue).matches(timeMatch))) {

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
						
						if (StringUtils.compare(str, "today")) {
							str = String.valueOf(DateUtils.formatDate(
									new Date(), "yyyy-MM-dd"));
						} else if (StringUtils.compare(str, "now")) {
							str = String.valueOf(DateUtils.formatDate(
									new Date(), "yyyy-MM-dd HH:mm:ss"));
						}
						Date date1 = sdf.parse(str);
						Date date2 = sdf.parse(String.valueOf(proValue));
						if (!date1.after(date2)) {
							errMsg = proName + ":" + errMsgParam[0] + ":必须早于"
									+ errMsgParam[1];
						}

					}
				} else if (StringUtils.compare(checkFlag, STR_FUTURE)) {
					String str = String.valueOf(params[1]);
					if (StringUtils.compare(str, "today")) {
						str = String.valueOf(DateUtils.formatDate(new Date(),
								"yyyy-MM-dd"));
					} else if (StringUtils.compare(str, "now")) {
						str = String.valueOf(DateUtils.formatDate(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
					}

					if (StringUtils.compare(String.valueOf(params[1]), "now")) {
						String checkDate = "";
						if (proValue instanceof Date) {
							checkDate = String.valueOf(DateUtils.formatDate(
									(Date) proValue, "yyyy-MM-dd HH:mm:ss"));
						} else {
							checkDate = String.valueOf(proValue);
						}

						if (str.matches(timeMatch)
								&& checkDate.matches(timeMatch)) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
							Date date1 = sdf.parse(str);
							Date date2 = sdf.parse(checkDate);
							if (!date1.before(date2)) {
								errMsg = proName + ":" + errMsgParam[0]
										+ ":必须晚于" + errMsgParam[1];
							}
						}
					} else {
						String checkDate = "";
						if (proValue instanceof Date) {
							checkDate = String.valueOf(DateUtils.formatDate(
									(Date) proValue, "yyyy-MM-dd"));
						} else {
							checkDate = String.valueOf(proValue);
						}
						if (str.matches(dateMatch)
								&& checkDate.matches(dateMatch)) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd", Locale.CHINA);
							Date date1 = sdf.parse(str);
							Date date2 = sdf.parse(checkDate);
							if (!date1.before(date2)) {
								errMsg = proName + ":" + errMsgParam[0]
										+ ":必须晚于" + errMsgParam[1];
							}
						}
					}

				} else if (StringUtils.compare(checkFlag, STR_EQUALS)) {
					String target = String.valueOf(params[1]);
					String source = String.valueOf(proValue);
					if (!StringUtils.compare(target, source)) {
						errMsg = proName + ":" + errMsgParam[0] + ":"
								+ errMsgParam[1] + "必须等于" + errMsgParam[2];
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return errMsg;
	}
}
