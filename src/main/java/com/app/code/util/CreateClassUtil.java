package com.app.code.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreateClassUtil {
		private static Connection conn = null;
		private static PreparedStatement ps = null;
		private static ResultSet rs = null;
		private static Statement stmt = null;
		private static String tableName;// 表名字
		private static String[] colType = null; // 表类型
		private static String[] colName = null; // 表列名
		private static String[] colSize = null; // 列长度
		private static String packname = null; // 列长度
		private static String table_desc = null; // 表描述
		private static boolean f_util;// 是否需要导入util包
		private static String[] remaks;// 列注释
		static {
			try {
				InputStream inputStream = CreateClassUtil.class
						.getResourceAsStream("/config.properties");
				Properties properties = new Properties();
				properties.load(inputStream);
				Class.forName(properties.getProperty("jdbc.driverCls"));
				conn = DriverManager.getConnection(
						properties.getProperty("jdbc.url"),
						properties.getProperty("jdbc.username"),
						properties.getProperty("jdbc.password"));
				stmt = conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private CreateClassUtil() {
		}

		/**
		 * 
		 * @author li tao
		 * @Title: create
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param name
		 *            表名
		 * @param name
		 *            包名
		 */
		public static void create(String name, String packName) {
			tableName = name;
			packname = packName;
			if (!findAllTables()) {
				throw new RuntimeException("表没找到");
			}
			initConfig();// 初始化参数

			getUpper(tableName);// 将表名大写

			String classContent = getClassContent(packName);
			createJavaFile(classContent);
			System.out.println("主键为:"+getParGeneratedKey());
		}

		public static void createJavaFile(String classContent) {
			String path = packname.replace(".", "/");

			String file = CreateClassUtil.class.getResource("/").getPath();
			file = file.substring(1).replace("bin", "src");// 得到src的路径
			File f = new File(file, path);

			try {
				if (!f.exists()) {
					f.mkdirs();
				}
				File javaCls = new File(f.getAbsoluteFile(), getUpper(tableName)
						+ ".java");
				// if (!javaCls.exists()) {
				javaCls.createNewFile();
				// }
				PrintWriter pw = new PrintWriter(javaCls);
				pw.println(classContent);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static boolean findAllTables() {
			try {
				String sql = " SHOW TABLES";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery(sql);
				while (rs.next()) {
					if (tableName.equalsIgnoreCase(rs.getString(1))) {//
						return true;// 有表
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public static void initConfig() {// 初始化数据库里面字段的数据
			try {
				String sql = "SELECT * FROM " + tableName;
				ps = conn.prepareStatement(sql);
				ResultSetMetaData metaData = ps.getMetaData();

				int size = metaData.getColumnCount();
				colType = new String[size];
				colName = new String[size];
				remaks = new String[size];
				Map<String, String> comments = getComments(tableName);

				for (int i = 0; i < size; i++) {
					colName[i] = metaData.getColumnName(i + 1);
					colType[i] = metaData.getColumnTypeName(i + 1);
					if (colType[i].equalsIgnoreCase("datetime")) {
						f_util = true;
					}
					if (colType[i].equalsIgnoreCase("timestamp")) {
						f_util = true;
					}
					colType[i] = sqlToJavaType(metaData.getColumnTypeName(i + 1));
					remaks[i] = comments.get(colName[i]);

				}

				String descSql = "SHOW CREATE TABLE " + tableName;
				rs = conn.createStatement().executeQuery(descSql);
				if (rs != null && rs.next()) {
					String create = rs.getString(2);// 得到2个参数 第一个是表名 第二个才是表描述
					table_desc = parse(create);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String parse(String create) {
			String[] s = create.split("COMMENT='");
			if (s.length > 1) {// 有注释
				return s[1].replace("'", "");// 把最后面的 ' 去掉
			}
			return null;
		}

		/**
		 * 功能：获得列的数据类型
		 * 
		 * @param sqlType
		 *            数据库中字段的类型
		 * @return
		 */
		public static String sqlToJavaType(String sqlType) {
			if (sqlType.equalsIgnoreCase("bit")) {
				return "boolean";
			} else if (sqlType.equalsIgnoreCase("tinyint")) {
				return "Integer";
			} else if (sqlType.equalsIgnoreCase("smallint")) {
				return "Integer";
			} else if (sqlType.equalsIgnoreCase("int")) {
				return "Integer";
			} else if (sqlType.equalsIgnoreCase("bigint")) {
				return "Long";
			} else if (sqlType.equalsIgnoreCase("float")) {
				return "float";
			} else if (sqlType.equalsIgnoreCase("decimal")
					|| sqlType.equalsIgnoreCase("numeric")
					|| sqlType.equalsIgnoreCase("real")
					|| sqlType.equalsIgnoreCase("money")
					|| sqlType.equalsIgnoreCase("smallmoney")) {
				return "double";
			} else if (sqlType.equalsIgnoreCase("varchar")
					|| sqlType.equalsIgnoreCase("char")
					|| sqlType.equalsIgnoreCase("nvarchar")
					|| sqlType.equalsIgnoreCase("nchar")
					|| sqlType.equalsIgnoreCase("text")) {
				return "String";
			} else if (sqlType.equalsIgnoreCase("datetime")) {
				return "Date";
			} else if (sqlType.equalsIgnoreCase("image")) {
				return "Blod";
			} else if (sqlType.equalsIgnoreCase("timestamp")) {
				return "Date";
			} else if (sqlType.equalsIgnoreCase("int unsigned")) {
				return "Integer";
			} else if (sqlType.equalsIgnoreCase("tinyint unsigned")) {
				return "Integer";
			}
			return null;
		}

		/**
		 * @Title: getComments
		 * @Description: TODO(得到表字段的注释 封装到map里面)
		 * @param table
		 * @return Map<String,String>
		 */
		public static Map<String, String> getComments(String table) {
			Map<String, String> column_infoMap = new HashMap<>();
			try {
				DatabaseMetaData dbmd = conn.getMetaData();
				ResultSet resultSet = dbmd.getTables(null, "%", "%",
						new String[] { "TABLE" });
				while (resultSet.next()) {
					String tableName = resultSet.getString("TABLE_NAME");
					if (tableName.equals(table)) {
						ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
						while (rs.next()) {
							column_infoMap.put(rs.getString("COLUMN_NAME"),
									rs.getString("REMARKS"));

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return column_infoMap;
		}

		public static String getClassContent(String packName) {
			StringBuffer sb = new StringBuffer();
			sb.append("package " + packName + ";\r\n\r\n");// 添加包名

			if (f_util) {
				sb.append("\t import java.util.*;\r\n\r\n");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// 注释部分
			sb.append("\t/**\r\n");
			sb.append("\t* @TableName: " + tableName + " \r\n");
			sb.append("\t* @Package: " + packName + " \r\n");
			sb.append("\t* @Title:" + initcap(tableName) + ".java \r\n");
			sb.append("\t* @Description: " + table_desc + " \r\n");
			sb.append("\t* @date: " + sdf.format(new Date()) + " \r\n");
			sb.append("\t* @version V1.0    \r\n");
			sb.append("\t*/ \r\n");
			sb.append("\tpublic class " + getUpper(tableName) + "{\r\n");// 添加类名

			// 字段
			appendField(sb);
			// get set
			appendMethod(sb);
			// toString
			appendToString(sb);
			sb.append("\t}");
			return sb.toString();
		}

		/**
		 * @author li tao
		 * @Title: appendToString
		 * @Description: TODO(model的toString方法)
		 * @param sb
		 *            void
		 */
		public static void appendToString(StringBuffer sb) {
			sb.append("\t\tpublic String toString() {\r\n");
			sb.append("\t\t\tStringBuffer stringBuffer = new StringBuffer();\r\n");
			sb.append("\t\t\tstringBuffer.append(\"" + initcap(tableName)
					+ "=[\");\r\n");
			for (int i = 0; i < colName.length; i++) {
				sb.append("\t\t\tif(" + colName[i] + "!=null){\r\n");
				sb.append("\t\t\t\tstringBuffer.append(\"" + colName[i] + "=\"+"
						+ colName[i] + ");\r\n");
				sb.append("\t\t\t}\r\n");
			}
			sb.append("\t\t\tstringBuffer.append(\"]\");").append("\r\n");
			sb.append("\t\t\treturn stringBuffer.toString();\r\n");
			sb.append("\t\t}\r\n");
		}

		/**
		 * @author li tao
		 * @Title: appendMethod
		 * @Description: TODO(生成get set 方法)
		 * @param sb
		 *            void
		 */
		public static void appendMethod(StringBuffer sb) {
			sb.append("\r\n");
			// get
			for (int i = 0; i < colName.length; i++) {
				sb.append("\t\tpublic ").append(colType[i] + " ").append("get")
						.append(getUpper(colName[i])).append("(){\r\n");
				sb.append("\t\t\treturn ").append(colName[i]).append(";\r\n");
				sb.append("\t\t}").append("\r\n").append("\r\n");
			}
			// set
			for (int i = 0; i < colName.length; i++) {
				sb.append("\t\tpublic void set").append(getUpper(colName[i]))
						.append("(" + colType[i] + " " + colName[i] + "){\r\n");
				sb.append("\t\t\tthis.").append(colName[i]).append(" = ")
						.append(colName[i]).append(";\r\n");
				sb.append("\t\t}\r\n").append("\r\n");
			}
		}

		public static void appendField(StringBuffer sb) {
			for (int i = 0; i < colName.length; i++) {
				sb.append("\t\t/**\r\n");
				sb.append("\t\t*@Fields ").append(colName[i]).append("\r\n");// 字段名
				if (!"".equals(remaks[i]) && remaks[i] != null) {
					sb.append("\t\t*\t  ").append(remaks[i]).append("\r\n");// 字段数据库注释
				}
				sb.append("\t\t*/\r\n");
				sb.append("\t\tprivate ").append(colType[i]).append(" ")
						.append(colName[i]).append(";\r\n");
			}
		}

		/**
		 * @return 把字符串首字母小写
		 */
		public static String initcap(String tableName) {
			String head = tableName.substring(0, 1).toLowerCase();
			tableName = head + tableName.substring(1);
			return tableName;
		}

		/**
		 * @return 把字符串首字母大写
		 */
		public static String getUpper(String str) {
			String head = str.substring(0, 1).toUpperCase();
			str = head + str.substring(1);
			return str;
		}
		
		/**
		 * 得到主键
		 */
		public static String getParGeneratedKey(){
			String primaryKey = "";
			try {
				String sql = "SELECT * FROM "+tableName;
				ps = conn.prepareStatement(sql);
				ResultSetMetaData metaData = ps.getMetaData();
				//查出来的第一个就是主键
				primaryKey =  metaData.getColumnName(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return primaryKey;
		}
}
