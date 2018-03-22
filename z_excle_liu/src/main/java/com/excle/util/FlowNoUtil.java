package com.excle.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FlowNoUtil {

	/**
	 * 
	* @Title: getFlowNo 
	* @Description: 获取流水号主键
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getFlowNo(){
		SimpleDateFormat f1 = new SimpleDateFormat("yyMMddHHmmssSSS");
		String s1 = f1.format(new Date());
		Random rnd1 = new Random();
		int num1 = 100 + rnd1.nextInt(900);
		s1=s1+num1;
		return s1;
	}
	
	public static void main(String[] args) {
		System.out.println(getFlowNo());
	}
}