package com.parkinglot.model;

import com.parkinglot.constants.VehicleType;

public abstract class Vehicle {

	private String registrationNumber;
	private final VehicleType type;
	private ParkingTicket ticket;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

	public void setTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}
	
	public VehicleType getType() {
		return type;
	}

	public Vehicle(VehicleType type) {
		this.type = type;
	}

	public void assignTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Vehicle [registrationNumber=" + registrationNumber + ", type=" + type + ", ticket=" + ticket + "]";
	}
}
