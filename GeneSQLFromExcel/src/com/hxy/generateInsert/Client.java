package com.hxy.generateInsert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.hxy.content.StringHelper;
import com.hxy.generateInsert.table.CMMTHISBUI;
import com.hxy.generateInsert.table.cmmtwlst;

/**
 * 生成insert语句，方便插入数据表，部分会从E:/test.xls位置读取Excel内容以插入。
 * @author hxy
 *
 */
public class Client {

    public static void main(String[] args) {
//		Client.readFromExcelCMMTHISBUI();
        Client.readFromExcelcmmtwlst();
    }

    private static String setParamsCMMTHISBUI(String bui_cd, String bui_nm, String par_bui_cd, String bui_lv, String sort_no) {
        // ����ʱ����ĸ�ʽģ��
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);

        // �������ļ��ж�ȡ����
        String his_cd = Constant.his_cd;
        String are_no = Constant.are_id;

        String tm_smp = dateString;
        String returnValString = "";

        CMMTHISBUI table = new CMMTHISBUI();

        table.setHis_cd(his_cd);
        table.setAre_id(are_no);
        table.setTm_smp(tm_smp);
        table.setPar_bui_cd(par_bui_cd);
        table.setSort_no(sort_no);
        table.setBui_cd(bui_cd);
        table.setBui_nm(bui_nm);
        table.setBui_lv(bui_lv);

        returnValString = returnValString + table.getInsertSQL() + "\n";
        return returnValString;
    }

    /**
     * 从Excel中读取信息生成insert
     * 
     * Excel格式：参考管理网站标准格式
     * @return
     */
    private static void readFromExcelCMMTHISBUI() {
        jxl.Workbook readwb = null;
        try {
            // ����Workbook����, ֻ��Workbook����
            // ֱ�Ӵӱ����ļ�����Workbook
            InputStream instream = new FileInputStream("E:/test.xls");
            readwb = Workbook.getWorkbook(instream);
            // Sheet���±��Ǵ�0��ʼ
            // ��ȡ��һ��Sheet��
            Sheet readsheet = readwb.getSheet(0);
            // ��ȡSheet������������������
            int rsColumns = readsheet.getColumns();
            // ��ȡSheet������������������
            int rsRows = readsheet.getRows();
            int bui_index_lv1 = 0;// һ�������Ľ������
            TreeMap<Integer, Integer> mapLv1 = new TreeMap<Integer, Integer>();
            System.out.println("--===================������һ������======================");
            // ������һ��������
            for (int i = 1; i < rsRows; i++) {
                int j = 0;
                Cell cell = readsheet.getCell(j, i);
                String cellContentsString = StringHelper.Trim(cell.getContents());
                if (!cellContentsString.equals("")) {
                    bui_index_lv1++;

                    // ��������¼��������򱣴�
                    if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
                        Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
                        String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
                        if (!nextLvCellContent.equals("")) {
                            mapLv1.put(i, bui_index_lv1);
                        }
                    }

                    String returnSQL = Client.setParamsCMMTHISBUI(
                            Integer.toString(bui_index_lv1),
                            cellContentsString,
                            "-1",
                            "1",
                            Integer.toString(bui_index_lv1));// һ��������ID��Ϊ-1������Ϊ1�������bui_cd��ͬ��
                    System.out.println(returnSQL);
                }
            }

            System.out.println("--=====================�����Ƕ�������====================");

            int bui_index_lv2 = 0;// ���������Ľ������
            TreeMap<Integer, Integer> mapLv2 = new TreeMap<Integer, Integer>();// ���ڱ������������Ӧ��ϵ��map��key��excel�кţ�Value�����к��¶�Ӧ��bui_cd
            Integer[] keyArrObjectLv1 = mapLv1.keySet().toArray(new Integer[0]);// maplv1��keySetת��Ϊ���״��������
            // �����ɶ���������
            for (int keyIndex = 0; keyIndex < keyArrObjectLv1.length; keyIndex++) {
                int lvAdd = mapLv1.get(keyArrObjectLv1[keyIndex]) * 1000;// bui_cdȨ��

                // �ó���Ҫ�������жεĽ�β
                int endRow = 0;
                if (keyIndex + 1 < keyArrObjectLv1.length) {
                    endRow = keyArrObjectLv1[keyIndex + 1];
                } else {
                    endRow = rsRows;
                }

                // ÿһ���жε�bui_index��Ӧ����
                bui_index_lv2 = 0;

                System.out.println("--------------------------------");
                for (int i = keyArrObjectLv1[keyIndex] + 1; i < endRow; i++) {
                    int j = 1;
                    Cell cell = readsheet.getCell(j, i);
                    String cellContentsString = StringHelper.Trim(cell.getContents());
                    if (!cellContentsString.equals("")) {
                        bui_index_lv2++;

                        // ��������¼��������򱣴�
                        if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
                            Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
                            String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
                            if (!nextLvCellContent.equals("")) {
                                mapLv2.put(i, bui_index_lv2 + lvAdd);
                            }
                        }

                        String returnSQL = Client.setParamsCMMTHISBUI(
                                Integer.toString(bui_index_lv2 + lvAdd),
                                cellContentsString,
                                Integer.toString(mapLv1.get(keyArrObjectLv1[keyIndex])),
                                Integer.toString(j + 1),
                                Integer.toString(bui_index_lv2));
                        System.out.println(returnSQL);
                    }
                }
            }

            System.out.println("--====================��������������=====================");

            // ��������������
            int bui_index_lv3 = 0;// ���������Ľ������
            TreeMap<Integer, Long> mapLv3 = new TreeMap<Integer, Long>();
            Integer[] keyArrObjectLv2 = mapLv2.keySet().toArray(new Integer[0]);

            for (int keyIndex = 0; keyIndex < keyArrObjectLv2.length; keyIndex++) {
                long lvAdd = mapLv2.get(keyArrObjectLv2[keyIndex]) * 1000;
                // �ó���Ҫ�������жεĽ�β
                int endRow = 0;
                if (keyIndex + 1 < keyArrObjectLv2.length) {
                    endRow = keyArrObjectLv2[keyIndex + 1];
                } else {
                    endRow = rsRows;
                }

                // ÿһ���жε�bui_index��Ӧ����
                bui_index_lv3 = 0;

                System.out.println("--------------------------------");
                // ��ȡ��������֮������ݣ�����������
                for (int i = keyArrObjectLv2[keyIndex] + 1; i < endRow; i++) {
                    int j = 2;
                    Cell cell = readsheet.getCell(j, i);
                    String cellContentsString = StringHelper.Trim(cell.getContents());
                    if (!cellContentsString.equals("")) {
                        bui_index_lv3++;

                        // ��������¼��������򱣴�
                        if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
                            Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
                            String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
                            if (!nextLvCellContent.equals("")) {
                                mapLv3.put(i, bui_index_lv3 + lvAdd);
                            }
                        }

                        String returnSQL = Client.setParamsCMMTHISBUI(
                                Long.toString(bui_index_lv3 + lvAdd), cellContentsString,
                                Integer.toString(mapLv2.get(keyArrObjectLv2[keyIndex])),
                                Integer.toString(j + 1),
                                Integer.toString(bui_index_lv3));

                        System.out.println(returnSQL);
                    }
                }
            }

            System.out.println("--=====================�������ļ�����====================");

            // �����ļ�������
            int bui_index_lv4 = 0;// �ļ������Ľ������
            TreeMap<Integer, Long> mapLv4 = new TreeMap<Integer, Long>();
            Integer[] keyArrObjectLv3 = mapLv3.keySet().toArray(new Integer[0]);

            for (int keyIndex = 0; keyIndex < keyArrObjectLv3.length; keyIndex++) {
                long lvAdd = mapLv3.get(keyArrObjectLv3[keyIndex]) * 1000;

                // �ó���Ҫ�������жεĽ�β
                int endRow = 0;
                if (keyIndex + 1 < keyArrObjectLv3.length) {
                    endRow = keyArrObjectLv3[keyIndex + 1];
                } else {
                    endRow = rsRows;
                }

                // ÿһ���жε�bui_index��Ӧ����
                bui_index_lv4 = 0;

                System.out.println("--------------------------------");
                // ��ȡ��������֮������ݣ����ļ�����
                for (int i = keyArrObjectLv3[keyIndex] + 1; i < endRow; i++) {
                    int j = 3;
                    Cell cell = readsheet.getCell(j, i);
                    String cellContentsString = StringHelper.Trim(cell.getContents());
                    if (!cellContentsString.equals("")) {
                        bui_index_lv4++;

                        // ��������¼��������򱣴�
                        if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
                            Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
                            String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
                            if (!nextLvCellContent.equals("")) {
                                mapLv4.put(i, bui_index_lv4 + lvAdd);
                            }
                        }

                        String returnSQL = Client.setParamsCMMTHISBUI(
                                Long.toString(bui_index_lv4 + lvAdd), cellContentsString,
                                Long.toString(mapLv3.get(keyArrObjectLv3[keyIndex])),
                                Integer.toString(j + 1),
                                Integer.toString(bui_index_lv4));
                        System.out.println(returnSQL);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
    }

    /**
     * 配置微信白名单
     * 
     * @return
     */
    private static String setParmascmmtwlst(String usrName, String opnId, String bus_cnl) {
        String his_cd = Constant.his_cd;
        
        cmmtwlst table = new cmmtwlst();
        table.setHis_cd(his_cd);
        table.setOpn_id(opnId);
        table.setUsr_nm(usrName);
        table.setBus_cnl(bus_cnl);
        table.setW_sts("1");
        
        return table.getInsertSQL();
    }
    
    /**
     * 从Excel中读取信息生成insert
     * 
     * Excel格式：usr_nm + opn_id + bus_cnl
     * @return
     */
    private static void readFromExcelcmmtwlst(){
        //select zfb_usr_nm, opn_id, bus_cnl from cmmtusr where his_cd = '40' and bus_cnl = 'ZFB' and zfb_usr_nm is not null;
        //select wx_nic_nm, opn_id, bus_cnl from cmmtusr where his_cd = '27' and wx_nic_nm like '%香芋%';
        jxl.Workbook readwb = null;
        try
        {
            InputStream instream = new FileInputStream("E:/test.xls");
            readwb = Workbook.getWorkbook(instream);
            Sheet readsheet = readwb.getSheet(0);
            int rsColumns = readsheet.getColumns();
            int rsRows = readsheet.getRows();
            for (int i = 0; i < rsRows; i++)
            {
                Cell cell1 = readsheet.getCell(0, i);
                String usr_nm = cell1.getContents();
                Cell cell2 = readsheet.getCell(1, i);
                String opn_id = cell2.getContents();
                Cell cell3 = readsheet.getCell(2, i);
                String bus_cnl = cell3.getContents();

                System.out.println(Client.setParmascmmtwlst(usr_nm, opn_id, bus_cnl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
    }
}
