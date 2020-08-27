package com.parkinglot.service;

import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.strategy.ParkingStrategy;

public class ParkingLotService {

	private ParkingLot parkingLot;
	private ParkingStrategy parkingStrategy;

	/**
	 * Allots a parking lot into the parking service. Throwns
	 * {@link ParkingLotException} if parkingLot already allocated
	 * 
	 * @param parkingLot
	 * @param parkingStrategy
	 */

	public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
		if (this.parkingLot != null) {
			throw new ParkingLotException("Parking lot already exists.");
		}
		this.parkingLot = parkingLot;
		this.parkingStrategy = parkingStrategy;
		for (int i = 1; i <= parkingLot.getCapacity(); i++) {
			parkingStrategy.allocateSpot(i);
		}
	}
}
