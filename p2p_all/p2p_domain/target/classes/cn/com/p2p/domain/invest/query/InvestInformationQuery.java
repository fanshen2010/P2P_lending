package cn.com.p2p.domain.invest.query;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.invest.entity.InvestInformation;


public interface InvestInformationQuery {
	
	/**
	 * <p>融资业务中的投资信息查询</p>.<br>
	 * author：<br>
	 *===================================
	 * @param loanCode 融资编号
	 */
	public List<InvestInformation> getInvestInformation(@Param("loanCode") String loanCode);
	
	/**
	 * <p>根据用户ID获取投资信息（对应前台个人中心收益详情）</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId 用户ID
	 */
	public InvestInformation getInvestByUser(@Param("userId") String userId);

	/**
	 * <p>获取投资计划收益信息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param startTime 统计
	 * @param endTime
	 * @return
	 */
	public List<InvestInformation> getPlanInvestByUser(@Param("userId") String userId,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
	
	/**
	 * <p>获取投资实际收益信息</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<InvestInformation> getActualInvestByUser(@Param("userId") String userId,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}
