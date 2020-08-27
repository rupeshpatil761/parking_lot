package com.parkinglot.model;

import com.parkinglot.constants.ParkingSpotType;

public class MotorBikeSpot extends ParkingSpot {
	public MotorBikeSpot(int number) {
		super(ParkingSpotType.MOTORBIKE, number);
	}
}
