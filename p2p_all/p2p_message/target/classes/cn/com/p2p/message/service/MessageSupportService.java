package cn.com.p2p.message.service;

import java.util.List;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.domain.message.entity.MessageTemplete;

public interface MessageSupportService {

    /**
	 * <p>
     * 短信通道配置信息取得的对外接口，通过商户信息取得通道信息。
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * MessageSupportService messageSupportService;
     * <br>
     *   通过商户信息取得通道信息
     *   SmsSupportService.getMessageTempList(tenantCd);
     * <br>
     * </pre>
     * 
     * @param msgType  消息类型 0:手机短信 1：站内消息
     * @param msgReceType  接受者类型 0:大众用户 1：系统员工
     * @param tenantCd  商户
     * 
     * 
     */
	public List<MessageTemplete> getMessageTempList(String msgType, String msgBizType, String msgReceType, String tenantCd);
	
	
    /**
	 * <p>
     * 站内消息通知查询，为首页通知功能使用。
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * MessageSupportService messageSupportService;
     * <br>
     *   通过商户信息取得通道信息
     *   SmsSupportService.getMessageLog(tenantCd，userId);
     * <br>
     * </pre>
     * 
     * @param tenantCd  商户
     * @param userId  用户
     * 
     * 
     */
	public List<MessageLog> getMessageLog(String tenantCd, String userId);
	
	
	/**
	 * 
	  * 
	  * <p> 站内消息分页查询* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param tenantCd  商户
	  * @param userId  用户
	  *
	 */
	
	public List<MessageLog> findPageMessageLog(MessageLogCriteria criteria);
	   
    /**
      * <p>更改消息日志的状态到未读</p>
      * @author 
      * @date 2015-10-27 16:48
      * @param id 消息日志的ID
      */
    public void changeStatusToUnread(String id);
    
    /**
     * <p>更改消息日志的状态到已读</p>
     * @author 
     * @date 2015-10-27 16:53
     * @param id 消息日志的ID
     */
    public void changeStatusToRead(String id);
    
    /**
     * <p>更改消息日志的状态到已读</p>
     * @author 
     * @date 2015-10-27 16:53
     * @param id 消息日志的ID
     */
    public void changeStatusToReadByCriteria(MessageLogCriteria criteria);
    
    
    /**
     * <p>获取未读消息数</p>
     * @param msgBizType
     * @param msgReceType
     * @param userId
     * @return
     */
    public int getUnreadMessageCount(String msgReceType, String userId);
}
