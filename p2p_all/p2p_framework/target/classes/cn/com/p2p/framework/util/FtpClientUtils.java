package cn.com.p2p.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtils {

	private static final Log logger = LogFactory.getLog(FtpClientUtils.class);

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param settings
	 *            系统属性文件setting.properties
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(Properties settings, String filename,
			InputStream input) {

		logger.debug("[FtpClientUtils][uploadFile][filename]" + filename);
		String ftpUse = settings.getProperty("ftpUse");
		String ftpServer = settings.getProperty("ftpServer");
		int ftpPort = Integer.valueOf(settings.getProperty("ftpPort", "21"));
		String ftpUserName = settings.getProperty("ftpUserName");
		String ftpUserPwd = settings.getProperty("ftpUserPwd");
		String ftpPath = settings.getProperty("ftpPath", "");

		if ("1".equals(ftpUse)) {

			return uploadFile(ftpServer, ftpPort, ftpUserName, ftpUserPwd,
					ftpPath, filename, input);

		} else {
			logger.warn("[uploadFile][未启动ftp上传服务][ftpUse]" + ftpUse);
		}
		return true;
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param settings
	 *            系统属性文件setting.properties
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFiles(Properties settings,
			List<String> fileNameAry, List<InputStream> inputAry) {
		logger.debug("[FtpClientUtils][uploadFiles][filename]");
		String ftpUse = settings.getProperty("ftpUse");
		String ftpServer = settings.getProperty("ftpServer");
		int ftpPort = Integer.valueOf(settings.getProperty("ftpPort", "21"));
		String ftpUserName = settings.getProperty("ftpUserName");
		String ftpUserPwd = settings.getProperty("ftpUserPwd");
		String ftpPath = settings.getProperty("ftpPath", "");

		if ("1".equals(ftpUse)) {

			return uploadFiles(ftpServer, ftpPort, ftpUserName, ftpUserPwd,
					ftpPath, fileNameAry, inputAry);

		} else {
			logger.warn("[uploadFile][未启动ftp上传服务][ftpUse]" + ftpUse);
		}
		return true;

	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @Version1.0
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	private static boolean uploadFile(String url, int port, String username,
			String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			// ftp.enterLocalPassiveMode();
			success = ftp.login(username, password);// 登录
			if (!success) {

				logger.warn("[uploadFile][login]" + ftp.getReplyString());
				return success;
			}

			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				logger.warn("[uploadFile][reply]" + reply + "[ReplyString]"
						+ ftp.getReplyString());
				return success;
			}
			success = ftp.setFileType(ftp.BINARY_FILE_TYPE);
			if (!success) {

				logger.warn("[uploadFile][setFileType][ReplyString]"
						+ ftp.getReplyString());
				return success;
			}

			String asbPath = "";
			if ("".equals(path)) {
				asbPath = filename;
			} else {
				if (path.endsWith("/")) {

					if (filename.startsWith("/")) {
						asbPath = path + filename.substring(1);
					} else {
						asbPath = path + filename;
					}
				} else {

					if (filename.startsWith("/")) {
						asbPath = path + filename;
					} else {
						asbPath = path + "/" + filename;
					}
				}

			}
			success = createDirecroty(asbPath, ftp);// ftp.changeWorkingDirectory(path);

			if (!success) {

				logger.warn("[uploadFile][changeWorkingDirectory][ReplyString]"
						+ ftp.getReplyString());
				return success;
			}

			String[] fileNameAry = filename.split("/");
			if (fileNameAry.length > 1) {
				success = ftp.storeFile(fileNameAry[fileNameAry.length - 1],
						input);
			} else {
				success = ftp.storeFile(filename, input);
			}

			if (!success) {

				logger.warn("[uploadFile][storeFile][ReplyString]"
						+ ftp.getReplyString());
				return success;
			}

			success = true;
		} catch (IOException e) {
			logger.error("[uploadFile]", e);
		} finally {
			try {
				input.close();
			} catch (Exception e) {

			}
			try {
				ftp.logout();
			} catch (Exception ex) {
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 *
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	private static boolean uploadFiles(String url, int port, String username,
			String password, String path, List<String> fileNameAry,
			List<InputStream> inputAry) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		InputStream input = null;
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			// ftp.enterLocalPassiveMode();
			success = ftp.login(username, password);// 登录
			if (!success) {

				logger.warn("[uploadFiles][login]" + ftp.getReplyString());
				return success;
			}

			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				logger.warn("[uploadFiles][reply]" + reply + "[ReplyString]"
						+ ftp.getReplyString());
				return success;
			}
			success = ftp.setFileType(ftp.BINARY_FILE_TYPE);
			if (!success) {

				logger.warn("[uploadFiles][setFileType][ReplyString]"
						+ ftp.getReplyString());
				return success;
			}

			if (!"".equals(path)) {
				if (path.startsWith("/")) {
					path = path.substring(1);
				}
				success = createDirecroty(path, ftp);// ftp.changeWorkingDirectory(path);

				if (!success) {

					logger.warn("[uploadFiles][changeWorkingDirectory][ReplyString]"
							+ ftp.getReplyString());
					return success;
				}
			}

			for (int i = 0; i < fileNameAry.size(); i++) {

				String asbPath = "";
				if ("".equals(path)) {
					asbPath = fileNameAry.get(i);
				} else {
					if (path.endsWith("/")) {

						if (fileNameAry.get(i).startsWith("/")) {
							asbPath = path + fileNameAry.get(i).substring(1);
						} else {
							asbPath = path + fileNameAry.get(i);
						}
					} else {

						if (fileNameAry.get(i).startsWith("/")) {
							asbPath = path + fileNameAry.get(i);
						} else {
							asbPath = path + "/" + fileNameAry.get(i);
						}
					}

				}
				success = createDirecroty(asbPath, ftp);// ftp.changeWorkingDirectory(path);

				if (!success) {

					logger.warn("[uploadFiles][changeWorkingDirectory][ReplyString]"
							+ ftp.getReplyString());
					return success;
				}

				input = inputAry.get(i);
				String[] fileNameToAry = fileNameAry.get(i).split("/");
				if (fileNameToAry.length > 1) {
					success = ftp.storeFile(
							fileNameToAry[fileNameToAry.length - 1], input);
				} else {
					success = ftp.storeFile(fileNameAry.get(i), input);
				}
				input.close();
				input = null;
				if (!success) {

					logger.warn("[uploadFiles][storeFile][ReplyString]"
							+ ftp.getReplyString());
					return success;
				}
				// /
			}

			success = true;
		} catch (IOException e) {
			logger.error("[uploadFiles]", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {

			}
			try {
				ftp.logout();
			} catch (Exception ex) {
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * 递归创建远程服务器目录
	 * 
	 * @param remote
	 *            远程服务器文件绝对路径
	 * @param ftpClient
	 *            FTPClient对象
	 * @return 目录创建是否成功
	 * @throws IOException
	 */
	private static boolean createDirecroty(String remote, FTPClient ftpClient)
			throws IOException {
		boolean status = true;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

		if ("".equals(directory)) {
			return status;
		}
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(directory)) {
			// 如果远程目录不存在，则递归创建远程服务器目录
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = remote.substring(start, end);
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						logger.debug("[FtpClientUtils][createDirecroty][remote]"
								+ remote + "[创建目录失败]");
						return false;
					}
				}
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return status;
	}

	// public static void main(String[] args) {

	// try {
	// uploadFile("192.168.0.253", 21, "ftpuser", "123456", "/ttt/ddd",
	// "CICC_ACCOUNT.jpg",
	//
	// new FileInputStream(new File("D:\\我的文档\\bootstrap.jpg")));
	//
	// uploadFile("192.168.0.253", 21, "ftpuser", "123456", "/ttt/ddd/",
	// "CICC_ACCOUNT.jpg",
	//
	// new FileInputStream(new File("D:\\我的文档\\bootstrap.jpg")));
	//
	// uploadFile("192.168.0.253", 21, "ftpuser", "123456", "/ttt/ddd/",
	// "/CICC_ACCOUNT.jpg",
	//
	// new FileInputStream(new File("D:\\我的文档\\bootstrap.jpg")));
	//
	// uploadFile("192.168.0.253", 21, "ftpuser", "123456", "/ttt/ddd",
	// "CICC_ACCOUNT.jpg",
	//
	// new FileInputStream(new File("D:\\我的文档\\bootstrap.jpg")));
	//
	// List<String> filename = new ArrayList<String>();
	// filename.add("/CICC_ACCOUNTfff.jpg");
	// filename.add("/ssss/sscc/CICC_ACCOUNT.jpg");
	// List<InputStream> filenames = new ArrayList<InputStream>();
	// filenames.add(new FileInputStream(new File(
	// "D:\\我的文档\\bootstrap.jpg")));
	// filenames.add(new FileInputStream(new File(
	// "D:\\我的文档\\bootstrap.jpg")));
	//
	// uploadFiles("192.168.0.253", 21, "ftpuser", "123456", "", filename,
	//
	// filenames);
	// } catch (FileNotFoundException e) {
	//
	// e.printStackTrace();
	// }
	// }
}
