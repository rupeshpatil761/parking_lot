package com.parkinglot.commands;

import java.util.HashMap;
import java.util.Map;

import com.parkinglot.exception.InvalidCommandException;
import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public class CommandExecutorFactory {
	private Map<String, CommandExecutor> commands = new HashMap<>();

	  public CommandExecutorFactory(final ParkingLotService parkingLotService) {
	    final DisplayBoard outputPrinter = new DisplayBoard();
	    
	    /**
	     * Will add all commands in map here
	     */
	    
	  }

	  /**
	   * Basically this method fetch the underlying command executor based on the command passed
	   *
	   * @param command Command for which executor has to be fetched.
	   * @return Command executor.
	   */
	  public CommandExecutor getCommandExecutor(final Command command) {
	    final CommandExecutor commandExecutor = commands.get(command.getCommandName());
	    if (commandExecutor == null) {
	      throw new InvalidCommandException();
	    }
	    return commandExecutor;
	  }
}
