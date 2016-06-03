package com.app.code.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.app.code.constant.Constans;

/**
 * 根据表名称生成javabean
 * @author admin
 *
 */
public class CreateModelUtil {
	
	private static Connection conn = null;
	private static Statement stmt = null;
	
	static{
		try {
			InputStream input = CreateModelUtil.class.getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties();
			prop.load(input);
			
			conn = DriverManager.getConnection(
					prop.getProperty(Constans.SYSTEM_JDBC_URL),
					prop.getProperty(Constans.SYSTEM_JDBC_USERNAME),
					prop.getProperty(Constans.SYSTEM_JDBC_PASSWORD));
			stmt = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createModel(String tableName){
		
	}
	
	public static void main(String[] args) {
		
	}
}
