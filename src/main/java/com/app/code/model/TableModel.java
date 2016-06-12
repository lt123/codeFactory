package com.app.code.model;

import java.util.Arrays;

/**
 * 表相关属性的封装
 * @author admin
 *
 */
public class TableModel {
	
	/**
	 * 表名称
	 */
	public String tabName;
	
	/**
	 * 表字段
	 */
	public String[] colName;
	
	/**
	 * 表字段类型
	 */
	public String[] colType;
	
	/**
	 * 表字段描述
	 */
	public String[] colDesc;
	
	/**
	 * 表描述
	 */
	public String tabDesc;
	
	/**
	 * 是否导入util包
	 */
	public boolean flag;

	/**
	 * 
	 * @param columnCount 表字段长度
	 */
	public TableModel(Integer columnCount) {
		this.colName = new String[columnCount];
		this.colType = new String[columnCount];
		this.colDesc = new String[columnCount];
	}

	public String[] getColName() {
		return colName;
	}

	public void setColName(String[] colName) {
		this.colName = colName;
	}

	public String[] getColType() {
		return colType;
	}

	public void setColType(String[] colType) {
		this.colType = colType;
	}

	public String[] getColDesc() {
		return colDesc;
	}

	public void setColDesc(String[] colDesc) {
		this.colDesc = colDesc;
	}

	public String getTabDesc() {
		return tabDesc;
	}

	public void setTabDesc(String tabDesc) {
		this.tabDesc = tabDesc;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	@Override
	public String toString() {
		return "TableModel [tabName=" + tabName + ", colName=" + Arrays.toString(colName) + ", colType="
				+ Arrays.toString(colType) + ", colDesc=" + Arrays.toString(colDesc) + ", tabDesc=" + tabDesc
				+ ", flag=" + flag + "]";
	}

}
