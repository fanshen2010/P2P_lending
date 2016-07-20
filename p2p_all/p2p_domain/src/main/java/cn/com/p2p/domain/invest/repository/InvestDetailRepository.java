/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestDetailRepository.java
 * Description:       InvestDetailRepository类定义
 *        实体 InvestDetail的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.invest.entity.InvestDetail
 *        cn.com.p2p.domain.invest.Criteria.InvestDetailCriteria
 *        cn.com.p2p.domain.invest.repository.InvestDetailRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-24             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * InvestDetailRepository定义.
 * <p>
 * 数据访问层<投资明细表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface InvestDetailRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return InvestDetail
     */
	public InvestDetail findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String investDetailCode
     * @return InvestDetail
     */
	public InvestDetail findInvestDetailByInvestDetailCode(String investDetailCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<InvestDetail>
     */
	public List<InvestDetail> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  InvestDetailSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<InvestDetail> findPageAll(InvestDetailCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  InvestDetailSearchCriteria 检索条件
     * @return List<InvestDetail>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<InvestDetail> findByCriteria(InvestDetailCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  InvestDetailSearchCriteria 检索条件
     * @return List<InvestDetail>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<InvestDetail> findPageByCriteria(InvestDetailCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  InvestDetail investDetail
     * @return 插入成功标志
     */
	public int insert(InvestDetail investDetail);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  InvestDetail investDetail
     * @return 更新成功标志
     */
	public int dynamicUpdate(InvestDetail investDetail);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  InvestDetail investDetail
     * @return 更新成功标志
     */
	public int update(InvestDetail investDetail);

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

