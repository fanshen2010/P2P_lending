package cn.com.p2p.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 资源文件文本内容枚举
 * @author zhirong
 *
 */
public class TextEnumUtils {

	/**
	 * 获得枚举值
	 * 例如：资源文件中abc.x=1:是,0:否<br>
	 * 的形式那么textKey为：abc.x，enumKey为：1、0，返回值为：是或否
	 * @param textKey
	 * @param enumKey
	 * @return
	 */
	public static String getEnumValue(String textKey, String enumKey) {
		if (StringUtils.isEmpty(textKey) || StringUtils.isEmpty(enumKey)) {
			return "";
		}
		String msg = LangUtils.getMessage(textKey);
		String[] enumArray = msg.split(",");
		for (String enumStr : enumArray) {
			String[] strArray = enumStr.split(":");
			if (strArray.length == 2) {
				if (enumKey.equals(strArray[0])) {
					return strArray[1];
				}
			}
		}
		return "";
	}
	
	public static Map<String, String> getEnumMap(String textKey) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(textKey)) {
			return map;
		}
		String msg = LangUtils.getMessage(textKey);
		String[] enumArray = msg.split(",");
		for (String enumStr : enumArray) {
			String[] strArray = enumStr.split(":");
			if (strArray.length == 2) {
				map.put(strArray[0], strArray[1]);
			}
		}
		return map;
	}

}
