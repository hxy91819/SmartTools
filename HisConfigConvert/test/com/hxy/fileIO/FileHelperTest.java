package com.hxy.fileIO;

import junit.framework.TestCase;

import com.hxy.IO.FileHelper;

public class FileHelperTest extends TestCase {
	
	/**
	 * ≤‚ ‘∂¡»°–ßπ˚
	 */
	public void testreadFileByChars(){
		String filePath = "E:\\MyStudyProject\\HisConfigConvert\\XML\\";
		String fileName = "simple.xml";
		
		String printString = FileHelper.readFileByChars(filePath + fileName);
		
		System.out.println(printString);
	}
}
