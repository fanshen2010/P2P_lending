package cn.com.p2p.framework.web.validator;

/***
 * 验证类型枚举，定义服务器端数据验证的类型
 * @author kezhida
 *
 */
public enum ValidatorTypeEnum {
	
	/**
	 * 必须输入校验
	*/
	TextRequired("Required","requiredDataCheck","%s:Please fill in "),
	/**
	 * 必须输入校验
	 */
	SelectRequired("Required","requiredDataCheck","%s:Please choose "),
	
	/**
	 * 
	 * 字符串格式校验
	*/
	String("String","stringCheck","%s:"),
	
	/**
	 * 数字类型校验
	 * 包含  integer(整数),number(数字),rateFormat(百分比)
	 */ 
	Number("Number","numberCheck","%s:"),
	
	/**
	 * 长度校验
	 */
	Length("Length","lengthCheck","%s:length not valid!"),

	/**
	 * 自定义校验
	 */
	Custom("Custom","customCheck","%s:"),
	/**
	 * 范围校验
	 */
	Range("Range","rangeCheck","%s:%sno later than%s"),
	/**
	 *时间日期校验 
	 */
	Date("Date","dateCheck","%s:Date not valid");
	
	
	
	private ValidatorTypeEnum(String chkName,String checkBeanName,String errorMessage){
		this.checkName =chkName;
		this.checkBeanName = checkBeanName;
		this.errorMessage = errorMessage;
	}
	private String checkName = null;
	private String checkBeanName = null;
	private String errorMessage = null;
	
	
	public String getValidatorBeanName(){
		return this.checkBeanName;
	}
	public String getErrorMessage(){
		return this.errorMessage;
	}
	public String getCheckName(){
		return this.checkName;
	}
}
