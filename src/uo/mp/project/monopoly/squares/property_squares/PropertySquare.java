package uo.mp.project.monopoly.squares.property_squares;

import uo.mp.project.monopoly.Player;
import uo.mp.project.monopoly.check.Arguments;
import uo.mp.project.monopoly.squares.Square;

public abstract class PropertySquare extends Square {
	private double buyingPrice;
	private boolean status;
	private Player owner;
	private double rentingPrice;

	/**
	 * Constructor for the PropertySquare class
	 * 
	 * @param name         Name of the square
	 * @param position     Position of the square according to the board
	 * @param buyingPrice  Price the square costs if you want to buy it
	 * @param rentingPrice Price the square costs to maintain
	 */
	public PropertySquare(String name, int position, double buyingPrice, double rentingPrice) {
		super(name, position);
		setBuyingPrice(buyingPrice);
		this.status = false;
		setRentingPrice(rentingPrice);

	}

	/**
	 * Sets the buying price of the square
	 * 
	 * @param buying price the square will have
	 */
	public void setBuyingPrice(double buyingPrice) {
		Arguments.check(buyingPrice > 0);
		this.buyingPrice = buyingPrice;
	}

	/**
	 * Sets the status of the square (owned or not)
	 * 
	 * @param status being 0 if it's not owned or 1 otherwise
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Returns the status of the square (owned or not)
	 * 
	 * @return 0 if it's not owned, 1 otherwise
	 */
	public boolean getStatus() {
		return this.status;

	}

	/**
	 * Sets the owner of such square
	 * 
	 * @param owner player who owns the square
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Returns the owner of the square (a player)
	 * 
	 * @return player who owns the square
	 */
	public Player getOwner() {
		return this.owner;

	}

	/**
	 * Sets the renting price of the square
	 * 
	 * @param rentingPrice the square will have
	 */
	public void setRentingPrice(double rentingPrice) {
		Arguments.check(rentingPrice > 0);
		this.rentingPrice = rentingPrice;
	}

	/**
	 * Returns the renting price of the square
	 * 
	 * @return price the square costs to maintain
	 */
	public double getRentingPrice() {
		return this.rentingPrice;

	}

	/**
	 * Returns the buying price of the square
	 * 
	 * @return price the square costs
	 */
	public double getBuyingPrice() {
		return this.buyingPrice;

	}

	/**
	 * Prints information about the landing place of the player
	 * 
	 * @param player who lands on a specific square
	 */
	@Override
	public void landedOn(Player player) {
		System.out.println(player.getName() + " lands on " + toString());
		if (getStatus() == false && player.getMoney() >= getBuyingPrice()) {
			player.wantToBuy(this);
		} else if (getStatus() == true && getOwner() != player) {
			player.payRent(this);
		}
	}

	/**
	 * Returns a string containing information about the property square
	 * 
	 * @return string with all the information provided
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder = getFirstToStringLine(builder);
		builder.append("POSITION = " + getPosition() + ", ");
		builder.append("OWNER = ");
		if (getStatus() == false)
			builder.append("not owned, ");
		else
			builder.append(getOwner().getName() + ", ");
		builder.append("BUYING PRICE = " + getBuyingPrice() + ", ");
		builder.append("RENTING PRICE = " + getRentingPrice());
		return builder.toString();
	}

	/**
	 * Gets the first line used in the toString method
	 * 
	 * @param stringbuilder that will contain all the information about the square
	 * @return stringbuilder with the added name of the square
	 */
	public abstract StringBuilder getFirstToStringLine(StringBuilder string);
}
