package cn.com.p2p.framework.util;

import freemarker.core.Environment;
import freemarker.ext.beans.CollectionModel;
import freemarker.template.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.support.RequestContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

/**
 * Freemarker标签工具类 自定义freemarker标签工具的方法中用到
 * 
 * @author liufang
 * 
 */
public abstract class DirectiveUtils {
	/**
	 * 输出参数：对象数据
	 */
	public static final String OUT_BEAN = "tag_bean";
	/**
	 * 输出参数：列表数据
	 */
	public static final String OUT_LIST = "tag_list";
	/**
	 * 输出参数：分页数据
	 */
	public static final String OUT_PAGINATION = "tag_pagination";
	/**
	 * 参数：是否调用模板。
	 */
	public static final String PARAM_TPL = "tpl";
	/**
	 * 参数：次级模板名称
	 */
	public static final String PARAM_TPL_SUB = "tplSub";

	/**
	 * 将params的值复制到variable中
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws TemplateException
	 */
	public static Map<String, TemplateModel> addParamsToVariable(
			Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}

	/**
	 * 将variable中的params值移除
	 * 
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws TemplateException
	 */
	public static void removeParamsFromVariable(Environment env,
			Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	}

	/**
	 * 获得RequestContext
	 * 
	 * ViewResolver中的exposeSpringMacroHelpers必须为true
	 * 
	 * @param env
	 * @return
	 * @throws TemplateException
	 */
	public static RequestContext getContext(Environment env)
			throws TemplateException {
		TemplateModel ctx = env
				.getGlobalVariable(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		if (ctx instanceof AdapterTemplateModel) {
			return (RequestContext) ((AdapterTemplateModel) ctx)
					.getAdaptedObject(RequestContext.class);
		} else {
			throw new TemplateModelException("RequestContext '"
					+ SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE
					+ "' not found in DataModel.");
		}
	}
	
	public static String getString(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		}
		return null;
	}
	
	public static Boolean getBoolean(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateBooleanModel) {
			return ((TemplateBooleanModel) model).getAsBoolean();
		}
		return null;
	}
	public static List<Object> getList(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof CollectionModel) {
			List<Object> list = (List<Object>) ((CollectionModel) model).getWrappedObject();
			return list;
			
		} else if ((model instanceof TemplateNumberModel)) {
			
		}
		return null;
		
	}
	public static TemplateHashModel getMap(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateHashModel) {
			return ((TemplateHashModel) model);
			
		} else if ((model instanceof TemplateNumberModel)) {
			
		}
		return null;
	}
	
	public static Long getLong(String name, Map<String, TemplateModel> params)
			throws Exception {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s)) {
				return null;
			}
			try {
				return Long.parseLong(s);
			} catch (NumberFormatException e) {
				throw new Exception("the model type must be String!");
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().longValue();
		} else {
			throw new Exception("the model type must be String!");
		}
	}

	public static Integer getInt(String name, Map<String, TemplateModel> params)
			throws Exception {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s)) {
				return null;
			}
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new Exception("the model type must be String!");
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().intValue();
		} else {
			throw new Exception("the model type must be String!");
		}
	}
	
	public static BigDecimal getBigDecimal(String name, Map<String, TemplateModel> params)
			throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateNumberModel) {
			return new BigDecimal(((TemplateNumberModel) model).getAsNumber().toString());
		} else if (model instanceof TemplateScalarModel) {
			if(StringUtils.isNotEmpty(((TemplateScalarModel) model).getAsString())){
				return new BigDecimal(((TemplateScalarModel) model).getAsString());
			} else {
				return null;
			}
		} else {
			throw new TemplateException("the model type must be String!", null);
		}
	}

//	public static Integer[] getIntArray(String name,
//			Map<String, TemplateModel> params) throws TemplateException {
//		String str = DirectiveUtils.getString(name, params);
//		if (StringUtils.isBlank(str)) {
//			return null;
//		}
//		String[] arr = StringUtils.split(str, ',');
//		Integer[] ids = new Integer[arr.length];
//		int i = 0;
//		try {
//			for (String s : arr) {
//				ids[i++] = Integer.valueOf(s);
//			}
//			return ids;
//		} catch (NumberFormatException e) {
//			throw new MustSplitNumberException(name, e);
//		}
//	}

//	public static Boolean getBool(String name, Map<String, TemplateModel> params)
//			throws TemplateException {
//		TemplateModel model = params.get(name);
//		if (model == null) {
//			return null;
//		}
//		if (model instanceof TemplateBooleanModel) {
//			return ((TemplateBooleanModel) model).getAsBoolean();
//		} else if (model instanceof TemplateNumberModel) {
//			return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
//		} else if (model instanceof TemplateScalarModel) {
//			String s = ((TemplateScalarModel) model).getAsString();
//			// 空串应该返回null还是true呢？
//			if (!StringUtils.isBlank(s)) {
//				return !(s.equals("0") || s.equalsIgnoreCase("false") || s
//						.equalsIgnoreCase("f"));
//			} else {
//				return null;
//			}
//		} else {
//			throw new MustBooleanException(name);
//		}
//	}

	public static Date getDate(String name, Map<String, TemplateModel> params)
			throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateDateModel) {
			return ((TemplateDateModel) model).getAsDate();
		} else if (model instanceof TemplateScalarModel) {
			return DateUtils.parseDate(((TemplateScalarModel) model).getAsString());
		} else {
			throw new TemplateException(name + "the model type must be Date!", null);
		}
	}

	/**
	 * 模板调用类型
	 * 
	 * @author liufang
	 */
	public enum InvokeType {
		body, custom, sysDefined, userDefined
	};

	/**
	 * 是否调用模板
	 * 
	 * 0：不调用，使用标签的body；1：调用自定义模板；2：调用系统预定义模板；3：调用用户预定义模板。默认：0。
	 * 
	 * @param params
	 * @return
	 * @throws TemplateException
	 */
//	public static InvokeType getInvokeType(Map<String, TemplateModel> params)
//			throws TemplateException {
//		String tpl = getString(PARAM_TPL, params);
//		if ("3".equals(tpl)) {
//			return InvokeType.userDefined;
//		} else if ("2".equals(tpl)) {
//			return InvokeType.sysDefined;
//		} else if ("1".equals(tpl)) {
//			return InvokeType.custom;
//		} else {
//			return InvokeType.body;
//		}
//	}

	
}
