package cn.com.p2p.framework.web.servlet;

import static javax.servlet.http.HttpServletResponse.SC_FOUND;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ServletRedirectResult;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.framework.web.security.EncodeURLInterface;

public class FeroServletRedirectResult extends ServletRedirectResult {

	@Autowired
	public Properties settings;
	@Autowired()
	private EncodeURLInterface encodeURLInterface;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5694674119301029436L;

	/**
	 * Sends the redirection. Can be overridden to customize how the redirect is
	 * handled (i.e. to use a different status code)
	 * 
	 * @param response
	 *            The response
	 * @param finalLocation
	 *            The location URI
	 * @throws IOException
	 */
	protected void sendRedirect(HttpServletResponse response,
			String finalLocation) throws IOException {

//		if (!finalLocation.startsWith("http")) {

			finalLocation = settings.getProperty("domainSsl")
					+ encodeURLInterface.encodeURL(finalLocation);
//		}
		if (SC_FOUND == statusCode) {
			response.sendRedirect(finalLocation);
		} else {
			response.setStatus(statusCode);
			response.setHeader("Location", finalLocation);
			response.getWriter().write(finalLocation);
			response.getWriter().close();
		}

	}
}
