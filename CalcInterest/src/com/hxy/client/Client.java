package com.hxy.client;

import java.text.DecimalFormat;

public class Client {
	public static void main(String[] args) {
		DecimalFormat dFormat = new DecimalFormat("#.00");//��ʽ�����������ݵ���
		
		double moneyYear = 3150;//ÿ����ɵĽ��
		
		System.out.printf("����ÿ�꽻%s�����ձ�Ϣ���롣", moneyYear);
		System.out.println();
		System.out.println("--------------------");
		
		System.out.println("����:0.04|" + dFormat.format(Client.Calc(moneyYear, 0.04, 20)));
		System.out.println("����:0.05|" + dFormat.format(Client.Calc(moneyYear, 0.05, 20)));
		System.out.println("����:0.0X|" + dFormat.format(Client.Calc(moneyYear, 0.076, 20)));
		System.out.println("����:0.11|" + dFormat.format(Client.Calc(moneyYear, 0.11, 20)));
		System.out.println("����:0.13|" + dFormat.format(Client.Calc(moneyYear, 0.13, 20)));
		
		System.out.println("---------���㳬��15w�����ʺͱ�Ϣ-----------");
		
		for(int i = 0; i < 20; i ++)
		{
			double rate = (double)i /100;
			double sum = Client.Calc(moneyYear, rate, 20);
			if(sum > 150000){
				System.out.println("����15w�����ʺͱ�Ϣ��"+ rate + "|" + dFormat.format(sum));
				break;
			}
		}
		
		System.out.println("---------����������λ�Ĳ��----------");
		
		double moneyYear1 = 2100;
		double moneyYear2 = 3150;
		double moneyYear3 = 4200;
		
		System.out.println(dFormat.format(Client.Calc(moneyYear1, 0.0755, 20) - 100000));
		System.out.println(dFormat.format(Client.Calc(moneyYear2, 0.0755, 20) - 150000));
		System.out.println(dFormat.format(Client.Calc(moneyYear3, 0.0755, 20) - 200000));
	}
	
	/**
	 * ���㱾Ϣ��
	 * @param moneyYear
	 * @param rate
	 * @param years
	 * @return
	 */
	public static double Calc(double moneyYear, double rate, int years){
		double moneySum = moneyYear;
		
		for(int i = 0; i < years; i++){
			moneySum = CalcO(moneySum, rate, moneyYear);
		}
		
		return moneySum;
	}
	
	/**
	 * ԭ�ӣ�Ϊ�����ṩʹ��
	 * @param moneySum
	 * @param rate
	 * @param moneyYear
	 * @return
	 */
	public static double CalcO(double moneySum, double rate, double moneyYear){
		double returnVal = moneySum * (1 + rate) + moneyYear;
/*		System.out.printf("%.2f * (1 + %.2f) + %.2f = %.2f", moneySum, rate, moneyYear,returnVal);
		System.out.println();*/
		return returnVal;
	}
}
