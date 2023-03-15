package uo.mp.project.monopoly.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uo.mp.project.monopoly.Player;
import uo.mp.project.monopoly.squares.NoFeeSquare;
import uo.mp.project.monopoly.squares.Square;
import uo.mp.project.monopoly.squares.TaxSquare;
import uo.mp.project.monopoly.squares.property_squares.LotSquare;
import uo.mp.project.monopoly.squares.property_squares.PropertySquare;
import uo.mp.project.monopoly.squares.property_squares.RailroadSquare;
import uo.mp.project.monopoly.squares.property_squares.UtilitySquare;

public class PlayerTest {
	Player player1;
	Player player2;
	Player player3;
	Square square1;
	Square square2;
	Square square3;
	Square square4;
	Square square5;
	Square square6;
	Square square7;
	Square square8;
	Square square9;
	ArrayList<Player> players;

	/**
	 * Sets up the objects to be tested
	 */
	@Before
	public void setUp() throws Exception {
		player1 = new Player("Jorge");
		player2 = new Player("Patricia");
		player3 = new Player("Sara");
		square1 = new NoFeeSquare("TWO", 2);
		square2 = new NoFeeSquare("THREE", 3);
		square3 = new NoFeeSquare("TEN", 10);
		square4 = new TaxSquare("TaxSquare1", 20, 10);
		square5 = new TaxSquare("TaxSquare1", 21, 20);
		square6 = new TaxSquare("TaxSquare1", 22, 30);
		square7 = new RailroadSquare("RailroadSquare", 30, 20, 200);
		square8 = new UtilitySquare("UtilitySquare", 31, 50, 500);
		square9 = new LotSquare("LotSquare", 32, 80, 800);
	}

	/**
	 * Tests the player constructor
	 */
	@Test
	public void testPlayer() {
		Player player4 = new Player("Pedro");
		assertEquals(player4.getName(), "Pedro");
		assertEquals(player4.getSquare(), 0);
		assertEquals(player4.getMoney(), 1500, 0.01);
	}

	/**
	 * Tests the goTo method
	 */
	@Test
	public void testGoTo() {
		System.out.println("***** Test method: goTo *****");
		System.out.println();
		player1.goTo(square1);
		assertEquals(player1.getSquare(), 2);
		player2.goTo(square2);
		assertEquals(player2.getSquare(), 3);
		player3.goTo(square3);
		assertEquals(player3.getSquare(), 10);
		System.out.println();
	}

	/**
	 * Tests the payRent method
	 */
	@Test
	public void testPayRent() {
		System.out.println("***** Test method: payRent *****");
		assertEquals(player1.getMoney(), 1500, 0.01); // Starting money = 1500
		player1.buyProperty((PropertySquare) square7);
		player1.buyProperty((PropertySquare) square8);
		player1.buyProperty((PropertySquare) square9); // Jorge owns all 3 squares
		System.out.println();
		assertEquals(player1.getMoney(), 1350, 0.01); // Jorge ends up with 1350 after buying all those properties
		player1.goTo(square7);
		assertEquals(player1.getMoney(), 1350, 0.01); // Jorge doesn't receive nor give money because he landed on his
														// property
		player2.goTo(square8);
		assertEquals(player2.getMoney(), 1000, 0.01); // Patricia pays 500 $ to Jorge
		player3.goTo(square9);
		assertEquals(player3.getMoney(), 700, 0.01); // Sara pays 800 $ to Jorge
		System.out.println();
	}

	/**
	 * Tests the payTax method
	 */
	@Test
	public void testPayTax() {
		System.out.println("***** Test method: payTax *****");
		System.out.println();
		player1.goTo(square4);
		assertEquals(player1.getMoney(), 1490, 0.01);
		player2.goTo(square5);
		assertEquals(player2.getMoney(), 1480, 0.01);
		player3.goTo(square6);
		assertEquals(player3.getMoney(), 1470, 0.01);
		System.out.println();
	}

	/**
	 * Tests the buyProperty method
	 */
	@Test
	public void testBuyProperty() {
		System.out.println("***** Test method: buyProperty *****");
		System.out.println();
		// Scenario 1: Jorge buys Square7, Patricia lands on it, she can't buy it
		// because it is already owned, so she has to pay
		player1.buyProperty((PropertySquare) square7);
		assertEquals(player1.getMoney(), 1480, 0.01);
		player2.goTo(square7); // Patricia can't buy the property because it is already owned by Jorge
		// Scenario 2: Patricia lands on an available square and she has the money
		player2.setMoney(1500); // We reset Patricia's money
		player2.goTo(square8); // We're asked if we want to buy the square.
		// Scenario 3: Sara lands on an available square but she doesn't have the money
		player3.setMoney(5); // We reduce Sara's money to 5
		player3.goTo(square9); // Sara lands there, but she doesn't get the option to choose since she lacks
								// the money
		System.out.println();

	}

	/**
	 * Tests the liquidate method
	 */
	@Test
	public void testLiquidate() {
		System.out.println();
		player1.buyProperty((PropertySquare) square7); 
		player1.buyProperty((PropertySquare) square8);
		player1.buyProperty((PropertySquare) square9); //Jorge buys all three propertySquares available
		assertEquals(player1.toString(),
				"Player = Jorge, at square = 0, isBanrkupt() = false, money = 1350.0, properties = [RailroadSquare, UtilitySquare, LotSquare]");
		player1.liquidate();  //When Jorge is liquidated, he loses his properties and receives money
		assertEquals(player1.toString(),
				"Player = Jorge, at square = 0, isBanrkupt() = false, money = 1425.0, properties = [The player owns no squares]");
	}

	/**
	 * Tests the bankrupcy in general
	 */
	@Test
	public void testBankrupcy() {
		player2.buyProperty((PropertySquare) square8); //Patricia owns square8
		player1.buyProperty((PropertySquare) square7); //Jorge owns square7
		assertEquals(player2.toString(), "Player = Patricia, at square = 0, isBanrkupt() = false, money = 1450.0, properties = [UtilitySquare]");
		player2.setMoney(150); //We reduce her money so she doesn't have the money to pay the renting price of the square she is going to land
		player2.goTo(square7); //Patricia lands on square7 (she doesn't have the money to pay the rent)
		assertEquals(player1.getMoney(), 1680, 0.01); //Jorge receives the money
		assertEquals(player2.getMoney(), 0, 0.01); //Patricia has money 0 (she is bankrupt)
		System.out.println();
	}
}
