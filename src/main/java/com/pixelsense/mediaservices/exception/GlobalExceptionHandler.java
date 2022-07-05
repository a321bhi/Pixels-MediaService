package com.pixelsense.mediaservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { PostNotFoundException.class })
	public ResponseEntity<String> postNotFoundExceptionHandler() {
		return new ResponseEntity<String>("Post not found or does not exist..", HttpStatus.NOT_FOUND);
	}

}
