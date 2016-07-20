/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RepayDetailRepository.java
 * Description:       RepayDetailRepository类定义
 *        实体 RepayDetail的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.loan.entity.RepayDetail
 *        cn.com.p2p.domain.loan.Criteria.RepayDetailCriteria
 *        cn.com.p2p.domain.loan.repository.RepayDetailRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-09-18             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.criteria.RepayDetailRepaymentCriteria;
import cn.com.p2p.domain.loan.entity.RepayDetail;


/**
 * RepayDetailRepository定义.
 * <p>
 * 数据访问层<融资还款明细>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface RepayDetailQuery {

	/**
	 * 自定义条件查询
	 * @param criteria
	 */
	public List<RepayDetail> findByRepayDetailRepaymentCriteria(@Param("criteria") 
			RepayDetailRepaymentCriteria criteria);

}

