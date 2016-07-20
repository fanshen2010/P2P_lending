package cn.com.p2p.payment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payment.api.system.CMBEnvironment;
import payment.api.system.PaymentUserEnvironment;
import payment.api.system.TxMessenger;

/**
 * 支付接口基类
 * @author 
 *
 */
public class PaymentBase {
	private static final Logger logger = LoggerFactory
			.getLogger(PaymentBase.class);
	/**
	 * 发送消息
	 * 
	 * @param message
	 * @param signature
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	protected String[] sendMessage(String message, String signature, String flag)
			throws Exception {

		// 与支付平台进行通讯
		TxMessenger txMessenger = new TxMessenger();
		String[] respMsg = null;
		// Flag=10:cmb, 20:paymentAccount
		if ("10".equals(flag)) {
			respMsg = txMessenger.send(message, signature,
					CMBEnvironment.cmbtxURL);// 0:message;
		} else if ("20".equals(flag)) {
			respMsg = txMessenger.send(message, signature,
					PaymentUserEnvironment.paymentusertxURL);
		} else {
			respMsg = txMessenger.send(message, signature);// 0:message;
		}
		// 1:signature
		String plainText = new String(
				payment.tools.util.Base64.decode(respMsg[0]), "UTF-8");

		logger.debug("[message]=[" + respMsg[0] + "]");
		logger.debug("[signature]=[" + respMsg[1] + "]");
		logger.debug("[plainText]=[" + plainText + "]");
		return respMsg;
	}

}
