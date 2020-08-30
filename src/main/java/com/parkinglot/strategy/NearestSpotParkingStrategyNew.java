package com.parkinglot.strategy;

import java.util.TreeSet;

import com.parkinglot.exception.NoFreeSpotAvailableException;
import com.parkinglot.model.ParkingSpot;

/**
 * Parking strategy in which the natural ordering numbers are used for deciding the slot numbers.
 * For example, 1st car will be parked in slot 1, then next in slot 2, then in slot 3, and so on.
 */
public class NearestSpotParkingStrategyNew {
  TreeSet<ParkingSpot> slotTreeSet;

  public NearestSpotParkingStrategyNew() {
    this.slotTreeSet = new TreeSet<>();
  }

  public void allocateSpot(ParkingSpot spot) {
    this.slotTreeSet.add(spot);
  }

  public void removeSpot(ParkingSpot spot) {
    this.slotTreeSet.remove(spot);
  }

  
  public ParkingSpot getNextSpot() {
    if (slotTreeSet.isEmpty()) {
      throw new NoFreeSpotAvailableException();
    }
    return this.slotTreeSet.first();
  }
}
