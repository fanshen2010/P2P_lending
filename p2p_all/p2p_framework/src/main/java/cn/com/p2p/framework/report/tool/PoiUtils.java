package cn.com.p2p.framework.report.tool;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;


public class PoiUtils {
	/**
	 * 打印参数信息
	 * 
	 * @param map
	 */
	public static void printMap(Map<String, ?> map) {
		System.out
				.println("*******************************************************************");
		System.out
				.println("********************************输出参数内容***************************");
		if (map != null) {
			for (Map.Entry<String, ?> entry : map.entrySet()) {
				System.out.printf("Key=%s\t\t,\t\tValue= %s\r\n",
						entry.getKey(), entry.getValue());
			}
		} else {
			System.out.printf("Map is null!");
		}
		System.out
				.println("*******************************************************************");
	}
	/**
	 * 
	 * @param sheet
	 * @return
	 */
	public static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {

		Map<String, String> map0 = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		int mergedNum = sheet.getNumMergedRegions();
		CellRangeAddress range = null;
		for (int i = 0; i < mergedNum; i++) {
			range = sheet.getMergedRegion(i);
System.out.println("Range="+range.toString());
			int firstRow = range.getFirstRow();
			int firstColumn = range.getFirstColumn();
			int lastRow = range.getLastRow();
			int lastColumn = range.getLastColumn();
			map0.put(firstRow + "," + firstColumn, lastRow + "," + lastColumn);
			int tempRow = firstRow;
			while (tempRow <= lastRow) {
				int tempCol = firstColumn;
				while (tempCol <= lastColumn) {
					map1.put(tempRow + "," + tempCol, "");
					tempCol++;
				}
				tempRow++;
			}
			map1.remove(firstRow + "," + firstColumn);
		}
		Map[] map = { map0, map1 };
		return map;
	}
	
	public static String getCellValue(HSSFCell cell) {
		if (cell == null) return null;
		String rst = null;
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // 数字
				DecimalFormat format = new DecimalFormat("#0.##");
				rst = format.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING: // 字符串
				rst = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				System.out.println(cell.getBooleanCellValue() + "   ");
				break;
			case HSSFCell.CELL_TYPE_FORMULA: // 公式
				System.out.print(cell.getCellFormula() + "   ");
				break;
			case HSSFCell.CELL_TYPE_BLANK: // 空值
				System.out.println(" ");
				break;
			case HSSFCell.CELL_TYPE_ERROR: // 故障
				System.out.println(" ");
				break;
			default:
				System.out.print("未知类型   ");
				break;
		}
		
		return rst;
	}
	
	
	public static String getCellWidth(HSSFSheet sheet, int rowNum, int colNum) {
		
		
		return null;
	}
	
	/*
	 * 根据指定的行和列号，获得cell的数据（字符串）
	 */
	public static String getCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		if (sheet == null) return null;
		if (sheet.getRow(rowNum) == null) return null;
		if (sheet.getRow(rowNum).getCell(colNum) == null) return null;
		
		HSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
		
		String rst = cell.getStringCellValue();
		
		return rst;
		
	}
	
	/*
	 * 根据指定的行和列号，获得cell的数据
	 */
	public static double getNumericCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		if (sheet == null) return 0;
		if (sheet.getRow(rowNum) == null) return 0;
		if (sheet.getRow(rowNum).getCell(colNum) == null) return 0;
		
		HSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
		
		double rst = cell.getNumericCellValue();
		
		return rst;
	}

	}
