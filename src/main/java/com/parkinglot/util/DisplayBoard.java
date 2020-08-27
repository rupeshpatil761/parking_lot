package com.parkinglot.util;

public class DisplayBoard {

	public void welcome() {
		printWithNewLine("Welcome to Parking lot.");
	}

	public void end() {
		printWithNewLine("Thanks for using Parking lot service.");
	}

	public void notFound() {
		printWithNewLine("Not found");
	}

	public void createParkingLot(int size) {
		printWithNewLine("Created a parking lot with " + size + " slots");
	}
	
	public void allocateSlot(int slot) {
		printWithNewLine("Allocated slot number: " + slot);
	}

	public void statusHeader() {
		printWithNewLine("Slot No.    Registration No  ");
	}

	public void parkingLotFull() {
		printWithNewLine("Sorry, parking lot is full");
	}

	public void parkingLotEmpty() {
		printWithNewLine("Parking lot is empty");
	}

	public void invalidFile() {
		printWithNewLine("Invalid file given.");
	}

	public void printWithNewLine(final String msg) {
		System.out.println(msg);
	}

}
