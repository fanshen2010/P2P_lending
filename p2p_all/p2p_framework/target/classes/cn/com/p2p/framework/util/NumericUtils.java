package cn.com.p2p.framework.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 此类封装了精确数值计算及数值格式化处理的功能，在JAVA普通数值计算中，会出现以下问题：<br>
 * 0.05 + 0.01 = 0.060000000000000005; <br>
 * 1.0 - 0.42 = 0.5800000000000001;<br>
 * 4.015 * 100 = 401.49999999999994;<br>
 * 等问题，所以如果在商业运用中，我们需要BigDecimal进行精确计算，在此类中我们封装了数值计算的常用方法，<br>
 * 以及一些常用的和货币有关的操作。
 */
public class NumericUtils implements Serializable {

	private static final long serialVersionUID = -6923439365106695985L;
	// 除法运算默认精度
	public static final int DEF_DIV_SCALE = 10;

	/**
	 * 精确除法计算
	 */
	public static double divide(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 精确除法计算
	 */
	public static double divide(BigDecimal v1, BigDecimal v2) {
		return v1.divide(v2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 精确乘法计算
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 精确乘法计算
	 */
	public static double multiply(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2).doubleValue();
	}

	/**
	 * 精确减法计算
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 精确减法计算
	 */
	public static double subtract(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2).doubleValue();
	}

	/**
	 * 精确加法计算
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(String.valueOf(v1));
		BigDecimal b2 = new BigDecimal(String.valueOf(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 精确加法计算
	 */
	public static double add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2).doubleValue();
	}
	
	/**
	 * <p>精确加法计算 参数可以为null</p>.<br>
	 */
	public static BigDecimal addCalculation(BigDecimal bd1, BigDecimal bd2) {
	    if (bd1 == null ) {
	        bd1 = BigDecimal.ZERO;
	    }
	    if (bd2 == null ) {
            bd2 = BigDecimal.ZERO;
        }
	    return bd1.add(bd2);
	}
	
	
	/**
     * 精确乘法计算 参数可以为null
     */
    public static double multiplyCalculation(BigDecimal bd1, BigDecimal bd2) {
        if (bd1 == null ) {
            bd1 = BigDecimal.ONE;
        }
        if (bd2 == null ) {
            bd2 = BigDecimal.ONE;
        }
        return bd1.multiply(bd2).doubleValue();
    }

	/**
	 * 四舍五入运算，scale为小数点位数，默认为DEF_DIV_SCALE
	 */
	public static double round(double v, int scale) {
		int fs = DEF_DIV_SCALE;
		if (scale > 0) {
			fs = scale;
		}
		BigDecimal b = new BigDecimal(String.valueOf(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, fs, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四舍五入运算，scale为小数点位数，默认为DEF_DIV_SCALE
	 */
	public static double round(BigDecimal b, int scale) {
		int fs = DEF_DIV_SCALE;
		if (scale > 0) {
			fs = scale;
		}
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, fs, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 格式化货币显示，货币显示默认会带个前缀，如果英文操作系统，则会显示类似
	 * [$2,454,534.23]，而中文则显示[￥2,454,534.23]，<br>
	 * 此类中用以下规则来处理相应处理：<br>
	 * 第一，可以选择地域，即有Locale这个参数对象;<br>
	 * 第二，可以选择是否加前缀;<br>
	 * 第三，如果选择不加前缀，则自动将Locale高为US;<br>
	 * 第四，如果Locale为空，则默认为中国人民币;<br>
	 * (注：Locale.TRADITIONAL_CHINESE或者Locale.TAIWAN应该为台湾，而不要想当然认为Locale.
	 * CHINESE为台湾)
	 */
	public static String moneyFormat(Number v, Locale area, boolean hasPrefix) {
		Locale local = area == null ? Locale.US : area;
		if (!hasPrefix) {
			local = Locale.US;
		}
		NumberFormat formator = NumberFormat.getCurrencyInstance(local);
		DecimalFormat formatorNoPrefix = new DecimalFormat("#,##0.00");
		String money = formator.format(v);
		String tag = money.substring(0, 1);
		String r = formatorNoPrefix.format(v);
		if (hasPrefix) {
			r = tag + r;
		}
		return r;
	}

	public static String moneyFormat(Number v, boolean hasPrefix) {
		return moneyFormat(v, null, hasPrefix);
	}

	public static String moneyFormat(Number v) {
		return moneyFormat(v, null, false);
	}

	public static String moneyFormat(double v, Locale area) {
		return moneyFormat(v, area, true);
	}

	public static BigDecimal toBigDecimal(double obj) {
		return BigDecimal.valueOf(obj);
	}

	public static String toFormatString(BigDecimal obj) {
		DecimalFormat formatorNoPrefix = new DecimalFormat("0.00");
		return formatorNoPrefix.format(obj);
	}
	public static Integer StringToInteger(String str) {
	    Integer formatRestlt=0;
	    try{
	     formatRestlt=Integer.parseInt(str);
	    }catch(Exception e){
	        e.printStackTrace();
	        formatRestlt=0;
	    }
	    
	    return formatRestlt;
	}

	
	public static double StringToDouble(String str){
	    double result=0;
	    try{
	    result=Double.parseDouble(str);
	    }catch(Exception e){
	        e.printStackTrace();
	        result=0;
	    }
	    return result;
	}
	
	public static BigDecimal StringToBigdecimal(String str){
	    BigDecimal result=BigDecimal.ZERO;
	    try{
	        result=new BigDecimal(str);
	        }catch(Exception e){
	            e.printStackTrace();
	            result=BigDecimal.ZERO;
	        }
	        return result;
	}
	
	/**
	 * <p>空的时候0返回</p>.<br>
	 * author：<br>
	 *===================================
	 * @param obj
	 * @return
	 */
	public static BigDecimal emptyToZero(BigDecimal obj){
	   BigDecimal result = BigDecimal.ZERO;
	   if (obj != null ) {
	       result = obj;
	   }
	   return result;
	}
}
