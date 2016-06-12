package com.app.code.model;

import java.util.Properties;

import com.app.code.constant.Constans;
import com.app.code.util.DateUtil;
import com.app.code.util.PropertiesUtil;

/**
 * 生成javabean所需要的常见属性,属性值都从配置文件中获取(/resources.properties)
 * @author admin
 *
 */
public class PojoModel {
	
	private static PojoModel pojoModel;
	
	/**
	 * 生成包的根路径 比如:com.xx.xx
	 */
	private String basePackage;
	
	/**
	 * 根路径下面model的包名称
	 */
	private String typeModel;
	
	/**
	 * 生成作者名称
	 */
	private String author;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	
	private TableModel tableModel;
	
	private PojoModel(){}
	
	public static PojoModel getInstance(){
		if (pojoModel == null) {
			Properties prop = PropertiesUtil.getInstance().load("resources");
			
			pojoModel = new PojoModel();
			pojoModel.setAuthor(prop.getProperty(Constans.MODEL_AUTHOR_NAME));
			pojoModel.setBasePackage(prop.getProperty(Constans.MODEL_BASE_PACKAGE));
			pojoModel.setTypeModel(prop.getProperty(Constans.MODEL_TYPE));
			pojoModel.setCreateDate(DateUtil.getCurrent());
			
		}
		return pojoModel;
	}

	public static PojoModel getPojoModel() {
		return pojoModel;
	}

	public static void setPojoModel(PojoModel pojoModel) {
		PojoModel.pojoModel = pojoModel;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(String typeModel) {
		this.typeModel = typeModel;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	@Override
	public String toString() {
		return "PojoModel [basePackage=" + basePackage + ", typeModel=" + typeModel + ", author=" + author
				+ ", createDate=" + createDate + ", tableModel=" + tableModel + "]";
	}

	public static void main(String[] args) {
		System.out.println(PojoModel.getInstance());
	}

}
