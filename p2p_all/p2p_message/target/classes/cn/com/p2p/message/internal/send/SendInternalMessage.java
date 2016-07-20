
package cn.com.p2p.message.internal.send;


public interface SendInternalMessage {

    /**
     * 发送后台用户消息
     * @param tenantCd 商户ID (通过商户ID获取消息接受者)
     * @param msgBizType 消息类型 例如：（系统通知消息 账户被锁定消息等等）
     * @param replaceArrsy 消息参数
     * @return
     */
	public boolean send2BackUser(String tenantCd, String msgBizType, String... replaceArrsy);

    /**
     * 发送前台用户消息
     * @param toUser 消息接受者
     * @param msgBizType 消息类型 例如：（系统通知消息 账户被锁定消息等等）
     * @param replaceArrsy 消息参数
     * @return
     */
	public boolean send2FrontUser(String toUser, String msgBizType, String... replaceArrsy);

}
