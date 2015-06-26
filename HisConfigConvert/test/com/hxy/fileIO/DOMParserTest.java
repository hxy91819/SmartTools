package com.hxy.fileIO;

import junit.framework.TestCase;

public class DOMParserTest extends TestCase {
	private DOMParser domParser;
	private String xmlTest;
	
	private DOMParser domParserRead;
	
	@Override
	public void setUp() throws Exception {
		domParser = new DOMParser();
		domParserRead = new DOMParser();
		
		//��ʼ��domParser������XML�ĵ�ַ��ע���ַ������ڣ���δ������
		String filePath = "E:\\MyStudyProject\\HisConfigConvert\\XML\\";
		domParser.setModel(filePath + "WRITE\\model.xml");
		domParser.setGroupModel(filePath + "WRITE\\groupModel.xml");
		domParser.setGroupModelTail(filePath + "WRITE\\groupModelTail.XML");
		xmlTest = filePath + "IO\\simple.xml";
		domParser.setWriteFileName(filePath + "IO\\result.xml");
		
		//��ʼ��domParserRead
		String filePathRead = "E:\\MyStudyProject\\HisConfigConvert\\XML\\";
		domParserRead.setModel(filePathRead + "READ\\model.xml");
		domParserRead.setGroupModel(filePathRead + "READ\\groupModel.xml");
		domParserRead.setGroupModelTail(filePathRead + "READ\\groupModelTail.XML");
		xmlTest = filePathRead + "IO\\simple.xml";
		domParserRead.setWriteFileName(filePathRead + "IO\\resultRead.xml");
	}

	/**
	 * ���ɽ��XML��result.XML��
	 */
	public void testgetStringOfHisConfig(){
//		System.out.println(domParser.getStringOfHisConfig(xmlTest));
		System.out.println("------------------------");
		System.out.println(domParserRead.getStringOfHisConfig(xmlTest));
	}
}
