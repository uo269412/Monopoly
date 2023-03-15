package uo.mp.project.monopoly.squares.property_squares;

public class RailroadSquare extends PropertySquare {

	/**
	 * Constructor for the PropertySquare class
	 * 
	 * @param name         Name of the square
	 * @param position     Position of the square according to the board
	 * @param buyingPrice  Price the square costs if you want to buy it
	 * @param rentingPrice Price the square costs to maintain
	 */
	public RailroadSquare(String name, int position, double buyingPrice, double rentingPrice) {
		super(name, position, buyingPrice, rentingPrice);
	}

	/**
	 * Gets the first line used in the toString method
	 * 
	 * @param stringbuilder that will contain all the information about the square
	 * @return stringbuilder with the added name of the square
	 */
	public StringBuilder getFirstToStringLine(StringBuilder string) {
		return string.append("RAILROAD = " + getName() + ", ");
	}

}
