package uo.mp.project.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private Board board;
	private List<Player> players;
	private Dice dice1;
	private Dice dice2;
	private Scanner scanner;

	/**
	 * Starts up the game
	 */
	public void startUP() {
		dice1 = new Dice();
		dice2 = new Dice();
	}

	/**
	 * Adds players into the game
	 * 
	 * @param list of players who will play
	 */
	public void addPlayers(List<Player> list) {
		createListOfPlayers();
		for (Player person : list) {
			players.add(person);
		}
	}

	/**
	 * Creates the list of players
	 */
	private void createListOfPlayers() {
		players = new ArrayList<Player>();

	}

	/**
	 * Adds the squares to the board
	 * 
	 * @param board who will constitute the board to be played in
	 */
	public void addBoard(Board board) {
		createBoard();
		this.board = board;

	}

	/**
	 * Creates the board
	 */
	private void createBoard() {
		board = new Board();

	}

	/**
	 * Starts playing the game
	 */
	public void play() {
		System.out.println("Welcome to Monopoly Game");
		startUP();
		while (players.size() >= 2) {
			playTurn();
			checkBankrupcy(players);
		}
		gameOver();
	}

	/**
	 * Reads the enter key when the user enters it
	 */
	private void promptEnterKey() {
		System.out.print("	Press \"ENTER\" to roll the dice...");
		scanner = new Scanner(System.in);
		scanner.nextLine();
	}

	/**
	 * Plays a turn
	 */
	private void playTurn() {
		for (Player player : players) {
			System.out.println(
					"Turn " + player.getName() + " stands on " + board.getNthSquare(player.getSquare()).getName());
			int diceScore = dice1.roll() + dice2.roll();
			promptEnterKey();
			System.out.println("	..." + diceScore);
			positionChange(player, diceScore);
		}
	}

	/**
	 * Modifies the position of the player according to what it has been obtained in
	 * the dice
	 * 
	 * @param player    who is going to a new square
	 * @param diceScore obtained from the rolls of the dices
	 */
	private void positionChange(Player player, int diceScore) {
		int newPosition = player.getSquare() + diceScore;
		if (newPosition < board.getNumberOfSquares()) {
			player.goTo(board.getNthSquare(newPosition));
		} else {
			player.goTo(board.getNthSquare(newPosition - (board.getNumberOfSquares() - 1)));
		}
	}

	/**
	 * Checks if one of the players is in bankrupcy in order to remove they from the
	 * game. If all players but one are in bankrupcy, the game is over
	 * 
	 * @param list of players that are going to be checked
	 */
	private void checkBankrupcy(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isBankrupt()) {
				players.remove(players.get(i));
			}
		}
	}

	/**
	 * Ends the game
	 */
	private void gameOver() {
		System.out.println("End of game");
		for (Player player : players) {
			System.out.println("Player " + player.getName() + ": His net worth is " + player.sumOfAssets());
			System.out.println("And the winner is " + player.getName());
		}
	}
}
