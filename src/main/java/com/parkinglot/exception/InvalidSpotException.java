package com.parkinglot.exception;

public class InvalidSpotException extends RuntimeException {

	public InvalidSpotException() {
	}

	public InvalidSpotException(String message) {
		super(message);
	}
}
