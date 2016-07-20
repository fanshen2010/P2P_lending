/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanRepository.java
 * Description:       LoanRepository类定义
 *        实体 Loan的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.Loan
 *        cn.com.p2p.domain.loan.Criteria.LoanCriteria
 *        cn.com.p2p.domain.loan.repository.LoanRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-05             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.criteria.LoanCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * LoanRepository定义.
 * <p>
 * 数据访问层<融资表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface LoanRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return Loan
     */
	public Loan findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String loanCode
     * @return Loan
     */
	public Loan findLoanByLoanCode(String loanCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<Loan>
     */
	public List<Loan> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  LoanSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<Loan> findPageAll(LoanCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  LoanSearchCriteria 检索条件
     * @return List<Loan>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Loan> findByCriteria(LoanCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  LoanSearchCriteria 检索条件
     * @return List<Loan>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<Loan> findPageByCriteria(LoanCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  Loan loan
     * @return 插入成功标志
     */
	public int insert(Loan loan);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  Loan loan
     * @return 更新成功标志
     */
	public int dynamicUpdate(Loan loan);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  Loan loan
     * @return 更新成功标志
     */
	public int update(Loan loan);

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

