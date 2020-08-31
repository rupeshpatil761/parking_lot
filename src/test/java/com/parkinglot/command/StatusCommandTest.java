package com.parkinglot.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.parkinglot.commands.StatusCommandExecutor;
import com.parkinglot.model.Command;
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

	/*@Test
	public void testCommandExecutionWhenParkingLotIsEmpty() {
		List<Slot> occupiedSlots = new ArrayList<>();
		when(parkingLotService.getOccupiedSlots()).thenReturn(occupiedSlots);
		statusCommandExecutor.execute(new Command("status"));
		verify(parkingLotService).getOccupiedSlots();
		verify(outputPrinter).parkingLotEmpty();
	}*/

	/*@Test
	public void testCommandExecutionWithOccupiedParkingLot() {
		final Slot slot1 = new Slot(1);
		slot1.assignCar(new Car("reg-1", "white"));

		final Slot slot2 = new Slot(2);
		slot2.assignCar(new Car("reg-2", "blue"));

		when(parkingLotService.getOccupiedSlots()).thenReturn(Arrays.asList(slot1, slot2));

		statusCommandExecutor.execute(new Command("status"));

		verify(parkingLotService).getOccupiedSlots();
		verify(outputPrinter).statusHeader();
		verify(outputPrinter).printWithNewLine("1           reg-1              white");
		verify(outputPrinter).printWithNewLine("2           reg-2              blue");
	}*/
}
