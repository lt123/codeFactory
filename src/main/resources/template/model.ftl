package ${basePackage};

/**
 * ${tabDesc}
 * @author admin
 *
 */
public class TableModel {
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
	public boolean isImportUtil;

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
	
	public boolean isImportUtil() {
		return isImportUtil;
	}

	public void setImportUtil(boolean isImportUtil) {
		this.isImportUtil = isImportUtil;
	}

	@Override
	public String toString() {
		return "TableModel [colName=" + Arrays.toString(colName) + ", colType=" + Arrays.toString(colType)
				+ ", colDesc=" + Arrays.toString(colDesc) + ", tabDesc=" + tabDesc + ", isImportUtil=" + isImportUtil
				+ "]";
	}
	
}
