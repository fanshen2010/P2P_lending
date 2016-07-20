/**
 * Copyright (c) 2010 www.pub.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringContextUtils.java,v 1.4 2010/08/22 07:14:17 xp Exp $
 */
package cn.com.p2p.framework.util;

import org.springframework.context.ApplicationContext;

import cn.com.p2p.framework.context.SpringBeanContext.SpringBeanContextAdapter;

/**
 * 以静态变量保存Spring ApplicationContext.
 * 
 * @author pub
 */
public class SpringContextUtils {
	
	private static SpringBeanContextAdapter adapter = new SpringBeanContextAdapter();
	
	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		return adapter.getApplicationContext();
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) adapter.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class type) {
		String[] names = getApplicationContext().getBeanNamesForType(type);
		for (String name : names) {
			T o = getBean(name);
			if (o.getClass().equals(type)) {
				return o;
			}
		}
		return null;
	}
	
}
