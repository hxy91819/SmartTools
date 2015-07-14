package com.hxy.client;

public class BLL {
	/**
	 * 转化XML节点名称为ETF名称
	 * 
	 * 规则：
	 * 
	 * ·如果全为大写，则不转换
	 * 
	 * ·除第一个字母，遇到大写字母，在前面添加“_”
	 * 
	 * ·大写字母如果是连续的，若前一个不是大写，则添加。若前一个是大写，后一个是小写，则添加。若前后都为大写，则不添加
	 * 
	 * 版本：V1.0
	 * 
	 * @param nodeName
	 * @return
	 */
	public static String convertToETFName(String nodeName) {
		String upperNodeName = nodeName.toUpperCase();// 转换为大写等待判断

		StringBuffer buffer = new StringBuffer();// 拼接“_”使用的临时存储

		// 如果全为大写，则不转换
		if (upperNodeName.equals(nodeName)) {
			return nodeName;
		}

		buffer.setLength(0);// 清空buffer
		for (int i = 0; i < nodeName.length(); i++) {
			if (nodeName.charAt(i) >= 65 && nodeName.charAt(i) <= 90 && i != 0) {
				// 如果i处为大写且不是第一个字符
				if (nodeName.charAt(i - 1) >= 65
						&& nodeName.charAt(i - 1) <= 90) {
					// 如果i前一个字符也为大写
					if (i <= nodeName.length() - 2) {
						// 存在后一个字符（在index上加1的时候，要考虑越界的风险）
						if (nodeName.charAt(i + 1) >= 97
								&& nodeName.charAt(i + 1) <= 122) {
							// 但后一个字符为小写
							buffer.append("_");
						}
					}
				} else {
					// 反之，增加"_"
					buffer.append("_");
				}
			}

			buffer.append(nodeName.charAt(i));
		}

		// 将buffer转换为String，且将此String转换为大写返回
		return buffer.toString().toUpperCase();
	}

}
