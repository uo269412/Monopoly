package uo.mp.project.monopoly;

import java.util.ArrayList;
import java.util.List;

import uo.mp.project.monopoly.squares.Square;

public class Board {

	private ArrayList<Square> board;

	/**
	 * Constructor of the board class.
	 */
	public Board() {
		board = new ArrayList<Square>();
	}

	/**
	 * Adds new Squares into the board
	 * 
	 * @param list we want to add in the board
	 */
	public void addSquares(List<Square> list) {
		for (Square element : list) {
			board.add(element);
		}
	}

	/**
	 * Gets the square we are searching
	 * 
	 * @param n the position of the square we are looking for
	 * @return square with the specified position
	 */
	public Square getNthSquare(int n) {
		return board.get(n);
	}

	/**
	 * Returns the number of squares the board has
	 * 
	 * @return number of squares
	 */
	public int getNumberOfSquares() {
		return board.size();
	}
}
