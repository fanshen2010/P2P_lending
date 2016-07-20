package cn.com.p2p.customer.service.dto;

import java.math.BigDecimal;

/**
 * 融资客户业务统计信息数据传输对象
 * 主要用于后台个人/企业客户管理页面的统计数据查询
 * @author 
 *
 */
public class StatisticalDto {
    /**融资总金额（个人）   借款总金额（企业）*/
    private BigDecimal loanAmount;
    /**逾期总金额（个人）   逾期金额（企业）*/
    private BigDecimal outTimeAmount;
    /**待还总金额（个人）   待还金额（企业）*/
    private BigDecimal waitPayAmount;
    /**应还总金额*/
    private BigDecimal payAmount;
    /**已还金额*/
    private BigDecimal hasPayAmount;
    /**逾期待还*/
    private BigDecimal outTimeWaitPayAmount;
    /**融资记录*/
    private Integer loanCount;
    /**逾期记录*/
    private Integer timeOutCount;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getOutTimeAmount() {
        return outTimeAmount;
    }

    public void setOutTimeAmount(BigDecimal outTimeAmount) {
        this.outTimeAmount = outTimeAmount;
    }

    public BigDecimal getWaitPayAmount() {
        return waitPayAmount;
    }

    public void setWaitPayAmount(BigDecimal waitPayAmount) {
        this.waitPayAmount = waitPayAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getHasPayAmount() {
        return hasPayAmount;
    }

    public void setHasPayAmount(BigDecimal hasPayAmount) {
        this.hasPayAmount = hasPayAmount;
    }

    public BigDecimal getOutTimeWaitPayAmount() {
        return outTimeWaitPayAmount;
    }

    public void setOutTimeWaitPayAmount(BigDecimal outTimeWaitPayAmount) {
        this.outTimeWaitPayAmount = outTimeWaitPayAmount;
    }

    public Integer getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Integer loanCount) {
        this.loanCount = loanCount;
    }

    public Integer getTimeOutCount() {
        return timeOutCount;
    }

    public void setTimeOutCount(Integer timeOutCount) {
        this.timeOutCount = timeOutCount;
    }
}
