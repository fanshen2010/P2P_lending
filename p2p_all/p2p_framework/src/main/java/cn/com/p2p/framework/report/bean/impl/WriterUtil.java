package cn.com.p2p.framework.report.bean.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.p2p.framework.report.ExcelTable;
import cn.com.p2p.framework.report.tool.FileUtils;
import cn.com.p2p.framework.report.tool.FreeMarkerUtils;
import cn.com.p2p.framework.report.tool.ParserRule;
import cn.com.p2p.framework.report.tool.PinyinUtils;
import cn.com.p2p.framework.util.PropertyManager;


public class WriterUtil {
	//java实体的文件路径
	//public static String OUT_ENTITY_PATH = "D:\\tmp\\class\\";//"D:\\project_work\\jcgl_resourse\\src\\main\\java\\cn\\gov\\syzj\\entity\\";
	public static String OUT_ENTITY_PATH = PropertyManager.getProperty("report.comm.report.tmp.path");
	// 生成文件输出目录
	//public static final String OUT_RESOURSE_CATALAG = "D:\\project_work\\jcgl\\WebRoot";
	public static String OUT_RESOURSE_CATALAG = PropertyManager.getProperty("report.comm.report.path");
	// 要操作的模版文件路径
	public static final String TEMPLATE_RESOURSE_CATALAG = "WEB-INF\\view\\jy\\report\\template\\";

	// ftl模版文件base_templateFTL
	public static final File TEMPLATE_FILE_FTL = new File(OUT_RESOURSE_CATALAG+FileUtils.getFileSeparator()+TEMPLATE_RESOURSE_CATALAG + "templateFTL.ftl");
	// 业务平台命名空间
	public static final String NAME_SPACE_JY = "jy";
	// ftl命名分割符
	public static final String FTL_DELIMITER = "-";
	
	private static final Log log = LogFactory.getLog(FtlFileWriter.class);
	
	public static void process2(String type, ExcelTable eTable, Map<String, ?> root,String report_name,OutputStream out) throws Exception {
		// parse
		java.io.Writer writer = null;
		OutputStreamWriter outputStreamWriter = null;
		OutputStream fileOutputStream = out;
		StringBuffer sb = null;

		ParserRule.printMap((Map<String, Object>) root);
		try{
		sb = new StringBuffer();
		sb.append(OUT_RESOURSE_CATALAG);
		sb.append(FileUtils.getFileSeparator());

		String pinyinReportName = PinyinUtils.getFirstSpell(report_name);
		String pinyinSheetName = PinyinUtils.getFirstSpell(eTable.getSheetName());
		File f = null;
		if ("ftl".equalsIgnoreCase(type)) {//FTL模板文件生成
			sb.append( "WEB-INF")
			.append(FileUtils.getFileSeparator() + "view" + FileUtils.getFileSeparator() + "jy"+ FileUtils.getFileSeparator() 
					+ "report" + FileUtils.getFileSeparator() + "bg")
			.append(FileUtils.getFileSeparator() + pinyinReportName)
			.append(FileUtils.getFileSeparator())
			.append(NAME_SPACE_JY)
			.append(FTL_DELIMITER)
			.append(pinyinReportName)
			.append(FTL_DELIMITER)
			.append(pinyinSheetName)
			.append(".ftl");			
			
			f = TEMPLATE_FILE_FTL;

			File file = new File(sb.toString());
			File fileCatalog = new File(file.getParent());
			
			if (!fileCatalog.exists()) {
				fileCatalog.mkdirs();
			}
			if (file.createNewFile()) {
				log.info("输出文件" + file.getCanonicalPath() + "已经创建");
			} else {
				throw new Exception("Error: 输出文件" + file.getCanonicalPath() + "创建失败");
			}

			fileOutputStream = new FileOutputStream(file);
		}else{
			throw new Exception("Error: 不能识别模版文件类型。");
		}
		outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
		writer = new BufferedWriter(outputStreamWriter);
 		FreeMarkerUtils.process(f, root, writer);
		}catch(Exception xe){
			xe.printStackTrace();
		}finally{
	 		try{
	 			if(writer!=null){
		 			writer.close();
	 			}
	 		}catch(Exception xe){

	 		}
		}
	}
	
	/***
	 * 生成SQL文件
	 * @param type
	 * @param eTable
	 * @param root
	 * @param report_name
	 * @param out
	 * @throws Exception
	 */
	public static void processSQL(String type, Map<String, ?> params,String report_name,OutputStream out) throws Exception {
		// parse
		java.io.Writer writer = null;
		OutputStreamWriter outputStreamWriter = null;
		OutputStream fileOutputStream = out;
		StringBuffer sb = null;

		try{
			sb = new StringBuffer();
			sb.append(OUT_RESOURSE_CATALAG);
			sb.append(FileUtils.getFileSeparator());
			if ("sql".equalsIgnoreCase(type)) {//FTL模板文件生成
				outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
				writer = new BufferedWriter(outputStreamWriter);
				File sql_ftl = new File(OUT_RESOURSE_CATALAG+FileUtils.getFileSeparator()+TEMPLATE_RESOURSE_CATALAG + "templateSQL.ftl");
		 		FreeMarkerUtils.process(sql_ftl, params, writer);
			}else if ("entity".equalsIgnoreCase(type)) {//FTL模板文件生成
				outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
				writer = new BufferedWriter(outputStreamWriter);
				File sql_ftl = new File(OUT_RESOURSE_CATALAG+FileUtils.getFileSeparator()+TEMPLATE_RESOURSE_CATALAG + "templateEntity.ftl");
		 		FreeMarkerUtils.process(sql_ftl, params, writer);
			}else{
				throw new Exception("Error: 不能识别模版文件类型。");
			}
		}catch(Exception xe){
			xe.printStackTrace();
		}finally{
	 		try{
	 			if(writer!=null){
		 			writer.close();
	 			}
	 		}catch(Exception xe){

	 		}
		}
	}
	/***
	 * 生成Entity文件
	 * @param type
	 * @param eTable
	 * @param root
	 * @param report_name
	 * @param out
	 * @throws Exception
	 */
	public static void processEntity(String type, Map<String, ?> params,String report_name,OutputStream out) throws Exception {

		// parse
		java.io.Writer writer = null;
		OutputStreamWriter outputStreamWriter = null;
		if ("entity".equalsIgnoreCase(type)) {//FTL模板文件生成
			List<Map<String,Object>> talbe_list = (List<Map<String,Object>>)params.get("table");
			for(Map<String,Object> talbe :talbe_list){
				//--------------------------------------------------------
				List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
				list1.add(talbe);
				Map<String, Object> param_entity = new HashMap<String,Object>();
				param_entity.putAll(params);
				param_entity.put("table", list1);
				//========================================================
				//文件名
				String class_name = String.valueOf(talbe.get("cls_name"))+"Entity";
				String file_name = OUT_ENTITY_PATH+class_name+".java";
				File file = new File(file_name);
				if(!file.exists()){
					file.createNewFile();
				}
				FileOutputStream fileOut = new FileOutputStream(file);
				//========================================================
				outputStreamWriter = new OutputStreamWriter(fileOut, "UTF-8");
				try{
					writer = new BufferedWriter(outputStreamWriter);
					File entity_ftl = new File(OUT_RESOURSE_CATALAG+FileUtils.getFileSeparator()+TEMPLATE_RESOURSE_CATALAG + "templateEntity.ftl");
			 		FreeMarkerUtils.process(entity_ftl, param_entity, writer);
				}catch(Exception xe){
					xe.printStackTrace();
				}finally{
			 		try{
			 			if(writer!=null){
				 			writer.close();
			 			}
			 		}catch(Exception xe){

			 		}
				}
			}
		}else{
			throw new Exception("Error: 不能识别模版文件类型。");
		}
	}
}
