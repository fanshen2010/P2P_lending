/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CustEnterpriseInfoRepository.java
 * Description:       CustEnterpriseInfoRepository类定义
 *        实体 CustEnterpriseInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.customer.entity.CustEnterpriseInfo
 *        cn.com.p2p.domain.customer.Criteria.CustEnterpriseInfoCriteria
 *        cn.com.p2p.domain.customer.repository.CustEnterpriseInfoRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-20             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.customer.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * CustEnterpriseInfoRepository定义.
 * <p>
 * 数据访问层<企业客户表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface CustEnterpriseInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return CustEnterpriseInfo
     */
	public CustEnterpriseInfo findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String customerCode
     * @return CustEnterpriseInfo
     */
	public CustEnterpriseInfo findCustEnterpriseInfoByCustomerCode(String customerCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<CustEnterpriseInfo>
     */
	public List<CustEnterpriseInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  CustEnterpriseInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<CustEnterpriseInfo> findPageAll(CustEnterpriseInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  CustEnterpriseInfoSearchCriteria 检索条件
     * @return List<CustEnterpriseInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustEnterpriseInfo> findByCriteria(CustEnterpriseInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  CustEnterpriseInfoSearchCriteria 检索条件
     * @return List<CustEnterpriseInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustEnterpriseInfo> findPageByCriteria(CustEnterpriseInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  CustEnterpriseInfo custEnterpriseInfo
     * @return 插入成功标志
     */
	public int insert(CustEnterpriseInfo custEnterpriseInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  CustEnterpriseInfo custEnterpriseInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(CustEnterpriseInfo custEnterpriseInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  CustEnterpriseInfo custEnterpriseInfo
     * @return 更新成功标志
     */
	public int update(CustEnterpriseInfo custEnterpriseInfo);

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

