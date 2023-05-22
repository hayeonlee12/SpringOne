package com.codingbox.sprip.reservation.plane;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "AIR_SEQ_GENERATOR",
		sequenceName = "AIR_SEQ",
		initialValue = 1, allocationSize = 1 )
public class Air {
	
	@Id @Column(name="AIRLINEID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "AIR_SEQ_GENERATOR")
	private Long id;
	private String airname;
	private String airstate; 
	private String logourl;
	
	@OneToMany(mappedBy = "air", cascade = CascadeType.ALL)
	private List<Aircraft> aircrafts = new ArrayList<>();
	
	public void addAircraft(Aircraft aircraft) {
		this.aircrafts.add(aircraft);
		aircraft.setAir(this);
	}
	
	public Air(String airname, String airstate, String logourl) {
		this.airname = airname;
		this.airstate = airstate;
		this.logourl = logourl;
	}
}
