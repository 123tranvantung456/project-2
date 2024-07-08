package jdbc.util;

public class NumberUtil {
	public static boolean checkNumber(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
