package com.hxy.content;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHelper {
	/**
	 * 去除String两侧空格
	 * @param string
	 * @return
	 */
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

	/**
	 * 为String按某一长度补齐某字符
	 * 
	 * @param inputStr
	 *            入参
	 * @param strLength
	 *            目标String长度
	 * @param subString
	 *            补齐用的字符
	 * @param leftStr
	 *            在左边补0？
	 * @return
	 */
	public static String polishString(String inputStr, int strLength,
			String subString, boolean leftStr) {
		return oPolishString(inputStr, strLength, subString, leftStr);
	}

	/**
	 * 默认左补齐
	 * 
	 * @param inputStr
	 * @param strLength
	 * @param subString
	 * @return
	 */
	public static String polishString(String inputStr, int strLength,
			String subString) {
		return oPolishString(inputStr, strLength, subString, true);
	}

	/**
	 * 原子补齐函数
	 * 
	 * @return
	 */
	private static String oPolishString(String inputStr, int strLength,
			String subString, boolean leftStr) {
		int strLen = inputStr.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				if (leftStr) {
					sb.append(subString).append(inputStr);// 左补0
				} else {
					sb.append(inputStr).append(subString);// 右补0
				}
				inputStr = sb.toString();
				strLen = inputStr.length();
			}
		}
		return inputStr;
	}

	/**
	 * 获取String的MD5
	 * @param str
	 * @return
	 */
    public static String getMD5(String str) {
        byte [] buf = str.getBytes();
        MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        md5.update(buf);
        byte [] tmp = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b:tmp) {
            sb.append(Integer.toHexString(b&0xff));
        }
        return sb.toString();
    }
}
