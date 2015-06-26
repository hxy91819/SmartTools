package com.hxy.icsLogConvert;

import com.hxy.IO.FileHelper;
import com.hxy.IO.PathHelper;
import com.hxy.content.StringHelper;

/**
 * 用于转换ICS日志中的SQL为可执行SQL
 * @author hxy
 *
 */
public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		
		String filePath = PathHelper.getProjectPathWithoutBin((Object)client) + "Files\\SQL_CMD.TXT";//获取待转换指令文件的路径地址
		String test = FileHelper.readFileByChars(filePath);//读取文件内容到String中

		String[] splitStrings = test.split("]:");

		for (String string : splitStrings) {
			System.out.println(string);
		}

		// 分割的第一段为FORMAT，第二段为PARAMS
		// 处理第一段的[]
		String formatString = splitStrings[0].replace("[", "");
		formatString = formatString.replace("]", "");
		System.out.println(formatString);
		// 处理第一段的?为%s
		formatString = formatString.replace("?", "'%s'");
		System.out.println(formatString);

		// 处理第二段[]
		String paramsString = splitStrings[1].replace("[", "");
		paramsString = paramsString.replace("]", "");
		// 第二段根据,分割
		String[] paramsStrings = paramsString.split(",");
		for (String string : paramsStrings) {
			System.out.println(string);
		}

		Object[] paramsObject = new Object[paramsStrings.length];
		for (int i = 0; i < paramsStrings.length; i++) {
			//将分割的结果处理掉两边空格后，添加到Object中
			paramsObject[i] = (Object)StringHelper.Trim(paramsStrings[i]);
		}
		
		//组合
		System.out.println("--------------------");
		System.out.println(String.format(formatString, paramsObject));
	}
}
