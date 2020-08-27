package com.parkinglot.exception;

public class ParkingFullException extends RuntimeException {

	public ParkingFullException() {
	}

	public ParkingFullException(String message) {
		super(message);
	}

}
