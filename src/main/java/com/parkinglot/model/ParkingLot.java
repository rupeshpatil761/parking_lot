package com.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.ParkingLotException;

public class ParkingLot {

	private static int MAX_CAPACITY = 100000;
	private int capacity;
	private Map<Integer, ParkingSpot> spots;

	public int getCapacity() {
		return capacity;
	}

	public ParkingLot(final int capacity) {
		if (capacity > MAX_CAPACITY || capacity <= 0) {
			throw new ParkingLotException("Invalid capacity given for parking lot.");
		}
		this.capacity = capacity;
		this.spots = new HashMap<>();
	}

}
