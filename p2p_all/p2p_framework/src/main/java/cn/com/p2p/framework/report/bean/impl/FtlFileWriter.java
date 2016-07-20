package cn.com.p2p.framework.report.bean.impl;

import java.io.OutputStream;
import java.util.Map;

import cn.com.p2p.framework.report.ExcelTable;
import cn.com.p2p.framework.report.ExcleTablWorkBook;
import cn.com.p2p.framework.report.bean.Writer;



/**
 * 输出Ftl模板文件
 * @author kezhida
 *
 */
public class FtlFileWriter implements Writer {

	public void writer(OutputStream out, ExcleTablWorkBook tableWrokBook,
			Map<String, Object> dataSource) {
		String report_name = tableWrokBook.getSetting().get("document.report.name");
		String report_id = tableWrokBook.getSetting().get("template.type");
		for(ExcelTable excelTable :tableWrokBook.getSheetTable()){
			Map map = excelTable.getHtmlData();
			if(map!=null){
				map.put("report_id", report_id);
				map.put("report_name", report_name);
			}
			try {
				WriterUtil.process2("ftl", excelTable,map ,report_name,out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
