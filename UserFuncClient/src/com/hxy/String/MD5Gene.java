package com.hxy.String;

import com.hxy.content.StringHelper;

public class MD5Gene {
	public static void main(String[] args) {
		String string = "2015-06-29 17:52:54CDCHQRMYY";
				
		System.out.print(StringHelper.getMD5(string).toUpperCase());
	}
}
