package com.parkinglot.model;

import java.math.BigDecimal;
import java.util.Date;

import com.parkinglot.constants.ParkingTicketStatus;

public class ParkingTicket {
	
	private String ticketnumber;
	
	private Date issuedAt = new Date();
	
	private Date payedAt;
	
	private BigDecimal paymentAmount;
	
	private ParkingTicketStatus status = ParkingTicketStatus.ACTIVE;
	
	public ParkingTicket(String ticketNumber) {
		this.ticketnumber = ticketNumber;
	}

	public String getTicketnumber() {
		return ticketnumber;
	}

	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getPayedAt() {
		return payedAt;
	}

	public void setPayedAt(Date payedAt) {
		this.payedAt = payedAt;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public ParkingTicketStatus getStatus() {
		return status;
	}

	public void setStatus(ParkingTicketStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ParkingTicket [ticketnumber=" + ticketnumber + ", issuedAt=" + issuedAt + ", payedAt=" + payedAt
				+ ", paymentAmount=" + paymentAmount + ", status=" + status + "]";
	}
}
