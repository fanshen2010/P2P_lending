package cn.com.p2p.framework.web.security;

public interface EncodeURLInterface {

	/**
	 * url参数加密
	 * @param param 参数 url？后面的东西
	 * @return
	 */
	public String encodeURLParam(String param);
	
	/**
	 * url参数加密
	 * @param param 完整url
	 * @return
	 */
	public String encodeURL(String param);
}
