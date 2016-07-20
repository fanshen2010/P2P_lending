package cn.com.p2p.framework.exception;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerTemplateExceptionHandler implements TemplateExceptionHandler  {
	/**
	 * 
	 */
	private final static Logger logger = LoggerFactory.getLogger(FreemarkerTemplateExceptionHandler.class); 

	public void handleTemplateException(TemplateException arg0,
			Environment arg1, Writer out) throws TemplateException {
		//这里构建你的错误机制，可以进行跳转及错误日志的打印等等
		logger.error("模板异常:" + arg0.getStackTrace());
	}
}
