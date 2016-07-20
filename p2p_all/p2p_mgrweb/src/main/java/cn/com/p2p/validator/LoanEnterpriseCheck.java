package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.enumpack.PaymentWayEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;
import cn.com.p2p.utils.Constants;

@Service("loanEnterpriseCheck")
public class LoanEnterpriseCheck extends BaseCheck implements DataCheck{

	public List<String> check(BaseAction action, Map<String, Object> parameters){
		dataParameters=parameters;
		errors= new ArrayList<String>();
		//企业基本信息
		//企业全称
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"enterpriseInfoDto.enterpriseName",getFieldValue("enterpriseInfoDto.enterpriseName"),null,new String[]{"企业全称"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.enterpriseName",getFieldValue("enterpriseInfoDto.enterpriseName"),getParamsValue(new String[]{"max","64"}),new String[]{"企业全称"}));
		//营业执照号
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"enterpriseInfoDto.businessIicense",getFieldValue("enterpriseInfoDto.businessIicense"),null,new String[]{"营业执照号"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.businessIicense",getFieldValue("enterpriseInfoDto.businessIicense"),getParamsValue(new String[]{"max","15"}),new String[]{"营业执照号"}));
		//经营范围
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.operatingScope",getFieldValue("enterpriseInfoDto.operatingScope"),getParamsValue(new String[]{"max","100"}),new String[]{"经营范围"}));
		//注册资金
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"enterpriseInfoDto.registeredCapital",getFieldValue("enterpriseInfoDto.registeredCapital"),getParamsValue(new String[]{"integer"}),new String[]{"注册资金"}));
		//成立年份
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"enterpriseInfoDto.createdYear",getFieldValue("enterpriseInfoDto.createdYear"),null,new String[]{"成立年份"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"enterpriseInfoDto.createdYear",getFieldValue("enterpriseInfoDto.createdYear"),getParamsValue(new String[]{"integer"}),new String[]{"成立年份"}));
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"enterpriseInfoDto.createdYear",getFieldValue("enterpriseInfoDto.createdYear"),getParamsValue(new String[]{"checkCreatedYear","p:enterpriseInfoDto.createdYear"}),new String[]{"成立年份"}));
		//联系人
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"enterpriseInfoDto.legalRepresentative",getFieldValue("enterpriseInfoDto.legalRepresentative"),null,new String[]{"联系人"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.legalRepresentative",getFieldValue("enterpriseInfoDto.legalRepresentative"),getParamsValue(new String[]{"max","32"}),new String[]{"联系人"}));
		//联系电话
        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"enterpriseInfoDto.contactPhone",getFieldValue("enterpriseInfoDto.contactPhone"),null,new String[]{"联系电话"}));
        addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"enterpriseInfoDto.contactPhone",getFieldValue("enterpriseInfoDto.contactPhone"),getParamsValue(new String[]{"phone"}),new String[]{"联系电话"}));
        //联系邮箱
        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.contactMail",getFieldValue("enterpriseInfoDto.contactMail"),getParamsValue(new String[]{"max","64"}),new String[]{"电子邮箱"}));
        addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"enterpriseInfoDto.contactMail",getFieldValue("enterpriseInfoDto.contactMail"),getParamsValue(new String[]{"email"}),new String[]{"电子邮箱"}));
		//企业办公地址
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.officeAddr",getFieldValue("enterpriseInfoDto.officeAddr"),getParamsValue(new String[]{"max","100"}),new String[]{"企业办公地址"}));
		//年收益
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"enterpriseInfoDto.annualEarnings",getFieldValue("enterpriseInfoDto.annualEarnings"),getParamsValue(new String[]{"integer"}),new String[]{"年收益"}));
		//企业简介
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"enterpriseInfoDto.brief",getFieldValue("enterpriseInfoDto.brief"),getParamsValue(new String[]{"max","1000"}),new String[]{"企业简介"}));
		//===================================================================================================================
		//融资项目信息
		//融资项目
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanName",getFieldValue("projectInfoDto.loanName"),null,new String[]{"融资项目"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"projectInfoDto.loanName",getFieldValue("projectInfoDto.loanName"),getParamsValue(new String[]{"max","30"}),new String[]{"融资项目"}));
		//预期融资总额
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanAmount",getFieldValue("projectInfoDto.loanAmount"),null,new String[]{"预期融资总额"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.loanAmount",getFieldValue("projectInfoDto.loanAmount"),getParamsValue(new String[]{"number"}),new String[]{"预期融资总额"}));
		//融资期限
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"projectInfoDto.loanTimeLimit",getFieldValue("projectInfoDto.loanTimeLimit"),getParamsValue(new String[]{"checkLoanTimeLimit","p:projectInfoDto.loanTimeLimit","p:projectInfoDto.loanTimeLimitUnit"}),new String[]{"融资期限"}));
		//起投金额
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanUnitPrice",getFieldValue("projectInfoDto.loanUnitPrice"),null,new String[]{"起投金额"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.loanUnitPrice",getFieldValue("projectInfoDto.loanUnitPrice"),getParamsValue(new String[]{"integer"}),new String[]{"起投金额"}));
		addError(rangeCheck.checkData(this,ValidatorTypeEnum.Range,"projectInfoDto.loanUnitPrice",getFieldValue("projectInfoDto.loanUnitPrice"),getParamsValue(new String[]{"range","0","p:projectInfoDto.loanAmount"}),new String[]{"起投金额"}));
		//递增金额
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanStartShare",getFieldValue("projectInfoDto.loanStartShare"),null,new String[]{"递增金额"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.loanStartShare",getFieldValue("projectInfoDto.loanStartShare"),getParamsValue(new String[]{"integer"}),new String[]{"递增金额"}));
		addError(rangeCheck.checkData(this,ValidatorTypeEnum.Range,"projectInfoDto.loanStartShare",getFieldValue("projectInfoDto.loanStartShare"),getParamsValue(new String[]{"range","0","p:projectInfoDto.loanAmount"}),new String[]{"递增金额"}));
		//还款方式
		addError(selectRequiredDataCheck.checkData(this,ValidatorTypeEnum.SelectRequired,"projectInfoDto.loanInterestManner",getFieldValue("projectInfoDto.loanInterestManner"),null,new String[]{"还款方式"}));
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"projectInfoDto.loanInterestManner",getFieldValue("projectInfoDto.loanInterestManner"),getParamsValue(new String[]{"checkInterestManner","p:projectInfoDto.loanInterestManner","p:projectInfoDto.loanTimeLimit","p:projectInfoDto.loanTimeLimitUnit"}),new String[]{"还款方式"}));
		//=================================================================================================================
		//最大投资金额
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanMaxInvest",getFieldValue("projectInfoDto.loanMaxInvest"),null,new String[]{"最大投资金额"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.loanMaxInvest",getFieldValue("projectInfoDto.loanMaxInvest"),getParamsValue(new String[]{"number"}),new String[]{"最大投资金额"}));
		addError(rangeCheck.checkData(this,ValidatorTypeEnum.Range,"projectInfoDto.loanMaxInvest",getFieldValue("projectInfoDto.loanMaxInvest"),getParamsValue(new String[]{"range","0","p:projectInfoDto.loanAmount"}),new String[]{"最大投资金额"}));
		//年化收益率
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanInterestRates",getFieldValue("projectInfoDto.loanInterestRates"),null,new String[]{"年化收益率"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.loanInterestRates",getFieldValue("projectInfoDto.loanInterestRates"),getParamsValue(new String[]{"rateFormat"}),new String[]{"年化收益率"}));
		//投资截止时间
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.loanEndTime",getFieldValue("projectInfoDto.loanEndTime"),null,new String[]{"投资截止时间"}));
		addError(dateCheck.checkData(this,ValidatorTypeEnum.Date,"enterpriseInfoDto.loanEndTime",getFieldValue("enterpriseInfoDto.loanEndTime"),getParamsValue(new String[]{"dateTimeFormat24"}),new String[]{"投资截止时间"}));
		//平台管理费率
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"projectInfoDto.platformRate",getFieldValue("projectInfoDto.platformRate"),null,new String[]{"平台管理费率"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"projectInfoDto.platformRate",getFieldValue("projectInfoDto.platformRate"),getParamsValue(new String[]{"rateFormat"}),new String[]{"平台管理费率"}));
		//====================================================================================================================
		//项目描述
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanProjectMsgDto.loanUse",getFieldValue("loanProjectMsgDto.loanUse"),null,new String[]{"项目描述"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanProjectMsgDto.loanUse",getFieldValue("loanProjectMsgDto.loanUse"),getParamsValue(new String[]{"max","1000"}),new String[]{"项目描述"}));
		//成立前提
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanProjectMsgDto.premise",getFieldValue("loanProjectMsgDto.premise"),getParamsValue(new String[]{"max","1000"}),new String[]{"成立前提"}));
		//还款来源
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanProjectMsgDto.repaySource",getFieldValue("loanProjectMsgDto.repaySource"),getParamsValue(new String[]{"max","1000"}),new String[]{"还款来源"}));
		//风险控制措施
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanProjectMsgDto.riskControl",getFieldValue("loanProjectMsgDto.riskControl"),getParamsValue(new String[]{"max","1000"}),new String[]{"风险控制措施"}));
		//平台建议
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanProjectMsgDto.platform",getFieldValue("loanProjectMsgDto.platform"),getParamsValue(new String[]{"max","1000"}),new String[]{"平台建议"}));
		//===================================================================================================================
		
		return errors;
		
		
	}
	
	// *********************************后台服务检验--自定义校验

	/**
	 * 校验成立年份
	 * 
	 * @param params
	 * @return
	 */
	public String checkCreatedYear(Object[] params) {

		String result = "";
		try {
			int createdYear = Integer.parseInt(String.valueOf(params[1]));
			int currentYear = Integer.parseInt(DateUtils.getCurrentDateYear());
			if (createdYear > currentYear) {
				result = "Setup year should be no larger than now";
			}
		} catch (Exception e) {
			result = "Setup year is invalid";

		}

		return result;
	}

	/**
	 * 校验张证件号码
	 * 
	 * @param params
	 * @return
	 */
    public String checkDocumentNumber(Object[] params) {
        String result = "";
        try {
            String documentNumber = String.valueOf(params[1]);
            if (documentNumber.length() > 18) {
                result = "the length of ID number is 18";
            } else {
                ICommonDataCheck stringDataCheck = (ICommonDataCheck) SpringContextUtils
                        .getBean(ValidatorTypeEnum.String
                                .getValidatorBeanName());
                result = stringDataCheck.checkData(this,
                        ValidatorTypeEnum.String, "ID number", documentNumber,
                        new Object[] { "identityCard" },
                        new String[] { "ID number" });
            }
        } catch (Exception e) {

            result = "ID number is invalid";

        }
        return result;
    }

	/**
	 * 融资期限校验
	 */
	public String checkLoanTimeLimit(Object[] params) {
		String result = "";
		try {
			String timeLimit = String.valueOf(params[1]);
			String timeLimitUnit = String.valueOf(params[2]);

			String intMatch = "^[1-9]\\d*$";// 整数
			if (!timeLimit.matches(intMatch)) {
				result = "loan limit should be integer";
			} else {
				if (StringUtils.compare(timeLimitUnit,
						LoanTimeLimitUnitEnum.Day.getCode())) {

					if (Integer.parseInt(timeLimit) > 9999) {
						result = "loan limit no more tham 9999 days";
					}
				} else if (StringUtils.compare(timeLimitUnit,
						LoanTimeLimitUnitEnum.Month.getCode())) {
					if (Integer.parseInt(timeLimit) > 99) {
						result = "loan limit no more tham 99 months";
					}
				}
			}
		} catch (Exception e) {
			result = "loan limit is invalid";
		}
		return result;
	}

	/**
	 * 还款方式校验
	 * 
	 * @param params
	 * @return
	 */
	public String checkInterestManner(Object[] params) {
		String result = "";
		try {
			String interestManner = String.valueOf(params[1]);
			String loanTimeLimit = String.valueOf(params[2]);
			String loanTimeLimitUnit = String.valueOf(params[3]);
			if (StringUtils.compare(loanTimeLimitUnit,
					LoanTimeLimitUnitEnum.Day.getCode())) {
				int TimeLimit = Integer.parseInt(loanTimeLimit);
				if (TimeLimit <= 30
						&& !StringUtils
								.compare(interestManner,
								        LoanInterestMannerEnum.DISPOSABLE_PRINCIPAL_INTEREST
												.getCode())) {
					result = "interest manner should be"+LoanInterestMannerEnum.DISPOSABLE_PRINCIPAL_INTEREST.getValue();
				}
			}

		} catch (Exception e) {
			result = "interest manner is invalid";
		}
		return result;
	}
	
}
