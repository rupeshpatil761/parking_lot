package com.parkinglot.model;

import com.parkinglot.constants.ParkingSpotType;

public class CompactSpot extends ParkingSpot {
	public CompactSpot(int number) {
		super(ParkingSpotType.COMPACT, number);
	}
}
