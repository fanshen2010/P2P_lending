package cn.com.p2p.framework.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

/**
 * 国际化资源处理工具类，切换语言，读取资源文件。
 * 
 * @author pub
 * 
 */
@SuppressWarnings( { "unchecked" })
public class LangUtils {
	
	private static final String Error_Message = "The error locale";
	private static final String Not_Found_Message = "Not found the key : ";
	public static final String DEFAULT_CONFIG_LOCATION_PREFIX = "WEB-INF/classes"; // 资源文件的默认根目录

	// --语种--//
	static public final String ZHS = "zh_CN";
	static public final String US = "en_US";
	static public final String ZH_CN = "zh_CN";
	static public final String ZH_TW = "zh_TW";
	static public final String FR_FR = "fr_FR";
	static public final String DE_DE = "de_DE";
	static public final String IT_IT = "it_IT";
	static public final String JA_JP = "ja_JP";
	static public final String KO_KR = "ko_KR";
	static public final String EN_GB = "en_GB";
	static public final String EN_CA = "en_CA";
	static public final String FR_CA = "fr_CA";

	private static final String SESSION_LANG = "WW_TRANS_I18N_LOCALE";// SESSION中“当前语种”的关键字。

	private static final List<String> DEFAULT_RESOURCE_BUNDLES = new CopyOnWriteArrayList<String>();
	private static final ConcurrentMap<String, ResourceBundle> bundlesMap = new ConcurrentHashMap<String, ResourceBundle>();
	private static final ResourceBundle EMPTY_BUNDLE = new EmptyResourceBundle();
	private static final Map<MessageFormatKey, MessageFormat> messageFormats = new HashMap<MessageFormatKey, MessageFormat>();

	/**
	 * 读取所有的properties资源文件名（包括子目录），放入DEFAULT_RESOURCE_BUNDLES。
	 */
	static {
		String resourcePath = getResourcePath();
		File directory = new File(resourcePath);
		Iterator<File> it = FileUtils.iterateFiles(directory, new String[] { "properties" }, true);
		Map<String, String> preName = new HashMap<String, String>();
		while (it.hasNext()) {
			File f = it.next();
			if (f.getName().length() - StringUtils.replace(f.getName(), "_", "").length() == 2) {// 有2个下划线。
				String absolutePath = f.getAbsolutePath().substring(resourcePath.length() + 1);
				String fileName = StringUtils.replace(absolutePath, "\\", "/");
				fileName = fileName.substring(0, fileName.length() - 17);
				preName.put(fileName, fileName);
			}
		}
		if (preName.size() > 0) {
			Iterator<String> names = preName.keySet().iterator();
			while (names.hasNext()) {
				DEFAULT_RESOURCE_BUNDLES.add(names.next());
			}
		}
	}

	private LangUtils() {
	}

	/**
	 * 设置当前所选择语言放入session
	 * 
	 * @param lang
	 *            ISO语言代码_ISO国家代码：ZHS、US、zh_CN、en_US、zh_CN、zh_TW、fr_FR、de_DE、it_IT
	 *            、ja_JP、ko_KR 、en_GB、en_CA、fr_CA
	 * 
	 */
	public static void setLanguage(String lang) {
		Locale locale = null;
		try {
			locale = getLocale(lang);
		} catch (Exception e) {
			locale = Locale.getDefault();
		}
		ServletActionContext.getRequest().getSession().setAttribute(SESSION_LANG, locale);
	}

	/**
	 * 从session获得当前选择的语言
	 * 
	 * @return 当前选择的语言
	 */
	public static String getLanguage() {
		Locale locale = (Locale) ServletActionContext.getRequest().getSession().getAttribute(SESSION_LANG);
		if (locale == null) {
			try {
				locale = Struts2Utils.getRequest().getLocale();
			} catch (Exception e) {
				locale = Locale.getDefault();
			}
		}
		ServletActionContext.getRequest().getSession().setAttribute(SESSION_LANG, locale);

		return locale.getLanguage() + "_" + locale.getCountry();
	}

	/**
	 * 根据传入的关键字，返回指定语种（该语种是已经设置过的）的本地化的文本。
	 * 
	 * @param key
	 * @return 国际化文本
	 */
	public static String getMessage(String key) {
		String lang = getLanguage();
		Locale locale = null;
		try {
			locale = getLocale(lang);
		} catch (Exception e) {
			locale = Locale.getDefault();
		}
		return getMessage(key, locale).trim();
	}

	/**
	 * 根据传入的关键字和参数，返回指定语种（该语种是已经设置过的）的本地化的文本。
	 * 
	 * @param key
	 *            key值
	 * @param params
	 *            参数
	 * @return 国际化文本
	 */
	public static String getMessage(String key, Object[] params) {
		String lang = getLanguage();
		Locale locale = null;
		try {
			locale = getLocale(lang);
		} catch (Exception e) {
			locale = Locale.getDefault();
		}
		return getMessage(key, locale, params).trim();
	}

	/**
	 * 根据传入的关键字、语种，返回该语种的本地化的文本。
	 * 
	 * @param key
	 *            资源文件的key
	 * @param locale
	 * @return 国际化文本
	 */
	public static String getMessage(String key, Locale locale) {
		List<String> localList = DEFAULT_RESOURCE_BUNDLES;
		for (String bundleName : localList) {
			ResourceBundle bundle = findResourceBundle(bundleName, locale);
			if (bundle != null) {
				reloadBundles();
				try {
					return bundle.getString(key);
				} catch (MissingResourceException e) {
					// ignore and try others
				}
			}
		}
		return Not_Found_Message + key;
	}

	/**
	 * 根据传入的关键字、语种和参数，返回该语种的本地化的文本。
	 * 
	 * @param key
	 *            key值
	 * @param locale
	 *            指定的locale
	 * @param params
	 *            参数
	 * @return 国际化文本
	 */
	public static String getMessage(String key, Locale locale, Object[] params) {
		String defaultText = getMessage(key, locale);
		if (defaultText != null) {
			MessageFormat mf = buildMessageFormat(defaultText, locale);
			return mf.format(params);
		}
		return null;
	}
	
	/**
	 * 判断传人的key是否存在
	 * @param key
	 * @param locale
	 * @return
	 */
	public static boolean hasMessage(String key, Locale locale) {
		List<String> localList = DEFAULT_RESOURCE_BUNDLES;
		for (String bundleName : localList) {
			ResourceBundle bundle = findResourceBundle(bundleName, locale);
			if (bundle != null) {
				reloadBundles();
				if (bundle.containsKey(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断传人的key是否存在
	 * @param key
	 * @return
	 */
	public static boolean hasMessage(String key) {
		String lang = getLanguage();
		Locale locale = null;
		try {
			locale = getLocale(lang);
		} catch (Exception e) {
			locale = Locale.getDefault();
		}
		return hasMessage(key, locale);
	}

	/**
	 * 将参数加入到文本中。
	 * 
	 * @param pattern
	 * @param locale
	 * @return
	 */
	private static MessageFormat buildMessageFormat(String pattern, Locale locale) {
		MessageFormatKey key = new MessageFormatKey(pattern, locale);
		MessageFormat format = null;
		synchronized (messageFormats) {
			format = (MessageFormat) messageFormats.get(key);
			if (format == null) {
				format = new MessageFormat(pattern);
				format.setLocale(locale);
				format.applyPattern(pattern);
				messageFormats.put(key, format);
			}
		}

		return format;
	}

	private static void reloadBundles() {
		try {
			clearMap(ResourceBundle.class, null, "cacheList");
			clearTomcatCache();
		} catch (Exception e) {
		}
	}

	private static void clearMap(Class cl, Object obj, String name)
			throws NoSuchFieldException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		Field field = cl.getDeclaredField(name);
		field.setAccessible(true);
		Object cache = field.get(obj);
		synchronized (cache) {
			Class ccl = cache.getClass();
			Method clearMethod = ccl.getMethod("clear");
			clearMethod.invoke(cache);
		}

	}

	private static void clearTomcatCache() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class cl = loader.getClass();
		try {
			if ("org.apache.catalina.loader.WebappClassLoader".equals(cl.getName())) {
				clearMap(cl, loader, "resourceEntries");
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 根据指定的语言国家编码，获得Locale 对象
	 * 
	 * @param lang
	 *            ISO语言代码_ISO国家代码。
	 * @return
	 * @throws Exception
	 */
	public static Locale getLocale(String lang) throws Exception {
		Locale locale = Locale.getDefault();
		if (lang != null && !lang.equals("")) {
			try {
				if (lang.length() - StringUtils.replace(lang, "_", "").length() == 1) {
					String language = lang.split("_")[0];
					String country = lang.split("_")[1];
					locale = new Locale(language, country, "");
				} else if (lang.equals("ZHS")) {
					locale = new Locale("zh", "CN", "");
				} else if (lang.equals("US")) {
					locale = new Locale("en", "US", "");
				}
			} catch (Exception e) {
				throw new Exception(Error_Message);
			}
		}
		return locale;
	}

	/**
	 * Finds the given resorce bundle by it's name.
	 * <p/>
	 * Will use <code>Thread.currentThread().getContextClassLoader()</code> as
	 * the classloader. If {@link #delegatedClassLoader} is defined and the
	 * bundle cannot be found the current classloader it will delegate to that.
	 * 
	 * @param aBundleName
	 *            the name of the bundle (usually it's FQN classname).
	 * @param locale
	 *            the locale.
	 * @return the bundle, <tt>null</tt> if not found.
	 */
	private static ResourceBundle findResourceBundle(String aBundleName, Locale locale) {
		String key = createMissesKey(aBundleName, locale);

		ResourceBundle bundle;

		try {
			if (!bundlesMap.containsKey(key)) {
				bundle = ResourceBundle.getBundle(aBundleName, locale, Thread.currentThread().getContextClassLoader());
				bundlesMap.put(key, bundle);
			}

			bundle = bundlesMap.get(key);
		} catch (MissingResourceException ex) {
			bundle = EMPTY_BUNDLE;
		}
		return (bundle == EMPTY_BUNDLE) ? null : bundle;
	}

	/**
	 * Creates a key to used for lookup/storing in the bundle misses cache.
	 * 
	 * @param aBundleName
	 *            the name of the bundle (usually it's FQN classname).
	 * @param locale
	 *            the locale.
	 * @return the key to use for lookup/storing in the bundle misses cache.
	 */
	private static String createMissesKey(String aBundleName, Locale locale) {
		return aBundleName + "_" + locale.toString();
	}

	private static String getResourcePath() {
		String str = ServletActionContext.getServletContext().getRealPath("/") + DEFAULT_CONFIG_LOCATION_PREFIX;
		str = StringUtils.replace(str, "\\", "/");
		return str;
	}

	private static class EmptyResourceBundle extends ResourceBundle {
		@Override
		public Enumeration<String> getKeys() {
			return null;
		}

		@Override
		protected Object handleGetObject(String key) {
			return null;
		}
	}

	static class MessageFormatKey {
		String pattern;
		Locale locale;

		MessageFormatKey(String pattern, Locale locale) {
			this.pattern = pattern;
			this.locale = locale;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof MessageFormatKey)) {
				return false;
			}

			final MessageFormatKey messageFormatKey = (MessageFormatKey) o;

			if (locale != null ? !locale.equals(messageFormatKey.locale) : messageFormatKey.locale != null) {
				return false;
			}
			if (pattern != null ? !pattern.equals(messageFormatKey.pattern) : messageFormatKey.pattern != null) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			int result;
			result = (pattern != null ? pattern.hashCode() : 0);
			result = 29 * result + (locale != null ? locale.hashCode() : 0);
			return result;
		}
	}

}
