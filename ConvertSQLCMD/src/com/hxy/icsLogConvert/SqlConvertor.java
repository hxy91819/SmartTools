package com.hxy.icsLogConvert;

import com.hxy.content.StringHelper;

/**
 * 转换ICS日志为SQL
 * 
 * @author hxy
 *
 */
public class SqlConvertor {
    public static void main(String[] args) {
        String forTransString = "sadfj;lsdjfa;lsdf";
        System.out.println(SqlConvertor.getSQL(forTransString));
    }

    public static String getSQL(String forConvert) {
        if (forConvert.equals("")) {
            return "--[Empty error]";
        }

        String[] splitStrings = forConvert.split("\\]\\:");//多个字符需要使用正则表达式\\转义。

        if (splitStrings.length < 2) {
            //此时分割失败，尝试使用“][”来进行分割
            splitStrings = forConvert.split("\\]\\[");
            if (splitStrings.length < 2) {
                return "--[Format error]" + forConvert;
            }
        }

        //过滤掉[]，替换你?号为'%s'，然后使用String.format完成字符串
        String formatString = splitStrings[0].replace("[", "");
        formatString = formatString.replace("]", "");

        formatString = formatString.replace("?", "'%s'");

        String paramsString = splitStrings[1].replace("[", "");
        paramsString = paramsString.replace("]", "");
        // 
        String[] paramsStrings = paramsString.split(",");

        Object[] paramsObject = new Object[paramsStrings.length];
        for (int i = 0; i < paramsStrings.length; i++) {
            paramsObject[i] = (Object) StringHelper.Trim(paramsStrings[i]);
        }

        return String.format(formatString, paramsObject) + ";";
    }
}
