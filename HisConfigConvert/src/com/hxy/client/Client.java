package com.hxy.client;

import com.hxy.fileIO.DOMParser;

/**
 * 转换输入的XML为格式化的XML
 * 
 * 规则：
 * 
 * ·根据传入的路径找到待转换的XML
 * 
 * ·参考hisConfig进行开发，Request的necessary均为yes，ETFName使用函数convertToETFName转换。
 * 
 * ·首先能够处理简单的XML，不包含XML层次仅有一层。
 * 
 * ·然后考虑处理复杂多层的XML。
 * 
 * @author hxy
 *
 */
public class Client {

	/**
	 * 根据model/groupModel/groupModelTail设置DOMParser。DOMParser根据这些属性，
	 * 生成相应的HisConfig需要的XML。转换结果存放在result/resultRead中。
	 * 
	 * 目前生成的XML的necessary属性均为yes，且无注释。可以考虑后续版本增加智能判断？增加注释功能？
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		DOMParser domParser = new DOMParser();// Request用的解析器
		DOMParser domParserRead = new DOMParser();// Response用的解析器
		String xmlTest;// 待转换的XML文件存放路径

		// 初始化domParser，设置XML的地址。注意地址必须存在，暂未做处理。
		String filePath = client.getProjectPathWithoutBin() + "XML\\";
		domParser.setModel(UserConst.MODEL_WRITE);
		domParser.setGroupModel(UserConst.GROUP_MODEL_WRITE);
		domParser.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_WRITE);
		domParser.setWriteFileName(filePath + "IO\\result.xml");

		xmlTest = filePath + "IO\\simple.xml";

		// 初始化domParserRead
		String filePathRead = client.getProjectPathWithoutBin() + "XML\\";
		domParserRead.setModel(UserConst.MODEL_READ);
		domParserRead.setGroupModel(UserConst.GROUP_MODEL_READ);
		domParserRead.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_READ);
		domParserRead.setWriteFileName(filePathRead + "IO\\resultRead.xml");
		xmlTest = filePathRead + "IO\\simple.xml";

		// 生成指定XML到result.xml（对应Request）和resultRead.xml（对应Response）
		System.out.println(domParser.getStringOfHisConfig(xmlTest));
		System.out.println("------------------------");
		System.out.println(domParserRead.getStringOfHisConfig(xmlTest));
	}
	
	/**
	 * 获取不包含"/bin"的项目路径
	 * @return
	 */
	public String getProjectPathWithoutBin(){
		String originPath = this.getClass().getResource("/").getPath();//获取原始的项目路径
		
		return originPath.substring(0, originPath.length() - 4); //去除原始项目路径后面的/bin
	}
}
