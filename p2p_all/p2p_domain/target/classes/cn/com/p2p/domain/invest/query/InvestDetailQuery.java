package cn.com.p2p.domain.invest.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestDetail;
import cn.com.p2p.domain.invest.dto.InvestDetailDto;
import cn.com.p2p.domain.invest.entity.InvestDetail;

public interface InvestDetailQuery {
	
	/**
	  * 
	  * 
	  * <p> 根据投资年份和用户计算 </p>.<br> 说明：
	  *      已收回款
	  *      待收回款
	  *      罚息
	  * 
	  * author：<br> 
	  * ===================================
	  * @param investYear 投资年份
	  * @param userId 用户ID
	  *
	  */
	public List<InvestDetailDto> findInvestByInvestYear(@Param("investYear") String investYear,@Param("userId") String userId );
	
	/**
	  * 
	  * 
	  * <p> 根据用户查找用户的投资年份* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param userId 用户ID
	  *
	  */
	public List<String> findInvestDetailYearByUserId(@Param("userId") String userId);
	
	/**
	  * 
	  * 
	  * <p> 根据年份、月份、用户 查找投资信息* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param investYear 投资年份
	  * @param investMonth 投资月份
	  * @param userId 用户ID
	  *
	  */
	public List<InvestDetail> findInvestDetailByYearMonth(@Param("investYear") String investYear,@Param("investMonth") String investMonth,@Param("userId") String userId);
	
	/**
     * 
     * 
     * <p> 根据条件，查询投资明细* </p>.<br> 
     * 
     * author：周凯<br> 
     * ===================================
     * @param criteria 检索条件
     * @return 投资明细
     */
	public List<InvestDetail>findPageInvestDetailByCriteria(@Param("criteria") InvestDetailCriteria criteria);
	
	/**
     * 
     * 
     * <p> 根据年份和用户ID，查询指定用户的指定年份的投资明细信息 </p>.<br> 
     * 
     * author：东长昆<br> 
     * ===================================
     * @param investYear 年份
     * @param userId 用户ID
     * @return 投资明细
     */
	public List<InvestDetail>findInvestDetailsByYear(@Param("investYear") String investYear, @Param("userId") String userId);

	/**
	 * <p> 根据条件查询投资详细信息 </p>.<br> 
	 *  author：<br> 
	 * @param criteria 条件
	 * @return
	 */
    public List<CustomInvestDetail> findInvestDetailListByCriteria(@Param("criteria") InvestDetailCriteria criteria);
	
}
