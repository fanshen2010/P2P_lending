package cn.com.p2p.framework.jofc;

import org.apache.commons.lang.StringUtils;

/**
 * 标签值
 * @author zhirong
 *
 */
public class TagValue extends TagChart {

	private static final long serialVersionUID = 1L;

	private Number x;
	
	private Number y;
	
	private Integer rotate = 0;
	
	/**
	 * 获得JSON数据
	 */
	public String getJson() {
		StringBuffer jsonBuff = new StringBuffer();
		Number fontSize = getFontSize() == null ? 12 : getFontSize();
		jsonBuff.append("{\"x\":")
				.append(x)
				.append(",\"y\":")
				.append(y)
				.append(",\"axis\":")
				.append("\"")
				.append(getAxis())
				.append("\"")
				.append(",\"pad-x\":")
				.append(getxPad())
				.append(",\"pad-y\":")
				.append(getyPad())
				.append(",\"align-x\":")
				.append("\"")
				.append(getxAlign())
				.append("\"")
				.append(",\"align-y\":")
				.append("\"")
				.append(getyAlign())
				.append("\"")
				.append(",\"font-size\":")
				.append(fontSize)
				.append(",\"colour\":")
				.append("\"")
				.append(getColour())
				.append("\"")
				.append(",\"font\":")
				.append("\"")
				.append(getFont())
				.append("\"")
				.append(",\"rotate\":")
				.append(rotate);
		
		if (StringUtils.isNotEmpty(getText())) {
			jsonBuff.append(",\"text\":")
					.append("\"")
					.append(getText())
					.append("\"");
		}
		
		jsonBuff.append(",\"bold\":")
				.append(isBold());
			if (StringUtils.isNotEmpty(getOnClick())) {
				jsonBuff.append(",\"on-click\":")
					.append("\"")
					.append(getOnClick())
					.append("\"");
			}
		jsonBuff.append("}");
		return jsonBuff.toString();
	}

	/**
	 * 相对标签x轴偏移
	 * @return
	 */
	public Number getX() {
		return x;
	}

	/**
	 * 设置相对标签x轴偏移
	 */
	public void setX(Number x) {
		this.x = x;
	}

	/**
	 * 相对标签y轴偏移
	 * @return
	 */
	public Number getY() {
		return y;
	}

	/**
	 * 设置相对标签y轴偏移
	 */
	public void setY(Number y) {
		this.y = y;
	}

	/**
	 * 旋转角度
	 * @return
	 */
	public Integer getRotate() {
		return rotate;
	}

	/**
	 * 设置旋转角度
	 * @param rotate
	 */
	public void setRotate(Integer rotate) {
		this.rotate = rotate;
	}
	
}
