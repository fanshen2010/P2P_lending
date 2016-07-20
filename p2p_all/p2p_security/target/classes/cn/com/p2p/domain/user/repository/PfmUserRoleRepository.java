/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmUserRoleRepository.java
 * Description:       PfmUserRoleRepository类定义
 *        实体 PfmUserRole的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmUserRole
 *        cn.com.p2p.domain.user.Criteria.PfmUserRoleCriteria
 *        cn.com.p2p.domain.user.repository.PfmUserRoleRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-03             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.user.entity.PfmUserRole;
import cn.com.p2p.domain.user.criteria.PfmUserRoleCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmUserRoleRepository定义.
 * <p>
 * 数据访问层<商户登录用户权限表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmUserRoleRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return PfmUserRole
     */
	public PfmUserRole findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String userId
     * @return PfmUserRole
     */
	public PfmUserRole findPfmUserRoleByUserId(String userId);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmUserRole>
     */
	public List<PfmUserRole> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmUserRoleSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmUserRole> findPageAll(PfmUserRoleCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmUserRoleSearchCriteria 检索条件
     * @return List<PfmUserRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmUserRole> findByCriteria(PfmUserRoleCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmUserRoleSearchCriteria 检索条件
     * @return List<PfmUserRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmUserRole> findPageByCriteria(PfmUserRoleCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmUserRole pfmUserRole
     * @return 插入成功标志
     */
	public int insert(PfmUserRole pfmUserRole);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmUserRole pfmUserRole
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmUserRole pfmUserRole);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmUserRole pfmUserRole
     * @return 更新成功标志
     */
	public int update(PfmUserRole pfmUserRole);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param id
     * @return 假删除成功标志
     */
	public int suspend(@Param("id") String id);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param id
     * @return 删除成功标志
     */
	public int delete(@Param("id") String id);

}

