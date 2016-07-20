/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageReceiverRepository.java
 * Description:       MessageReceiverRepository类定义
 *        实体 MessageReceiver的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.message.entity.MessageReceiver
 *        cn.com.p2p.domain.message.Criteria.MessageReceiverCriteria
 *        cn.com.p2p.domain.message.repository.MessageReceiverRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-10             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.message.entity.MessageReceiver;
import cn.com.p2p.domain.message.criteria.MessageReceiverCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * MessageReceiverRepository定义.
 * <p>
 * 数据访问层<消息接收者设定表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface MessageReceiverRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return MessageReceiver
     */
	public MessageReceiver findOne(String id);


    /**
     * 按业务键进行查询，返回集合记录
     * <p>
     * @param msgId
     * @paramtenantCd
     * @return List<MessageReceiver>
     */
	public List<MessageReceiver> findMessageReceiverByBizKeys(@Param("msgId") String msgId, @Param("tenantCd") String tenantCd);
	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<MessageReceiver>
     */
	public List<MessageReceiver> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  MessageReceiverSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<MessageReceiver> findPageAll(MessageReceiverCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  MessageReceiverSearchCriteria 检索条件
     * @return List<MessageReceiver>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageReceiver> findByCriteria(MessageReceiverCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  MessageReceiverSearchCriteria 检索条件
     * @return List<MessageReceiver>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageReceiver> findPageByCriteria(MessageReceiverCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  MessageReceiver messageReceiver
     * @return 插入成功标志
     */
	public int insert(MessageReceiver messageReceiver);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  MessageReceiver messageReceiver
     * @return 更新成功标志
     */
	public int dynamicUpdate(MessageReceiver messageReceiver);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  MessageReceiver messageReceiver
     * @return 更新成功标志
     */
	public int update(MessageReceiver messageReceiver);

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

