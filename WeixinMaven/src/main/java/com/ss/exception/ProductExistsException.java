package com.ss.exception;

public class ProductExistsException extends RuntimeException {
	
	public ProductExistsException(String msg) {
		super(msg);
	}
}
