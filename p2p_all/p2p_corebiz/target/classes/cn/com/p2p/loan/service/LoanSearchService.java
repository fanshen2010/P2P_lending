package cn.com.p2p.loan.service;


/**
 * 融资接口 <p> 融资的主要实现接口
 * 
 * @作者 何生
 * @创建时间 2015-09-13 10:14
 */

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.json.JSONException;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.criteria.LoanCriteria;
import cn.com.p2p.domain.loan.dto.LoaneeLoanRedordDto;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.loan.service.dto.CustomerDto;
import cn.com.p2p.loan.service.dto.LoanCustomerInfoDto;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.loan.service.dto.LoanEnterpriseInfoDto;
import cn.com.p2p.loan.service.dto.LoanPersonalBasicDto;


public interface LoanSearchService {

    /**
     * <p>指定融资的所有信息查询</p>.<br>
     * 
     * author：<br>
     *===================================
     * @param pstrLoanCode融资编号
     * @param plstSearchInfoType 查询信息区分
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JSONException
     */
    public LoanDto getLoanInfo(String pstrLoanCode,SearchInfoTypeEnum  ... pSearchInfoType)
            throws IllegalAccessException, InvocationTargetException, JSONException;
    
    /**
     * 融资方信息查询
     * author：<br>
     * ===================================
     * 
     * @param pCustomerCriteria 客户信息查询条件
     * @return 融资方信息
     */
    public LoanCustomerInfoDto getLoanCustomerInfo(
    		CustomerDto customerDto);
    
    
    /**
     * 根据多参数分页查询融资信息（ 融资状态可以是多个）
     * 
     * author：
     * 
     * @param LoanCommSelCriteria loanParamete 查询条件参数
     * @return List<Loan>融资实体
     */
    public List<Loan> getPageLoanInfoByCriteria(LoanCommSelCriteria loanParameter);
    
    /**
	 * 根据多参数查询融资信息（ 融资状态可以是多个）
	 * 
	 * author：
	 * 
	 * @param loanParamete 查询条件参数
	 * @return List<Loan>融资实体
	 */
	public List<Loan> getLoanInfoByCriteria(LoanCommSelCriteria loanParameter);
    
    
    /**
     * 根据多参数查询融资件数（ 融资状态可以是多个）
     * 
     * author：
     * 
     * @param LoanCommSelCriteria loanParamete 查询条件参数
     * @return 融资件数
     */
    public int getLoanCountByCriteria(LoanCommSelCriteria loanParameter);
    
    
    /**
     * 根据多参数查询融资信息（ 融资状态可以是多个）
     * 
     * author：wangxiangshun
     * 
     * @param LoanCommSelCriteria loanParamete 查询条件参数
     * @return List<Loan>融资实体
     */
    public List<Loan> getPageLoanInfoByCriteria(LoanCriteria criteria);
    
    /**
     * <p>财务处理列表信息查询</p>.<br>
     * author：<br>
     *===================================
     * @param loanParameter 查询条件参数
     * @return 财务处理列表
     */
    public List<Loan> getFinancialApprovalInfo(LoanCommSelCriteria loanParameter);
    
    
    /**
     * 前台--项目预告列表
     * @param loanParameter 查询条件参数
     * @return  项目预告列表
     */ 
    public List<Loan> getLoanNoticeList(LoanCommSelCriteria loanParameter);
    
    
    /**
     * 前台--项目列表(分页)
     * @param loanParameter 查询条件参数
     * @return  项目列表
     */ 
    public List<Loan> getPageLoanObjectList(LoanCommSelCriteria criteria);
    
    /**
     * 前台--项目列表
     * @param loanParameter 查询条件参数
     * @return  项目列表
     */ 
    public List<Loan> getLoanObjectList(LoanCommSelCriteria criteria);
    
    
    /**
     * 根据融资客户编号查询当前融资人的融资记录信息
     * @param customCode 客户编号
     * @return
     */
    public LoaneeLoanRedordDto getLoaneeLoanRedordDtoByCustomCode(String customCode);
    
    /**
     * <p>
     * 担保公司类型转换
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * @param pfmTenantDepartment 担保公司信息
     * @return 转换后的担保公司信息
     */
    public LinkedMap getGuaranteeCorporationInfo();

    /**
     * 根据融资code查询
     * @author 
     * 
     * @param loanCode 融资code
     * @return Loan 融资实体
     */
    public Loan getLoanByCode(String loanCode);

    
    /**
     * <p>获取个人客户信息</p>.<br>
     * author：<br>
     *===================================
     * @param pstrCustomCode 客户编号
     * @param LoanPersonalBasicDto 个人信息
     * @return 个人信息
     */
    public LoanPersonalBasicDto getPersonalInfo(String pstrCustomCode , LoanPersonalBasicDto pPersonalBasicDto);
    
    /**
     * <p>获取企业客户信息</p>.<br>
     * author：<br>
     *===================================
     * @param pstrCustomCode 客户编号
     * @param pLoanEnterpriseInfoDto 企业信息
     * @return 企业信息
     */
    public LoanEnterpriseInfoDto getEnterpriseInfo(String pstrCustomCode , LoanEnterpriseInfoDto pLoanEnterpriseInfoDto);
}
