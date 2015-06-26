package com.hxy.content;

public class StringHelper {
	//È¥³ý×Ö·û´®Á½¶Ë¿Õ¸ñ
	public static String Trim(String string) {
		int start = 0;
		int end = string.length() - 1;
		while (start <= end && string.charAt(start) == ' ') {
			start++;
		}

		while (start <= end && string.charAt(end) == ' ') {
			end--;
		}
		return string.substring(start, end + 1);

	}
}
