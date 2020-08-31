package com.parkinglot.strategy;

import java.util.TreeSet;

import com.parkinglot.exception.NoFreeSpotAvailableException;

/**
 * Parking strategy in which the natural ordering numbers are used for deciding the slot numbers.
 * For example, 1st car will be parked in slot 1, then next in slot 2, then in slot 3, and so on.
 */
public class NearestSpotParkingStrategy implements ParkingStrategy {
  TreeSet<Integer> slotTreeSet;

  public NearestSpotParkingStrategy() {
    this.slotTreeSet = new TreeSet<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void allocateSpot(Integer slotNumber) {
    this.slotTreeSet.add(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSpot(Integer slotNumber) {
    this.slotTreeSet.remove(slotNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getNextSpot() {
    if (slotTreeSet.isEmpty()) {
      throw new NoFreeSpotAvailableException();
    }
    // change here to get the free slot as per requirement. 
    return this.slotTreeSet.first();
  }
}
