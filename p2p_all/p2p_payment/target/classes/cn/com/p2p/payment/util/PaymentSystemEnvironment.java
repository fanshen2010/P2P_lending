package cn.com.p2p.payment.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentSystemEnvironment {
	public static String institutionId;

	public static String pageCallbackURL;

	public static String projectURL;

	public static String platformSetting;
	public static final String SYS_CONFIG_FILE = "payemtSystem.ini";

	public static String webAgreementNo = "";
	public static String webPaymentAccountName = "";
	public static String webPaymentAccountNumber = "";
	private static final Logger logger = LoggerFactory
			.getLogger(PaymentSystemEnvironment.class);

	public static void initialize(String paymentConfigPath) {
		try {
			// 初始化
			String sysConfigFile = paymentConfigPath + File.separatorChar
					+ SYS_CONFIG_FILE;
			System.out.println(sysConfigFile);

			Properties sysProperties = new Properties();
			sysProperties.load(new FileInputStream(sysConfigFile));

			// 数据库链接配置
			institutionId = sysProperties.getProperty("institutionId");
			pageCallbackURL = sysProperties.getProperty("pageCallbackURL");
			projectURL = sysProperties.getProperty("ProjectURL");

			webAgreementNo = sysProperties.getProperty("webAgreementNo");
			webPaymentAccountName = new String(sysProperties.getProperty(
					"webPaymentAccountName").getBytes("ISO8859-1"), "UTF-8");
			webPaymentAccountNumber = sysProperties
					.getProperty("webPaymentAccountNumber");
			platformSetting= sysProperties
					.getProperty("platformSetting");
			logger.info("[PaymentSystemEnvironment][institutionId]"
					+ institutionId + "[webPaymentAccountName]"
					+ webPaymentAccountName + "[webPaymentAccountNumber]"
					+ webPaymentAccountNumber+"[platformSetting]"+platformSetting);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
