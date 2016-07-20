package cn.com.p2p.domain.loan.query;

import org.apache.ibatis.annotations.Param;

/**
 * @author 
 *
 */
public interface LoanPersonalCountQuery {
	
    /**
     * <p>个人融资次数查询</p>.<br>
     * author：<br>
     *===================================
     * @param identity 身份证号
     * @return 融资次数
     */
    public int findLoanPersonalCount(@Param("identity") String identity);

}
