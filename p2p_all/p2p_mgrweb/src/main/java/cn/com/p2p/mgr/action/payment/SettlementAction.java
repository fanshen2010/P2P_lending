package cn.com.p2p.mgr.action.payment;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.payment.criteria.PaymentLogCreateCriteria;
import cn.com.p2p.domain.payment.entity.PaymentLog;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.payment.enumtype.PaymentTypeEnum;
import cn.com.p2p.paymentlog.service.PaymentLogService;
import cn.com.p2p.paymentlog.service.dto.PaymentLogDto;

/**
 * 交易支付查询中的项目结算查询<br>
 *
 * @author 
 */
@Namespace("/payment/settlement")
@Results({
        @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
        @Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker")
})
public class SettlementAction extends BaseAction {

    @Autowired
    private PaymentLogService paymentLogService;

    private PaymentLogCreateCriteria criteria = new PaymentLogCreateCriteria();
    /**
     * 查询交易支付日志列表
     */
    private List<PaymentLog> paymentLogs;
    /**
     * 查询交易支付日志数据
     */
    private PaymentLogDto paymentLogDto;
    /**
     * 查询交易支付日志id
     */
    private String id;

    @Override
    @Action(value = "index")
    public String init() throws Exception {
        //默认结算类型
        criteria.setType(PaymentTypeEnum.settlement.getCode());
        //设置查询条件
        //设置最大时间为选中天的最后一秒前
        if(criteria.getPaymentMaxTime() != null){
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(criteria.getPaymentMaxTime());
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            criteria.setPaymentMaxTime(c.getTime());
        }
        paymentLogs = paymentLogService.findPageByCriteria(criteria);
        return BaseAction.INIT;
    }

    @Action(value = "view")
    public String view() throws Exception {
        paymentLogDto = paymentLogService.getPaymentLog(id);
        return BaseAction.VIEW;
    }


    /*
     * ==================================================================
     * ===========================Get/Set方法============================
     * ==================================================================
     */

    public PaymentLogCreateCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(PaymentLogCreateCriteria criteria) {
        this.criteria = criteria;
    }

    public List<PaymentLog> getPaymentLogs() {
        return paymentLogs;
    }

    public void setPaymentLogs(List<PaymentLog> paymentLogs) {
        this.paymentLogs = paymentLogs;
    }

    public PaymentLogDto getPaymentLogDto() {
        return paymentLogDto;
    }

    public void setPaymentLogDto(PaymentLogDto paymentLogDto) {
        this.paymentLogDto = paymentLogDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
