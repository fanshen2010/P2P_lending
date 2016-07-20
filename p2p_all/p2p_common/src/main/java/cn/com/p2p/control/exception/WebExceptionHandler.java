package cn.com.p2p.control.exception;

import cn.com.p2p.framework.exception.BusinessException;
import cn.com.p2p.framework.exception.DBAccessException;
import cn.com.p2p.framework.exception.SystemException;
import cn.com.p2p.framework.resource.MessageResourceBundle;
import cn.com.p2p.framework.web.validator.ActionErrors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class WebExceptionHandler extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String EXCEPTION = "exception";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	      try {
	          return invocation.invoke();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return handleException(e);
	        }
	}

    /**
     * 处理异常
     * @param e
     */
    private String handleException(Exception e) {
        String result = EXCEPTION;
        String errorCode = "";
        //业务check异常
        if(e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            errorCode = be.getErrorCode();
			result = Action.INPUT;
		//业务处理层系统异常
        } else if (e instanceof SystemException) {
        	SystemException se = (SystemException) e;
            errorCode = se.getErrorCode();
        //DB访问层系统异常
        } else if (e instanceof DBAccessException) {
        	DBAccessException de = (DBAccessException) e;
            errorCode = de.getErrorCode();
        } else {
        	errorCode = "NKE001";
        }
        
        
        // 通过ErrorCode取得对应message
        String errorMsg = MessageResourceBundle.getMessage(errorCode);
        
        // 页面显示错误提示信息
		ActionErrors errors = new ActionErrors();
		errors.addError(errorMsg);

		return result;
    }
    
}
