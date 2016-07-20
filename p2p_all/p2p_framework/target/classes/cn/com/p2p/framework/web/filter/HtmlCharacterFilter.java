package cn.com.p2p.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlCharacterFilter implements Filter {
	 
     public void doFilter(ServletRequest req, ServletResponse resp,
              FilterChain chain) throws IOException, ServletException {
          
          HttpServletRequest request = (HttpServletRequest) req;
          HttpServletResponse response = (HttpServletResponse) resp;
  
          HtmlCharacterRequestWrapper hcrequest = new HtmlCharacterRequestWrapper(request);
          chain.doFilter(hcrequest, response);
          
      }
  
      
      public void destroy() {
          
      }
  
      
      public void init(FilterConfig filterConfig) throws ServletException {
          
      }
  }
