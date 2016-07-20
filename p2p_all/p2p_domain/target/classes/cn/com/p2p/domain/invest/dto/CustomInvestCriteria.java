package cn.com.p2p.domain.invest.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.p2p.framework.dao.BaseCriteria;

public class CustomInvestCriteria extends BaseCriteria {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**投资编号*/
    private String investCode;

    /**投资项目名*/
    private String loanName;

    /**投资金额*/
    private BigDecimal investmentAmount;

    /**投资期限*/
    private Integer investLimit;

    /**投资期限单位*/
    private String investTimeLimitUnit;

    /**投资日*/
    private Date investmentTime;

    /**起息日*/
    private Date interestDate;

    /**结清日*/
    private Date endDate;

    /**投资收益率*/
    private BigDecimal investInterstRate;

    /**预期收益*/
    private BigDecimal investInterst;

    /**已收本金*/
    private BigDecimal receivedPrincipal;

    /**已收利息*/
    private BigDecimal receivedInterest;

    /**应收本金*/
    private BigDecimal receivablePrincipal;

    /**应收利息*/
    private BigDecimal receivableInterest;

    /**已收罚息*/
    private BigDecimal receivedPunitive;

    /**待收罚息*/
    private BigDecimal receivablePunitive;

    /**还款起息日*/
    private Date paymentStartDate;

    /**下期还款日*/
    private Date nextPaymentDate;

    /**下期还款金额*/
    private BigDecimal nextPaymentAmount;

    /**下期还款期号*/
    private String nextPaymentNo;

    /**还款总期数*/
    private Integer paymentTotalNum;

    /**投资状态*/
    private List<String> status;

    /**投资类型*/
    private String investType;

    /**结清方式*/
    private String settlementType;

    /**中金交易记录json*/
    private String ciccTradingJson;

    /**投资客户名称*/
    private String investUserName;

    /**投资用户ID*/
    private String userId;

    /**融资编号（code）*/
    private String loanCode;

    /**债权编号（code）*/
    private String debtCode;

    /**总收益*/
    private BigDecimal investEarnings;

    /**投资客户回款账户*/
    private String receivedAccount;

    /**投资客户回款账户名*/
    private String receivedAccountName;

    /**交易流水号*/
    private String serialNumber;

    /**商户ID*/
    private String tenantId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者*/
    private String createUserId;

    /**更新者*/
    private String updateUserId;

    /**版本*/
    private Integer version;

         

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取投资编号*/
    public String getInvestCode() {
        return investCode;
    }

    /**设置投资编号*/
    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }
    /**获取投资项目名*/
    public String getLoanName() {
        return loanName;
    }

    /**设置投资项目名*/
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }
    /**获取投资金额*/
    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    /**设置投资金额*/
    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
    /**获取投资期限*/
    public Integer getInvestLimit() {
        return investLimit;
    }

    /**设置投资期限*/
    public void setInvestLimit(Integer investLimit) {
        this.investLimit = investLimit;
    }
    /**获取投资期限单位*/
    public String getInvestTimeLimitUnit() {
        return investTimeLimitUnit;
    }

    /**设置投资期限单位*/
    public void setInvestTimeLimitUnit(String investTimeLimitUnit) {
        this.investTimeLimitUnit = investTimeLimitUnit;
    }
    /**获取投资日*/
    public Date getInvestmentTime() {
        return investmentTime;
    }

    /**设置投资日*/
    public void setInvestmentTime(Date investmentTime) {
        this.investmentTime = investmentTime;
    }
    /**获取起息日*/
    public Date getInterestDate() {
        return interestDate;
    }

    /**设置起息日*/
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }
    /**获取结清日*/
    public Date getEndDate() {
        return endDate;
    }

    /**设置结清日*/
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**获取投资收益率*/
    public BigDecimal getInvestInterstRate() {
        return investInterstRate;
    }

    /**设置投资收益率*/
    public void setInvestInterstRate(BigDecimal investInterstRate) {
        this.investInterstRate = investInterstRate;
    }
    /**获取预期收益*/
    public BigDecimal getInvestInterst() {
        return investInterst;
    }

    /**设置预期收益*/
    public void setInvestInterst(BigDecimal investInterst) {
        this.investInterst = investInterst;
    }
    /**获取已收本金*/
    public BigDecimal getReceivedPrincipal() {
        return receivedPrincipal;
    }

    /**设置已收本金*/
    public void setReceivedPrincipal(BigDecimal receivedPrincipal) {
        this.receivedPrincipal = receivedPrincipal;
    }
    /**获取已收利息*/
    public BigDecimal getReceivedInterest() {
        return receivedInterest;
    }

    /**设置已收利息*/
    public void setReceivedInterest(BigDecimal receivedInterest) {
        this.receivedInterest = receivedInterest;
    }
    /**获取应收本金*/
    public BigDecimal getReceivablePrincipal() {
        return receivablePrincipal;
    }

    /**设置应收本金*/
    public void setReceivablePrincipal(BigDecimal receivablePrincipal) {
        this.receivablePrincipal = receivablePrincipal;
    }
    /**获取应收利息*/
    public BigDecimal getReceivableInterest() {
        return receivableInterest;
    }

    /**设置应收利息*/
    public void setReceivableInterest(BigDecimal receivableInterest) {
        this.receivableInterest = receivableInterest;
    }
    /**获取已收罚息*/
    public BigDecimal getReceivedPunitive() {
        return receivedPunitive;
    }

    /**设置已收罚息*/
    public void setReceivedPunitive(BigDecimal receivedPunitive) {
        this.receivedPunitive = receivedPunitive;
    }
    /**获取待收罚息*/
    public BigDecimal getReceivablePunitive() {
        return receivablePunitive;
    }

    /**设置待收罚息*/
    public void setReceivablePunitive(BigDecimal receivablePunitive) {
        this.receivablePunitive = receivablePunitive;
    }
    /**获取还款起息日*/
    public Date getPaymentStartDate() {
        return paymentStartDate;
    }

    /**设置还款起息日*/
    public void setPaymentStartDate(Date paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }
    /**获取下期还款日*/
    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    /**设置下期还款日*/
    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }
    /**获取下期还款金额*/
    public BigDecimal getNextPaymentAmount() {
        return nextPaymentAmount;
    }

    /**设置下期还款金额*/
    public void setNextPaymentAmount(BigDecimal nextPaymentAmount) {
        this.nextPaymentAmount = nextPaymentAmount;
    }
    /**获取下期还款期号*/
    public String getNextPaymentNo() {
        return nextPaymentNo;
    }

    /**设置下期还款期号*/
    public void setNextPaymentNo(String nextPaymentNo) {
        this.nextPaymentNo = nextPaymentNo;
    }
    /**获取还款总期数*/
    public Integer getPaymentTotalNum() {
        return paymentTotalNum;
    }

    /**设置还款总期数*/
    public void setPaymentTotalNum(Integer paymentTotalNum) {
        this.paymentTotalNum = paymentTotalNum;
    }
    /**获取投资状态*/
    public List<String> getStatus() {
        return status;
    }

    /**设置投资状态*/
    public void setStatus(List<String> status) {
        this.status = status;
    }
    /**获取投资类型*/
    public String getInvestType() {
        return investType;
    }

    /**设置投资类型*/
    public void setInvestType(String investType) {
        this.investType = investType;
    }
    /**获取结清方式*/
    public String getSettlementType() {
        return settlementType;
    }

    /**设置结清方式*/
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
    /**获取中金交易记录json*/
    public String getCiccTradingJson() {
        return ciccTradingJson;
    }

    /**设置中金交易记录json*/
    public void setCiccTradingJson(String ciccTradingJson) {
        this.ciccTradingJson = ciccTradingJson;
    }
    /**获取投资客户名称*/
    public String getInvestUserName() {
        return investUserName;
    }

    /**设置投资客户名称*/
    public void setInvestUserName(String investUserName) {
        this.investUserName = investUserName;
    }
    /**获取投资用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置投资用户ID*/
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**获取融资编号（code）*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号（code）*/
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }
    /**获取债权编号（code）*/
    public String getDebtCode() {
        return debtCode;
    }

    /**设置债权编号（code）*/
    public void setDebtCode(String debtCode) {
        this.debtCode = debtCode;
    }
    /**获取总收益*/
    public BigDecimal getInvestEarnings() {
        return investEarnings;
    }

    /**设置总收益*/
    public void setInvestEarnings(BigDecimal investEarnings) {
        this.investEarnings = investEarnings;
    }
    /**获取投资客户回款账户*/
    public String getReceivedAccount() {
        return receivedAccount;
    }

    /**设置投资客户回款账户*/
    public void setReceivedAccount(String receivedAccount) {
        this.receivedAccount = receivedAccount;
    }
    /**获取投资客户回款账户名*/
    public String getReceivedAccountName() {
        return receivedAccountName;
    }

    /**设置投资客户回款账户名*/
    public void setReceivedAccountName(String receivedAccountName) {
        this.receivedAccountName = receivedAccountName;
    }
    /**获取交易流水号*/
    public String getSerialNumber() {
        return serialNumber;
    }

    /**设置交易流水号*/
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }

    /**设置商户ID*/
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version) {
        this.version = version;
        
    }


}
