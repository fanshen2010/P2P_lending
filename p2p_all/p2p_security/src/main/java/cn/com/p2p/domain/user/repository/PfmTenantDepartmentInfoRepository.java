/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartmentInfoRepository.java
 * Description:       PfmTenantDepartmentInfoRepository类定义
 *        实体 PfmTenantDepartmentInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo
 *        cn.com.p2p.domain.user.Criteria.PfmTenantDepartmentInfoCriteria
 *        cn.com.p2p.domain.user.repository.PfmTenantDepartmentInfoRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmTenantDepartmentInfo;
import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmTenantDepartmentInfoRepository定义.
 * <p>
 * 数据访问层<商户部门信息表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmTenantDepartmentInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String departmentCd
     * @return PfmTenantDepartmentInfo
     */
	public PfmTenantDepartmentInfo findOne(String departmentCd);

    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param tenantCd
     * @paramdepartmentCd
     * @return PfmTenantDepartmentInfo
     */
	public PfmTenantDepartmentInfo findPfmTenantDepartmentInfoByKeys(@Param("tenantCd") String tenantCd, @Param("departmentCd") String departmentCd);
	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmTenantDepartmentInfo>
     */
	public List<PfmTenantDepartmentInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmTenantDepartmentInfo> findPageAll(PfmTenantDepartmentInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentInfoSearchCriteria 检索条件
     * @return List<PfmTenantDepartmentInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantDepartmentInfo> findByCriteria(PfmTenantDepartmentInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmTenantDepartmentInfoSearchCriteria 检索条件
     * @return List<PfmTenantDepartmentInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantDepartmentInfo> findPageByCriteria(PfmTenantDepartmentInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmTenantDepartmentInfo pfmTenantDepartmentInfo
     * @return 插入成功标志
     */
	public int insert(PfmTenantDepartmentInfo pfmTenantDepartmentInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmTenantDepartmentInfo pfmTenantDepartmentInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmTenantDepartmentInfo pfmTenantDepartmentInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmTenantDepartmentInfo pfmTenantDepartmentInfo
     * @return 更新成功标志
     */
	public int update(PfmTenantDepartmentInfo pfmTenantDepartmentInfo);

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

