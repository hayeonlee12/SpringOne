package com.codingbox.sprip.reservation;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Reservation {
	
	private int reservenum;
	private String reservedate;
	private String startdate;
	private String finishdate;
	private int paymentprice;
	
	@Enumerated(EnumType.STRING)
	private ReserveStatus status; 
 
}
