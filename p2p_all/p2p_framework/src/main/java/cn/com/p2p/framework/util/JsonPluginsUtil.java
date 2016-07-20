package cn.com.p2p.framework.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

public class JsonPluginsUtil {

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * @param beanCalss
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
		T bean = null;
		if (StringUtils.isNotEmpty(jsonString)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			bean = (T) JSONObject.toBean(jsonObject, beanCalss);
		}
		return bean;
	}

	/**
	 * 将java对象转换成json字符串
	 *
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		jsonConfig.registerDefaultValueProcessor(BigDecimal.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		jsonConfig.registerDefaultValueProcessor(Long.class,
				new DefaultValueProcessor() {
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		jsonConfig.registerDefaultValueProcessor(Double.class,
				new DefaultValueProcessor() {
			public Object getDefaultValue(Class type) {
				return null;
			}
		});
		JSONObject json = JSONObject.fromObject(bean,jsonConfig);
		return json.toString();
	}

	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param <T>
	 * @param beans
	 * @return
	 */
	public static <T> String beanListToJson(List<T> beans) {
		if (beans == null) {
			return null;
		}
		StringBuffer rest = new StringBuffer();
		rest.append("[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("]");
		return rest.toString();
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象List集合
	 * 
	 * @param jsonString
	 * @param beanCalss
	 * @return java对象List集合
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> JsonToBeanList(String jsonString,
			Class<T> beanCalss) {
		// 返回结果
		List<T> lstBean = new ArrayList<T>();

		if (StringUtils.isNotEmpty(jsonString)) {
			String strTemp = jsonString.substring(1, jsonString.length() - 1);
			String[] astrJsonString = strTemp.split("},");
			StringBuffer sbTemp = null;
			for (int i = 0; i < astrJsonString.length; i++) {
				sbTemp = new StringBuffer();
				sbTemp.append(astrJsonString[i]);
				if (i != astrJsonString.length - 1) {
					sbTemp.append("}");
				}
				JSONObject jsonObject = JSONObject
						.fromObject(sbTemp.toString());
				T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
				lstBean.add(bean);
			}
		}
		return lstBean;
	}

	/**
	 * 将java对象list转为JSONArray
	 * 
	 * <pre>
	 * 用途：
	 * 当需要将java对象List通过ajax响应回写的时候，如果直接将java对象回写，则ajax响应接收到的会是json字符串，这样不利于js处理
	 * 此时可以使用此方法，将java对象list转为JSONArray然后回写，这样ajax响应会接收到json对象的数组，可以直接操作
	 * </pre>
	 * 
	 * @param beans
	 * @return
	 * @throws JSONException
	 * @author 
	 */
	public static <T> JSONArray beanListToJSONArray(List<T> beans)
			throws JSONException {
		JSONArray dataJSONArray = new JSONArray();
		JSONObject jo = null;
		if (beans != null) {
			for (T bean : beans) {
				jo = JSONObject.fromObject(bean);
				dataJSONArray.put(jo);
			}
		}
		return dataJSONArray;
	}
}
