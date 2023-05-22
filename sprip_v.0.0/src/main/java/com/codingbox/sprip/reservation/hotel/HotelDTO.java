package com.codingbox.sprip.reservation.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class HotelDTO {
	
	private String addr;
	private String hotelname;
	private int hotelprice;
	private String hotelphone;
	private String hotelplaceid;
	private int reservenum;
	private String reservedate;
	private String startdate;
	private String finishdate;
	private int paymentprice;
	
}
