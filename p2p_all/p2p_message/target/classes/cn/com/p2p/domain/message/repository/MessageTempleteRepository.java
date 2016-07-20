/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageTempleteRepository.java
 * Description:       MessageTempleteRepository类定义
 *        实体 MessageTemplete的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.message.entity.MessageTemplete
 *        cn.com.p2p.domain.message.Criteria.MessageTempleteCriteria
 *        cn.com.p2p.domain.message.repository.MessageTempleteRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-13             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.domain.message.criteria.MessageTempleteCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * MessageTempleteRepository定义.
 * <p>
 * 数据访问层<消息模板设定>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface MessageTempleteRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return MessageTemplete
     */
	public MessageTemplete findOne(String id);


    /**
     * 按业务键进行查询，返回集合记录
     * <p>
     * @param msgType
     * @parammsgBizType
     * @parammsgReceiveType
     * @paramvalidFlag
     * @paramtenantCd
     * @return List<MessageTemplete>
     */
	public List<MessageTemplete> findMessageTempleteByBizKeys(@Param("msgType") String msgType, @Param("msgBizType") String msgBizType, @Param("msgReceiveType") String msgReceiveType, @Param("validFlag") String validFlag, @Param("tenantCd") String tenantCd);
	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<MessageTemplete>
     */
	public List<MessageTemplete> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  MessageTempleteSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<MessageTemplete> findPageAll(MessageTempleteCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  MessageTempleteSearchCriteria 检索条件
     * @return List<MessageTemplete>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageTemplete> findByCriteria(MessageTempleteCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  MessageTempleteSearchCriteria 检索条件
     * @return List<MessageTemplete>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<MessageTemplete> findPageByCriteria(MessageTempleteCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  MessageTemplete messageTemplete
     * @return 插入成功标志
     */
	public int insert(MessageTemplete messageTemplete);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  MessageTemplete messageTemplete
     * @return 更新成功标志
     */
	public int dynamicUpdate(MessageTemplete messageTemplete);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  MessageTemplete messageTemplete
     * @return 更新成功标志
     */
	public int update(MessageTemplete messageTemplete);

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

