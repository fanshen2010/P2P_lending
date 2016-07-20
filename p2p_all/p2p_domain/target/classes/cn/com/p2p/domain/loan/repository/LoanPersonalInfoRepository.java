/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanPersonalInfoRepository.java
 * Description:       LoanPersonalInfoRepository类定义
 *        实体 LoanPersonalInfo的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.LoanPersonalInfo
 *        cn.com.p2p.domain.loan.Criteria.LoanPersonalInfoCriteria
 *        cn.com.p2p.domain.loan.repository.LoanPersonalInfoRepository.xml
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

import cn.com.p2p.domain.loan.entity.LoanPersonalInfo;
import cn.com.p2p.domain.loan.criteria.LoanPersonalInfoCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * LoanPersonalInfoRepository定义.
 * <p>
 * 数据访问层<融资个人信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface LoanPersonalInfoRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return LoanPersonalInfo
     */
	public LoanPersonalInfo findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String loanCode
     * @return LoanPersonalInfo
     */
	public LoanPersonalInfo findLoanPersonalInfoByLoanCode(String loanCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<LoanPersonalInfo>
     */
	public List<LoanPersonalInfo> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  LoanPersonalInfoSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<LoanPersonalInfo> findPageAll(LoanPersonalInfoCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  LoanPersonalInfoSearchCriteria 检索条件
     * @return List<LoanPersonalInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanPersonalInfo> findByCriteria(LoanPersonalInfoCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  LoanPersonalInfoSearchCriteria 检索条件
     * @return List<LoanPersonalInfo>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<LoanPersonalInfo> findPageByCriteria(LoanPersonalInfoCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  LoanPersonalInfo loanPersonalInfo
     * @return 插入成功标志
     */
	public int insert(LoanPersonalInfo loanPersonalInfo);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  LoanPersonalInfo loanPersonalInfo
     * @return 更新成功标志
     */
	public int dynamicUpdate(LoanPersonalInfo loanPersonalInfo);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  LoanPersonalInfo loanPersonalInfo
     * @return 更新成功标志
     */
	public int update(LoanPersonalInfo loanPersonalInfo);

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

