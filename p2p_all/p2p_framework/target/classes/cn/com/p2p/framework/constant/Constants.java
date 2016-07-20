
package cn.com.p2p.framework.constant;


import java.util.HashMap;
import java.util.Map;


public class Constants{
	
	/** 平台默认的商户ID */
	public static String PLAT_TENANT_CD = "001";

	/** 共通验证，错误消息KEY */
	public static String COMM_ERROR_MESSAGE_KEY = "common_error_msg_key";
	public static String COMM_ERROR_MESSAGE_LOGIN_KEY = "common_error_msg_login_key";

	/***************************************************************************************************************************************************
	 * **********************通用区域**********************通用区域********************* 通用区域*********************通用区域*****************************
	 *************************************************************************************************************************************************/
	/**
	 * 通用常量：数据状态-启用 对应资源文件COMMON.STATE
	 */
	public static final String DB_STATE_NORMAL = "NORMAL";

	/**
	 * 通用常量：数据状态-禁用 对应资源文件COMMON.STATE
	 */
	public static final String DB_STATE_DISABLED = "DISABLED";

	/**
	 * 通用常量：中金统一账户平台
	 */
	public static final String UNIFIED_PLATFORM = "1";

	/**
	 * 画面模式_浏览模式
	 */
	public static final int PAGE_MODEL_VIEW = 1;

	/**
	 * 画面模式_添加模式
	 */
	public static final int PAGE_MODEL_ADD = 2;

	/**
	 * 画面模式_修改模式
	 */
	public static final int PAGE_MODEL_EDIT = 3;

	/**
	 * 上传文件路径
	 */
	public static final String STR_UPLOAN_PATH = "/upload/";

	/**
	 * 项目模板存放共同路径
	 */
	// public static final String TEMPLATE_PATH = "/WEB-INF/view";
	/* 前台个人中心融资银行卡添加模板 */
	public static final String ADDBANKDIALOG_TEMPLATE = "addbankdialog.ftl";

	/* 前台个人中心融资银行卡修改模板 */
	public static final String UPDATEBANKDIALOG_TEMPLATE = "updatebankdialog.ftl";

	/* 前台个人中心融资银行卡模板存放路径 */
	public static final String FINANCINGINFO_TEMPLATE_PATH = "/account/financingbank";

	/**
	 * 前台cost分页模板名称
	 */
	public static final String PAGETINFO_COST_TEMPLATE = "/common/ajax_cost_page.ftl";

	/**
	 * 前台refund分页模板名称
	 */
	public static final String PAGETINFO_REFUND_TEMPLATE = "/common/ajax_refund_page.ftl";

	/**
	 * 前台分页模板名称
	 */
	public static final String PAGETINFO_TEMPLATE = "/common/ajax_page.ftl";

	/**
	 * 前台退回列表分页模板名称
	 */
	public static final String RETURNPAGE_TEMPLATE = "/account/myfinancing/returnPage.ftl";

	/**
	 * 前台安全中心绑定手机弹窗模板名称
	 */
	public static final String PHONE_DIALOG_TEMPLATE = "/account/security/phone_dialog.ftl";

	/**
	 * 前台安全中心短信弹窗模板名称
	 */
	public static final String MSG_DIALOG_TEMPLATE = "/account/security/msg_dialog.ftl";

	/**
	 * 前台退回列表分页模板名称
	 */
	public static final String INVEST_RETURNPAGE_TEMPLATE = "/account/investlist/returnPage.ftl";

	/**
	 * 前台投资退回列表分页模板名称
	 */
	public static final String INVESTPAGE_TEMPLATE = "/account/myfinancing/investPage.ftl";

	/**
	 * 前台投资退回列表分页模板名称
	 */
	public static final String INVEST_INVESTPAGE_TEMPLATE = "/account/investlist/investPage.ftl";

	/**
	 * 前台还款计划列表分页模板名称
	 */
	public static final String PLANPAGE_TEMPLATE = "/account/myfinancing/planPage.ftl";

	/**
	 * 前台还款计划列表分页模板名称
	 */
	public static final String INVEST_PLANPAGE_TEMPLATE = "/account/investlist/planPage.ftl";

	/**
	 * 后台添加融资选择客户后输出客户信息模板名
	 */
	public static final String FINANCINGINFO_TEMPLATE = "/common/ajax_financing.ftl";

	/**
	 * 后台融资修改后台校验消息模板
	 */
	public static final String ERRORMSG_TEMPLATE = "/financing/all/errorMsg.ftl";

	/************************************************************************************** 企业客户信息 ******************************************************/
	/**
	 * 后台融资修改企业客户信息编辑模式
	 */
	public static final String EDITLOANENTERPRISEINFO_TEMPLATE = "/financing/auditting/editLoanEnterpriseInfo.ftl";

	/**
	 * 后台融资修改企业客户信息查看模式
	 */
	public static final String VIEWLOANENTERPRISEINFO_TEMPLATE = "/financing/viewLoanEnterpriseInfo.ftl";

	/**
	 * 后台融资修改企业客户法人信息编辑模式
	 */
	public static final String EDITLOANLEGAL_TEMPLATE = "/financing/auditting/editLoanLegal.ftl";

	/**
	 * 后台融资修改企业客户法人信息查看模式
	 */
	public static final String VIEWLOANLEGAL_TEMPLATE = "/financing/viewLoanLegal.ftl";

	/**
	 * 后台融资修改企业客户财务信息编辑模式
	 */
	public static final String EDITLOANFINANCIAL_TEMPLATE = "/financing/auditting/editLoanFinancial.ftl";

	/**
	 * 后台融资修改企业客户财务信息查看模式
	 */
	public static final String VIEWLOANFINANCIAL_TEMPLATE = "/financing/viewLoanFinancial.ftl";

	/**
	 * 后台融资修改企业客户社保信息编辑模式
	 */
	public static final String EDITLOANSOCIALSECURITY_TEMPLATE = "/financing/auditting/editLoanSocialSecurity.ftl";

	/**
	 * 后台融资修改企业客户社保信息查看模式
	 */
	public static final String VIEWLOANSOCIALSECURITY_TEMPLATE = "/financing/viewLoanSocialSecurity.ftl";

	/**
	 * 后台融资修改企业合伙人信息编辑模式
	 */
	public static final String EDITLOANSHAREHOLDERPARTNERS_TEMPLATE = "/financing/auditting/editLoanShareholderPartners.ftl";

	/**
	 * 后台融资修改企业合伙人信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANSHAREHOLDERPARTNERSLIST_TEMPLATE = "/financing/viewLoanShareholderPartnersList.ftl";

	/**
	 * 后台融资修改企业客户联系人信息编辑模式
	 */
	public static final String EDITLOANENTERPRISECONTACT_TEMPLATE = "/financing/auditting/editLoanEnterpriseContact.ftl";

	/**
	 * 后台融资修改企业客户联系人信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANENTERPRISECONTACTLIST_TEMPLATE = "/financing/viewLoanEnterpriseContactList.ftl";

	/************************************************************************************** 个人客户信息 ******************************************************/
	/**
	 * 后台个人客户工作信息添加补充资料模板名称
	 */
	public static final String SUPPLEMENTINFO_TEMPLATE = "/customer/personalfinanccust/Supplement.ftl";

	/**
	 * 后台企业客户社保选择否在选择是后插入模版
	 */
	public static final String SOCIAL_SECURITYPARTINFO_TEMPLATE = "/customer/financcust/socialSecurityPart.ftl";

	/**
	 * 后台个待审核融资查看个人工作信息添加补充资料模板名称
	 */
	public static final String LOANSUPPLEMENTINFO_TEMPLATE = "/customer/personalfinanccust/loanSupplement.ftl";

	/**
	 * 后台融资修改个人信息编辑模式
	 */
	public static final String EDITLOANUSERINFO_TEMPLATE = "/financing/auditting/editLoanUserInfo.ftl";

	/**
	 * 后台融资修改个人信息查看模式
	 */
	public static final String VIEWLOANUSERINFO_TEMPLATE = "/financing/viewLoanUserInfo.ftl";

	/**
	 * 后台融资修改个人工作信息编辑模式
	 */
	public static final String EDITLOANUSERJOB_TEMPLATE = "/financing/auditting/editLoanUserJob.ftl";

	/**
	 * 后台融资修改个人工作信息信息查看模式
	 */
	public static final String VIEWLOANUSERJOB_TEMPLATE = "/financing/viewLoanUserJob.ftl";

	/**
	 * 后台融资修改个人家庭成员信息编辑模式
	 */
	public static final String EDITFAMILYMEMBER_TEMPLATE = "/financing/auditting/editLoanFamilyMember.ftl";

	/**
	 * 后台融资修改个人房产信息编辑模式
	 */
	public static final String EDITLOANPROPERTY_TEMPLATE = "/financing/auditting/editLoanProperty.ftl";

	/**
	 * 后台融资修改个人车产信息编辑模式
	 */
	public static final String EDITLOANCARMSG_TEMPLATE = "/financing/auditting/editLoanCarMsg.ftl";

	/**
	 * 后台融资修改个人车产信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANCARMSGLIST_TEMPLATE = "/financing/viewLoanCarMsgList.ftl";

	/**
	 * 后台融资修改个人银行卡信息编辑模式
	 */
	public static final String EDITLOANUSERBANKACCOUNT_TEMPLATE = "/financing/auditting/editLoanUserBankAccount.ftl";

	/**
	 * 后台融资修改个人银行卡信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANUSERBANKACCOUNTLIST_TEMPLATE = "/financing/viewLoanUserBankAccountList.ftl";

	/**
	 * 后台融资修改个人联系人信息编辑模式
	 */
	public static final String EDITLOANCONTACT_TEMPLATE = "/financing/auditting/editLoanContact.ftl";

	/**
	 * 后台融资修改个人联系人信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANCONTACTLIST_TEMPLATE = "/financing/viewLoanContactList.ftl";

	/**
	 * 后台融资修改个人信用卡信息编辑模式
	 */
	public static final String EDITLOANCREDITCARD_TEMPLATE = "/financing/auditting/editLoanCreditCard.ftl";

	/**
	 * 后台融资修改个人信用卡信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANCREDITCARDLIST_TEMPLATE = "/financing/viewLoanCreditCardList.ftl";

	/**
	 * 后台融资修改个人家庭成员信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWFAMILYMEMBERLIST_TEMPLATE = "/financing/viewLoanFamilyMemberList.ftl";

	/**
	 * 后台融资修改个人房产信息添加或修改成功后插入列表模板
	 */
	public static final String VIEWLOANPROPERTYLIST_TEMPLATE = "/financing/viewLoanPropertyList.ftl";

	/**
	 * 后台添加融资选择客户后输出融资产品模板名
	 */
	public static final String SELECT_TEMPLATE = "/common/ajax_select.ftl";

	/**
	 * 前台地址
	 */
	public static final String SELECT_CHECK_TEMPLATE = "/common/ajax_select_check.ftl";

	/**
	 * 前台需要验证的地址
	 */
	public static final String PRODUCTION_TEMPLATE = "/common/ajax_product_select.ftl";

	/**
	 * 后台添加融资选择客户后输出还款途径模板名
	 */
	public static final String REPAYMENTWAY_TEMPLATE = "/common/ajax_repayment_select.ftl";

	/**
	 * 后台新增融资还款方式
	 */
	public static final String PRODUCT_REPAYTYPE = "/common/ajax_product_repayType_select.ftl";

	/**
	 * 前台低级级联下拉列表2模板名称 为防止一个页面同时出现两个地址级联标签而存在
	 */
	public static final String SELECT_ADDRESS_TEMPLATE = "/common/ajax_address_select.ftl";

	/**
	 * 后台公司类型->公司名->部门->职位级联下拉列表模板名称
	 */
	public static final String SELECT_STAFF_TEMPLATE = "/common/ajax_staff_select.ftl";

	/**
	 * 后台流程管理、添加任务、公司类型->公司名->部门->职员下拉列表模板名称
	 */
	public static final String SELECT_TASK_TEMPLATE = "/common/ajax_task_select.ftl";

	/**
	 * 后台待办事项配置管理 流程->任务 
	 */
	public static final String SELECT_PROCESSTASK_TEMPLATE = "/common/ajax_processtask_select.ftl";

	/**
	 * 提现分页模板
	 */
	public static final String CASH_PAGE_TEMPLATE="account/cash/page.ftl";
	
	/**
	 * 充值分页模板
	 */
	public static final String COST_PAGE_TEMPLATE="account/cost/page.ftl";
	
	
	/**
	 * 后台流程管理、添加任务、公司类型专用:平台，1
	 */
	public static final Integer COMP_PLATFORM = 1;

	/**
	 * 后台流程管理、添加任务、公司类型专用:担保公司，2
	 */
	public static final Integer COMP_WAR = 2;

	/**
	 * 后台流程管理、添加任务、公司类型专用:小贷公司，3
	 */
	public static final Integer COMP_SLOAN = 3;

	/**
	 * 后台流程管理、添加任务、公司类型专用:保险公司，4
	 */
	public static final Integer COMP_INSURANCE = 4;

	/**
	 * 前台融资银行卡模板名称
	 */
	public static final String FINANCINGINFO_TEMPLATE2 = "/account/financingbank/addbankdialog.ftl";

	public static final String FINANCINGINFO_TEMPLATE3 = "/account/financingbank/updatebankdialog.ftl";

	/**
	 * 前台融资消息中心模板名称
	 */
	public static final String MESSAGE_VIEW = "/account/message/viewmessage.ftl";

	/**
	 * 前台融资申请列表模板名称
	 */
	public static final String APPLY_DIALOG = "/account/myfinancing/applydialog.ftl";

	/**
	 * 页面条数
	 */
	public static final int PAGE_SIZE = 20;

	public static final int PAGE_SIZE_10 = 10;

	public static final int PAGE_SIZE_6 = 6;

	public static final int PAGE_SIZE_4 = 4;

	public static final String BOTTLE_EQUIP_CODE = "QP";

	/**
	 * 查询当前日期子项参数类型_年份
	 */
	public static final String SELECT_DATE_YEAR = "YEAR";

	/**
	 * 查询当前日期子项参数类型_月份
	 */
	public static final String SELECT_DATE_MONTH = "MONTH";

	/**
	 * 查询当前日期子项参数类型_日
	 */
	public static final String SELECT_DATE_DYAS = "DAYS";

	/**
	 * 查询当前日期子项参数类型_星期
	 */
	public static final String SELECT_DATE_WEEK = "WEEK";

	/**
	 * 日期转换格式yyyy/MM/dd
	 */
	public static final String FORMAT_SLASH = "yyyy/MM/dd";

	/**
	 * 日期转换格式yyyy-MM-dd
	 */
	public static final String FORMAT_SLASH_YYYY_MM_DD = "yyyy-MM-dd";

	// -------------------------------------------------服务端校验专用----------------------------------------------------------
	public static final String SERVER_VALIDATE_STRING = "String";

	public static final String SERVER_VALIDATE_NUMBER = "Number";

	public static final String SERVER_VALIDATE_LENGTH = "Length";

	public static final String SERVER_VALIDATE_CUSTOM = "Custom";

	public static final String SERVER_VALIDATE_RANGE = "Range";

	public static final String SERVER_VALIDATE_DATE = "Date";

	// -------------------------------------------------服务端校验专用----------------------------------------------------------
	// -------------------------------------------------用户操作类型专用----------------------------------------------------------
	/**
	 * 用户操作类型专用:登录，0
	 */
	public static final Integer YH_CZLX_DL = 0;

	/**
	 * 用户操作类型专用:注销，1
	 */
	public static final Integer YH_CZLX_ZX = 1;

	/**
	 * 用户操作内容专用：登录
	 */
	public static final String YH_CZNR_DL = "登录";

	/**
	 * 用户操作内容专用：注销登录
	 */
	public static final String YH_CZNR_ZX = "注销登录";

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
	// -------------------------------------自定义常量地区---------------------------------------------------
	/***************************************************************************************************************************************************
	 * **********************字典区域**********************字典区域********************* 字典区域*********************字典区域*****************************
	 * 变量名都要以DICT_开头
	 *************************************************************************************************************************************************/
	public static final String DICT_MENU_TYPE = "menu_type";

	public static final String DICT_ON_OFF = "on_off";

	public static final String DICT_ON_OFF_ENABLED = "enabled";

	public static final String DICT_ON_OFF_DISABLED = "disabled";

	/**
	 * int表示的启动禁用状态，禁用：0，见于
	 */
	public static final Integer DISABLEDNO = 0;

	/**
	 * int表示的启动禁用状态，启动：1，见于
	 */
	public static final Integer ENABLENO = 1;

	/**
	 * short表示的启动禁用状态，禁用：0，见于
	 */
	public static final short DISABLEDSH = 0;

	/**
	 * short表示的启动禁用状态，启动：1，见于
	 */
	public static final short ENABLESH = 1;

	/**
	 * string表示的启动禁用状态，禁用：0，见于文章管理
	 */
	public static final String DISABLEDSTR = "0";

	/**
	 * string表示的启动禁用状态，启动：1，见于文章管理
	 */
	public static final String ENABLESTR = "1";

	/**
	 * 布尔值表示的启动禁用状态，禁用：false，见于栏目管理
	 */
	public static final boolean DISABLEDBO = false;

	/**
	 * 布尔值表示的启动禁用状态，启动：true，见于栏目管理
	 */
	public static final boolean ENABLEBO = true;

	/**
	 * Long类型值表示的启动禁用状态，禁用：0L，见于字典管理
	 */
	public static final long DISABLEDLO = 0L;

	/**
	 * Long类型值表示的启动禁用状态，启动：1L，见于字典管理
	 */
	public static final long ENABLELO = 1L;

	/**
	 * 还款方式
	 */
	public static final String DICT_REPAYMENT = "repayment";// 还款方式

	public static final String DICT_REPAYMENT_DISPOSABLE_P_I = "disposable_principal_interest";// 项目到期日还本付息

	public static final String DICT_REPAYMENT_MATCHING_P = "matching_principal";// 等额本金

	public static final String DICT_REPAYMENT_MATCHING_S = "matching_service";// 等额本息

	public static final String DICT_REPAYMENT_MONTHLY_I = "monthly_interest";// 按月付息到期还本

	/**
	 * 字典数据类型,一般common和基本basic
	 */
	public static final String COMMON = "common";

	public static final String BASIC = "basic";

	/**
	 * 接单员
	 */
	public static final String FMROLE_ORDER_TAKERS = "Order_takers";

	/**
	 * 派单员
	 */
	public static final String FMROLE_ORDER_SENDER = "Order_sender";

	/**
	 * 业务员
	 */
	public static final String FMROLE_FIN_SALESMAN = "Fin_Salesman";

	// ===============融资状态 start===================
	/**
	 * 融资状态
	 */
	public static final String DICT_LOAN_STATUS = "loan_status";// 融资状态

	/**
	 * 待审核
	 */
	public static final String DICT_LOAN_STATUS_PENDING_AUDIT = "pending_audit"; // 待审核

	/**
	 * 审核中
	 */
	public static final String DICT_LOAN_STATUS_AUDITTING = "auditting"; // 审核中

	/**
	 * 招标中
	 */
	public static final String DICT_LOAN_STATUS_INVESTING = "investing"; // 招标中

	/**
	 * 流标退款中
	 */
	public static final String DICT_LOAN_STATUS_RETURNING = "returning"; // 流标退款中

	/**
	 * 流标
	 */
	public static final String DICT_LOAN_STATUS_RETURNED = "returned"; // 流标

	/**
	 * 已接单
	 */
	public static final String DICT_LOAN_STATUS_HAVE_ORDERS = "have_orders"; // 已接单

	/**
	 * 申请中
	 */
	public static final String DICT_LOAN_STATUS_APPLYING = "applying"; // 申请中

	/**
	 * 已受理
	 */
	public static final String DICT_LOAN_STATUS_HAVE_ACCEPTED = "have_accepted"; // 已受理

	/**
	 * 验证未通过
	 */
	public static final String DICT_LOAN_STATUS_NOT_VALIDATION = "not_validation"; // 验证未通过

	/**
	 * 已派单
	 */
	public static final String DICT_LOAN_STATUS_SENT_SINGLE = "sent_single"; // 已派单

	/**
	 * 已提审
	 */
	public static final String DICT_LOAN_STATUS_HAVE_SUBMIT_AUDIT = "have_submit_audit"; // 已提审

	/**
	 * 资验未通过
	 */
	public static final String DICT_LOAN_STATUS_NOT_QALIFICATIONS = "not_qalifications"; // 资验未通过

	/**
	 * 小贷公司审核不通过
	 */
	public static final String DICT_LOAN_STATUS_PETTY_LOAN_AUDIT_FAILED = "petty_loan_audit_failed"; // /小贷公司审核不通过

	/**
	 * 待指派担保
	 */
	public static final String DICT_LOAN_STATUS_WAIT_ASSIGN_GUARANTEE = "wait_assign_guarantee"; // 待指派担保

	/**
	 * 等待担保审核
	 */
	public static final String DICT_LOAN_STATUS_WAIT_GUARANTEE_AUDIT = "wait_guarantee_audit"; // 等待担保审核

	/**
	 * 担保公司审核不通过
	 */
	public static final String DICT_LOAN_STATUS_GUARANTEE_AUDIT_FAILED = "guarantee_audit_failed"; // 担保公司审核不通过

	/**
	 * 担保审核通过
	 */
	public static final String DICT_LOAN_STATUS_GUARANTEE_AUDIT_SUCCESS = "guarantee_audit_success"; // 担保审核通过

	/**
	 * 审核不通过
	 */
	public static final String DICT_LOAN_STATUS_AUDIT_FAILED = "audit_failed"; // 审核不通过

	/**
	 * 审核通过
	 */
	public static final String DICT_LOAN_STATUS_AUDIT_SUCCESS = "audit_success"; // 审核通过

	/**
	 * 满额审核不通过
	 */
	public static final String DICT_LOAN_STATUS_FULL_AUDIT_FAILED = "full_audit_failed"; // 满额审核不通过

	/**
	 * 满额审核通过
	 */
	public static final String DICT_LOAN_STATUS_FULL_AUDIT_SUCCESS = "full_audit_success"; // 满额审核通过

	/**
	 * 融资放款中
	 */
	public static final String DICT_LOAN_STATUS_LENDING = "lending"; // 融资放款中

	/**
	 * 还款中
	 */
	public static final String DICT_LOAN_STATUS_REPAYING = "repaying"; // 还款中

	/**
	 * 还款完成
	 */
	public static final String DICT_LOAN_STATUS_REPAYED = "repayed"; // 还款完成

	/**
	 * 逾期还款完成
	 */
	public static final String DICT_LOAN_STATUS_OVERDUE_REPAYED = "overdue_repayed"; // 逾期还款完成

	/**
	 * 融资作废
	 */
	public static final String DICT_LOAN_STATUS_DESTROIED = "loan_destroied"; // 融资作废

	// ===============融资状态 end===================
	// ===================融资明细状态 start
	/**
	 * 已还款
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_HAS_BEEN_PAYMENT = "has_been_payment";// 已还款

	/**
	 * 逾期还款
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_LATE_PAYMENT = "late_payment";// 逾期还款

	/**
	 * 未还款
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_NO_PAYMENT = "no_payment";// 未还款

	/**
	 * 逾期未还
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_OVERDUE = "overdue";// 逾期未还

	/**
	 * 逾期代偿
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_OVERDUE_COMPENSATORY = "overdue_compensatory";// 逾期代偿

	/**
	 * 还款中
	 */
	public static final String DICT_LOAN_DETAIL_STATUS_HAVING_PAYMENT = "having_payment";// 还款中

	// ===================融资明细状态end
	// ===========投资状态start=========================
	/**
	 * 融资状态
	 */
	public static final String DICT_INVEST_STATUS = "invest_status";// 融资状态

	/**
	 * 待付款
	 */
	public static final String DICT_INVEST_STATUS_FOR_THE_PAYMENT = "for_the_payment"; // 待付款

	/**
	 * 付款
	 */
	public static final String DICT_INVEST_STATUS_PAYMENT = "payment"; // 付款 \

	/**
	 * 待生效
	 */
	public static final String DICT_INVEST_STATUS_EFFECTIVE = "effective"; // 待生效

	/**
	 * 还款中
	 */
	public static final String DICT_INVEST_STATUS_EFFECT = "effect"; // 还款中

	/**
	 * 还款完成
	 */
	public static final String DICT_INVEST_STATUS_COMPLETE = "complete"; // 还款完成

	/**
	 * 取消
	 */
	public static final String DICT_INVEST_STATUS_CANCEL = "cancel"; // 取消

	/**
	 * 申请退款
	 */
	public static final String DICT_INVEST_STATUS_APPLY_REFUND = "apply_refund"; // 申请退款

	/**
	 * 退款
	 */
	public static final String DICT_INVEST_STATUS_REFUND = "refund"; // 退款

	/**
	 * 流标退回
	 */
	public static final String DICT_INVEST_STATUS_FLOW_BACK = "flow_back"; // 流标退回

	/**
	 * 超募退回
	 */
	public static final String DICT_INVEST_STATUS_RAISE_RETURN = "raise_return"; // 超募退回

	/**
	 * 流标退款中
	 */
	public static final String DICT_INVEST_STATUS_FLOW_BACK_ING = "flow_back_ing"; // 流标退款中

	/**
	 * 超募退款中
	 */
	public static final String DICT_INVEST_STATUS_RAISE_RETURNING = "raise_returning"; // 超募退款中

	/**
	 * 还款中 public static final String DICT_INVEST_STATUS_RETURNING = "returning"; // 还款中
	 */
	// ===========投资状态end=========================
	// ===========投资退款状态start=========================
	/**
	 * 申请
	 */
	public static final String DICT_INVEST_REFUND_STATUS_APPLY = "apply";// 申请

	/**
	 * 驳回
	 */
	public static final String DICT_INVEST_REFUND_STATUS_REJECTED = "rejected"; // 驳回

	/**
	 * 批准
	 */
	public static final String DICT_INVEST_REFUND_STATUS_APPROVAL = "approval"; // 批准

	/**
	 * 撤销
	 */
	public static final String DICT_INVEST_REFUND_STATUS_APPLY_CANCEL = "apply_cancel"; // 撤销

	// ===========投资退款状态end=========================
	// ===========融资操作类型start=========================
	/**
	 * 添加融资
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LA = "loan_add";// 添加融资

	/**
	 * 修改融资
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LE = "loan_edit";// 修改融资

	/**
	 * 投资截止时间延长
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LL = "loan_longer";// 投资截止时间延长

	/**
	 * 融资作废
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LD = "loan_destroy";// 融资作废

	/**
	 * 融资暂停投资
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LDIS = "loan_disable";// 融资暂停投资

	/**
	 * 融资开放投资
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LEN = "loan_enable";// 融资开放投资

	/**
	 * 融资管理流程批准操作
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LAS = "loan_audit_success";// 融资管理流程批准操作

	/**
	 * 融资管理流程驳回操作
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LAF = "loan_audit_failed";// 融资管理流程驳回操作

	/**
	 * 融资管理流标操作操作
	 */
	public static final String DICT_LOAN_OPERATION_TYPE_LR = "loan_return";// 融资管理流标操作操作

	/**
	 * 融资管理工作流业务代码
	 */
	public static final String LOAN_MANAGE_BUSSINESS_CODE = "loan_manage";// 融资管理工作流业务代码

	/**
	 * 融资管理工作流业务代码(待审核之前)
	 */
	public static final String LOAN_APPLYING_BUSSINESS_CODE = "loan_applying";// 融资管理工作流业务代码(待审核之前)

	/**
	 * 融资操作记录格式 {0}时间 {1}操作内容 {2}操作人 {3}操作说明
	 */
	public static final String LOAN_OPERATION_FORMAT = "{0} {1}。操作人：{2}。{3}";// 融资操作记录格式

	/**
	 * 融资操作记录格式 {0}时间 {1}操作内容 {2}操作人 {3}操作说明
	 */
	public static final String LOAN_OPERATION_FORMAT2 = "{0} {1}。操作人：{2}。";// 融资操作记录格式
																			// {0}时间
																			// {1}操作内容
																			// {2}操作人
																			// {3}操作说明

	// ===========融资操作类型start=========================
	// ===========客户相关start=========================
	/**
	 * 客户来源：用户注册
	 */
	public static final String DICT_CUST_SOURCE_REG = "registration";// 客户来源：用户注册

	/**
	 * 客户来源：后台添加
	 */
	public static final String DICT_CUST_SOURCE_BA = "backstage_add";// 客户来源：后台添加

	/**
	 * 客户状态：已激活、可用
	 */
	public static final Long USER_ACTIVE_STATUS_ENABLED = 1L;// 客户状态：已激活、可用

	/**
	 * 客户状态：未激活、不可用
	 */
	public static final Long USER_ACTIVE_STATUS_DISABLED = 0L;// 客户状态：未激活、不可用

	/**
	 * 客户状态：已删除
	 */
	public static final Long USER_ACTIVE_STATUS_DELETED = 2L;// 客户状态：已删除

	/**
	 * 客户类型：个人客户、投资客户
	 */
	public static final int USER_TYPE_INVEST = 0;// 客户类型：个人客户、投资客户

	/**
	 * 客户类型：企业客户、融资客户
	 */
	public static final int USER_TYPE_FINANCE = 1;// 客户类型：企业客户、融资客户

	/**
	 * 其他
	 */
	public static final int USER_TYPE_OTHER = 2;// 其他

	// ===========客户相关end=========================
	// ===========系统设置start=========================
	/**
	 * 系统设置-GroupCode-短信通道
	 */
	public static final String SYS_SETTING_GROUP_CODE_SYS = "sms";// 系统设置-GroupCode-短信通道

	/**
	 * 系统设置-GroupCode-费率
	 */
	public static final String SYS_SETTING_GROUP_CODE_FEE = "fee";// 系统设置-GroupCode-费率

	/**
	 * 系统设置-GroupCode-最大投资金额设置
	 */
	public static final String SYS_SETTING_GROUP_CODE_MAX_INVEST = "max_invest";// 系统设置-GroupCode-费率

	/**
	 * 最大投资金额默认值
	 */
	public static final String SYS_SETTING_GROUP_CODE_MAX_INVEST_DAFAULT = "max_invest_default";// 系统设置-最大投资金额默认值

	/**
	 * 是否启用最大投资金额限制
	 */
	public static final String SYS_SETTING_GROUP_CODE_MAX_INVEST_ENABLE = "max_invest_enable";// 系统设置-最大投资金额默认值

	/**
	 * 系统设置-GroupCode-水印组
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK = "watermark";// 系统设置-是否启用遮盖图水印功能

	/**
	 * 是否启用遮盖图水印功能
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_ENABLE = "watermark_enable";// 系统设置-是否启用遮盖图水印功能

	/**
	 * 系统设置-水印文字
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_NAME = "watermark_name";// 系统设置-水印文字

	/**
	 * 水印字体样式
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_STYLE = "watermark_style";// 系统设置-水印字体样式

	/**
	 * 水印字体大小
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_SIZE = "watermark_size";// 系统设置-水印字体大小

	/**
	 * 水印X间距
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_X_SPACING = "watermark_x_spacing";// 系统设置-水印X间距

	/**
	 * 水印Y间距
	 */
	public static final String SYS_SETTING_GROUP_CODE_WATERMARK_Y_SPACING = "watermark_y_spacing";// 系统设置-水印Y间距

	/**
	 * 平台管理费率
	 */
	public static final String SYS_SETTING_GROUP_CODE_FEE_PLATFORM = "fee_platform";// 系统设置-平台管理费率

	/**
	 * 系统设置-GroupCode-短信模板
	 */
	public static final String SYS_SETTING_GROUP_CODE_MESSAGE = "message";// 系统设置-GroupCode-短信模板

	/**
	 * 系统设置-GroupCode-天数
	 */
	public static final String SYS_SETTING_GROUP_CODE_DAYS = "days";// 系统设置-GroupCode-天数

	/**
	 * 系统设置-GroupCode-小时数
	 */
	public static final String SYS_SETTING_GROUP_CODE_HOURS = "hours";

	// ===========系统设置end=========================
	// ===========退款状态start=========================
	/**
	 * 退款中
	 */
	public static final String REFUND_SERIAL_STATUS_REFUNDING = "refunding";// 退款中

	/**
	 * 已退款
	 */
	public static final String REFUND_SERIAL_STATUS_REFUNDED = "refunded";// 已退款

	// ===========退款状态end=========================
	// ===========退款类型start=========================
	/**
	 * 流标退款
	 */
	public static final String REFUND_SERIAL_TYPE_RETURNED = "refund_returned";// 流标退款

	/**
	 * 超募退款
	 */
	public static final String REFUND_SERIAL_TYPE_FULL = "refund_full";// 超募退款

	// ===========退款类型end=========================
	/**
	 * 部门
	 */
	public static final String DICT_DEPARTMENT = "department";// 部门

	/**
	 * 工作
	 */
	public static final String DICT_JOB = "job";// 工作

	/**
	 * 性别
	 */
	public static final String GENDER = "gender";// 性别

	public static final String EDUCATIONAL = "educational";// 学历

	public static final String LOAN_FINANCING_INFO = "loan_financing_info";// 融资方信息

	public static final String STR_1 = "1";

	public static final String STR_2 = "2";

	public static final String STR_3 = "3";

	public static final String STR_4 = "4";

	public static final String STR_PROVINCE = "province";

	public static final String STR_CITY = "city";

	public static final String STR_AREA = "area";

	// 没份
	// public static final BigDecimal UNIT_PRICE=BigDecimal.valueOf(10000);
	/**
	 * 投资统计-已生效投资
	 */
	public static final String STATISTIC_INVEST_EFFECT = "EFFECT_INVEST";

	/**
	 * 投资统计-所有投资
	 */
	public static final String STATISTIC_INVEST_ALL = "ALL_INVEST";

	/**
	 * 投资统计-第一级别上限，1000
	 */
	public static final Long STATISTIC_INVEST_LEVEL1 = 1000L;

	/**
	 * 投资统计-第二级别上限，5000
	 */
	public static final Long STATISTIC_INVEST_LEVEL2 = 5000L;

	/**
	 * 投资统计-第三级别上限，10000
	 */
	public static final Long STATISTIC_INVEST_LEVEL3 = 10000L;

	/**
	 * 投资统计-第四级别上限，50000
	 */
	public static final Long STATISTIC_INVEST_LEVEL4 = 50000L;

	/**
	 * 投资统计-第一级别下限，1笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL1_DOWN = 1L;

	/**
	 * 投资统计-第一级别上限，1笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL1_UP = 1L;

	/**
	 * 投资统计-第二级别下限，2笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL2_DOWN = 2L;

	/**
	 * 投资统计-第二级别上限，4笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL2_UP = 4L;

	/**
	 * 投资统计-第三级别下限，5笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL3_DOWN = 5L;

	/**
	 * 投资统计-第三级别下限，9笔
	 */
	public static final Long STATISTIC_INVEST_COUNT_LEVEL3_UP = 9L;

	/**
	 * 用户统计-用户类型-注册用户
	 */
	public static final String STATISTIC_USER_TYPE_REGIST = "regist_user_count";

	/**
	 * 用户统计-用户类型-中金用户
	 */
	public static final String STATISTIC_USER_TYPE_CICC = "cicc_user_count";

	/**
	 * 用户统计-用户类型-投资用户
	 */
	public static final String STATISTIC_USER_TYPE_INVEST = "invest_user_count";

	/**
	 * 用户地域统计-其他国家或地区
	 */
	public static final String STATISTIC_USER_REGION_OTHER = "其他国家或地区";

	/**
	 * 个人信用认证类型
	 */
	public static final String[] USER_CREDIT_TYPE = {"credit_certification"};

	/**
	 * 个人其他认证类型
	 */
	public static final String[] USER_CERTIFICATION_TYPE = {"identity_certification","work_certification","income_certification",
			"house_certification","car_certification","marriage_certification","degree_certificate","title_certificate",
			"phone_authentication","weibo_certification","live_certification"};

	/**
	 * 企业信用认证类型
	 */
	public static final String[] ENTERPRISE_CREDIT_TYPE = {"business_license","tax_reg_certification","bank_account","legal_per_identity",
			"organization_code","corporate_credit"};

	/**
	 * 企业其他认证类型
	 */
	public static final String[] ENTERPRISE_CERTIFICATION_TYPE = {"enterprise_qualification","tax_bill","equipment_contract",
			"pledge_certification","bank_statements","office_space","financial_audit_report","legal_per_resume","sale_contract"};

	public static final String PERSONAL_LICENSE = "personal_license";

	public static final String ENTERPRISE_LICENSE = "enterprise_license";


	/**
	 * 前台投资频道查询列表常量定义
	 */
	public static String getCondition(String str){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("to_invest","investing");
		map.put("has_been_completed","repaying,repayed");
		map.put("down_thousand","0&10000");
		map.put("up_thousand_down_five_thousand","10000&50000");
		map.put("six_ten_thousand","50000&100000");
		map.put("ten_fifty_thousand","100000&500000");
		map.put("up_fifty_thousand","500000");
		map.put("more_than_180_day","180");
		map.put("less_than_180_day","0&180");
		map.put("invest_down_thousand","0&10000");
		map.put("invest_up_thousand_down_five_thousand","10000&50000");
		map.put("invest_six_ten_thousand","50000&100000");
		map.put("invest_ten_fifty_thousand","100000&500000");
		map.put("invest_up_fifty_thousand","500000");
		return (String) map.get(str);
	}


	/**
	 * 后台融资审核，融资查看企业财务数据修改模板
	 */
	public static final String EDITFINANCIAL_TEMPLATE = "/financing/editFinancial.ftl";

	/**
	 * 后台融资审核，融资查看企业财务数据修改模板
	 */
	public static final String VIEWFINANCIAL_TEMPLATE = "/financing/financial.ftl";

	/**
	 * 后台融资审核，融资查看企业财务数据修改模板
	 */
	public static final String EDITSOCIALSECURITY_TEMPLATE = "/financing/editSocialSecurity.ftl";

	/**
	 * 后台融资审核，融资查看企业财务数据修改模板
	 */
	public static final String VIEWSOCIALSECURITY_TEMPLATE = "/financing/socialSecurity.ftl";


	/**
     * 前台我的投资--待生效
     */
	public static final String STR_PENDING="pending"; 
	/**
     * 前台我的投资--回款中
     */
	public static final String STR_RECEIVABLES="receivables"; 
	/**
     * 前台我的投资--已完成
     */
	public static final String STR_COMPLETE="complete"; 
	
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
	
	/***********************************************************************
	 * **************************判断状态常量start******************************
	 * *********************************************************************
	 */
	
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
	
	/********************************************* 爱投基金常量 *******************************************************/
	/**
	 * 爱投基金-净值取值类型-非结构化-固定收益
	 */
	public static final String FUND_NETVALUETYPE_0 = "0";
	
	/**
	 * 爱投基金-净值取值类型-非结构化-非固定收益
	 */
	public static final String FUND_NETVALUETYPE_1 = "1";
	/**
	 * 爱投基金-净值取值类型-结构化
	 */
	public static final String FUND_NETVALUETYPE_2 = "2";
	
	/********************************************** 爱投基金模版路径 *********************************************************/
	/**
	 * 爱投基金-积分商城-收货地址模版路径
	 */
	public static final String FUND_ADDRESS_MODEL = "/mall/addressModel.ftl";
	
	/**
	 * 爱投基金-购买产品弹出层
	 */
	public static final String FUND_PRODUCTBUY_POP = "/investProduct/productBuyPop.ftl";
	
}
