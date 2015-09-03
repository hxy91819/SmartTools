package com.file.connectfiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.hxy.IO.FileHelper;

/**
 * 连接SQL文件
 * @author hxy
 *
 */
public class Client {
    public static void main(String[] args) {
        String filePath = "E:\\临时SQL\\";
        
        File retFile = new File(filePath + "retSQL.sql");
        
        //如果已经存在输出文件，则删除
        if(retFile.exists()){
            retFile.delete();
        }

        //获取文件列表（需要在创建输出文件之前）
        ArrayList<File> arrSQLs = FileHelper.getFiles(filePath);
        
        try {
            retFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int arrIndex = 0; arrIndex < arrSQLs.size(); arrIndex++ )
        {
            String fileContent = FileHelper.readFileByChars(arrSQLs.get(arrIndex).getPath());
            if(arrIndex != arrSQLs.size() - 1){
                fileContent += "\n";
            }
            
            //添加到新的File中
            FileHelper.appendContent(retFile.getPath(), fileContent);
            
            System.out.println("添加文件 " + arrSQLs.get(arrIndex).getPath() + " 成功！"); 
        }
        
        System.out.println("完成！");
    }
}
