/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CiccAccountRepository.java
 * Description:       CiccAccountRepository类定义
 *        实体 CiccAccount的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.payment.entity.CiccAccount
 *        cn.com.p2p.domain.payment.Criteria.CiccAccountCriteria
 *        cn.com.p2p.domain.payment.repository.CiccAccountRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-14             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.payment.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.payment.criteria.CiccAccountCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * CiccAccountRepository定义.
 * <p>
 * 数据访问层<中金账户表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface CiccAccountRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return CiccAccount
     */
	public CiccAccount findOne(String id);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<CiccAccount>
     */
	public List<CiccAccount> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  CiccAccountSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<CiccAccount> findPageAll(CiccAccountCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  CiccAccountSearchCriteria 检索条件
     * @return List<CiccAccount>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CiccAccount> findByCriteria(CiccAccountCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  CiccAccountSearchCriteria 检索条件
     * @return List<CiccAccount>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<CiccAccount> findPageByCriteria(CiccAccountCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  CiccAccount ciccAccount
     * @return 插入成功标志
     */
	public int insert(CiccAccount ciccAccount);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  CiccAccount ciccAccount
     * @return 更新成功标志
     */
	public int dynamicUpdate(CiccAccount ciccAccount);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  CiccAccount ciccAccount
     * @return 更新成功标志
     */
	public int update(CiccAccount ciccAccount);

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

