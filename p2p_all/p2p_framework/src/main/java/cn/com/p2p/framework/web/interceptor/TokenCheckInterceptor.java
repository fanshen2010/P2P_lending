package cn.com.p2p.framework.web.interceptor;

import java.lang.reflect.Method;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.token.Token;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenCheckInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public TokenCheckInterceptor() {

	}

	private static final Logger log = LoggerFactory.getLogger(TokenCheckInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object tmpAction = invocation.getAction();

		if (tmpAction instanceof BaseAction) {
			BaseAction action = (BaseAction) tmpAction;
			// 获取执行Action中的方法名
			Method method = getActionMethod(action.getClass(), invocation
					.getProxy().getMethod());
			Token token = method.getAnnotation(Token.class);
            if (token != null) {
                boolean needGenSession = token.generator();
                if (needGenSession) {
                	Struts2Utils.getSession().setAttribute("token", StringUtils.getUUID());
                }
                boolean needCheckSession = token.check();
                if (needCheckSession) {
                    if (isRepeatSubmit()) {
                    	System.out.println(action.getClass().getName() + " [" + method.getName() + "] " +"重复提交");
                    	log.error(action.getClass().getName() + " [" + method.getName() + "] " +"重复提交");
                    	String result=token.result();
                    	if(StringUtils.compare("", result)){
                    	    return null;
                    	}
                        return token.result();
                    }
                    Struts2Utils.getSession().removeAttribute("token");
                }
            }			
		}
		String result=invocation.invoke();
		return result;
	}

	/**
	 * 重复提交check
	 * @param request
	 * @return
	 */
    private boolean isRepeatSubmit() {
    	//请求时 token value为空时，当作不进行token check
        String clientToken = Struts2Utils.getRequest().getParameter("token");
        if (clientToken == null) {
            return true;
        }
        String serverToken = (String) Struts2Utils.getSession().getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
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
