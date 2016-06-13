package com.app.code.util;

public class StringUtil {
	/**
	 * 将首字母大写 
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	/**
	 * 删掉 "_" 并且把后面的字母大写 比如 :login_time >> loginTime
	 * @param string
	 * @return
	 */
	public static String replaceStr(String str) {
		String[] fields = str.split("_");
		if (fields.length > 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(fields[0]);
			for (int i = 1; i < fields.length; i++) {
				sb.append(toUpperCase(fields[i].replace("_", "")));
			}
			return sb.toString();
		}else {
			return str;
		}
	}
}
