package cn.com.p2p.framework.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

import cn.com.p2p.framework.util.CryptUtils;

public class MyDelegatingFilterProxy extends DelegatingFilterProxy {
	private String encryptKey = "";

	private String errorRedirectUrl = "/index.htm";

	@Override
	protected void initFilterBean() throws ServletException {

		super.initFilterBean();
		encryptKey = this.getFilterConfig().getInitParameter("encryptKey");
		String tmpRedirectUrl = this.getFilterConfig().getInitParameter(
				"errorRedirectUrl");

		if (tmpRedirectUrl != null && !"".equals(tmpRedirectUrl)) {
			errorRedirectUrl = tmpRedirectUrl;
		}
	}

	private PathMatcher urlMatcher = new AntPathMatcher();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if ("".equals(encryptKey) || encryptKey == null
				|| request.getParameterMap() == null
				|| request.getParameterMap().size() == 0) {
			super.doFilter(request, response, filterChain);

		} else {

			// object 是一个URL，被用户请求的url。
			String url = ((HttpServletRequest) request).getServletPath();

			int firstQuestionMarkIndex = url.indexOf("?");

			if (firstQuestionMarkIndex != -1) {
				url = url.substring(0, firstQuestionMarkIndex);
			}
			// 不处理url
			if (urlMatcher.match(url, "/account/security/updateMail.htm")
					|| urlMatcher.match(url,
							"/account/security/findPwdByMail.htm")
					|| urlMatcher.match(url, "/register.htm")
					|| urlMatcher.match(url,
							"/account/security/mailSuccess.htm")) {
				super.doFilter(request, response, filterChain);

				return;
			}
			HashMap m = new HashMap(request.getParameterMap());
			for (Iterator iter = request.getParameterMap().entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry element = (Map.Entry) iter.next();
				Object strKey = element.getKey();

				Object strObj = element.getValue();
				if (strObj == null || strObj.getClass().isArray()) {

					try {

						// Object[] objArray = (Object[]) strKey;
						if (strKey != null) {

							// 解密处理
							String key = strKey.toString();

							if (key != null && key.length() > 10
									&& Character.isDigit(key.charAt(0))) {

								key = new String(CryptUtils.hex2byte(key
										.substring(10)), "UTF-8");
								key = CryptUtils.decryptString(key, encryptKey);

								String[] arrSplit = null;
								arrSplit = key.split("&");

								for (String strSplit : arrSplit) {
									String[] arrSplitEqual = null;
									arrSplitEqual = strSplit.split("=");
									if (arrSplitEqual.length > 1) {
										// 正确解析
										m.put(arrSplitEqual[0], URLDecoder
												.decode(arrSplitEqual[1],
														"UTF-8"));

									} else {
										if (arrSplitEqual[0] != "") {
											// 只有参数没有值，
											m.put(arrSplitEqual[0], null);
										}
									}
								}
							} else {

								m.put(strKey, strObj);
							}
						} else {
							m.put(strKey, strObj);
						}
					} catch (Exception ex) {
						// 非法url
						((HttpServletResponse) response)
								.sendRedirect(((HttpServletRequest) request)
										.getContextPath() + errorRedirectUrl);

						return;
					}

				} else {
					m.put(strKey, strObj);
				}
			}

			MyHttpServletRequestWrapper wrapRequest = new MyHttpServletRequestWrapper(
					(HttpServletRequest) request, m);

			super.doFilter(wrapRequest, response, filterChain);

		}
	}

}
