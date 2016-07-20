package cn.com.p2p.framework.report;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.Ognl;
import ognl.OgnlException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.com.p2p.framework.report.ExcelTable.DataEnum;
import cn.com.p2p.framework.report.ExcelTable.ExRegion;
import cn.com.p2p.framework.report.tool.PinyinUtils;

/**
 * WorkBook格式转接工具类 根据Excel的模板生成XML
 * 
 * @author 张旭
 *
 */
public class WorkBookConvert {

	/**
	 * 将ExcleTablWorkBook对象转换成XML数据格式
	 * 
	 * @param workbook
	 * @return XML数据的字符串。
	 */
	public static String toXML(ExcleTablWorkBook workbook) {
		// 定义一个XML文档
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("document");
		Element settingsElement = rootElement.addElement("settings");
		// 设置选项
		for (Map.Entry<String, String> entry : workbook.getSetting().entrySet()) {
			Element settingElement = settingsElement.addElement("setting");
			settingElement.addAttribute("name", entry.getKey());// key
			settingElement.addAttribute("value", entry.getValue());// 缩放百分比
		}
		// 页面
		Element pages = rootElement.addElement("pages");

		for (int i = 0; i < workbook.getSheetTable().size(); i++) {
			ExcelTable exl_table = workbook.getSheetTable().get(i);
			Element page = pages.addElement("page");
			page.addAttribute("name", exl_table.getSheetName());
			page.addAttribute("index", String.valueOf(i));
			Element properties = page.addElement("properties");
			// x_skip
			Element propertie = properties.addElement("property");
			propertie.addAttribute("name", "x_skip");
			propertie.addAttribute("value", String.valueOf(ExcelTable.X_SKIP));
			// y_skip
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "y_skip");
			propertie.addAttribute("value", String.valueOf(ExcelTable.Y_SKIP));
			// y_skip
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "sheetName");
			propertie.addAttribute("value", exl_table.getSheetName());
			// pinyinFirstName
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "pinyinFirstName");
			propertie.addAttribute("value",
					PinyinUtils.getFirstSpell(exl_table.getSheetName()));
			// pinyinName
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "pinyinName");
			propertie.addAttribute("value",
					PinyinUtils.getPinYinStr(exl_table.getSheetName()));
			// row_count
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "row_count");
			propertie.addAttribute("value",
					String.valueOf(exl_table.getRow_count()));
			// col_count
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "col_count");
			propertie.addAttribute("value",
					String.valueOf(exl_table.getCol_count()));
			// twidth
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "twidth");
			propertie.addAttribute("value",
					String.valueOf(exl_table.getTwidth()));
			// theight
			propertie = properties.addElement("property");
			propertie.addAttribute("name", "theight");
			propertie.addAttribute("value",
					String.valueOf(exl_table.getTheight()));
			// table
			Element table = page.addElement("table");
			Element cells = table.addElement("cells");
			// Cell
			for (Map<DataEnum, String> cell_map : exl_table.getValListMap()) {
				Element cell = cells.addElement("cell");
				for (Map.Entry<DataEnum, String> cell_entity : cell_map
						.entrySet()) {
					if (cell_entity.getKey().toString().matches("^c?value$")) {
						if ("".equals(cell_entity.getValue()))
							continue;
						Element cellsValue = cell.addElement(cell_entity
								.getKey().toString());
						cellsValue.addCDATA(cell_entity.getValue());
					} else {
						cell.addAttribute(cell_entity.getKey().toString(),
								cell_entity.getValue());
					}
				}
			}
			// region
			Element regions = table.addElement("regions");
			for (ExRegion region_obj : exl_table.getRegList()) {
				Element region = regions.addElement("region");
				for (String property : region_obj.propertiesNames()) {
					String value = "";
					try {
						value = String.valueOf(Ognl.getValue(property,
								region_obj));
					} catch (OgnlException e) {
						e.printStackTrace();
					}
					if (property.matches("^c?value$")) {
						if ("".equals(value))
							continue;
						Element regionValue = region.addElement(property);
						regionValue.addCDATA(value);
					} else {
						region.addAttribute(property, value);
					}
				}
			}
		}
		String xml_string = formatXML(document, null);
		// System.out.println(xml_string);
		// ***********************************************************
		// 解析XML文件
		// ExcleTablWorkBook book = loadFromXML(xml_string);
		// ***********************************************************
		return xml_string;
	}

	/***
	 * 将XML数据转换成ExcleTablWorkBook对象。
	 * 
	 * @param xml_string
	 * @return ExcleTablWorkBook
	 */
	@SuppressWarnings("rawtypes")
	public static ExcleTablWorkBook loadFromXML(String xml_string) {
		ExcleTablWorkBook workbook = new ExcleTablWorkBook();
		List<ExcelTable> sheetTable = new ArrayList<ExcelTable>();
		try {
			Document document = DocumentHelper.parseText(xml_string);

			Element rootElt = document.getRootElement(); // 获取根节点

			Iterator iter = rootElt.elementIterator();// 获取根节点下的子节点
			// 遍历子节点（settings,pages)
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				// System.out.println("recordEle:\t\t"+recordEle.getName());
				// setting节点
				Iterator iter_setting = recordEle.elementIterator("setting");
				while (iter_setting.hasNext()) {
					Element itemEle = (Element) iter_setting.next();
					String attr_name = itemEle.attributeValue("name");
					String attr_value = itemEle.attributeValue("value");
					workbook.getSetting().put(attr_name, attr_value);
					// System.out.println("settings:\t\t"+itemEle.getName());
				}
				// page节点
				Iterator iter_pages = recordEle.elementIterator("page");
				int j = 0;
				while (iter_pages.hasNext()) {
					Element item_page = (Element) iter_pages.next();
					// 得到是否是附页的标识
					String sign = item_page.attributeValue("sign");
					if (sign == null) {
						workbook.getPageSign().put("" + j + "", "zc");
					} else {
						workbook.getPageSign().put("" + j + "", sign);
					}
					// 得到报告附页的标志
					String fysign = item_page.attributeValue("fysing");
					if (fysign != null) {
						workbook.getPageSign().put("fy" + j + "", fysign);
					}
					// System.out.println("item_page:\t\t"+item_page.getName());
					// --------------------------------------------------------------------------------------------
					// ExcelTable 的类成员变量
					ExcelTable excelTable = new ExcelTable();

					List<Map<DataEnum, String>> valListMap = new ArrayList<Map<DataEnum, String>>();
					Map<String, Map<DataEnum, String>> tmap = new ConcurrentHashMap<String, Map<DataEnum, String>>();
					List<ExRegion> regList = new ArrayList<ExRegion>();
					// ---------------------------------------------------------------------------------------------
					// properties
					Iterator iter_properties = item_page
							.elementIterator("properties");
					while (iter_properties.hasNext()) {
						Element item_properties = (Element) iter_properties
								.next();
						// System.out.println("item_properties:\t\t"+item_properties.getName());
						// property
						Iterator iter_property = item_properties
								.elementIterator("property");
						while (iter_property.hasNext()) {
							Element propertyElm = (Element) iter_property
									.next();
							// System.out.println("propertyElm:\t\t"+propertyElm.getName());
							String name = propertyElm.attributeValue("name");// name属性值

							if (name.matches("^x_skip|y_skip|pinyinFirstName|pinyinName$"))
								continue;
							String value = propertyElm.attributeValue("value");// value属性值
							Ognl.setValue(name, excelTable, value);
							// continue;//x_skip和_skip值忽略

						}
					}
					// table
					Iterator iter_table = item_page.elementIterator("table");
					while (iter_table.hasNext()) {
						Element item_table = (Element) iter_table.next();
						// System.out.println("item_table:\t\t"+item_table.getName());
						// cells
						Iterator iter_cells = item_table
								.elementIterator("cells");
						while (iter_cells.hasNext()) {
							Element iter_cellsElm = (Element) iter_cells.next();
							// System.out.println("iter_cellsElm:\t\t"+iter_cellsElm.getName());
							// cell
							Iterator iter_cellElm = iter_cellsElm
									.elementIterator("cell");
							while (iter_cellElm.hasNext()) {
								// cell
								Element cellElm = (Element) iter_cellElm.next();
								// System.out.println("iter_cellsElm:\t\t"+iter_cellsElm.getName());
								List list = cellElm.attributes();
								Map<DataEnum, String> cell_map = new ConcurrentHashMap<DataEnum, String>();
								for (int i = 0; list != null && i < list.size(); i++) {
									Attribute atrr = (Attribute) list.get(i);
									String attr_name = atrr.getName();
									String attr_value = atrr.getValue();
									DataEnum denum = Enum.valueOf(
											DataEnum.class, attr_name);
									cell_map.put(denum, attr_value);
								}
								// CDATA TEXT
								String text = cellElm.elementText("value");
								text = text == null ? "" : text;
								cell_map.put(DataEnum.value, text);
								valListMap.add(cell_map);
								tmap.put(cell_map.get(DataEnum.index), cell_map);
							}
						}
						// regions
						Iterator iter_regions = item_table
								.elementIterator("regions");
						while (iter_regions.hasNext()) {
							Element iter_regionsElm = (Element) iter_regions
									.next();
							// System.out.println("iter_cellsElm:\t\t"+iter_regionsElm.getName());
							// region
							Iterator iter_regionElm = iter_regionsElm
									.elementIterator("region");
							while (iter_regionElm.hasNext()) {
								// region
								Element regionElm = (Element) iter_regionElm
										.next();
								// System.out.println("regionElm:\t\t"+regionElm.getName());
								List list = regionElm.attributes();
								// 创建一个ExRegion对象
								ExRegion region = excelTable.newRegion();
								for (int i = 0; list != null && i < list.size(); i++) {
									Attribute atrr = (Attribute) list.get(i);
									String attr_name = atrr.getName();
									String attr_value = atrr.getValue();
									Ognl.setValue(attr_name, region, attr_value);
								}
								// CDATA TEXT
								String text = regionElm.elementText("cvalue");
								text = text == null ? "" : text;
								Ognl.setValue("cvalue", region, text);

								regList.add(region);
							}
						}
					}
					// 完成一页XML内容的解析
					excelTable.setRegList(regList);
					excelTable.setTmap(tmap);
					excelTable.setValListMap(valListMap);
					// 设置page页信息
					sheetTable.add(excelTable);
					// ----------------------------------------------------------
					j++;
				}

			}
			workbook.setSheetTable(sheetTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initWrokBookForXML(workbook);
		return workbook;
	}

	/**
	 * 从XML解析生成的ExcleTablWorkBook对象，需要对ExcelTable中的部分数据进行初始化， 保证数据格式的一致性
	 * 根据XML字符串初始化ExcleTablWorkBook对象
	 */
	private static void initWrokBookForXML(ExcleTablWorkBook workBook) {
		// 遍历每一个sheet页
		for (int i = 0; i < workBook.getSheetTable().size(); i++) {
			ExcelTable excelTable = workBook.getSheetTable().get(i);
			excelTable.initForXML();
		}
	}

	/**
	 * 格式化XML文档
	 * 
	 * @param document
	 *            xml文档
	 * @param charset
	 *            字符串的编码
	 * @return 格式化后XML字符串
	 */
	public static String formatXML(Document document, String charset) {
		String chset = "UTF-8";
		if (charset != null && charset.trim().length() > 0) {
			chset = charset;
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(chset);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		try {
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * 根据所选择的子报告页重新生成xml--移除xml节点
	 * 
	 * @return
	 */
	public static String cxLoadxml(String xml_string, String id) {
		String znewXml = null;

		try {
			Document document = DocumentHelper.parseText(xml_string);

			Element rootElt = document.getRootElement(); // 获取根节点
			// 截取节点为pages的xml
			String xpath = "/document/pages";
			Element ele = (Element) rootElt.selectSingleNode(xpath);
			// 循环所有pages下面的节点
			Iterator iterElt = ele.elementIterator();// 获取根节点下的子节点
			int i = 0;
			List list = new ArrayList();
			while (iterElt.hasNext()) {
				Element recordEle = (Element) iterElt.next();
				// Iterator iter_pages = recordEle.elementIterator("page");
				if (id.indexOf("" + i + "") < 0) {
					list.add(i);
				}
				i++;
			}
			for (int j = 0; j < list.size(); j++) {
				// 移除没有选中的xml
				Element book_society = (Element) document
						.selectSingleNode("/document/pages/page[@index='"
								+ list.get(j) + "']");
				ele.remove(book_society);
			}
			// 循环所有pages下面的节点
			Iterator newIterElt = ele.elementIterator();// 获取根节点下的子节点
			// 遍历子节点（settings,pages)
			// 对所有的page 节点进行重新排序
			int n = 0;
			while (newIterElt.hasNext()) {
				Element recordEle = (Element) newIterElt.next();
				recordEle.setAttributeValue("index", "" + n + "");
				n++;
			}
			znewXml = document.asXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return znewXml;
	}

	/**
	 * 插入检验报告附页---插入Xml节点
	 * 
	 * @param jyReportTemplate
	 * @param jyCyljybgmbb
	 * @param fyid
	 * @param index
	 */
	public static Map<String, String> insertXmljd(String Content,
			String jyContent, String jynewContent, String fyid, String index,
			String fysign) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			// 先截取想要插入的检验报告附页
			Document document = DocumentHelper.parseText(Content);
			// 获取附页根节点
			Element fyrootElt = document.getRootElement();
			// 得到检验报告附页的名称
			String fyxmlSetting = "/document/settings";
			Element fysettingele = (Element) fyrootElt
					.selectSingleNode(fyxmlSetting);
			// 得到settings下面的节点list
			List<Element> fysetelements = fysettingele.elements();
			String fybgvalue = null;
			// 插入附页报告的名称
			for (Element element : fysetelements) {
				String fybgName = element.attributeValue("name");
				if ("document.report.name".equals(fybgName)) {
					fybgvalue = element.attributeValue("value");
				}

			}
			// 获得检验报告附页的节点
			Element book_society = (Element) document
					.selectSingleNode("/document/pages/page[@index='" + fyid
							+ "']");
			// 得到想要被插入的Xml
			Document binsertdocument = DocumentHelper.parseText(jyContent);
			// 获取根节点
			Element rootElt = binsertdocument.getRootElement();
			// 截取节点为settings的xml
			String xmlSetting = "/document/settings";
			Element settingele = (Element) rootElt.selectSingleNode(xmlSetting);
			// 得到settings下面的节点list
			List<Element> setelements = settingele.elements();
			// 插入附页报告的名称
			for (Element element : setelements) {
				String fyName = element
						.attributeValue("document.report.fy.name");
				if (fyName == null) {
					// 得到一个新的page xml节点
					Element setting = DocumentHelper.createElement("setting");
					setting.addAttribute("name", "document.report.fy.name");
					setting.addAttribute("value", fybgvalue);
					setelements.add(setting);
					break;
				}
			}
			// 截取节点为pages的xml
			String xpath = "/document/pages";
			Element ele = (Element) rootElt.selectSingleNode(xpath);
			// 得到pages下面的节点list
			List<Element> elements = ele.elements();
			String pageName = null;
			int i = 0;
			for (Element element : elements) {
				i++;
				if (i == (Integer.parseInt(index) + 1)) {
					pageName = element.attributeValue("name");
					// 得到一个新的page xml节点
					Element page = DocumentHelper.createElement("page");
					page.addAttribute("name",
							book_society.attributeValue("name"));
					page.addAttribute("index",
							String.valueOf((Integer.parseInt(index) + 1)));
					page.addAttribute("sign", "fy");
					page.addAttribute("fysing", fysign);
					page.appendContent(book_society);
					elements.add(i, page);

					break;
				}
			}
			// 循环所有pages下面的节点
			Iterator neweleElt = ele.elementIterator();// 获取根节点下的子节点
			// 对所有的page 节点进行重新排序
			int n = 0;
			while (neweleElt.hasNext()) {
				Element recordEle = (Element) neweleElt.next();
				recordEle.setAttributeValue("index", "" + n + "");

				n++;
			}
			// ---------------------------------------------------------------------------------
			// 得到想要被插入的Xml
			Document bNewinsertdocument = DocumentHelper
					.parseText(jynewContent);
			// 获取根节点
			Element newRootElt = bNewinsertdocument.getRootElement();
			// 截取节点为pages的xml
			Element newEle = (Element) newRootElt.selectSingleNode(xpath);
			// 得到pages下面的节点list
			List<Element> newElements = newEle.elements();
			int j = 0;
			for (Element element : newElements) {
				j++;
				if (pageName.equals(element.attributeValue("name"))) {
					// 得到一个新的page xml节点
					Element page = DocumentHelper.createElement("page");
					page.addAttribute("name",
							book_society.attributeValue("name"));
					page.addAttribute("index",
							String.valueOf((Integer.parseInt(index) + 1)));
					page.addAttribute("sign", "fy");
					page.addAttribute("fysing", fysign);
					page.appendContent(book_society);
					newElements.add(j, page);
					break;
				}
			}
			// 循环所有pages下面的节点
			Iterator newIterElt1 = newEle.elementIterator();// 获取根节点下的子节点
			// 对所有的page 节点进行重新排序
			int m = 0;
			while (newIterElt1.hasNext()) {
				Element recordEle = (Element) newIterElt1.next();
				recordEle.setAttributeValue("index", "" + m + "");
				m++;
			}
			// ----------------------------------------------------------------------------------

			map.put("jyContent", formatXML(binsertdocument, null));
			map.put("jynewContent", formatXML(bNewinsertdocument, null));

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return map;

	}
}
