package cn.com.p2p.framework.report.tool;

import java.io.File;
import java.util.List;

/**
 * @author Administrator
 */
public class FileUtils {
	public static String getFileSeparator() {
		return File.separator;
	}
	/**
	 * 取得不带后缀的文件名称
	 * @param File 文件
	 * @return String
	 */
	public static String getFileNameWithNotSuffix(File f) {
		if (f == null) {
			return null;
		}
		String name = null;
		name = f.getName().replace(".xls", "");
		return name;
	}
	
	/**
	 * 判断目标目录下是否有子文件存在
	 * @param file
	 * @return
	 */
	public static boolean isChildFileExists(File file) {
		if (file != null && file.isDirectory()) {
			String[] children = file.list();
			if (children != null && children.length > 0) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	public static boolean isChildFileExists(String filePath) {
		return isChildFileExists(new File(filePath));
	}
	/**
	 * 删除目标文件下所有的文件以及子目录
	 * @param dir
	 * @return
	 */
	public static boolean delFileList(File dir) {
		boolean success = Boolean.TRUE;
		if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                success = delFileList(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
	}
	public static boolean delFileList(String dir) {
		return delFileList(new File(dir));
	}
	
	/**
	 * 取得文件目录中的所有文件（非目录）
	 * 
	 * @param targetFile
	 *            要操作的文件
	 * @param rst
	 *            实例化后的List<File>
	 * @return List<File> 所有的文件集合
	 */
	public static List<File> getFiles(File targetFile, List<File> rst) {
		// 参数rst如果为null时，不执行方法
		if (rst == null) {
			return null;
		}
		// 判断目标是否是文件夹
		if (targetFile.isDirectory()) {
			// 取得目录中所有文件
			File[] files = targetFile.listFiles();
			for (File file : files) {
				// 当文件存在，且不是目录时
				if (file.exists() && file.isFile() && !file.isHidden()) {
					rst.add(file);
				} else if (file.exists() && file.isDirectory()) {
					// 当前文件如果是目录时，递归
					getFiles(file, rst);
				}
			}
		} else {
			rst.add(targetFile);
		}
		return rst;
	}
}
