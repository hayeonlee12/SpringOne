package com.codingbox.sprip.reservation.travelspot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "TRAVELSPOT_SEQ_GENERATOR",
		sequenceName = "TRAVELSPOT_SEQ",
		initialValue = 1, allocationSize = 1 )
public class TravelSpot {
	
	@Id @Column(name="TRAVELSPOTID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "TRAVELSPOT_SEQ_GENERATOR")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Continent continent;
	
	private String nation;
	private String city;
	
	@Enumerated(EnumType.STRING)
	private Climate climate;

	public TravelSpot(Continent continent, String nation, String city, Climate climate) {
		this.continent = continent;
		this.nation = nation;
		this.city = city;
		this.climate = climate;
	}
	
	
}
