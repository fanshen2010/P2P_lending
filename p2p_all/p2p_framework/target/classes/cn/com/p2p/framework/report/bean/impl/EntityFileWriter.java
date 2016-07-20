package cn.com.p2p.framework.report.bean.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.p2p.framework.report.ExcleTablWorkBook;
import cn.com.p2p.framework.report.bean.Writer;
import cn.com.p2p.framework.report.tool.ParserRule;
import cn.com.p2p.framework.report.tool.PinyinUtils;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;

/**
 * 输出SQL文件
 * @author kezhida
 *
 */
public class EntityFileWriter implements Writer {
	

	/**
	 * 将模板参数转换成适合SQL输出的模板数据
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	private Map<String,Object> convertParamToEntity(Map<String, ?> params){
		Map<String,Object> sqlParam = new HashMap<String,Object>();
		List<String> pageList = (List<String>) params.get("page_name");//获取页码

		String report_type =(String)params.get("template.type");
		List<Map<String,Object>> sqlTablList = new ArrayList<Map<String,Object>>();
		for(int i =0;i<pageList.size();i++){
			Map<String,Object> tableMap = new HashMap<String,Object>();
			String page_key = String.format("page_%s",i);
			String sheetName = pageList.get(i);
			String pinyinSheetName = PinyinUtils.getFirstSpell(sheetName);
			//表名
			String table_name = String.format("RP_JY_%s_%s", report_type.toUpperCase(),pinyinSheetName.toUpperCase());
			tableMap.put("cls_name", fromatToJavaField(table_name));
			tableMap.put("tbl_name", table_name.toUpperCase());
			tableMap.put("cls_desc", sheetName);
			
			List<Map<String,String>> fieldProperties = new ArrayList<Map<String,String>>();
			//每个报告页对应的字段变量值（未解析）
			List<String> fieldList = (List<String>) params.get(page_key);
			for(int j=0;fieldList!=null && j<fieldList.size();j++){
				String field = fieldList.get(j);
				Map<String,String> map = parseField(field);
				if(map==null ||map.size()==0) continue;
				String fieldName = map.get("field");
				String fieldType = map.get("dbtype");
				String fieldLength = map.get("dblength");
				//转换成java属性名
				if(!StringUtils.isEmpty(fieldName)){
					map.put("jfield", fromatToJavaField(fieldName));
					map.put("pname", StringUtils.deCapitalize(fromatToJavaField(fieldName)));
				}
				//转换成java数据类型
				if(!StringUtils.isEmpty(fieldType)){
					map.put("jtype", fromatToJavaType(fieldType,fieldLength));
				}
				fieldProperties.add(map);
			}
			tableMap.put("fields", fieldProperties);
			sqlTablList.add(tableMap);
		}
		sqlParam.put("table", sqlTablList);
		sqlParam.put("now", DateUtils.formatCurrentDateTime("yyyy-MM-dd"));
		return sqlParam;
	}

	/***
	 * 解析数据库字段值
	 * @param field
	 * @return
	 */
	private Map<String,String> parseField(String field){
		Map<String,String> resMap = null;
		boolean isDbField = ParserRule.isDBField(field);
		if(!isDbField) return null;//不是数据字段
		resMap = ParserRule.parsePropertis(field);
		return resMap;
	}
	
	/**
	 * 转换成java对象属性名
	 * @param field
	 * @return
	 */
	private String fromatToJavaField(String field){
		String lField = field.toLowerCase();
		String[] partArry=lField.split("_");
		StringBuilder resultField = new StringBuilder();
		for(int i=0;i<partArry.length;i++){
			resultField.append(StringUtils.capitalize(partArry[i]));
		}
		return resultField.toString();
	}
	
	/**
	 * 获得数据库类型所对应的java类型。
	 * @param type
	 * @param length
	 * @return
	 */
	private String fromatToJavaType(String type,String length){
		if(StringUtils.isEmpty(type)) return "";
		String ltype = type.toLowerCase();
		String jtype ="";
		if(ltype.matches("^varchar|varchar2|char|clob$")){
			jtype="String";
		}else if(ltype.matches("^int|number|integer$")){
			jtype="Long";
			if(StringUtils.isNotEmpty(length) && length.indexOf('_')>0){
				jtype="Double";
			}
		}else if(ltype.matches("^date$")){
			jtype="Date";
		}
		return jtype;
	}

	public void writer(OutputStream out, ExcleTablWorkBook tableWrokBook,
			Map<String, Object> dataSource) {
		String report_name = tableWrokBook.getSetting().get("document.report.name");
		try {
			Map<String,Object> param = tableWrokBook.getTemplateInfo();
			//下载Entity文件
			WriterUtil.processSQL("entity",convertParamToEntity(param)/*转换参数*/,report_name,out);
			//Entity文件直接输出到指定目录中
			WriterUtil.processEntity("entity",convertParamToEntity(param)/*转换参数*/,report_name,out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
