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
 * 调用我们的微提醒接口
 * @author hxy
 *
 */
public class WeitixingCall {
	public static void main(String[] args)  {
		WeitixingCall client = new WeitixingCall();
		//访问WebService必须的参数
		String wsURL = "http://uat.gzhc365.com/openapi/services/ws";
		String packageString = "http://uat.gzhc365.com/openapi";
		String methodName = "healthService";
		
		//生成需要传入的xml
		String filePath = PathHelper.getProjectPathWithoutBin((Object) client)
				+ "Files\\Input.xml";// 获取待转换指令文件的路径地址
		String formaterString = FileHelper.readFileByChars(filePath);// 读取文件内容到String中
		
		String nowString = DateHelper.getNow("yyyy-MM-dd HH:mm:ss");
		String key = "CDCHQRMYY";
		
		String md5 = StringHelper.getMD5(nowString + key).toUpperCase();
		
		md5 = "953EF61E819E412323256B58DBDA7ADB";//数据库配置的
		
		String inputString = String.format(formaterString, nowString, key, md5);

		System.out.println("intputString:\n" + inputString);
		
		Object[] opAddEntryArgs = new Object[1];
		opAddEntryArgs[0] = (Object) inputString;

		try {
			System.out.println(CallWS(wsURL, packageString, methodName, opAddEntryArgs));
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param wsURL
	 *            WebService地址
	 * @param packageString
	 *            WebService包名
	 * @param methodName
	 *            WebService方法名
	 * @param opAddEntryArgs
	 *            指定方法参数
	 * @throws AxisFault
	 */
	private static String CallWS(String wsURL, String packageString, String methodName, Object[] opAddEntryArgs)
			throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		// 指定调用WebService的URL
		EndpointReference targetEPR = new EndpointReference(wsURL);
		options.setTo(targetEPR);

		// 指定要调用的计算机器中的方法及WSDL文件的命名空间：test.hxy.com。
		QName opAddEntry = new QName(packageString, methodName);

		// 指定plus方法返回值的数据类型的Class对象
		@SuppressWarnings("rawtypes")
		Class[] classes = new Class[] { String.class };

		// 调用plus方法并输出该方法的返回值
		return serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0].toString();
	}
}
