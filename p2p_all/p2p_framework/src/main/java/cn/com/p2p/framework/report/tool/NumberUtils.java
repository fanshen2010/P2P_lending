package cn.com.p2p.framework.report.tool;



public class NumberUtils {
	/**
	 * 磅转像素
	 * @param pt
	 * @return
	 */
	public static int getPxFromPt(float pt) {
		return (int) (pt * 1f/72f * 96f);
	}
	
	public static void main(String[] args) {
		System.out.println(NumberUtils.getPxFromPt(25.5f));
	}
	
	public static int toInt(Object o) {
		if (o == null) return 0;
		
		return Integer.parseInt((String)o);
	}

}
