package com.parkinglot.model;

import java.util.Objects;

import com.parkinglot.constants.ParkingSpotType;

public abstract class ParkingSpot {

	private int number;
	private Vehicle vehicle;
	private final ParkingSpotType type;

	public boolean isSpotFree() {
		return Objects.isNull(this.vehicle);
	};

	public ParkingSpot(ParkingSpotType type, int spotNumber) {
		this.type = type;
		this.number = spotNumber;
	}

	public void assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void removeVehicle() {
		this.vehicle = null;
	}

	public Vehicle getParkedVehicle() {
		return vehicle;
	}

	public Integer getSpotNumber() {
		return number;
	}
}
