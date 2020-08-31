package com.parkinglot.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.parkinglot.commands.StatusCommandExecutor;
import com.parkinglot.model.Car;
import com.parkinglot.model.Command;
import com.parkinglot.model.CompactSpot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public class StatusCommandTest {
	private ParkingLotService parkingLotService;
	private DisplayBoard displayBoard;
	private StatusCommandExecutor statusCommandExecutor;

	@Before
	public void setUp() throws Exception {
		parkingLotService = mock(ParkingLotService.class);
		displayBoard = mock(DisplayBoard.class);
		statusCommandExecutor = new StatusCommandExecutor(parkingLotService, displayBoard);
	}

	@Test
	public void testValidCommand() {
		assertTrue(statusCommandExecutor.validate(new Command("status")));
	}

	@Test
	public void testInvalidCommand() {
		assertFalse(statusCommandExecutor.validate(new Command("status 1")));
		assertFalse(statusCommandExecutor.validate(new Command("status 2 3")));
	}

	@Test
	public void testCommandExecutionWhenParkingLotIsEmpty() {
		List<ParkingSpot> occupiedSlots = new ArrayList<>();
		Mockito.when(parkingLotService.getOccupiedParkingSpots()).thenReturn(occupiedSlots);
		statusCommandExecutor.execute(new Command("status"));
		Mockito.verify(parkingLotService).getOccupiedParkingSpots();
		Mockito.verify(displayBoard).parkingLotEmpty();
	}

	@Test
	public void testCommandExecutionWithOccupiedParkingLot() {
		final CompactSpot spot1 = new CompactSpot(1);
		spot1.assignVehicle(new Car("car-1"));

		final CompactSpot spot2 = new CompactSpot(2);
		spot2.assignVehicle(new Car("car-2"));

		Mockito.when(parkingLotService.getOccupiedParkingSpots()).thenReturn(Arrays.asList(spot1, spot2));

		statusCommandExecutor.execute(new Command("status"));

		Mockito.verify(parkingLotService).getOccupiedParkingSpots();
		Mockito.verify(displayBoard).statusHeader();
		Mockito.verify(displayBoard).printWithNewLine("1           car-1");
		Mockito.verify(displayBoard).printWithNewLine("2           car-2");
	}
}
