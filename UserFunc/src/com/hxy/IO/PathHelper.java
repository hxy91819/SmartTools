package com.hxy.IO;

public class PathHelper {
	/**
	 * 获取不包含"/bin"的项目路径
	 * 
	 * @return
	 */
	public static String getProjectPathWithoutBin(Object object) {
		String originPath = object.getClass().getResource("/").getPath();// 获取原始的项目路径

		return originPath.substring(0, originPath.length() - 4); // 去除原始项目路径后面的/bin
	}
}
