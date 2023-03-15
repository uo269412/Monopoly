package uo.mp.project.monopoly;

import java.util.ArrayList;
import java.util.Scanner;

import uo.mp.project.monopoly.check.Arguments;
import uo.mp.project.monopoly.squares.Square;
import uo.mp.project.monopoly.squares.TaxSquare;
import uo.mp.project.monopoly.squares.property_squares.PropertySquare;

public class Player {

	private String name;
	private int player_square;
	private double money;
	private final static double BASE_MONEY = 1500;
	private ArrayList<PropertySquare> properties;
	private boolean bankrupt;
	private Scanner scanner;

	/**
	 * Constructor of the player class
	 * 
	 * @param name Name the player has
	 */
	public Player(String name) {
		setName(name);
		player_square = 0;
		this.money = BASE_MONEY;
		this.bankrupt = false;
		properties = new ArrayList<PropertySquare>();
	}

	/**
	 * Sets the name of the player
	 * 
	 * @param name of the player
	 */
	private void setName(String name) {
		Arguments.check(name != null);
		this.name = name;
	}

	/**
	 * Returns the name of the player
	 * 
	 * @return String containing the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the position of the player
	 * 
	 * @return Square meaning the square the player is
	 */
	public int getSquare() {
		return player_square;
	}

	/**
	 * This method sets the money a player has
	 * 
	 * @param money the person will have
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * Returns the money the player owns
	 * 
	 * @return double containing all the money the player has
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * Sets the person to be bankrupt or not
	 * 
	 * @param bankrupt boolean value (true if bankrupt, false if not)
	 */
	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
		System.out.println("Player " + getName() + " goes bankrupt");
	}

	/**
	 * Returns true if the person is bankrupt or false if it is not
	 * 
	 * @return boolean value indicating whether or not the player is in bankrupcy or
	 *         not
	 */
	public boolean isBankrupt() {
		return this.bankrupt;
	}

	/**
	 * Modifies the position of the player into the specified square
	 * 
	 * @param square where the new position is
	 */
	public void goTo(Square square) {
		if (square.getPosition() <= this.player_square) {
			passesGO();
		}
		this.player_square = square.getPosition();
		square.landedOn(this);

	}

	/**
	 * Returns a string containing all the information about the players
	 * 
	 * @return String with all the information about the player
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player = " + getName() + ", at square = " + getSquare());
		builder.append(", isBanrkupt() = " + isBankrupt() + ", money = " + getMoney());
		builder.append(", properties = " + getNameOfProperties());
		return builder.toString();
	}

	/**
	 * Gets the name of the owned properties
	 * 
	 * @return String with the name of every property the player owns
	 */
	private String getNameOfProperties() {
		if (properties.size() > 0) {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			int counter = 0;
			for (Square boughtsquare : properties) {
				counter++;
				builder.append(boughtsquare.getName());
				if (properties.size() > counter) {
					builder.append(", ");
				} else if (properties.size() == counter) {
					builder.append("]");
				}
			}
			return builder.toString();
		} else {
			return "[The player owns no squares]";
		}
	}

	/**
	 * If the player lands or passesGo it will receive money
	 */
	public void passesGO() {
		addMoney(200);
	}

	/**
	 * This method makes the player pay the rent of the square if it's one and it's
	 * not of their property
	 * 
	 * @param square which has a owner and a paying rent
	 */
	public void payRent(PropertySquare square) {
		if (getMoney() >= square.getRentingPrice()) {
			enoughCashToPayRent(square);
		} else {
			notEnoughCashToPayRent(square);
		}
	}

	/**
	 * Operates when the player has enough money to pay
	 * 
	 * @param square the other player owns
	 */
	private void enoughCashToPayRent(PropertySquare square) {
		takeMoneyAway(square.getRentingPrice());
		square.getOwner().addMoney(square.getRentingPrice());
	}

	/**
	 * Operates when the player doesn't have the money
	 * 
	 * @param square the player landed on
	 */
	private void notEnoughCashToPayRent(PropertySquare square) {
		double owedCash = square.getRentingPrice();
		setBankrupt(true);
		this.liquidate();
		bankrupcyOwes(square.getOwner(), owedCash);
	}

	/**
	 * Operates when after the player is in bankrupcy
	 * 
	 * @param owner    of the property square
	 * @param owedCash the money the player owes to the other player
	 */
	private void bankrupcyOwes(Player owner, double owedCash) {
		this.takeMoneyAway(owedCash);
		owner.addMoney(owedCash);
		this.payDebts();
	}

	/**
	 * This method makes the player pay the taxes of the square
	 * 
	 * @param square which has a tax price
	 */
	public void payTax(TaxSquare square) {
		if (getMoney() >= square.getTaxPrice()) {
			enoughCashToPayTax(square);
		} else {
			notEnoughCashToPayTax();
		}
	}

	/**
	 * Operates when the player has to pay taxes and has the money
	 * 
	 * @param square the player landed on
	 */
	private void enoughCashToPayTax(TaxSquare square) {
		takeMoneyAway(square.getTaxPrice());
	}

	/**
	 * Operates when the player has to pay taxes but they doesn't have the money
	 * 
	 */
	private void notEnoughCashToPayTax() {
		setBankrupt(true);
		this.liquidate();
		this.payDebts();
	}

	/**
	 * This method makes the player pay debts to the game if it is in bankrupcy
	 */
	public void payDebts() {
		takeMoneyAway(this.getMoney());
	}

	/**
	 * This method allows the player to purchase such property if it's not already
	 * owned and the player has money
	 * 
	 * @param square which the player can buy
	 */
	public void buyProperty(PropertySquare square) {
		this.properties.add(square);
		takeMoneyAway(square.getBuyingPrice());
		square.setStatus(true);
		square.setOwner(this);
	}

	/**
	 * This method takes money away form the player if there is enough
	 * 
	 * @param amount of money that will be taken away
	 */
	public void takeMoneyAway(double amount) {
		setMoney(this.money - amount);
	}

	/**
	 * This method adds money to the player
	 * 
	 * @param amount the player will receive
	 */
	public void addMoney(double amount) {
		setMoney(this.money + amount);
	}

	/**
	 * This method proposes the choice of buying the property to the player who
	 * lands there
	 * 
	 * @param propertySquare where the player lands and has the choice to buy
	 */
	public void wantToBuy(PropertySquare propertySquare) {
		System.out.println("Do you want to buy " + propertySquare.getName() + " for " + propertySquare.getBuyingPrice()
				+ " $ (you have " + getMoney() + " $)?");
		scanner = new Scanner(System.in);
		String val = scanner.next();
		if (val.equalsIgnoreCase("y")) {
			buyProperty(propertySquare);
		}
	}

	/**
	 * This method makes the player liquidate all properties gaining half the buying
	 * price
	 */
	public void liquidate() {
		for (int i = properties.size() - 1; i >= 0; i--) {
			this.addMoney(properties.get(i).getBuyingPrice() / 2);
			liquidateProperty(properties.get(i));
			properties.remove(i);
		}
	}

	/**
	 * Liquidates the property (becomes available again)
	 * 
	 * @param propertySquare we operate with
	 */
	private void liquidateProperty(PropertySquare propertySquare) {
		propertySquare.setOwner(null);
		propertySquare.setStatus(false);

	}

	/**
	 * Calculates the total money a player has after finishing the game
	 * 
	 * @return total money the player has after the game is finished
	 */
	public double sumOfAssets() {
		double result = 0;
		for (PropertySquare square : properties) {
			result += square.getBuyingPrice();
		}
		result += getMoney();
		setMoney(result);
		return result;
	}
}
