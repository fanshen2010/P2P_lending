/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CustInvestRepository.java
 * Description:       CustInvestRepository类定义
 *        实体 CustInvest的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.customer.entity.CustInvest
 *        cn.com.p2p.domain.customer.Criteria.CustInvestCriteria
 *        cn.com.p2p.domain.customer.repository.CustInvestRepository.xml
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

import cn.com.p2p.domain.customer.entity.CustInvest;
import cn.com.p2p.domain.customer.criteria.CustInvestCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * CustInvestRepository定义.
 * <p>
 * 数据访问层<投资客户表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface CustInvestRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return CustInvest
     */
	public CustInvest findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<CustInvest>
     */
	public List<CustInvest> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  CustInvestSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<CustInvest> findPageAll(CustInvestCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  CustInvestSearchCriteria 检索条件
     * @return List<CustInvest>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustInvest> findByCriteria(CustInvestCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  CustInvestSearchCriteria 检索条件
     * @return List<CustInvest>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CustInvest> findPageByCriteria(CustInvestCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  CustInvest custInvest
     * @return 插入成功标志
     */
	public int insert(CustInvest custInvest);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  CustInvest custInvest
     * @return 更新成功标志
     */
	public int dynamicUpdate(CustInvest custInvest);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  CustInvest custInvest
     * @return 更新成功标志
     */
	public int update(CustInvest custInvest);

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

