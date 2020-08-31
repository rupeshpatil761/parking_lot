package com.parkinglot.commands;
import java.util.List;

import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.strategy.NearestSpotParkingStrategy;
import com.parkinglot.util.DisplayBoard;
import com.parkinglot.util.ParamValidator;

/**
 * Executor to handle command of creating the initial parking lot.
 */
public class CreateParkingLotCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "create_parking_lot";

  public CreateParkingLotCommandExecutor(
      final ParkingLotService parkingLotService, final DisplayBoard displayBoard) {
    super(parkingLotService, displayBoard);
  }

  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 1) {
      return false;
    }
    return ParamValidator.isInteger(params.get(0));
  }

  @Override
  public void execute(final Command command) {
    final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
    final ParkingLot parkingLot = ParkingLot.getInstance(parkingLotCapacity);
    parkingLotService.createParkingLot(parkingLot, new NearestSpotParkingStrategy());
    displayBoard.createParkingLot(parkingLot.getCapacity());
  }
}
