package cn.com.p2p.domain.message.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.message.criteria.MessageManagementCriteria;
import cn.com.p2p.domain.message.entity.MessageReceiver;
import cn.com.p2p.domain.message.entity.MessageTemplete;

public interface MessageTempleteQuery {
	
	/**
     * 根据查询条件查询
     * <p>
     * @param  String criteria
     * @return List<MessageTemplete>
     */
	public List<MessageTemplete> findPageByCriteria(@Param("criteria") MessageManagementCriteria criteria);
	
	/**
     * 根据消息模版ID查询消息接收者设定表
     * <p>
     * @param  String id
     * @return List<MessageTemplete>
     */
	public List<MessageReceiver> findMessageReceiverByMsgId(@Param("receiverId") String receiverId);

}
