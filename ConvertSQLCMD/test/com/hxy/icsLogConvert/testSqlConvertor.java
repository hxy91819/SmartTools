package com.hxy.icsLogConvert;

import junit.framework.TestCase;

public class testSqlConvertor extends TestCase {
    //passed
    public void testgetSQLnull(){
        String forTransString = "";
        System.out.println(SqlConvertor.getSQL(forTransString));
    }
    
    public void testgetSQLdisorder(){
        String forTransString = "sadfj;lsdjfa;lsdf";
        System.out.println(SqlConvertor.getSQL(forTransString));
    }
}
