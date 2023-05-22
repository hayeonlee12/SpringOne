package com.codingbox.sprip.reservation.plane;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class TicketFormDTO {
	
	@NotBlank(message="SELECT_ROUNDORNOT")
	private String roundornot;
	
	@Min(value = 1, message="SELECT_RESERVENUM")
	private Integer reservenum;
	
	@NotBlank(message="SELECT_SEATCLASS")
	private String seatclass;
	
	@NotBlank(message="SELECT_STARTPOINT")
	private String startpoint;
	
	@NotBlank(message="SELECT_FINISHPOINT")
	private String finishpoint;
	
	@NotBlank(message="SELECT_DEPARTURE")
	private String startdate;
	
	private String finishdate;
	
}
