package com.db.interview.example.exceptions;

public class StaleTradeVersionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2712921044824866364L;

	public StaleTradeVersionException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
