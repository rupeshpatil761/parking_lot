package com.parkinglot.model;

import com.parkinglot.constants.VehicleType;

public class Car extends Vehicle {

	public Car(final String registrationNumber) {
		super(VehicleType.CAR);
	}
}
