package cn.com.p2p.framework.web.common;

import java.util.List;
import java.util.Random;

import cn.com.p2p.framework.util.CryptUtils;
import cn.com.p2p.framework.web.security.EncodeURLInterface;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * URL编码
 * 
 * @author 
 *
 */
public class EncodeURLMethod implements TemplateMethodModel, EncodeURLInterface {

	private String encryptKey = "";

	private static Random random = new Random();

	/**
	 * 加密key
	 * 
	 * @param encryptKey
	 */
	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	/**
	 * 执行方法
	 * 
	 * @param argList
	 *            方法参数列表
	 * @return Object 方法返回值
	 * @throws TemplateModelException
	 */
	public Object exec(List argList) throws TemplateModelException {
		if (argList.size() != 1) // 限定方法中必须且只能传递一个参数
		{
			throw new TemplateModelException("Wrong arguments!");

		}

		// 返回response.encodeURL执行结果
		String url = (String) argList.get(0);
		return endodeUrlSub(url);

	}

	private String endodeUrlSub(String url) {
		if ("".equals(encryptKey)) {
			return url;
		}
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {

			return url.subSequence(0, firstQuestionMarkIndex) + "?"
					+ encodeValue(url.substring(firstQuestionMarkIndex + 1));

		} else {

			return url;
		}
	}

	private String encodeValue(String value) {
		try {
			String tmpStr = CryptUtils.byte2hex(CryptUtils.encryptString(value,
					encryptKey).getBytes("UTF-8"));

			String tmpPrefixStr = String.valueOf(random.nextLong())
					.replace("-", "").substring(0, 10);

			return tmpPrefixStr + tmpStr;
		} catch (Exception e) {
			return value;
		}
	}

	@Override
	public String encodeURLParam(String param) {

		try {
			return encodeValue(param);

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}

	@Override
	public String encodeURL(String param) {

		return endodeUrlSub(param);
	}
}
