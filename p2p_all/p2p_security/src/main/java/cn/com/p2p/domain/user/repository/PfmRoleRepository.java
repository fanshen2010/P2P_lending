/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRoleRepository.java
 * Description:       PfmRoleRepository类定义
 *        实体 PfmRole的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmRole
 *        cn.com.p2p.domain.user.Criteria.PfmRoleCriteria
 *        cn.com.p2p.domain.user.repository.PfmRoleRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.criteria.PfmRoleCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmRoleRepository定义.
 * <p>
 * 数据访问层<商户权限表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmRoleRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String roleId
     * @return PfmRole
     */
	public PfmRole findOne(String roleId);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmRole>
     */
	public List<PfmRole> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmRoleSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmRole> findPageAll(PfmRoleCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmRoleSearchCriteria 检索条件
     * @return List<PfmRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmRole> findByCriteria(PfmRoleCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmRoleSearchCriteria 检索条件
     * @return List<PfmRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmRole> findPageByCriteria(PfmRoleCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmRole pfmRole
     * @return 插入成功标志
     */
	public int insert(PfmRole pfmRole);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmRole pfmRole
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmRole pfmRole);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmRole pfmRole
     * @return 更新成功标志
     */
	public int update(PfmRole pfmRole);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param roleId
     * @return 假删除成功标志
     */
	public int suspend(@Param("roleId") String roleId);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param roleId
     * @return 删除成功标志
     */
	public int delete(@Param("roleId") String roleId);

}

