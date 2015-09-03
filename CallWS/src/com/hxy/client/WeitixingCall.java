package com.hxy.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.hxy.IO.FileHelper;
import com.hxy.IO.PathHelper;
import com.hxy.content.DateHelper;
import com.hxy.content.StringHelper;

/**
 * �������ǵ�΢���ѽӿ�
 * 
 * @author hxy
 *
 */
public class WeitixingCall {
    public static void main(String[] args) {

    }

    private static String callWTX(String inputFileName) {
        WeitixingCall client = new WeitixingCall();
        String wsURL = "http://uat.gzhc365.com/openapi/services/ws";
        String packageString = "http://uat.gzhc365.com/openapi";
        String methodName = "healthService";

        String filePath = PathHelper.getProjectPathWithoutBin((Object) client) + "Files\\" + inputFileName;
        String formaterString = FileHelper.readFileByChars(filePath);// ��ȡ�ļ����ݵ�String��

        String nowString = DateHelper.getNow("yyyy-MM-dd HH:mm:ss");
        String key = "CDCHQRMYY";

        String md5 = StringHelper.getMD5(nowString + key).toUpperCase();

        md5 = "953EF61E819E412323256B58DBDA7ADB";//���ݿ����õ�

        String inputString = String.format(formaterString, nowString, key, md5);

        System.out.println("intputString:\n" + inputString);

        Object[] opAddEntryArgs = new Object[1];
        opAddEntryArgs[0] = (Object) inputString;

        String output = "";
        try {
            output = CallWS(wsURL, packageString, methodName, opAddEntryArgs);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 沈阳六院
     * 
     * @param inputFileName
     * @return
     */
    private static String callSY6(String inputFileName) {
        WeitixingCall client = new WeitixingCall();
        String wsURL = "http://59.46.42.87/HaiciHospService.asmx?wsdl ";
        String packageString = "http://zlsoft.com/haici";
        String methodName = "NetTest";

        String filePath = PathHelper.getProjectPathWithoutBin((Object) client) + "Files\\" + inputFileName;
        String formaterString = FileHelper.readFileByChars(filePath);

        String inputString = formaterString;

        System.out.println("intputString:\n" + inputString);

        Object[] opAddEntryArgs = new Object[1];
        opAddEntryArgs[0] = (Object) inputString;

        String output = "";
        try {
            output = CallWS(wsURL, packageString, methodName, opAddEntryArgs);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 
     * @param wsURL
     * @param packageString
     * @param methodName
     *            WebService������
     * @param opAddEntryArgs
     *            ָ����������
     * @throws AxisFault
     */
    private static String CallWS(String wsURL, String packageString, String methodName, Object[] opAddEntryArgs)
            throws AxisFault {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        // ָ������WebService��URL
        EndpointReference targetEPR = new EndpointReference(wsURL);
        options.setTo(targetEPR);

        // ָ��Ҫ���õļ�������еķ�����WSDL�ļ��������ռ䣺test.hxy.com��
        QName opAddEntry = new QName(packageString, methodName);

        // ָ��plus��������ֵ���������͵�Class����
        @SuppressWarnings("rawtypes")
        Class[] classes = new Class[] { String.class };

        // ����plus����������÷����ķ���ֵ
        return serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0].toString();
    }
}
