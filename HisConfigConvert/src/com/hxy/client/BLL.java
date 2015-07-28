package com.hxy.client;

public class BLL {
	/**
	 * 转换nodeName为ETF命名
	 * 
	 * @param nodeName
	 * @return
	 */
	public static String convertToETFName(String nodeName) {
		String upperNodeName = nodeName.toUpperCase();// ת��Ϊ��д�ȴ��ж�

		StringBuffer buffer = new StringBuffer();// ƴ�ӡ�_��ʹ�õ���ʱ�洢

		// ���ȫΪ��д����ת��
		if (upperNodeName.equals(nodeName)) {
			return nodeName;
		}

		buffer.setLength(0);// ���buffer
		for (int i = 0; i < nodeName.length(); i++) {
			if (nodeName.charAt(i) >= 65 && nodeName.charAt(i) <= 90 && i != 0) {
				// ���i��Ϊ��д�Ҳ��ǵ�һ���ַ�
				if (nodeName.charAt(i - 1) >= 65
						&& nodeName.charAt(i - 1) <= 90) {
					// ���iǰһ���ַ�ҲΪ��д
					if (i <= nodeName.length() - 2) {
						// ���ں�һ���ַ�����index�ϼ�1��ʱ��Ҫ����Խ��ķ��գ�
						if (nodeName.charAt(i + 1) >= 97
								&& nodeName.charAt(i + 1) <= 122) {
							// ����һ���ַ�ΪСд
							buffer.append("_");
						}
					}
				} else {
					// ��֮������"_"
					buffer.append("_");
				}
			}

			buffer.append(nodeName.charAt(i));
		}

		return buffer.toString().toUpperCase();
	}

}
