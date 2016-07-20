/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantRepository.java
 * Description:       PfmTenantRepository类定义
 *        实体 PfmTenant的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmTenant
 *        cn.com.p2p.domain.user.Criteria.PfmTenantCriteria
 *        cn.com.p2p.domain.user.repository.PfmTenantRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmTenant;
import cn.com.p2p.domain.user.criteria.PfmTenantCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmTenantRepository定义.
 * <p>
 * 数据访问层<商户表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmTenantRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String tenantCd
     * @return PfmTenant
     */
	public PfmTenant findOne(String tenantCd);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmTenant>
     */
	public List<PfmTenant> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmTenantSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmTenant> findPageAll(PfmTenantCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmTenantSearchCriteria 检索条件
     * @return List<PfmTenant>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenant> findByCriteria(PfmTenantCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmTenantSearchCriteria 检索条件
     * @return List<PfmTenant>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenant> findPageByCriteria(PfmTenantCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmTenant pfmTenant
     * @return 插入成功标志
     */
	public int insert(PfmTenant pfmTenant);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmTenant pfmTenant
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmTenant pfmTenant);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmTenant pfmTenant
     * @return 更新成功标志
     */
	public int update(PfmTenant pfmTenant);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param tenantCd
     * @return 假删除成功标志
     */
	public int suspend(@Param("tenantCd") String tenantCd);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param tenantCd
     * @return 删除成功标志
     */
	public int delete(@Param("tenantCd") String tenantCd);

}

