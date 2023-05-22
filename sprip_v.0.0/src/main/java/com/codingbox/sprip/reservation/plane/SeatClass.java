package com.codingbox.sprip.reservation.plane;

public enum SeatClass {
	
	ECONOMY("이코노미석"), BUSINESS("비즈니스석"), FIRST("일등석");
	
	final private String name;
	
	private SeatClass(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
}
