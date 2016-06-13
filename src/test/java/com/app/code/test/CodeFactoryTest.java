package com.app.code.test;

import com.app.code.util.CreateUtil;

public class CodeFactoryTest {
	public static void main(String[] args) {
		// 默认只生成 model dao service serviceImpl mapper
		CreateUtil.createCode("user", true);
		
		// 如果想自己控制生成的文件 可通过Fileflag来设置
		/*FlagModel flagModel = new FlagModel();
		flagModel.setCreateModel(true);
		CreateUtil.createCode("user", true, flagModel);*/
	}
}
