package com.hxy.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileHelper {

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * Mod by Hxy 2015-05-22 修改原函数，返回一个String。采用一次读取一个字节的方法。
	 */
	public static String readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		StringBuffer buffer = new StringBuffer();//中间存储。
		try {
			buffer.setLength(0);//清空buffer
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
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
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
