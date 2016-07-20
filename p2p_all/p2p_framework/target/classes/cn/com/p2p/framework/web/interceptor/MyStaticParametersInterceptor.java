package cn.com.p2p.framework.web.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import cn.com.p2p.framework.util.CryptUtils;
import cn.com.p2p.framework.util.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class MyStaticParametersInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475892721375648613L;

	private static final String COMMON_SAVE_SEARCH_PARAM_SESSION = "COMMON_SAVE_SEARCH_PARAM_SESSION";
	private static final String COMMON_SAVE_SEARCH_PARAM_SESSION_FLAG = "COMMON_SAVE_SEARCH_PARAM_SESSION_FLAG";
	private boolean refreshUrl = true;

	private String prefixString = "";

	private String encryptKey = "";

	public void setPrefixString(String prefixString) {
		this.prefixString = prefixString;
	}

	private boolean ignoreAll = false;

	public boolean isIgnoreAll() {
		return ignoreAll;
	}

	public void setIgnoreAll(boolean ignoreAll) {
		this.ignoreAll = ignoreAll;
	}

	private List<String> ignoreNameList = null;

	public List<String> getIgnoreNameList() {
		return ignoreNameList;
	}

	public void setIgnoreNameList(String ignoreNameList) {

		this.ignoreNameList = new ArrayList<String>();
		if (ignoreNameList != null) {
			for (String tmp : ignoreNameList.split(",")) {
				this.ignoreNameList.add(tmp);
			}
		}

	}

	public boolean isRefreshUrl() {
		return refreshUrl;
	}

	public void setRefreshUrl(boolean refreshUrl) {
		this.refreshUrl = refreshUrl;
	}

	public MyStaticParametersInterceptor() {

	}

	private static final Logger LOG = LoggerFactory
			.getLogger(MyStaticParametersInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map map = invocation.getInvocationContext().getParameters();
		Set keys = map.keySet();
		Iterator iters = keys.iterator();
		while (iters.hasNext()) {
			Object key = iters.next();
			Object value = map.get(key);

			if (value != null && !(value instanceof String)
					&& value instanceof String[]) {

				map.put(key, transfer(key, (String[]) value));

			} else {
				map.put(key, value);
			}

		}
		// map.put("returnComSearchHtm", "/home/index.htm");
		// invocation.getStack().setValue("returnComSearchHtm",
		// "/home/index.htm");
		// Struts2Utils.getRequest().setAttribute("returnComSearchHtm",
		// "/home/index.htm");
		// return "returnComSearchHtm";

//		try {
//			Object action = invocation.getAction();
//			// 获取执行Action中的方法名
//			Method method = getActionMethod(action.getClass(), invocation
//					.getProxy().getMethod());
//			SaveSearchParam saveSearchParam = method
//					.getAnnotation(SaveSearchParam.class);
//			if (saveSearchParam != null) {
//				String sessioKey = action.getClass().getName() + "."
//						+ invocation.getProxy().getMethod();
//				//
//				Map<String, Object> parameters = getParamterMap(sessioKey);
//
//				// if (parameters == null) {
//				// parameters = new HashMap<String, Object>();
//				// Struts2Utils.getSession().setAttribute(
//				// COMMON_SAVE_SEARCH_PARAM_SESSION, parameters);
//				// }
//				//
//				if (Struts2Utils.getSession().getAttribute(
//						COMMON_SAVE_SEARCH_PARAM_SESSION_FLAG) != null) {
//
//					Map<String, Object> params = new TreeMap<String, Object>();
//					for (String key : parameters.keySet()) {
//						map.put(key, parameters.get(key));
//						if (key != null && key.indexOf("#filter_") != -1) {
//
//							String[] unprefixedAry = key.split("#filter_");
//
//							Object values = parameters.get(key);
//
//							if (values instanceof String[]) {
//								String[] valueAry = (String[]) values;
//
//								if (valueAry == null || valueAry.length == 0) {
//									// Do nothing, no values found at all.
//								} else if (valueAry.length > 1) {
//									params.put(unprefixedAry[1], valueAry);
//								} else {
//									params.put(unprefixedAry[1], valueAry[0]);
//								}
//							} else if (values instanceof String) {
//								params.put(unprefixedAry[1], values);
//							}
//							if (params.size() > 0) {
//								Struts2Utils
//										.getRequest()
//										.setAttribute(
//												Constant.SEARCH_PARAM_SET_REQUEST_PARAM,
//												params);
//							}
//
//						}
//					}
//
//					Struts2Utils.getSession().removeAttribute(
//							COMMON_SAVE_SEARCH_PARAM_SESSION_FLAG);
//				} else {
//					if (map.containsKey(saveSearchParam.saveFlag())) {
//
//						if (refreshUrl) {
//							Struts2Utils.getSession().setAttribute(
//									COMMON_SAVE_SEARCH_PARAM_SESSION_FLAG, "");
//							return saveSearchParam.redirectResult();
//						} else {
//							Map<String, Object> params = new TreeMap<String, Object>();
//							for (String key : parameters.keySet()) {
//								map.put(key, parameters.get(key));
//
//								if (key != null && key.indexOf("#filter") != -1) {
//									String[] unprefixedAry = key
//											.split("#filter_");
//
//									Object values = parameters.get(key);
//
//									if (values instanceof String[]) {
//										String[] valueAry = (String[]) values;
//
//										if (valueAry == null
//												|| valueAry.length == 0) {
//											// Do nothing, no values found at
//											// all.
//										} else if (valueAry.length > 1) {
//											params.put(unprefixedAry[1],
//													valueAry);
//										} else {
//											params.put(unprefixedAry[1],
//													valueAry[0]);
//										}
//									} else if (values instanceof String) {
//										params.put(unprefixedAry[1], values);
//									}
//									if (params.size() > 0) {
//										Struts2Utils
//												.getRequest()
//												.setAttribute(
//														Constant.SEARCH_PARAM_SET_REQUEST_PARAM,
//														params);
//									}
//								}
//							}
//						}
//
//					} else {
//						// 保存
//
//						for (String param : saveSearchParam.paramIds()) {
//
//							parameters.put(param, map.get(param));
//						}
//						if (saveSearchParam.hasPage()) {
//
//							parameters.put("page.order", map.get("page.order"));
//							parameters.put("page.orderBy",
//									map.get("page.orderBy"));
//							parameters.put("page.pageSize",
//									map.get("page.pageSize"));
//							parameters.put("page.pageNo",
//									map.get("page.pageNo"));
//						}
//					}
//				}
//			}
//
//		} catch (Exception exx) {
			// logger.error("PermissonInterceptor", exx);
//			throw exx;
//		}
		return invocation.invoke();
	}

//	private Map<String, Object> getParamterMap(String sessionKey) {
//
//		List<SessionDataParam> tmpList = (List<SessionDataParam>) Struts2Utils
//				.getSession().getAttribute(COMMON_SAVE_SEARCH_PARAM_SESSION);
//
//		if (tmpList == null) {
//			tmpList = new LinkedList<SessionDataParam>();
//			Struts2Utils.getSession().setAttribute(
//					COMMON_SAVE_SEARCH_PARAM_SESSION, tmpList);
//		}
//		synchronized (tmpList) {
//
//			SessionDataParam tmpSdp = null;
//			for (SessionDataParam sdp : tmpList) {
//
//				if (sdp.sessionKey.equals(sessionKey)) {
//					tmpSdp = sdp;
//					break;
//				}
//
//			}
//
//			if (tmpSdp != null) {
//				return tmpSdp.parameters;
//			} else {
//
//				tmpSdp = new SessionDataParam();
//				tmpSdp.sessionKey = sessionKey;
//				tmpSdp.parameters = new HashMap<String, Object>();
//
//				if (tmpList.size() > 3) {
//					tmpList.remove(0);
//				}
//				tmpList.add(tmpSdp);
//
//				return tmpSdp.parameters;
//			}
//		}
//	}

	private class SessionDataParam {
		private String sessionKey = "";
		private Map<String, Object> parameters = null;
	}

	private String[] transfer(Object name, String[] params) throws IOException {
		for (int i = 0; i < params.length; i++) {

			if (StringUtils.isEmpty(params[i]))
				continue;

			// 危险字符处理
			if ((ignoreNameList != null && ignoreNameList.contains(name))
					|| ignoreAll) {
				params[i] = checkAndDecrypt(params[i]);
			} else {
				params[i] = StringEscapeUtils
						.escapeHtml3(checkAndDecrypt(params[i]));
			}

		}
		return params;
	}

	/**
	 * 加密key
	 * 
	 * @param encryptKey
	 */
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	private String checkAndDecrypt(String tmpStr) throws IOException {

		if (tmpStr != null && !"".equals(encryptKey)) {

			if (tmpStr.startsWith(prefixString)) {
				tmpStr = tmpStr.replace(prefixString, "");
				try {
					tmpStr = CryptUtils.decryptString(tmpStr, encryptKey);
					return tmpStr;
				} catch (IOException e) {

					LOG.error("解密", e);
					throw e;
				}

			}

		}

		return tmpStr;

	}

	/***
	 * 获取Action调用的方法名
	 * 
	 * @param actionClass
	 * @param methodName
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method getActionMethod(Class actionClass, String methodName)
			throws NoSuchMethodException {
		Method method;
		try {
			method = actionClass.getMethod(methodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			throw e;
		}
		return method;
	}
}
