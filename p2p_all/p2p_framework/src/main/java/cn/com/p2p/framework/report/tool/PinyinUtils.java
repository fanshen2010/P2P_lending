package cn.com.p2p.framework.report.tool;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

	public static String getPinYinStr(String cnstr) {

		StringBuilder sb = new StringBuilder();
		HanyuPinyinOutputFormat pin_format = new HanyuPinyinOutputFormat();
		//返回的字符串中去除音调标记
		pin_format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//发音“驴（lu->lv）”的那个韵母用v表示
		pin_format.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int i = 0; i < cnstr.length(); i++) {
			char c = cnstr.charAt(i);
			if (c <= 255) {
				sb.append(c);
			} else {
				String pinyin = null;
				try {
					String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, pin_format);
					pinyin = pinyinArray[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.getMessage();
				} catch (NullPointerException e) {
					// 如果是日文，可能抛出该异常
				}
				if (pinyin != null) {
					sb.append(pinyin);
				}
			}
		}
		//System.out.println(sb.toString()); 
		return sb.toString();
	}

	/**
	 * 
	 * @param chinese
	 * @return
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}
	
	public static void main(String[] args) {
		System.out.println(PinyinUtils.getFirstSpell("(你  妹)"));
	}
}
