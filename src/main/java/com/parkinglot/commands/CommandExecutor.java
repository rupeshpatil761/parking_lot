package com.parkinglot.commands;

import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public abstract class CommandExecutor {
	protected ParkingLotService parkingLotService;
	protected DisplayBoard displayBoard;

	public CommandExecutor(final ParkingLotService parkingLotService, final DisplayBoard displayBoard) {
		this.parkingLotService = parkingLotService;
		this.displayBoard = displayBoard;
	}

	/**
	 * Validates that whether a command is valid to be executed or not.
	 *
	 * @param command
	 *            Command to be validated.
	 * @return Boolean indicating whether command is valid or not.
	 */
	public abstract boolean validate(Command command);

	/**
	 * Executes the command.
	 *
	 * @param command
	 *            Command to be executed.
	 */
	public abstract void execute(Command command);
}
