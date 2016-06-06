package com.app.codeGenerateFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest {
	
	@Test
	public void testGetPath() throws Exception {
		String relativelyPath=System.getProperty("user.dir"); 
		System.out.println(relativelyPath + System.getProperty("file.separator") + "src\\main\\java\\");
	}
	
	@Test
	public void testCreate() throws Exception {
		
		// 获取项目根路径
		String relativelyPath=System.getProperty("user.dir"); 
		// 获取模板路径
		relativelyPath = relativelyPath + "\\src\\main\\resources\\template";
		
		// 1、创建freemark的配置对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		
		// 2、设置模板地址
		configuration.setDirectoryForTemplateLoading(new File(relativelyPath));
		
		// 3、设置编码
		configuration.setDefaultEncoding("utf-8");
		
		// 4、创建模板对象(根路径是模板地址)
		Template template = configuration.getTemplate("helloworld.ftl");
		
		// 5、创建需要替换字符的map(可以是javabean也可以是map，推荐map)
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("username", "hello world");
		
		// 6、创建需要输出的文件writer
		Writer out = new FileWriter(new File(relativelyPath + "/test.txt"));
		
		// 7、写数据
		template.process(dataModel, out);
		out.flush();
		out.close();
	}
}
