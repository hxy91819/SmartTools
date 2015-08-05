package com.hxy.generateInsert.table;

public class cmmtwlst {
    private String his_cd;
    private String opn_id;
    private String bus_cnl;
    private String usr_nm;
    private String w_sts;
    private String tm_smp;
    public String getHis_cd() {
        return his_cd;
    }
    public void setHis_cd(String his_cd) {
        this.his_cd = his_cd;
    }
    public String getOpn_id() {
        return opn_id;
    }
    public void setOpn_id(String opn_id) {
        this.opn_id = opn_id;
    }
    public String getBus_cnl() {
        return bus_cnl;
    }
    public void setBus_cnl(String bus_cnl) {
        this.bus_cnl = bus_cnl;
    }
    public String getUsr_nm() {
        return usr_nm;
    }
    public void setUsr_nm(String usr_nm) {
        this.usr_nm = usr_nm;
    }
    public String getW_sts() {
        return w_sts;
    }
    public void setW_sts(String w_sts) {
        this.w_sts = w_sts;
    }
    public String getTm_smp() {
        return tm_smp;
    }
    public void setTm_smp(String tm_smp) {
        this.tm_smp = tm_smp;
    }
    
    public String getInsertSQL(){
        String insertSQL = "";
        String fmtSQL= "INSERT INTO cmmtwlst(his_cd, opn_id, bus_cnl, usr_nm, w_sts, tm_smp) \nVALUES('%s','%s','%s','%s','%s',to_char(sysdate,'YYYYMMDDHH24MISS'));";
        
        insertSQL = String.format(fmtSQL, getHis_cd(), getOpn_id(), getBus_cnl(), getUsr_nm(), getW_sts());
        return insertSQL;
    }
}
