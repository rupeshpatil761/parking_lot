package com.parkinglot.model;

import com.parkinglot.constants.ParkingSpotType;

public abstract class ParkingSpot {
	
	private int number;
	private boolean free;
	private Vehicle vehicle;
	private final ParkingSpotType type;

	public boolean isSpotFree() {
		return this.free;
	};

	public ParkingSpot(ParkingSpotType type, int spotNumber ) {
		this.type = type;
		this.number = spotNumber;
	}

	public void assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		free = false;
	}

	public void removeVehicle() {
		this.vehicle = null;
		free = true;
	}
}
