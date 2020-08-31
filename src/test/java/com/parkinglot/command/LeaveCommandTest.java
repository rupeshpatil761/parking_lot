package com.parkinglot.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.parkinglot.commands.LeaveCommandExecutor;
import com.parkinglot.model.Car;
import com.parkinglot.model.Command;
import com.parkinglot.model.CompactSpot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;

public class LeaveCommandTest {

  private ParkingLotService parkingLotService;
  private DisplayBoard displayBoard;
  private LeaveCommandExecutor leaveCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    displayBoard = mock(DisplayBoard.class);
    leaveCommandExecutor = new LeaveCommandExecutor(parkingLotService, displayBoard);
  }

  @Test
  public void testValidCommand() {
    assertTrue(leaveCommandExecutor.validate(new Command("leave MH-19-FD-1231 3")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(leaveCommandExecutor.validate(new Command("leave")));
    assertFalse(leaveCommandExecutor.validate(new Command("leave MH-19-FD-1231")));
  }

  @Test
  public void testLeavingCarFromSpot() {
	List<ParkingSpot> occupiedSpots = new ArrayList();
	occupiedSpots.add(getParkingSpot());
    Mockito.when(parkingLotService.getOccupiedParkingSpots()).thenReturn(occupiedSpots);
    Optional<ParkingSpot> returnCacheValue = Optional.of((ParkingSpot) getParkingSpot());
    Mockito.<Optional<ParkingSpot>>when(parkingLotService.getMatchingParkingSpots(occupiedSpots, "MH-19-FD-1231")).thenReturn(returnCacheValue);
    leaveCommandExecutor.execute(new Command("leave MH-19-FD-1231 2"));
    Mockito.verify(parkingLotService).getOccupiedParkingSpots();
    String msg = "Registration number MH-19-FD-1231 with Spot Number 1 is free with Charge $"+ 10.0;
    Mockito.verify(displayBoard).printWithNewLine(msg);
  }
  
  private ParkingSpot getParkingSpot() {
	  ParkingSpot spot = new CompactSpot(1);
	  Vehicle vehicle = new Car("MH-19-FD-1231");
	  ParkingTicket ticket = new ParkingTicket("TICKET-31231");
	  vehicle.assignTicket(ticket);
	  spot.assignVehicle(vehicle);
	  return spot;
  }
}
