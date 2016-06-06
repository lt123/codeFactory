package com.app.code.exception;

/**
 * 表没有找到时抛出此异常
 * @author admin
 *
 */
public class TableNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TableNotFoundException(){
		super();
	}
	
	public TableNotFoundException(String s) {
		super(s);
	}
}
