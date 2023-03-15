package uo.mp.project.monopoly;

import java.util.Random;

public class Dice {
	/**
	 * Rolls the dice, obtaining a value between one and 6
	 * 
	 * @return number between one or six (included)
	 */
	public int roll() {
		Random score = new Random();
		return score.nextInt(6)+1;
	}
}
