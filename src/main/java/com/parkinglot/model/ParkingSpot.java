package com.parkinglot.model;

import com.parkinglot.constants.ParkingSpotType;

public abstract class ParkingSpot {
	private String number;
	private boolean free;
	private Vehicle vehicle;
	private final ParkingSpotType type;

	public boolean isFree() {
		return this.free;
	};

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
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
