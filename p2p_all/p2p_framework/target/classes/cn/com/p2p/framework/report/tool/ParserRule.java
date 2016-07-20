package cn.com.p2p.framework.report.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.com.p2p.framework.util.StringUtils;


/***
 * 解析规则，根据字符串规则（Excel中字符）生成FTL模板代码
 * @author kezhida
 *
 */
public class ParserRule {
	public static String COM_FIELD_REGX = "^[$#&].+$";
	public static String OUT_FIELD_REGX = "^[$]((?:\\w|\\.)+)$";
	public static String IN_FIELD_REGX = "^([#&]\\w)-\\{(.+)\\}$";//输入变量表达式
	public static String IN_FIELD_NAME_REG="^.+field[:：]((?:\\w|\\.)+).+$";//获取字符变量名的表达式
	public static String PARAM_REGX = "^(\\w+)[:：](.+)$";//ck:must|max.500 或field：clyj|v|1500
	//===============================================================
	public static String[] KEYS={
		"ctl_type",//控件类型
		"in_out"   //输入输出类型
	};
	//===============================================================
	/***
	 * 根据字段值验证是否是变量
	 * @param value
	 * @return
	 */
	public static boolean isVarField(String value){
		if(StringUtils.isEmpty(value)||value.startsWith("&p")||value.startsWith("&l")){
			return false;
		}else{
			return value.matches(COM_FIELD_REGX);
		}
	}
	
	/***
	 * 验证是否是数据库字段。
	 * @param value
	 * @return
	 */
	public static boolean isDBField(String value){
		boolean result = false;
		if(value!= null && !value.startsWith("&p")&& !value.startsWith("&l")){
			result= value.matches(IN_FIELD_REGX);//是输入字段
		}
		return result;
	}
	
	/**
	 * 解析字段属性
	 * @param value 字段设置
	 * @return
	 */
	public static Map<String,String> parsePropertis(String value){
		Map<String,String> map = new HashMap<String,String>();
		//字段属性
		String param_string = value.replaceAll(IN_FIELD_REGX, "$2");
		String[] params = param_string.split(",");
		for(String param:params){
			String pm = param.trim();
			String key = pm.replaceAll(PARAM_REGX, "$1");
			String val = pm.replaceAll(PARAM_REGX, "$2");
			//val = val.replaceAll("\\|", ",");
			map.put(key,val);
		}
		return map;
	}
	
	/**
	 * 获取变量字段的名字
	 * @param value
	 * @return
	 */
	public static String getFieldName(String value){
		String REGX_1 = OUT_FIELD_REGX;
		String result = "";
		if(StringUtils.isEmpty(value)){
			return result;
		}else if(value.matches(REGX_1)){
			result = value.replaceAll(REGX_1, "$1");
		}else if(value.matches(IN_FIELD_NAME_REG)){
			result = value.replaceAll(IN_FIELD_NAME_REG, "$1");
		}
		return result;
	}
	/**
	 * 根据字段的名字获取变量的默认值
	 * @param value
	 * @return
	 */
	public static String getDefaultValueByFieldName(String value){
		String default_value = null;
		if(isDBField(value)){
			Map<String,String> properties = parsePropertis(value);
			String dv = properties.get("dvalue");
			if(StringUtils.isNotEmpty(dv)){
				default_value = dv;
			}
		}
		return default_value;
	}	
	/**
	 * 将Excel中的数据类型转换成Ftl 片段(控件)
	 * @param value_ary
	 * @return
	 */
	public static List<Map<String,Object>> getFiledInfo(String value_ary){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String[] var_arry = value_ary.split(";");
		for(int i=0;i<var_arry.length;i++){
			list.add(getFiledParams(var_arry[i]));
		}
		return list;
	}
	
	/***
	 * 获取参数的设置数据
	 * @param value
	 * @return
	 */
	public static Map<String,Object> getFiledParams(String value){
		Map<String,Object> map = new ConcurrentHashMap<String, Object>();
			if(StringUtils.isEmpty(value))
				return map;
			if(value.matches(OUT_FIELD_REGX)){ //
				map.put(KEYS[1],FieldTypeEnum.OUT_FIELD);
				map.put(KEYS[0],"get");
				map.put("field", value.replaceAll(OUT_FIELD_REGX, "$1"));
			}else if(value.matches(IN_FIELD_REGX)){
				map.put(KEYS[1], FieldTypeEnum.IN_FIELD);//输入参数
				String cltType = value.replaceAll(IN_FIELD_REGX, "$1");
				map.put(KEYS[0], cltType);//控件类型
				//---------------------------------------------------
				//其他属性
				String param_string = value.replaceAll(IN_FIELD_REGX, "$2");
				String[] params = param_string.split(",");
				for(String param:params){
					String pm = param.trim();
					String key = pm.replaceAll(PARAM_REGX, "$1");
					String val = pm.replaceAll(PARAM_REGX, "$2");
					
					//-------------------------------------------------------
					if("dblength".equals(key)){
						String dblength = val;
						if(StringUtils.isNotEmpty(dblength)){
							dblength = dblength.replaceAll("_[0-9]+", "");
						}
						if(StringUtils.isNotEmpty(dblength)){
							int lg = Integer.parseInt(dblength);
							map.put("mlength", lg>50?String.valueOf((int)lg/4):String.valueOf(lg));
						}
					}
					//-------------------------------------------------------
					//val = val.replaceAll("\\|", ",");
					map.put(key,val);
				}
				//---------------------------------------------------
			}else{
				if(value.startsWith("T-")){
					map.put(KEYS[0], "title");//title控件
				}else{
					map.put(KEYS[0], "label");//label控件
				}
				map.put(KEYS[1], FieldTypeEnum.TEXT);//输入参数
				map.put("text", value.replaceAll("T-", ""));
				map.put("html_text", value.replaceAll("T-", "").replaceAll("\\r\\n|\\n", "<br/>"));
			}
		return map;
	}
	
	
	/***
	 * 数据类型
	 * 输入字段，输出字段,文本控件
	 * @author kezhida
	 *
	 */
	public enum FieldTypeEnum{
		OUT_FIELD,IN_FIELD,TEXT,TITLE;
	}
	
	/**
	 * 打印参数信息
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public static void printMap(Map<String, Object> map) {
		System.out.println("*******************************************************************");
		System.out.println("********************************输出参数内容***************************");
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Object valObj = entry.getValue();
				if(valObj instanceof List){
					List valList = (List)valObj;
					for(int i=0;i<valList.size();i++){
						System.out.printf("\t\t\tList[%s]=%s\t\t,\t\tValue= %s\r\n",
								i,entry.getKey(), valList.get(i));
					}
				}if(valObj instanceof Map){
					Map<String, Object> m = (Map<String, Object>) valObj;
					printMap(m);
				}else{
				System.out.printf("Key=%s\t\t,\t\tValue= %s\r\n",
						entry.getKey(), entry.getValue());
				}
			}
		} else {
			System.out.printf("Map is null!");
		}
		System.out.println("*******************************************************************");
	}	
}
