package com.revature.shopmanagement.exception;

public class DataBaseException extends RuntimeException{
	
	private static final long serialVersionUID = -550105279094709335L;

	public DataBaseException(String msg) {
		super(msg);
	}
}