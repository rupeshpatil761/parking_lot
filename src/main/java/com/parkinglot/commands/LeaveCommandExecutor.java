package com.parkinglot.commands;

import com.parkinglot.exception.NoFreeSpotAvailableException;
import com.parkinglot.model.Car;
import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

/**
 * Executor to handle command of parking a vehicle into the parking lot. This
 * outputs the alloted slot in which the car is parked.
 */
public class LeaveCommandExecutor extends CommandExecutor {
	public static String COMMAND_NAME = "leave";

	public LeaveCommandExecutor(final ParkingLotService parkingLotService, final DisplayBoard displayBoard) {
		super(parkingLotService, displayBoard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(final Command command) {
		return command.getParams().size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final Command command) {
		final int spotNumber = Integer.parseInt(command.getParams().get(0));
		parkingLotService.makeSlotFree(spotNumber);
		displayBoard.printWithNewLine("Spot number " + spotNumber + " is free");
	}
}
