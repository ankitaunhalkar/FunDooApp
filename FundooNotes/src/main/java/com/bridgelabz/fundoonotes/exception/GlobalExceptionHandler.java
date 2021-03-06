package com.bridgelabz.fundoonotes.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = SQLException.class)
	public ResponseEntity<String> handleSqlException(SQLException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);

	}

	@ExceptionHandler(value = DuplicateEmailException.class)
	public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.ALREADY_REPORTED);

	}

	@ExceptionHandler(value = TokenExpiredException.class)
	public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value = RuntimeException.class)
    public	ResponseEntity<String> handleRuntimeException(RuntimeException e){
    	
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		
	}

}
