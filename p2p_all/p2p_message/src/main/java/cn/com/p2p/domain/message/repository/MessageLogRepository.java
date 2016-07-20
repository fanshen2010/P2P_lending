/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageLogRepository.java
 * Description:       MessageLogRepository类定义
 *        实体 MessageLog的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.message.entity.MessageLog
 *        cn.com.p2p.domain.message.Criteria.MessageLogCriteria
 *        cn.com.p2p.domain.message.repository.MessageLogRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-24             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * MessageLogRepository定义.
 * <p>
 * 数据访问层<消息日志>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface MessageLogRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return MessageLog
     */
	public MessageLog findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<MessageLog>
     */
	public List<MessageLog> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  MessageLogSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<MessageLog> findPageAll(MessageLogCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  MessageLogSearchCriteria 检索条件
     * @return List<MessageLog>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageLog> findByCriteria(MessageLogCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  MessageLogSearchCriteria 检索条件
     * @return List<MessageLog>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageLog> findPageByCriteria(MessageLogCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  MessageLog messageLog
     * @return 插入成功标志
     */
	public int insert(MessageLog messageLog);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  MessageLog messageLog
     * @return 更新成功标志
     */
	public int dynamicUpdate(MessageLog messageLog);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  MessageLog messageLog
     * @return 更新成功标志
     */
	public int update(MessageLog messageLog);

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
     * 按业务键进行查询，返回集合记录
     * <p>
     * @param msgType
     * @paramtenantCd
     * @return List<MessageLog>
     */
	public List<MessageLog> findMessageLogByBizKeys(@Param("msgType") String msgType, @Param("tenantCd") String tenantCd);
	
    /**
     * 按业务键进行查询，返回集合记录
     * <p>
     * @param msgType
     * @paramtenantCd
     * @return List<MessageLog>
     */
	public List<MessageLog> findMessageLogByUser(@Param("msgType") String msgType, @Param("userId") String userId, @Param("tenantCd") String tenantCd);


}

