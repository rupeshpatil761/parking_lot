package com.parkinglot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.parkinglot.commands.ParkCommandExecutor;
import com.parkinglot.exception.NoFreeSpotAvailableException;
import com.parkinglot.model.Car;
import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public class ParkCommandTest {

	private ParkingLotService parkingLotService;
	private DisplayBoard displayBoard;
	private ParkCommandExecutor parkCommandExecutor;

	@Before
	public void setUp() throws Exception {
		parkingLotService = mock(ParkingLotService.class);
		displayBoard = mock(DisplayBoard.class);
		parkCommandExecutor = new ParkCommandExecutor(parkingLotService, displayBoard);
	}

	@Test
	public void testValidCommand() {
		assertTrue(parkCommandExecutor.validate(new Command("park MH-12-SS-3121")));
	}

	@Test
	public void testInvalidCommand() {
		assertFalse(parkCommandExecutor.validate(new Command("park")));
		assertFalse(parkCommandExecutor.validate(new Command("park MH-12-SS-3121 abcd")));
	}

	@Test
	public void testCommandExecutionWhenParkingIsFull() {
		when(parkingLotService.park(any())).thenThrow(new NoFreeSpotAvailableException());
		parkCommandExecutor.execute(new Command("park MH-12-SS-3121"));
		verify(displayBoard).parkingLotFull();
	}
}
