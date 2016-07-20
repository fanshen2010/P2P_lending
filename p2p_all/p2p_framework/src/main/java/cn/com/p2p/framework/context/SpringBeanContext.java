package cn.com.p2p.framework.context;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存Spring ApplicationContext，以便在程序中随时获取IoC的bean，前提是需要知道该bean的名称
 * @author pub
 * 
 */
public class SpringBeanContext implements ApplicationContextAware, Serializable {

	private static final long serialVersionUID = 7754599673733455588L;

	private static ApplicationContext applicationContext;



	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
		}
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	static <T> T getBean(String name) {
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * SpringBeanContext适配器
	 * @author zhirong
	 *
	 */
	public static class SpringBeanContextAdapter {
		
		public ApplicationContext getApplicationContext() {
			return SpringBeanContext.getApplicationContext();
		}
		
		public <T> T getBean(String name) {
			return SpringBeanContext.getBean(name);
		}
		
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
		
	}
}
