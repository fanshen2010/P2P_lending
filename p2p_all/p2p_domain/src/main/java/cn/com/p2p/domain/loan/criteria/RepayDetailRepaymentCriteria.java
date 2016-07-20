/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        RepayDetailCriteria.java
 * Description:       查询条件RepayDetailCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-09-18             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;


import java.util.Date;
import java.util.List;

import cn.com.p2p.framework.dao.BaseCriteria;

public class RepayDetailRepaymentCriteria extends BaseCriteria {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**还款期号*/
    private String num;
    
    /**融资编号*/
    private String loanCode;
    
    /**计划收款日*/
    private Date repayPlanDate;

    /**还款期号*/
    private List<String> nums;
    
    /**还款状态*/
    private List<String> status;
    
    /**比较用计划收款日*/
    private Date comparisonRepayPlanDate;


    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }


    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }


    /**
     * @return the loanCode
     */
    public String getLoanCode() {
        return loanCode;
    }


    /**
     * @param loanCode the loanCode to set
     */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    /** 获取repayPlanDate */
    public Date getRepayPlanDate() {
        return repayPlanDate;
    }


    /** 设置repayPlanDate*/
    public void setRepayPlanDate(Date repayPlanDate) {
        this.repayPlanDate = repayPlanDate;
    }


    /**
     * @return the status
     */
    public List<String> getStatus() {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(List<String> status) {
        this.status = status;
    }


    /** 获取nums */
    public List<String> getNums() {
        return nums;
    }


    /** 设置nums*/
    public void setNums(List<String> nums) {
        this.nums = nums;
    }


    /** 获取比较用计划收款日 */
    public Date getComparisonRepayPlanDate() {
        return comparisonRepayPlanDate;
    }


    /** 设置比较用计划收款日*/
    public void setComparisonRepayPlanDate(Date comparisonRepayPlanDate) {
        this.comparisonRepayPlanDate = comparisonRepayPlanDate;
    } 
}
