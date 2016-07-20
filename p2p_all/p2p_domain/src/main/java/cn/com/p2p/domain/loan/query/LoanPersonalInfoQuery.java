package cn.com.p2p.domain.loan.query;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.entity.LoanPersonalInfo;

/**
 * @author 
 *
 */
public interface LoanPersonalInfoQuery {
	
	/**
	 * <p>个人融资申请信息查询</p>.<br>
	 * author：<br>
	 *===================================
	 * @param loanCode 融资编号
	 * @param identity 身份证号
	 * @return 个人融资申请信息
	 */
	public LoanPersonalInfo findLatestLoanPersonalInfo(@Param("identity")String identity);
	
	 /**
     * 根据融资编号删除融资表信息
     * @param loanCode 融资编号
     * @return
     */
    public void deleteByLoanCode(@Param("loanCode")
            String loanCode);
	
}
