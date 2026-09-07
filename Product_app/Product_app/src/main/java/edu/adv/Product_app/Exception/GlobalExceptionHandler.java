package edu.adv.Product_app.Exception;

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

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<LinkedHashMap<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		
		List<FieldError> l = ex.getFieldErrors();
		
		for(FieldError error:l) {
			String field = error.getField();
			String msg = error.getDefaultMessage();
			map.put(field, msg);
		}
		
		return new ResponseEntity<LinkedHashMap<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
}
