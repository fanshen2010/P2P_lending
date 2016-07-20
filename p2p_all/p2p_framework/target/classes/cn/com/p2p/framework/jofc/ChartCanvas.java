package cn.com.p2p.framework.jofc;

import java.util.ArrayList;
import java.util.List;

import ro.nextreports.jofc2.model.Chart;
import ro.nextreports.jofc2.model.Text;

/**
 * jofc Chart 扩展
 * @author zhirong
 * @see Chart
 */
public class ChartCanvas {
	
	private static final long serialVersionUID = 1L;
	
	private List<TagChart> tags = new ArrayList<TagChart>();
	
	public Chart canvas;
	
	public ChartCanvas() {
		this("");
	}
	
	public ChartCanvas(String titleText) {
		this(titleText, null);
	}
	
	public ChartCanvas(String titleText, String style) {
		canvas = new Chart();
		canvas.setTitle(new Text(titleText, style));
	}
	
	/**
	 * 获得图表的JSON数据
	 * @return
	 */
	@Override
	public String toString() {
		String jsonStr = canvas.toString();
		String[] arr = jsonStr.split("\"elements\"\\:\\[");
		StringBuffer tagsBuff = new StringBuffer();
		tagsBuff.append(arr[0]).append("\"elements\":[");
		for (TagChart tag : tags) {
			tagsBuff.append(tag.getJson());
		}
		tagsBuff.append(arr[1]);
		return tagsBuff.toString();
	}

	/**
	 * 获得图中的标签(label)集合
	 * @return
	 */
	public List<TagChart> getTags() {
		return tags;
	}

	/**
	 * 设置图中的标签(label)集合
	 * @return
	 */
	public void setTags(List<TagChart> tags) {
		this.tags = tags;
	}
	
	/**
	 * 向图中添加标签(label)
	 * @return
	 */
	public List<TagChart> addTags(TagChart tag) {
		tags.add(tag);
		return tags;
	}

}
