/**
 * cn.pub.framework.i18n LanguageUtil.java
 * 2010-5-7 下午04:32:10
 */
package cn.com.p2p.framework.i18n;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import cn.com.p2p.framework.context.annotation.Language;
import cn.com.p2p.framework.util.LangUtils;


/**
 * 根据注解设定Bean的属性
 * 
 * @author pub
 * @since 2010-5-7
 * 
 */
public class LanguageUtil implements Serializable {
	
	private static final long serialVersionUID = -2534122473698940754L;

	public static Object getPropertyValue(Object bean, String propertyName) {
		return getPropertyValue(bean, propertyName, LangUtils.getLanguage());
	}

	public static void setPropertyValue(Object bean, String propertyName, Object value) {
		setPropertyValue(bean, propertyName, LangUtils.getLanguage(), value);
	}

	public static Object getPropertyValue(Object bean, String propertyName, String lang) {
		Object result = null;
		String className = bean.getClass().getName();
		if (className.indexOf("_$$_javassist") > -1) {
			className = className.substring(0, className.indexOf("_$$_javassist"));
		}

		Field[] fields;
		try {
			fields = Class.forName(className).getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				Language lanAnnotation = field.getAnnotation(Language.class);
				if (lanAnnotation != null) {
					if (propertyName.equals(lanAnnotation.name()) && lang.equals(lanAnnotation.lang())) {
						try {
							result = PropertyUtils.getProperty(bean, field.getName());
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public static void setPropertyValue(Object bean, String propertyName, String lang, Object value) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Language lanAnnotation = field.getAnnotation(Language.class);
			if (lanAnnotation != null) {
				if (propertyName.equals(lanAnnotation.name()) && lang.equals(lanAnnotation.lang())) {
					try {
						field.set(bean, value);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
