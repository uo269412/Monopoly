package uo.mp.project.monopoly.squares;

import uo.mp.project.monopoly.Player;

public class GoSquare extends Square {
	/**
	 * Constructor of the goSquare class
	 * 
	 * @param name     Name of the initial square
	 * @param position Position of the initial square
	 */
	public GoSquare(String name, int position) {
		super("GO", 0);
	}

	/**
	 * Prints information about the landing place of the player
	 * 
	 * @param player who lands on a specific square
	 */
	public void landedOn(Player player) {
		System.out.println(player.getName() + " lands on GO");
	}

	/**
	 * Returns a formatted string containing information about the go square
	 * 
	 * @return string with the information about the go square
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GO SQUARE, ");
		builder.append("POSITION = " + getPosition());
		return builder.toString();
	}
}
