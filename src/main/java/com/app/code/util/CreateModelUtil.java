package com.app.code.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			
			TableModel tModel = createTabModel(conn, tableName);
			
			String classContent = createClassContent(tModel);
			
				
		} catch (Exception e) {
			logger.error("创建javabean出错:",e.getMessage());
		}
	}
	
	private static String createClassContent(TableModel tModel) {
		
		return "";
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
				
				// 拼装值
				int i = 0;
				while(columnRs.next()) {
					//判断是否导入util包
					String type = columnRs.getString("TYPE_NAME");
					if("DATETIME".equalsIgnoreCase(type) || "TIMESTAMP".equalsIgnoreCase(type)) {
						tModel.setImportUtil(true);
					}
					
					tModel.getColName()[i] = columnRs.getString("COLUMN_NAME");
					tModel.getColType()[i] = columnRs.getString("TYPE_NAME");
					tModel.getColDesc()[i] = columnRs.getString("REMARKS");
					i++;
				}
				// 拼装表注释
				String sql = "show create table " + tableName;
				ResultSet rs2 = conn.createStatement().executeQuery(sql);
				while (rs2.next()) {
					tModel.setTabDesc(rs2.getString(2).split("COMMENT=")[1].replace("'", ""));
				}
				
				System.out.println(tModel);
				
			}
			
			return tModel;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据表名称创建表对象出错：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * @Title: getComments
	 * @Description: TODO(得到表字段的注释 封装到map里面)
	 * @param table
	 * @return Map<String,String>
	 */
	/*public static Map<String, String> getRemarks(String table) {
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
	}*/
	
	public static void main(String[] args) {
		CreateModelUtil.createModel("user2",true);
	}
}
