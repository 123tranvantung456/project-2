package jdbc.util;

public class StringUtil {
	public static boolean checkString(String str) {
		if(str != null && !str.equals("")) {
			return true;
		}
		return false;
	}
}
