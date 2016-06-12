package com.app.code.util;

public class CreateUtil {
	
	private CreateUtil(){}
	
	/**
	 * 
	 * @param name 表名称
	 * @param isCover 是否覆盖生成
	 */
	public static void createCode(String tableName,boolean isCover){
		
		// 创建javabean
		CreateModelUtil.createModel(tableName,isCover);
			
	}
	
}
