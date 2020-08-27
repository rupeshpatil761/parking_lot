package com.parkinglot.exception;

public class NoFreeSpotAvailableException extends RuntimeException {

	public NoFreeSpotAvailableException() {
	}

	public NoFreeSpotAvailableException(String message) {
		super(message);
	}
}
