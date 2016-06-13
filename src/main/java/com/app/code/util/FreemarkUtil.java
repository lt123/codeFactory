package com.app.code.util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.code.constant.Constans;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FreemarkUtil.class);
	
	/**
	 * 
	 * @param templateName model模板名称 参考com.app.code.constant.Constans
	 * @param dataModel 需要替换数据的javabean
	 * @param distinctPath 文件相对于项目 /src/main/java 的路径 例如：com/app/code/model
	 * @param fileName 文件名称
	 * @param isCover 是否覆盖
	 */
	public static void createTemplate(String templateName,Object dataModel,String dirPath,String fileName,boolean isCover) {
		try {
			// 加载配置文件
			Properties prop = PropertiesUtil.getInstance().load(Constans.FACTORY_RESOURCES_NAME);
			
			// 1、创建freemark的配置对象
			Configuration configuration = new Configuration(Configuration.getVersion());
			// 2、设置模板地址
			configuration.setClassForTemplateLoading(FreemarkUtil.class, prop.getProperty(Constans.FREEMARK_TEMPLATE_PATH));
			// 3、设置编码
			configuration.setDefaultEncoding("utf-8");
			// 4、创建模板对象(根路径是模板地址)
			Template template = configuration.getTemplate(prop.getProperty(templateName));
			// 5、创建需要输出的文件writer
			File file = FileUtil.createFile(dirPath,fileName);
			
			if(!isCover){
				if(file.exists()) {
					System.err.println("文件：" + file.getAbsolutePath() + " 已存在!");
					return;
				}
			}
			Writer out = new FileWriter(file);
			// 6、写数据
			template.process(dataModel, out);
			
			out.flush();
			out.close();
			System.out.println("代码生成成功：" + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("代码生成出错：" + e.getMessage());
		}
	}

}
