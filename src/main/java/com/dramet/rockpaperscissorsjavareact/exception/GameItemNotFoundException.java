package com.dramet.rockpaperscissorsjavareact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle when an item is not found.
 * @author dramet
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Item Not Found")
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
