package uo.mp.project.monopoly.squares;

import uo.mp.project.monopoly.Player;

public class TaxSquare extends Square {

	private double taxPrice;

	/**
	 * Constructor of the TaxSquare class
	 * 
	 * @param name     Name of the square
	 * @param position Position of the square according to the board
	 * @param taxPrice Price the player must pay if they land in the square
	 */
	public TaxSquare(String name, int position, double taxPrice) {
		super(name, position);
		setTaxPrice(taxPrice);
	}

	/**
	 * Sets the tax price of the square
	 * 
	 * @param taxPrice of the square
	 */
	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}

	/**
	 * Returns the tax price of the square
	 * 
	 * @return price you have to pay when you land
	 */
	public double getTaxPrice() {
		return taxPrice;
	}

	/**
	 * Prints information about the landing place of the player
	 * 
	 * @param player who lands on a specific square
	 */
	public void landedOn(Player player) {
		System.out.println(player.getName() + " lands on " + toString());
		player.payTax(this);
	}

	/**
	 * Returns a formatted string containing information about the tax square
	 * 
	 * @return string with the information about the tax square
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TAX = " + getName() + ", ");
		builder.append("POSITION = " + getPosition() + ", ");
		builder.append("TAX = " + getTaxPrice());
		return builder.toString();
	}
}
