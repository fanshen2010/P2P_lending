/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanInfoRepository.java
 * Description:       LoanInfoRepository类定义
 *        实体 LoanInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.LoanInfo
 *        cn.com.p2p.domain.loan.Criteria.LoanInfoCriteria
 *        cn.com.p2p.domain.loan.repository.LoanInfoRepository.xml
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

import cn.com.p2p.domain.loan.entity.LoanInfo;
import cn.com.p2p.domain.loan.criteria.LoanInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * LoanInfoRepository定义.
 * <p>
 * 数据访问层<融资信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface LoanInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return LoanInfo
     */
	public LoanInfo findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String loanCode
     * @return LoanInfo
     */
	public LoanInfo findLoanInfoByLoanCode(String loanCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<LoanInfo>
     */
	public List<LoanInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  LoanInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<LoanInfo> findPageAll(LoanInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  LoanInfoSearchCriteria 检索条件
     * @return List<LoanInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanInfo> findByCriteria(LoanInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  LoanInfoSearchCriteria 检索条件
     * @return List<LoanInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanInfo> findPageByCriteria(LoanInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  LoanInfo loanInfo
     * @return 插入成功标志
     */
	public int insert(LoanInfo loanInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  LoanInfo loanInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(LoanInfo loanInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  LoanInfo loanInfo
     * @return 更新成功标志
     */
	public int update(LoanInfo loanInfo);

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

