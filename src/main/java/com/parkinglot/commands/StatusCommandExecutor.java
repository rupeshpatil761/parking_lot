package com.parkinglot.commands;

import java.util.List;

import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

/**
 * Executor to handle command of fetching the current status of the parking lot. 
 */
public class StatusCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "status";

  public StatusCommandExecutor(final ParkingLotService parkingLotService,
      final DisplayBoard displayBoard) {
    super(parkingLotService, displayBoard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final Command command) {
    return command.getParams().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(Command command) {
    final List<ParkingSpot> occupiedSlots = parkingLotService.getOccupiedParkingSpots();

    if (occupiedSlots.isEmpty()) {
      displayBoard.parkingLotEmpty();
      return;
    }

    displayBoard.statusHeader();
    for (ParkingSpot slot : occupiedSlots) {
      final Vehicle parkedVehicle = slot.getParkedVehicle();
      final String slotNumber = slot.getSpotNumber().toString();
      displayBoard.printWithNewLine(slotNumber+"           " + parkedVehicle.getRegistrationNumber());
    }
  }
}
