package com.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.InvalidSpotException;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.exception.SpotAlreadyOccupiedException;

public class ParkingLot {

	private static int MAX_CAPACITY = 100000;
	private int capacity;
	private Map<Integer, ParkingSpot> parkingSpots;

	public int getCapacity() {
		return capacity;
	}

	public Map<Integer, ParkingSpot> getSlots() {
		return parkingSpots;
	}

	public ParkingLot(final int capacity) {
		if (capacity > MAX_CAPACITY || capacity <= 0) {
			throw new ParkingLotException("Invalid capacity given for parking lot.");
		}
		this.capacity = capacity;
		this.parkingSpots = new HashMap<>();
	}

	/**
	 * Method to get a {@link ParkingSpot} object for a given spot number. If spot
	 * does not exists, then new spot will be created before giving it back.
	 *
	 * @param spotNumber
	 *            ParkingSpot number.
	 * @return ParkingSpot.
	 */

	private ParkingSpot getParkingSpot(final Integer spotNumber) {
		if (spotNumber > getCapacity() || spotNumber <= 0) {
			throw new InvalidSpotException();
		}
		final Map<Integer, ParkingSpot> allSlots = getSlots();
		if (!allSlots.containsKey(spotNumber)) {
			allSlots.put(spotNumber, new CompactSpot(spotNumber));
		}
		return allSlots.get(spotNumber);
	}

	/**
	 * Parks a car into a given spot number.
	 *
	 * @param car
	 *            Car to be parked.
	 * @param spotNumber
	 *            Spot number in which it has to be parked.
	 * @return {@link ParkingSpot} if the parking succeeds. If the spot is already
	 *         occupied then {@link SpotAlreadyOccupiedException} is thrown.
	 */
	public ParkingSpot park(final Car car, final Integer spotNumber) {
		final ParkingSpot spot = getParkingSpot(spotNumber);
		if (!spot.isSpotFree()) {
			throw new SpotAlreadyOccupiedException();
		}
		spot.assignVehicle(car);
		return spot;
	}

	/**
	 * Makes the spot free from the current parked car.
	 *
	 * @param spotNumber
	 *            Spot number to be freed.
	 * @return Freed spot.
	 */
	public ParkingSpot makeSpotFree(final Integer spotNumber) {
		final ParkingSpot spot = getParkingSpot(spotNumber);
		spot.removeVehicle();
		return spot;
	}

}
