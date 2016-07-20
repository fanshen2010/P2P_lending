package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author FERO-015
 *
 */
public class UpdateInvestDto {
    
    /**
     *  支付状态
     */
    private boolean payStatus;
    
    /**
     *  本金
     */
    private BigDecimal principal;
    
    /**
     * 利息
     */
    private BigDecimal interest;
    
    /**
     * 总金额 
     */
    private BigDecimal totalAmount;
    
    /**
     * 期数
     */
    private int num;
    
    /**
     * 逾期罚息
     */
    private BigDecimal punitiveInterest;
    
    /**
     * 下一期还款时间
     */
    private Date nextPaymentDate;
    
    /**
     * 下一期还款状态
     */
    private String nextStatus;
    
    /** 获取支付状态 */
    public boolean isPayStatus() {
        return payStatus;
    }

    /** 设置支付状态*/
    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    /** 获取本金 */
    public BigDecimal getPrincipal() {
        return principal;
    }

    /** 设置本金*/
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    /** 获取利息 */
    public BigDecimal getInterest() {
        return interest;
    }

    /** 设置利息*/
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /** 获取总金额 */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /** 设置总金额 */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /** 获取期数 */
    public int getNum() {
        return num;
    }

    /** 设置期数*/
    public void setNum(int num) {
        this.num = num;
    }

    /** 获取下一期还款时间 */
    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    /** 设置下一期还款时间*/
    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    /** 获取逾期罚息 */
    public BigDecimal getPunitiveInterest() {
        return punitiveInterest;
    }

    /** 设置逾期罚息*/
    public void setPunitiveInterest(BigDecimal punitiveInterest) {
        this.punitiveInterest = punitiveInterest;
    }

    /** 获取下一期还款状态 */
    public String getNextStatus() {
        return nextStatus;
    }

    /** 设置下一期还款状态*/
    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus;
    }
}
