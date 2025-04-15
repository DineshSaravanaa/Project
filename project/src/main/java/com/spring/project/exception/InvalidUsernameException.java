package com.spring.project.exception;

public class InvalidUsernameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007335746501442432L;
	
	
	private String message;


	public InvalidUsernameException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
