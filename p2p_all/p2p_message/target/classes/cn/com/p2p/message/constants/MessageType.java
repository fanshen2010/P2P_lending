package cn.com.p2p.message.constants;

/***
 * 消息类型枚举
 * @author 
 *
 */
public enum MessageType {
	
	/**短消息*/
	SMS("0"),

	/**站内信*/
	INTERNAL("1");


	private String value;
	private MessageType(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	
	public static MessageType typeOf(String value) {
		for (MessageType rt : values()) {
			if (rt.value.equals(value)) {
				return rt;
			}
		}
		return null;
	}
}
