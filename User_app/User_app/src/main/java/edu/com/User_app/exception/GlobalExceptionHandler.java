package edu.com.User_app.exception;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<LinkedHashMap<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		LinkedHashMap<String , String> map = new LinkedHashMap<String, String>();
		
		List<FieldError> errors = ex.getFieldErrors();
		
		for(FieldError er : errors) {
			String field = er.getField();
			String msg = er.getDefaultMessage();
			map.put(field, msg);
		}
		
		return new ResponseEntity<LinkedHashMap<String , String>>(map,HttpStatus.BAD_REQUEST);
	}
}
