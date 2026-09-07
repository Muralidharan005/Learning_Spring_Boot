package com.edu.Banking_app.Exception;

public class NotFoundException extends RuntimeException{

	public NotFoundException (String resourceName, String feildName, long feildId) {
		super(resourceName + "not found for" + feildName + "=" +feildId);
	}
}
