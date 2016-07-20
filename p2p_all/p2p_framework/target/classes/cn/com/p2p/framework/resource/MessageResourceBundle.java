package cn.com.p2p.framework.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageResourceBundle {

    /**
     * 
     * @param msgId
     * @param params
     * @return message内容
     */
	public static String getMessage(String msgId, String ... params) {
		//获得本地属性  
		Locale local =Locale.getDefault();  
		//根据本地国家语言来获得classes路径下baseName为message的资源文件  
		ResourceBundle rb = ResourceBundle.getBundle("message",local);  
		Object[] msgparams = params;
		
		//可以传入参数，如果资源文件这样定义的话:msgId={0}用户名
		String message = MessageFormat.format(rb.getString(msgId), msgparams);
		
		return message;
	}
}
