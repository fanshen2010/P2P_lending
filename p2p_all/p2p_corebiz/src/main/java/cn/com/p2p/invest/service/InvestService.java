package cn.com.p2p.invest.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

import cn.com.p2p.common.dto.CalculatorResultInstallmentsDto;
import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.MyInvestDtoCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestCriteria;
import cn.com.p2p.domain.invest.dto.MyInvestDto;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.security.login.service.dto.LoginUserDetail;

/**
 * 投资接口
 * @author 
 *
 */
public interface InvestService {

	
	/**
	 * 投资
	 * @param loanCode 融资编号
	 * @param investAmount 投资金额
	 * @param loginUser 当前登录人
	 * @return  投资ID
	 * @throws Exception 
	 */
	public Invest invest(String loanCode, BigDecimal investAmount,UserContext loginUser) throws Exception;
	
	/**
	 * 通过条件查询投资记录
	 * @param criteria 条件
	 * @return
	 */
	public List<Invest> findPageByCriteriaByLoanCode(InvestCriteria criteria);


	/**
	 * 投资确认
	 * @param loanCode
	 * @param investAmount
	 * @return
	 * @throws Exception 
	 */
	 public Invest investVerify(String loanCode, BigDecimal investAmount,UserContext loginUser) throws Exception;
	 
	 
	 public List<CalculatorResultInstallmentsDto> getPlan(String loanCode,
				BigDecimal investAmount);
	 
	 /**
	 * 获取前台个人中心我的投资页所需显示的数据
	 * @param loginUser 当前登录人
	 * @param status 投资状态
	 * @return  投资页面所显示的Dto
	 * @throws Exception 
	 */
	public List<MyInvestDto> findPageMyInvestByStatus(MyInvestDtoCriteria criteria);
	
	/**
	 * 获取前台个人中心我的投资页数据条数
	 * @param pstrUserId 当前登录人ID
	 * @return  条数map
	 * @throws Exception 
	 */
	public Map<String, Integer> findInvestCount(String pstrUserId);
	
	/**
	 * 根据投资Code获取投资对象
	 * @param investCode 投资Code
	 * @return  投资对象
	 * @throws Exception
	 */
	public Invest findInvestByInvestCode(String investCode);
	
	/**
	 * 获取我的投资记录
	 * @param criteria
	 * @return
	 */
    public List<MyInvestDto> findPageMyInvest(CustomInvestCriteria criteria);

    
    public List<Invest> getMyInvest(CustomInvestCriteria criteria);
}
