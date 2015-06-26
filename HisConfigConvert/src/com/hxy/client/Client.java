package com.hxy.client;

import com.hxy.fileIO.DOMParser;

/**
 * ת�������XMLΪ��ʽ����XML
 * 
 * ����
 * 
 * �����ݴ����·���ҵ���ת����XML
 * 
 * ���ο�hisConfig���п�����Request��necessary��Ϊyes��ETFNameʹ�ú���convertToETFNameת����
 * 
 * �������ܹ�����򵥵�XML��������XML��ν���һ�㡣
 * 
 * ��Ȼ���Ǵ����Ӷ���XML��
 * 
 * @author hxy
 *
 */
public class Client {

	/**
	 * ����model/groupModel/groupModelTail����DOMParser��DOMParser������Щ���ԣ�
	 * ������Ӧ��HisConfig��Ҫ��XML��ת����������result/resultRead�С�
	 * 
	 * Ŀǰ���ɵ�XML��necessary���Ծ�Ϊyes������ע�͡����Կ��Ǻ����汾���������жϣ�����ע�͹��ܣ�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		
		DOMParser domParser = new DOMParser();// Request�õĽ�����
		DOMParser domParserRead = new DOMParser();// Response�õĽ�����
		String xmlTest;// ��ת����XML�ļ����·��

		// ��ʼ��domParser������XML�ĵ�ַ��ע���ַ������ڣ���δ������
		String filePath = client.getProjectPathWithoutBin() + "XML\\";
		domParser.setModel(UserConst.MODEL_WRITE);
		domParser.setGroupModel(UserConst.GROUP_MODEL_WRITE);
		domParser.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_WRITE);
		domParser.setWriteFileName(filePath + "IO\\result.xml");

		xmlTest = filePath + "IO\\simple.xml";

		// ��ʼ��domParserRead
		String filePathRead = client.getProjectPathWithoutBin() + "XML\\";
		domParserRead.setModel(UserConst.MODEL_READ);
		domParserRead.setGroupModel(UserConst.GROUP_MODEL_READ);
		domParserRead.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_READ);
		domParserRead.setWriteFileName(filePathRead + "IO\\resultRead.xml");
		xmlTest = filePathRead + "IO\\simple.xml";

		// ����ָ��XML��result.xml����ӦRequest����resultRead.xml����ӦResponse��
		System.out.println(domParser.getStringOfHisConfig(xmlTest));
		System.out.println("------------------------");
		System.out.println(domParserRead.getStringOfHisConfig(xmlTest));
	}
	
	/**
	 * ��ȡ������"/bin"����Ŀ·��
	 * @return
	 */
	public String getProjectPathWithoutBin(){
		String originPath = this.getClass().getResource("/").getPath();//��ȡԭʼ����Ŀ·��
		
		return originPath.substring(0, originPath.length() - 4); //ȥ��ԭʼ��Ŀ·�������/bin
	}
}
