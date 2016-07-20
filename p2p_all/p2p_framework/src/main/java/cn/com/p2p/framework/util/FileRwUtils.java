package cn.com.p2p.framework.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 文件读写工具
 * @author pub
 *
 */
public class FileRwUtils implements Serializable {
	
	private static final long serialVersionUID = -8528517553888197354L;

	/**
	 * 创建文本文件,若文件目录不存在，则自动创建；若已存在该文件，则替换。使用系统默认编码
	 * 
	 * @param path
	 *            文件的全路径，例如：c:/tomcat/ecram/UserFiles/aa.txt
	 * @param content
	 *            文件内容。
	 * @throws IOException
	 */
	public static void createFile(String path, String content) throws IOException {
		File file = new File(path);
		FileUtils.writeStringToFile(file, content);
	}

	/**
	 * 创建文本文件,若文件目录不存在，则自动创建；若已存在该文件，则替换。使用指定的编码。
	 * 
	 * @param path
	 *            文件的全路径，例如：c:/tomcat/ecram/UserFiles/aa.txt
	 * @param content
	 *            文件内容。
	 * @param encoding
	 *            文件编码
	 * @throws IOException
	 */
	public static void createFile(String path, String content, String encoding) throws IOException {
		File file = new File(path);
		FileUtils.writeStringToFile(file, content, encoding);
	}

	/**
	 * 读取文本文件,使用系统默认编码。
	 * 
	 * @param path
	 *            文件的全路径，例如：c:/tomcat/ecram/UserFiles/aa.txt
	 * @return 文本文件内容
	 * @throws IOException
	 */
	public static String readeFile(String path) throws IOException {
		File file = new File(path);
		return FileUtils.readFileToString(file);
	}

	/**
	 * 读取文本文件,使用指定的编码。
	 * 
	 * @param path
	 *            文件的全路径，例如：c:/tomcat/ecram/UserFiles/aa.txt
	 * @param encoding
	 *            文件编码
	 * @return 文本文件内容
	 * @throws IOException
	 */
	public static String readeFile(String path, String encoding) throws IOException {
		File file = new File(path);
		return FileUtils.readFileToString(file, encoding);
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param path
	 *            文件或文件夹路径
	 * @throws IOException
	 */
	public static void deleteDir(String path) throws IOException {
		File file = new File(path);
		// FileUtils.deleteQuietly(file);
		FileUtils.forceDelete(file);
		// FileUtils.deleteDirectory(file);
	}
	
	
	public static void whenNotExistsThenMkdirs(String pathName) {
		File f = new File(pathName);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static void whenNotExistsThenMkdirs(String attachPath, String dir) {
		File f = new File(attachPath);
		if (!f.exists()) {
			if (f.mkdirs()) {
				f = new File(attachPath + dir);
				if (!f.exists()) {
					f.mkdir();
				}
			}
		}
	}

	public static void copyFile(String srcPath, String distPath) {
		try {
			FileUtils.copyFile(new File(srcPath), new File(distPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getRealPath(String pathName) throws Exception {
		String classPath = FileRwUtils.class.getResource("/").toURI().getPath();
		if (pathName.startsWith("/")) {
			pathName = pathName.substring(1);
		}
		String realPath = classPath.substring(0, classPath.indexOf("/WEB-INF/")) + "/" + pathName;
		return realPath;
	}
	
	public static String getRandomFileName(String uploadName) {
		String fileName = DateUtils.formatCurrentDateTime("yyyyMMddHHssms") + uploadName.substring(uploadName.lastIndexOf("."), uploadName.length());
		return fileName;
	}
	
	public static String getSuffixName(String uploadName) {
		String suffixName = uploadName.substring(uploadName.lastIndexOf("."), uploadName.length());
		return suffixName;
	}
	
	public static InputStream openInputStream(byte[] bytes) {
		return new ByteArrayInputStream(bytes);
	}
	
	public static byte[] getFileBlob(String fileDir, String realFileName) throws Exception {
		if (StringUtils.isNotEmpty(realFileName)) {
			String uploadPath = Struts2Utils.getSession().getServletContext().getRealPath(fileDir);
			File f = new File(uploadPath + "/" + realFileName);
			if (f.exists()) {
				return FileUtils.readFileToByteArray(f);
			}
		}
		return null;
	}
	
	public static void delTempFile(String fileDir, String realFileName) {
		if (StringUtils.isNotEmpty(realFileName)) {
			String uploadPath = Struts2Utils.getSession().getServletContext().getRealPath(fileDir);
			File f = new File(uploadPath + "/" + realFileName);
			if (f.exists()) {
				f.delete();
			}
		}
	}
	
}
