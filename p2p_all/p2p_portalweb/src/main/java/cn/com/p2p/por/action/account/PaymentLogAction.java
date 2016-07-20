package cn.com.p2p.por.action.account;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.payment.criteria.PaymentLogCustomCriteria;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.PaymentService;

/**
 * 交易记录Action
 * 
 * @author 
 *	
 */
@Namespace("/account/paymentLog")
@Results({ @Result(name = "paymentLog", location = "paymentLog.ftl", type = "freemarker")})
public class PaymentLogAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 交易记录查询条件 */
	private PaymentLogCustomCriteria criteria = new PaymentLogCustomCriteria();
	
	/** 交易记录集合列表 */
	private List<PaymentLog> paymentLogList;
	
	/** 投资支付接口 */
	@Autowired
	private PaymentService paymentService;
	
	/** 交易记录-查询操作按钮类型-默认为近三个月 */
	private String paySearchBtnType = PAYSEARCHBTNTYPE_THREEMONTH;

	/** 交易记录-查询操作按钮类型-1:今天 */
	private static final String PAYSEARCHBTNTYPE_TODAY = "1";
	/** 交易记录-查询操作按钮类型-2:最近一个月 */
	private static final String PAYSEARCHBTNTYPE_ONEMONTH = "2";
	/** 交易记录-查询操作按钮类型-3:近三个月 */
	private static final String PAYSEARCHBTNTYPE_THREEMONTH = "3";
	/** 交易记录-查询操作按钮类型-4:全部 */
	private static final String PAYSEARCHBTNTYPE_ALL = "4";

	@Action(value="paymentLog")
	@Override
	public String init() throws Exception {
		
		Date curDate = DateUtils.getCurrentDateTime();	//当前系统日期
		criteria.setUserLogin(this.getLoginuser().getUsername());	//获取当前登录用户名

		//TODO 日期查询 涉及到时分秒 没想到好办法 
		if(PAYSEARCHBTNTYPE_TODAY.equals(paySearchBtnType)){//查询类型为当天
			
			criteria.setMaxDate(DateUtils.dateAdd(DateUtils.parseDateTime(curDate,"yyyy-MM-dd"), Calendar.SECOND, 86399));
			criteria.setMinDate(DateUtils.dateAdd(DateUtils.parseDateTime(curDate,"yyyy-MM-dd"), Calendar.SECOND, 0));
		} else if(PAYSEARCHBTNTYPE_ONEMONTH.equals(paySearchBtnType)){//查询类型为最近一个月
			
			criteria.setMaxDate(DateUtils.dateAdd(DateUtils.parseDateTime(curDate,"yyyy-MM-dd"), Calendar.SECOND, 86399));
			criteria.setMinDate(DateUtils.dateAdd(DateUtils.parseDateTime(DateUtils.dateAdd(curDate, Calendar.MONTH, -1),"yyyy-MM-dd"), Calendar.SECOND, 0));
		} else if(PAYSEARCHBTNTYPE_THREEMONTH.equals(paySearchBtnType)){//查询类型为近三个月
			criteria.setMaxDate(DateUtils.dateAdd(DateUtils.parseDateTime(curDate,"yyyy-MM-dd"), Calendar.SECOND, 86399));
			criteria.setMinDate(DateUtils.dateAdd(DateUtils.parseDateTime(DateUtils.dateAdd(curDate, Calendar.MONTH, -3),"yyyy-MM-dd"), Calendar.SECOND, 0));
			
		} else if(PAYSEARCHBTNTYPE_ALL.equals(paySearchBtnType)){//查询类型为全部
			criteria.setMaxDate(null);
			criteria.setMinDate(null);
		}
		
		paymentLogList = paymentService.getPageListByCustomCriteria(criteria);
		
		return "paymentLog";
	}

	
	
	
	/*************************************************** Get Set 方法 ***************************************************/
	/** 获取交易记录查询条件 */
	public PaymentLogCustomCriteria getCriteria() {
		return criteria;
	}

	/** 设置交易记录查询条件 */
	public void setCriteria(PaymentLogCustomCriteria criteria) {
		this.criteria = criteria;
	}

	/** 获取交易记录集合列表 */
	public List<PaymentLog> getPaymentLogList() {
		return paymentLogList;
	}

	/** 设置交易记录集合列表 */
	public void setPaymentLogList(List<PaymentLog> paymentLogList) {
		this.paymentLogList = paymentLogList;
	}
	
	/** 获取交易记录-查询操作按钮类型 */
	public String getPaySearchBtnType() {
		return paySearchBtnType;
	}
	/** 设置交易记录-查询操作按钮类型 */
	public void setPaySearchBtnType(String paySearchBtnType) {
		this.paySearchBtnType = paySearchBtnType;
	}
	
}
