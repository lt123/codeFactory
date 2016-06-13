package com.app.code.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 根据 文件夹路径+文件名称 生成文件
	 * @param dirPath 文件夹路径
	 * @param fileName 文件名称
	 * @return
	 */
	public static File createFile(String dirPath, String fileName) {
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		file = new File(dirPath, fileName);
		return file;
	}
	
}
