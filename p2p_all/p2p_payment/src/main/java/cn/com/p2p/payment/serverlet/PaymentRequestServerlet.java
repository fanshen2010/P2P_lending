package cn.com.p2p.payment.serverlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import payment.api.notice.Notice3118Request;
import payment.api.notice.Notice3119Request;
import payment.api.notice.Notice3218Request;
import payment.api.notice.Notice4203Request;
import payment.api.notice.Notice4218Request;
import payment.api.notice.Notice4228Request;
import payment.api.notice.Notice4233Request;
import payment.api.notice.Notice4243Request;
import payment.api.notice.Notice4247Request;
import payment.api.notice.Notice4253Request;
import payment.api.notice.Notice4257Request;
import payment.api.notice.Notice4263Request;
import payment.api.notice.Notice4322Request;
import payment.api.notice.NoticeRequest;
import payment.api.notice.NoticeResponse;
import payment.tools.util.Base64;
import cn.com.p2p.domain.payment.entity.CiccAccount;

public class PaymentRequestServerlet extends HttpServlet implements
		ApplicationContextAware {
//
//	private PaymentNoticeService paymentNoticeService = null;
//	private ApplicationContext ac;
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -8121671779482063511L;
//
//	private static final Logger logger = LoggerFactory
//			.getLogger(PaymentRequestServerlet.class);
//
//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {}
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		try {
//			logger.info("---------- Begin [ReceiveNoticePage] process......");
//			ac=WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//			if (paymentNoticeService == null) {
//				paymentNoticeService = (PaymentNoticeService) ac
//						.getBean("paymentNoticeService");
//			}
//
//			// 获得参数message和signature
//			String message = request.getParameter("message");
//			String signature = request.getParameter("signature");
//
//			logger.info("[message]=[" + message + "]");
//			logger.info("[signature]=[" + signature + "]");
//
//			// 定义变量
//			// String txName = "";
//
//			// 生成交易结果对象
//			NoticeRequest noticeRequest = new NoticeRequest(message, signature);
//
//			if ("4233".equals(noticeRequest.getTxCode())) {
//				Notice4233Request nr = new Notice4233Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4233]");
//				logger.info("[TxName]       = [用户支付账户注册成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//				logger.info("[UserName]=[" + nr.getUserName() + "]");
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//
//				CiccAccount ciccAccount = new CiccAccount();
//				ciccAccount.setInstitutionId(nr.getInstitutionID());
//				ciccAccount.setIdentificationNumber(nr
//						.getIdentificationNumber());
//				ciccAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccAccount.setPhoneNumber(nr.getPhoneNumber());
//				ciccAccount.setUserName(nr.getUserName());
//
//				paymentNoticeService.ciccDoRegistSuccess(ciccAccount);
//
//			} else if ("4203".equals(noticeRequest.getTxCode())) {
//				Notice4203Request nr = new Notice4203Request(
//						noticeRequest.getDocument());
//				logger.info("[TxCode]       = [4203]");
//				logger.info("[TxName]       = [统一账户注册成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//				logger.info("[UserName]=[" + nr.getUserName() + "]");
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//
//				CiccAccount ciccAccount = new CiccAccount();
//				ciccAccount.setRegisterNo(nr.getRegisterNo());
//				ciccAccount.setInstitutionId(nr.getInstitutionID());
//				ciccAccount.setIdentificationNumber(nr
//						.getIdentificationNumber());
//				ciccAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccAccount.setPhoneNumber(nr.getPhoneNumber());
//				ciccAccount.setUserName(nr.getUserName());
//				ciccAccount.setUserType(nr.getUserType());
//
//				paymentNoticeService.ciccDoRegistSuccess(ciccAccount);
//
//			} else if ("4218".equals(noticeRequest.getTxCode())) {
//				Notice4218Request nr = new Notice4218Request(
//						noticeRequest.getDocument());
//				logger.info("[TxCode]       = [4218]");
//				logger.info("[TxName]       = [支付账户余额查询签约成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[AgreementNo]=[" + nr.getAgreementNo() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//
//				CiccAccount ciccAccount = new CiccAccount();
//				ciccAccount.setBalanceAgreementNo(nr.getAgreementNo());
//				ciccAccount.setInstitutionId(nr.getInstitutionID());
//				ciccAccount.setIdentificationNumber(nr
//						.getIdentificationNumber());
//				ciccAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccAccount.setPhoneNumber(nr.getPhoneNumber());
//				ciccAccount.setUserName(nr.getPaymentAccountName());
//
//				paymentNoticeService
//						.ciccAccountBalanceSearchSigningSuccess(ciccAccount);
//
//			} else if ("4322".equals(noticeRequest.getTxCode())) {
//				// 4322- 统一账户自动投资签约成功通知
//				Notice4322Request nr = new Notice4322Request(
//						noticeRequest.getDocument());
//				logger.info("[TxCode]       = [4322]");
//				logger.info("[TxName]       = [统一账户自动投资签约成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[AgreementNo]=[" + nr.getAgreementNo() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//
//				CiccAccount ciccAccount = new CiccAccount();
//				ciccAccount.setAutoInvestAgreementNo(nr.getAgreementNo());
//				ciccAccount.setInstitutionId(nr.getInstitutionID());
//				ciccAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccAccount.setUserName(nr.getPaymentAccountName());
//
//				paymentNoticeService.ciccAutoInvestSigningSuccess(ciccAccount);
//
//			} else if ("4243".equals(noticeRequest.getTxCode())) {
//				Notice4243Request nr = new Notice4243Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4243]");
//				logger.info("[TxName]       = [银行卡绑定成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[BindingSystemNo]=[" + nr.getBindingSystemNo()
//						+ "]");
//				logger.info("[BankAccountNumber]=[" + nr.getBankAccountNumber()
//						+ "]");
//				logger.info("[BankID]=[" + nr.getBankID() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				// TODO
//				// paymentNoticeService.ciccDoAccountBankBindSuccess(nr);
//
//			} else if ("4247".equals(noticeRequest.getTxCode())) {
//				Notice4247Request nr = new Notice4247Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4247]");
//				logger.info("[TxName]       = [银行卡解绑成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[BankAccountNumber]=[" + nr.getBankAccountNumber()
//						+ "]");
//				logger.info("[BankID]=[" + nr.getBankID() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[BindingSystemNo]=[" + nr.getBindingSystemNo()
//						+ "]");
//				// TODO
//				// paymentNoticeService.ciccDoAccountBankRemoveSuccess(nr);
//			} else if ("4253".equals(noticeRequest.getTxCode())) {
//				Notice4253Request nr = new Notice4253Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4253]");
//				logger.info("[TxName]       = [充值成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PaymentNo]=[" + nr.getPaymentNo() + "]");
//				logger.info("[PaymentTime]=[" + nr.getPaymentTime() + "]");
//				logger.info("[Amount]=[" + nr.getAmount() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				// TODO
////				 paymentNoticeService.ciccDoRechargeSuccess(nr);
//			} else if ("4257".equals(noticeRequest.getTxCode())) {
//				Notice4257Request nr = new Notice4257Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4257]");
//				logger.info("[TxName]       = [用户支付账户提现成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[TxSN]=[" + nr.getTxSN() + "]");
//				logger.info("[PaymentTime]=[" + nr.getAcceptTime() + "]");
//				logger.info("[Amount]=[" + nr.getAmount() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				// TODO
//				// paymentNoticeService.ciccDoAccountCashSuccess(nr);
//			} else if ("3218".equals(noticeRequest.getTxCode())) {
//
//				Notice3218Request nr = new Notice3218Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [3218]");
//				logger.info("[TxName]       = [P2P支付成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PaymentNo]=[" + nr.getPaymentNo() + "]");
//				logger.info("[PaymentTime]=[" + nr.getPaymentTime() + "]");
//				logger.info("[Amount]=[" + nr.getAmount() + "]");
//				logger.info("[LoanerPaymentAccountNumber]=["
//						+ nr.getLoanerPaymentAccountNumber() + "]");
//
//				paymentNoticeService.ciccDoAccountPaySuccess(nr,
//						noticeRequest.getPlainText(), ac);
//			} else if ("4263".equals(noticeRequest.getTxCode())) {
//				Notice4263Request nr = new Notice4263Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4263]");
//				logger.info("[TxName]       = [用户支付账户扣款签约成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[AgreementNo]=[" + nr.getAgreementNo() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//
//				CiccDebtAccount ciccDebtAccount = new CiccDebtAccount();
//				ciccDebtAccount.setInstitutionId(nr.getInstitutionID());
//				ciccDebtAccount.setAgreementNo(nr.getAgreementNo());
//				ciccDebtAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccDebtAccount.setUserName(nr.getPaymentAccountName());
//				ciccDebtAccount.setPhoneNumber(nr.getPhoneNumber());
//				paymentNoticeService
//						.ciccDoAccountDebitSigningSuccess(ciccDebtAccount);
//			} else if ("4228".equals(noticeRequest.getTxCode())) {
//
//				Notice4228Request nr = new Notice4228Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [4228]");
//				logger.info("[TxName]       = [支付账户扣款签约成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[AgreementNo]=[" + nr.getAgreementNo() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//				CiccDebtAccount ciccDebtAccount = new CiccDebtAccount();
//				ciccDebtAccount.setInstitutionId(nr.getInstitutionID());
//				ciccDebtAccount.setAgreementNo(nr.getAgreementNo());
//				ciccDebtAccount.setPaymentAccountNumber(nr
//						.getPaymentAccountNumber());
//				ciccDebtAccount.setUserName(nr.getPaymentAccountName());
//				ciccDebtAccount.setPhoneNumber(nr.getPhoneNumber());
//
//				ciccDebtAccount.setIdentificationNumber(nr
//						.getIdentificationNumber());
//				paymentNoticeService
//						.ciccDoAccountDebitSigningSuccess(ciccDebtAccount);
//
//			} else if ("3118".equals(noticeRequest.getTxCode())) {
//				Notice3118Request nr = new Notice3118Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [3118]");
//				logger.info("[TxName]       = [P2P支付成功通知]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PaymentNo]=[" + nr.getPaymentNo() + "]");
//				logger.info("[PaymentTime]=[" + nr.getPaymentTime() + "]");
//				logger.info("[Amount]=[" + nr.getAmount() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//
//				paymentNoticeService.ciccDoAccountPaySuccess(nr,
//						noticeRequest.getPlainText(), ac);
//			} else if ("3119".equals(noticeRequest.getTxCode())) {
//				Notice3119Request nr = new Notice3119Request(
//						noticeRequest.getDocument());
//				// ！！！ 在这里添加商户处理逻辑！！！
//				// 以下为演示代码
//				logger.info("[TxCode]       = [3119]");
//				logger.info("[TxName]       = [P2P支付成功通知（使用优惠券）]");
//				logger.info("[InstitutionID]=[" + nr.getInstitutionID() + "]");
//				logger.info("[PaymentNo]=[" + nr.getPaymentNo() + "]");
//				logger.info("[PaymentTime]=[" + nr.getPaymentTime() + "]");
//				logger.info("[Amount]=[" + nr.getAmount() + "]");
//				logger.info("[PaymentAccountName]=["
//						+ nr.getPaymentAccountName() + "]");
//				logger.info("[PaymentAccountNumber]=["
//						+ nr.getPaymentAccountNumber() + "]");
//				logger.info("[PhoneNumber]=[" + nr.getPhoneNumber() + "]");
//				logger.info("[CouponNo]=[" + nr.getCouponNo() + "]");
//				logger.info("[CouponAmount]=[" + nr.getCouponAmount() + "]");
//				logger.info("[IdentificationNumber]=["
//						+ nr.getIdentificationNumber() + "]");
//
//				paymentNoticeService.ciccDoAccountPaySuccess(nr,
//						noticeRequest.getPlainText(), ac);
//			} else {
//				// txName = "未知通知类型";
//				logger.info("！！！ 错误的通知 ！！！");
//				logger.info("[txCode]       = [????]");
//				logger.info("[txName]       = [未知通知类型]");
//			}
//
//			logger.info("[plainText]=[" + noticeRequest.getPlainText() + "]");
//
//			logger.info("---------- End [ReceiveNoticePage] process.");
//			PrintWriter out = response.getWriter();
//			String xmlString = "";
//
//			xmlString = new NoticeResponse().getMessage();
//
//			String base64String = new String(Base64.encode(xmlString
//					.getBytes("UTF-8")));
//
//			out.print(base64String);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			logger.error("", e);
//
//		}
//	}
//
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
