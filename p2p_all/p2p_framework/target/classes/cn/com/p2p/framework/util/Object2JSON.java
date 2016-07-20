package cn.com.p2p.framework.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Object2JSON implements Serializable {

	private static final long serialVersionUID = 503428970827020237L;

	public static String list2json(List<Object> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Object object : list) {
			try {
				sb = node2json(object, sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int lastdot = sb.lastIndexOf(",");

		StringBuffer json = sb.deleteCharAt(lastdot);
		json = json.append("]");
		return json.toString();
	};

	@SuppressWarnings("unchecked")
	public static StringBuffer node2json(Object object, StringBuffer sb)
			throws Exception {
		Class classType = object.getClass();
		Field[] fields = classType.getDeclaredFields();
		sb.append("{");
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			String firstLetter = name.substring(0, 1).toUpperCase();
			String getName = "get" + firstLetter + name.substring(1);
			Method getMethod = classType.getMethod(getName, new Class[] {});
			Object value = getMethod.invoke(object, new Object[] {});
			sb.append("\"").append(name).append("\":\"").append(value).append("\",");
		}

		int lastdot = sb.lastIndexOf(",");
		sb = sb.deleteCharAt(lastdot).append("},");
		return sb;
	}
	
	/**
	 * 将List<Map<String, Object>>对象转换成JSONArray对象，方便输出
	 * 
	 * @param listData
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("unused")
	public static JSONArray convertListToJSONArray(List<Map<String, Object>> listData)
			throws JSONException {
		JSONArray dataJSONArray = new JSONArray();
		JSONObject jo = null;
		for (Map<String, Object> data : listData) {
			jo = new JSONObject();
			for (String key : data.keySet()) {
				jo.put(key, data.get(key));
			}
			dataJSONArray.put(jo);
		}
		return dataJSONArray;
	}

}
