package com.example.youngforyou.commom.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class IdGenerater {
	
	public static final String dtLong = "yyyyMMddHHmmssSSS";

	//获取时间+随机数 主键
	public static String getID() {
		return getOrderNum() + getFive();
	}
	
	
	//获取时间+随机数 主键
	public static String getShortID() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("HHmmssSSS");
		return df.format(date) + getFive();
	}

	//获取guid主键
	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase().replaceAll("-", "");
	}

	
	public static String getCookieId() {
		return getGUID();
	}

	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 产生随机的五位数
	 * 
	 * @return
	 */
	public static String getFive() {
		Random rad = new Random();
		return rad.nextInt(10) + ""+rad.nextInt(10)+rad.nextInt(10)+rad.nextInt(10)+rad.nextInt(10);
	}

	

//	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++) {
//			System.out.println(getShortID());
//		}
//	}


}
