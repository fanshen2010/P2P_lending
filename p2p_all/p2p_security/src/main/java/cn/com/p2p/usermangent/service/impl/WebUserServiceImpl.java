package cn.com.p2p.usermangent.service.impl;


import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.message.entity.MessageReceiver;
import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.domain.message.repository.MessageReceiverRepository;
import cn.com.p2p.domain.message.repository.MessageTempleteRepository;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.exception.SystemException;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.message.constants.MessageReceiverType;
import cn.com.p2p.message.constants.MessageType;
import cn.com.p2p.message.internal.send.SendInternalMessage;
import cn.com.p2p.usermangent.service.WebUserService;

/**
 * 
 * 业务人员管理接口实现类
 * 
 * @author 
 *
 */
@Service
public class WebUserServiceImpl implements WebUserService {
	
	private static final Log logger = LogFactory.getLog(WebUserServiceImpl.class);

	/** 前台用户服务组件  */
    @Autowired
    private WebUserRepository webUserRepository;
	
	/** 消息发送服务组件 */
	@Autowired
	private SendInternalMessage sendInternalMessage;
	
	@Autowired
	private MessageTempleteRepository messageTempleteRepository;
	
	@Autowired
	private MessageReceiverRepository messageReceiverRepository;
	
    /**
     * <p>保存用户</p>
     * @author 
     * @date 2015-10-16 16:43
     * @param webUser 前台用户实体
     * @return int 保存成功的标志
     * @description 保存用户时分为企业：1、个人：0
     */
    @Override
    public int save(WebUser webUser) {
        //TODO 手动设置ID 注意修改
        webUser.setId(StringUtils.getUUID());
        // 注册用户时，设置为激活状态
        webUser.setActive(1L);
        // 设置为可用状态
        webUser.setValidFlag("1");
        // 设置密钥
        webUser.setPasswordSalt(StringUtils.getRandomSalt());
        try {
            // 加密密码
            webUser.setPassword(DESPlus.byteArr2HexStr((webUser.getPassword() + webUser.getPasswordSalt()).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存数据
        return webUserRepository.insert(webUser);
    }

    
	/**
	 * <p>发前台站内消息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param toUserId
	 * @param msgBizType
	 * @param msgParam
	 * @return
	 */
	@Override
	public boolean sendInternalFrontMessage(String toUserId,String msgBizType, String... msgParam){
		try{
			return sendInternalMessage.send2FrontUser(toUserId, msgBizType, msgParam);
		}catch(SystemException e){
			logger.debug(e.getMessage());
			return false;
		}
	}
	
	/**
	 * <p>发后台站内消息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param msgBizType
	 * @param msgParam
	 * @return
	 */
	@Override
	public boolean sendInternalBackMessage(String msgBizType, String... msgParam){
		try{
			String tenantCd ="001";
			return sendInternalMessage.send2BackUser(tenantCd, msgBizType, msgParam);
		}catch(SystemException e){
			logger.debug(e.getMessage());
			return false;
		}
	}

    /**
     * <p>检查用户名是否存在</p>
     * @author 
     * @date 2015-10-23 14:43
     * @param login 用户名
     * @return boolean 是否存在的标志
     * @description 无
     */
    @Override
    public boolean checkUserLogin(String login) {
        boolean isExsit = false;   // 默认不存在
        // 设置查询条件
        WebUserCriteria criteria = new WebUserCriteria();
        criteria.setLogin(login, Operator.equal);
        List<WebUser> webUsers = webUserRepository.findByCriteria(criteria);
        // 如果查询到的实体集合包含元素，则通过校验
        if(webUsers != null && !webUsers.isEmpty()){
            isExsit = true;
        }
        return isExsit;
    }

    /**
     * <p>条件查询前台用户集合</p>
     * @author 
     * @date 2015-10-23 14:43
     * @param criteria 查询条件实体
     * @return List<WebUser> 返回查询到的所有用户实体
     * @description 无
     */
    @Override
    public List<WebUser> findByCriteria(WebUserCriteria criteria) {
        return webUserRepository.findByCriteria(criteria);
    }

    /**
     * <p>动态的更新前台用户实体</p>
     * @author 
     * @date 2015-10-23 17:43
     * @param webUser 待更新的用户实体
     * @return int 更新成功的条数
     * @description 待更新的属性值为空("",null)时，则忽略此属性的更新操作
     */
    @Override
    public int dynamicUpdate(WebUser webUser) {
        return webUserRepository.dynamicUpdate(webUser);
        
        
    }

    /**
     * <p>根据主键，获取前台用户实体</p>
     * @author 
     * @date 2015-10-23 17:47
     * @param id 用户实体ID
     * @return WebUser 查询到的用户实体
     * @description 无
     */
    @Override
    public WebUser findOne(String id) {
        return webUserRepository.findOne(id);
    }
    
    /**
     * <p>校验是否符合原密码</p>.<br>
     * author：<br>
     *===================================
     * @param password
     * @param userId
     * @return
     */
    @Override
    public boolean checkOldPassword(String password,String userId) throws Exception{
    	boolean output = false; 
    	WebUser webUser = this.findOne(userId);
    	String oldPassword = webUser.getPassword();
    	password = DESPlus.byteArr2HexStr((password + webUser.getPasswordSalt()).getBytes());
    	if(oldPassword.equals(password)){
    		output = true;
        }
    	return output;
    }
    
    /**
     * <p>校验是否符合原邮箱</p>.<br>
     * author：<br>
     *===================================
     * @param mail
     * @param userId
     * @return
     */
    @Override
    public boolean checkOldMail(String mail,String userId){
    	boolean output = false; 
    	WebUser webUser = this.findOne(userId);
    	String oldMail = webUser.getEmail();
    	if(oldMail.equals(mail)){
    		output = true;
        }
    	return output;
    }
    
    /**
     * <p>校验新手机是否是唯一的</p>.<br>
     * author：<br>
     *===================================
     * @param newCellphone
     * @param userId
     * @return
     */
    @Override
    public boolean checkUniqueCellphone(String newCellphone,String userId) {
    	boolean output = false;
    	WebUserCriteria criteria = new WebUserCriteria();
		criteria.setCelphone(newCellphone, Operator.equal);
		List<WebUser> webUserList = this.findByCriteria(criteria);
		if(webUserList==null || webUserList.isEmpty() || userId.equals(webUserList.get(0).getId())){
			output = true;
		}
    	return output;
    }
    
    /**
     * <p>校验新邮箱是否是唯一的</p>.<br>
     * author：<br>
     *===================================
     * @param newMail
     * @param userId
     * @return
     */
    @Override
    public boolean checkUniqueMail(String newMail,String userId) {
    	boolean output = false; 
    	WebUserCriteria criteria = new WebUserCriteria();
    	criteria.setEmail(newMail, Operator.equal);
		List<WebUser> webUserList = this.findByCriteria(criteria);
		if(webUserList==null || webUserList.isEmpty() || userId.equals(webUserList.get(0).getId())){
			output = true;
		}
    	return output;
    }


	
	@Override
	public List<WebUser> getWebUserAll(WebUserCriteria criteria) {
		return webUserRepository.findPageByCriteria(criteria);
	}



}
