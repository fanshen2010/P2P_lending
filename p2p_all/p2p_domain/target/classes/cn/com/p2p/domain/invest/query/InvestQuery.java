package cn.com.p2p.domain.invest.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.invest.criteria.MyInvestDtoCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestCriteria;
import cn.com.p2p.domain.invest.dto.MyInvestDto;
import cn.com.p2p.domain.invest.entity.Invest;

/**
 * 
 * InvestQuery自定义
 *  <p>
 * 投资自定义查询 ,数据访问层<投资表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * @author 
 *
 */
public interface InvestQuery {

	
	/**
	 * 根据投资编号查找投资数据
	 * @param investCode 投资编号
	 * @return 投资
	 */
	public Invest findInvest(@Param("investCode")String investCode);
	
	/**
	 * 根据投资支付流水号获取投资
	 * @param serialNumber
	 * @return
	 */
	public Invest findOneBySerialNumber(@Param("serialNumber")String serialNumber);

	/**
	 * 根据融资编号获取投资编号
	 * @param loanCode
	 * @return
	 */
	public String findMaxInvestCodeByLoanCode(@Param("loanCode") String loanCode);

	/**
	 * 根据用户Id、投资状态获取前台个人中心我的投资信息分页列表
	 * @param InvestCriteria
	 * @return
	 */
	public List<MyInvestDto> findPageMyInvestDtoList(@Param("criteria") MyInvestDtoCriteria criteria);
	
	/**
	 * 根据用户Id、投资状态获取前台个人中心我的投资信息列表条数
	 * @param InvestCriteria
	 * @return
	 */
	public Integer findInvestCountByStatus(@Param("criteria") CustomInvestCriteria criteria);
	
	
	/**
	 * 根据条件查询投资列表
	 * @param criteria 条件
	 * @return
	 */
	public List<Invest> findEffectInvestListByCriteria(@Param("criteria") CustomInvestCriteria criteria);
	/**
	 * 根据条件查询投资列表带融资信息
	 * @param criteria 条件
	 * @return
	 */
	public List<MyInvestDto> findPageInvestListByCriteria(@Param("criteria") CustomInvestCriteria criteria);
}
