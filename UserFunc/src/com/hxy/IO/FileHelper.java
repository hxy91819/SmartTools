package com.hxy.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class FileHelper {

	/**
	 * 按照字符读取文件内容
	 */
	public static String readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.setLength(0);
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
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
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    /**
     * 获取文件指定目录下的文件列表
     * @param filePath
     * @return
     */
    public static ArrayList<File> getFiles(String filePath, final String filterName){
        ArrayList<File> retValArr = new ArrayList<File>();
        
        File file = new File(filePath);
        
        file.mkdir();
        
        String[] namesStrings = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(filterName)){
                    return true;
                }
                return false;
            }
        });
        
        for(String nameString: namesStrings){
            retValArr.add(new File(filePath + nameString));
        }
        return retValArr;
    }
}
