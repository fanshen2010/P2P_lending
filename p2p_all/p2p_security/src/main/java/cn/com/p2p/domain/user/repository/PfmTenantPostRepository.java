/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantPostRepository.java
 * Description:       PfmTenantPostRepository类定义
 *        实体 PfmTenantPost的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmTenantPost
 *        cn.com.p2p.domain.user.Criteria.PfmTenantPostCriteria
 *        cn.com.p2p.domain.user.repository.PfmTenantPostRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.criteria.PfmTenantPostCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmTenantPostRepository定义.
 * <p>
 * 数据访问层<商户职位表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmTenantPostRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String postCd
     * @return PfmTenantPost
     */
	public PfmTenantPost findOne(String postCd);

    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param tenantCd
     * @parampostCd
     * @return PfmTenantPost
     */
	public PfmTenantPost findPfmTenantPostByKeys(@Param("tenantCd") String tenantCd, @Param("postCd") String postCd);
	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmTenantPost>
     */
	public List<PfmTenantPost> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmTenantPostSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmTenantPost> findPageAll(PfmTenantPostCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmTenantPostSearchCriteria 检索条件
     * @return List<PfmTenantPost>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantPost> findByCriteria(PfmTenantPostCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmTenantPostSearchCriteria 检索条件
     * @return List<PfmTenantPost>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmTenantPost> findPageByCriteria(PfmTenantPostCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmTenantPost pfmTenantPost
     * @return 插入成功标志
     */
	public int insert(PfmTenantPost pfmTenantPost);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmTenantPost pfmTenantPost
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmTenantPost pfmTenantPost);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmTenantPost pfmTenantPost
     * @return 更新成功标志
     */
	public int update(PfmTenantPost pfmTenantPost);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param tenantCd
     * @parampostCd
     * @return 假删除成功标志
     */
	public int suspend(@Param("tenantCd") String tenantCd, @Param("postCd") String postCd);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param tenantCd
     * @parampostCd
     * @return 删除成功标志
     */
	public int delete(@Param("tenantCd") String tenantCd, @Param("postCd") String postCd);

}

