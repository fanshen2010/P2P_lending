package cn.com.p2p.usermangent.service;

import java.util.List;

import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

/**
 * 
 * 业务人员管理接口
 * 
 * 
 * @author 
 *
 */
public interface BusinessStaffService {
	
	/**
	 * 根据条件类查询业务人员列表(分页)
	 * @author 
	 * 
	 * @param criteria 条件参数类
	 * @return List<PfmUser> 业务人员列表
	 */
	public List<PfmUser> getPfmUserPageByCriteria(PfmUserCriteria criteria);
	
	/**
	 * 根据条件类查询业务人员Dto列表(分页)   
	 * @author 
	 * 
	 * @param criteria 条件参数类
	 * @return List<PfmUserDto> 业务人员Dto列表
	 */
	public List<PfmUserDto> getPfmUserDtoPageByCriteria(PfmUserCriteria criteria);
	
	/**
	 * 根据条件类查询业务人员列表
	 * @author 
	 * 
	 * @param criteria 条件参数类
	 * @return List<PfmUser> 业务人员列表
	 */
	public List<PfmUser> getPfmUserByCriteria(PfmUserCriteria criteria);
	
	
	/**
	 * 根据ID（主键）查询业务人员实体
	 * @author 
	 * 
	 * @param id 查询参数
	 * @return PfmUser 业务人员实体
	 */
	public PfmUser getPfmUserById(String id);
	
	/**
	 * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
	 * @author 
	 * 
	 * @param pfmUser 更新实体
	 * @return 更新成功标志
	 */
	public int dynamicUpdatePfmUser(PfmUser pfmUser);
	
	/**
	 * 根据Id进行Delete删除
	 * @author 
	 * 
	 * @param id 删除Id
	 * @return 删除成功标志
	 */
	public int deletePfmUserById(String id);
	
	/**
	 * 插入一条新纪录，正确插入时返回值为 1
	 * @author 
	 * 
	 * @param pfmUser 插入实体
	 * @return 插入成功标志(1为成功，2为用户名冲突，0为异常失败)
	 */
	public int insertPfmUser(PfmUser pfmUser);
	
	/**
	 * 业务人员管理 主键用户名验证
	 * @author 
	 * 
	 * @param userName 需要验证用户名
	 * @return 验证结果 存在 返回 true
	 */
	public boolean pfmUserNameCheck(String userName);
	
	/**
	 * 
	  * 
	  * <p>根据真是姓名查找 * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param realName 真实姓名
	  *
	 */
	public List<PfmUser> findPfmUserByRealName(String realName);
	
	/**
	 * 查询用户的全部信息包含部门名及职位名
	 * 
	 * @param id 用户ID
	 * @return PfmUserDto
	 */
	public PfmUserDto findPfmUserInfo(String id);
	
	/**
	 * 业务人员登录密码校验
	 * @author 
	 * 
	 * @param userName 登录用户名
	 * @param passWord 登录密码
	 * 
	 * @return boolean 校验结果
	 */
	public boolean pfmUserPassWordCheck(String userName,String passWord);

	/**
	 * 查询全部用户
	 * 
	 * @return PfmUser
	 */
	public List<PfmUser> findAllUser();

	public List<PfmUser> getUserByRealName(String realName);
	
}
