package com.hxy.content;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	/**
	 * 
	 * @param formaterString
	 * 	such as yyyyMMddHHmmss
	 * @return
	 */
	public static String getNow(String formaterString){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(formaterString);
		String dateString = formatter.format(currentTime);
		
		return dateString;
	}
}
