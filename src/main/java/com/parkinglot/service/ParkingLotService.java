package com.parkinglot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.strategy.ParkingStrategy;

public class ParkingLotService {

	private ParkingLot parkingLot;
	private ParkingStrategy parkingStrategy;

	/**
	 * Allots a parking lot into the parking service. Throws
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

	/**
	 * Parks a {@link Vehicle} into the parking lot. {@link ParkingStrategy} is used
	 * to decide the spot number and then the vehicle is parked into the
	 * {@link ParkingLot} spot number.
	 *
	 * @param vehicle
	 *            Vehicle to be parked.
	 * @return Spot number in which the car is parked.
	 */
	public Integer park(final Vehicle vehicle) {
		validateParkingLotExists();
		final Integer nextFreeSpot = parkingStrategy.getNextSpot();
		parkingLot.park(vehicle, nextFreeSpot);
		parkingStrategy.removeSpot(nextFreeSpot);
		return nextFreeSpot;
	}

	/**
	 * Remove a vehicle from a slot. Freed spot number is given back to the parking
	 * strategy so that it becomes available for future parking.
	 *
	 * @param spotNumber
	 *            Spot number to be freed.
	 */
	public void makeSlotFree(final Integer spotNumber) {
		validateParkingLotExists();
		parkingLot.makeSpotFree(spotNumber);
		parkingStrategy.allocateSpot(spotNumber);
	}

	/**
	 * Gets the list of all the slots which are occupied.
	 */
	public List<ParkingSpot> getOccupiedParkingSpots() {
		validateParkingLotExists();
		final List<ParkingSpot> occupiedSpotList = new ArrayList<>();
		// Here we can return occupancy count based on the parking spot type
		final Map<Integer, ParkingSpot> allSlots = parkingLot.getCompactParkingSpots();
		
		
		//try to convert this logic to java 8 streams
		for (int i = 1; i <= parkingLot.getCapacity(); i++) {
			if (allSlots.containsKey(i)) {
				final ParkingSpot slot = allSlots.get(i);
				if (!slot.isSpotFree()) {
					occupiedSpotList.add(slot);
				}
			}
		}
		return occupiedSpotList;
	}
	
	public Optional<ParkingSpot> getMatchingParkingSpots(List<ParkingSpot> list, String vehicleNumber) {
		return list.stream().filter(spot -> spot.getParkedVehicle().getRegistrationNumber().equals(vehicleNumber)).findFirst();
	}
	
	public Optional<ParkingSpot> getMatchingParkingSpots(String vehicleNumber) {
		return this.getOccupiedParkingSpots().stream().filter(spot -> spot.getParkedVehicle().getRegistrationNumber().equals(vehicleNumber)).findFirst();
	}

	private void validateParkingLotExists() {
		if (parkingLot == null) {
			throw new ParkingLotException("Parking lot does not exists to park.");
		}
	}
}
