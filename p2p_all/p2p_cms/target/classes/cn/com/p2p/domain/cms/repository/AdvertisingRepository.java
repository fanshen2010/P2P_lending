/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        AdvertisingRepository.java
 * Description:       AdvertisingRepository类定义
 *        实体 Advertising的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.cms.entity.Advertising
 *        cn.com.p2p.domain.cms.Criteria.AdvertisingCriteria
 *        cn.com.p2p.domain.cms.repository.AdvertisingRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-01             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.cms.entity.Advertising;
import cn.com.p2p.domain.cms.criteria.AdvertisingCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * AdvertisingRepository定义.
 * <p>
 * 数据访问层<广告版位>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface AdvertisingRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return Advertising
     */
	public Advertising findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<Advertising>
     */
	public List<Advertising> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  AdvertisingSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<Advertising> findPageAll(AdvertisingCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  AdvertisingSearchCriteria 检索条件
     * @return List<Advertising>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Advertising> findByCriteria(AdvertisingCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  AdvertisingSearchCriteria 检索条件
     * @return List<Advertising>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Advertising> findPageByCriteria(AdvertisingCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  Advertising advertising
     * @return 插入成功标志
     */
	public int insert(Advertising advertising);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  Advertising advertising
     * @return 更新成功标志
     */
	public int dynamicUpdate(Advertising advertising);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  Advertising advertising
     * @return 更新成功标志
     */
	public int update(Advertising advertising);

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

