package cn.com.p2p.domain.payment.query;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.payment.entity.CiccAccount;

/**
 * 用户支付账户自定义查询
 * @author 
 *
 */
public interface CiccAccountQuery {

	public CiccAccount findOneByPaymentAccountNumber(
			@Param("paymentAccountNumber") String paymentAccountNumber);

	public CiccAccount findOneByCustCode(
			@Param("custCode") String custCode);
	
	public CiccAccount findOneByUserId(
			@Param("userId") String userId);

}
