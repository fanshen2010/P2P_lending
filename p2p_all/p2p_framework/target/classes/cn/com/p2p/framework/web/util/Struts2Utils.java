/**
 * Copyright (c) 2010 www.pub.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Struts2Utils.java,v 1.4 2010/08/22 07:14:29 xp Exp $
 */
package cn.com.p2p.framework.web.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.util.WebUtils;

import cn.com.p2p.framework.context.UserContext;

/**
 * Struts2 Utils类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author pub
 */
public class Struts2Utils implements Serializable {

	private static final long serialVersionUID = 8135767349279345254L;

	// -- header 常量定义 --//
	private static final String ENCODING_PREFIX = "encoding";
	private static final String NOCACHE_PREFIX = "no-cache";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	// -- content-type 常量定义 --//
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";

	private static Logger logger = LoggerFactory.getLogger(Struts2Utils.class);

	// -- 取得Request/Response/Session的简化函数 --//
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	/**
	 * 取得ServletContext的简化函数.
	 */
	public static ServletContext getApplication() {
		return  ServletActionContext.getServletContext();
	}
	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. <br/>
	 * render("text/plain", "hello", "encoding:GBK"); <br/>
	 * render("text/plain", "hello", "no-cache:false"); <br/>
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else {
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
				}
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @throws JSONException 
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final String... headers) throws JSONException {
		String str =new JSONObject(jsonString).toString();
		render(HTML_TYPE, str, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param map
	 *            Map对象,将被转化为json字符串.
	 * @throws JSONException 
	 * @see #render(String, String, String...)
	 */
	@SuppressWarnings("unchecked")
	public static void renderJson(final Map map, final String... headers) throws JSONException {
		JSONObject json =new JSONObject(map);
//		json.
		render(JSON_TYPE, json.toString(), headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param object
	 *            Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object, final String... headers) {
//		String jsonString = JSONObject.fromObject(object).toString();
//		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param collction
	 *            Java对象集合, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Collection<?> collction, final String... headers) {
//		String jsonString = JSONArray.fromObject(collction).toString();
//		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param array
	 *            Java对象数组, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object[] array, final String... headers) {
//		String jsonString = JSONArray.fromObject(array).toString();
//		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName
	 *            callback函数名.
	 * @param contentMap
	 *            Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	@SuppressWarnings("unchecked")
	public static void renderJsonp(final String callbackName, final Map contentMap, final String... headers) {
//		String jsonParam = JSONObject.fromObject(contentMap).toString();

//		StringBuilder result = new StringBuilder().append(callbackName).append("(").append(jsonParam).append(");");
//
//		// 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
//		// 如callback197("{content:'Hello World!!!'}");
//		render(JS_TYPE, result.toString(), headers);
	}
	
	
	/**
	 * 将指定前缀的参数添加到Request对象中
	 * @param prefix 参数前缀
	 */
	public static void fillSearchParams(String prefix){
		if(StringUtils.isBlank(prefix)){
			return;
		}
		HttpServletRequest request = getRequest();
		Map<String,Object> parmMap = new HashMap<String,Object>();
		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map
		Map<String, Object> filterParamMap = WebUtils.getParametersStartingWith(request, prefix + "#filter_");
		if (filterParamMap != null && !filterParamMap.isEmpty()) {
			for (Entry<String, Object> entry : filterParamMap.entrySet()) {
				String filterName = entry.getKey();
				String value = ((String) entry.getValue()).trim().replaceAll("\"", "&quot;");
				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll(">", "&gt;");
				// 如果value值为空,则忽略此filter.
				boolean omit = StringUtils.isBlank(value);
				if (!omit) {
					request.setAttribute("filter_" + filterName, value);
					parmMap.put("filter_" + filterName, value);

				}
			}
//			request.setAttribute(HibernateWebUtils.SEARCH_KEY, parmMap);
			request.setAttribute(prefix, parmMap);
		}
	}
	
	public static String getLoginUserId() {
		String userId = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			if(authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserContext) {
				UserContext user = (UserContext) authentication.getPrincipal();
				userId = user.getUsername();
			}
		}
		return userId;
	}
}
