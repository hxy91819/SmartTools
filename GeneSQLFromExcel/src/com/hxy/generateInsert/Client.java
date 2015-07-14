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

/**
 * 生成指定规则的Insert语句
 * 
 * @author hxy
 *
 */
public class Client {

	public static void main(String[] args) {
		// System.out.println(Client.getNeedSQL("6001", 7, "3"));
		Client.readFromExcel();
	}

	/**
	 * 根据输入的参数生成相应的Insert SQL并返回
	 * 
	 * @param bui_cd
	 *            建筑编号
	 * @param bui_nm
	 *            建筑名称
	 * @param par_bui_cd
	 *            建筑的父建筑编号
	 * @param bui_lv
	 *            建筑的层级
	 * @param sort_no
	 *            建筑的排序号
	 * @return 生成好的Insert SQL
	 */
	private static String getNeedSQL(String bui_cd, String bui_nm, String par_bui_cd, String bui_lv, String sort_no) {
		// 生成时间戳的格式模版
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);

		// 从配置文件中读取参数
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
	 * 从Excel中读取建筑数据，并打印出Insert SQL TODO 需要改进算法，修改成递归模式，并可以任意读取多级建筑
	 */
	private static void readFromExcel() {
		jxl.Workbook readwb = null;
		try {
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook
			InputStream instream = new FileInputStream("E:/test.xls");
			readwb = Workbook.getWorkbook(instream);
			// Sheet的下标是从0开始
			// 获取第一张Sheet表
			Sheet readsheet = readwb.getSheet(0);
			// 获取Sheet表中所包含的总列数
			int rsColumns = readsheet.getColumns();
			// 获取Sheet表中所包含的总行数
			int rsRows = readsheet.getRows();
			int bui_index_lv1 = 0;// 一级建筑的建筑序号
			TreeMap<Integer, Integer> mapLv1 = new TreeMap<Integer, Integer>();
			System.out.println("--===================以下是一级建筑======================");
			// 先生成一级建筑的
			for (int i = 1; i < rsRows; i++) {
				int j = 0;
				Cell cell = readsheet.getCell(j, i);
				String cellContentsString = StringHelper.Trim(cell.getContents());
				if (!cellContentsString.equals("")) {
					bui_index_lv1++;

					// 如果存在下级建筑，则保存
					if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
						Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
						String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
						if (!nextLvCellContent.equals("")) {
							mapLv1.put(i, bui_index_lv1);
						}
					}

					String returnSQL = Client.getNeedSQL(
							Integer.toString(bui_index_lv1),
							cellContentsString,
							"-1",
							"1",
							Integer.toString(bui_index_lv1));// 一级建筑父ID均为-1，级别为1，序号与bui_cd相同。
					System.out.println(returnSQL);
				}
			}

			System.out.println("--=====================以下是二级建筑====================");

			int bui_index_lv2 = 0;// 二级建筑的建筑序号
			TreeMap<Integer, Integer> mapLv2 = new TreeMap<Integer, Integer>();// 用于保存二级建筑对应关系的map，key：excel行号；Value：此行号下对应的bui_cd
			Integer[] keyArrObjectLv1 = mapLv1.keySet().toArray(new Integer[0]);// maplv1的keySet转化为容易处理的数组
			// 再生成二级建筑的
			for (int keyIndex = 0; keyIndex < keyArrObjectLv1.length; keyIndex++) {
				int lvAdd = mapLv1.get(keyArrObjectLv1[keyIndex]) * 1000;// bui_cd权重

				// 得出需要遍历的行段的结尾
				int endRow = 0;
				if (keyIndex + 1 < keyArrObjectLv1.length) {
					endRow = keyArrObjectLv1[keyIndex + 1];
				} else {
					endRow = rsRows;
				}

				// 每一个行段的bui_index均应重置
				bui_index_lv2 = 0;

				System.out.println("--------------------------------");
				for (int i = keyArrObjectLv1[keyIndex] + 1; i < endRow; i++) {
					int j = 1;
					Cell cell = readsheet.getCell(j, i);
					String cellContentsString = StringHelper.Trim(cell.getContents());
					if (!cellContentsString.equals("")) {
						bui_index_lv2++;

						// 如果存在下级建筑，则保存
						if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
							Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
							String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
							if (!nextLvCellContent.equals("")) {
								mapLv2.put(i, bui_index_lv2 + lvAdd);
							}
						}

						String returnSQL = Client.getNeedSQL(
								Integer.toString(bui_index_lv2 + lvAdd),
								cellContentsString,
								Integer.toString(mapLv1.get(keyArrObjectLv1[keyIndex])),
								Integer.toString(j + 1),
								Integer.toString(bui_index_lv2));
						System.out.println(returnSQL);
					}
				}
			}

			System.out.println("--====================以下是三级建筑=====================");

			// 生成三级建筑的
			int bui_index_lv3 = 0;// 三级建筑的建筑序号
			TreeMap<Integer, Long> mapLv3 = new TreeMap<Integer, Long>();
			Integer[] keyArrObjectLv2 = mapLv2.keySet().toArray(new Integer[0]);

			for (int keyIndex = 0; keyIndex < keyArrObjectLv2.length; keyIndex++) {
				long lvAdd = mapLv2.get(keyArrObjectLv2[keyIndex]) * 1000;
				// 得出需要遍历的行段的结尾
				int endRow = 0;
				if (keyIndex + 1 < keyArrObjectLv2.length) {
					endRow = keyArrObjectLv2[keyIndex + 1];
				} else {
					endRow = rsRows;
				}

				// 每一个行段的bui_index均应重置
				bui_index_lv3 = 0;

				System.out.println("--------------------------------");
				// 读取二级建筑之间的数据，即三级建筑
				for (int i = keyArrObjectLv2[keyIndex] + 1; i < endRow; i++) {
					int j = 2;
					Cell cell = readsheet.getCell(j, i);
					String cellContentsString = StringHelper.Trim(cell.getContents());
					if (!cellContentsString.equals("")) {
						bui_index_lv3++;

						// 如果存在下级建筑，则保存
						if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
							Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
							String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
							if (!nextLvCellContent.equals("")) {
								mapLv3.put(i, bui_index_lv3 + lvAdd);
							}
						}

						String returnSQL = Client.getNeedSQL(
								Long.toString(bui_index_lv3 + lvAdd), cellContentsString,
								Integer.toString(mapLv2.get(keyArrObjectLv2[keyIndex])),
								Integer.toString(j + 1),
								Integer.toString(bui_index_lv3));

						System.out.println(returnSQL);
					}
				}
			}

			System.out.println("--=====================以下是四级建筑====================");

			// 生成四级建筑的
			int bui_index_lv4 = 0;// 四级建筑的建筑序号
			TreeMap<Integer, Long> mapLv4 = new TreeMap<Integer, Long>();
			Integer[] keyArrObjectLv3 = mapLv3.keySet().toArray(new Integer[0]);

			for (int keyIndex = 0; keyIndex < keyArrObjectLv3.length; keyIndex++) {
				long lvAdd = mapLv3.get(keyArrObjectLv3[keyIndex]) * 1000;

				// 得出需要遍历的行段的结尾
				int endRow = 0;
				if (keyIndex + 1 < keyArrObjectLv3.length) {
					endRow = keyArrObjectLv3[keyIndex + 1];
				} else {
					endRow = rsRows;
				}

				// 每一个行段的bui_index均应重置
				bui_index_lv4 = 0;

				System.out.println("--------------------------------");
				// 读取三级建筑之间的数据，即四级建筑
				for (int i = keyArrObjectLv3[keyIndex] + 1; i < endRow; i++) {
					int j = 3;
					Cell cell = readsheet.getCell(j, i);
					String cellContentsString = StringHelper.Trim(cell.getContents());
					if (!cellContentsString.equals("")) {
						bui_index_lv4++;

						// 如果存在下级建筑，则保存
						if ((i + 1 < rsRows) && (j + 1 < rsColumns)) {
							Cell nextLvCell = readsheet.getCell(j + 1, i + 1);
							String nextLvCellContent = StringHelper.Trim(nextLvCell.getContents());
							if (!nextLvCellContent.equals("")) {
								mapLv4.put(i, bui_index_lv4 + lvAdd);
							}
						}

						String returnSQL = Client.getNeedSQL(
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
}
