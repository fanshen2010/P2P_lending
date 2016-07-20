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


/**
 * 输出SQL文件
 * @author kezhida
 *
 */
public class SqlFileWriter implements Writer {

	public void writer(OutputStream out, ExcleTablWorkBook tableWrokBook,
			Map<String, Object> dataSource) {
		String report_name = tableWrokBook.getSetting().get("document.report.name");
		try {
			Map<String,Object> param = tableWrokBook.getTemplateInfo();
			WriterUtil.processSQL("sql",convertParamToFtl(param)/*转换参数*/,report_name,out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将模板参数转换成适合SQL输出的模板数据
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	private Map<String,Object> convertParamToFtl(Map<String, ?> params){
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
			tableMap.put("tbl_name", table_name);
			tableMap.put("tbl_desc", sheetName);
			
			List<Map<String,String>> fieldProperties = new ArrayList<Map<String,String>>();
			//每个报告页对应的字段变量值（未解析）
			List<String> fieldList = (List<String>) params.get(page_key);
			for(int j=0;fieldList!=null && j<fieldList.size();j++){
				String field = fieldList.get(j);
//System.out.println(field);
				Map<String,String> map = parseField(field);
				if(map==null ||map.size()==0) continue;
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
}
