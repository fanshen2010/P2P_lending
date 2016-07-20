package cn.com.p2p.framework.report.tool;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {

	/**
	 * 设置Freemarker的 Configuration
	 * 
	 * @return Configuration
	 */
	private Configuration getConfig() {

		Configuration tempConfiguration = new Configuration();
		tempConfiguration.setClassicCompatible(true);
		// tempConfiguration.setClassForTemplateLoading(this.getClass(),
		// "/com/kzd/rg/template");
		tempConfiguration.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		tempConfiguration.setNumberFormat("");
		tempConfiguration.setDefaultEncoding("UTF-8");
		tempConfiguration.setObjectWrapper(new DefaultObjectWrapper());
		// Properties p = new Properties();
		//
		// try {
		// p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("freemarker.properties"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return tempConfiguration;
	}

	/**
	 * 调用Freemarker API实现代码生成
	 * 
	 * @param templatePath
	 *            String 文件的标准路径
	 * @param dataModel
	 *            Map 文件数据
	 * @param out
	 *            Writer 文件写入流
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static void process(File templateFile, Object dataModel, Writer out)
			throws TemplateException, IOException {

		FreeMarkerUtils fmu = new FreeMarkerUtils();

		Configuration configuration = fmu.getConfig();
		// 通过TemplateLoader的方式加载模版文件，如果不指定，即使文件存在freemarker也会抛出File not found异常
		configuration.setDirectoryForTemplateLoading(new File(templateFile
				.getParent()));
		// 指定模板如何查看数据模型
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		Template template = configuration.getTemplate(templateFile.getName());

		// 通过模版文件生成按逻辑实现的文件
		template.process(dataModel, out);

	}
}
