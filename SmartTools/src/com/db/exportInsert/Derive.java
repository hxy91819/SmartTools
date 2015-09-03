package com.db.exportInsert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 实现到导出Oracle中表的insert语句
 *
 * 代码来源：http://bbs.csdn.net/topics/360200693
 * 
 * TODO
 * 需要尝试读取正式环境数据表的字段列表;在服务器上执行，生成一个特殊文件，然后在本地读取特殊文件执行生成指定SQL;
 * 多模式：打包模式，合并文件，增加验证SQL；普通模式，分离文件。
 * 设计UI；
 *
 */
public class Derive {
    private final static String PRE_NULL = "MARK_NULL|";
    private final static String PRE_NUMBER = "MARK_NUMBER|";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //加载驱动程序
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //打开连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.11.101:1521:yldb", "bkgadm", "bkgadm#gzhc2014");
        //输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input table names, use \"|\" to separate ：");
        String input = scanner.nextLine();
        System.out.println("Input where(this exp will be used with all the tables):");
        String whereString = scanner.nextLine();

        //定义tableNames保存所有需要导出的表名
        String[] tableNames = input.split("\\|");

        //此处设置一个循环处理多个表
        for (int tableIndex = 0; tableIndex < tableNames.length; tableIndex++) {
            String tableName = tableNames[tableIndex];
            if (tableName.equals("")) {
                continue;
            }

            //定义columns集合保存所有的字段名
            List<String> columns = new ArrayList<String>();
            //定义columnTypes集合保存所有字段的类型
            List<String> columnTypes = new ArrayList<String>();
            //定以list集合保存表中的数据
            List<String> list = new ArrayList<String>();

            //sql语句
            String sql = "select * from " + tableName + " " + whereString;
            //执行sql
            PreparedStatement prst = conn.prepareStatement(sql);
            //返回结果
            ResultSet rs = prst.executeQuery();
            //获取关于 ResultSet 对象中列的类型和属性信息的对象
            ResultSetMetaData rmd = rs.getMetaData();
            //循环得到所有的字段名字保存到column集合中
            for (int i = 1; i <= rmd.getColumnCount(); i++) {
                columns.add(rmd.getColumnName(i));
                columnTypes.add(rmd.getColumnTypeName(i));
            }
            //System.out.println(column);
            //定义存储insert语句中的列名
            String columnList = "";
            //循环读出表中数据，保存到list集合中
            while (rs.next()) {
                //从column集合中取得数据，便于读出数据库表的数据
                for (int i = 0; i < columns.size(); i++) {
                    //每个字段名
                    String column = columns.get(i);
                    //每个字段类型
                    String columnType = columnTypes.get(i);
                    //每个字段内容
                    String content = rs.getString(column);

                    //如果字段是null，则加上标签
                    if (rs.wasNull()) {
                        content = PRE_NULL + content;
                    } else {
                        //如果字段类型是NUMBER，则加上NUMBER标签
                        if (columnType.equals("NUMBER")) {
                            content = PRE_NUMBER + content;
                        }
                    }
                    //加载到list中
                    list.add(content);
                }
            }

            //获取字段列表
            for (int i = 0; i < columns.size(); i++) {
                //每个字段名
                String column = columns.get(i);
                //获得字段列表
                if (i == columns.size() - 1) {
                    columnList += column;
                } else {
                    columnList += column + ",";
                }
            }

            String str = "";
            //定义st集合，用于保存一个对象数据
            List<String> st = null;
            //column的长度， 也就是列数
            int num = columns.size();

            StringBuffer sb2 = new StringBuffer();
            sb2.append("REM INSERTING into " + tableName.toUpperCase() + "\n");
            //保存最终的sql语句
            String insert = "";
            int r = 0;
            while (r < list.size()) {
                //截取集合，得到一个对象的数据
                st = list.subList(r, r + num);

                //定义Stringbuffer截取字符串
                StringBuffer sb = new StringBuffer();
                //循环出单个的值
                for (int i = 0; i < st.size(); i++) {
                    //用于储存单个字段的
                    String ss = st.get(i);
                    //用于存储用与拼接的insert中字段块
                    String ssforAppend;
                    
                    if (ss.startsWith(PRE_NULL)) {
                        //如果字段值为null，需要插入null值
                        ssforAppend = ss.substring(PRE_NULL.length(), ss.length());
                    } else if (ss.startsWith(PRE_NUMBER)) {
                        //如果字段值为NUMBER，不需要引号。
                        ssforAppend = ss.substring(PRE_NUMBER.length(), ss.length());
                    } else {
                        ssforAppend = "'" + ss + "'";
                    }
                    //判断是否为最后一个字段，则末尾不能加上,
                    if (i == st.size() - 1) {
                        sb.append(ssforAppend);
                    } else {
                        sb.append(ssforAppend + ",");
                    }
                    //拼写insert语句
                    str = "Insert into " + tableName.toUpperCase() + " (" + columnList + ")" + " values (" + sb + ");";
                }
                sb2.append(str);
                sb2.append("\n");
                insert = sb2.toString();

                r = r + num;
            }

            //创建输出目录
            File file = new File("D:\\exports\\");
            file.mkdirs();
            /*
             * 创建输出流对象
             */
            FileWriter fw = new FileWriter(file.getPath() + "\\" + tableName + ".sql");
            //把insert放到fw所指的文件夹中
            fw.write(insert);
            //关闭输出流
            if (null != fw) {
                fw.close();
            }

            System.out.println(tableName + " Done!");
            /*
             * 关闭相关连接
             */
            rs.close();
            prst.close();
        }

        conn.close();
    }
}
