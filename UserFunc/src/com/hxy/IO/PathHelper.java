package com.hxy.IO;

public class PathHelper {
	/**
	 * ��ȡ������"/bin"����Ŀ·��
	 * 
	 * @return
	 */
	public static String getProjectPathWithoutBin(Object object) {
		String originPath = object.getClass().getResource("/").getPath();// ��ȡԭʼ����Ŀ·��

		return originPath.substring(0, originPath.length() - 4); // ȥ��ԭʼ��Ŀ·�������/bin
	}
}
