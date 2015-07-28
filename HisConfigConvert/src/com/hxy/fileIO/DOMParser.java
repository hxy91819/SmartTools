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

import com.hxy.client.BLL;

/**
 * Parer XML by using DOM
 * 
 * @author hxy
 *
 */
public class DOMParser {
    private String model;//�ڵ�ģ��
    private String groupModel;//group�ڵ�ģ��
    private String groupModelTail;//group�ڵ�β��
    private String writeFileName;//�����ص�HisConfigд����ļ�

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
     * 获取传入XML的根节点名称
     * @param xmlContent
     * @return
     */
    public String getXMLRoot(String xmlContent) {
        DOMParser parser = new DOMParser();
        Document document = parser.parseFromString(xmlContent);
        Element rootElement = document.getDocumentElement();
        return rootElement.getTagName();
    }

    /**
     * Convert XML to HisConfig
     * 
     * @param readXML
     * @param model
     * @param groupModel
     * @return
     */
    public String getStringOfHisConfig(String xmlContent) {
        StringBuffer buffer = new StringBuffer();// ��ʱ�洢���

        // ���±������ڴ���DOMXML
        DOMParser parser = new DOMParser();
        Document document = parser.parseFromString(xmlContent);
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();// ������ȡ��XML�еĽڵ�Ϊnodes
        if (nodes == null) {
            return "";
        }

        buffer.setLength(0);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;// �������ĵ����ڵ�ת��Ϊ�ɷ��㴦���Element

                NodeList childNodes = element.getChildNodes();// �����˽ڵ���ӽڵ�

                //�ж�childNodes�Ƿ����Element�Ľڵ�
                Boolean hasElement = false;
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        hasElement = true;
                        break;
                    }
                }

                if (hasElement) {
                    // �������ӽڵ㣬�����ӽڵ�������ӽڵ㺯���������ý������ܵ����ӽڵ�
                    buffer.append(getStringOfChildNode(rootElement, element));
                } else {
                    // ���������ӽڵ�
                    String elementNodeName = element.getNodeName();// �ڵ������
                    String tempString = String.format(
                            this.getModel(),
                            elementNodeName,
                            BLL.convertToETFName(elementNodeName), "yes");
                    buffer.append(tempString);
                }
            }
        }

        return buffer.toString();
    }

    /**
     * 用于迭代
     * 
     * @param fatherElement
     * @param thisElement
     * @return
     */
    private String getStringOfChildNode(Element fatherElement,
            Element thisElement) {
        StringBuffer buffer = new StringBuffer();// ��ʱ�洢��buffer

        NodeList nodesOfChild = thisElement.getChildNodes();

        if (nodesOfChild == null) {
            return "";
        }

        buffer.setLength(0);
        // ����ƴ��һ��group����
        String elementNodeName = thisElement.getNodeName();
        buffer.append(String.format(
                this.getGroupModel(), elementNodeName,
                BLL.convertToETFName(elementNodeName),
                BLL.convertToETFName(elementNodeName) + "_NUM", "yes"));

        for (int i = 0; i < nodesOfChild.getLength(); i++) {
            Node childNode = nodesOfChild.item(i);

            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNode;

                NodeList grandChildNodes = childElement.getChildNodes();//�����Ƿ�������ӽڵ�

                //�ж�grandChildNodes�Ƿ����Element���
                Boolean hasElement = false;
                for (int j = 0; j < grandChildNodes.getLength(); j++) {
                    Node grandChildNode = grandChildNodes.item(j);
                    if (grandChildNode.getNodeType() == Node.ELEMENT_NODE) {
                        hasElement = true;
                        break;
                    }
                }

                if (hasElement) {
                    // �������������
                    buffer.append(getStringOfChildNode(thisElement, childElement));
                } else {
                    // ��������ֱ�����
                    String elementNodeNameChild = childElement.getNodeName();// �ڵ������
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
