package com.parkinglot.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.parkinglot.commands.CreateParkingLotCommandExecutor;
import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public class CreateParkingLotCommandTest {
	private ParkingLotService parkingLotService;
	private DisplayBoard displayBoard;
	private CreateParkingLotCommandExecutor createParkingLotCommandExecutor;

	@Before
	public void setUp() throws Exception {
		parkingLotService = mock(ParkingLotService.class);
		displayBoard = mock(DisplayBoard.class);
		createParkingLotCommandExecutor = new CreateParkingLotCommandExecutor(parkingLotService, displayBoard);
	}

	@Test
	public void testValidCommand() {
		assertTrue(createParkingLotCommandExecutor.validate(new Command("create_parking_lot 2")));
	}

	@Test
	public void testInvalidCommand() {
		assertFalse(createParkingLotCommandExecutor.validate(new Command("create_parking_lot")));
		assertFalse(createParkingLotCommandExecutor.validate(new Command("create_parking_lot abcd")));
	}
}
