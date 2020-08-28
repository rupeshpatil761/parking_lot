package com.parkinglot;

import java.io.IOException;

import com.parkinglot.commands.CommandExecutorFactory;
import com.parkinglot.exception.InvalidFileException;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;
import com.parkinglot.util.FileUtil;

public class App {
	public static void main(String[] args) throws IOException {
		final DisplayBoard displayBoard = new DisplayBoard();
		final ParkingLotService parkingLotService = new ParkingLotService();
		final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);
		
		if(args.length == 1) {
			new FileUtil(commandExecutorFactory, displayBoard, args[0]).process();
		} else {
			throw new InvalidFileException();
		}
	}
}
