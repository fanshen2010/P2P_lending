package cn.com.p2p.framework.util;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 此类封装了读取系统配置文件(application.properties)的功能。<br/>
 * 用法 : <br/>
 * String value = PropertyManager.getProperty("system.charset.encoding"); <br/>
 * <br/>
 * 配置文件是在WEB-INF/classes/application.properties
 */
public class PropertyManager implements Serializable {
	
	private static final long serialVersionUID = 4663301615585197591L;

	private static PropertyManager manager = null;
	private static Object managerLock = new Object();
	private Object propertiesLock = new Object();
	private static String configName = "/application.properties";
	private Properties properties = null;

	private static PropertyManager getInstance() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager();
				}
			}
		}
		return manager;
	}

	private PropertyManager() {
	}

	public static String getProperty(String name) {
		return getInstance()._getProperty(name);
	}

	private String _getProperty(String name) {
		initProperty();
		String property = properties.getProperty(name);
		if (property == null) {
			return "";
		} else {
			return property.trim();
		}
	}

	@SuppressWarnings("unchecked")
	public static Enumeration propertyNames() {
		return getInstance()._propertyNames();
	}

	@SuppressWarnings("unchecked")
	private Enumeration _propertyNames() {
		initProperty();
		return properties.propertyNames();
	}

	private void initProperty() {
		if (properties == null) {
			synchronized (propertiesLock) {
				if (properties == null) {
					try {
						loadProps();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void loadProps() throws Exception {
		properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(configName);
			properties.load(in);
		} catch (Exception e) {
			properties = null;
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
}
