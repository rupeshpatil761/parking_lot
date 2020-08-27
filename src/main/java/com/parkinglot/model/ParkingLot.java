package com.parkinglot.model;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.InvalidSpotException;
import com.parkinglot.exception.ParkingFullException;
import com.parkinglot.exception.ParkingLotException;
import com.parkinglot.exception.SpotAlreadyOccupiedException;

//make it singleton
public class ParkingLot {

	private static int MAX_CAPACITY = 100000;
	private int capacity;

	/**
	 * Here we can multiple spots depends on type like compact, motorbike
	 */
	private Map<Integer, ParkingSpot> compactParkingSpots;

	public int getCapacity() {
		return capacity;
	}

	public Map<Integer, ParkingSpot> getCompactParkingSpots() {
		return compactParkingSpots;
	}

	public ParkingLot(final int capacity) {
		if (capacity > MAX_CAPACITY || capacity <= 0) {
			throw new ParkingLotException("Invalid capacity given for parking lot.");
		}
		this.capacity = capacity;
		this.compactParkingSpots = new HashMap<>();
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
	 * Parks a car into a given spot number.
	 *
	 * @param car
	 *            Car to be parked.
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

	// note that the following method is 'synchronized' to allow multiple entrances
	// panels to issue a new parking ticket without interfering with each other
	public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) {
		/*if (this.isFull(vehicle.getType())) {
			throw new ParkingFullException();
		}*/
		ParkingTicket ticket = new ParkingTicket();
		vehicle.assignTicket(ticket);
		/*this.incrementSpotCount(vehicle.getType());
		this.activeTickets.put(ticket.getTicketNumber(), ticket);*/
		return ticket;
	}

}
