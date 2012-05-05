package life.board;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GameBoard classUndTest = new GameBoardImpl();
		
		int result = classUndTest.getLiveNeighbors(2, 2);
		assertEquals(3, result);
	}

}
