package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.entity.LoanEnterpriseInfo;

/**
 * 业务相关LoanEnterpriseInfoQuery定义.
 * <p>
 * 数据访问层<融资企业信息>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 */
public interface LoanEnterpriseInfoQuery {
    /**
     * 企业融资申请历史融资信息查询
     * <p>
     * @param pstrLoanCode 融资编号
     * @param  pstrCompanyName 
     * @return  企业融资申请历史融资信息
     */
    public List<LoanEnterpriseInfo> findLoanEnterpriseInfo(@Param("pstrLoanCode") String pstrLoanCode,
            @Param("pstrCompanyName") String pstrCompanyName);
    
    /**
     * 企业融资件数查询
     * <p>
     * @param  pstrCompanyName 
     * @return  企业融资件数
     */
    public int findLoanEnterpriseInfoCountByCustomName(
            @Param("pstrCompanyName") String pstrCompanyName);
    
    /**
     * 根据融资编号删除融资表信息
     * @param loanCode 融资编号
     * @return
     */
    public void deleteByLoanCode(@Param("loanCode")
            String loanCode);
    
}
