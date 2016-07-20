package cn.com.p2p.framework.web.validator.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.validator.ICommonDataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 自定义校验，一般是对前端的ajax校验补充，目前是对一个值得校验
 * 
 * @author 
 *
 */
@Service("customCheck")
public class CustomCheck implements ICommonDataCheck {

	@Override
	public String checkData(Object dataAction, ValidatorTypeEnum checkType,
			String proName, Object proValue, Object[] params,
			String[] errMsgParam) {
		String result = null;
		try {
			// 反射第一步：获取要操作类的Class对象
			// 获取Class对象的第二种方法、通过.class
			// Class<?> classType = dataAction.getClass();
			// 运行期创建对象
			// Object obj = classType.newInstance();// 因为泛型给定的是object
			// 因为是反射 不需要知道他具体的类型、直接获得这个obj的方法（虽然我已知这是ReflectionDemo02类的）
			Method method = dataAction.getClass().getMethod(
					String.valueOf(params[0]), Object[].class); // 此时获得了验证方法
			result = (String) method
					.invoke(dataAction, new Object[] { params }); // 自动装箱
			// 此时result真正类型为Integer、虽然真正的方法声明是int、
			// 但是对于基本数据类型、永远返回的是其包装类
			if (StringUtils.isEmpty((String) result)) {
				result = null;
			} else {
				result = proName
						+ ":"
						+ String.format(checkType.getErrorMessage(),
								errMsgParam) + result;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return result;
	}

}
