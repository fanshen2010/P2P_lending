package cn.com.p2p.system.service;

import java.util.List;

import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.system.service.dto.MessageReceiverDto;

public interface MessageReceiverService {
	/**
	 * 
	  * 
	  * <p>根据消息ID查找消息接收者设定表 * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param groupCode 组编码
	  *	@return SysSetting类型集合
	 */
	public MessageReceiverDto findMessageReceiverByMsgId(String id, String realName);
	
	/**
	 * 
	  * 
	  * <p>根据消息模版和用户信息保存消息接收者设定表 * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param MessageReceiverDto messageReceiverDto, MessageTemplete messageTemplete
	 */
	public void saveMessageReceiver(MessageReceiverDto messageReceiverDto, MessageTemplete messageTemplete);
	
}
