package cn.com.p2p.framework.jofc;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ro.nextreports.jofc2.model.elements.Element;


/**
 * 扩展jofc的标签图形，使其支持在图表中绘制标签
 * @author zhirong
 *
 */
public class TagChart extends Element {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String key = "";

	private Integer xPad = 0;
	
	private Integer yPad = 0;
	
	private String xAlign = "center";
	
	private String yAlign = "above"; // above、below、center
	
	private String axis = ""; // ""、right
	
	private String font = "Arial";
	
	private String colour = "#000000";
	
	private boolean underline;
	
	private boolean italic;
	
	private boolean border;
	
	private boolean bold;
	
	private String onClick;
		
	public TagChart() {
		this(Style.NORMAL);
	}
	
	public TagChart(Style style) {
		super(style.getStyle());
	}

	public TagChart(String style) {
		super(style);
	}

	public static enum Style {
		
		NORMAL("tags");

		private String style;

		private Style(String style) {
			this.style = style;
		}

		public String getStyle() {
			return this.style;
		}
		
	}
	
	/**
	 * 获得JSON数据
	 * @return
	 */
	public String getJson() {
		StringBuffer jsonBuff = new StringBuffer();
		jsonBuff.append("{")
				.append("\"type\":")
				.append("\"")
				.append(getType())
				.append("\",");
		if (StringUtils.isNotEmpty(getId())) {
			jsonBuff.append("\"id\":")
					.append(id)
					.append(",");;
		}
		Number fontSize = getFontSize() == null ? 12 : getFontSize();
		jsonBuff.append("\"font\":")
				.append("\"")
				.append(font)
				.append("\"")
				.append(",\"font-size\":")
				.append(fontSize)
				.append(",\"colour\":\"#000000\"")
				.append(",\"pad-x\":")
				.append(xPad)
				.append(",\"pad-y\":")
				.append(yPad)
				.append(",\"rotate\":0")
				.append(",\"align-x\":")
				.append("\"")
				.append(xAlign)
				.append("\"")
				.append(",\"align-y\":")
				.append("\"")
				.append(yAlign)
				.append("\"")
				.append(",\"text\":\"")
				.append(StringUtils.isEmpty(getText()) ? "#y#" : getText())
				.append("\"");
		List<?> values = getValues();
		if (values != null && ! values.isEmpty()) {
			jsonBuff.append(",\"values\":[");
		}
		int i = 0;
		for (Object obj : values) {
			TagValue value = (TagValue) obj;
			jsonBuff.append(value.getJson());
			if (i + 1 != values.size()) {
				jsonBuff.append(",");
			}
			i ++;
		}
		jsonBuff.append("]},");
		return jsonBuff.toString();
	}

	/**
	 * 获得ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得KEY
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置KEY
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获得相对横坐标(x)轴的填充值
	 * @return
	 */
	public Integer getxPad() {
		return xPad;
	}

	/**
	 * 设置相对横坐标(x)轴的填充值
	 */
	public void setxPad(Integer xPad) {
		this.xPad = xPad;
	}

	/**
	 * 获得相对纵坐标(y)轴的填充值
	 * @return
	 */
	public Integer getyPad() {
		return yPad;
	}

	/**
	 * 设置相对纵坐标(y)轴的填充值
	 */
	public void setyPad(Integer yPad) {
		this.yPad = yPad;
	}

	/**
	 * 获得相对横坐标(x)轴的对齐方式；left、right、center
	 * @return
	 */
	public String getxAlign() {
		return xAlign;
	}

	/**
	 * 设置相对横坐标(x)轴的对齐方式
	 * @param xAlign left、right、center
	 */
	public void setxAlign(String xAlign) {
		this.xAlign = xAlign;
	}

	/**
	 * 获得相对纵坐标(y)轴的对齐方式；above、below、center
	 * @return
	 */
	public String getyAlign() {
		return yAlign;
	}

	/**
	 * 设置相对纵坐标(y)轴的对齐方式
	 * @param yAlign above、below、center
	 */
	public void setyAlign(String yAlign) {
		this.yAlign = yAlign;
	}

	/**
	 * 
	 * @return
	 */
	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	/**
	 * 获得字体
	 * @return
	 */
	public String getFont() {
		return font;
	}

	/**
	 * 设置字体
	 * @param font
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * 获得文本颜色
	 * @return
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * 设置文本颜色
	 * @param colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * 判断是否下划线
	 * @return
	 */
	public boolean isUnderline() {
		return underline;
	}

	/**
	 * 设置是否有下划线
	 * @param underline
	 */
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}

	/**
	 * 判断文本是否斜体
	 * @return
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * 设置文本是否斜体
	 * @param italic
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	/**
	 * 判断是否有边框
	 * @return
	 */
	public boolean isBorder() {
		return border;
	}

	/**
	 * 设置是否有边框
	 * @param border
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/**
	 * 判断文本是否加粗
	 * @return
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * 设置文本是否加粗
	 * @param bold
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	/**
	 * 获得点击链接
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * 设置点击链接
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	/**
	 * 向标签图中添加标签值
	 * @param tagValue
	 */
	public void addValue(TagValue tagValue) {
		getValues().add(tagValue);
	}

}
