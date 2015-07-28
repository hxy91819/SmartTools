package com.hxy.fileIO;

import junit.framework.TestCase;

public class DOMParserTest extends TestCase {
	public void testgetXMLRoot(){
	    DOMParser domParser = new DOMParser();
	    
	    String test = domParser.getXMLRoot("<Request><test></test></Request>");
	    
	    System.out.println(test);
	}
}
