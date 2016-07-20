package cn.com.p2p.framework.report.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import cn.com.p2p.framework.report.ExcleTablWorkBook;
import cn.com.p2p.framework.report.bean.impl.EntityFileWriter;
import cn.com.p2p.framework.report.bean.impl.FtlFileWriter;
import cn.com.p2p.framework.report.bean.impl.PdfFileWriter;
import cn.com.p2p.framework.report.bean.impl.SqlFileWriter;


/***
 * 文件输出类根据模板文件输出文件格式。
 * @author kezhida
 *
 */
public class MyFileWriter {

	/**
	 * 根据文件模板和数据生成指定格式的文件
	 * @param ftype 文件类型
	 * @param path	生成文件路径
	 * @param fileFormat	文件格式模板接口（暂定）
	 * @param dataSource	数据源
	 */
	public static void printFile(FileType ftype,String path,ExcleTablWorkBook workBook,Map<String,Object> dataSource){
		try {
			File file = new File(path);
			if(!file.exists()){
				if(!file.createNewFile()){//创建文件失败
					return ;
				}
			}
			FileOutputStream fo = new FileOutputStream(file);
			printFile(ftype,fo,workBook,dataSource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据文件模板和数据生成指定格式的文件
	 * @param ftype 文件类型
	 * @param out	文件输出流
	 * @param fileFormat	文件格式模板接口（暂定）
	 * @param dataSource	数据源
	 */
	public static void printFile(FileType ftype,OutputStream out,ExcleTablWorkBook workBook,Map<String,Object> dataSource){
		Writer writer = null;
		if(ftype==FileType.PDF){
			writer = new PdfFileWriter();
		}else if(ftype==FileType.FTL){
			writer = new FtlFileWriter();
		}else if(ftype==FileType.SQL){
			writer = new SqlFileWriter();
		}else if(ftype==FileType.ENTITY){
			writer = new EntityFileWriter();
		}
//		//=======================================================================
//		Map<String,Object> map = new ConcurrentHashMap<String, Object>();
//		map.put("bgbh", "2013-TYT00006");
//		map.put("zcdm", "30002102012014030013");
//		//=======================================================================
		writer.writer(out, workBook, dataSource);
	}
}
