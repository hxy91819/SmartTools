package com.hxy.client;

import com.hxy.fileIO.DOMParser;

public class Client {

    public static String getStringOfHisConfig(String inputXML) {
        StringBuffer returnBuffer = new StringBuffer();

        DOMParser domParser = new DOMParser();// Request
        DOMParser domParserRead = new DOMParser();// Response

        //Request
        domParser.setModel(UserConst.MODEL_WRITE);
        domParser.setGroupModel(UserConst.GROUP_MODEL_WRITE);
        domParser.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_WRITE);

        //Response
        domParserRead.setModel(UserConst.MODEL_READ);
        domParserRead.setGroupModel(UserConst.GROUP_MODEL_READ);
        domParserRead.setGroupModelTail(UserConst.GROUP_MODEL_TAIL_READ);

        String rootName = domParser.getXMLRoot(inputXML);

        if (rootName.equals("Request") || rootName.equals("request")) {
            returnBuffer.append("<Request name=\"Request\">");
            returnBuffer.append(domParser.getStringOfHisConfig(inputXML));
            returnBuffer.append("</Request>");
            return returnBuffer.toString();
        } else if (rootName.equals("Response") || rootName.equals("response")) {
            returnBuffer.append("<Response name=\"Response\">");
            returnBuffer.append(domParserRead.getStringOfHisConfig(inputXML));
            returnBuffer.append("</Response>");
            return returnBuffer.toString();
        } else {
            return "Root Name is Wrong: " + rootName + "! Only \"Request\",\"request\",\"Response\",\"response\" is Allowed!";
        }
    }
}
