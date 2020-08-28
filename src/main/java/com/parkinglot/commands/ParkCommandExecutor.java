package com.parkinglot.commands;

import com.parkinglot.exception.NoFreeSpotAvailableException;
import com.parkinglot.model.Car;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;
import com.parkinglot.util.RandomTicketIdUtil;

/**
 * Executor to handle command of parking a car into the parking lot. This outputs the alloted slot
 * in which the car is parked.
 */
public class ParkCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "park";

  public ParkCommandExecutor(
      final ParkingLotService parkingLotService, final DisplayBoard displayBoard) {
    super(parkingLotService, displayBoard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 2;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final Command command) {
    final Car car = new Car(command.getParams().get(0));
    try {
      final Integer slot = parkingLotService.park(car);
      displayBoard.printWithNewLine("Allocated slot number: " + slot);
    } catch (NoFreeSpotAvailableException exception) {
    	displayBoard.parkingLotFull();
    }
  }
}
