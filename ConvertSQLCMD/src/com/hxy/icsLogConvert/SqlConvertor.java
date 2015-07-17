package com.hxy.icsLogConvert;

import com.hxy.content.StringHelper;

/**
 * ����ת��ICS��־�е�SQLΪ��ִ��SQL
 * 
 * @author hxy
 *
 */
public class SqlConvertor {
	public static String getSQL(String forConvert) {
	    if(forConvert.equals("")){
	        return "--[Empty error]";
	    }

		String[] splitStrings = forConvert.split("]:");

		if(splitStrings.length < 2){
		    return "--[Format error]" + forConvert;
		}

		// �ָ�ĵ�һ��ΪFORMAT���ڶ���ΪPARAMS
		// �����һ�ε�[]
		String formatString = splitStrings[0].replace("[", "");
		formatString = formatString.replace("]", "");
		// �����һ�ε�?Ϊ%s
		formatString = formatString.replace("?", "'%s'");

		// ����ڶ���[]
		String paramsString = splitStrings[1].replace("[", "");
		paramsString = paramsString.replace("]", "");
		// �ڶ��θ���,�ָ�
		String[] paramsStrings = paramsString.split(",");

		Object[] paramsObject = new Object[paramsStrings.length];
		for (int i = 0; i < paramsStrings.length; i++) {
			// ���ָ�Ľ����������߿ո����ӵ�Object��
			paramsObject[i] = (Object) StringHelper.Trim(paramsStrings[i]);
		}

		// ���
		return String.format(formatString, paramsObject) + ";";
	}
}
