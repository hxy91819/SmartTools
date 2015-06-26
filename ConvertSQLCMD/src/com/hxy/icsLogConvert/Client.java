package com.hxy.icsLogConvert;

import com.hxy.IO.FileHelper;
import com.hxy.IO.PathHelper;
import com.hxy.content.StringHelper;

/**
 * ����ת��ICS��־�е�SQLΪ��ִ��SQL
 * @author hxy
 *
 */
public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		
		String filePath = PathHelper.getProjectPathWithoutBin((Object)client) + "Files\\SQL_CMD.TXT";//��ȡ��ת��ָ���ļ���·����ַ
		String test = FileHelper.readFileByChars(filePath);//��ȡ�ļ����ݵ�String��

		String[] splitStrings = test.split("]:");

		for (String string : splitStrings) {
			System.out.println(string);
		}

		// �ָ�ĵ�һ��ΪFORMAT���ڶ���ΪPARAMS
		// �����һ�ε�[]
		String formatString = splitStrings[0].replace("[", "");
		formatString = formatString.replace("]", "");
		System.out.println(formatString);
		// �����һ�ε�?Ϊ%s
		formatString = formatString.replace("?", "'%s'");
		System.out.println(formatString);

		// ����ڶ���[]
		String paramsString = splitStrings[1].replace("[", "");
		paramsString = paramsString.replace("]", "");
		// �ڶ��θ���,�ָ�
		String[] paramsStrings = paramsString.split(",");
		for (String string : paramsStrings) {
			System.out.println(string);
		}

		Object[] paramsObject = new Object[paramsStrings.length];
		for (int i = 0; i < paramsStrings.length; i++) {
			//���ָ�Ľ����������߿ո����ӵ�Object��
			paramsObject[i] = (Object)StringHelper.Trim(paramsStrings[i]);
		}
		
		//���
		System.out.println("--------------------");
		System.out.println(String.format(formatString, paramsObject));
	}
}
