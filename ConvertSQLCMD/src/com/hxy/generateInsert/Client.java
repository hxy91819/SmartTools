package com.hxy.generateInsert;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hxy.generateInsert.table.CMMTHISBUI;

/**
 * ����ָ�������Insert���
 * 
 * @author hxy
 *
 */
public class Client {
	
	public static void main(String[] args) {
		System.out.println(Client.getNeedSQL("23", 6, "2"));
	}

	/**
	 * ����ָ����������SQL���
	 * 
	 * ��������¥��
	 * 
	 * @param par_bui_cd
	 *            ���ڵ���
	 * @param num
	 *            ��Ҫ���ɵ�����
	 * @return
	 */
	private static String getNeedSQL(String par_bui_cd, int num, String bui_lv) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);

		String his_cd = "26";
		String are_no = "1";
		String tm_smp = dateString;
		
		String returnValString = "";

		for (int i = 0; i < num; i++) {
			CMMTHISBUI table = new CMMTHISBUI();

			table.setHis_cd(his_cd);
			table.setAre_id(are_no);
			table.setTm_smp(tm_smp);
			table.setPar_bui_cd(par_bui_cd);
			
			String sort_no = ((Integer)(i + 1)).toString();
			table.setSort_no(sort_no);
			
			
			String bui_cd = par_bui_cd + Client.addZeroForNum(sort_no, 3);
			table.setBui_cd(bui_cd);
			
			String bui_nm = sort_no + "F";
			table.setBui_nm(bui_nm);
			
			table.setBui_lv(bui_lv);
			
			returnValString = returnValString + table.getInsertSQL() + "\n";
		}
		return returnValString;
	}

	/**
	 * �����ַ�
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// ��0
				// sb.append(str).append("0");//�Ҳ�0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}
}