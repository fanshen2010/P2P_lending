package cn.com.p2p.domain.user.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

public interface PfmUserManageQuery {
	/**
	 * 
	  * 
	  * <p> 根据真实姓名查找信息* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param realName 真实姓名
	  * @return List<PfmUser>
	  *
	 */
	public List<PfmUser> findPfmUserByDepartCd(@Param("realName") String realName);

    /**
     * <p>根据真实姓名查询职位为空的所有用户集合</p>
     * @author 
     * @date 2015-10-03 :16:26
     * @param realName 用户真实姓名
     * @return 用户集合
     */
    public List<PfmUser> findPfmUserOfNoPost(@Param("realName") String realName);
    
    
    /**
     * 根据多条件查询用户Dto集合
     * @author 
     * 
     * @param criteria 查询参数集
     * @return List<PfmUserDto> 用户Dto集合
     */
    public List<PfmUserDto> findPagePfmUserDtoByCriteria(@Param("criteria")PfmUserCriteria criteria);
    
    /**
     * 根据ID查询用户Dto集合
     * @author 
     * 
     * @param id 查询参数集
     * @return PfmUserDto 用户Dto
     */
    public PfmUserDto findPfmUserById(@Param("id") String id);
    
    /**
     * 根据用户名查询用户Dto集合
     * @author 
     * 
     * @param realName 查询参数集
     * @return List<PfmUserDto> 用户Dto集合
     */
    public List<PfmUserDto> findAll(@Param("realName") String realName);
}
