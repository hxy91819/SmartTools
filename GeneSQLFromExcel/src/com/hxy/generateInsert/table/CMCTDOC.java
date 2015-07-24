package com.hxy.generateInsert.table;

public class CMCTDOC {
    private String his_cd;
    private String dep_cd;
    private String doc_cd;
    private String dep_nm;
    private String doc_nm;
    private String app_cd;
    private String emp_id;
    private String con_flg;
    private String con_fee;
    private String con_num;
    private String doc_sex;
    private String doc_tit;
    private String doc_ski;
    private String doc_rm;
    private String doc_img;
    private String doc_sort_no;
    private String tm_smp;
    private String beg_tm;
    private String end_tm;
    public String getHis_cd() {
        return his_cd;
    }
    public void setHis_cd(String his_cd) {
        this.his_cd = his_cd;
    }
    public String getDep_cd() {
        return dep_cd;
    }
    public void setDep_cd(String dep_cd) {
        this.dep_cd = dep_cd;
    }
    public String getDoc_cd() {
        return doc_cd;
    }
    public void setDoc_cd(String doc_cd) {
        this.doc_cd = doc_cd;
    }
    public String getDep_nm() {
        return dep_nm;
    }
    public void setDep_nm(String dep_nm) {
        this.dep_nm = dep_nm;
    }
    public String getDoc_nm() {
        return doc_nm;
    }
    public void setDoc_nm(String doc_nm) {
        this.doc_nm = doc_nm;
    }
    public String getApp_cd() {
        return app_cd;
    }
    public void setApp_cd(String app_cd) {
        this.app_cd = app_cd;
    }
    public String getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
    public String getCon_flg() {
        return con_flg;
    }
    public void setCon_flg(String con_flg) {
        this.con_flg = con_flg;
    }
    public String getCon_fee() {
        return con_fee;
    }
    public void setCon_fee(String con_fee) {
        this.con_fee = con_fee;
    }
    public String getCon_num() {
        return con_num;
    }
    public void setCon_num(String con_num) {
        this.con_num = con_num;
    }
    public String getDoc_sex() {
        return doc_sex;
    }
    public void setDoc_sex(String doc_sex) {
        this.doc_sex = doc_sex;
    }
    public String getDoc_tit() {
        return doc_tit;
    }
    public void setDoc_tit(String doc_tit) {
        this.doc_tit = doc_tit;
    }
    public String getDoc_ski() {
        return doc_ski;
    }
    public void setDoc_ski(String doc_ski) {
        this.doc_ski = doc_ski;
    }
    public String getDoc_rm() {
        return doc_rm;
    }
    public void setDoc_rm(String doc_rm) {
        this.doc_rm = doc_rm;
    }
    public String getDoc_img() {
        return doc_img;
    }
    public void setDoc_img(String doc_img) {
        this.doc_img = doc_img;
    }
    public String getDoc_sort_no() {
        return doc_sort_no;
    }
    public void setDoc_sort_no(String doc_sort_no) {
        this.doc_sort_no = doc_sort_no;
    }
    public String getTm_smp() {
        return tm_smp;
    }
    public void setTm_smp(String tm_smp) {
        this.tm_smp = tm_smp;
    }
    public String getBeg_tm() {
        return beg_tm;
    }
    public void setBeg_tm(String beg_tm) {
        this.beg_tm = beg_tm;
    }
    public String getEnd_tm() {
        return end_tm;
    }
    public void setEnd_tm(String end_tm) {
        this.end_tm = end_tm;
    }

    /**
     * 生成需要的SQL语句
     * @return
     */
    public String getInsertSQL(){
        String insertSQL = "";
        String fmtSQL= "Insert into CMCTDOC (HIS_CD,DEP_CD,DOC_CD,DEP_NM,DOC_NM,APP_CD,EMP_ID,CON_FLG,CON_FEE,CON_NUM,DOC_SEX,DOC_TIT,DOC_SKI,DOC_RM,DOC_IMG,DOC_SORT_NO,TM_SMP,BEG_TM,END_TM)\n values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');";
        
        insertSQL = String.format(fmtSQL, getHis_cd(), getDep_cd(), getDoc_cd(), getDep_nm(), getDoc_nm(), getApp_cd(), getEmp_id(), 
                getCon_flg(),getCon_fee(), getCon_num(), getDoc_sex(),getDoc_tit(),getDoc_ski(),getDoc_rm(), getDoc_img(), getDoc_sort_no(), getTm_smp(), getBeg_tm(), getEnd_tm());
        return insertSQL;
    }
}
