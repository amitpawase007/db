package com.db.interview.example.exceptions;

public class MaturedTradeNotAllowedException extends RuntimeException {

	public MaturedTradeNotAllowedException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
