package uo.mp.project.monopoly.squares;

import uo.mp.project.monopoly.Player;

public class NoFeeSquare extends Square {
	/**
	 * Constructor of the noFeeSquare
	 * 
	 * @param name     Name of the square
	 * @param position Position of the square in the board
	 */
	public NoFeeSquare(String name, int position) {
		super(name, position);
	}

	/**
	 * Prints information about the landing place of the player
	 * 
	 * @param player who lands on a specific square
	 */
	@Override
	public void landedOn(Player player) {
		System.out.println(player.getName() + " lands on " + toString());

	}

	/**
	 * Returns a formatted string containing information about the no fee square
	 * 
	 * @return string with the information about the no fee square
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NO FEE SQUARE = " + getName() + ", ");
		builder.append("POSITION = " + getPosition());
		return builder.toString();
	}

}
