package cn.com.p2p.framework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 请求参数处理工具
 * @author pub
 *
 */
public class ParamUtils implements Serializable {
	
	private static final long serialVersionUID = 7260372065767847482L;
	
	public static final String DEFAULT_CHARSET = "UTF-8";

	public ParamUtils() {
	}

	public static String encode(String param) {
		if (param == null) {
			return "";
		}
		try {
			return new String(param.getBytes("ISO-8859-1"), DEFAULT_CHARSET);
		} catch (Exception ex) {
			return param;
		}
	}

	/**
	 * 获取页面访问者IP地址
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip == null || ip.trim().equals("")) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

	/**
	 * 写Cookies方法
	 */
	public static void setCookies(HttpServletResponse response, String name, String value) {
		try {
			Cookie c = new Cookie(name, java.net.URLEncoder.encode(value, "gb2312"));
			c.setMaxAge(1000000);
			c.setPath("/");
			response.addCookie(c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 读取Cookies方法
	 */
	public static String getCookies(HttpServletRequest request, String name) {
		String v = "";
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return "";
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (c.getName().equals(name)) {
				v = c.getValue();
			}
		}
		v = (v == null || v.trim().equals("")) ? "" : v.trim();
		String r = "";
		try {
			r = java.net.URLDecoder.decode(v, "gb2312");
		} catch (Exception ex) {
			r = "";
		}
		return r;
	}

	/**
	 * 判断对象是否包含在指定的数组对象中
	 */
	public static boolean inArray(Object o, Object[] arrayObj) {
		if (arrayObj == null || arrayObj.length == 0) {
			return false;
		}
		for (int i = 0; i < arrayObj.length; i++) {
			if (arrayObj[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取HttpServletRequest对象中的Attriute属性对象
	 */
	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	public static String getAttribute(HttpServletRequest request, String name, boolean empty2null) {
		String s = (String) request.getAttribute(name);
		if (s != null) {
			if (s.equals("") && empty2null) {
				return null;
			} else {
				return s;
			}
		} else {
			return "";
		}
	}

	/**
	 * 获取HttpServletRequest对象中的Attriute属性对象，并且将结果直接转换成boolean型
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request, String name) {
		String s = (String) request.getAttribute(name);
		return s != null && (s.equals("true") || s.equals("True") || s.equals("TRUE"));
	}

	/**
	 * 获取HttpServletRequest对象中的Attriute属性对象，并且将结果直接转换成整型，<br>
	 * 如果获取的值不为整型，则指定默认值
	 */
	public static int getIntAttribute(HttpServletRequest request, String name, int df) {
		String s = (String) request.getAttribute(name);
		if (s != null && !s.equals("")) {
			int num = df;
			try {
				num = Integer.parseInt(s);
			} catch (Exception ignored) {
				num = df;
			}
			return num;
		} else {
			return df;
		}
	}

	/**
	 * 封装HttpServletRequest对象中getParameterValues方法，将字符数组结果转换为长整型数组，<br>
	 * 数组中存在非数值字符，则指定默认长整型
	 */
	public static long[] getLongParameters(HttpServletRequest request, String name, long df) {
		String paramValues[] = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new long[0];
		}
		long values[] = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++)
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = df;
			}
		return values;
	}

	/**
	 * 封装HttpServletRequest对象中getParameter方法，将字符结果转换为长整型，<br>
	 * 如果是非数值字符，则指定默认长整型
	 */
	public static long getLongParameter(HttpServletRequest request, String name, long df) {
		String s = request.getParameter(name);
		if (s != null && !s.equals("")) {
			long num = 0;
			try {
				num = Long.parseLong(s);
			} catch (Exception ignored) {
				num = df;
			}
			return num;
		} else {
			return df;
		}
	}

	/**
	 * 封装HttpServletRequest对象中getParameter方法
	 */
	public static String getParameter(String val, String df) {
		String param = val;
		if (param == null || param.trim().equals("")) {
			param = df.trim();
		}
		return param.trim();
	}

	public static String getParameter(HttpServletRequest request, String name) {
		return getParameter(request, name, false);
	}

	/**
	 * 如果HTTP请求以GET方式发送，并且在浏览器中将请求地址以encodeURI(URL)进行编码<br>
	 * 则在服务器端需要以java.net.URLDecoder.decode方式将信息还原。<br>
	 * (注：在还原以前，request对象不能做如request.setCharacterEncoding("UTF-8")等类似操作)
	 */
	public static String getURLParameter(HttpServletRequest request, String name) {
		String s = request.getParameter(name);
		s = (s == null) ? "" : s.trim();
		try {
			String charset = DEFAULT_CHARSET;
			return new String(s.getBytes("ISO-8859-1"), charset);
		} catch (Exception ex) {
			return name;
		}
	}

	public static String getURLParameter(HttpServletRequest request, String name, String df) {
		String s = request.getParameter(name);
		s = (s == null) ? "" : s.trim();
		s = urlDecode(s);
		if (s.equals("")) {
			return df;
		}
		return s;
	}

	private static String urlDecode(String s) {
		String r = "";
		try {
			r = java.net.URLDecoder.decode(s, DEFAULT_CHARSET);
		} catch (Exception ex) {
		}
		return r;
	}

	@SuppressWarnings("unchecked")
	public static String[] getURLParameters(HttpServletRequest request, String name) {
		if (name == null) {
			return new String[0];
		}
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new String[0];
		}
		List values = new ArrayList();
		if (paramValues.length == 1) {
			String param = urlDecode(paramValues[0]);
			String[] params = param.split(",");
			for (int i = 0; i < params.length; i++) {
				values.add(params[i]);
			}
		} else {
			for (int i = 0; i < paramValues.length; i++) {
				values.add(urlDecode(paramValues[i]));
			}
		}
		return (String[]) values.toArray(new String[0]);
	}

	/**
	 * 封装HttpServletRequest对象中getParameter方法，如果结果为空，则指定默认值
	 */
	public static String getParameter(HttpServletRequest request, String name, String df) {
		String param = getParameter(request, name, true);
		if (param == null || param.trim().equals("")) {
			param = df.trim();
		}
		return param.trim();
	}

	/**
	 * 封装HttpServletRequest对象中getParameter方法，如果字符串结果为Empty，则转换为null
	 */
	public static String getParameter(HttpServletRequest request, String name, boolean empty2null) {
		try {
			request.setCharacterEncoding(DEFAULT_CHARSET);
		} catch (Exception ex) {
		}
		String s = request.getParameter(name);
		if (s != null) {
			if (s.equals("") && empty2null) {
				return null;
			} else {
				return s;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static String[] getParameters(HttpServletRequest request, String name) {
		if (name == null) {
			return new String[0];
		}
		String paramValues[] = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new String[0];
		}
		List values = new ArrayList(paramValues.length);
		for (int i = 0; i < paramValues.length; i++) {
			if (paramValues[i] != null && !"".equals(paramValues[i])) {
				values.add(paramValues[i]);
			}
		}
		return (String[]) values.toArray(new String[0]);
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name) {
		return getBooleanParameter(request, name, false);
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		}
		if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return defaultVal;
		}
	}

	public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static int[] getIntParameters(HttpServletRequest request, String name, int defaultNum) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {
		}
		String paramValues[] = request.getParameterValues(name);
		if (paramValues == null || paramValues.length == 0) {
			return new int[0];
		}
		int values[] = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++)
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		return values;
	}

	public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 从Servlet全局中查找指定KEY值的对象，顺序为request , session , application<br>
	 * session.get/setValue()这个方法在JDK1.3.1中用于HttpSession对象的存取，<br>
	 * 但JDK1.4.1后推荐存取方法为：session.get/setAttribute方法，<br>
	 * get/setAttribute方法在JDK1.3.1版本及以往版本不提供此方法，这里需要注意<br>
	 * 注：在Servlet中获取session对象的方法是：<br>
	 * HttpSession session = request.getSession();<br>
	 * 而获取application对象的方法是：<br>
	 * ServletContext application = getServletConfig().getServletContext();
	 */
	public static Object getScopeObject(HttpServletRequest request, HttpSession session, ServletContext application, String key) {
		Object o = null;
		o = request.getAttribute((String) key);
		if (o == null) {
			o = session.getAttribute(key);
		}
		if (o == null) {
			o = application.getAttribute(key);
		}
		return o;
	}

	public static String getURLDecode(String s) {
		String r = "";
		try {
			r = java.net.URLDecoder.decode(s, "UTF-8");
		} catch (Exception ex) {
		}
		return r;
	}

}
