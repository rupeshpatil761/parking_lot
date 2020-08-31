package com.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.InvalidSpotException;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.exception.SpotAlreadyOccupiedException;
import com.parkinglot.util.RandomTicketIdUtil;

//make it singleton
public class ParkingLot {

	private static int MAX_CAPACITY = 100000;
	private int capacity;

	/**
	 * Here we can multiple spots depends on type like compact, motorbike
	 */
	private Map<Integer, ParkingSpot> compactParkingSpots;

	private static ParkingLot parkingLot = null;

	private ParkingLot() {
	}

	public static ParkingLot getInstance(final int capacity) {
		if (parkingLot == null) {
			if (capacity > MAX_CAPACITY || capacity <= 0) {
				throw new ParkingLotException("Invalid capacity given for parking lot.");
			}
			parkingLot = new ParkingLot();
			parkingLot.capacity = capacity;
			parkingLot.compactParkingSpots = new HashMap<>();
		}
		return parkingLot;
	}

	public int getCapacity() {
		return capacity;
	}

	public Map<Integer, ParkingSpot> getCompactParkingSpots() {
		return compactParkingSpots;
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
		final Map<Integer, ParkingSpot> allCompactSpots = getCompactParkingSpots();
		if (!allCompactSpots.containsKey(spotNumber)) {
			allCompactSpots.put(spotNumber, new CompactSpot(spotNumber));
		}
		return allCompactSpots.get(spotNumber);
	}

	/**
	 * Parks a vehicle into a given spot number.
	 *
	 * @param vehicle
	 *            Vehicle to be parked.
	 * @param spotNumber
	 *            Spot number in which it has to be parked.
	 * @return {@link ParkingSpot} if the parking succeeds. If the spot is already
	 *         occupied then {@link SpotAlreadyOccupiedException} is thrown.
	 */
	public ParkingSpot park(final Vehicle vehicle, final Integer spotNumber) {
		final ParkingSpot spot = getParkingSpot(spotNumber);
		if (!spot.isSpotFree()) {
			throw new SpotAlreadyOccupiedException();
		}
		assignNewParkingTicket(vehicle);
		spot.assignVehicle(vehicle);
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

	// Made method as 'synchronized' to allow multiple entrances in future
	public synchronized void assignNewParkingTicket(Vehicle vehicle) {
		ParkingTicket parkingTicket = new ParkingTicket(RandomTicketIdUtil.createTicketId());
		vehicle.assignTicket(parkingTicket);
	}

}
