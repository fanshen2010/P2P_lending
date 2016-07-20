/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmPostRoleRepository.java
 * Description:       PfmPostRoleRepository类定义
 *        实体 PfmPostRole的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmPostRole
 *        cn.com.p2p.domain.user.Criteria.PfmPostRoleCriteria
 *        cn.com.p2p.domain.user.repository.PfmPostRoleRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmPostRole;
import cn.com.p2p.domain.user.criteria.PfmPostRoleCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmPostRoleRepository定义.
 * <p>
 * 数据访问层<职位与角色关联表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmPostRoleRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return PfmPostRole
     */
	public PfmPostRole findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmPostRole>
     */
	public List<PfmPostRole> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmPostRoleSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmPostRole> findPageAll(PfmPostRoleCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmPostRoleSearchCriteria 检索条件
     * @return List<PfmPostRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmPostRole> findByCriteria(PfmPostRoleCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmPostRoleSearchCriteria 检索条件
     * @return List<PfmPostRole>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmPostRole> findPageByCriteria(PfmPostRoleCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmPostRole pfmPostRole
     * @return 插入成功标志
     */
	public int insert(PfmPostRole pfmPostRole);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmPostRole pfmPostRole
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmPostRole pfmPostRole);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmPostRole pfmPostRole
     * @return 更新成功标志
     */
	public int update(PfmPostRole pfmPostRole);

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
	
    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param id
     * @return 删除成功标志
     */
	public int deleteByRoleId(@Param("roleId") String roleId);

}

