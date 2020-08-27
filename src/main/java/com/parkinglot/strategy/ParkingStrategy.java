package com.parkinglot.strategy;

public interface ParkingStrategy {
	
	/**
	   * Add a new slot to parking strategy to make it available.
	   *
	   * @param spotNumber Slot number to be added.
	   */
	  public void allocateSpot(Integer spotNumber);
	  
	  /**
	   * Removes a spot from the parking strategy.
	   *
	   * @param spotNumber Slot number to be removed.
	   */
	  public void removeSpot(Integer spotNumber);

	  /**
	   * Get the next free spot as per the parking strategy.
	   *
	   * @return Next free spot number.
	   */
	  public Integer getNextSpot();
}
