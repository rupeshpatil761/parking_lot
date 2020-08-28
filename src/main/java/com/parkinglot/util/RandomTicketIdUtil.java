package com.parkinglot.util;

import java.util.Random;

public class RandomTicketIdUtil {
	
    public static String createTicketId() {
    	Random random = new Random();
    	return "Ticket-"+ random.nextInt((10001 - 1) + 1) + 1;
    }

}
