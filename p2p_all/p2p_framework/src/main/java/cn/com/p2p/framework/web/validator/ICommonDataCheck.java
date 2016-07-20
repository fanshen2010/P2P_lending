package cn.com.p2p.framework.web.validator;

import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * 服务器端共通数据校验接口
 * */
public interface ICommonDataCheck {

	/**
	 * 数据校验接口方法
	 * @param dataAction  需要校验的数据（Action对象）
	 * @param checkType 校验类型，可以获取错误提示信息
	 * @param proName  字段属性名
	 * @param proValue	字段属性值
	 * @param params    校验的数据参数
	 * @param errMsgParam    错误提示信息参数
	 * @return          错误消息，格式（proName:错误消息)，没有错误返回null
	 */
	public String checkData(Object dataAction,ValidatorTypeEnum checkType,String proName,Object proValue,Object[] params,String[] errMsgParam);
}
