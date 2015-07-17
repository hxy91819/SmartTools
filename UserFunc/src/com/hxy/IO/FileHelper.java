package com.hxy.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileHelper {

	/**
	 * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
	 * 
	 * Mod by Hxy 2015-05-22 �޸�ԭ����������һ��String������һ�ζ�ȡһ���ֽڵķ�����
	 */
	public static String readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		StringBuffer buffer = new StringBuffer();//�м�洢��
		try {
			buffer.setLength(0);//���buffer
			// һ�ζ�һ���ַ�
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
				// ������������ַ��ֿ���ʾʱ���ỻ�����С�
				// ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
				if (((char) tempchar) != '\r') {
					//System.out.print((char) tempchar);
					buffer.append((char)tempchar);
				}
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}

	/**
	 * append content to this file's end
	 * @param fileName
	 * @param content
	 */
	public static void appendContent(String fileName, String content){
        try {
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
