package cn.com.p2p.domain.payment.query;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.payment.criteria.PaymentLogCreateCriteria;
import cn.com.p2p.domain.payment.criteria.PaymentLogCustomCriteria;
import cn.com.p2p.domain.payment.entity.PaymentLog;

public interface PaymentLogQuery {

	/**
	 * 按流水号进行查询，返回唯一一条记录
	 * @param serialNumber 流水号
	 * @return
	 */
	public PaymentLog findOneBySerialNumber(@Param("serialNumber")String serialNumber);
	
	/**
	 * 按照自定义查询条件进行分页查询，返回支付记录集合
	 * @author 
	 * 
	 * @param PaymentLogCustomCriteria criteria 自定义参数类
	 * @return List<PaymentLog> 支付记录集合
	 */
	public List<PaymentLog> findPageListByCustomCriteria(@Param("criteria")PaymentLogCustomCriteria criteria);
	
	/**
     * 按条件翻页检索，返回实体集合
     * <p>
     * @param  PaymentLogSearchCriteria 检索条件
     * @return List<PaymentLog>
     */
	public List<PaymentLog> findPageByCreateCriteria(@Param("criteria")PaymentLogCreateCriteria criteria);
	
	
	/**
     * 统计支出金额
     * <p>
     * @param  paymentYearMonth 交易年月
     * @param  userLogin 用户名
     * @return 统计支出金额
     */
    public BigDecimal findTotalAmountByCriteria(@Param("paymentYearMonth") String paymentYearMonth,
            @Param("userLogin") String userLogin);
}
