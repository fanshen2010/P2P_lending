package cn.com.p2p.message.constants;

/***
 * 消息类型枚举
 * @author 
 *
 */
public enum MessageReceiverType {
	
	/**前台用户*/
	FRONT_USER("0"),

	/**后台用户*/
	BACK_USER("1");


	private String value;
	private MessageReceiverType(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	
	public static MessageReceiverType typeOf(String value) {
		for (MessageReceiverType rt : values()) {
			if (rt.value.equals(value)) {
				return rt;
			}
		}
		return null;
	}
}
