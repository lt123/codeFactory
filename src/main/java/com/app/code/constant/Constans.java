package com.app.code.constant;

/**
 * 公共的系统常量
 * @author admin
 *
 */
public interface Constans {
	
	/**
	 * jdbc数据库文件名称
	 */
	public static final String SYSTEM_JDBC_FILE_NAME = "/jdbc.properties";
	
	/**
	 * jdbc数据库连接
	 */
	public static final String SYSTEM_JDBC_URL = "jdbc.url";
	
	/**
	 * jdbc驱动
	 */
	public static final String SYSTEM_JDBC_DRIVER = "jdbc.driver";
	
	/**
	 * 数据库用户名
	 */
	public static final String SYSTEM_JDBC_USERNAME = "jdbc.username";
	
	/**
	 * 数据库密码
	 */
	public static final String SYSTEM_JDBC_PASSWORD = "jdbc.password"; 
	
	/**
	 * 生成文件的用户名
	 */
	public static final String MODEL_AUTHOR_NAME = "model.author.name";
	
	/**
	 * 生成文件的包路径
	 */
	public static final String MODEL_BASE_PACKAGE = "model.base.package";
	
	/**
	 * 生成javabean文件的包名称
	 */
	public static final String MODEL_TYPE = "model.type";
	
	/**
	 * freemark模板路径 相对于项目根路径
	 */
	public static final String FREEMARK_TEMPLATE_PATH = "freemark.template.path";
	
	/**
	 * model模板名称
	 */
	public static final String FREEMARK_TEMPLATE_MODEL_NAME = "freemark.template.model.name";
	
}
