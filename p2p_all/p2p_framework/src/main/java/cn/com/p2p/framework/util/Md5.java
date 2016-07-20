/**
 * Copyright (c) 2010 www.pub.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ReflectionUtils.java 578  
 */
package cn.com.p2p.framework.util;

import java.security.MessageDigest;

/**
 * 此类为单例模式，封装了MD5加密的方法
 */
public class Md5 {
	
	private static Md5 md5 = null;
	private static Object logLock = new Object();

	private Md5() {
	}

	public static Md5 getInstance() {
		if (md5 == null) {
			synchronized (logLock) {
				if (md5 == null) {
					md5 = new Md5();
				}
			}
		}
		return md5;
	}

	public String encode(String seed, String msg, boolean isUpperCase) {
		MessageDigest md5 = null;
		StringBuffer sb = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
			StringBuffer temp = new StringBuffer();
			if (seed != null) {
				temp.append(seed);
			}
			temp.append(msg);
			md5.update(temp.toString().getBytes());
			byte[] array = md5.digest();
			for (int i = 0; i < array.length; i++) {
				int b = array[i] & 0xFF;
				if (b < 0x10) {
					sb.append('0');
				}
				sb.append(Integer.toHexString(b));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
		if (isUpperCase) {
			sb.toString().toUpperCase();
		}
		return sb.toString();
	}

	public String encode(String msg) {
		return encode(null, msg, false);
	}

	public String encode(String seed, String msg) {
		return encode(seed, msg, false);
	}
}
