package com.codingbox.sprip.reservation.plane;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.codingbox.sprip.reservation.Reservation;
import com.codingbox.sprip.reservation.travelspot.TravelSpot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "TICKET_SEQ_GENERATOR",
		sequenceName = "TICKET_SEQ",
		initialValue = 1, allocationSize = 1 )
public class Ticket {
	
	@Id @Column(name="TICKETID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "TICKET_SEQ_GENERATOR")
	private Long id;
	private int ticketprice;
	private String seatnum;
 
	@Enumerated(EnumType.STRING)
	private SeatClass seatClass;
	
	@ManyToOne @JoinColumn(name="STARTPOINT_ID")
	private TravelSpot startpoint;
	
	@ManyToOne @JoinColumn(name="FINISHPOINT_ID")
	private TravelSpot finishpoint;
 
	@Embedded
	private Reservation reservation;
}
