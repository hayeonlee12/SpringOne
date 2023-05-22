package com.codingbox.sprip.reservation.travelspot;

public enum Continent {
	
	OCEANIA("오세아니아"), EUROPE("유럽"), ASIA("아시아"), SOUTH_AMERICA("남아메리카"), NORTH_AMERICA("북아메리카"), AFRICA("아프리카");
	
	final private String name;
	
	private Continent(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
}
