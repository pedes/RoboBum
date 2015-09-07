/**
 * 
 */
package com.andrespedes.robobum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 * @author Andres.Cespedes.Morales
 * @since 1.8
 * @version 1.0.27
 *
 */
public class Application {

	private static Robot robobum;

	private static int[][] matrix;

	private static List<String> lineas = null;
	private static List<String> amenazas = null;

	public static void initRobot(Position posicion) {
		robobum = new Robot(posicion);
		checkValidPosition();
		checkThreat();
	}

	public static void initMatrix(int x, int y) {
		matrix = new int[x][y];
	}

	public static void initAplication() {
		StringTokenizer stk = new StringTokenizer(lineas.get(0));
		try {
			int xmax = Integer.parseInt(stk.nextToken());
			int ymax = Integer.parseInt(stk.nextToken());
			// Se agrega 1, ya que al ser zero-index no existiria posicion 5,5
			initMatrix(xmax + 1, ymax + 1);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error en las coordenadas maximas");
			return;
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static List<String> convertFileToList(String filename) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("c:/temp", filename));
		} catch (IOException e) {
			return null;
		}
		return lines;
	}

	/**
	 * 
	 */
	public static void checkValidPosition() {
		if (robobum.getPosicion().getCoordX() > matrix[0].length
				|| robobum.getPosicion().getCoordY() > matrix[1].length) {
			JOptionPane.showMessageDialog(null, "El Robot está posicionado fuera de los limites.");
			return;
		}
	}

	public static void positioningRobot(Position position) {
		robobum.setPosicion(position);
		checkValidPosition();
		checkThreat();
	}

	/**
	 * Inserta las amenazas en la matrix o tablero de posiciones. Marcando una
	 * posicion como amenaza con el valor 1.
	 */
	public static void markThreatsInMatrix() {
		for (String string : amenazas) {
			if ("*".equalsIgnoreCase(string.substring(string.length() - 1))) {
				String coords = string.replaceAll("[^0-9,]", "");
				StringTokenizer str = new StringTokenizer(coords, ",");
				if (str.hasMoreTokens()) {
					int amenazaX = Integer.parseInt(str.nextToken());
					int amenazaY = Integer.parseInt(str.nextToken());
					matrix[amenazaX][amenazaY] = 1;
				} else {
					JOptionPane.showMessageDialog(null, "Coordenada de amenaza no valida: " + string);
					return;
				}
			}
		}
	}

	/**
	 * Checkea si el robot está parado en una posición de amenaza y la guarda en
	 * una coleccion
	 */
	public static void checkThreat() {
		if (1 == matrix[robobum.getPosicion().getCoordX()][robobum.getPosicion().getCoordY()]) {
			Position posThreat = formatStringToPosition(
					robobum.getPosicion().getCoordX() + " " + robobum.getPosicion().getCoordY() + " " + "N");
			if (!robobum.getAmenazas().contains(posThreat)) {
				robobum.getAmenazas().add(posThreat);
			}
		}

	}

	/**
	 * Ejecuta los movimientos de una linea del archivo
	 * 
	 * @param c
	 */
	public static void executeMovements(char[] c) {
		for (int i = 0; i < c.length; i++) {
			switch (c[i]) {
			case 'A': {
				robobum.forward();
				checkValidPosition();
				checkThreat();
				break;
			}
			case 'D': {
				robobum.turnsRight();
				break;
			}
			case 'I': {
				robobum.turnsLeft();
				break;
			}
			}
		}
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	public static Position formatStringToPosition(String position) throws NumberFormatException {
		StringTokenizer stk = new StringTokenizer(position);

		int coordx = Integer.parseInt(stk.nextToken());
		int coordy = Integer.parseInt(stk.nextToken());
		String direction = stk.nextToken();
		return new Position(coordx, coordy, direction);

	}

	/**
	 * Imprime las amenazas detectadas
	 */
	public static void printThreats() {
		System.out.print("Amenazas detectadas: ");
		for (Position position : robobum.getAmenazas()) {
			System.out.print("(" + position.getCoordX() + "," + position.getCoordY() + ") ");
		}
		System.out.println();
	}

	/**
	 * 
	 */
	public static void printLastPosition() {
		System.out.println(robobum.getPosicion().getCoordX() + " " + robobum.getPosicion().getCoordY() + " "
				+ robobum.getPosicion().getDirection());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if ((null != (lineas = convertFileToList("test.txt")))
				&& (null != (amenazas = convertFileToList("amenazas.txt")))) {
			try {
				initAplication();
				markThreatsInMatrix();
				// La primer linea es para inicializar la aplicacion
				for (int i = 1; i < lineas.size(); i = i + 2) {
					initRobot(formatStringToPosition(lineas.get(i)));
					executeMovements(lineas.get(i + 1).toCharArray());
					printLastPosition();
					printThreats();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Esta leyendo un archivo inexistente o corrupto.");
		}
	}// ends main

}
