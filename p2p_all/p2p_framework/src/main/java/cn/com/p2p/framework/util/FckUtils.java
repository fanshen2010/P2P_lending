package cn.com.p2p.framework.util;

public class FckUtils {
	
	public static String replaceP_BR(String content) {
		String temp = content.replaceAll("<(?i)p(?-i)>", "");
		return temp.replaceAll("</(?i)p(?-i)>", "<br/>");
	}
	
	public static String filterP(String content) {
		return content.replaceAll("</*(?i)p(?-i)\\s*/*>|[\\t\\r\\n]", "");
	}
	
	public static String filterBR(String content) {
		return content.replaceAll("<(?i)br(?-i)\\s*/*>|[\\t\\r\\n]", "");
	}
	
	public static String filterS(String content) {
		return content.replaceAll("[\\t\\r\\n]", "");
	}
	
}
