package cn.com.p2p.framework.web.ui;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.framework.code.BizQueryList;
import cn.com.p2p.framework.code.CodeList;
import cn.com.p2p.framework.code.EnumCodeList;
import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.framework.util.SpringContextUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
/**
 * <#list cart?keys as itemKey>  
 * <#assign item = cart[itemKey]>
 * @author 
 *
 */
public class UiListControl implements TemplateDirectiveModel {
	/**
	 * 模板路径
	 */
	public String tplPath = "";
	
	public static final String ENUM_PACKAGE = "cn.com.p2p.framework.enumpack";
	
	public static final String DATA_QUERY_PACKAGE = "cn.com.p2p.ui.service";

	@Autowired
	private CodeList bizCodeList;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取控件id
		String id = DirectiveUtils.getString("id", params);
		
		// 获取控件name
		String name = DirectiveUtils.getString("name", params);
		
		// 获取控件选中值
		String value = DirectiveUtils.getString("value", params);
		
		// 获取数据类型
		String dataType = DirectiveUtils.getString("dataType", params);
		
		// 获取是否显示【全部】选项
		Boolean hasAll = DirectiveUtils.getBoolean("hasAll", params);
		
		// 获取是否显示【请选择】选项
		Boolean hasChoice = DirectiveUtils.getBoolean("hasChoice", params);
		
		// 获取【请选择】文本
		String textChoice = DirectiveUtils.getString("textChoice", params);
		
		// 获取是否显示【无】选项
		Boolean hasNone = DirectiveUtils.getBoolean("hasNone", params);
		
		// 获取数据分组code
		String groupCode = DirectiveUtils.getString("groupCode", params);
		
		// 获取data属性内容
		String data = DirectiveUtils.getString("data", params);
		
		// 获取class属性内容
		String classAttr = DirectiveUtils.getString("class", params);
				
		// 获取外层span的class属性
		String spanClass = DirectiveUtils.getString("spanClass", params);
		
		// 获取外层label的class属性
		String labelClass = DirectiveUtils.getString("labelClass", params);
		
		// 获取数据
		TemplateHashModel listData = DirectiveUtils.getMap("listData", params);
		
		// 获取common路径
		String commonResultPath = env.getVariable("commonResultPath").toString();
		
		
		Map<String, String> rstList = new HashMap<String, String>();
		
		if("BizCode".equals(dataType)){
			// 数据来源是BizCode，从xml中获取code list
			rstList = bizCodeList.getMap(groupCode);
		} else if("Enum".equals(dataType)){
			// 数据来源是枚举，从枚举包中获取
			try {
				EnumCodeList enumCodeList= new EnumCodeList((Class<? extends Enum<?>>)Class.forName(ENUM_PACKAGE + "." + groupCode));
				rstList = enumCodeList.toMap();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (DropdownDatasourceType.getType(dataType) == DropdownDatasourceType.MARKET_PATH) {
			try {
				EnumCodeList enumCodeList= new EnumCodeList((Class<? extends Enum<?>>)Class.forName(DropdownDatasourceType.getType(dataType).getValue() + "." + groupCode));
				rstList = enumCodeList.toMap();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("BizQuery".equals(dataType)){
			// 数据来源是业务查询，从业务查询中获取
			BizQueryList bizQueryList = new BizQueryList(SpringContextUtils.getBean(groupCode));
			rstList = bizQueryList.toMap();
			
		}
		
		env.setVariable("id", DEFAULT_WRAPPER.wrap(id));
		env.setVariable("name", DEFAULT_WRAPPER.wrap(name));
		env.setVariable("value", DEFAULT_WRAPPER.wrap(value));
		env.setVariable("hasAll", DEFAULT_WRAPPER.wrap(hasAll));
		env.setVariable("textChoice", DEFAULT_WRAPPER.wrap(textChoice));
		env.setVariable("hasChoice", DEFAULT_WRAPPER.wrap(hasChoice));
		env.setVariable("hasNone", DEFAULT_WRAPPER.wrap(hasNone));
		env.setVariable("rstlist", DEFAULT_WRAPPER.wrap(rstList));
		env.setVariable("data", DEFAULT_WRAPPER.wrap(data));
		env.setVariable("class", DEFAULT_WRAPPER.wrap(classAttr));
		env.setVariable("labelClass", DEFAULT_WRAPPER.wrap(labelClass));
		env.setVariable("spanClass", DEFAULT_WRAPPER.wrap(spanClass));
		env.setVariable("listData", DEFAULT_WRAPPER.wrap(listData));

		env.include("/" + commonResultPath + tplPath, "UTF-8", true);

	}

}
