package cn.com.p2p.message.service;

import java.util.List;

import cn.com.p2p.domain.message.criteria.MessageManagementCriteria;
import cn.com.p2p.domain.message.criteria.MessageTempleteCriteria;
import cn.com.p2p.domain.message.entity.MessageTemplete;

public interface MessageTempleteManageService {

    /**
	 * <p>
     * 短信通道配置信息取得的对外接口，通过商户信息取得通道信息。
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * SmsSupportService smsSupportService;
     * <br>
     *   通过商户信息取得通道信息
     *   SmsSupportService.getSmsChanelList(tenantCd);
     * <br>
     * </pre>
     * 
     * 
     * @param tenantCd  商户
     * 
     */
	 public List<MessageTemplete> getMessageTempList(MessageTempleteCriteria criteria);
	 
    /**
	 * <p>
     * 短信通道配置管理服务，核心是管理短信通道信息，添加短信通道功能
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * SmsManageService smsManageService;
     * <br>
     *   通过用户信息业务类别CD进行融资申请
     *   smsManageService.getSmsChanelList(tenantCd);
     * <br>
     * </pre>
     * 
     * 
     * @param id  主键
     * 
     */
	 public void stopMessageTemp(String id);
	 
    /**
	 * <p>
     * 短信通道配置管理服务，核心是管理短信通道信息，添加短信通道功能
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * SmsManageService smsManageService;
     * <br>
     *   通过用户信息业务类别CD进行融资申请
     *   smsManageService.getSmsChanelList(tenantCd);
     * <br>
     * </pre>
     * 
     * 
     * @param id  主键
     * 
     */
	 public void startMessageTemp(String id);
	 
	/**
	 * <p>
     * 短信管理和消息管理列表页面
     *
     * <pre>
     * 通过spring依赖注入方式实例化短信通道配置管理服务
     * @Autowired
     * SmsSupportService smsSupportService;
     * <br>
     *   通过商户信息取得通道信息
     *   SmsSupportService.getSmsChanelList(tenantCd);
     * <br>
     * </pre>
     * 
     * 
     * @param tenantCd  商户
     * 
     */
	 public List<MessageTemplete> getMessageTempLists(MessageManagementCriteria criteria);
	 
	 /**
		 * <p>
	     * 短信管理和消息管理设置页面
	     *
	     * <pre>
	     * 通过spring依赖注入方式实例化短信通道配置管理服务
	     * @Autowired
	     * SmsSupportService smsSupportService;
	     * <br>
	     *   通过商户信息取得通道信息
	     *   SmsSupportService.getSmsChanelList(tenantCd);
	     * <br>
	     * </pre>
	     * 
	     * 
	     * @param tenantCd  商户
	     * 
	     */
		 public MessageTemplete getMessageTempById(String id);
}
