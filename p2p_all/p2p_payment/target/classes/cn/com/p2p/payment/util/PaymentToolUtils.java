package cn.com.p2p.payment.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PaymentToolUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * bigdeciaml数据乘以100返回long
	 * 
	 * @param value
	 * @return
	 */
	public static long convertBigDecimalM100(BigDecimal value){

		BigDecimal investment = value;
		investment = investment.multiply(new BigDecimal(100));

		return investment.longValue();
	}


	/**
	 * long数据除以100返回BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal convertBigDecimalD100(long value){

		BigDecimal investment = BigDecimal.valueOf(value);
		investment = investment.divide(new BigDecimal(100));

		return investment;
	}
	/**
	 * 取得指定时间固定格式的字符串形式
	 * 
	 * @param date
	 * @时间
	 * @param format
	 * @格式
	 * @return
	 */
	public static String formatDateTime(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 获取随机UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		String str = UUID.randomUUID().toString().replaceAll("-", "");
		return str;
	}

	/**
	 * 将指定字符串按固定格式转换为日期格式，当前兼容的格式如下：<br>
	 * 1、eg. 1978-12-21 14:21:25<br>
	 * 2、eg. 12/21/1978 14:21:35<br>
	 * 如果当前字符串格式违例，则返回null。
	 */

	public static Date parseDate(String strDate, String format) {
		try {
			if(strDate.indexOf("-")<0 && strDate.length()>7 && strDate.length()<14){
				strDate = strDate.substring(0, 4)+"-"+strDate.substring(4, 6)+"-"+strDate.substring(6, 8);
			}
			
			if(strDate.indexOf("-")<0 && strDate.length()>13 ){
				strDate = strDate.substring(0, 4)+"-"+strDate.substring(4, 6)+"-"+strDate.substring(6, 8)+" "+strDate.substring(8, 10)+":"+strDate.substring(10, 12)+":"+strDate.substring(12, 14);
			}
			SimpleDateFormat formator = new SimpleDateFormat(format);
			return formator.parse(strDate);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param string
	 *            设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}


}
