package com.parkinglot.util;

public class ParamValidator {

	public static boolean isInteger(final String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

}
