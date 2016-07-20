package cn.com.p2p.framework.web.interceptor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.ActionError;

import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.ActionErrors;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.impl.ValidatorErrorParamImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/***
 * Fero服务器端校验通用拦截器
 * 
 * @author 张旭
 *
 */
public class CommonValidatorMethodInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		Object tmpAction = invocation.getAction();

		if (tmpAction instanceof BaseAction) {
			BaseAction action = (BaseAction) tmpAction;
			// 获取执行Action中的方法名
			Method method = getActionMethod(action.getClass(), invocation
					.getProxy().getMethod());
			Validators validators = method.getAnnotation(Validators.class);
			// 没有添加验证
			if (validators == null) {
				return invocation.invoke();
			} else {
				// 验证数据
				ActionContext ac = invocation.getInvocationContext();
				final Map<String, Object> parameters = retrieveParameters(ac);

				
				DataCheck rangeCheck = (DataCheck) SpringContextUtils.getBean(validators.str());
				List<String> msgs= rangeCheck.check(action,  parameters);
				if (msgs != null && msgs.size() > 0) {
					// 将错误消息放到Request对象中
					ActionErrors errors=new  ActionErrors();
					for (String msg : msgs) {
						errors.addError(msg.substring(msg.indexOf(":")+1));
						//errors.addError(msg);
					}
					if (!"".equals(validators.param())) {
						action.doDataValidatorFailure(validators.param(),
								new ValidatorErrorParamImpl(parameters));
					}
					return validators.result();

				}
			}

		}
		return invocation.invoke();
	}

	protected Map<String, Object> retrieveParameters(ActionContext ac) {
		
		return ac.getParameters();
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
