package com.app.code.test;

import com.app.code.model.FlagModel;
import com.app.code.util.CreateUtil;

public class CodeFactoryTest {
	public static void main(String[] args) {
		FlagModel flagModel = new FlagModel();
		flagModel.setCreateModel(false);
		CreateUtil.createCode("user", true, flagModel);
	}
}
