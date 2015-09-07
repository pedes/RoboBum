package com.andrespedes.robobum;

public class Position implements Comparable<Position> {

	private int coordX;

	private int coordY;

	private String direction;

	/**
	 * Default cons
	 */
	public Position() {
		this.coordX = 0;
		this.coordY = 0;
		this.direction = "N";
	}

	public Position(int coordX, int coordY, String direction) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.direction = direction;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public int compareTo(Position o) {
		return Integer.compare(this.getCoordX(), o.getCoordX());
	}

}
