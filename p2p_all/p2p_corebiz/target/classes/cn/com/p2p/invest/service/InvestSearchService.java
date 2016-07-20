package cn.com.p2p.invest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestDetail;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.entity.InvestInformation;
import cn.com.p2p.invest.service.dto.InvestIncomeDto;
import cn.com.p2p.invest.service.dto.InvestInformationDto;

/**
 * 投资接口 <p> 投资的主要实现接口
 * 
 * @作者 
 * @创建时间 2015-09-19 10:05
 */
public interface InvestSearchService {
	
	 /**
     * <p>融资业务中获取投资信息</p>.<br>
     * author：<br>
     *===================================
     * @param loanCode 融资编号
     * @return 投资信息
     */
	public List<InvestInformationDto> getInvestInformation(String loanCode);
	/**
	 * 
	 * <p>业务查询——投资查询列表 * </p>.<br> 说明：
	 * 
	 * author：<br> 
	 * ===================================
	 * @param criteria 查询条件类
	 */
	public List<Invest> getInvestAll(InvestCriteria criteria);
	
	/**
	 * 
	 * <p>根据投资ID获取投资信息 * </p>.<br> 说明：
	 * 
	 * author：<br> 
	 * ===================================
	 * @param InvestId 投资信息ID
	 */
	public Invest getInvestById(String InvestId);
	
	/**
	 * 
	 * 根据多条件查询投资详情
	 * 
	 * @author 
	 * 
	 * @param InvestDetailCriteria criteria 投资详情查询条件类
	 * 
	 * @return List<InvestDetail> 投资详情List
	 */
	public List<CustomInvestDetail> getInvestDetailByCriteria(InvestDetailCriteria criteria);
	
	/**
     * 
     * 根据多条件查询投资的分页详情
     * 
     * @author 
     * 
     * @param InvestDetailCriteria criteria 投资详情查询条件类
     * 
     * @return List<InvestDetail> 投资详情List
     */
    public List<InvestDetail> getPageInvestDetailByCriteria(InvestDetailCriteria criteria);
	
	/**
     * <p>根据用户ID获取投资信息（对应前台个人中心收益详情）</p>.<br>
     * author：<br>
     *===================================
     * @param loanCode 融资编号
     * @return 投资信息
     */
	public InvestIncomeDto getInvestByUser(String userId);
	
	
	/**
     * <p>根据用户ID获取投资信息（对应前台个人中心收益图表）</p>.<br>
     * 
     * author：<br> 
     * ===================================
     * 
     * @param userId 用户ID
     * @return 投资信息
     */
	public Map<String,String> getInvestStatisticByUser(String userId);
	
	
	/**
	 * <p>用户每月的计划收益</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param pastTime
	 * @param futureTime
	 * @return
	 */
	public List<InvestInformation> getPlanInvestByUser(String userId,Date pastTime,Date futureTime);
	
	/**
	 * <p>用户每月的实际收益</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param pastTime
	 * @param currentTime
	 * @return
	 */
	public List<InvestInformation> getActualInvestByUser(String userId,Date pastTime,Date currentTime);
	
	/**
	 * 多条件检索投资列表
	 * @param criteria 条件
	 * @return 投资列表
	 */
	public List<Invest> getInvestList(InvestCriteria criteria);
	
}
