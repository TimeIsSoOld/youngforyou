package com.example.youngforyou.commom.util;

import java.util.regex.Pattern;

public class ValidateUtils {

	/**
	 * 判断是否是整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	
}
