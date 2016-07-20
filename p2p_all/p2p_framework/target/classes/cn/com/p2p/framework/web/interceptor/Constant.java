package cn.com.p2p.framework.web.interceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * 框架常量定义类
 * 
 * @author kezhida
 *
 */
public class Constant {

	/** 共通验证，错误消息KEY */
	public static String COMM_ERROR_MESSAGE_KEY = "common_error_msg_key";
	public static String COMM_ERROR_MESSAGE_LOGIN_KEY = "common_error_msg_login_key";

	public static String SEARCH_PARAM_SET_REQUEST_PARAM = "SEARCH_PARAM_SET_REQUEST_PARAM";

	public static String getStatusName(String str) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("1", "已收款");
		map.put("2", "收款失败");

		map.put("10", "融资人融资款");
		map.put("20", "担保公司担保费");
		map.put("30", "P2P平台服务费");
		map.put("40", "投资收益");
		map.put("50", "投资撤回退款");
		map.put("60", "投资超募退款");
		return (String) map.get(str);
	}

	public static String getFicationName(String str) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("identity_certification", "身份证号");
		map.put("work_certification", "单位名称");

		map.put("income_certification", "月收入");
		map.put("house_certification", "房产认证");
		map.put("car_certification", "购车认证");
		map.put("marriage_certification", "结婚认证");
		map.put("degree_certificate", "学历认证");
		map.put("title_certificate", "职称认证");
		map.put("phone_authentication", "手机认证");
		map.put("weibo_certification", "微博认证");
		map.put("live_certification", "居住认证");
		map.put("credit_certification", "信用报告");
		map.put("enterprise_qualification", "企业资质证书");
		map.put("tax_bill", "近三个月的税票");
		map.put("equipment_contract", "主要生产设备的购买合同");
		map.put("pledge_certification", "抵、质押物的所有权的证明材料");
		map.put("bank_statements", "近一年流水信息（银行对账单）");
		map.put("office_space", "办公场地购买或租赁合同及土地证、房产证");
		map.put("financial_audit_report", "本年度及之前2年的财务审计报告");
		map.put("legal_per_resume", "法定代表人简历");
		map.put("sale_contract", "进、销货单或者销售合同");
		map.put("business_license", "营业执照");
		map.put("tax_reg_certification", "税务登记证");
		map.put("bank_account", "银行开户许可证");
		map.put("legal_per_identity", "法人代表身份证复印件");
		map.put("organization_code", "组织机构代码");
		map.put("corporate_credit", "企业征信报告及企业法人征信");
		return (String) map.get(str);
	}
}
