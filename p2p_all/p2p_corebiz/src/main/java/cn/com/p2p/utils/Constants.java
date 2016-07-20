
package cn.com.p2p.utils;


import java.util.HashMap;
import java.util.Map;


public class Constants{
	/** 特殊字符常量 */
	public static final String BACKSLASH = "/";
	public static final String SLASH = "\\";
	public static final String LF = "\n";
	public static final String CR = "\r";
	public static final String COMMA = ",";
	public static final String EQUAL = "=";
	public static final String SEMICOLON = ";";
	public static final String COLON = ":";
	public static final String DOT = ".";
	public static final String BAR = "|";
	public static final String UNDERLINE = "_";
	public static final String BLANK = "";
	/** 后台-项目支付信息添加 (中金) */
	public static final String PAYACCOUNT_ADD_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_add.ftl";
	/** 后台-项目支付信息修改 (中金) */
	public static final String PAYACCOUNT_EDIT_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_edit.ftl";
	/** 后台-项目支付信息添加 (银行卡) */
	public static final String PAYACCOUNT_BANK_ADD_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_bank_add.ftl";
	/** 后台-项目支付信息修改 (银行卡) */
	public static final String PAYACCOUNT_BANK_EDIT_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_bank_edit.ftl";
	/** 后台-担保公司、担保费率模版 */
	public static final String PRODUCT_SHOW_GUARANTEE_FLAG_TEMPLATE = "/loan/apply/viewtab/guarantee.ftl";
	/** 后台-还款方式模版 */
	public static final String PRODUCT_INTERESTMANNER_TEMPLATE = "/loan/apply/viewtab/interestManner.ftl";
	/**
	 * 后台-项目支付信息--企业客户-股东合伙人列表-list模板
	 */
	public static final String PAYACCOUNT_TAB_TR_TEMPLATE = "/loan/apply/payaccount/payaccount_tab_tr.ftl";
	/**
	 * 后台-融资申请--企业客户查询数据模板
	 */
	public static final String ENTERPRISEMSG_TEMPLATE = "/loan/apply/enterprise/enterpriseMsg.ftl";
	/**
	 * 后台-融资申请--企业客户-股东合伙人添加模板
	 */
	public static final String SHAREHOLDER_ADD_TEMPLATE = "/loan/apply/enterprise/supplement/shareholder/shareholder_add.ftl";
	/**
	 * 后台-融资申请--企业客户-股东合伙人列表-list模板
	 */
	public static final String SHAREHOLDER_TAB_TR_TEMPLATE = "/loan/apply/enterprise/supplement/shareholder/shareholder_tab_tr.ftl";
	/**
	 * 后台-融资申请--企业客户-股东合伙人-查看模板
	 */
	public static final String SHAREHOLDER_VIEW_TEMPLATE = "/loan/apply/enterprise/supplement/shareholder/shareholder_view.ftl";
	/**
	 * 后台-融资申请--企业客户-股东合伙人-修改模板
	 */
	public static final String SHAREHOLDER_EDIT_TEMPLATE = "/loan/apply/enterprise/supplement/shareholder/shareholder_edit.ftl";
	/**
	 * 后台-融资申请--企业客户-股东合伙人修改-list模板
	 */
	public static final String SHAREHOLDER_EDIT_TR_TEMPLATE = "/loan/apply/enterprise/supplement/shareholder/shareholder_edit_tr.ftl";
	/**
	 * 后台-融资申请--企业客户-联系人添加模板
	 */
	public static final String CONTACTS_ADD_TEMPLATE = "/loan/apply/enterprise/supplement/contacts/contacts_add.ftl";
	/**
	 * 后台-融资申请--企业客户-联系人列表-list模板
	 */
	public static final String CONTACTS_TAB_TR_TEMPLATE = "/loan/apply/enterprise/supplement/contacts/contacts_tab_tr.ftl";
	
	/**
	 * 后台-融资申请--企业客户-联系人-查看模板
	 */
	public static final String CONTACTS_VIEW_TEMPLATE = "/loan/apply/enterprise/supplement/contacts/contacts_view.ftl";
	/**
	 * 后台-融资申请--企业客户-联系人-修改模板
	 */
	public static final String CONTACTS_EDIT_TEMPLATE = "/loan/apply/enterprise/supplement/contacts/contacts_edit.ftl";
	/**
	 * 后台-融资申请--企业客户-联系人修改-list模板
	 */
	public static final String CONTACTS_EDIT_TR_TEMPLATE = "/loan/apply/enterprise/supplement/contacts/contacts_edit_tr.ftl";
	
	
	
	
	/**
	 * 后台-融资申请--个人客户查询数据模板
	 */
	public static final String PERSONALMSG_TEMPLATE = "/loan/apply/personal/personalMsg.ftl";
	/**
	 * 后台-融资申请--基本信息--个人客户查询数据模板
	 */
	public static final String HOUSE_TEMPLATE = "/loan/apply/personal/rent.ftl";

	/** 后台-融资申请--个人客户-家庭成员信息添加模板 */
	public static final String FAMILY_ADD_TEMPLATE = "/loan/apply/personal/supplement/family/family_add.ftl";
	
	/** 后台-融资申请--个人客户-家庭成员信息列表-list模板 */
	public static final String FAMILY_TAB_TR_TEMPLATE = "/loan/apply/personal/supplement/family/family_tab_tr.ftl";
	
	/** 后台-融资申请--个人客户-家庭成员信息-查看模板 */
	public static final String FAMILY_VIEW_TEMPLATE = "/loan/apply/personal/supplement/family/family_view.ftl";
	
	/** 后台-融资申请--个人客户-家庭成员信息-修改模板 */
	public static final String FAMILY_EDIT_TEMPLATE = "/loan/apply/personal/supplement/family/family_edit.ftl";
	
	/** 后台-融资申请--个人客户-家庭成员信息修改-list模板 */
	public static final String FAMILY_EDIT_TR_TEMPLATE = "/loan/apply/personal/supplement/family/family_edit_tr.ftl";
	
	/** 后台-融资申请--个人客户-车产信息添加模板 */
	public static final String CAR_ADD_TEMPLATE = "/loan/apply/personal/supplement/car/car_add.ftl";
	
	/**后台-融资申请--个人客户-车产信息列表-list模板*/
	public static final String CAR_TAB_TR_TEMPLATE = "/loan/apply/personal/supplement/car/car_tab_tr.ftl";
	
	/**后台-融资申请--个人客户-车产信息-查看模板*/
	public static final String CAR_VIEW_TEMPLATE = "/loan/apply/personal/supplement/car/car_view.ftl";
	
	/** 后台-融资申请--个人客户-车产信息修改-list模板*/
	public static final String CAR_EDIT_TR_TEMPLATE = "/loan/apply/personal/supplement/car/car_edit_tr.ftl";
	
	/** 后台-融资申请--个人客户-车产信息-修改模板 */
	public static final String CAR_EDIT_TEMPLATE = "/loan/apply/personal/supplement/car/car_edit.ftl";
	
	/** 后台-融资申请--个人客户-信用卡信息添加模板 */
	public static final String CREDITCARD_ADD_TEMPLATE = "/loan/apply/personal/supplement/creditCard/creditCard_add.ftl";
	
	/**后台-融资申请--个人客户-信用卡信息列表-list模板*/
	public static final String CREDITCARD_TAB_TR_TEMPLATE = "/loan/apply/personal/supplement/creditCard/creditCard_tab_tr.ftl";
	
	/** 后台-融资申请--个人客户-信用卡信息-查看模板*/
	public static final String CREDITCARD_VIEW_TEMPLATE = "/loan/apply/personal/supplement/creditCard/creditCard_view.ftl";
	
	/** 后台-融资申请--个人客户-信用卡信息修改-list模板*/
	public static final String CREDITCARD_EDIT_TR_TEMPLATE = "/loan/apply/personal/supplement/creditCard/creditCard_edit_tr.ftl";
	
	/** 后台-融资申请--个人客户-信用卡信息-修改模板 */
	public static final String CREDITCARD_EDIT_TEMPLATE = "/loan/apply/personal/supplement/creditCard/creditCard_edit.ftl";
	
	/** 后台-融资申请--个人客户-房产信息添加模板 */
	public static final String HOUSE_ADD_TEMPLATE = "/loan/apply/personal/supplement/house/house_add.ftl";
	
	/**后台-融资申请--个人客户-房产信息列表-list模板*/
	public static final String HOUSE_TAB_TR_TEMPLATE = "/loan/apply/personal/supplement/house/house_tab_tr.ftl";
	
	/** 后台-融资申请--个人客户-房产信息-查看模板*/
	public static final String HOUSE_VIEW_TEMPLATE = "/loan/apply/personal/supplement/house/house_view.ftl";
	
	/** 后台-融资申请--个人客户-房产信息修改-list模板*/
	public static final String HOUSE_EDIT_TR_TEMPLATE = "/loan/apply/personal/supplement/house/house_edit_tr.ftl";
	
	/** 后台-融资申请--个人客户-房产信息-修改模板 */
	public static final String HOUSE_EDIT_TEMPLATE = "/loan/apply/personal/supplement/house/house_edit.ftl";
	
	/** 后台-融资申请--企业客户-联系人添加模板  */
	public static final String CONTACT_PER_ADD_TEMPLATE = "/loan/apply/personal/supplement/contact/contact_add.ftl";
	
	/** 后台-融资申请--企业客户-联系人列表-list模板 */
	public static final String CONTACT_PER_TAB_TR_TEMPLATE = "/loan/apply/personal/supplement/contact/contact_tab_tr.ftl";
	
	/** 后台-融资申请--企业客户-联系人-查看模板 */
	public static final String CONTACT_PER_VIEW_TEMPLATE = "/loan/apply/personal/supplement/contact/contact_view.ftl";
	
	/** 后台-融资申请--企业客户-联系人-修改模板 */
	public static final String CONTACT_PER_EDIT_TEMPLATE = "/loan/apply/personal/supplement/contact/contact_edit.ftl";
	
	/** 后台-融资申请--企业客户-联系人修改-list模板 */
	public static final String CONTACT_PER_EDIT_TR_TEMPLATE = "/loan/apply/personal/supplement/contact/contact_edit_tr.ftl";
	/** 后台-融资申请--企业客户-联系人修改-list模板 */
	public static final String INSTRUCTIONS_TEMPLATE = "/loan/apply/viewtab/instructions.ftl";
	/** 后台-融资申请--补充信息-delete模板 */
	public static final String DELETE_TEMPLATE = "/loan/apply/viewtab/delete.ftl";
	/** 后台-融资申请--个人融资-办公场地性质-租金模板 */
	public static final String RENT_TEMPLATE = "/loan/apply/viewtab/rent.ftl";
	/** 后台-融资申请--个人融资-办公场地性质-租金模板 */
	public static final String CAR_SUPPLEMENT_TEMPLATE = "/loan/apply/personal/supplement/car/car_supplement.ftl";
	/** 后台-融资申请--个人融资-办公场地性质-租金模板 */
	public static final String HOUSE_SUPPLEMENT_TEMPLATE = "/loan/apply/personal/supplement/house/house_supplement.ftl";
	
	/** 后台-待审核的融资--风控审核*/
	public static final String NOTATION_TEMPLATE = "/loan/pending/notationView.ftl";
	
	/** 后台-待审核的融资--驳回信息*/
	public static final String REJECT_TEMPLATE = "/loan/pending/rejectView.ftl";
	
	/** 后台-财务审批--放款和退款*/
	public static final String FINANCIAL_APPROVAL_TEMPLATE = "/loan/financialApproval/password.ftl";
	/** 后台-还款途径*/
	public static final String ACCOUNT_CHANGE_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_change.ftl";
	/** 后台-还款账户-提交*/
	public static final String ACCOUNT_CHANGE_TD_TEMPLATE = "/loan/apply/viewtab/payaccount/payaccount_change_td.ftl";
	
	/** 后台-业务系统管理-业务人员管理-修改 */
	public static final String SYSTEM_BUSINESSSTAFF_EDIT = "/system/viewtab/businessStaffEditPop.ftl";
	/** 后台-业务系统管理-业务人员管理-添加 */
	public static final String SYSTEM_BUSINESSSTAFF_ADD = "/system/viewtab/businessStaffAddPop.ftl";
	/** 后台-内容管理-友情链接管理-修改 */
	public static final String SYSTEM_LINKS_EDIT = "/content/link/edit.ftl";
	
	
	
	/** 后台-内容管理-栏目内容管理-一级标题-修改 */
	public static final String CONTENT_CATEGORY_FIRSTTITLE_EDIT = "/content/viewtab/categoryFirstTitleEditPop.ftl";
	/** 后台-内容管理-栏目内容管理-二级标题-添加 */
	public static final String CONTENT_CATEGORY_SECONDTITLE_ADD = "/content/viewtab/categorySecondTitleAddPop.ftl";
	/** 后台-内容管理-栏目内容管理-二级标题-修改 */
	public static final String CONTENT_CATEGORY_SECONDTITLE_EDIT = "/content/viewtab/categorySecondTitleEditPop.ftl";
	
	/** 后台-业务系统管理-修改*/
	public static final String SYSTEM_PRODUCT_EDIT = "/system/product/edit.ftl";
	/** 后台-系统角色管理-修改*/
	public static final String SYSTEM_ROLE_EDIT = "/system/role/edit.ftl";
	
	/** 后台- 投资客户管理-投资详细查看*/
	public static final String INVESTOR_INVESTDETAIL_TEMPLATE="/customer/investors/detail.ftl";
	/** 后台- 投资客户管理-还款计划*/
	public static final String INVESTOR_REPAYMENTPLAN_TEMPLATE="/customer/investors/repaymentPlan.ftl";
	
	public static final String INVEST_DETAIL_TEMPLATE="/loan/viewtab/investRepayDetail.ftl";
	
	
	
	
	
	
	
	
	
	
	
	//==========================================这是分割线=====================================================
	
	/**前台 - 投资记录模板*/
	public static final String INVEST_RECORD_TEMPLATE="/invest/viewtab/investRecord.ftl";
	/**前台 - 投资确认模板*/
	public static final String INVEST_VERIFY_TEMPLATE="/invest/viewtab/investVerify.ftl";
	
	/**前台 - 用户修改密码模板*/
	public static final String SECURITY_PASSWOED_CHANGE="/account/security/viewtab/passwordChange.ftl";
	/**前台 - 用户修改电话模板*/
	public static final String SECURITY_CELLPHONE_CHANGE="/account/security/viewtab/cellphoneChange.ftl"; 
	/**前台 - 用户修改邮箱模板*/
	public static final String SECURITY_MAIL_CHANGE="/account/security/viewtab/mailChange.ftl";
	
	/**前台 - 个人中心我的投资待生效模版*/
	public static final String MYINVEST_PENDING_TEMPLATE="/account/myinvest/viewtab/investPending.ftl";
	/**前台 - 个人中心我的投资回款中模版*/
	public static final String MYINVEST_RECEIVABLES_TEMPLATE="/account/myinvest/viewtab/investReceivables.ftl";
	/**前台 - 个人中心我的投资已完成模版*/
	public static final String MYINVEST_COMPLETE_TEMPLATE="/account/myinvest/viewtab/investComplete.ftl";
	/**前台 - 个人中心我的投资投资明细模版*/
    public static final String MY_INVEST_DETAIL_TEMPLATE="/account/myinvest/viewtab/investDetail.ftl";
    
	/**前台 - 投资校验模板*/
	public static final String INVEST_CHECK_TEMPLATE="/invest/viewtab/investCheck.ftl";
	/**前台 - 投资回款计划模板*/
	public static final String INVEST_PLAN_TEMPLATE="/invest/viewtab/investPlan.ftl";
	/**前台 - 项目说明书模板*/
	public static final String INVEST_INSTRUCTIONS_TEMPLATE="/invest/viewtab/instructions.ftl";
	/**前台 - 投资支付模板*/
	public static final String INVEST_PAYTRUE_TEMPLATE="/invest/viewtab/paytrue.ftl";
	/**前台 - 个人中心 - 回款计划模版*/
	public static final String PAYMENT_PLAN_INVEST_DETAIL="/account/paymentPlan/investDetail.ftl";
	
	
	/***************************系统设定组编码**************************/
	/** 基本站点设置*/
	public static final String BASIC_SETTING = "basic_setting";
	/** 邮箱设置*/
	public static final String MAIL_SETTING = "mail_setting";
	/** 短信设置*/
	public static final String SMS_SETTING = "sms_setting";
	/** 费用相关参数*/
	public static final String COST_SETTING = "cost_setting";
	/** 时间相关参数*/
	public static final String DATE_SETTING = "date_setting";
	/** 合同参数*/
	public static final String CONTRACT_SETTING = "contract_setting";
	/** 项目说明书配置*/
	public static final String MANUAL_SETTING = "manual_setting";
	/*****************************************************************/
	
	
	
	/***********************************************************************
	 * **************************判断状态常量start******************************
	 * *********************************************************************
	 */
	/** 产品类型：(1：企业) */
	public static final String PRODUCT_TYPE_ENTERPRISE = "1";
	
	/** 产品类型：(0：个人) */
	public static final String PRODUCT_TYPE_PERSONAL = "0";
	
	/** 产品是否显示担保方信息：显示 */
	public static final String PRODUCT_GUARANTEE_DISPLAY_SHOW = "1";
	
	/** 产品是否显示担保方信息：不显示 */
	public static final String PRODUCT_GUARANTEE_DISPLAY_NONE = "0";
	
	/** 产品状态：启用 */
	public static final String PRODUCT_STATUS_ENABLE = "1";
	
	/** 产品状态：禁用 */
	public static final String PRODUCT_STATUS_DISABLE = "0";
	
	/** 栏目内容管理-页面类别-单页 */
	public static final String CATEGORY_PAGETYPE_SINGLE = "0";
	
	/** 栏目内容管理-页面类别-列表 */
	public static final String CATEGORY_PAGETYPE_LIST = "1";
	
	/** 有效flag：有效 */
    public static final String VALID_FLAG_VALID= "1";
    
    /** 有效flag：无效 */
    public static final String VALID_FLAG_INVALID = "0";
	
	
	/****************************************
	 * ***********判断状态常量end**************
	 * **************************************
	 */
	
	/************************************
	 * 服务端校验常量 start**************************
	 ***************************************/
	/**
	 * 中文汉字
	 */
	public static final String CHECK_CHINESE = "chinese";

	/**
	 * 半角字符
	 */
	public static final String CHECK_ONLYBYTE = "onlybyte";

	/**
	 * 邮编
	 */
	public static final String CHECK_CHINAZIP = "chinaZip";

	/**
	 * 英文字母大小写
	 */
	public static final String CHECK_ONLYLETTERSP = "onlyLetterSp";

	/**
	 * 不接受特殊字符
	 */
	public static final String CHECK_ONLYLETTERNUMBER = "onlyLetterNumber";

	/**
	 * 只能填数字
	 */
	public static final String CHECK_ONLYNUMBERSP = "onlyNumberSp";

	/**
	 * IP地址
	 */
	public static final String CHECK_IPV4 = "ipv4";

	/**
	 * url
	 */
	public static final String CHECK_URL = "url";

	/**
	 * 手机号码
	 */
	public static final String CHECK_PHONE = "phone";

	/**
	 * 银行卡号
	 */
	public static final String CHECK_BANKCARD = "bankCard";

	/**
	 * 军官证号码
	 */
	public static final String CHECK_MILITARYID = "militaryId";

	/**
	 * 身份证号码
	 */
	public static final String CHECK_IDENTITYCARD = "identityCard";

	/**
	 * 只能输入由数字、26个英文字母或者下划线组成的字符串
	 */
	public static final String CHECK_ALLCHARACTER = "allcharacter";

	/**
	 * 国内电话号码
	 */
	public static final String CHECK_TELEPHONE = "telephone";

	/**
	 * 邮件
	 */
	public static final String CHECK_EMAIL = "email";

	/**
	 * 日期
	 */
	public static final String CHECK_DATEFORMAT = "dateFormat";

	/**
	 * 日期时间
	 */
	public static final String CHECK_DATETIMEFORMAT = "dateTimeFormat";

	/**
	 * 日期时间24小时格式
	 */
	public static final String CHECK_DATETIMEFORMAT24 = "dateTimeFormat24";

	/**
	 * 整数
	 */
	public static final String CHECK_INTEGER = "integer";

	/**
	 * 数字
	 */
	public static final String CHECK_NUMBER = "number";

	/**
	 * 百分比
	 */
	public static final String CHECK_RATEFORMAT = "rateFormat";

	/**
	 * 日期范围
	 */
	public static final String CHECK_DATERANGE = "dateRange";

	/**
	 * 时间范围
	 */
	public static final String CHECK_DATETIMERANGE = "dateTimeRange";

	/**
	 * 最小值
	 */
	public static final String CHECK_MIN = "min";

	/**
	 * 最大值
	 */
	public static final String CHECK_MAX = "max";

	/**
	 * 早于
	 */
	public static final String CHECK_PAST = "past";

	/**
	 * 晚于
	 */
	public static final String CHECK_FUTURE = "future";

	/**
	 * 相等
	 */
	public static final String CHECK_EQUALS = "equals";

	/************************************
	 * 服务端校验常量 end**************************
	 ***************************************/

	/*******************************************
	 * 用于比较的常量 start ************************
	 *******************************************/
	/**
	 * 常量  "0"
	 */
	public static final String STR_0="0";
	/**
	 * 常量  "1"
	 */
	public static final String STR_1="1";
	/**
	 * 常量  "2"
	 */
	public static final String STR_2="2";
	/**
	 * 常量  "3"
	 */
	public static final String STR_3="3";
	/**
	 * 常量  "4"
	 */
	public static final String STR_4="4";

	
	/**
	 * 常量  "C"
	 */
	public static final String STR_C="C";
	
	
	
	
	
	/*******************************************
	 * 用于比较的常量 end ************************
	 *******************************************/
	/*******************************************
	 * 操作描述 start ************************
	 *******************************************/
	public static final String LOAN_APPLY="提交融资申请";
	
	public static final String AGAIN_LOAN_APPLY="再次提交";
	
	public static final String APPROVE_PENDING="初级审核";
	public static final String APPROVE_LOAN="担保审核";
	public static final String FINANCIAL_LOAN="最终审核";
	
	
	/** 延时*/
	public static final String STR_DELAYED_START="延时至[";
	public static final String STR_DELAYED_END="]";
	
	/** 放款申请*/
    public static final String STR_LOAN_APPLY="放款申请";
	
	/** 流标退款 */
    public static final String STR_FLOW_REFUND="流标退款";
	
    /** 超募退款 */
    public static final String STR_ALTRA_REFUND="超募退款";
	
    
    /**财务批准放款*/
    public static final String STR_FINANCIAL_APPROVAL_LOAN="财务批准放款";
    /**还款申请*/
    public static final String STR_LOANEE_REPAYMENT_APPLY="[%s/%s期]还款申请";
    /**财务批准放款*/
    public static final String STR_FINANCIAL_APPROVAL_REPAYMENT="[%s/%s期]财务批准还款";
    /**财务批准担保代偿*/
    public static final String STR_FINANCIAL_APPROVAL_GUARANTOR_REPAYMENT="财务批准担保代偿";
    /**财务批准平台代偿*/
    public static final String STR_FINANCIAL_APPROVAL_P2PPLATFORMPARTY_REPAYMENT="财务批准平台代偿";
	/*******************************************
	 * 操作描述 end ************************
	 *******************************************/
	
	
    /*************************************************************************
     * 系统设定(SETTING_CODE)
     *******************************************************************/
    
    /** 逾期滞纳金费率 */
    public static final String SETTING_CODE_FEE_DELAYING="fee_delaying";
    /** 逾期罚息费率 */
    public static final String SETTING_CODE_FEE_OVERDUE="fee_overdue";
    /** 项目价值最低折价率 */
    public static final String SETTING_CODE_CONVERT_MONEY_MIN = "convert_money_min";
    /** 项目价值最高折价率 */
    public static final String SETTING_CODE_CONVERT_MONEY_MAX = "convert_money_max";
    /** 服务费率 */
    public static final String SETTING_CODE_SERVICE = "service";
	
    
	/**
	 * CiccPaymentLog status
	 */
	public static Map<Integer,String> getCiccPaymentLogStatus(){

		Map<Integer,String> ciccPaymentLogStatus = new HashMap<Integer,String>();
		ciccPaymentLogStatus.put(0,"处理中");
		ciccPaymentLogStatus.put(1,"处理成功");
		ciccPaymentLogStatus.put(2,"处理失败");
		return ciccPaymentLogStatus;
	}


	public static String getCiccPaymentLogStatus(Integer status){

		Map<Integer,String> ciccPaymentLogStatus = getCiccPaymentLogStatus();
		return ciccPaymentLogStatus.get(status);
	}
}
