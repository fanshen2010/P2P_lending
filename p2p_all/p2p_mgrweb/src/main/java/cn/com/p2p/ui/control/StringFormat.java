package cn.com.p2p.ui.control;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.p2p.framework.util.StringUtils;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 格式化数字
 * 
 * @author 
 *
 */
public class StringFormat implements TemplateMethodModel {
	

	/**
	 * 格式化数字
	 * 例：
	 *    500000，3（没有小数）-> 500，000
	 *    500000，2（小数位不固定）-> 500，000.000
	 *    500000，1（小数位固定）-> 500，000.00
	 */
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		String result = "";
		if (null != arguments && 0 < arguments.size()) {
			String flag = arguments.get(0).toString();
			String srcString = arguments.get(1).toString();
			if(StringUtils.equals(flag, "bankCard")){
				result = formatBankCard(srcString);
			}
		}
		return result;
	}
	
	private String formatBankCard(String src){
		Pattern pattern = Pattern.compile("\\w{0,4}");
		Matcher matcher = pattern.matcher(src);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) { 
	        matcher.appendReplacement(sb, matcher.group() + " "); 
	    }
		return sb.toString();
	}
}
