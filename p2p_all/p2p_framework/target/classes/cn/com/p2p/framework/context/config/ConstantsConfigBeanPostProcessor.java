package cn.com.p2p.framework.context.config;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ReflectionUtils;

import cn.com.p2p.framework.context.annotation.ConstantsConfig;

/**
 * 常量资源文件加载器，加载指定的资源文件中的值到Spring环境中。
 * 
 * @author zhirong
 * @see cn.com.p2p.framework.context.annotation.ConstantsConfig
 */
public class ConstantsConfigBeanPostProcessor extends
		PropertyPlaceholderConfigurer implements BeanPostProcessor,
		InitializingBean {

	private static Properties properties;

	/**
	 * 处理bean中含有@ConstantsConfig注解的成员属性，并试图将对应的常量值注入。
	 * 
	 * @param bean
	 *            IoC中的对象
	 * @param asClass
	 *            bean类型
	 * @throws SecurityException
	 */
	private void handlePropertiesAnnotatedBean(Object bean, Class<?> asClass)
			throws SecurityException {
		if (isHandleInheritance(asClass.getSuperclass())) {
			handlePropertiesAnnotatedBean(bean, asClass.getSuperclass());
		}
		Field[] fields = asClass.getDeclaredFields();
		for (Field field : fields) {
			handleField(bean, field);
		}
	}

	/**
	 * 判断@ConstantsConfig注解是否可以从超类中继承过来而使用，即@ConstantsConfig在该bean的超类中使用，
	 * 但要通过当前bean对象进行操作。
	 * 
	 * @param clazz
	 *            bean对象的超类类型
	 * @return true or false
	 */
	boolean isHandleInheritance(Class<?> clazz) {
		if (clazz != null && clazz.isAnnotationPresent(ConstantsConfig.class)) {
			return clazz.getAnnotation(ConstantsConfig.class).inherited();
		}
		return false;
	}

	/**
	 * 为成员属性设置资源文件中的值
	 * 
	 * @param bean
	 *            对象
	 * @param field
	 *            成员属性
	 */
	private void handleField(Object bean, Field field) {
		if (field != null && field.isAnnotationPresent(ConstantsConfig.class)) {
			try {
				ReflectionUtils.makeAccessible(field);
				field.set(bean, getPropertyValue(field));
			} catch (Exception ex) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null,
						ex);
			}
		}
	}

	/**
	 * 获得指定字段的字符串名称
	 * 
	 * @param field
	 *            成员属性
	 * @return 属性名称
	 */
	private String getPropertyName(Field field) {
		if (field == null) {
			return "";
		}
		ConstantsConfig c = field.getAnnotation(ConstantsConfig.class);
		String namePrefix = c.namePrefix();
		String name = field.getName();
		return namePrefix.equals("") ? name : namePrefix + "." + name;
	}

	/**
	 * 获得指定属性的值，前提是改属性使用了@ConstantsConfig，且在常量资源文件中能够找到对应的值。
	 * 
	 * @param field
	 * @return
	 */
	private String getPropertyValue(Field field) {
		if (field == null) {
			return "";
		}
		String defaultValue = "";
		if (field.isAnnotationPresent(ConstantsConfig.class)) {
			defaultValue = field.getAnnotation(ConstantsConfig.class).value();
		}
		return properties.getProperty(getPropertyName(field), defaultValue);
	}

	public void afterPropertiesSet() throws Exception {
		properties = mergeProperties();
	}

	static Properties getProperties() {
		return properties;
	}

	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}
}