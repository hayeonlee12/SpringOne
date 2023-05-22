package com.codingbox.sprip.reservation.travelspot;

public enum Climate {
	
	TROPICAL("열대"), TEMPERATE("온대"), SUBARCTIC("냉대"), POLAR("한대"), DRY("건조");
	
	final private String name;
	
	private Climate(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
}
