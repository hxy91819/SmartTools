package com.hxy.client;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class BLLTest extends TestCase {
	
	public void testconvertToETFName(){
		String str = "UserInfo";
		
		str = BLL.convertToETFName(str);
		
		System.out.println(str);
		
		Assert.assertEquals("USER_INFO", str);
	}
	
	public void testconvertToETFName2(){
		String str = "OPRegister";
		
		str = BLL.convertToETFName(str);
		
		System.out.println(str);
		
		Assert.assertEquals("OP_REGISTER", str);
	}
	
	public void testconvertToETFName3(){
		String str = "endTime";
		
		str = BLL.convertToETFName(str);
		
		System.out.println(str);
		
		Assert.assertEquals("END_TIME", str);
	}
	
	public void testconvertToETFName4(){
		String str = "abnormal";
		
		str = BLL.convertToETFName(str);
		
		System.out.println(str);
		
		Assert.assertEquals("ABNORMAL", str);
	}
	
	
	public void testconvertXMLToConfigStyle(){
		
	}
}
