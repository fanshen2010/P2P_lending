package cn.com.p2p.domain.loan.criteria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.p2p.framework.dao.BaseCriteria;

/**
 * 融资共通查询参数类
 * 
 * @author 
 */

public class LoanCommSelCriteria extends BaseCriteria {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 融资产品Code */
    private String productCode;

    /** 融资项目名称 */
    private String loanName;

    /** 客户名称 */
    private String customName;

    /** 客户编号 */
    private String customCode;

    /** 最小融资金额 */
    private Integer loanAmountMin;

    /** 最大融资金额 */
    private Integer loanAmountMax;

    /** 融资发布日期 */
    private Date loanPostTime;

    /** 投资结束日期 */
    private Date loanEndTime;

    /** 最小融资期限 */
    private Integer loanTimeLimitMin;

    /** 最大融资期限 */
    private Integer loanTimeLimitMax;

    /** 融资期限单位 */
    private String loanTimeLimitUnit;

    /** 最小年化收益率 */
    private BigDecimal loanInterestRatesMin;

    /** 最大年化收益率 */
    private BigDecimal loanInterestRatesMax;

    /** 起始还款日期 */
    private Date repayTimeMin;

    /** 结束还款日期 */
    private Date repayTimeMax;

    /** 融资顾问 */
    private String loanConsultant;

    /* log:  2015-09-20 15:14, 用于我的融资申请界面****** begin ******* */
    /** 当前用户ID */
    private String loginUserId;

    /** 融资状态的集合，根据多个状态进行查询：in(01,02,...,12) */
    private List<String> status;

    /** 融资融资编号 */
    private String loanCode;
    /* log:  2015-09-20 15:14 ****** end ******* */

    /** 当期还款状态 */
    private String ReceivableRepayStatus;

    /* 前台项目投资列表使用  2015-10-17 */
    /** 还款方式 */
    private String loanInterestManner;

    /* 前台项目投资列表使用  2015-10-17 */
    
    /* log:  2015-10-11 ****** begin ******* */
    /** 担保公司Cd */
    private String guaranteeComCode;
    /* log:  2015-10-11 ****** end ******* */
    
    /*log:Batch用     2015-10-19 ****** begin ******* */
    /** 下次还款日 年月日*/
    private String receivableNextDateYMD;
    /* log: Batch用    2015-10-19 ****** end ******* */
    
    /*log:Batch用    2015-10-02 ****** begin ******* */
    private String onlineStatus;
    /* log: Batch用    2015-10-02 ****** end ******* */

    /** 获取融资产品Code */
    public String getProductCode() {
        return productCode;
    }

    /** 设置融资产品Code */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /** 获取融资项目名称 */
    public String getLoanName() {
        return loanName;
    }

    /** 设置融资项目名称 */
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    /** 获取客户名称 */
    public String getCustomName() {
        return customName;
    }

    /** 设置客户名称 */
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    /** 获取最小融资金额 */
    public Integer getLoanAmountMin() {
        return loanAmountMin;
    }

    /** 设置最小融资金额 */
    public void setLoanAmountMin(Integer loanAmountMin) {
        this.loanAmountMin = loanAmountMin;
    }

    /** 获取最大融资金额 */
    public Integer getLoanAmountMax() {
        return loanAmountMax;
    }

    /** 设置最大融资金额 */
    public void setLoanAmountMax(Integer loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    /** 获取融资发布日期 */
    public Date getLoanPostTime() {
        return loanPostTime;
    }

    /** 设置融资发布日期 */
    public void setLoanPostTime(Date loanPostTime) {
        this.loanPostTime = loanPostTime;
    }

    /** 获取投资结束日期 */
    public Date getLoanEndTime() {
        return loanEndTime;
    }

    /** 设置投资结束日期 */
    public void setLoanEndTime(Date loanEndTime) {
        this.loanEndTime = loanEndTime;
    }

    /** 获取最小融资期限 */
    public Integer getLoanTimeLimitMin() {
        return loanTimeLimitMin;
    }

    /** 设置最小融资期限 */
    public void setLoanTimeLimitMin(Integer loanTimeLimitMin) {
        this.loanTimeLimitMin = loanTimeLimitMin;
    }

    /** 获取最大融资期限 */
    public Integer getLoanTimeLimitMax() {
        return loanTimeLimitMax;
    }

    /** 设置最大融资期限 */
    public void setLoanTimeLimitMax(Integer loanTimeLimitMax) {
        this.loanTimeLimitMax = loanTimeLimitMax;
    }

    /** 获取融资期限单位 */
    public String getLoanTimeLimitUnit() {
        return loanTimeLimitUnit;
    }

    /** 设置融资期限单位 */
    public void setLoanTimeLimitUnit(String loanTimeLimitUnit) {
        this.loanTimeLimitUnit = loanTimeLimitUnit;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public BigDecimal getLoanInterestRatesMin() {
        return loanInterestRatesMin;
    }

    public void setLoanInterestRatesMin(BigDecimal loanInterestRatesMin) {
        this.loanInterestRatesMin = loanInterestRatesMin;
    }

    public BigDecimal getLoanInterestRatesMax() {
        return loanInterestRatesMax;
    }

    public void setLoanInterestRatesMax(BigDecimal loanInterestRatesMax) {
        this.loanInterestRatesMax = loanInterestRatesMax;
    }

    public String getLoanConsultant() {
        return loanConsultant;
    }

    public void setLoanConsultant(String loanConsultant) {
        this.loanConsultant = loanConsultant;
    }

    /** 获取最小还款日期 */
    public Date getRepayTimeMin() {
        return repayTimeMin;
    }

    /** 设置最小还款日期 */
    public void setRepayTimeMin(Date repayTimeMin) {
        this.repayTimeMin = repayTimeMin;
    }

    /** 获取最大还款日期 */
    public Date getRepayTimeMax() {
        return repayTimeMax;
    }

    /** 设置最大还款日期 */
    public void setRepayTimeMax(Date repayTimeMax) {
        this.repayTimeMax = repayTimeMax;
    }

    /** 获取当期还款状态 */
    public String getReceivableRepayStatus() {
        return ReceivableRepayStatus;
    }

    /** 设置当期还款状态 */
    public void setReceivableRepayStatus(String receivableRepayStatus) {
        ReceivableRepayStatus = receivableRepayStatus;
    }

    /**
     * @return the loanInterestManner
     */
    public String getLoanInterestManner() {
        return loanInterestManner;
    }

    /**
     * @param loanInterestManner the loanInterestManner to set
     */
    public void setLoanInterestManner(String loanInterestManner) {
        this.loanInterestManner = loanInterestManner;
    }

    /**
     * @return the customCode
     */
    public String getCustomCode() {
        return customCode;
    }

    /**
     * @param customCode the customCode to set
     */
    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    /** 获取担保公司Cd */
	public String getGuaranteeComCode() {
		return guaranteeComCode;
	}
	/** 设置担保公司Cd */
	public void setGuaranteeComCode(String guaranteeComCode) {
		this.guaranteeComCode = guaranteeComCode;
	}

    /** 获取下次还款日 年月日 */
    public String getReceivableNextDateYMD() {
        return receivableNextDateYMD;
    }

    /** 设置下次还款日 年月日*/
    public void setReceivableNextDateYMD(String receivableNextDateYMD) {
        this.receivableNextDateYMD = receivableNextDateYMD;
    }

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

}
