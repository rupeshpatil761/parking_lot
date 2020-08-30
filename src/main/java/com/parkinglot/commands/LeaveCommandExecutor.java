package com.parkinglot.commands;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.parkinglot.constants.ParkingTicketStatus;
import com.parkinglot.exception.VehicleNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.util.DisplayBoard;
import com.parkinglot.util.ParamValidator;

/**
 * Executor to handle command of parking a vehicle into the parking lot. This
 * outputs the alloted slot in which the car is parked.
 */
public class LeaveCommandExecutor extends CommandExecutor {
	public static String COMMAND_NAME = "leave";

	public LeaveCommandExecutor(final ParkingLotService parkingLotService, final DisplayBoard displayBoard) {
		super(parkingLotService, displayBoard);
	}

	@Override
	public boolean validate(final Command command) {
		final List<String> params = command.getParams();
	    if (params.size() != 2) {
	      return false;
	    }
		return ParamValidator.isInteger(params.get(1));
	}

	@Override
	public void execute(final Command command) {
		final String vehicleNumber = command.getParams().get(0);
		final Integer numHours = Integer.parseInt(command.getParams().get(1));
		List<ParkingSpot> occupiedSpots = parkingLotService.getOccupiedParkingSpots();
		Optional<ParkingSpot> spotMatchingRegNo = occupiedSpots.stream().filter(spot -> spot.getParkedVehicle().getRegistrationNumber().equals(vehicleNumber)).findFirst();
		int spotNumber = 0;
		double paymentAmount = 10;
		if(spotMatchingRegNo.isPresent()) {
			spotNumber = spotMatchingRegNo.get().getSpotNumber();
			Vehicle vehicle = spotMatchingRegNo.get().getParkedVehicle();
			if(numHours>2) {
				paymentAmount = paymentAmount + 10 *(numHours - 2);
			}
			ParkingTicket ticket = vehicle.getTicket();
			ticket.setPayedAt(new Date());
			ticket.setPaymentAmount(BigDecimal.valueOf(paymentAmount));
			ticket.setStatus(ParkingTicketStatus.PAID);
			System.out.println(vehicle);
		} else {
			throw new VehicleNotFoundException();
		}
		parkingLotService.makeSlotFree(spotNumber);
		String msg = "Registration number "+ vehicleNumber +" with Spot Number "+ spotNumber +" is free with Charge "+ paymentAmount;
		displayBoard.printWithNewLine(msg);
	}
}
