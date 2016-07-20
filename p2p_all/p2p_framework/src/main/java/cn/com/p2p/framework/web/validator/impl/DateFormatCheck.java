package cn.com.p2p.framework.web.validator.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 后端日期格式校验
 * 
 * @author 
 *
 */
@Service("dateCheck")
public class DateFormatCheck implements ICommonDataCheck {

	private static final String STR_DATEFORMAT = "dateFormat";
	private static final String STR_DATETIMEFORMAT = "dateTimeFormat";
	private static final String STR_DATETIMEFORMAT24 = "dateTimeFormat24";

	@Override
	public String checkData(Object dataAction, ValidatorTypeEnum checkType,
			String proName, Object proValue, Object[] params,
			String[] errMsgParam) {
		if (!StringUtils.objectNull(proValue)) {
			try {
				String match = "";
				String checkFlag = String.valueOf(params[0]);
				String checkDate = "";
				if (StringUtils.compare(STR_DATEFORMAT, checkFlag)) {// 日期
																		// 2014-07-18
					match = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\\/|-)(?:29|30)))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\\/|-)(?:0?[1-9]|1\\d|2[0-8]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(0?2(\\/|-)29)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
					if (proValue instanceof Date) {
						checkDate = String.valueOf(DateUtils.formatDate(
								(Date) proValue, "yyyy-MM-dd"));
					} else {
						checkDate = String.valueOf(proValue);
					}
				} else if (StringUtils.compare(STR_DATETIMEFORMAT, checkFlag)) {// 时间日期：
					match = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])\\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\\/|-)(?:29|30)))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^((1[012]|0?[1-9]){1}\\/(0?[1-9]|[12][0-9]|3[01]){1}\\/\\d{2,4}\\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\\s+(am|pm|AM|PM){1})$";
					if (proValue instanceof Date) {
						checkDate = String.valueOf(DateUtils.formatDate(
								(Date) proValue, "yyyy-MM-dd hh:mm:ss"));
					} else {
						checkDate = String.valueOf(proValue);
					}
				} else if (StringUtils.compare(STR_DATETIMEFORMAT24, checkFlag)) { // 时间日期24小时制
					match = "^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])\\s+(0[0-9]|1[0-9]|2[0-3]){1}:(0?[1-5]|[0-5][0-9]){1}:(0?[0-6]|[0-5][0-9]){1}$|^(?:(?:(?:0?[13578]|1[02])(\\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\\/|-)(?:29|30)))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^((1[012]|0?[1-9]){1}\\/(0?[1-9]|[12][0-9]|3[01]){1}\\/\\d{2,4}\\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1})$";
					if (proValue instanceof Date) {
						checkDate = String.valueOf(DateUtils.formatDate(
								(Date) proValue, "yyyy-MM-dd HH:mm:ss"));
					} else {
						checkDate = String.valueOf(proValue);
					}
				}

				if (!String.valueOf(checkDate).matches(match)) {
					String errMsg = proName
							+ ":"
							+ String.format(checkType.getErrorMessage(),
									errMsgParam);
					return errMsg;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}

}
