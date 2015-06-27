package com.hxy.content;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHelper {
	/**
	 * ȥ��String����ո�
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
	 * ΪString��ĳһ���Ȳ���ĳ�ַ�
	 * 
	 * @param inputStr
	 *            ���
	 * @param strLength
	 *            Ŀ��String����
	 * @param subString
	 *            �����õ��ַ�
	 * @param leftStr
	 *            ����߲�0��
	 * @return
	 */
	public static String polishString(String inputStr, int strLength,
			String subString, boolean leftStr) {
		return oPolishString(inputStr, strLength, subString, leftStr);
	}

	/**
	 * Ĭ������
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
	 * ԭ�Ӳ��뺯��
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
					sb.append(subString).append(inputStr);// ��0
				} else {
					sb.append(inputStr).append(subString);// �Ҳ�0
				}
				inputStr = sb.toString();
				strLen = inputStr.length();
			}
		}
		return inputStr;
	}

	/**
	 * ��ȡString��MD5
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
