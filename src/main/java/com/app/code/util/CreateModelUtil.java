package com.app.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.code.constant.Constans;
import com.app.code.model.PojoModel;

/**
 * 根据表名称生成javabean
 * @author admin
 *
 */
public class CreateModelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CreateModelUtil.class);
	
	public static void createModel(PojoModel pojoModel, boolean isCover){
		try {
			// 获取项目根路径
			String relativelyPath = System.getProperty("user.dir");
			// 文件相对于项目 /src/main/java 的路径
			String javaPath = relativelyPath + "/src/main/java/"; // 当前项目java的路径
			// 文件的包路径 
			String packagePath = (pojoModel.getBasePackage() + "." + pojoModel.getTypeModel()).replace(".", "/");
			String fileName = pojoModel.getTableModel().getTabName() + ".java";
			String dirPath = javaPath + packagePath;
			FreemarkUtil.createTemplate(Constans.FREEMARK_TEMPLATE_MODEL, pojoModel, dirPath, fileName, isCover);
		} catch (Exception e) {
			logger.error("创建javabean出错:",e.getMessage());
		}
	}
	
}
