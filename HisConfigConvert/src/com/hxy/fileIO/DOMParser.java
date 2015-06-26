package com.hxy.fileIO;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hxy.IO.FileHelper;
import com.hxy.client.BLL;

/**
 * Parer XML by using DOM
 * 
 * @author hxy
 *
 */
public class DOMParser {
	private String model;//节点模版
	private String groupModel;//group节点模版
	private String groupModelTail;//group节点尾部
	private String writeFileName;//将返回的HisConfig写入此文件

	public String getWriteFileName() {
		return writeFileName;
	}

	public void setWriteFileName(String writeFileName) {
		this.writeFileName = writeFileName;
	}

	public String getGroupModelTail() {
		return groupModelTail;
	}

	public void setGroupModelTail(String groupModelTail) {
		this.groupModelTail = groupModelTail;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(String groupModel) {
		this.groupModel = groupModel;
	}

	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
			.newInstance();

	/**
	 * Load and parse XML file into DOM
	 * 
	 * @param filePath
	 * @return Document
	 */
	public Document parseFromUri(String filePath) {
		Document document = null;
		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * Load from contended String
	 * 
	 * @param contendedString
	 * @return Document
	 */
	public Document parseFromString(String contendedString) {
		Document document = null;
		StringReader sr = new StringReader(contendedString);
		InputSource is = new InputSource(sr);

		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * Convert XML to HisConfig
	 * 
	 * @param readXML
	 * @param model
	 * @param groupModel
	 * @return
	 */
	public String getStringOfHisConfig(String xmlPath) {
		StringBuffer buffer = new StringBuffer();// 临时存储结果

		// 以下变量用于处理DOMXML
		DOMParser parser = new DOMParser();
		Document document = parser.parseFromUri(xmlPath);
		Element rootElement = document.getDocumentElement();
		NodeList nodes = rootElement.getChildNodes();// 解析读取的XML中的节点为nodes
		if (nodes == null) {
			return "";
		}

		buffer.setLength(0);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;// 将遍历的单个节点转化为可方便处理的Element

				NodeList childNodes = element.getChildNodes();// 分析此节点的子节点
				
				//判断childNodes是否具有Element的节点
				Boolean hasElement = false;
				for(int j = 0; j < childNodes.getLength(); j ++){
					Node childNode = childNodes.item(j);
					if(childNode.getNodeType() == Node.ELEMENT_NODE){
						hasElement = true;
						break;
					}
				}

				if (hasElement) {
					// 若存在子节点，调用子节点解析，子节点函数迭代调用解析可能的子子节点
					buffer.append(getStringOfChildNode(rootElement, element));
				} else {
					// 若不存在子节点
					String elementNodeName = element.getNodeName();// 节点的名称
					String tempString = String.format(
							this.getModel(),
							elementNodeName,
							BLL.convertToETFName(elementNodeName), "yes");
					buffer.append(tempString);
				}
			}
		}

		//将返回结果写入文件
		FileHelper.appendContent(this.getWriteFileName(), "\n------------------------------------------------\n" + buffer.toString());
		
		return buffer.toString();
	}

	/**
	 * 读取子节点，子阶段递归调用此函数读取子节点直至没有子节点
	 * 
	 * @param fatherElement
	 * @param thisElement
	 * @return
	 */
	private String getStringOfChildNode(Element fatherElement,
			Element thisElement) {
		StringBuffer buffer = new StringBuffer();// 临时存储的buffer

		NodeList nodesOfChild = thisElement.getChildNodes();

		if (nodesOfChild == null) {
			return "";
		}

		buffer.setLength(0);
		// 首先拼接一个group出来
		String elementNodeName = thisElement.getNodeName();
		buffer.append(String.format(
				this.getGroupModel(), elementNodeName,
				BLL.convertToETFName(elementNodeName),
				BLL.convertToETFName(elementNodeName) + "_NUM", "yes"));

		for (int i = 0; i < nodesOfChild.getLength(); i++) {
			Node childNode = nodesOfChild.item(i);

			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) childNode;
				
				NodeList grandChildNodes = childElement.getChildNodes();//分析是否存在孙子节点
				
				//判断grandChildNodes是否具有Element结点
				Boolean hasElement = false;
				for(int j = 0; j < grandChildNodes.getLength(); j ++){
					Node grandChildNode = grandChildNodes.item(j);
					if(grandChildNode.getNodeType() == Node.ELEMENT_NODE){
						hasElement = true;
						break;
					}
				}
				
				if(hasElement){
					// 存在则迭代调用
					buffer.append(getStringOfChildNode(thisElement, childElement));
				} else {
					// 不存在则直接添加
					String elementNodeNameChild = childElement.getNodeName();// 节点的名称
					String tempString = String.format(
							this.getModel(),
							elementNodeNameChild,
							BLL.convertToETFName(elementNodeNameChild), "yes");
					buffer.append(tempString);
				}
			}

		}
		buffer.append(this.getGroupModelTail());

		return buffer.toString();
	}
}
