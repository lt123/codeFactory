package com.app.code.model;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.code.constant.Constans;

/**
 * 数据库配置
 * @author admin
 *
 */
public class DbModel {
	
	private static DbModel dbModel = null;
	private static Logger logger = LoggerFactory.getLogger(DbModel.class);
	private String url;
	private String driver;
	private String password;
	private String username;
	
	private DbModel(){}
	
	public static synchronized DbModel getInstance() {
		try {
			if(dbModel == null) {
				dbModel = new DbModel();
				InputStream input = DbModel.class.getResourceAsStream(Constans.SYSTEM_JDBC_FILE_NAME);
				Properties prop = new Properties();
				prop.load(input);
				
				dbModel.setDriver(prop.getProperty(Constans.SYSTEM_JDBC_DRIVER));
				dbModel.setUsername(prop.getProperty(Constans.SYSTEM_JDBC_USERNAME));
				dbModel.setPassword(prop.getProperty(Constans.SYSTEM_JDBC_PASSWORD));
				dbModel.setUrl(prop.getProperty(Constans.SYSTEM_JDBC_URL));
			}
		} catch (Exception e) {
			logger.error("初始化数据库配置文件出错:", e.getMessage());
		}
		return dbModel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "DbModel [url=" + url + ", driver=" + driver + ", password=" + password + ", username=" + username + "]";
	}
	
}
