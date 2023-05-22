package com.codingbox.sprip.reservation.plane;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "AIRCRAFT_SEQ_GENERATOR",
		sequenceName = "AIRCRAFT_SEQ",
		initialValue = 1, allocationSize = 1 )
public class Aircraft {

	@Id @Column(name="AIRCRAFTID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "AIRCRAFT_SEQ_GENERATOR")
	private Long id;
	private String airname;
	
	@ManyToOne @JoinColumn(name="AIRLINEID")
	private Air air;
	
	public void setAir(Air air) {
		this.air = air;
		air.getAircrafts().add(this);
	}

	public Aircraft(String airname) {
		this.airname = airname;
	} 
	
	
}
