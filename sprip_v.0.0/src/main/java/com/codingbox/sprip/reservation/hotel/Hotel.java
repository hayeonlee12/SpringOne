package com.codingbox.sprip.reservation.hotel;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.codingbox.sprip.reservation.Reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "HOTEL_SEQ_GENERATOR",
		sequenceName = "HOTEL_SEQ",
		initialValue = 1, allocationSize = 1 )
public class Hotel {
	
	@Id @Column(name="HOTELID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "HOTEL_SEQ_GENERATOR")
	private Long id;
	private String addr;
	private String hotelname;
	private int hotelprice;
	private String hotelphone;
	private String hotelplaceid; 
	@Embedded
	private Reservation reservation;
	
}
