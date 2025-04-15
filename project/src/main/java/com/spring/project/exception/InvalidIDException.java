package com.spring.project.exception;

public class InvalidIDException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7909344217507940501L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InvalidIDException(String message) {
		super();
		this.message = message;
	}

}
