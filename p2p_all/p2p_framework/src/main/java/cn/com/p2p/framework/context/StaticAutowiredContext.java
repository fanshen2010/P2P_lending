package cn.com.p2p.framework.context;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import cn.com.p2p.framework.context.annotation.StaticAutowired;

/**
 * 扫描packagesToScan属性中的类路径表达式所匹配的类，
 * 并在Spring IoC中查找由@StaticAutowired注解所标识的类(static)变量并注入。
 * @author zhirong
 * 
 */
public class StaticAutowiredContext implements ApplicationContextAware {
	
	private static final String RESOURCE_PATTERN = "**/*.class";
	
	private static ApplicationContext applicationContext;
	
	private String[] packagesToScan;
	
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	/**
	 * 改类被IoC加载后立即回调此方法，完成扫描、注入。
	 */
	protected void init() {
		if (this.packagesToScan != null) {
			try {
				for (String pkg : this.packagesToScan) {
					String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
					Resource[] resources = this.resourcePatternResolver.getResources(pattern);
					MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
					for (Resource resource : resources) {
						if (resource.isReadable()) {
							MetadataReader reader = readerFactory.getMetadataReader(resource);
							String className = reader.getClassMetadata().getClassName();
							Class<?> c = this.resourcePatternResolver.getClassLoader().loadClass(className);
							Field[] fields = c.getDeclaredFields();
							for (Field f : fields) {
								if (f.getAnnotation(StaticAutowired.class) != null) {
									f.setAccessible(true);
									String typeName = f.getType().getSimpleName();
									String beanName = typeName.substring(0, 1).toLowerCase() + typeName.substring(1);
									f.set(null, applicationContext.getBean(beanName));
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
		
	}	
}
