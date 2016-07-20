package cn.com.p2p.framework.util;

/**=============================================================
 * <p>文件: common.util.Xml2JsonUtil </p>
 * <p>所包含类: Xml2JsonUtil </p>
 * <p>功能描述: </p>
 * <p><b>中航金网(北京)电子商务有限公司</b> 版权所有&copy;2009-2065</p>
 * 
 * ========================== 维护日志 ===========================
 * 日期				作者				内容 
 * -------------------------------------------------------------
 * 2009-09-23		liuzuwu			主要实现XML2JSON格式
 * ========================== 维护日志 ==========================*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xml2JsonUtils implements Serializable {
	private static final long serialVersionUID = -6483762744800946548L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String NODE_ITME = "item";
	private static final String NODE_CONTENT = "content";
	private static final String NODE_NAME = "name";
	private int NODE_COUNT = 0; // 节点个数
	private static Document doc = null; // 当前结构文档

	private static Object _init_lock = new Object();
	private static Xml2JsonUtils xml2JsonUtil = null;

	private Xml2JsonUtils() {
	}

	public static Xml2JsonUtils getInstance() {
		if (xml2JsonUtil == null) {
			synchronized (_init_lock) {
				if (xml2JsonUtil == null) {
					xml2JsonUtil = new Xml2JsonUtils();
				}
			}
		}
		return xml2JsonUtil;
	}

	/**
	 * 根据字符串生成XML结构文件
	 * 
	 * @param xmlStr
	 * @param path
	 */
	public void writeXMLFile(String xmlStr, String path) {
		SAXBuilder builder = new SAXBuilder();
		StringReader reader = new StringReader(xmlStr);
		OutputStream out = null;
		try {
			Document doc = builder.build(reader);
			out = new BufferedOutputStream(new FileOutputStream(path));
			Format format = Format.getRawFormat();
			format.setEncoding("UTF-8");
			format.setIndent("  ");
			XMLOutputter xmlOut = new XMLOutputter(format);
			xmlOut.output(doc, out);
			reader.close();
			out.flush();
		} catch (Exception ex) {
			logger.trace("写入XML文件抛出异常,路径为： " + path);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 文件存放路径
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public String xml2json(String filePath) {
		StringBuffer sb = new StringBuffer();
		SAXBuilder builder = new SAXBuilder();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			doc = builder.build(in);
			Element root = doc.getRootElement();
			NODE_COUNT = 0;
			recursionFind(root, sb);
			in.close();
		} catch (Exception ex) {
			logger.trace("没有找到XML文件,路径为： " + filePath);
		}
		return sb.toString();
	}

	/**
	 * 递归查找子结点
	 * 
	 * @param element
	 * @param sb
	 */
	@SuppressWarnings("unchecked")
	private void recursionFind(Element element, StringBuffer sb) {
		List<Element> list = element.getChildren(NODE_ITME);
		if (list != null && list.size() > 0) {
			for (int i = 0, n = list.size(); i < n; i++) {
				Element item = list.get(i);
				Element content = item.getChild(NODE_CONTENT);
				String name = content.getChildText(NODE_NAME);
				NODE_COUNT++;
				if (hasChildren(item)) {
					sb.append("\n{ attributes: { id : \"tree_node_" + NODE_COUNT + "\" }, data: \"" + name + "\", state: \"close\", children :[");
					recursionFind(item, sb);
					if (i == (n - 1)) {
						sb.append("]}");
					} else {
						sb.append("]},");
					}
				} else {
					if (i == (n - 1)) {
						sb.append("\n{ attributes: { id : \"tree_node_" + NODE_COUNT + "\" }, data: \"" + name + "\" }");
					} else {
						sb.append("\n{ attributes: { id : \"tree_node_" + NODE_COUNT + "\" }, data: \"" + name + "\" },");
					}
				}
			}
		}
	}

	/**
	 * 判断是否有孩子节点
	 * 
	 * @param item
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean hasChildren(Element item) {
		boolean flag = false;
		List<Element> list = item.getChildren(NODE_ITME);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}
}
