/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RefundRepository.java
 * Description:       RefundRepository类定义
 *        实体 Refund的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.Refund
 *        cn.com.p2p.domain.loan.Criteria.RefundCriteria
 *        cn.com.p2p.domain.loan.repository.RefundRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-23             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.loan.entity.Refund;
import cn.com.p2p.domain.loan.criteria.RefundCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * RefundRepository定义.
 * <p>
 * 数据访问层<退款表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface RefundRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return Refund
     */
	public Refund findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<Refund>
     */
	public List<Refund> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  RefundSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<Refund> findPageAll(RefundCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  RefundSearchCriteria 检索条件
     * @return List<Refund>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Refund> findByCriteria(RefundCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  RefundSearchCriteria 检索条件
     * @return List<Refund>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Refund> findPageByCriteria(RefundCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  Refund refund
     * @return 插入成功标志
     */
	public int insert(Refund refund);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  Refund refund
     * @return 更新成功标志
     */
	public int dynamicUpdate(Refund refund);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  Refund refund
     * @return 更新成功标志
     */
	public int update(Refund refund);

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

