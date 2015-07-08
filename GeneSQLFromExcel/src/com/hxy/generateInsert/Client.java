package com.hxy.generateInsert;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hxy.content.StringHelper;
import com.hxy.generateInsert.table.CMMTHISBUI;

/**
 * ����ָ�������Insert���
 * 
 * @author hxy
 *
 */
public class Client {
	
	public static void main(String[] args) {
		System.out.println(Client.getNeedSQL("6001", 7, "3"));
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
			
			
			String bui_cd = par_bui_cd + StringHelper.polishString(sort_no, 3, "0");
			table.setBui_cd(bui_cd);
			
			String bui_nm = sort_no + "F";
			table.setBui_nm(bui_nm);
			
			table.setBui_lv(bui_lv);
			
			returnValString = returnValString + table.getInsertSQL() + "\n";
		}
		return returnValString;
	}

}
