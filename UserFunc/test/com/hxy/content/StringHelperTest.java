package com.hxy.content;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class StringHelperTest extends TestCase {
	public void testgetMD5(){
		String string = "hello\n";
		string = StringHelper.getMD5(string);
		Assert.assertEquals("b1946ac92492d2347c6235b4d2611184", string);
	}
}
