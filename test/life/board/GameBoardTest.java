package life.board;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	private GameBoard classUndTest;
	private int[][] board1 = { 
			{ 1, 0, 0, 0, 1 },
			{ 0, 1, 0, 0, 0 },
			{ 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1 },
			{ 0, 1, 0, 0, 0 }};

	@Before
	public void setUp() throws Exception {
		classUndTest = new GameBoardImpl(board1, 5, 5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLiveNeighborsMiddleOfBoard() {
		int result = classUndTest.getLiveNeighbors(2, 2);
		assertEquals(3, result);
	}

	@Test
	public void testGetLiveNeighborsBottomLeftCornerOfBoard() {

		int result = classUndTest.getLiveNeighbors(0, 0);
		assertEquals(1, result);
	}
	
	@Test
	public void testGetLiveNeighborsBottomRightCornerOfBoard() {

		int result = classUndTest.getLiveNeighbors(0, 4);
		assertEquals(0, result);
	}
	@Test
	public void testGetLiveNeighborsTopLeftCornerOfBoard() {

		int result = classUndTest.getLiveNeighbors(4, 0);
		assertEquals(2, result);
	}
	@Test
	public void testGetLiveNeighborsTopRightCornerOfBoard() {

		int result = classUndTest.getLiveNeighbors(4, 4);
		assertEquals(2, result);
	}
	
	@Test
	public void testGetLiveNeighborsMidBoard() {

		int result = classUndTest.getLiveNeighbors(2, 1);
		assertEquals(3, result);
	}
	
}
