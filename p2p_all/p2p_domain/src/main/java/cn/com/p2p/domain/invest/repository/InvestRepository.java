/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestRepository.java
 * Description:       InvestRepository类定义
 *        实体 Invest的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.invest.entity.Invest
 *        cn.com.p2p.domain.invest.Criteria.InvestCriteria
 *        cn.com.p2p.domain.invest.repository.InvestRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-07             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * InvestRepository定义.
 * <p>
 * 数据访问层<投资表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface InvestRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return Invest
     */
	public Invest findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<Invest>
     */
	public List<Invest> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  InvestSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<Invest> findPageAll(InvestCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  InvestSearchCriteria 检索条件
     * @return List<Invest>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Invest> findByCriteria(InvestCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  InvestSearchCriteria 检索条件
     * @return List<Invest>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Invest> findPageByCriteria(InvestCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  Invest invest
     * @return 插入成功标志
     */
	public int insert(Invest invest);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  Invest invest
     * @return 更新成功标志
     */
	public int dynamicUpdate(Invest invest);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  Invest invest
     * @return 更新成功标志
     */
	public int update(Invest invest);

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

