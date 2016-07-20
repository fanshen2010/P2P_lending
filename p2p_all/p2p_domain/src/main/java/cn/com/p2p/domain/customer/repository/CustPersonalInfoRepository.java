/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CustPersonalInfoRepository.java
 * Description:       CustPersonalInfoRepository类定义
 *        实体 CustPersonalInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.customer.entity.CustPersonalInfo
 *        cn.com.p2p.domain.customer.Criteria.CustPersonalInfoCriteria
 *        cn.com.p2p.domain.customer.repository.CustPersonalInfoRepository.xml
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

import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * CustPersonalInfoRepository定义.
 * <p>
 * 数据访问层<个人客户表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface CustPersonalInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return CustPersonalInfo
     */
	public CustPersonalInfo findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String customerCode
     * @return CustPersonalInfo
     */
	public CustPersonalInfo findCustPersonalInfoByCustomerCode(String customerCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<CustPersonalInfo>
     */
	public List<CustPersonalInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  CustPersonalInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<CustPersonalInfo> findPageAll(CustPersonalInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  CustPersonalInfoSearchCriteria 检索条件
     * @return List<CustPersonalInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustPersonalInfo> findByCriteria(CustPersonalInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  CustPersonalInfoSearchCriteria 检索条件
     * @return List<CustPersonalInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustPersonalInfo> findPageByCriteria(CustPersonalInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  CustPersonalInfo custPersonalInfo
     * @return 插入成功标志
     */
	public int insert(CustPersonalInfo custPersonalInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  CustPersonalInfo custPersonalInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(CustPersonalInfo custPersonalInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  CustPersonalInfo custPersonalInfo
     * @return 更新成功标志
     */
	public int update(CustPersonalInfo custPersonalInfo);

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

