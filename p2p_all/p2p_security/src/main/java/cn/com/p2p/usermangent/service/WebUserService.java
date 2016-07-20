package cn.com.p2p.usermangent.service;



import java.util.List;

import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;

/**
 * 
 * 业务人员管理接口
 * 
 * 
 * @author 
 *
 */
public interface WebUserService {
	
	/**
     * <p>保存用户</p>
     * @author 
     * @date 2015-10-16 16:43
     * @param webUser 前台用户实体
     * @return int 保存成功的标志
     * @description 保存用户时分为企业：1、个人：0
     */
    int save(WebUser webUser);

	/**
	 * <p>发前台站内消息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param toUserId
	 * @param msgBizType
	 * @param msgParam
	 * @return
	 */
	public boolean sendInternalFrontMessage(String toUserId,String msgBizType, String... msgParam);
	
	/**
	 * <p>发后台站内消息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param msgBizType
	 * @param msgParam （除项目名，融资编号之外的其他参数）
	 * @return
	 */
	public boolean sendInternalBackMessage(String msgBizType, String... msgParam);

    /**
     * <p>检查用户名是否存在</p>
     * @author 
     * @date 2015-10-23 14:43
     * @param login 用户名
     * @return boolean 是否存在的标志
     * @description 无
     */
    boolean checkUserLogin(String login);

    /**
     * <p>条件查询前台用户集合</p>
     * @author 
     * @date 2015-10-23 14:43
     * @param criteria 查询条件实体
     * @return List<WebUser> 返回查询到的所有用户实体
     * @description 无
     */
    List<WebUser> findByCriteria(WebUserCriteria criteria);

    /**
     * <p>动态的更新前台用户实体</p>
     * @author 
     * @date 2015-10-23 17:43
     * @param webUser 待更新的用户实体
     * @return int 更新成功的条数
     * @description 待更新的属性值为空("",null)时，则忽略此属性的更新操作
     */
    int dynamicUpdate(WebUser webUser);

    /**
     * <p>根据主键，获取前台用户实体</p>
     * @author 
     * @date 2015-10-23 17:45
     * @param id 用户实体ID
     * @return WebUser 查询到的用户实体
     * @description 无
     */
    WebUser findOne(String id);
    
    
    /**
     * <p>校验是否符合原密码</p>.<br>
     * author：<br>
     *===================================
     * @param oldpassword
     * @param userId
     * @return
     */
    public boolean checkOldPassword(String password,String userId) throws Exception;
    
    /**
     * <p>校验新手机是否是唯一的</p>.<br>
     * author：<br>
     *===================================
     * @param newCellphone
     * @param userId
     * @return
     */
    public boolean checkUniqueCellphone(String newCellphone,String userId);
    
    /**
     * <p>校验新邮箱是否是唯一的</p>.<br>
     * author：<br>
     *===================================
     * @param newMail
     * @param userId
     * @return
     */
    public boolean checkUniqueMail(String newMail,String userId);
    
    /**
     * <p>校验是否符合原邮箱</p>.<br>
     * author：<br>
     *===================================
     * @param mail
     * @param userId
     * @return
     */
    public boolean checkOldMail(String mail,String userId);
    
    /**
     * 
     * <p>用户管理分页</p>.<br>
     * author：shaolichao<br>
     *===================================
     * @param criteria
     * @return
     */
    public List<WebUser> getWebUserAll(WebUserCriteria criteria);
    
}
