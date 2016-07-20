/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PaymentLogRepository.java
 * Description:       PaymentLogRepository类定义
 *        实体 PaymentLog的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.payment.entity.PaymentLog
 *        cn.com.p2p.domain.payment.Criteria.PaymentLogCriteria
 *        cn.com.p2p.domain.payment.repository.PaymentLogRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-22             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.domain.payment.criteria.PaymentLogCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PaymentLogRepository定义.
 * <p>
 * 数据访问层<支付记录表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PaymentLogRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return PaymentLog
     */
	public PaymentLog findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PaymentLog>
     */
	public List<PaymentLog> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PaymentLogSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PaymentLog> findPageAll(PaymentLogCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PaymentLogSearchCriteria 检索条件
     * @return List<PaymentLog>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PaymentLog> findByCriteria(PaymentLogCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PaymentLogSearchCriteria 检索条件
     * @return List<PaymentLog>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PaymentLog> findPageByCriteria(PaymentLogCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PaymentLog paymentLog
     * @return 插入成功标志
     */
	public int insert(PaymentLog paymentLog);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PaymentLog paymentLog
     * @return 更新成功标志
     */
	public int dynamicUpdate(PaymentLog paymentLog);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PaymentLog paymentLog
     * @return 更新成功标志
     */
	public int update(PaymentLog paymentLog);

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

