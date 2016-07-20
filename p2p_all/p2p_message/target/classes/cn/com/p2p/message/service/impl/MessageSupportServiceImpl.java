package cn.com.p2p.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.domain.message.repository.MessageLogRepository;
import cn.com.p2p.domain.message.repository.MessageTempleteRepository;
import cn.com.p2p.framework.constant.Constants;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.message.constants.MessageType;
import cn.com.p2p.message.service.MessageSupportService;

@Service
public class MessageSupportServiceImpl implements MessageSupportService {

	@Autowired
	MessageTempleteRepository messageTempleteRepository;
	
	@Autowired
	MessageLogRepository messageLogRepository;

	@Override
	public List<MessageTemplete> getMessageTempList(String msgType,
			String msgBizType, String msgReceType, String tenantCd) {
		return messageTempleteRepository.findMessageTempleteByBizKeys(msgType,
				msgBizType, msgReceType, "1", tenantCd);
	}

	@Override
	public List<MessageLog> getMessageLog(String tenantCd, String userId) {
		String tenantCdTmp = tenantCd;
		if (StringUtils.isEmpty(tenantCd)) {
			tenantCdTmp = Constants.PLAT_TENANT_CD;
		}
		List<MessageLog> msglogList = messageLogRepository.findMessageLogByUser(MessageType.INTERNAL.getValue(), userId, tenantCdTmp);
		return msglogList;
	}
	@Override
	public List<MessageLog> findPageMessageLog(MessageLogCriteria criteria) {
		if (StringUtils.isEmpty(criteria.getTenantCd())) {
			criteria.setTenantCd(Constants.PLAT_TENANT_CD, Operator.equal);
		}
		List<MessageLog> msglogList = messageLogRepository.findPageByCriteria(criteria);
		return msglogList;
	}

    /**
     * <p>更改消息日志的状态到未读</p>
     * @author 
     * @date 2015-10-27 16:48
     * @param id 消息日志的ID
     */
    @Override
    public void changeStatusToUnread(String id) {
        MessageLog messageLog = new MessageLog();
        messageLog.setId(id);
        // TODO 状态的值为手动设置，请注意修改
        messageLog.setStatus("0");
        messageLogRepository.dynamicUpdate(messageLog);
    }

    /**
     * <p>更改消息日志的状态到已读</p>
     * @author 
     * @date 2015-10-27 16:53
     * @param id 消息日志的ID
     */
    @Override
    public void changeStatusToRead(String id) {
        MessageLog messageLog = new MessageLog();
        messageLog.setId(id);
        // TODO 状态的值为手动设置，请注意修改
        messageLog.setStatus("1");
        messageLogRepository.dynamicUpdate(messageLog);
    }
    
    /**
     * <p>更改消息日志的状态到已读</p>
     * @author 
     * @date 2015-10-27 16:53
     * @param id 消息日志的ID
     */
    @Override
    public void changeStatusToReadByCriteria(MessageLogCriteria criteria){
    	List<MessageLog> msglogList = messageLogRepository.findByCriteria(criteria);
    	for(MessageLog messageLog :msglogList){
    		if("0".equals(messageLog.getStatus())){
    			messageLog.setStatus("1");
        		messageLogRepository.dynamicUpdate(messageLog);
    		}
    	}
    	
    }

	@Override
	public int getUnreadMessageCount(String msgReceType, String userId) {

        int msgCnt = 0;
		MessageLogCriteria criteria = new MessageLogCriteria();
		if (StringUtils.isNotEmpty(msgReceType)) {
		    criteria.setMsgReceiveType(msgReceType, Operator.equal);
		}
		criteria.setToUserId(userId, Operator.equal);
		criteria.setMsgType(MessageType.INTERNAL.getValue(), Operator.equal);
		criteria.setStatus("0", Operator.equal);
		List<MessageLog> msglogList = messageLogRepository.findByCriteria(criteria);
		msgCnt = msglogList.size();
		return msgCnt;
	}
}
