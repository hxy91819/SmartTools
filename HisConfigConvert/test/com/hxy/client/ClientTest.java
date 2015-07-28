package com.hxy.client;

import junit.framework.TestCase;

public class ClientTest extends TestCase {
    public void testgetStringOfHisConfig(){
        String forTranString = "<Request><test></test><aaa></aaa><bbb></bbb></Request>";
        String resultString = Client.getStringOfHisConfig(forTranString);
        
        System.out.println(resultString);
    }
    
    public void testgetStringOfHisConfigResponse(){
        String forTranString = "<Response><test></test><aaa></aaa><bbb></bbb></Response>";
        String resultString = Client.getStringOfHisConfig(forTranString);
        
        System.out.println(resultString);
    }
    
    public void testgetStringOfHisConfigWrongRoot(){
        String forTranString = "<adf><test></test><aaa></aaa><bbb></bbb></adf>";
        String resultString = Client.getStringOfHisConfig(forTranString);
        
        System.out.println(resultString);
    }
}
