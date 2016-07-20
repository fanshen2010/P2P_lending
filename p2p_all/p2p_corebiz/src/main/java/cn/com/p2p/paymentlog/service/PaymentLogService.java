package cn.com.p2p.paymentlog.service;

import java.util.List;

import cn.com.p2p.domain.payment.criteria.PaymentLogCreateCriteria;
import cn.com.p2p.domain.payment.criteria.PaymentLogCriteria;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.paymentlog.service.dto.PaymentInvestDto;
import cn.com.p2p.paymentlog.service.dto.PaymentLoanDto;
import cn.com.p2p.paymentlog.service.dto.PaymentLogDto;

/**
 * <p>
 * 交易支付日志对外接口
 * </p>
 *
 * @author 
 * @date 2015年4月22日 16:00:00
 */
public interface PaymentLogService {

	/**
	 * 查询交易支付日志列表
	 * 
	 * @param criteria
	 *            查询条件
	 * @return
	 */
	public List<PaymentLog> findPageByCriteria(PaymentLogCriteria criteria);

	/**
	 * 查询单个交易支付日志信息
	 * 
	 * @param id
	 * @return
	 */
	public PaymentLogDto getPaymentLog(String id);

	/**
	 * 查询交易支付日志列表(修复日期条件过滤无效问题)
	 * 
	 * @author 
	 * @param criteria
	 *            查询条件
	 * @return
	 */
	public List<PaymentLog> findPageByCriteria(PaymentLogCreateCriteria criteria);

	/**
	 * 获取投资支付日志的投资信息 在PaymentDefrayService.ciccDaoPay中使用,3111P2P 项目支付
	 * 
	 * @author 
	 * @param paymentNo
	 *            支付投资交易流水号
	 * @return
	 */
	public PaymentInvestDto getInvestJson(String paymentNo);
	
	/**
	 * 获取投资支付日志的融资信息 在PaymentDefrayService.ciccInvestTransfer中使用 债权转让
	 * 
	 * @author 
	 * @param serialNumber
	 *            投资交易流水号
	 * @return
	 */
	public PaymentLoanDto getLoanJson(String serialNumber);

	/**
	 * 根据流水号获取支付记录
	 * @author 
	 * @param serialNumber
	 * @return
	 */
	public PaymentLog getPaymentLogBySerialNumber(String serialNumber);
	
}
