package com.andrespedes.robobum;

import java.util.Set;
import java.util.TreeSet;

public class Robot {

	private Position posicion;

	private Set<Position> amenazas;

	public Robot() {
		posicion = new Position();
		amenazas = new TreeSet<Position>();
	}

	public Robot(Position posicion) {
		this.posicion = posicion;
		amenazas = new TreeSet<Position>();
	}

	public Robot(Position posicion, Set<Position> amenazas) {
		super();
		this.posicion = posicion;
		this.amenazas = amenazas;
	}

	public Position getPosicion() {
		return posicion;
	}

	public void setPosicion(Position posicion) {
		this.posicion = posicion;
	}

	public Set<Position> getAmenazas() {
		return amenazas;
	}

	public void setAmenazas(Set<Position> amenazas) {
		this.amenazas = amenazas;
	}

	/**
	 * 
	 */
	public void turnsLeft() {
		switch (getPosicion().getDirection()) {
		case "E": {
			getPosicion().setDirection("N");
			break;
		}
		case "N": {
			getPosicion().setDirection("O");
			break;
		}
		case "O": {
			getPosicion().setDirection("S");
			break;
		}
		case "S": {
			getPosicion().setDirection("E");
			break;
		}
		}

	}

	/**
	 * 
	 */
	public void turnsRight() {
		switch (getPosicion().getDirection()) {
		case "E": {
			getPosicion().setDirection("S");
			break;
		}
		case "N": {
			getPosicion().setDirection("E");
			break;
		}
		case "O": {
			getPosicion().setDirection("N");
			break;
		}
		case "S": {
			getPosicion().setDirection("O");
			break;
		}
		}
	}

	public void forward() {
		switch (getPosicion().getDirection()) {
		case "E": {
			getPosicion().setCoordX(getPosicion().getCoordX() + 1);
			break;
		}
		case "N": {
			getPosicion().setCoordY(getPosicion().getCoordY() + 1);
			break;
		}
		case "O": {
			getPosicion().setCoordX(getPosicion().getCoordX() - 1);
			break;
		}
		case "S": {
			getPosicion().setCoordY(getPosicion().getCoordY() - 1);
			break;
		}
		}
	}
}
