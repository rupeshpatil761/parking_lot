package com.parkinglot.exception;

public class SpotAlreadyOccupiedException extends RuntimeException {

	public SpotAlreadyOccupiedException() {
	}

	public SpotAlreadyOccupiedException(String message) {
		super(message);
	}

}
