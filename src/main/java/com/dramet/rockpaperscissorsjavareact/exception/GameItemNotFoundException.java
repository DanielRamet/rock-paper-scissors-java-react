package com.dramet.rockpaperscissorsjavareact.exception;

public class GameItemNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;

	public GameItemNotFoundException(String value) {
		super(createMessage(value));
		this.value = value;
	}

	public GameItemNotFoundException(String value, Throwable cause) {
		super(createMessage(value), cause);
		this.value = value;
	}

	public GameItemNotFoundException(String value, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(createMessage(value), cause, enableSuppression, writableStackTrace);
		
	}

	public String getValue() {
		return this.value;
	}
	
	private static String createMessage(String value) {
		return "The item: " + value + " does not exist.";
	}
}
