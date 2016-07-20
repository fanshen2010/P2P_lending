package cn.com.p2p.domain.loan.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.dto.LoaneeLoanRedordDto;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.LoanProjectInfo;

/**
 * 业务相关LoanQuery定义.
 * <p>
 * 数据访问层<融资>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 */
public interface LoanQuery {
    
    /**
     * 根据客户编号查询融资编号
     * <p>
     * @param  pstrCustomerCode  客户编号
     * @return  融资编号
     */
    public List<String> findLoanCodeByCustomerCode(
            @Param("pstrCustomerCode") String pstrCustomerCode);
    
   
    /**
     * 根据客户编号查询融资
     * @param customCode 客户编号
     * @return
     */
    public List<Loan> findLoansByCustomerCode(@Param("customCode") String pstrCustomerCode);
    /**
     * 根据融资编号前缀查询融资编号
     * <p>
     * @param  preLoanCode  融资编号前缀
     * @return  融资编号
     */
    public List<String> findLoanCodeByPre(
    		@Param("preLoanCode")String preLoanCode);
    
    /**
     * <p>获取融资项目信息</p>.<br>
     * author：<br>
     *===================================
     * @param pstrLoanCode 融资编号
     * @return 获取融资项目信息
     */
    public LoanProjectInfo findLoanProjectInfo(@Param("pstrLoanCode")String pstrLoanCode);
    
    /**
     * 通过融资状态、查询条件进行融资件数查询
     * @author 
     * 
     * @param loanCommSelectParameters loanParameter 查询条件
     * @return 件数
     */
    public int findLoanCountByCriteria(@Param("loanParameter") LoanCommSelCriteria loanParameter);
    
    /**
     * 根据融资客户编号查询当前融资人的融资记录信息
     * @param customCode 客户编号
     * @return
     */
	public LoaneeLoanRedordDto findLoaneeLoanRedordDtoByCustomCode(@Param("customCode")
			String customCode);
	
	 /**
     * 根据融资编号删除融资表信息
     * @param customCode 客户编号
     * @return
     */
    public void deleteByLoanCode(@Param("loanCode")
            String loanCode);
    
}
