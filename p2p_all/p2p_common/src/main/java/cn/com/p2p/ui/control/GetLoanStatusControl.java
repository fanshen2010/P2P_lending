package cn.com.p2p.ui.control;


import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

//import cn.com.p2p.domain.workflow.dto.ProcessInfo;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.LoanStatusOtherEnum;
import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.framework.util.StringUtils;
//import cn.com.p2p.workflow.service.ApproverService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class GetLoanStatusControl implements TemplateDirectiveModel{

//    @Autowired
//    private ApproverService approverService;
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String status = "";
		// 获取common路径
		String commonResultPath = env.getVariable("commonResultPath").toString();
		if(params != null && params.size() > 0){
			String bizType = DirectiveUtils.getString("bizType", params);
			String loanId = DirectiveUtils.getString("loanId", params);
		    String statusCode = DirectiveUtils.getString("statusCode", params);
			String activeName = DirectiveUtils.getString("activeName", params);
			Date loanEndTime = DirectiveUtils.getDate("loanEndTime", params);
			BigDecimal loanAmount = DirectiveUtils.getBigDecimal("loanAmount", params);
			BigDecimal investedAmount = DirectiveUtils.getBigDecimal("investedAmount", params);
			
			//start bizType=1 我的融资列表状态
			if (StringUtils.compare("1", bizType)) {
//			    ProcessInfo procInfo = approverService.getProcessInfoByBizKey(loanId);
//			    if(procInfo!=null){
//			    activeName = procInfo.getActivityName();
//			    }
			}
			//end
			
			LoanStatusEnum lse = LoanStatusEnum.getEnumByKey(statusCode);
			if(lse != null){
				status = lse.getValue();
			}
			
			if(investedAmount == null){
				investedAmount = BigDecimal.ZERO;
			}
			
			if(loanAmount != null && LoanStatusEnum.LOAN_STATUS_04.getCode().equals(statusCode)){
				int compare = investedAmount.compareTo(loanAmount);
				if(compare == 0){
					// 已投金额 = 融资金额
					status = LoanStatusOtherEnum.LOAN_STATUS_7.getValue()+"("+LoanStatusOtherEnum.LOAN_STATUS_6.getValue()+")";
				} else if(compare == 1){
					// 已投金额 > 融资金额
					status = LoanStatusOtherEnum.LOAN_STATUS_7.getValue()+"("+LoanStatusOtherEnum.LOAN_STATUS_5.getValue()+")";
				} else if(compare == -1){
					// 已投金额 < 融资金额
					if(loanEndTime != null && loanEndTime.compareTo(new Date()) <= 0){
						// 投资结束时间 <= 当前时间，到期未满标
						status = LoanStatusOtherEnum.LOAN_STATUS_7.getValue()+"("+LoanStatusOtherEnum.LOAN_STATUS_4.getValue()+")";
					} else if(loanEndTime != null && loanEndTime.compareTo(new Date()) > 0) {
						// 投资结束时间 >= 当前时间，招标中
						status = LoanStatusOtherEnum.LOAN_STATUS_7.getValue();
					}
				}
			}
			
			if(LoanStatusEnum.LOAN_STATUS_01.getCode().equals(statusCode) && StringUtils.isNotEmpty(activeName)){
				status = "审核中" + " (" + activeName +")" ;
			}
		}
		env.setVariable("status", DEFAULT_WRAPPER.wrap(status));
		env.include("/" + commonResultPath + "/common/bizTag/statusTag.ftl", "UTF-8", true);
	}

}
