package uo.mp.project.monopoly.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uo.mp.project.monopoly.Board;
import uo.mp.project.monopoly.squares.NoFeeSquare;
import uo.mp.project.monopoly.squares.Square;

public class BoardTest {

	Square square1;
	Square square2;
	Square square3;
	ArrayList<Square> list;
	Board board;

	@Before
	public void setUp() throws Exception {
		square1 = new NoFeeSquare("Square1", 1);
		square2 = new NoFeeSquare("Square2", 2);
		square3 = new NoFeeSquare("Square3", 3);
		board = new Board();
		list = new ArrayList<Square>();
		list.add(square1);
		list.add(square2);
		list.add(square3);
	}

	/**
	 * Test the addSquares and getNth square, checking if they work
	 */
	@Test
	public void testAddSquaresAndGetNthSquare() {
		board.addSquares(list);
		assertEquals(board.getNthSquare(0), square1);
		assertEquals(board.getNthSquare(1), square2);
		assertEquals(board.getNthSquare(2), square3);

	}
}
