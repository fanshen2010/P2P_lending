package cn.com.p2p.framework.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 提供了对List&lt;Object&gt;进行排序的功能 对外开放接口为SortTools.sortList
 * 可以以返回类型为int，long，double，date类型的方法进行排序
 * 
 * @author pub
 */
public class SortTools implements Serializable {

	private static final long serialVersionUID = -4930661127450243027L;

	/**
	 * @param objectList
	 *            -->需排序的对象列表
	 * @param methodName
	 *            -->列表中用于排序的对象方法名
	 */
	public static void sortList(List<?> objectList, String methodName) {
		if (objectList == null || objectList.size() < 2) {
			return;
		}
		SortTools tools = new SortTools();
		tools.doSort(objectList, methodName);
	}

	private String returnTypeInt = "int";
	private String returnTypeInteger = "Integer";
	private String returnTypeLong = "long";
	private String returnTypeLongObj = "Long";
	private String returnTypeDouble = "double";
	private String returnTypeDoubleObj = "Double";
	private String returnTypeDate = "Date";

	@SuppressWarnings("unchecked")
	private void doSort(List objectList, String methodName) {
		try {
			Object obj = objectList.get(0);
			Class cls = obj.getClass();
			Method method = cls.getMethod(methodName);
			if (method.getReturnType().getName()
					.equalsIgnoreCase(returnTypeInt)
					|| method.getReturnType().getName()
							.equalsIgnoreCase(returnTypeInteger)) {
				Collections.sort(objectList, new IntegerSort(methodName));
			}

			if (method.getReturnType().getName()
					.equalsIgnoreCase(returnTypeLong)
					|| method.getReturnType().getName()
							.equalsIgnoreCase(returnTypeLongObj)) {
				Collections.sort(objectList, new LongSort(methodName));
			}

			if (method.getReturnType().getName()
					.equalsIgnoreCase(returnTypeDouble)
					|| method.getReturnType().getName()
							.equalsIgnoreCase(returnTypeDoubleObj)) {
				Collections.sort(objectList, new DoubleSort(methodName));
			}

			if (method.getReturnType().getName()
					.equalsIgnoreCase(returnTypeDate)) {
				Collections.sort(objectList, new DateSort(methodName));
			}
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	private class LongSort implements Comparator {
		private String methodName = "getId";

		@SuppressWarnings("unused")
		public LongSort() {
		}

		public LongSort(String methodName) {
			this.methodName = methodName;
		}

		public int compare(Object obj1, Object obj2) {
			if (obj1 == null || obj2 == null) {
				return 0;
			}
			Class c1 = obj1.getClass();
			Class c2 = obj2.getClass();
			if (!c1.getName().equals(c2.getName())) {
				return 0;
			}

			try {
				Method m1 = c1.getMethod(methodName);
				Method m2 = c2.getMethod(methodName);

				Long l1 = (Long) m1.invoke(methodName);
				Long l2 = (Long) m2.invoke(methodName);

				int result = -1;
				if (l2 == l1) {
					result = 0;
				}
				if (l2 > l1) {
					result = 1;
				}
				return result;
			} catch (Exception e) {
				return 0;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private class IntegerSort implements Comparator {
		private String methodName = "getId";

		@SuppressWarnings("unused")
		public IntegerSort() {
		}

		public IntegerSort(String methodName) {
			this.methodName = methodName;
		}

		public int compare(Object obj1, Object obj2) {
			if (obj1 == null || obj2 == null) {
				return 0;
			}
			Class c1 = obj1.getClass();
			Class c2 = obj2.getClass();
			if (!c1.getName().equals(c2.getName())) {
				return 0;
			}

			try {
				Method m1 = c1.getMethod(methodName);
				Method m2 = c2.getMethod(methodName);

				Integer i1 = (Integer) m1.invoke(methodName);
				Integer i2 = (Integer) m2.invoke(methodName);

				int result = -1;
				if (i2 == i1) {
					result = 0;
				}
				if (i2 > i1) {
					result = 1;
				}
				return result;
			} catch (Exception e) {
				return 0;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private class DoubleSort implements Comparator {
		private String methodName = "";

		@SuppressWarnings("unused")
		public DoubleSort() {
		}

		public DoubleSort(String methodName) {
			this.methodName = methodName;
		}

		public int compare(Object obj1, Object obj2) {
			if (obj1 == null || obj2 == null) {
				return 0;
			}
			Class c1 = obj1.getClass();
			Class c2 = obj2.getClass();
			if (!c1.getName().equals(c2.getName()))
				return 0;
			if (methodName == null || methodName.equals(""))
				return 0;

			try {
				Method m1 = c1.getMethod(methodName);
				Method m2 = c2.getMethod(methodName);

				Double d1 = (Double) m1.invoke(methodName);
				Double d2 = (Double) m2.invoke(methodName);

				int result = -1;
				if (d2 == d1) {
					result = 0;
				}
				if (d2 > d1) {
					result = 1;
				}
				return result;
			} catch (Exception e) {
				return 0;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private class DateSort implements Comparator {
		private String methodName = "getLastUpdateDate";

		@SuppressWarnings("unused")
		public DateSort() {
		}

		public DateSort(String methodName) {
			this.methodName = methodName;
		}

		public int compare(Object obj1, Object obj2) {
			if (obj1 == null || obj2 == null) {
				return 0;
			}
			Class c1 = obj1.getClass();
			Class c2 = obj2.getClass();
			if (!c1.getName().equals(c2.getName())) {
				return 0;
			}

			try {
				Method m1 = c1.getMethod(methodName);
				Method m2 = c2.getMethod(methodName);

				Date d1 = (Date) m1.invoke(methodName);
				Date d2 = (Date) m2.invoke(methodName);

				int result = -1;
				if (d2 == d1) {
					result = 0;
				}
				if (d2.before(d1)) {
					result = 1;
				}
				return result;
			} catch (Exception e) {
				return 0;
			}
		}
	}
}
