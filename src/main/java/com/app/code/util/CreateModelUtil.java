package com.app.code.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 根据表名称生成javabean
 * @author admin
 *
 */
public class CreateModelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CreateModelUtil.class);
	
	public static void createModel(String tableName){
		try {
			Connection conn = DBCommonUtil.getConn();
			String sql = "select * form xx";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			
		} catch (Exception e) {
			logger.error("创建javabean出错:",e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		CreateModelUtil.createModel("");
	}
}
