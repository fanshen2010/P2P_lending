/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanEnterpriseInfoRepository.java
 * Description:       LoanEnterpriseInfoRepository类定义
 *        实体 LoanEnterpriseInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.LoanEnterpriseInfo
 *        cn.com.p2p.domain.loan.Criteria.LoanEnterpriseInfoCriteria
 *        cn.com.p2p.domain.loan.repository.LoanEnterpriseInfoRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-07             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.loan.entity.LoanEnterpriseInfo;
import cn.com.p2p.domain.loan.criteria.LoanEnterpriseInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * LoanEnterpriseInfoRepository定义.
 * <p>
 * 数据访问层<融资企业信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface LoanEnterpriseInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return LoanEnterpriseInfo
     */
	public LoanEnterpriseInfo findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String loanCode
     * @return LoanEnterpriseInfo
     */
	public LoanEnterpriseInfo findLoanEnterpriseInfoByLoanCode(String loanCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<LoanEnterpriseInfo>
     */
	public List<LoanEnterpriseInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  LoanEnterpriseInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<LoanEnterpriseInfo> findPageAll(LoanEnterpriseInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  LoanEnterpriseInfoSearchCriteria 检索条件
     * @return List<LoanEnterpriseInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanEnterpriseInfo> findByCriteria(LoanEnterpriseInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  LoanEnterpriseInfoSearchCriteria 检索条件
     * @return List<LoanEnterpriseInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanEnterpriseInfo> findPageByCriteria(LoanEnterpriseInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  LoanEnterpriseInfo loanEnterpriseInfo
     * @return 插入成功标志
     */
	public int insert(LoanEnterpriseInfo loanEnterpriseInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  LoanEnterpriseInfo loanEnterpriseInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(LoanEnterpriseInfo loanEnterpriseInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  LoanEnterpriseInfo loanEnterpriseInfo
     * @return 更新成功标志
     */
	public int update(LoanEnterpriseInfo loanEnterpriseInfo);

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

