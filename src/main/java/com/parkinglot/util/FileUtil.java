package com.parkinglot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.parkinglot.commands.CommandExecutor;
import com.parkinglot.commands.CommandExecutorFactory;
import com.parkinglot.exception.InvalidCommandException;
import com.parkinglot.model.Command;

public class FileUtil {

	private String fileName;
	private CommandExecutorFactory commandExecutorFactory;
	private DisplayBoard displayBoard;

	public FileUtil(final CommandExecutorFactory commandExecutorFactory, final DisplayBoard displayBoard,
			final String fileName) {
		this.fileName = fileName;
		this.displayBoard = displayBoard;
		this.commandExecutorFactory = commandExecutorFactory;
	}

	void processCommand(final Command command) {
		final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
		 if (commandExecutor.validate(command)) {
			commandExecutor.execute(command);
		} else {
			throw new InvalidCommandException();
		}
	}

	public void process() throws IOException {
		final File file = new File("file_input.txt");
		final BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			displayBoard.invalidFile();
			return;
		}

		String input = reader.readLine();
		while (input != null) {
			final Command command = new Command(input);
			processCommand(command);
			input = reader.readLine();
		}
	}

}
