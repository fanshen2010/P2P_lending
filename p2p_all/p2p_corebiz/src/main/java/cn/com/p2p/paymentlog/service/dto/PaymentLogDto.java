package cn.com.p2p.paymentlog.service.dto;

/**
 * Created by  on 2015/4/22.
 */
/**
 * <p>交易支付日志信息数据传输对象</p>
 * <p>查询到页面展示时使用</p>
 *
 * @author 
 * @date 2015年4月21日 17:15:07
 */
public class PaymentLogDto {
    /*共有数据字段*/
    //项目名称
    private String projectName;
    //项目编号
    private String projectNo;
    //响应码
    private String responseCode;
    //响应消息
    private String responseMessage;
    //投资信息
    private PaymentInvestDto investDto;
    //融资信息
    private PaymentLoanDto loanDto;
    //备注
    private String remark;

         /*
     * ==================================================================
     * ===========================Get/Set方法============================
     * ==================================================================
     */

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public PaymentInvestDto getInvestDto() {
        return investDto;
    }

    public void setInvestDto(PaymentInvestDto investDto) {
        this.investDto = investDto;
    }

    public PaymentLoanDto getLoanDto() {
        return loanDto;
    }

    public void setLoanDto(PaymentLoanDto loanDto) {
        this.loanDto = loanDto;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
