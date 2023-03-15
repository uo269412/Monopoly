package uo.mp.project.monopoly;

import java.util.Arrays;
import java.util.List;

import uo.mp.project.monopoly.squares.GoSquare;
import uo.mp.project.monopoly.squares.NoFeeSquare;
import uo.mp.project.monopoly.squares.Square;
import uo.mp.project.monopoly.squares.TaxSquare;
import uo.mp.project.monopoly.squares.property_squares.LotSquare;
import uo.mp.project.monopoly.squares.property_squares.RailroadSquare;
import uo.mp.project.monopoly.squares.property_squares.UtilitySquare;

public class Main {
	private Game game;

	public static void main(String[] args) {
		new Main().configure().run();
	}
		
	private  Main configure() {
		// Creates a board, creates squares and add squares to the board
		Board board = new Board();
		board.addSquares(createSquares());
		// Creates a game
		game = new Game();
		// Add a board to the game
		game.addBoard( board );
		// Creates players Bob, Alice and Eve and add them to the game
		game.addPlayers( createPlayers() );
		return this;
	}

	/** 
	 * Run the game
	 */
	private void run() {
		game.play();
	}
	
	/**
	 * Create Players
	 * @return
	 */
	private List<Player> createPlayers() {
		return Arrays.asList( new Player[] {
				new Player("Bob"),
				new Player("Alice"),
				new Player("Eve")
			});
	}

	/**
	 * Create squares for the board
	 */
	private List<Square> createSquares() {
		return Arrays.asList(new Square[] { 
			new GoSquare("GO", 0),
			new LotSquare("Mediterranean Ave", 1, 60, 2),
			new NoFeeSquare("Golden Gate Park", 2),
			new LotSquare("Baltic Ave", 3, 60, 4),
			new TaxSquare("Income tax", 4, 200),
			new RailroadSquare("Reading Railroad", 5, 200, 20),
			new LotSquare("Oriental Ave", 6, 100, 6),
			new NoFeeSquare("Central Park", 7),
			new LotSquare("Vermont Ave", 8, 100, 6),
			new LotSquare("Connecticut Ave", 9, 120, 8),
			new NoFeeSquare("Tivoli Gardens", 10),
			new LotSquare("St. Charles Place", 11, 140, 10),
			new UtilitySquare("Electric Company", 12, 150, 15),
			new LotSquare("States Ave", 13, 140, 10),
			new LotSquare("Virginia Ave", 14, 160, 12),
			new RailroadSquare("Pennsylvania Railroad", 15, 200, 20),
			new LotSquare("St. James Place", 16, 180, 14),
			new NoFeeSquare("Hyde Park", 17),
			new LotSquare("Tennessee Ave", 18, 180, 14),
			new LotSquare("New York Ave", 19, 200, 16),
			new NoFeeSquare("FreeParking", 20),
			new LotSquare("Kentucky Ave", 21, 220, 18),
			new NoFeeSquare("Park Güell", 22),
			new LotSquare("Indiana Ave", 23, 220, 18),
			new LotSquare("Illinois Ave", 24, 240, 20),
			new RailroadSquare("O Railroad", 25, 200, 20),
			new LotSquare("Atlantic Ave", 26, 260, 22),
			new LotSquare("Ventnor Ave", 27, 260, 22),
			new UtilitySquare("Water Works", 28, 150, 15),
			new LotSquare("Marvin Gardens Ave", 29, 280, 24),
			new NoFeeSquare("Griffith Park", 30),
			new LotSquare("Pacific Ave", 31, 300, 26),
			new LotSquare("North Carolina Ave", 32, 300, 26),
			new NoFeeSquare("Luxembourg Garden", 33),
			new LotSquare("Pennsylvania Ave", 34, 320, 28),
			new RailroadSquare("ShortLine", 35, 200, 20),
			new NoFeeSquare("Balboa Park", 36),
			new LotSquare("Park Place", 37, 350, 35),
			new TaxSquare("Luxury tax", 38, 100),
			new LotSquare("Boardwalk", 39, 400, 50)
		});
	}

}
