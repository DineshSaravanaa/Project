package com.spring.project.config;

import java.io.IOException;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.project.exception.InvalidIDException;
import com.spring.project.exception.InvalidUsernameException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidUsernameException.class)
	public ErrorResponse InvalidUsernameExceptionHandler(InvalidUsernameException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse InvalidIDExceptionHandler(InvalidIDException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public ErrorResponse IOExceptionHandler(IOException e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ErrorResponse ExceptionHandler(Exception e) {
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
}
