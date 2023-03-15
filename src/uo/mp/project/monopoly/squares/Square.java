package uo.mp.project.monopoly.squares;

import uo.mp.project.monopoly.Player;
import uo.mp.project.monopoly.check.Arguments;

public abstract class Square {
	private String name;
	protected int position;

	public Square(String name, int position) {
		setName(name);
		this.position = position;
	}

	/**
	 * Sets the name of the square
	 * 
	 * @param name of the square
	 */
	public void setName(String name) {
		Arguments.check(name != null);
		this.name = name;
	}

	/**
	 * Returns the name of the square
	 * 
	 * @return string containing the name of the square
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the position of the square
	 * 
	 * @param position of the square
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Returns the position of the square respect to board
	 * 
	 * @return integer containing the specified value
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Prints information about the landing place of the player
	 * 
	 * @param player who lands on a specific square
	 */
	public abstract void landedOn(Player player);

	/**
	 * Returns a formatted string including information about the selected square
	 * 
	 * @return String with the needed information about the square
	 */
	public abstract String toString();
}
