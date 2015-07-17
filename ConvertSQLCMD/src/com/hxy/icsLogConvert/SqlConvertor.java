package com.hxy.icsLogConvert;

import com.hxy.content.StringHelper;

/**
 * 用于转换ICS日志中的SQL为可执行SQL
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

		// 分割的第一段为FORMAT，第二段为PARAMS
		// 处理第一段的[]
		String formatString = splitStrings[0].replace("[", "");
		formatString = formatString.replace("]", "");
		// 处理第一段的?为%s
		formatString = formatString.replace("?", "'%s'");

		// 处理第二段[]
		String paramsString = splitStrings[1].replace("[", "");
		paramsString = paramsString.replace("]", "");
		// 第二段根据,分割
		String[] paramsStrings = paramsString.split(",");

		Object[] paramsObject = new Object[paramsStrings.length];
		for (int i = 0; i < paramsStrings.length; i++) {
			// 将分割的结果处理掉两边空格后，添加到Object中
			paramsObject[i] = (Object) StringHelper.Trim(paramsStrings[i]);
		}

		// 组合
		return String.format(formatString, paramsObject) + ";";
	}
}
