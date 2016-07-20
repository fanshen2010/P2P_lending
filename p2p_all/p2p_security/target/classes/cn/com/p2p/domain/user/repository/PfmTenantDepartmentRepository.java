/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartmentRepository.java
 * Description:       PfmTenantDepartmentRepository类定义
 *        实体 PfmTenantDepartment的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmTenantDepartment
 *        cn.com.p2p.domain.user.Criteria.PfmTenantDepartmentCriteria
 *        cn.com.p2p.domain.user.repository.PfmTenantDepartmentRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmTenantDepartmentRepository定义.
 * <p>
 * 数据访问层<商户部门表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmTenantDepartmentRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String departmentCd
     * @return PfmTenantDepartment
     */
	public PfmTenantDepartment findOne(String departmentCd);

    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param tenantCd
     * @paramdepartmentCd
     * @return PfmTenantDepartment
     */
	public PfmTenantDepartment findPfmTenantDepartmentByKeys(@Param("tenantCd") String tenantCd, @Param("departmentCd") String departmentCd);
	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmTenantDepartment>
     */
	public List<PfmTenantDepartment> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmTenantDepartment> findPageAll(PfmTenantDepartmentCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentSearchCriteria 检索条件
     * @return List<PfmTenantDepartment>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantDepartment> findByCriteria(PfmTenantDepartmentCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentSearchCriteria 检索条件
     * @return List<PfmTenantDepartment>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantDepartment> findPageByCriteria(PfmTenantDepartmentCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmTenantDepartment pfmTenantDepartment
     * @return 插入成功标志
     */
	public int insert(PfmTenantDepartment pfmTenantDepartment);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmTenantDepartment pfmTenantDepartment
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmTenantDepartment pfmTenantDepartment);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmTenantDepartment pfmTenantDepartment
     * @return 更新成功标志
     */
	public int update(PfmTenantDepartment pfmTenantDepartment);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param tenantCd
     * @paramdepartmentCd
     * @return 假删除成功标志
     */
	public int suspend(@Param("tenantCd") String tenantCd, @Param("departmentCd") String departmentCd);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param tenantCd
     * @paramdepartmentCd
     * @return 删除成功标志
     */
	public int delete(@Param("tenantCd") String tenantCd, @Param("departmentCd") String departmentCd);

}

