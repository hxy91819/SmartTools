package com.hxy.generateInsert.table;

/**
 * 科室分布表
 * @author hxy
 *
 */
public class CMMTHISBUI {
	private String his_cd;
	private String bui_cd;
	private String bui_nm;
	private String bui_lv;
	private String par_bui_cd;
	private String sort_no;
	private String tm_smp;
	private String are_id;
	
	public String getHis_cd() {
		return his_cd;
	}

	public void setHis_cd(String his_cd) {
		this.his_cd = his_cd;
	}

	public String getBui_cd() {
		return bui_cd;
	}

	public void setBui_cd(String bui_cd) {
		this.bui_cd = bui_cd;
	}

	public String getBui_nm() {
		return bui_nm;
	}

	public void setBui_nm(String bui_nm) {
		this.bui_nm = bui_nm;
	}

	public String getBui_lv() {
		return bui_lv;
	}

	public void setBui_lv(String bui_lv) {
		this.bui_lv = bui_lv;
	}

	public String getPar_bui_cd() {
		return par_bui_cd;
	}

	public void setPar_bui_cd(String par_bui_cd) {
		this.par_bui_cd = par_bui_cd;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	public String getTm_smp() {
		return tm_smp;
	}

	public void setTm_smp(String tm_smp) {
		this.tm_smp = tm_smp;
	}

	public String getAre_id() {
		return are_id;
	}

	public void setAre_id(String are_id) {
		this.are_id = are_id;
	}

	/**
	 * 生成需要的SQL语句
	 * @return
	 */
	public String getInsertSQL(){
		String insertSQL = "";
		String fmtSQL= "INSERT INTO CMMTHISBUI(HIS_CD, BUI_CD, BUI_NM, BUI_LV, PAR_BUI_CD, SORT_NO,TM_SMP, ARE_ID) \nVALUES('%s','%s','%s','%s','%s','%s','%s','%s');";
		
		insertSQL = String.format(fmtSQL, getHis_cd(), getBui_cd(), getBui_nm(), getBui_lv(), getPar_bui_cd(), getSort_no(), getTm_smp(), getAre_id());
		return insertSQL;
	}
}
