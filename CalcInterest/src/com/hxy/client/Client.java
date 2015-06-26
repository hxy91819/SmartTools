package com.hxy.client;

import java.text.DecimalFormat;

public class Client {
	public static void main(String[] args) {
		DecimalFormat dFormat = new DecimalFormat("#.00");//格式化浮点型数据的类
		
		double moneyYear = 3150;//每年缴纳的金额
		
		System.out.printf("计算每年交%s，最终本息收入。", moneyYear);
		System.out.println();
		System.out.println("--------------------");
		
		System.out.println("利率:0.04|" + dFormat.format(Client.Calc(moneyYear, 0.04, 20)));
		System.out.println("利率:0.05|" + dFormat.format(Client.Calc(moneyYear, 0.05, 20)));
		System.out.println("利率:0.0X|" + dFormat.format(Client.Calc(moneyYear, 0.076, 20)));
		System.out.println("利率:0.11|" + dFormat.format(Client.Calc(moneyYear, 0.11, 20)));
		System.out.println("利率:0.13|" + dFormat.format(Client.Calc(moneyYear, 0.13, 20)));
		
		System.out.println("---------计算超过15w的利率和本息-----------");
		
		for(int i = 0; i < 20; i ++)
		{
			double rate = (double)i /100;
			double sum = Client.Calc(moneyYear, rate, 20);
			if(sum > 150000){
				System.out.println("超过15w的利率和本息："+ rate + "|" + dFormat.format(sum));
				break;
			}
		}
		
		System.out.println("---------计算三个价位的差别----------");
		
		double moneyYear1 = 2100;
		double moneyYear2 = 3150;
		double moneyYear3 = 4200;
		
		System.out.println(dFormat.format(Client.Calc(moneyYear1, 0.0755, 20) - 100000));
		System.out.println(dFormat.format(Client.Calc(moneyYear2, 0.0755, 20) - 150000));
		System.out.println(dFormat.format(Client.Calc(moneyYear3, 0.0755, 20) - 200000));
	}
	
	/**
	 * 计算本息和
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
	 * 原子，为迭代提供使用
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
