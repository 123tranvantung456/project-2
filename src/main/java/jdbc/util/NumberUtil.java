package jdbc.util;

public class NumberUtil {
	public static boolean CheckNumber(String str) {
		try {
			Long.parseLong(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
