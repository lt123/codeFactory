package com.app.code.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.management.loading.PrivateClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.code.exception.TableNotFoundException;
import com.app.code.model.DbModel;

public class DBCommonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DBCommonUtil.class);
	
	public static Connection getConn(){
		try {
			DbModel dbModel =DbModel.getInstance();
			return DriverManager.getConnection(dbModel.getUrl(),dbModel.getUsername(),dbModel.getPassword());
		} catch (Exception e) {
			logger.error("获取数据库连接出错：",e.getMessage());
		}
		return null;
	}
	
	public static void checkTable(String tableName) {
		try {
			Connection conn = getConn();
			String sql = "show tables";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				if(tableName.equals(rs.getString(1))) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("表不存在:",e.getMessage());
			throw new TableNotFoundException("表>" + tableName + "不存在");
		}finally {
			System.out.println("DBCommonUtil.checkTable()");
		}
	}
	
	public static void test(){
		try {
			System.out.println("DBCommonUtil.test()>>try");
			return;
		} catch (Exception e) {
			System.out.println("DBCommonUtil.test()>>catch");
		}finally {
			System.out.println("DBCommonUtil.test()>>finally");
		}
	}
	public static void main(String[] args) {
//		checkTable("user");
		test();
	}
}
