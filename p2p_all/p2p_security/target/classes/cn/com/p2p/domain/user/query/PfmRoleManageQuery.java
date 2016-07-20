package cn.com.p2p.domain.user.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.entity.PfmPostRole;
import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.entity.PfmUserRole;



/**
 * 业务相关FmRoleMenuQuery定义.
 * <p>
 * 
 * 
 * @author 
 */
public interface PfmRoleManageQuery {
    /**
     * 左侧菜单信息查询
     * <p>
     * @param  pstrId  id
     * @return  图片信息
     */
    public List<PfmMenu> findLeftPfmMenu(@Param("roleIdList") List<Object> roleIdList);
    
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String userId
     * @return PfmUserRole
     */
	public List<PfmUserRole> findPfmUserRoleByUserId(String userId); 
	
    /**
     * 通过用户职位查询用户相对应的权限
     * <p>
     * @param  String userId
     * @return PfmUserRole
     */
	public List<PfmPostRole> findPfmUserRoleByPost(@Param("postId") String postId);
	
    
    /**
     * <p>根据职位ID，查询职位对应的所有的角色</p>
     * @author 
     * @date 2015-09-27 16:22
     * @param String postCd
     * @return List<PfmRole> 职位对应的所有的角色集合
     */
    public List<PfmRole> findPostRole(@Param("postCd") String postCd);
    
    /**
     * <p>根据角色ID，查询角色对应的所有的菜单</p>
     * @author 
     * @date 2015-09-30 11:36
     * @param String roleId
     * @return List<PfmMenu> 角色对应的所有的菜单集合，包括，增删改查
     */
    public List<PfmMenu> findRoleMenu(@Param("roleId") String roleId);
}
