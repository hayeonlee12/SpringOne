package com.codingbox.sprip.reservation.plane;

public enum RoundOrNot {
	
	ROUND("왕복"), ONE_WAY("편도");
	
	final private String name;
	
	private RoundOrNot(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
}
