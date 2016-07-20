package cn.com.p2p.ui.control;

import java.text.DecimalFormat;
import java.util.List;

import cn.com.p2p.framework.util.StringUtils;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 格式化数字
 * 
 * @author 
 *
 */
public class NumberFormat implements TemplateMethodModel {

	/**
	 * 格式化数字
	 * 例：
	 *    500000，3（没有小数）-> 500，000
	 *    500000，2（小数位不固定）-> 500，000.000
	 *    500000，1（小数位固定）-> 500，000.00
	 */
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (null != arguments && 0 < arguments.size()) {
			Number v = 0;
			Object obj = arguments.get(0);
			if (!StringUtils.objectNull(obj)) {
				v = Double.parseDouble((String) obj);
			}
			String flag = (String) arguments.get(1);
			String formatStr = "#,##0.00"; // 固定小数位
			if (StringUtils.compare(flag, "2")) {// 小数位不定
				formatStr = "#,##0.##";
			} else if (StringUtils.compare(flag, "3")) {// 没有小数位
				formatStr = "#,##0";
			}
			DecimalFormat formatorNoPrefix = new DecimalFormat(formatStr);
			String r = formatorNoPrefix.format(v);
			return r;
		} else {
			return null;
		}
	}
}
