package cn.com.p2p.framework.message;

public interface GetMessage {

	/**
	 * 发送的消息
	 * 
	 * @param msgType发送类型key
	 *            xml配置
	 * @param replaceArrsy替换的字符串
	 *            ，如果没有不同传入
	 * @return String 发送的消息
	 */
	public String getMessageContent(String msgType, String... replaceArrsy);

	/**
	 * 发送的消息主题
	 * 
	 * @param msgType发送类型key
	 *            xml配置
	 * @param replaceArrsy替换的字符串
	 *            ，如果没有不同传入
	 * @return String 发送的消息
	 */
	public String getMessageSubject(String msgType, String... replaceArrsy);
}
