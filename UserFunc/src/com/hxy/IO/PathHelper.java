package com.hxy.IO;

public class PathHelper {
	/**
	 * 获得项目路径，并去除Bin
	 * 
	 * @return
	 */
	public static String getProjectPathWithoutBin(Object object) {
		String originPath = object.getClass().getResource("/").getPath();// ��ȡԭʼ����Ŀ·��

		return originPath.substring(0, originPath.length() - 4); // ȥ��ԭʼ��Ŀ·�������/bin
	}
}
