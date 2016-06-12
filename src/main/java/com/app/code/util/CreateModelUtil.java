package com.app.code.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.code.constant.Constans;
import com.app.code.model.PojoModel;
import com.app.code.model.TableModel;

/**
 * 根据表名称生成javabean
 * @author admin
 *
 */
public class CreateModelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CreateModelUtil.class);
	
	public static void createModel(String tableName,boolean isCover){
		try {
			// 检查表是否存在
			DBCommonUtil.checkTable(tableName);
			
			Connection conn = DBCommonUtil.getConn();
			
			// 拼装pojo对象
			TableModel tModel = createTabModel(conn, tableName);
			PojoModel pojoModel = PojoModel.getInstance();
			pojoModel.setTableModel(tModel);
			
			// 文件相对于项目 /src/main/java 的路径
			String distinctPath = (pojoModel.getBasePackage() + "." + pojoModel.getTypeModel()).replace(".", "/");
			String fileName = tModel.getTabName() + ".java";
			FreemarkUtil.createTemplate(Constans.FREEMARK_TEMPLATE_MODEL_NAME, pojoModel, distinctPath, fileName, isCover);
		} catch (Exception e) {
			logger.error("创建javabean出错:",e.getMessage());
		}
	}
	
	/**
	 * 根据表名称创建表对象
	 * @param conn
	 * @param tableName
	 * @return
	 */
	private static TableModel createTabModel(Connection conn,String tableName) {
		try {
			TableModel tModel = null;
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rs = dbmd.getTables(conn.getCatalog(), null, "user2", new String[]{"TABLE"});
			while(rs.next()) {
				ResultSet columnRs = dbmd.getColumns(conn.getCatalog(), null, tableName, "%");
				
				// 获取表字段总数
				columnRs.last(); // 移到最后一行
				int columnCount = columnRs.getRow(); // 获取当前行号
				columnRs.beforeFirst(); // 由于后面还需要用结果集，所以把指针初始化
				
				// 初始化表对象
				tModel = new TableModel(columnCount);
				tModel.setTabName(parseTabName(tableName));
				
				// 拼装值
				int i = 0;
				while(columnRs.next()) {
					//判断是否导入util包
					String type = columnRs.getString("TYPE_NAME");
					if("DATETIME".equalsIgnoreCase(type) || "TIMESTAMP".equalsIgnoreCase(type)) {
						tModel.setFlag(true);
					}
					
					tModel.getColName()[i] = columnRs.getString("COLUMN_NAME");
					tModel.getColType()[i] = sqlType2JavaType(columnRs.getString("TYPE_NAME"));
					tModel.getColDesc()[i] = columnRs.getString("REMARKS");
					i++;
				}
				// 拼装表注释
				String sql = "show create table " + tableName;
				ResultSet rs2 = conn.createStatement().executeQuery(sql);
				while (rs2.next()) {
					String[] split = rs2.getString(2).split("COMMENT=");
					if(split.length == 2) { //有注释
						tModel.setTabDesc(split[1].replace("'", ""));
					}else {
						tModel.setTabDesc("");
					}
					
				}
			}
			
			return tModel;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据表名称创建表对象出错：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 将mysql的类型转换为java类型
	 * @param sqlType mysql的类型
	 * @return
	 */
	private static String sqlType2JavaType(String sqlType) {
		if(sqlType.equalsIgnoreCase("bit")){
			return "boolean";
		}else if(sqlType.equalsIgnoreCase("tinyint")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("smallint")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("int")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("bigint")){
			return "Long";
		}else if(sqlType.equalsIgnoreCase("float")){
			return "Float";
		}else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
				|| sqlType.equalsIgnoreCase("smallmoney")){
			return "Double";
		}else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
				|| sqlType.equalsIgnoreCase("text")){
			return "String";
		}else if(sqlType.equalsIgnoreCase("datetime")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("image")){
			return "Blod";
		}else if(sqlType.equalsIgnoreCase("timestamp")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("int unsigned")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("tinyint unsigned")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("mediumint unsigned")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("date")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("smallint unsigned")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("mediumint")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("DECIMAL UNSIGNED")){
			return "BigDecimal";
		}
		return null;
	}

	/**
	 * 格式化表名称 首字母大写 去掉_
	 * @param tableName
	 * @return
	 */
	private static String parseTabName(String tableName) {
		tableName.replace("_", "");
		StringBuilder sb = new StringBuilder(tableName);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	public static void main(String[] args) {
		CreateModelUtil.createModel("user2",true);
	}
}
