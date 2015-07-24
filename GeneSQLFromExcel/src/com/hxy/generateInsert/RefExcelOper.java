package com.hxy.generateInsert;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * �����Դ�Excel����Jar��ԭ���������ڲο�
 * @author hxy
 *
 */
public class RefExcelOper

{

	public static void main(String[] args)
	{
		jxl.Workbook readwb = null;
		try
		{
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
			// ��ȡָ����Ԫ��Ķ�������
			for (int i = 0; i < rsRows; i++)
			{
				for (int j = 0; j < rsColumns; j++)
				{
					Cell cell = readsheet.getCell(j, i);
					System.out.print(cell.getContents() + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readwb.close();
		}
	}
}
