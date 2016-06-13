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
	 * 
	 */
	public static final String FACTORY_RESOURCES_NAME = "codeFactory";
	
	/**
	 * 生成文件的用户名
	 */
	public static final String MODEL_AUTHOR_NAME = "model.author.name";
	
	/**
	 * 生成文件的包路径
	 */
	public static final String PROJECT_PACKAGE_BASE = "project.package.base";
	
	/**
	 * 生成javabean文件的包名称
	 */
	public static final String MODEL_PACKAGE_NAME = "model.package.name";
	
	/**
	 * freemark模板路径 相对于项目根路径
	 */
	public static final String FREEMARK_TEMPLATE_PATH = "freemark.template.path";
	
	/**
	 * model模板名称
	 */
	public static final String FREEMARK_TEMPLATE_MODEL = "freemark.template.model.name";
	
}
