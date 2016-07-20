package cn.com.p2p.security.control.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import cn.com.p2p.framework.util.Struts2Utils;

public class FeroLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint { 
  
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();  
    private Logger logger= LoggerFactory.getLogger(FeroLoginUrlAuthenticationEntryPoint.class);
    
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)  
            throws IOException, ServletException {  
  
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
  
        String redirectUrl = null;  
  
        String url = request.getRequestURI();  
  
        if (logger.isDebugEnabled()) {  
            logger.debug("url:" + url);  
        }
        logger.debug("request Header" + request.getHeader("requst_ajax_type"));
        boolean isAjax = "0".equals(request.getHeader("requst_ajax_type"));
        // 非ajax请求  
        if (!isAjax) {  
  
            if (this.isUseForward()) {  
  
                if (this.isForceHttps() && "http".equals(request.getScheme())) {  
                    // First redirect the current request to HTTPS.  
                    // When that request is received, the forward to the login page will be used.  
                    redirectUrl = buildHttpsRedirectUrlForRequest(httpRequest);  
                }  
  
                if (redirectUrl == null) {  
                    String loginForm = determineUrlToUseForThisRequest(httpRequest, httpResponse, authException);  
  
                    if (logger.isDebugEnabled()) {  
                        logger.debug("Server side forward to: " + loginForm);  
                    }  
  
                    RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginForm);  
  
                    dispatcher.forward(request, response);  
  
                    return;  
                }  
            } else {  
                // redirect to login page. Use https if forceHttps true  
  
                redirectUrl = buildRedirectUrlToLoginPage(httpRequest, httpResponse, authException);  
  
            }  
  
            redirectStrategy.sendRedirect(httpRequest, httpResponse, redirectUrl);  
        } else {  
            // ajax请求，返回json，替代redirect到login page  
            if (logger.isDebugEnabled()) {  
                logger.debug("ajax request or post");  
            }  

            Map<String, String> jsonData = new HashMap<String, String>();
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                jsonData.put("auth_result", "0");
                JSONObject content =new JSONObject(jsonData);
                response.getWriter().write(content.toString());
                response.getWriter().flush();
            } catch (Exception e) {
                logger.debug(e.getMessage()); 
            } 
        }  
    }
 }
