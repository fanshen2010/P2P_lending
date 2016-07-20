package cn.com.p2p.framework.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cn.com.p2p.framework.report.ExcelTable.DataEnum;
import cn.com.p2p.framework.report.tool.ParserRule;
import cn.com.p2p.framework.util.StringUtils;



public class ExcleTablWorkBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,String> setting = new ConcurrentHashMap<String, String>();
	private Map<String,String> pageSign = new ConcurrentHashMap<String, String>();
	private Map<String,String> fypageSign = new ConcurrentHashMap<String, String>();
	private List<ExcelTable> sheetTable = new ArrayList<ExcelTable>();
	private String reportName ;
	/**
	 * 默认构造函数
	 */
	public ExcleTablWorkBook(){
		
	}
	/***
	 * 根据Excel文件初始化对象
	 * @param excelFile 模板文件
	 */
	public void initWrokBook(File excelFile){
		// 文件不存在时， 不执行方法
		if (!excelFile.exists()) {
			return ;
		}
		try {
			//*****************************************************************
			String excelFileName = excelFile.getName();
			String fileName = excelFileName.replaceAll("^(?i)(.+)\\.xlsx?$","$1");
			setting.put("document.scale", String.valueOf(ExcelTable.SF_SCALE));
			setting.put("document.report.name", fileName);
			// 将存在的文件交给poi
			FileInputStream is = new FileInputStream(excelFile);
			HSSFWorkbook workBook = new HSSFWorkbook(is);

			int sheetCount = workBook.getNumberOfSheets();
			HSSFSheet sheet = null;
			// 遍历每一个sheet页
			for (int i = 0; i < sheetCount; i++) {
				// 取得sheet页
				sheet = workBook.getSheetAt(i);
				if("setting".equals(sheet.getSheetName())) {
					int b_row = sheet.getFirstRowNum();
					int l_row = sheet.getLastRowNum();
					for(int si = b_row;si<=l_row;si++){
						Row row = sheet.getRow(si);
						if(row ==null){
							continue;
						}
						Cell keyCell = row.getCell(0);
						Cell valCell = row.getCell(1);
						if(keyCell==null || valCell==null) continue;
						String key_value =ExcelTable.getCellValue(keyCell);
						String val_value =ExcelTable.getCellValue(valCell);
						setting.put(key_value, val_value);
					}
					continue;
				}
				ExcelTable eTable = new ExcelTable();
				eTable.initTableInfo(sheet);
				//eTable.print();
				sheetTable.add(eTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 何灵光数据导入使用
	 * 根据Excel文件初始化数据
	 * @param excelFile 模板文件
	 */
	public List<Map<String,Object>> importExcelToDatabase(File excelFile,String type){
		// 文件不存在时， 不执行方法
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (!excelFile.exists()) {
			return null;
		}
		try {
			//*****************************************************************
			List<Map<String,Object>> list_key = new ArrayList<Map<String,Object>>();
			String excelFileName = excelFile.getName();
			String fileName = excelFileName.replaceAll("^(?i)(.+)\\.xlsx?$","$1");
			setting.put("document.scale", String.valueOf(ExcelTable.SF_SCALE));
			setting.put("document.report.name", fileName);
			// 将存在的文件交给poi
			FileInputStream is = new FileInputStream(excelFile);
			HSSFWorkbook workBook = new HSSFWorkbook(is);

			int sheetCount = workBook.getNumberOfSheets();
			HSSFSheet sheet = null;
			// 遍历每一个sheet页
			for (int i = 0; i < sheetCount; i++) {
				// 取得sheet页
				sheet = workBook.getSheetAt(i);
				int b_row = sheet.getFirstRowNum();
				int l_row = sheet.getLastRowNum();
				for(int si = b_row;si<=l_row;si++){
					Row row = sheet.getRow(si);
					if(row ==null){
						continue;
					}
					//制造单位和许可证信息
					if ("zzdw".equals(type)) {
						if (si==0) {
							continue;
						}
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("xkzbh", nulltoblank(ExcelTable.getCellValue(row.getCell(2))));
						map.put("dwmc", nulltoblank(ExcelTable.getCellValue(row.getCell(3))));
						map.put("dwdm", nulltoblank(ExcelTable.getCellValue(row.getCell(4))));
						map.put("xkzjb", nulltoblank(ExcelTable.getCellValue(row.getCell(5))));
						map.put("xkzxkfw", nulltoblank(ExcelTable.getCellValue(row.getCell(6))));
						map.put("xkzjb1", nulltoblank(ExcelTable.getCellValue(row.getCell(7))));
						map.put("xkzxkfw1", nulltoblank(ExcelTable.getCellValue(row.getCell(8))));
						map.put("xkzjb2", nulltoblank(ExcelTable.getCellValue(row.getCell(9))));
						map.put("xkzxkfw2", nulltoblank(ExcelTable.getCellValue(row.getCell(10))));
						map.put("fzrq", nulltoblank(ExcelTable.getDateCellValue(row.getCell(11))));
						map.put("yxq1", nulltoblank(ExcelTable.getDateCellValue(row.getCell(12))));
						map.put("yxq2", nulltoblank(ExcelTable.getDateCellValue(row.getCell(13))));
						map.put("txdz", nulltoblank(ExcelTable.getCellValue(row.getCell(14))));
						map.put("jyks", nulltoblank(ExcelTable.getCellValue(row.getCell(15))));
						map.put("frdb", nulltoblank(ExcelTable.getCellValue(row.getCell(16))));
						map.put("zbgcs", nulltoblank(ExcelTable.getCellValue(row.getCell(17))));
						map.put("cz", nulltoblank(ExcelTable.getCellValue(row.getCell(18))));
						map.put("yzbm", nulltoblank(ExcelTable.getCellValue(row.getCell(19))));
						map.put("lxr", nulltoblank(ExcelTable.getCellValue(row.getCell(20))));
						map.put("lxrdh", nulltoblank(ExcelTable.getCellValue(row.getCell(21))));
						list.add(map);
					} else if ("sb".equals(type)) {//设备信息导
						//key值
						if (si==0) {
							for (int j = 0; j < 100; j++) {
								Map<String,Object> keyMap = new HashMap<String,Object>();
								Object obj = ExcelTable.getCellValue(row.getCell(j));
								if (obj != null) {
									String key = nulltoblank(obj).toString();
									keyMap.put("key", ExcelTable.getCellValue(row.getCell(j)));
									keyMap.put("num", j);
									if (key.indexOf("RQ") >= 0) {
										keyMap.put("rq", "1");
									} else {
										keyMap.put("rq", "0");
									}
									list_key.add(keyMap);
								} else {
									break;
								}
							}
						} else {//具体数据
							Map<String,Object> map = new HashMap<String,Object>();
							for (Map<String, Object> temp_temp : list_key) {
								if (temp_temp.get("key") != null) {
									if ("0".equals(temp_temp.get("rq"))) {
										map.put(temp_temp.get("key").toString(), 
												nulltoblank(ExcelTable.getCellValue(row.getCell(Integer.parseInt(temp_temp.get("num").toString())))));
									} else {
										try{
											map.put(temp_temp.get("key").toString(), 
													nulltoblank(ExcelTable.getDateCellValue(row.getCell(Integer.parseInt(temp_temp.get("num").toString())))));
										} catch (Exception e) {
											map.put(temp_temp.get("key").toString(), 
													nulltoblank(ExcelTable.getCellValue(row.getCell(Integer.parseInt(temp_temp.get("num").toString())))));
										}
									}
								}
							}
							list.add(map);
						}
					} else {
						if (si==0) {
							continue;
						}
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("sydwmc",nulltoblank(ExcelTable.getCellValue(row.getCell(0))));
						map.put("sydwdm",nulltoblank(ExcelTable.getCellValue(row.getCell(1))));
						map.put("sydwdz",nulltoblank(ExcelTable.getCellValue(row.getCell(2))));
						map.put("yzbm",nulltoblank(ExcelTable.getCellValue(row.getCell(3))));
						map.put("sfdm",nulltoblank(ExcelTable.getCellValue(row.getCell(4))));
						map.put("dsmc",nulltoblank(ExcelTable.getCellValue(row.getCell(5))));
						map.put("dsdm",nulltoblank(ExcelTable.getCellValue(row.getCell(6))));
						map.put("qxmc",nulltoblank(ExcelTable.getCellValue(row.getCell(7))));
						map.put("qxdm",nulltoblank(ExcelTable.getCellValue(row.getCell(8))));
						map.put("aqglbm",nulltoblank(ExcelTable.getCellValue(row.getCell(9))));
						map.put("aqglry",nulltoblank(ExcelTable.getCellValue(row.getCell(10))));
						map.put("dh",nulltoblank(ExcelTable.getCellValue(row.getCell(11))));
						map.put("glszdd",nulltoblank(ExcelTable.getCellValue(row.getCell(12))));
						map.put("dddh",nulltoblank(ExcelTable.getCellValue(row.getCell(13))));
						list.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> importZzdwInfo(File excelFile){
		List<Map<String,Object>> reData = new ArrayList<Map<String,Object>>();
		return reData;
	}
	
	private Object nulltoblank(Object obj){
		if (obj == null) {
			return "";
		}
		return obj;
	}

	public List<ExcelTable> getSheetTable() {
		return sheetTable;
	}

	public void setSheetTable(List<ExcelTable> sheetTable) {
		this.sheetTable = sheetTable;
	}

	public Map<String, String> getSetting() {
		return setting;
	}

	public void setSetting(Map<String, String> setting) {
		this.setting = setting;
	}
	
	public void printThis(){
		// 遍历每一个sheet页
		for (int i = 0; i < sheetTable.size(); i++) {
			// 取得sheet页
			sheetTable.get(i).print();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getTemplateInfo(){
		Map<String,Object> map = new ConcurrentHashMap<String, Object>();
		Map<String,Object> var_map = new ConcurrentHashMap<String, Object>();
		map.putAll(setting);
		map.putAll(pageSign);
		map.putAll(fypageSign);
		Map<String,Object> var_mapp=(Map)setting;
		map.put("var_map", var_mapp);//模板所有变量值
		//目录
		List<String> dir_list = new ArrayList<String>();
		map.put("page_name", dir_list);//报告目录名
		List<String> page_all_list = new ArrayList<String>();
		for(int i =0;i<sheetTable.size();i++){
			ExcelTable table=sheetTable.get(i);
			dir_list.add(table.getSheetName());//目录名
			String page_key = String.format("page_%s",i);
			List<String> list = (List<String>) map.get(page_key);
			List<Map<DataEnum, String>> varList= table.getValListMap();
			for(Map<DataEnum, String> item:varList){
				String value = item.get(DataEnum.value);
				String[] value_item = value.split(";");
				for(String it_value :value_item ){
					if(StringUtils.isEmpty(it_value))
						continue;
					if(ParserRule.isVarField(it_value)){//是变量值
						page_all_list.add(it_value);
						String var_field = ParserRule.getFieldName(it_value);
						var_map.put(var_field, it_value);
						if(list==null){
							list = new ArrayList<String>();
							map.put(page_key,list);
							list.add(it_value);
						}else{
							list.add(it_value);
						}
					}
				}
			}
		}
		//ParserRule.printMap(map);
		return map;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public Map<String, String> getPageSign() {
		return pageSign;
	}
	public void setPageSign(Map<String, String> pageSign) {
		this.pageSign = pageSign;
	}
	public Map<String, String> getFypageSign() {
		return fypageSign;
	}
	public void setFypageSign(Map<String, String> fypageSign) {
		this.fypageSign = fypageSign;
	}


	
}
