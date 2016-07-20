package cn.com.p2p.domain.loan.query;

import org.apache.ibatis.annotations.Param;

/**
 * 业务相关LOAN_INFO定义.
 * <p>
 * 数据访问层<融资企业信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 */
public interface LoanInfoQuery {
  
    
    /**
     * 根据融资编号删除融资表信息
     * @param loanCode 融资编号
     * @return
     */
    public void deleteByLoanCode(@Param("loanCode")
            String loanCode);
    
}
