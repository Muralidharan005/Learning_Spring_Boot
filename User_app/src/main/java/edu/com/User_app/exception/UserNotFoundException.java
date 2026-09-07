package edu.com.User_app.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
