package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.enumpack.PaymentWayEnum;
import cn.com.p2p.framework.enumpack.SexEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;
import cn.com.p2p.utils.Constants;

@Service("loanPersonalCheck")
public class LoanPersonalCheck extends BaseCheck  implements DataCheck{

	public List<String> check(BaseAction action, Map<String, Object> parameters)  {
		
		dataParameters=parameters;
		 errors= new ArrayList<String>();
/**********************************************************个人基本信息*****************************************************************************************************************************************************************************************************************************************/
		
		//客户姓名
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.name",getFieldValue("loanPersonalBasicDto.name"),null,new String[]{"客户姓名"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.name",getFieldValue("loanPersonalBasicDto.name"),getParamsValue(new String[]{"max","32"}),new String[]{"客户姓名"}));
		//身份证号
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.identity",getFieldValue("loanPersonalBasicDto.identity"),getParamsValue(new String[]{"max","18"}),new String[]{"身份证号"}));
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.identity",getFieldValue("loanPersonalBasicDto.identity"),null,new String[]{"身份证号"}));
		addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"loanPersonalBasicDto.identity",getFieldValue("loanPersonalBasicDto.identity"),getParamsValue(new String[]{"identityCard"}),new String[]{"身份证号"}));
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"loanPersonalBasicDto.identity",getFieldValue("loanPersonalBasicDto.identity"),getParamsValue(new String[]{"checkGender","p:loanPersonalBasicDto.gender","p:loanPersonalBasicDto.identity"}),new String[]{"性别"}));
		//性别
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.gender",getFieldValue("loanPersonalBasicDto.gender"),null,new String[]{"性别"}));
		//出生日期
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.birthdayDate",getFieldValue("loanPersonalBasicDto.birthdayDate"),null,new String[]{"出生日期"}));
		addError(dateCheck.checkData(this,ValidatorTypeEnum.Date,"loanPersonalBasicDto.birthdayDate",getFieldValue("loanPersonalBasicDto.birthdayDate"),getParamsValue(new String[]{"dateFormat"}),new String[]{"出生日期"}));
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"loanPersonalBasicDto.birthdayDate",getFieldValue("loanPersonalBasicDto.birthdayDate"),getParamsValue(new String[]{"checkBirthdayDate","p:loanPersonalBasicDto.identity","p:loanPersonalBasicDto.birthdayDate"}),new String[]{"出生日期"}));
		//籍贯
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.birthplace",getFieldValue("loanPersonalBasicDto.birthplace"),getParamsValue(new String[]{"max","100"}),new String[]{"籍贯"}));
		//手机号码
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.cellphone",getFieldValue("loanPersonalBasicDto.cellphone"),null,new String[]{"手机号码"}));
		addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"loanPersonalBasicDto.cellphone",getFieldValue("loanPersonalBasicDto.cellphone"),getParamsValue(new String[]{"phone"}),new String[]{"手机号码"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.cellphone",getFieldValue("loanPersonalBasicDto.cellphone"),getParamsValue(new String[]{"max","11"}),new String[]{"手机号码"}));
		//电子邮箱
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.email",getFieldValue("loanPersonalBasicDto.email"),getParamsValue(new String[]{"max","64"}),new String[]{"电子邮箱"}));
		addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"loanPersonalBasicDto.email",getFieldValue("loanPersonalBasicDto.email"),getParamsValue(new String[]{"email"}),new String[]{"电子邮箱"}));
		//职业
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalBasicDto.occupation",getFieldValue("loanPersonalBasicDto.occupation"),null,new String[]{"职业"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.occupation",getFieldValue("loanPersonalBasicDto.occupation"),getParamsValue(new String[]{"max","32"}),new String[]{"职业"}));
		//联系地址
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalBasicDto.address",getFieldValue("loanPersonalBasicDto.address"),getParamsValue(new String[]{"max","100"}),new String[]{"联系地址"}));
		
/************************************************************个人工作信息*****************************************************************************************************************************************************************************************************************************************/
		
		//单位名称
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalJobDto.companyName",getFieldValue("loanPersonalJobDto.companyName"),null,new String[]{"单位名称"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalJobDto.companyName",getFieldValue("loanPersonalJobDto.companyName"),getParamsValue(new String[]{"max","64"}),new String[]{"单位名称"}));
		//部门
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalJobDto.department",getFieldValue("loanPersonalJobDto.department"),null,new String[]{"部门"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalJobDto.department",getFieldValue("loanPersonalJobDto.department"),getParamsValue(new String[]{"max","32"}),new String[]{"部门"}));
		//职位
		addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"loanPersonalJobDto.workPosition",getFieldValue("loanPersonalJobDto.workPosition"),null,new String[]{"职位"}));
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalJobDto.workPosition",getFieldValue("loanPersonalJobDto.workPosition"),getParamsValue(new String[]{"max","32"}),new String[]{"职位"}));
		//月收入
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalJobDto.monthlyIncome",getFieldValue("loanPersonalJobDto.monthlyIncome"),getParamsValue(new String[]{"max","13"}),new String[]{"月收入"}));
		addError(numberCheck.checkData(this,ValidatorTypeEnum.Number,"loanPersonalJobDto.monthlyIncome",getFieldValue("loanPersonalJobDto.monthlyIncome"),getParamsValue(new String[]{"number"}),new String[]{"月收入"}));
		//公司电话
		addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"loanPersonalJobDto.companyTel",getFieldValue("loanPersonalJobDto.companyTel"),getParamsValue(new String[]{"phoneOrTel"}),new String[]{"公司电话"}));
		//公司地址
		addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"loanPersonalJobDto.addressOther",getFieldValue("loanPersonalJobDto.addressOther"),getParamsValue(new String[]{"max","100"}),new String[]{"公司地址"}));
		
/************************************************************融资项目信息*****************************************************************************************************************************************************************************************************************************************/
		
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
		addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"projectInfoDto.platformRate",getFieldValue("projectInfoDto.platformRate"),getParamsValue(new String[]{"checkGuarantee","p:projectInfoDto.productCode","p:projectInfoDto.guaranteeComCode","p:projectInfoDto.guaranteeRate"}),new String[]{"担保公司"}));
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
		return errors;
		
	}
	
	
	
	/** 根据身份证第17位校验性别*/
	public Object checkGender(Object[] params){
		String result = "";
		String gender = String.valueOf(params[1]);
		String identity = String.valueOf(params[2]);
		if(identity!=null && !identity.isEmpty()){
			int	sex = Integer.parseInt(identity.substring(16, 17));
			if(sex%2==0){
				if(!SexEnum.SEX_02.getCode().equals(gender)){
					result ="gender is invali";
				}
			}else{
				if(!SexEnum.SEX_01.getCode().equals(gender)){
					result ="gender is invali";
				}
			}
		}
		
		return result;
	}
	
	/** 根据身份证第7位到第14位校验出生日期*/
	public Object checkBirthdayDate(Object[] params){
		String result = "";
		String identity = String.valueOf(params[1]);
		String birthdayDate = String.valueOf(params[2]);
		if(identity!=null && !identity.isEmpty() && birthdayDate!=null && !birthdayDate.isEmpty()){
			String year = identity.substring(6,10);
			String month = identity.substring(10,12);
			String day = identity.substring(12, 14);
			String birthday = year+"-"+month+"-"+day;
			if(!birthday.equals(birthdayDate)){
				result = "birthday is invalid";
			}
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
